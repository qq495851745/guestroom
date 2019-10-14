package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.RepairForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairFormDao extends JpaRepository<RepairForm,Integer> {

    @Query("from RepairForm  rf where rf.declarationForm.id=:id order by  rf.createDate asc")
    public List<RepairForm> findAllByDeclarationFormId(int id);
}
