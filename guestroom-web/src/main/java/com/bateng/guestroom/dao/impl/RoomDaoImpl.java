package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.RoomRepository;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.Room;
import com.bateng.guestroom.entity.RoomAndRoomLevel;
import com.bateng.guestroom.entity.vo.RoomVo;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("roomRepository")
public class RoomDaoImpl implements RoomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object> findAjaxIndex01(RoomVo roomVo) {
        Map<String,Object> params = new HashMap<String,Object>();
        StringBuffer sb =new StringBuffer();
        sb.append("select t1.rid ,t1.rname, count(t2.did) as c from t_room t1 left outer join t_declaration_form t2 on (t1.rid = t2.room_id and t1.delflag=1 and t2.delflag=1 ");//") GROUP BY t1.rid ,t1.rname";
        if(roomVo.getTime01()!=null){
            sb.append(" and t2.actualDate >= :time01");
            params.put("time01",roomVo.getTime01());
        }

        if(roomVo.getTime02()!=null){
            sb.append(" and t2.actualDate <= :time02");
            params.put("time02",roomVo.getTime02());
        }
        sb.append(" ) GROUP BY t1.rid ,t1.rname");
        Query query = entityManager.createNativeQuery(sb.toString());
         for(Map.Entry<String,Object> param :params.entrySet()){
            query.setParameter(param.getKey(),param.getValue());
        }

        return query.getResultList();
    }

    @Override
    public PageVo<Room> findRoomForRoomByPage(PageVo<Room> pageVo, Room room) {
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        StringBuilder sb  = new StringBuilder();
        sb.append("from Room r where 1=1");
        Query query = entityManager.createQuery(sb.toString());

        int max= query.getResultList().size();
        int pages=max%pageVo.getNumPerPage()==0?max/pageVo.getNumPerPage():(max/pageVo.getNumPerPage()+1);

        query.setMaxResults(pageVo.getNumPerPage());
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());
        pageVo.setContents(query.getResultList());
        pageVo.setTotalCount(max);
        pageVo.setTotalPages(pages);
        return pageVo;
    }

    @Override
    public PageVo<Room> findRoomForRoomLevelByPage(PageVo<Room> pageVo, RoomAndRoomLevel roomAndRoomLevel) {
        Map<String,Object> paramsMap=new HashMap<String,Object>();
        StringBuilder sb=new StringBuilder();
        sb.append("select rarl.room  from RoomAndRoomLevel rarl where rarl.roomLevel.id=:roomLevelId");
        paramsMap.put("roomLevelId",roomAndRoomLevel.getRoomLevel().getId());
        if(roomAndRoomLevel.getRoom()!=null){
            if(roomAndRoomLevel.getRoom().getName()!=null && !"".equals(roomAndRoomLevel.getRoom().getName().trim()) ){
                sb.append(" and rarl.room.name like :roomName");
                paramsMap.put("roomName","%"+roomAndRoomLevel.getRoom().getName()+"%");
            }
        }
          sb.append(" order by rarl.room.id desc");
        Query query=entityManager.createQuery(sb.toString());
          //设置参数
          for(Map.Entry<String,Object> entry:paramsMap.entrySet()){
              query.setParameter(entry.getKey(),entry.getValue());
          }
          int max= query.getResultList().size();
          int pages=max%pageVo.getNumPerPage()==0?max/pageVo.getNumPerPage():(max/pageVo.getNumPerPage()+1);
          query.setMaxResults(pageVo.getNumPerPage());
          query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());



          pageVo.setContents(query.getResultList());
          pageVo.setTotalCount(max);
          pageVo.setTotalPages(pages);
        return pageVo;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
