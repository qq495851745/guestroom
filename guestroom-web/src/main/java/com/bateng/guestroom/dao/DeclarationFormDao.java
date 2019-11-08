package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.DeclarationFormRepository;
import com.bateng.guestroom.entity.DeclarationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeclarationFormDao extends JpaRepository<DeclarationForm,Integer>, DeclarationFormRepository {

    @Modifying
    @Query("update  DeclarationForm  df set df.finishDate=:finishDate where df.id = :declarationFormId")
    public void updateFinishDate(Integer declarationFormId,Date finishDate);

    //修改删除标记
    @Modifying
    @Query("update DeclarationForm df set df.flag=:flag where df.id=:id")
    public void updateByFlag(@Param("flag") int flag, @Param("id") int id);

    @Modifying
    @Query("update DeclarationForm  df set df.formName=:formName,df.updateDate=:updateDate,df.description=:description" +
            ",df.roomOption.id=:roomOptionId,df.room.id=:roomId ,df.finishDate=:finishDate where df.id=:id")
    public void updateDeclaration(String formName, Date updateDate,String description,int roomOptionId,int roomId,int id,Date finishDate);

    @Modifying
    @Query("update DeclarationForm  df set df.appointForm.id = :appointFormId where df.id=:id")
    public void updateDeclarationForm(int appointFormId,int id);

    @Modifying
    @Query("update DeclarationForm  df set df.declarationFormStatus.id = :statusId where df.id=:id")
    public void updateDeclarationForm2(int statusId,int id);

}
