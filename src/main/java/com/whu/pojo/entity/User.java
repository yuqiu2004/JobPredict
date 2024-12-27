package com.whu.pojo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String name;
    private Long phone;
    private Integer gender;
    private String username;
    private String password;
}
