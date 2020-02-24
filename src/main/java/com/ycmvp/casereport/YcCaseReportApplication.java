package com.ycmvp.casereport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ComponentScan({"com.ycmvp.plugin", "com.ycmvp.casereport"})
@ServletComponentScan
@EnableScheduling            // 静态任务管理器
@EnableTransactionManagement // 支持事务
@SpringBootApplication
public class YcCaseReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(YcCaseReportApplication.class, args);
    }

}
