package com.whu.service;

import com.whu.pojo.vo.EducationDistributionVO;
import com.whu.pojo.vo.EducationSalaryVO;
import com.whu.pojo.vo.GenderSalaryVO;
import com.whu.pojo.vo.SalaryRangeDistributionVO;

import java.util.List;

public interface JobDataService {
    List<GenderSalaryVO> salaryGender();

    List<EducationSalaryVO> salaryEdu();

    List<EducationDistributionVO> distributionEdu();

    List<SalaryRangeDistributionVO> distributionSalary();
}
