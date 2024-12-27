package com.whu.service;

import com.whu.pojo.dto.LoginDTO;
import com.whu.pojo.entity.User;

public interface UserService {
    User login(LoginDTO loginDTO);
}
