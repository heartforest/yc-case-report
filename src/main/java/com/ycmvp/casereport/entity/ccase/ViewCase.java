package com.ycmvp.casereport.entity.ccase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class ViewCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.sql.Date caseDate;
    private String pgroupname;
    private String groupname;
    private String name;
    private String userCity;
    private String userCommunity;
    private String userBuilding;
    private String strokeDest;
    private String strokeVia;
    private String strokeDescription;
    private String touchingSign;
    private String bodySign;
    private String bodycase;
    private String doctorSign;
    private String hospital;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private java.time.LocalDateTime doctorTime;
    private String dectorInfo;
    private String caseExplain;

}
