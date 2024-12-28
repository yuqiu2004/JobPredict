package com.whu.service;

import com.whu.pojo.dto.PredictDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "python-predict-service", url = "http://localhost:8081")
public interface PythonPredictServiceClient {

    @PostMapping("/predict")
    PredictVO predictRpc(@RequestBody PredictDTO predictDTO);
}
