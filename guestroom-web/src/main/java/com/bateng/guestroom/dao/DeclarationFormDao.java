package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.DeclarationFormRepository;
import com.bateng.guestroom.entity.DeclarationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationFormDao extends JpaRepository<DeclarationForm,Integer>, DeclarationFormRepository {
}
