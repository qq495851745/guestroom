package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.DeclarationFormRepository;
import com.bateng.guestroom.entity.DeclarationForm;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("declarationFormRepository")
public class DeclarationFormDaoImpl implements DeclarationFormRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage1(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {
        StringBuilder sb=new StringBuilder();
        Map<String,Object> params=new HashMap<String,Object>();
       sb.append("from DeclarationForm df where df.id in (select rf.declarationForm.id from RepairForm rf group by  rf.declarationForm.id having count(*) >=2) ");

        if(declarationForm!=null && declarationForm.getUser()!=null && declarationForm.getUser().getId()!= null && declarationForm.getUser().getId()!= 0 ) {
            sb.append(" and df.user.id = :uid ");
            params.put("uid",declarationForm.getUser().getId());
        }

        if(declarationForm.getFlag() != null){
            sb.append(" and df.flag = :flag");
            params.put("flag",declarationForm.getFlag());
        }

        if(declarationForm.getFormName() !=null && !declarationForm.getFormName().equals("")){
            sb.append(" and df.formName like :formName");
            params.put("formName","%"+declarationForm.getFormName()+"%");
        }
        if(declarationForm.getRoom()!=null && declarationForm.getRoom().getName()!=null && !declarationForm.getRoom().getName().equals("")){
            sb.append(" and df.room.name = :roomName ");
            params.put("roomName",declarationForm.getRoom().getName());
        }

        //获取是否有状态
        if(declarationForm.getDeclarationFormStatus() !=null && declarationForm.getDeclarationFormStatus().getId() != 0){
            sb.append(" and df.declarationFormStatus.id = :declarationFormStatusId");
            params.put("declarationFormStatusId",declarationForm.getDeclarationFormStatus().getId());
        }

        //查看搜索条件
        if(declarationForm.getDeclarationFormStatusList() !=null && declarationForm.getDeclarationFormStatusList().size()!=0){
            StringBuilder ps=new StringBuilder(" ( ");
            for(int i=0;i<declarationForm.getDeclarationFormStatusList().size();i++){
                ps.append(declarationForm.getDeclarationFormStatusList().get(i));
                if(i!= declarationForm.getDeclarationFormStatusList().size()-1)
                    ps.append(",");
            }
            ps.append(" ) ");
            sb.append(" and df.declarationFormStatus.id in ");
            sb.append(ps);
        }

        //委派条件
        if(declarationForm.getAppointForm()!=null && declarationForm.getAppointForm().getId()!=0){
            sb.append(" and df.appointForm.id = :appointFormId");
            params.put("appointFormId",declarationForm.getAppointForm().getId());
        }


        sb.append("  order by  df.roomOption.id , df.createDate");
        Query query=entityManager.createQuery(sb.toString());//生成查询对象

        //设置参数
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }

        //计算总页数，总数量
        List<DeclarationForm> list= query.getResultList();//查询的是总数据
        pageVo.setTotalCount(list.size());//设置总数据
        pageVo.setTotalPages((int) (pageVo.getTotalCount()%pageVo.getNumPerPage()==0?pageVo.getTotalCount()/pageVo.getNumPerPage():(pageVo.getTotalCount()/pageVo.getNumPerPage()+1)));
        //设置总页数

        query.setMaxResults(pageVo.getNumPerPage());//设置查询数量
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());//设置哪里开始取数据

        //query.getResultList();//获取查询结果

        pageVo.setContents( query.getResultList());//数据结果


        return pageVo;
    }

    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {
        StringBuilder sb=new StringBuilder();
        Map<String,Object> params=new HashMap<String,Object>();
        sb.append("from DeclarationForm df where 1=1");

        if(declarationForm!=null && declarationForm.getUser()!=null && declarationForm.getUser().getId()!= null && declarationForm.getUser().getId()!= 0 ) {
            sb.append(" and df.user.id = :uid ");
            params.put("uid",declarationForm.getUser().getId());
        }

        if(declarationForm.getFlag() != null){
            sb.append(" and df.flag = :flag");
            params.put("flag",declarationForm.getFlag());
        }

        if(declarationForm.getRoomOption()!=null && !declarationForm.getRoomOption().getName().equals("")){
            sb.append(" and df.roomOption.name like :roomOptionName");
            params.put("roomOptionName","%"+declarationForm.getRoomOption().getName()+"%");

        }


        if(declarationForm.getFormName() !=null && !declarationForm.getFormName().equals("")){
            sb.append(" and df.formName like :formName");
            params.put("formName","%"+declarationForm.getFormName()+"%");
        }
        if(declarationForm.getRoom()!=null && declarationForm.getRoom().getName()!=null && !declarationForm.getRoom().getName().equals("")){
            sb.append(" and df.room.name = :roomName ");
            params.put("roomName",declarationForm.getRoom().getName());
        }

        //获取是否有状态
        if(declarationForm.getDeclarationFormStatus() !=null && declarationForm.getDeclarationFormStatus().getId() != 0){
            sb.append(" and df.declarationFormStatus.id = :declarationFormStatusId");
            params.put("declarationFormStatusId",declarationForm.getDeclarationFormStatus().getId());
        }

        //获取报修内容数据
        if(declarationForm.getForNameOption()!=null &&declarationForm.getForNameOption().getName()!=null && !declarationForm.getForNameOption().getName().equals("")){
            sb.append(" and df.forNameOption.name like :forNameOptionName");
            params.put("forNameOptionName","%"+declarationForm.getForNameOption().getName()+"%");
        }

        //根据报修时间搜索
        if(declarationForm.getTime01()!=null){
            sb.append("  and df.actualDate >= :time01");
            params.put("time01",declarationForm.getTime01());
        }

        if(declarationForm.getTime02() != null){
            sb.append("  and df.actualDate <= :time02");
            params.put("time02",declarationForm.getTime02());
        }

        //查看搜索条件
        if(declarationForm.getDeclarationFormStatusList() !=null && declarationForm.getDeclarationFormStatusList().size()!=0){
            StringBuilder ps=new StringBuilder(" ( ");
            for(int i=0;i<declarationForm.getDeclarationFormStatusList().size();i++){
                ps.append(declarationForm.getDeclarationFormStatusList().get(i));
                if(i!= declarationForm.getDeclarationFormStatusList().size()-1)
                    ps.append(",");
            }
            ps.append(" ) ");
            sb.append(" and df.declarationFormStatus.id in ");
            sb.append(ps);
        }



        //委派条件
        if(declarationForm.getAppointForm()!=null && declarationForm.getAppointForm().getId()!=0){
            sb.append(" and df.appointForm.id = :appointFormId");
            params.put("appointFormId",declarationForm.getAppointForm().getId());
        }


        sb.append("  order by  df.roomOption.id , df.actualDate");
        Query query=entityManager.createQuery(sb.toString());//生成查询对象

        //设置参数
         for(Map.Entry<String,Object> entry:params.entrySet()){
             query.setParameter(entry.getKey(),entry.getValue());
         }

        //计算总页数，总数量
        List<DeclarationForm> list= query.getResultList();//查询的是总数据
        pageVo.setTotalCount(list.size());//设置总数据
        pageVo.setTotalPages((int) (pageVo.getTotalCount()%pageVo.getNumPerPage()==0?pageVo.getTotalCount()/pageVo.getNumPerPage():(pageVo.getTotalCount()/pageVo.getNumPerPage()+1)));
        //设置总页数

        query.setMaxResults(pageVo.getNumPerPage());//设置查询数量
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());//设置哪里开始取数据

        //query.getResultList();//获取查询结果

        pageVo.setContents( query.getResultList());//数据结果

        return pageVo;
    }

    @Override
    public PageVo<DeclarationForm> findDeclarationFormByPage4(PageVo<DeclarationForm> pageVo, DeclarationForm declarationForm) {
        StringBuilder sb=new StringBuilder();
        Map<String,Object> params=new HashMap<String,Object>();
       sb.append("select df.room.id, df.forNameOption.id , count(*) as count1,min(df.createDate) ,max(df.createDate)   from DeclarationForm df where 1=1 ");
//        sb.append(" select * from ");

        //根据房号
        if(declarationForm.getRoom()!=null && declarationForm.getRoom().getName()!=null && !declarationForm.getRoom().getName().equals("")){
            sb.append(" and df.room.name = :roomName ");
            params.put("roomName",declarationForm.getRoom().getName());
        }
        //获取报修内容数据
        if(declarationForm.getForNameOption()!=null &&declarationForm.getForNameOption().getName()!=null && !declarationForm.getForNameOption().getName().equals("")){
            sb.append(" and df.forNameOption.name = :forNameOptionName");
            params.put("forNameOptionName",declarationForm.getForNameOption().getName());
        }

        //根据报修时间搜索
        if(declarationForm.getTime01()!=null){
            sb.append("  and df.actualDate >= :time01");
            params.put("time01",declarationForm.getTime01());
        }

        if(declarationForm.getTime02() != null){
            sb.append("  and df.actualDate <= :time02");
            params.put("time02",declarationForm.getTime02());
        }


        sb.append(" group by df.room.id,df.forNameOption.id ");

        sb.append("having count(*) !=0 ");
        if( declarationForm.getCount01()!=null && declarationForm.getCount01()!=0){
            sb.append(" and count(*) >= :count01");
            params.put("count01",declarationForm.getCount01());
        }

        if(declarationForm.getCount02()!=null && declarationForm.getCount02() !=0){
            sb.append(" and  count(*) <= :count02");
            params.put("count02",declarationForm.getCount02());
        }

        Query query=entityManager.createQuery(sb.toString());//生成查询对象

        //设置参数
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
        List<Object> list= query.getResultList();//查询的是总数据

        pageVo.setTotalCount(list.size());//设置总数据
        pageVo.setTotalPages((int) (pageVo.getTotalCount()%pageVo.getNumPerPage()==0?pageVo.getTotalCount()/pageVo.getNumPerPage():(pageVo.getTotalCount()/pageVo.getNumPerPage()+1)));
        //设置总页数

        query.setMaxResults(pageVo.getNumPerPage());//设置查询数量
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());//设置哪里开始取数据

        //query.getResultList();//获取查询结果

        pageVo.setContents( convert(query.getResultList()));//数据结果


        return pageVo;
    }

    /**
     * 将集合进行转换
     * @param list
     * @return
     */
    private List<DeclarationForm> convert(List<Object> list){
        //计算总页数，总数量

        StringBuilder sb = new StringBuilder();
        sb.append("from DeclarationForm df where df.room.id =?1 and df.forNameOption.id =?2");
        List<DeclarationForm> declarationForms = new ArrayList<DeclarationForm>();

        for(int i=0;i<list.size();i++){
            Object[] objs = (Object[]) list.get(i);
            Query query = entityManager.createQuery(sb.toString());
            query.setParameter(1,objs[0]);
            query.setParameter(2,objs[1]);
            List<DeclarationForm> forms = query.getResultList();
            DeclarationForm declarationForm = forms.size()>0?forms.get(0):null;
            declarationForm.setCount(((Long) objs[2]).intValue());
            declarationForm.setTime01((Date) objs[3]);
            declarationForm.setTime02((Date) objs[4]);
            declarationForms.add(declarationForm);

        }
        return  declarationForms;
    }

    @Override
    public List<DeclarationForm> findDeclarationForms4(DeclarationForm declarationForm) {
        StringBuilder sb=new StringBuilder();
        Map<String,Object> params=new HashMap<String,Object>();
        sb.append("select df.room.id, df.forNameOption.id , count(*) as count1,min(df.createDate) ,max(df.createDate)   from DeclarationForm df where 1=1 ");
//        sb.append(" select * from ");

        //根据房号
        if(declarationForm.getRoom()!=null && declarationForm.getRoom().getName()!=null && !declarationForm.getRoom().getName().equals("")){
            sb.append(" and df.room.name = :roomName ");
            params.put("roomName",declarationForm.getRoom().getName());
        }
        //获取报修内容数据
        if(declarationForm.getForNameOption()!=null &&declarationForm.getForNameOption().getName()!=null && !declarationForm.getForNameOption().getName().equals("")){
            sb.append(" and df.forNameOption.name = :forNameOptionName");
            params.put("forNameOptionName",declarationForm.getForNameOption().getName());
        }

        //根据报修时间搜索
        if(declarationForm.getTime01()!=null){
            sb.append("  and df.createDate >= :time01");
            params.put("time01",declarationForm.getTime01());
        }

        if(declarationForm.getTime02() != null){
            sb.append("  and df.createDate <= :time02");
            params.put("time02",declarationForm.getTime02());
        }


        sb.append(" group by df.room.id,df.forNameOption.id ");

        sb.append("having count(*) !=0 ");
        if( declarationForm.getCount01()!=null && declarationForm.getCount01()!=0){
            sb.append(" and count(*) >= :count01");
            params.put("count01",declarationForm.getCount01());
        }

        if(declarationForm.getCount02()!=null && declarationForm.getCount02() !=0){
            sb.append(" and  count(*) <= :count02");
            params.put("count02",declarationForm.getCount02());
        }

        Query query=entityManager.createQuery(sb.toString());//生成查询对象

        //设置参数
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
       // List<Object> list= query.getResultList();//查询的是总数据
        return convert(query.getResultList());
    }

    /**
     * 查询所有维修记录两次以上的房间
     * @param declarationForm
     * @return
     */
    @Override
    public List<DeclarationForm> findDeclarationForms1(DeclarationForm declarationForm) {
        StringBuilder sb=new StringBuilder();
        Map<String,Object> params=new HashMap<String,Object>();
        sb.append("from DeclarationForm df where df.id in (select rf.declarationForm.id from RepairForm rf group by  rf.declarationForm.id having count(*) >=2) ");
        if(declarationForm.getFlag() != null){
            sb.append(" and df.flag = :flag");
            params.put("flag",declarationForm.getFlag());
        }

        if(declarationForm.getRoom()!=null && declarationForm.getRoom().getName()!=null && !declarationForm.getRoom().getName().equals("")){
            sb.append(" and df.room.name = :roomName ");
            params.put("roomName",declarationForm.getRoom().getName());
        }
        sb.append("  order by  df.roomOption.id , df.createDate");
        Query query=entityManager.createQuery(sb.toString());//生成查询对象
        //设置参数
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public List<DeclarationForm> findDeclarationForms(DeclarationForm declarationForm) {
        StringBuilder sb=new StringBuilder();
        Map<String,Object> params=new HashMap<String,Object>();
        sb.append("from DeclarationForm df where 1=1");
        if(declarationForm.getFlag() != null){
            sb.append(" and df.flag = :flag");
            params.put("flag",declarationForm.getFlag());
        }

        if(declarationForm.getRoom()!=null && declarationForm.getRoom().getName()!=null && !declarationForm.getRoom().getName().equals("")){
            sb.append(" and df.room.name = :roomName ");
            params.put("roomName",declarationForm.getRoom().getName());
        }
        //获取报修内容数据
        if(declarationForm.getForNameOption()!=null &&declarationForm.getForNameOption().getName()!=null && !declarationForm.getForNameOption().getName().equals("")){
            sb.append(" and df.forNameOption.name = :forNameOptionName");
            params.put("forNameOptionName",declarationForm.getForNameOption().getName());
        }

        //根据报修时间搜索
        if(declarationForm.getTime01()!=null){
            sb.append("  and df.createDate >= :time01");
            params.put("time01",declarationForm.getTime01());
        }

        if(declarationForm.getTime02() != null){
            sb.append("  and df.createDate <= :time02");
            params.put("time02",declarationForm.getTime02());
        }
        sb.append("  order by  df.roomOption.id , df.createDate");
        Query query=entityManager.createQuery(sb.toString());//生成查询对象
        //设置参数
        for(Map.Entry<String,Object> entry:params.entrySet()){
            query.setParameter(entry.getKey(),entry.getValue());
        }
        return query.getResultList();
    }

    @Override
    public PageVo findByRoomOptionCountByPage(PageVo<DeclarationForm> pageVo, RoomOptionVo roomOptionVo) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sb =new StringBuilder();
        Map<String,Object> params = new HashMap<String,Object>();
        sb.append("select t1.roid,t1.createdate,t1.delflag,t1.roname,t1.updatetime,t1.pid,t1.rodes,t1.pinyin,t2.c from t_room_option t1 inner join (select t1.roid as roid, count(t2.did) as c from t_room_option t1 left outer join t_declaration_form  t2 on (t1.roid = t2.roomOption_id and t2.delflag=1 and t1.delflag=1 )");
        sb.append(" where 1=1");
        if(roomOptionVo.getTime01()!=null){
            sb.append("  and t2.actualdate >:time01");
            params.put("time01",format.format(roomOptionVo.getTime01()));
        }
        if(roomOptionVo.getTime02()!=null){
            sb.append("  and t2.actualdate <:time02");
            params.put("time01",format.format(roomOptionVo.getTime02()));
        }
        if(roomOptionVo.getRoomOptionVo()!=null && roomOptionVo.getRoomOptionVo().getId()!=0){
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

        pageVo.setTotalCount(list.size());//设置总数据
        pageVo.setTotalPages((int) (pageVo.getTotalCount()%pageVo.getNumPerPage()==0?pageVo.getTotalCount()/pageVo.getNumPerPage():(pageVo.getTotalCount()/pageVo.getNumPerPage()+1)));
        //设置总页数

        query.setMaxResults(pageVo.getNumPerPage());//设置查询数量
        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());//设置哪里开始取数据

        //query.getResultList();//获取查询结果

        pageVo.setContents( query.getResultList());//数据结果


        return pageVo;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
