package com.whu.controller;

import com.whu.pojo.vo.R;
import com.whu.utils.MinioUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private MinioUtil minioUtil;

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        String upload = minioUtil.upload(file);
        return R.success(upload);
    }

}
