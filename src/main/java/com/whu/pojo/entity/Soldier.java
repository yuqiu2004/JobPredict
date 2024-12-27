package com.whu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Soldier {
    private Integer id;
    private String name;
    private Integer gender;
    private LocalDateTime birth;
    private String birthPlace;
    private String resettlement;
    private String img;
    private String militaryPositions;
    private String experience;
}
