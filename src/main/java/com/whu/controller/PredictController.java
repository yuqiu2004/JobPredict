package com.whu.controller;

import com.whu.pojo.dto.PredictDTO;
import com.whu.pojo.vo.R;
import com.whu.service.PredictService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predict")
public class PredictController {

    @Resource
    private PredictService predictService;

    @PostMapping
    public R predict(@RequestBody PredictDTO predictDTO) {
        String s = predictService.predict(predictDTO);
        if (s == null) return R.error("输入数据不合法");
        return R.success(s);
    }
}
