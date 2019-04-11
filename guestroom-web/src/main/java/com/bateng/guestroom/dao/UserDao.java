package com.bateng.guestroom.dao;

import com.bateng.guestroom.dao.repository.UserRepository;
import com.bateng.guestroom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer>, UserRepository {

public User findByPasswordAndUsername(String password,String useranme);

public User findByPasswordAndUsernameAndFlag(String password,String username,int flag);

public List<User> findAllByUsername(String username);
}
