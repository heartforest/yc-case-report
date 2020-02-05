package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.ViewPut4;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Component
public interface ViewPut4Mapper {

    //--->>> select <---

    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_PHONE, USER_CARD, USER_CITY, USER_COMMUNITY, USER_BUILDING, HVALEAV, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, TOUCHING_SIGN, BODY_SIGN, BODYCASE, USER_CALL_COMMUNITY, BODY_TEMPERATURE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN FROM VIEW_PUT4 WHERE ID=#{id}")
    ViewPut4 find(@Param("id") Object id);

    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_PHONE, USER_CARD, USER_CITY, USER_COMMUNITY, USER_BUILDING, HVALEAV, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, TOUCHING_SIGN, BODY_SIGN, BODYCASE, USER_CALL_COMMUNITY, BODY_TEMPERATURE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN FROM VIEW_PUT4")
    List<ViewPut4> findAll();

    @Select("SELECT count(*) FROM VIEW_PUT4")
    int count();

    //--->>> others <---
    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_PHONE, USER_CARD, USER_CITY, USER_COMMUNITY, USER_BUILDING, HVALEAV, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, TOUCHING_SIGN, BODY_SIGN, BODYCASE, USER_CALL_COMMUNITY, BODY_TEMPERATURE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN " +
            "FROM VIEW_PUT4 " +
            "WHERE CASE_DATE=#{caseDate} ")
    List<ViewPut4> findPut4(@Param("caseDate") LocalDate caseDate);
}
