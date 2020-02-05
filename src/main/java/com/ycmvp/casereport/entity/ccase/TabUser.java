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

}
