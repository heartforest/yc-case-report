package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.TabCaseUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface TabCaseUserMapper {

    @Insert("INSERT INTO TAB_CASE_USER(ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_DATE_GO, STROKE_DATE_BACK, STROKE_DESCRIPTION, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, TOUCHING_SIGN, STROKE_VIA, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE, ISOLATION_TAG, ISOLATION_DATE, UNISOLATION_DATE, ISOLATION_STATUS, ISOLATION_DESCRIPTION, WORK_STATUS, EPIDEMIC_COMMUNITY_COUNT, STRANDED_SIGN, EXAMINE_NAME, EXAMINE_TIME, EXAMINE_USERID) " + 
            "VALUES(#{id}, #{caseDate}, #{userid}, #{optTime}, #{strokeDest}, #{strokeDateGo}, #{strokeDateBack}, #{strokeDescription}, #{bodySign}, #{bodycase}, #{doctorSign}, #{hospital}, #{doctorTime}, #{dectorInfo}, #{caseExplain}, #{userCity}, #{userCommunity}, #{userBuilding}, #{userCallCommunity}, #{touchingSign}, #{strokeVia}, #{userMeet}, #{userPubPlace}, #{bodyTemperature}, #{isolationTag}, #{isolationDate}, #{unisolationDate}, #{isolationStatus}, #{isolationDescription}, #{workStatus}, #{epidemicCommunityCount}, #{strandedSign}, #{examineName}, #{examineTime}, #{examineUserid})")
    int create(TabCaseUser tabCaseUser);

    @Update("UPDATE TAB_CASE_USER SET CASE_DATE=#{caseDate}, USERID=#{userid}, OPT_TIME=#{optTime}, STROKE_DEST=#{strokeDest}, STROKE_DATE_GO=#{strokeDateGo}, STROKE_DATE_BACK=#{strokeDateBack}, STROKE_DESCRIPTION=#{strokeDescription}, BODY_SIGN=#{bodySign}, BODYCASE=#{bodycase}, DOCTOR_SIGN=#{doctorSign}, HOSPITAL=#{hospital}, DOCTOR_TIME=#{doctorTime}, DECTOR_INFO=#{dectorInfo}, CASE_EXPLAIN=#{caseExplain}, USER_CITY=#{userCity}, USER_COMMUNITY=#{userCommunity}, USER_BUILDING=#{userBuilding}, USER_CALL_COMMUNITY=#{userCallCommunity}, TOUCHING_SIGN=#{touchingSign}, STROKE_VIA=#{strokeVia}, USER_MEET=#{userMeet}, USER_PUB_PLACE=#{userPubPlace}, BODY_TEMPERATURE=#{bodyTemperature}, ISOLATION_TAG=#{isolationTag}, ISOLATION_DATE=#{isolationDate}, UNISOLATION_DATE=#{unisolationDate}, ISOLATION_STATUS=#{isolationStatus}, ISOLATION_DESCRIPTION=#{isolationDescription}, WORK_STATUS=#{workStatus}, EPIDEMIC_COMMUNITY_COUNT=#{epidemicCommunityCount}, STRANDED_SIGN=#{strandedSign}, EXAMINE_NAME=#{examineName}, EXAMINE_TIME=#{examineTime}, EXAMINE_USERID=#{examineUserid} " + 
            "WHERE ID=#{id}")
    int edit(TabCaseUser tabCaseUser);

    @Delete("DELETE FROM TAB_CASE_USER " + 
            "WHERE ID=#{id}")
    int remove(@Param("id") Object id);

    //--->>> select <---

    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_DATE_GO, STROKE_DATE_BACK, STROKE_DESCRIPTION, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, TOUCHING_SIGN, STROKE_VIA, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE, ISOLATION_TAG, ISOLATION_DATE, UNISOLATION_DATE, ISOLATION_STATUS, ISOLATION_DESCRIPTION, WORK_STATUS, EPIDEMIC_COMMUNITY_COUNT, STRANDED_SIGN, EXAMINE_NAME, EXAMINE_TIME, EXAMINE_USERID FROM TAB_CASE_USER WHERE ID=#{id}")
    TabCaseUser find(@Param("id") Object id);

    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_DATE_GO, STROKE_DATE_BACK, STROKE_DESCRIPTION, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, TOUCHING_SIGN, STROKE_VIA, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE, ISOLATION_TAG, ISOLATION_DATE, UNISOLATION_DATE, ISOLATION_STATUS, ISOLATION_DESCRIPTION, WORK_STATUS, EPIDEMIC_COMMUNITY_COUNT, STRANDED_SIGN, EXAMINE_NAME, EXAMINE_TIME, EXAMINE_USERID " +
            "FROM TAB_CASE_USER")
    List<TabCaseUser> findAll();

    @Select("SELECT count(*) FROM TAB_CASE_USER")
    int count();

    //--->>> others <---
    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_DATE_GO, STROKE_DATE_BACK, STROKE_DESCRIPTION, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, TOUCHING_SIGN, STROKE_VIA, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE, ISOLATION_TAG, ISOLATION_DATE, UNISOLATION_DATE, ISOLATION_STATUS, ISOLATION_DESCRIPTION, WORK_STATUS, EPIDEMIC_COMMUNITY_COUNT, STRANDED_SIGN, EXAMINE_NAME, EXAMINE_TIME, EXAMINE_USERID " +
            "FROM TAB_CASE_USER " +
            "WHERE USERID=#{userid} AND CASE_DATE=#{caseDate}")
    TabCaseUser findByUseridAndCaseDate(@Param("userid") Object userid, @Param("caseDate") Object caseDate);

    @Select("SELECT ID, CASE_DATE, USERID, OPT_TIME, STROKE_DEST, STROKE_DATE_GO, STROKE_DATE_BACK, STROKE_DESCRIPTION, BODY_SIGN, BODYCASE, DOCTOR_SIGN, HOSPITAL, DOCTOR_TIME, DECTOR_INFO, CASE_EXPLAIN, USER_CITY, USER_COMMUNITY, USER_BUILDING, USER_CALL_COMMUNITY, TOUCHING_SIGN, STROKE_VIA, USER_MEET, USER_PUB_PLACE, BODY_TEMPERATURE, ISOLATION_TAG, ISOLATION_DATE, UNISOLATION_DATE, ISOLATION_STATUS, ISOLATION_DESCRIPTION, WORK_STATUS, EPIDEMIC_COMMUNITY_COUNT, STRANDED_SIGN, EXAMINE_NAME, EXAMINE_TIME, EXAMINE_USERID " +
            "FROM TAB_CASE_USER " +
            "WHERE USERID=#{userid} ORDER BY CASE_DATE DESC LIMIT 0, 1 ")
    TabCaseUser findByLastUseridFullInfo(@Param("userid") Object userid);

    @Select("SELECT USERID, USER_CITY, USER_COMMUNITY, USER_BUILDING " +
            "FROM TAB_CASE_USER " +
            "WHERE USERID=#{userid} ORDER BY CASE_DATE DESC LIMIT 0, 1 ")
    TabCaseUser findByLastUserid(@Param("userid") Object userid);

    @Select("SELECT CASE_DATE, BODY_SIGN " +
            "FROM TAB_CASE_USER " +
            "WHERE USERID=#{userid} " +
            "ORDER BY CASE_DATE DESC ")
    List<TabCaseUser> findCaseDateByUserid(@Param("userid") Object userid);

    @Update("UPDATE TAB_CASE_USER SET EXAMINE_NAME=#{examineName}, EXAMINE_TIME=#{examineTime}, EXAMINE_USERID=#{examineUserid} " +
            "WHERE ID=#{id}")
    int editExamine(TabCaseUser tabCaseUser);
}
