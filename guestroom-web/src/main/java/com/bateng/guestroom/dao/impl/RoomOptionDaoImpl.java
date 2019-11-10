package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.RoomOptionRepository;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("RoomOptionRepository")
public class RoomOptionDaoImpl implements RoomOptionRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List findRoomOptionVo(RoomOptionVo roomOptionVo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb =new StringBuilder();
        Map<String,Object> params = new HashMap<String,Object>();
        sb.append("select t1.roid,t1.createdate,t1.delflag,t1.roname,t1.updatetime,t1.pid,t1.rodes,t1.pinyin,t2.c,t2.nd,t2.xd from t_room_option t1 inner join (select t1.roid as roid, count(t2.did) as c , min(t2.actualdate) as nd,max(t2.actualdate) as xd from t_room_option t1 left outer join t_declaration_form  t2 on (t1.roid = t2.roomOption_id and t2.delflag=1 and t1.delflag=1)");
        sb.append(" where 1=1");
        if(roomOptionVo.getTime01()!=null){
            sb.append("  and t2.actualdate >:time01");
            params.put("time01",format.format(roomOptionVo.getTime01()));
        }
        if(roomOptionVo.getTime02()!=null){
            sb.append("  and t2.actualdate <:time02");
            params.put("time01",format.format(roomOptionVo.getTime02()));
        }
        if(roomOptionVo.getRoomOptionVo()!=null && roomOptionVo.getRoomOptionVo().getId()!=null &&roomOptionVo.getRoomOptionVo().getId()!=0){
            sb.append(" and t1.pid = :pid ");
            params.put("pid",roomOptionVo.getRoomOptionVo().getId());
        }
        sb.append(" group by t1.roid) as t2 on (t1.roid = t2.roid) order by t2.c desc");

        Query query = entityManager.createNativeQuery(sb.toString());
        //设置参数
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
        List<Object> list= query.getResultList();//查询的是总数据
        return list;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
