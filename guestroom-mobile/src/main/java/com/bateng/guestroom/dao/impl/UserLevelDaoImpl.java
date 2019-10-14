package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.dao.repository.UserLevelRepository;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.UserLevel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userLevelRepository")
public class UserLevelDaoImpl implements UserLevelRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageVo<UserLevel> findUserLevelByPage(PageVo<UserLevel> pageVo, UserLevel userLevel) {
        Map<String,Object> params=new HashMap<String,Object>();
        if(userLevel!=null && userLevel.getUserLevel()!=null){
            params.put("pid",userLevel.getUserLevel().getId());
        }
        if(userLevel!=null && userLevel.getName()!=null){
            params.put("name","%"+userLevel.getName()+"%");
        }
        StringBuilder sb=new StringBuilder();
        sb.append("from UserLevel ul where 1=1");
        if(params.get("pid")!=null)
            sb.append(" and ul.userLevel.id=:pid ");
        if(params.get("name")!=null){
            sb.append("and ul.name like :name");
        }
        sb.append(" order by ul.createDate asc");

        Query query=entityManager.createQuery(sb.toString());
        for(Map.Entry<String,Object> param:params.entrySet()){
            query.setParameter(param.getKey(),param.getValue());
        }

        int max=query.getResultList().size();
        int pages=max%pageVo.getNumPerPage()==0?max/pageVo.getNumPerPage():(max/ pageVo.getNumPerPage()+1);

        query.setFirstResult((pageVo.getPageNum()-1)*pageVo.getNumPerPage());
        query.setMaxResults(pageVo.getNumPerPage());

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
