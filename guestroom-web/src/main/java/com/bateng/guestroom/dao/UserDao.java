package com.bateng.guestroom.dao;

import com.bateng.guestroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

public User findByPasswordAndUsername(String password,String useranme);

public User findByPasswordAndUsernameAndFlag(String password,String username,int flag);
}
