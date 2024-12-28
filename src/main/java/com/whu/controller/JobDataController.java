package com.whu.controller;

import com.whu.pojo.vo.R;
import com.whu.service.JobDataService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobDataController {

    @Resource
    private JobDataService jobDataService;

    @GetMapping("/salary/gender-statistics")
    public R salaryGender(){
        return R.success(jobDataService.salaryGender());
    }

    @GetMapping("/salary/education-statistics")
    public R salaryEdu(){
        return R.success(jobDataService.salaryEdu());
    }

    @GetMapping("/education/distribution")
    public R distributionEdu(){
        return R.success(jobDataService.distributionEdu());
    }

    @GetMapping("/salary/distribution")
    public R distributionSalary(){
        return R.success(jobDataService.distributionSalary());
    }

}
