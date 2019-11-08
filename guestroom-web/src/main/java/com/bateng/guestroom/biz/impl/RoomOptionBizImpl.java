package com.bateng.guestroom.biz.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.*;
import com.bateng.guestroom.biz.RoomOptionBiz;
import com.bateng.guestroom.config.constant.AttachJsonTreeDWZ;
import com.bateng.guestroom.dao.RoomOptionDao;
import com.bateng.guestroom.entity.PageVo;
import com.bateng.guestroom.entity.RoomOption;
import com.bateng.guestroom.entity.vo.RoomOptionVo;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("roomOptionBiz")
public class RoomOptionBizImpl implements RoomOptionBiz {

    @Autowired
    private RoomOptionDao roomOptionDao;

    @Override
    public List<RoomOptionVo> findAllRoomOptionVo(RoomOptionVo roomOptionVo) {
        List list = roomOptionDao.findRoomOptionVo(roomOptionVo);
        List<RoomOptionVo> roomOptionVos = new ArrayList<RoomOptionVo>();
        for(Object obj:list){
            Object[] objs= (Object[]) obj;
            RoomOptionVo vo =new RoomOptionVo();
            vo.setId((Integer) objs[0]);
            vo.setCreateDate((Date) objs[1]);
            vo.setFlag((Integer) objs[2]);
            vo.setName((String) objs[3]);
            vo.setUpdateDate((Date) objs[4]);
            vo.setDesprition((String) objs[6]);
            vo.setCount(new Integer(((BigInteger) objs[8]).toString()));
            vo.setTime01((Date) objs[9]);
            vo.setTime02((Date) objs[10]);
            roomOptionVos.add(vo);
        }
        return roomOptionVos;
    }

    @Override
    public String findRoomOptionAjax() {
        List<RoomOption> roomOptions = roomOptionDao.findAllByFlag(1);
        return JSONObject.toJSONString(roomOptions, new SerializeFilter[]{new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                if (s.equals("createDate"))
                    return false;
                else if (s.equals("updateDate"))
                    return false;
                else if (s.equals("flag"))
                    return false;
                else if (s.equals("desprition"))
                    return false;
                else
                    return true;
            }
        }, new NameFilter() {
            @Override
            public String process(Object o, String s, Object o1) {
                if (s.equals("roomOption"))
                    return "pid";
                else
                    return s;
            }
        }, new ValueFilter() {
            @Override
            public Object process(Object o, String s, Object o1) {
                if (s.equals("pid"))
                    if (o1 != null)
                        return ((RoomOption) o1).getId();
                    else
                        return 0;
                else
                    return o1;
            }
        },new AfterFilter() {
            @Override
            public void writeAfter(Object o) {
                writeKeyValue("rel", AttachJsonTreeDWZ.ROOM_OPTION_TREE_REL);
                writeKeyValue("href", AttachJsonTreeDWZ.ROOM_OPTION_TREE_HREF);
            }
        }}, SerializerFeature.DisableCircularReferenceDetect);
    }

    @Override
    public PageVo<RoomOption> findByPageAndPid(int pageNUm, int pageSize, int pId) {
                                         //当前页码-1 //每页显示数量
        Pageable pageable=PageRequest.of(pageNUm-1,pageSize);
        Page<RoomOption> page=roomOptionDao.findPageByPid(pId,pageable);
        PageVo<RoomOption> pageVo=new PageVo<RoomOption>();
        pageVo.setContents(page.getContent());
        pageVo.setNumPerPage(pageSize);
        pageVo.setTotalCount(page.getTotalElements());
        pageVo.setTotalPages(page.getTotalPages());
        pageVo.setPageNum(pageNUm);
        return pageVo;
    }

    @Override
    public PageVo<RoomOption> findByPageAndPid(PageVo<RoomOption> pageVo, RoomOption roomOption) {
        Pageable pageable=PageRequest.of(pageVo.getPageNum()-1,pageVo.getNumPerPage());

        //带条件查询
        Page<RoomOption> page=roomOptionDao.findAll(new Specification<RoomOption>() {
            @Override
            public Predicate toPredicate(Root<RoomOption> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                 List<Predicate> predicates=new ArrayList<Predicate>();
                if(roomOption.getName()!=null && !roomOption.getName().trim().equals("")){
                    predicates.add(criteriaBuilder.like(root.get("name"),"%"+roomOption.getName().trim()+"%"));// name like '%值%'
                }
                if(roomOption.getRoomOption()!=null && roomOption.getRoomOption().getId()!=null){
                    predicates.add(criteriaBuilder.equal(root.get("roomOption").get("id"),roomOption.getRoomOption().getId()));//id=?
                }
                predicates.add(criteriaBuilder.equal(root.get("flag"),1));

                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }
        },pageable);

        PageVo<RoomOption> p=new PageVo<RoomOption>();
        p.setPageNum(pageVo.getPageNum());
        p.setTotalPages(page.getTotalPages());
        p.setNumPerPage(page.getSize());
        p.setContents(page.getContent());
        p.setTotalCount(page.getTotalElements());
        return p;
    }

    @Override
    public boolean findRoomOptionBypId(int pId) {
        List<RoomOption> list=roomOptionDao.findAllByPid(pId);
        return list.size()>0?true:false;

    }

    @Override
    public List<RoomOption> findRoomOptionByName(String name) {
        return roomOptionDao.findByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteRoomOptionById(int id) {
        roomOptionDao.deleteFlagById(id);
    }

    @Override
    public RoomOption getRoomOptionById(int id) {
        return roomOptionDao.findById(id).get();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoomOption(RoomOption roomOption) {
          roomOptionDao.save(roomOption);
    }

    public RoomOptionDao getRoomOptionDao() {
        return roomOptionDao;
    }

    public void setRoomOptionDao(RoomOptionDao roomOptionDao) {
        this.roomOptionDao = roomOptionDao;
    }
}
