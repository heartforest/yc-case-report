package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.TabCaseUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface TabCaseUserMapper {

    @Insert("INSERT INTO TAB_CASE_USER(ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, STROKE_DESCRIPTION, TOUCHING_SIGN, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE) " + 
            "VALUES(#{id}, #{caseDate}, #{userid}, #{optTime}, #{strokeDest}, #{strokeVia}, #{strokeDateGo}, #{strokeDateBack}, #{isolationDate}, #{strokeDescription}, #{touchingSign}, #{bodySign}, #{bodycase}, #{doctorSign}, #{hospital}, #{doctorTime}, #{dectorInfo}, #{caseExplain}, #{userCity}, #{userCommunity}, #{userBuilding}, #{userCallCommunity}, #{userMeet}, #{userPubPlace}, #{bodyTemperature})")
    int create(TabCaseUser tabCaseUser);

    @Update("UPDATE TAB_CASE_USER SET CASE_DATE=#{caseDate}, USERID=#{userid}, OPT_TIME=#{optTime}, STROKE_DEST=#{strokeDest}, STROKE_VIA=#{strokeVia}, STROKE_DATE_GO=#{strokeDateGo}, STROKE_DATE_BACK=#{strokeDateBack}, ISOLATION_DATE=#{isolationDate}, STROKE_DESCRIPTION=#{strokeDescription}, TOUCHING_SIGN=#{touchingSign}, BODY_SIGN=#{bodySign}, BODYCASE=#{bodycase}, DOCTOR_SIGN=#{doctorSign}, HOSPITAL=#{hospital}, DOCTOR_TIME=#{doctorTime}, DECTOR_INFO=#{dectorInfo}, CASE_EXPLAIN=#{caseExplain}, USER_CITY=#{userCity}, USER_COMMUNITY=#{userCommunity}, USER_BUILDING=#{userBuilding}, USER_CALL_COMMUNITY=#{userCallCommunity}, USER_MEET=#{userMeet}, USER_PUB_PLACE=#{userPubPlace}, BODY_TEMPERATURE=#{bodyTemperature} " + 
            "WHERE ID=#{id}")
    int edit(TabCaseUser tabCaseUser);

    @Delete("DELETE FROM TAB_CASE_USER " + 
            "WHERE ID=#{id}")
    int remove(@Param("id") Object id);

    //--->>> select <---

    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, STROKE_DESCRIPTION, TOUCHING_SIGN, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE FROM TAB_CASE_USER WHERE ID=#{id}")
    TabCaseUser find(@Param("id") Object id);

    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, STROKE_DESCRIPTION, TOUCHING_SIGN, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE FROM TAB_CASE_USER")
    List<TabCaseUser> findAll();

    @Select("SELECT count(*) FROM TAB_CASE_USER")
    int count();

    //--->>> others <---
    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_VIA, STROKE_DATE_GO, STROKE_DATE_BACK, ISOLATION_DATE, STROKE_DESCRIPTION, TOUCHING_SIGN, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE " +
            "FROM TAB_CASE_USER " +
            "WHERE USERID=#{userid} AND CASE_DATE=#{caseDate}")
    TabCaseUser findByUseridAndCaseDate(@Param("userid") Object userid, @Param("caseDate") Object caseDate);

    @Select("SELECT CASE_DATE, BODY_SIGN " +
            "FROM TAB_CASE_USER " +
            "WHERE USERID=#{userid} " +
            "ORDER BY CASE_DATE DESC ")
    List<TabCaseUser> findCaseDateByUserid(@Param("userid") Object userid);
}
