package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends JpaRepository<Role,Integer> {

    public List<Role> findAllByFlag(int flag);
}
