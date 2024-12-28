package com.whu.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("job_data")
public class JobData {
    @TableId("id")
    private Long id;
    private String qualification;
    private Long salary;
    private Long companySize;
    private String preference;
    private String skills;
    private String responsibilities;
}
