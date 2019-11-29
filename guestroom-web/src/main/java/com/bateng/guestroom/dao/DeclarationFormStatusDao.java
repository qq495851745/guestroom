package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.DeclarationFormStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationFormStatusDao extends JpaRepository<DeclarationFormStatus,Integer> {


}
