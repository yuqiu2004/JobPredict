package com.whu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients
//@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class JobPredictApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobPredictApplication.class, args);
        System.out.println("=========================================>  项目启动成功   <=========================================");
    }

}
