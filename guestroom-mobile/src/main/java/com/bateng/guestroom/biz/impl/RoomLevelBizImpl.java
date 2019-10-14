package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.RoomLevelBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.dao.RoomLevelDao;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomLevel;
import com.bateng.guestroom.entity.RoomOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("roomLevelBiz")
public class RoomLevelBizImpl implements RoomLevelBiz {
    @Autowired
    private RoomLevelDao roomLevelDao;
    @Override
    public List<RoomLevel> findAllByFlag(int flag) {
        return  roomLevelDao.findAllByFlag(flag);
    }


    @Override
    public String findAllByFlagAjax(int flag) {
        return JSON.toJSONString(roomLevelDao.findAllByFlag(flag),new SerializeFilter[]{new RoomLevel.NameFilter(),new RoomLevel.PropertyFilter(),new RoomLevel.ValueFilter(),new AfterFilter() {
            @Override
            public void writeAfter(Object o) {
                writeKeyValue("rel", AttachJsonTreeDWZ.ROOM_LEVEL_TREE_REL);
                writeKeyValue("href", AttachJsonTreeDWZ.ROOM_LEVEL_TREE_HREF);
            }
        }}, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public String findAllRoomLevelAjax(int flag, Map<String, String> rel) {
         return JSON.toJSONString(roomLevelDao.findAllByFlag(flag),new SerializeFilter[]{new RoomLevel.NameFilter(),new RoomLevel.PropertyFilter(),new RoomLevel.ValueFilter(),new AfterFilter() {
            @Override
            public void writeAfter(Object o) {
                writeKeyValue("rel", rel.get("rel"));
                writeKeyValue("href", rel.get("href"));
            }
        }}, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public PageVo<RoomLevel> findALLByPageAndCon(PageVo<RoomLevel> pageVo, RoomLevel roomLevel) {
        //分页条件
        Pageable pageable= PageRequest.of(pageVo.getPageNum()-1,pageVo.getNumPerPage());
        //按条件查询
        Page<RoomLevel> page= roomLevelDao.findAll(new Specification<RoomLevel>() {
            @Override
            public Predicate toPredicate(Root<RoomLevel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<Predicate>();//查询条件
                  //添加有效条件
                predicates.add(criteriaBuilder.equal(root.get("flag"),roomLevel.getFlag()));
                  //根据父亲条件
                    if(roomLevel.getRoomLevel()!=null){
                        Predicate predicate=criteriaBuilder.equal(root.get("roomLevel").get("id"),roomLevel.getRoomLevel().getId());
                        predicates.add(predicate);
                    }

                   if("".equals(roomLevel.getName())==false &&  roomLevel.getName()!=null){
                       predicates.add(criteriaBuilder.like(root.get("name"),"%"+roomLevel.getName()+"%"));
                   }


                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }
        }, pageable);

        pageVo.setTotalCount(page.getTotalElements());
        pageVo.setTotalPages(page.getTotalPages());
        pageVo.setContents(page.getContent());


        return  pageVo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveRoomLevel(RoomLevel roomLevel) {
        //添加附加属性
        roomLevel.setFlag(1);
        roomLevel.setCreateDate(new Date());
        roomLevel.setUpdateDate(new Date());
        roomLevelDao.save(roomLevel);
    }

    @Override
    public boolean isFindChild(int id) {
        List<RoomLevel> list=roomLevelDao.findRoomLevelBypId(id);
        return list.size()>0?true:false;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRoomLevelById(int id) {
        roomLevelDao.deleteRoomLevelById(id);
    }

    @Override
    public RoomLevel getRoomLevelById(int id) {
        return roomLevelDao.getOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRoomLevel(RoomLevel roomLevel) {
         roomLevelDao.updateRoomLevel(roomLevel.getName(),roomLevel.getDes(),roomLevel.getUpdateDate(),roomLevel.getId());
    }

    public RoomLevelDao getRoomLevelDao() {
        return roomLevelDao;
    }

    public void setRoomLevelDao(RoomLevelDao roomLevelDao) {
        this.roomLevelDao = roomLevelDao;
    }
}
