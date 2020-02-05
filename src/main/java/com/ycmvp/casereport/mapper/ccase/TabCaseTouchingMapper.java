package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.TabCaseTouching;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TabCaseTouchingMapper {

    @Insert("INSERT INTO TAB_CASE_TOUCHING(ID, USERID, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN, FK_CASE_USER_ID) " + 
            "VALUES(#{id}, #{userid}, #{touchingUser}, #{touchingUserRelat}, #{touchingUserFrom}, #{touchingUserDate}, #{touchingUserHospitall}, #{touchingUserCallCommunity}, #{touchingMeet}, #{touchingPubPlace}, #{touchingExplain}, #{fkCaseUserId})")
    int create(TabCaseTouching tabCaseTouching);

    @Update("UPDATE TAB_CASE_TOUCHING SET USERID=#{userid}, TOUCHING_USER=#{touchingUser}, TOUCHING_USER_RELAT=#{touchingUserRelat}, TOUCHING_USER_FROM=#{touchingUserFrom}, TOUCHING_USER_DATE=#{touchingUserDate}, TOUCHING_USER_HOSPITALL=#{touchingUserHospitall}, TOUCHING_USER_CALL_COMMUNITY=#{touchingUserCallCommunity}, TOUCHING_MEET=#{touchingMeet}, TOUCHING_PUB_PLACE=#{touchingPubPlace}, TOUCHING_EXPLAIN=#{touchingExplain}, FK_CASE_USER_ID=#{fkCaseUserId} " + 
            "WHERE ID=#{id}")
    int edit(TabCaseTouching tabCaseTouching);

    @Delete("DELETE FROM TAB_CASE_TOUCHING " + 
            "WHERE ID=#{id}")
    int remove(@Param("id") Object id);

    //--->>> select <---

    @Select("SELECT ID, USERID, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN, FK_CASE_USER_ID FROM TAB_CASE_TOUCHING WHERE ID=#{id}")
    TabCaseTouching find(@Param("id") Object id);

    @Select("SELECT ID, USERID, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN, FK_CASE_USER_ID FROM TAB_CASE_TOUCHING")
    List<TabCaseTouching> findAll();

    @Select("SELECT count(*) FROM TAB_CASE_TOUCHING")
    int count();

    //--->>> others <---
    @Select("SELECT ID, USERID, TOUCHING_USER, TOUCHING_USER_RELAT, TOUCHING_USER_FROM, TOUCHING_USER_DATE, TOUCHING_USER_HOSPITALL, TOUCHING_USER_CALL_COMMUNITY, TOUCHING_MEET, TOUCHING_PUB_PLACE, TOUCHING_EXPLAIN, FK_CASE_USER_ID " +
            "FROM TAB_CASE_TOUCHING " +
            "WHERE FK_CASE_USER_ID=#{fkCaseUserId} ")
    TabCaseTouching findByFkCaseUserId(@Param("fkCaseUserId") Object fkCaseUserId);
}
