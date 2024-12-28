package com.whu.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenderSalaryVO {
    private String gender;
    private Double averageSalary;   // 平均薪资
    private Double medianSalary;    // 薪资众数
}
