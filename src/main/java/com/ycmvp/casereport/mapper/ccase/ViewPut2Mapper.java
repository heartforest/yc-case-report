package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.ViewPut2;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Component
public interface ViewPut2Mapper {

    //--->>> select <---

    @Select("SELECT CASE_DATE, GROUPNAME, NAME, USER_CARD, USER_PHONE, USER_COMMUNITY, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE FROM VIEW_PUT2 WHERE ID=#{id}")
    ViewPut2 find(@Param("id") Object id);

    @Select("SELECT CASE_DATE, GROUPNAME, NAME, USER_CARD, USER_PHONE, USER_COMMUNITY, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE FROM VIEW_PUT2")
    List<ViewPut2> findAll();

    @Select("SELECT count(*) FROM VIEW_PUT2")
    int count();

    //--->>> others <---
    @Select("SELECT CASE_DATE, GROUPNAME, NAME, USER_CARD, USER_PHONE, USER_COMMUNITY, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE " +
            "FROM VIEW_PUT2 " +
            "WHERE CASE_DATE=#{caseDate} ")
    List<ViewPut2> findPut2(@Param("caseDate") LocalDate caseDate);
}
