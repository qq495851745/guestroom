package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.DeclarationFormRepository;
import com.bateng.guestroom.entity.DeclarationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationFormDao extends JpaRepository<DeclarationForm,Integer>, DeclarationFormRepository {

    //修改删除标记
    @Modifying
    @Query("update DeclarationForm df set df.flag=:flag where df.id=:id")
    public void updateByFlag(@Param("flag") int flag, @Param("id") int id);
}
