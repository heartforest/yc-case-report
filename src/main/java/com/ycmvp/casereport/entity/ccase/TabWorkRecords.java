package com.ycmvp.casereport.entity.ccase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class TabWorkRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userid;
    private String workType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.time.LocalDateTime workTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate workDate;
    private String bodyTemperature;
    private String workId;
    private String workBuilding;

}
