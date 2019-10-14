package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.AppointForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointFormDao extends JpaRepository<AppointForm,Integer> {

    @Query("from AppointForm  af where af.declarationForm.id = :id order by  af.createDate asc ")
    public List<AppointForm> findAllByDeclarationFormId(int id);
}
