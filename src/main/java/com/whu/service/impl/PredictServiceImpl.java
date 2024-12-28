package com.whu.service.impl;

import com.whu.pojo.dto.PredictDTO;
import com.whu.service.PredictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictServiceImpl implements PredictService {
    @Override
    public String predict(PredictDTO predictDTO) {
        // 检验数据的合法性
        if (!validateInput(predictDTO)) return null;
        // 远程调用
        return "测试岗位";
    }

    private boolean validateInput(PredictDTO predictDTO) {
        String qualification = predictDTO.getQualification();
        String preference = predictDTO.getPreference();
        List<String> qualifications = List.of("b", "m", "p");
        List<String> genders = List.of("male", "female", "both");
        return qualifications.contains(qualification.toLowerCase()) &&
                genders.contains(preference.toLowerCase());
    }
}
