package com.whu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whu.constant.MessageConstant;
import com.whu.exception.UserException;
import com.whu.mapper.UserMapper;
import com.whu.pojo.dto.LoginDTO;
import com.whu.pojo.entity.User;
import com.whu.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UserException(MessageConstant.USER_NOT_FOUND);
        }
        //进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new UserException(MessageConstant.PASSWORD_ERROR);
        }
        //3、返回实体对象
        return user;
    }
}
