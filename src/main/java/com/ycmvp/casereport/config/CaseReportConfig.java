package com.ycmvp.casereport.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.Map;

@Slf4j
@Data
@Order(0)
@Component
@Configuration
@ConfigurationProperties(prefix = "case-report-params")
@ToString(exclude = {"mapperLowerCamel", "mapper_snake"})
public class CaseReportConfig {
    private Long signRedisTimeOut = 3600L;
    // 口令
    private String passwordkey;
    // 码规则
    private Map codeMap;
    //
    private ObjectMapper mapperLowerCamel = new ObjectMapper();
    private ObjectMapper mapper_snake = new ObjectMapper();

    @Value("${spring.application.name}")
    private String springApplicationName;

    @Bean
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        return new CommonsMultipartResolver();
    }

}
