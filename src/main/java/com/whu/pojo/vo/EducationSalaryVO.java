package com.whu.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationSalaryVO {
    private String education;
    private Double averageSalary;
    private Double medianSalary;
}
