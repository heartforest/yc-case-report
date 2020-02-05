package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.ViewPut1;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Component
public interface ViewPut1Mapper {

    //--->>> select <---

    @Select("SELECT CASE_DATE, PGROUPNAME, NAME, USER_CARD, USER_COMMUNITY, USER_PHONE, STROKE_DATE_GO, STROKE_DEST, STROKE_DATE_BACK, ISOLATION_DATE, HOSPITAL, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE FROM VIEW_PUT1 WHERE ID=#{id}")
    ViewPut1 find(@Param("id") Object id);

    @Select("SELECT CASE_DATE, PGROUPNAME, NAME, USER_CARD, USER_COMMUNITY, USER_PHONE, STROKE_DATE_GO, STROKE_DEST, STROKE_DATE_BACK, ISOLATION_DATE, HOSPITAL, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE FROM VIEW_PUT1")
    List<ViewPut1> findAll();

    @Select("SELECT count(*) FROM VIEW_PUT1")
    int count();

    //--->>> others <---
    @Select("SELECT CASE_DATE, PGROUPNAME, NAME, USER_CARD, USER_COMMUNITY, USER_PHONE, STROKE_DATE_GO, STROKE_DEST, STROKE_DATE_BACK, ISOLATION_DATE, HOSPITAL, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE " +
            "FROM VIEW_PUT1 " +
            "WHERE CASE_DATE=#{caseDate} ")
    List<ViewPut1> findPut1(@Param("caseDate") LocalDate caseDate);
}
