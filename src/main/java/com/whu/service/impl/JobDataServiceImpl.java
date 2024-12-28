package com.whu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.whu.mapper.JobDataMapper;
import com.whu.pojo.vo.EducationDistributionVO;
import com.whu.pojo.vo.EducationSalaryVO;
import com.whu.pojo.vo.GenderSalaryVO;
import com.whu.pojo.vo.SalaryRangeDistributionVO;
import com.whu.service.JobDataService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobDataServiceImpl implements JobDataService {

    @Resource
    private JobDataMapper jobDataMapper;

    @Override
    public List<GenderSalaryVO> salaryGender() {
        List<GenderSalaryVO> res = new ArrayList<>();
        List<String> genders = List.of("Female", "Male", "Both");
        QueryWrapper queryWrapper;
        for (String gender : genders) {
            queryWrapper = new QueryWrapper<Double>().eq("preference", gender)
                    .select("AVG(salary) AS avg_salary");
            List<Double> list = jobDataMapper.selectObjs(queryWrapper);
            Double modeSalary = jobDataMapper.selectModeSalary();
            GenderSalaryVO vo = GenderSalaryVO.builder().gender(gender)
                    .averageSalary(list.get(0))
                    .medianSalary(modeSalary)
                    .build();
            res.add(vo);
        }
        return res;
    }

    @Override
    public List<EducationSalaryVO> salaryEdu() {
        List<EducationSalaryVO> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            res.add(EducationSalaryVO.builder()
                            .education("学士")
                            .averageSalary(10000.0)
                            .medianSalary(100000.0)
                    .build());
        }
        return res;
    }

    @Override
    public List<EducationDistributionVO> distributionEdu() {
        List<EducationDistributionVO> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            res.add(EducationDistributionVO.builder()
                            .education("硕士")
                            .count(500L)
                    .build());
        }
        return res;
    }

    @Override
    public List<SalaryRangeDistributionVO> distributionSalary() {
        List<SalaryRangeDistributionVO> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            res.add(SalaryRangeDistributionVO.builder()
                            .salaryRange("100-100")
                            .count(100L)
                    .build());
        }
        return res;
    }
}
