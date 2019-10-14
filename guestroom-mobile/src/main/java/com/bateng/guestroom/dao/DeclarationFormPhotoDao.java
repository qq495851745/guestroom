package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.DeclarationFormPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationFormPhotoDao extends JpaRepository<DeclarationFormPhoto,Integer> {
}
