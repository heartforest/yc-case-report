package com.ycmvp.casereport.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ycmvp.casereport.config.CaseReportConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description 项目初始化信息
 **/
@Slf4j
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CaseReportConfig caseReportConfig;

    /*
     * @Description 这个和 ApplicationStartedEvent 很类似，也是在应用程序上下文刷新之后之后调用，
     * 区别在于此时ApplicationRunner 和 CommandLineRunner已经完成调用了，也意味着 SpringBoot 加载已经完成。
     * @Param [event]
     * @return void
     **/
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("--->>> ApplicationListener ---> SpringBoot 加载完成 ---> ApplicationReadyEvent <---");
        log.info("--->>> mapper ---> 初始化:mapper <---");
        //对象为空,不抛异常
        caseReportConfig.getMapper_snake().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        caseReportConfig.getMapperLowerCamel().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //反序列化多出属性，不抛异常
        caseReportConfig.getMapper_snake().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        caseReportConfig.getMapperLowerCamel().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //小写开头驼峰
        caseReportConfig.getMapperLowerCamel().setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
        //下划线转驼峰
        caseReportConfig.getMapper_snake().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        log.info("--->>> 项目启动完成 <---");
    }

}
