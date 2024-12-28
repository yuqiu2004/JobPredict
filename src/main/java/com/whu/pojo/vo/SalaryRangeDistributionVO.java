package com.whu.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SalaryRangeDistributionVO {
    private String salaryRange; // 薪资区间 (如 "5k-10k", "10k-15k")
    private Long count;
}

