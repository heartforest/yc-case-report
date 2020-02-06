package com.ycmvp.casereport.entity.ccase;

import lombok.Data;
import java.io.Serializable;

@Data
public class TabUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;
    private String name;
    private String updateKey;
    private String pgroupname;
    private String groupname;
    private String userPhone;
    private String userCard;
    private String userStatus;
    private String sapid;
    private String aowid;
    private String sex;
    private String orgSrc;
    private String userType;
    private String userPost;
    private String userUrgentPhone;
    private String userUrgentName;
    private String userUrgentRelat;
    private String userJob;
    private String userAge;

}
