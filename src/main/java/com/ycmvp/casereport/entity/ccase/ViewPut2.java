package com.ycmvp.casereport.entity.ccase;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class ViewPut2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate caseDate;
    private String groupname;
    private String name;
    private String userCard;
    private String userPhone;
    private String userCommunity;
    private String touchingUser;
    private String touchingUserRelat;
    private String touchingUserFrom;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private java.time.LocalDate touchingUserDate;
    private String touchingUserHospitall;
    private String touchingUserCallCommunity;
    private String touchingMeet;
    private String touchingPubPlace;

}
