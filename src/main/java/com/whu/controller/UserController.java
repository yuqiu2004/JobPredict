package com.whu.controller;

import com.whu.constant.JwtClaimsConstant;
import com.whu.pojo.dto.LoginDTO;
import com.whu.pojo.entity.User;
import com.whu.pojo.vo.LoginVO;
import com.whu.pojo.vo.R;
import com.whu.properties.JwtProperties;
import com.whu.service.UserService;
import com.whu.utils.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public R login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSecretKey(),
                jwtProperties.getTtl(),
                claims);

        LoginVO loginVO = LoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .token(token)
                .build();

        return R.success(loginVO);
    }
}
