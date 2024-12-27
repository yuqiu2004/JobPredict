package com.whu.config;

import com.whu.properties.MinioProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

//    @Resource
    private MinioProperties minioProperties;

//    @Bean
//    public MinioClient minioClient() {
//        return MinioClient.builder()
//                .endpoint(minioProperties.getEndpoint())
//                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
//                .build();
//    }
}
