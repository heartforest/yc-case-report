package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.ViewPut3;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Component
public interface ViewPut3Mapper {

    //--->>> select <---

    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_CARD, USER_PHONE, HAV_TOUCHING, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN FROM VIEW_PUT3 WHERE ID=#{id}")
    ViewPut3 find(@Param("id") Object id);

    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_CARD, USER_PHONE, HAV_TOUCHING, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN FROM VIEW_PUT3")
    List<ViewPut3> findAll();

    @Select("SELECT count(*) FROM VIEW_PUT3")
    int count();

    //--->>> others <---
    @Select("SELECT CASE_DATE, PGROUPNAME, GROUPNAME, NAME, USER_CARD, USER_PHONE, HAV_TOUCHING, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN " +
            "FROM VIEW_PUT3 " +
            "WHERE CASE_DATE=#{caseDate} ")
    List<ViewPut3> findPut3(@Param("caseDate") LocalDate caseDate);
}
