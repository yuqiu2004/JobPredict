package com.whu.service.impl;

import com.whu.pojo.dto.PredictDTO;
import com.whu.service.PredictService;
import com.whu.service.PredictVO;
import com.whu.service.PythonPredictServiceClient;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictServiceImpl implements PredictService {

    @Resource
    private PythonPredictServiceClient pythonPredictServiceClient;

    @Override
    public String predict(PredictDTO predictDTO) {
        // 检验数据的合法性
        if (!validateInput(predictDTO)) return null;
        // 远程调用
        PredictVO predictVO = pythonPredictServiceClient.predictRpc(predictDTO);
        return predictVO.getJob();
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
