package com.whu.pojo.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EducationDistributionVO {
    private String education;
    private Long count;
}
