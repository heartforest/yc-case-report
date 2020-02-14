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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate strokeDateGo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate strokeDateBack;
    private String strokeDescription;
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
    private String touchingSign;
    private String strokeVia;
    private String userMeet;
    private String userPubPlace;
    private String bodyTemperature;
    private String isolationTag;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate isolationDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate unisolationDate;
    private String isolationStatus;
    private String isolationDescription;
    private String workStatus;
    private Integer epidemicCommunityCount;
    private String strandedSign;
    private String examineName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.time.LocalDateTime examineTime;
    private String examineUserid;

}
