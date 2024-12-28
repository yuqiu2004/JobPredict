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
            Double modeSalary = jobDataMapper.selectModeSalary(gender);
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
        List<String> list = List.of("B", "M", "P"), str = List.of("学士", "硕士", "博士");
        QueryWrapper queryWrapper;
        for (int i = 0; i < list.size(); i++) {
            String edu = list.get(i);
            queryWrapper = new QueryWrapper<Double>().eq("qualification", edu)
                    .select("AVG(salary) AS avg_salary");
            List<Double> avg = jobDataMapper.selectObjs(queryWrapper);
            Double modeSalary = jobDataMapper.selectModeSalaryByQualification(edu);
            res.add(EducationSalaryVO.builder()
                    .education(str.get(i))
                    .averageSalary(avg.get(0))
                    .medianSalary(modeSalary)
                    .build());
        }
        return res;
    }

    @Override
    public List<EducationDistributionVO> distributionEdu() {
        List<EducationDistributionVO> res = new ArrayList<>();
        List<String> list = List.of("B", "M", "P"), str = List.of("学士", "硕士", "博士");
        QueryWrapper queryWrapper;
        for (int i = 0; i < 3; i++) {
            String edu = list.get(i);
            queryWrapper = new QueryWrapper<Long>().eq("qualification", edu).select("count(*) as counts");
            List<Long> counts = jobDataMapper.selectObjs(queryWrapper);
            res.add(EducationDistributionVO.builder()
                            .education(str.get(i))
                            .count(counts.get(0))
                    .build());
        }
        return res;
    }

    @Override
    public List<SalaryRangeDistributionVO> distributionSalary() {
        List<SalaryRangeDistributionVO> res = new ArrayList<>();
        List<Double> down = List.of(0.0, 70000.0, 80000.0, 90000.0, 100000.0);
        List<Double> up = List.of(70000.0, 80000.0, 90000.0, 100000.0, 9000000.0);
        List<String> str = List.of("<70000", "70000<=x<80000", "80000<=x<90000", "90000<=x<100000", ">=100000");
        for (int i = 0; i < up.size(); i++) {
            Double p1 = down.get(i), p2 = up.get(i);
            Long count = jobDataMapper.countBySalaryRange(p1, p2);
            res.add(SalaryRangeDistributionVO.builder()
                            .salaryRange(str.get(i))
                            .count(count)
                    .build());
        }
        return res;
    }
}
