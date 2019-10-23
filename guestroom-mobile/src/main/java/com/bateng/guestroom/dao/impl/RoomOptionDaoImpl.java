package com.bateng.guestroom.dao.impl;

import com.bateng.guestroom.config.util.PinyinUtil;
import com.bateng.guestroom.dao.repository.RoomOptionRepository;
import com.bateng.guestroom.entity.RoomOption;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("roomOptionRepository")
public class RoomOptionDaoImpl implements RoomOptionRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
