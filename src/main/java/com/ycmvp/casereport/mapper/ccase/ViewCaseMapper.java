package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.ViewCase;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface ViewCaseMapper {
    //--->>> select <---

    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_CITY, USER_COMMUNITY, USER_BUILDING, STROKE_DEST, STROKE_VIA, STROKE_DESCRIPTION, TOUCHING_SIGN, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN FROM VIEW_CASE WHERE ID=#{id}")
    ViewCase find(@Param("id") Object id);

    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_CITY, USER_COMMUNITY, USER_BUILDING, STROKE_DEST, STROKE_VIA, STROKE_DESCRIPTION, TOUCHING_SIGN, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN FROM VIEW_CASE")
    List<ViewCase> findAll();

    @Select("SELECT count(*) FROM VIEW_CASE")
    int count();

    //--->>> others <---

}