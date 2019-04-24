package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.AppointForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointFormDao extends JpaRepository<AppointForm,Integer> {
}
