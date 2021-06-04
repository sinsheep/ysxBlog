package com.ysx.service;

import com.ysx.po.User;

public interface UserService {

    User checkUser(String username, String password);
}
