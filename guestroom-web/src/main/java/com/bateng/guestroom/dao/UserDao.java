package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.UserRepository;
import com.bateng.guestroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, UserRepository {

    public User findByPasswordAndUsername(String password, String useranme);

    public User findByPasswordAndUsernameAndFlag(String password, String username, int flag);


    public List<User> findAllByUsername(String username);

    @Query("update User u set u.role.id= :roleId ,u.userLevel.id =:userLevelId, u.updateDate =:updateDate  where u.id= :id")
    @Modifying
    public void updateUser(int roleId, int userLevelId, Date updateDate, int id);

    @Query("update User u set u.role.id= :roleId ,u.userLevel.id =:userLevelId, u.updateDate =:updateDate , u.password = :password  where u.id= :id")
    @Modifying
    public void updateUser(int roleId, int userLevelId, Date updateDate, int id, String password);

    public List<User> findByUserLevelId(int id);
}
