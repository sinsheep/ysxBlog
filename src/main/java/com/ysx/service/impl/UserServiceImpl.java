package com.ysx.service.impl;

import com.ysx.dao.UserRepository;
import com.ysx.po.User;
import com.ysx.service.UserService;
import com.ysx.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findAllByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
