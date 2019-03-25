package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu,Integer> {


}
