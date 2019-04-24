package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.RepairForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairFormDao extends JpaRepository<RepairForm,Integer> {
}
