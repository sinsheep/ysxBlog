package com.ysx.dao;

import com.ysx.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findAllByUsernameAndPassword(String username, String password);
}
