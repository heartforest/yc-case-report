package com.ycmvp.casereport.entity.ccase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class ViewPut4 implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate caseDate;
    private String pgroupname;
    private String groupname;
    private String name;
    private String userPhone;
    private String userCard;
    private String userCity;
    private String userCommunity;
    private String userBuilding;
    private String hvaleav;
    private String strokeDest;
    private String strokeVia;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate strokeDateGo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate strokeDateBack;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate isolationDate;
    private String touchingSign;
    private String bodySign;
    private String bodycase;
    private String userCallCommunity;
    private String bodyTemperature;
    private String doctorSign;
    private String hospital;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate doctorTime;
    private String dectorInfo;
    private String caseExplain;

}
