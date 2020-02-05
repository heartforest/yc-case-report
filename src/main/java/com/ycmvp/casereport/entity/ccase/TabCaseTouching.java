package com.ycmvp.casereport.entity.ccase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class TabCaseTouching implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String userid;
    private String touchingUser;
    private String touchingUserRelat;
    private String touchingUserFrom;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate touchingUserDate;
    private String touchingUserHospitall;
    private String touchingUserCallCommunity;
    private String touchingMeet;
    private String touchingPubPlace;
    private String touchingExplain;
    private String fkCaseUserId;

}
