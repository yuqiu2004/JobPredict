package com.whu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whu.pojo.entity.JobData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JobDataMapper extends BaseMapper<JobData> {

    @Select("SELECT salary FROM job_data where preference = #{preference} GROUP BY salary ORDER BY COUNT(*) DESC LIMIT 1")
    Double selectModeSalary(String preference);

    @Select("SELECT salary FROM job_data where qualification = #{qualificatioon} GROUP BY salary ORDER BY COUNT(*) DESC LIMIT 1")
    Double selectModeSalaryByQualification(String qualification);
}
