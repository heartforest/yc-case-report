package com.ycmvp.casereport.entity.ccase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class TabCaseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate caseDate;
    private String userid;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.time.LocalDateTime optTime;
    private String strokeDest;
    private String strokeVia;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate strokeDateGo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate strokeDateBack;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate isolationDate;
    private String strokeDescription;
    private String touchingSign;
    private String bodySign;
    private String bodycase;
    private String doctorSign;
    private String hospital;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate doctorTime;
    private String dectorInfo;
    private String caseExplain;
    private String userCity;
    private String userCommunity;
    private String userBuilding;
    private String userCallCommunity;
    private String userMeet;
    private String userPubPlace;
    private String bodyTemperature;

}
