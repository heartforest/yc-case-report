package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.TabWorkRecords;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TabWorkRecordsMapper {

    @Insert("INSERT INTO TAB_WORK_RECORDS(ID, USERID, WORK_TYPE, WORK_TIME, WORK_DATE, BODY_TEMPERATURE, WORK_ID, WORK_BUILDING) " + 
            "VALUES(#{id}, #{userid}, #{workType}, #{workTime}, #{workDate}, #{bodyTemperature}, #{workId}, #{workBuilding})")
    int create(TabWorkRecords tabWorkRecords);

    @Update("UPDATE TAB_WORK_RECORDS SET USERID=#{userid}, WORK_TYPE=#{workType}, WORK_TIME=#{workTime}, WORK_DATE=#{workDate}, BODY_TEMPERATURE=#{bodyTemperature}, WORK_ID=#{workId}, WORK_BUILDING=#{workBuilding} " + 
            "WHERE ID=#{id}")
    int edit(TabWorkRecords tabWorkRecords);

    @Delete("DELETE FROM TAB_WORK_RECORDS " + 
            "WHERE ID=#{id}")
    int remove(@Param("id") Object id);

    //--->>> select <---

    @Select("SELECT ID, USERID, WORK_TYPE, WORK_TIME, WORK_DATE, BODY_TEMPERATURE, WORK_ID, WORK_BUILDING FROM TAB_WORK_RECORDS WHERE ID=#{id}")
    TabWorkRecords find(@Param("id") Object id);

    @Select("SELECT ID, USERID, WORK_TYPE, WORK_TIME, WORK_DATE, BODY_TEMPERATURE, WORK_ID, WORK_BUILDING FROM TAB_WORK_RECORDS")
    List<TabWorkRecords> findAll();

    @Select("SELECT count(*) FROM TAB_WORK_RECORDS")
    int count();

    //--->>> others <---
    @Select("SELECT count(*), WORK_DATE " +
            "FROM TAB_WORK_RECORDS " +
            "WHERE USERID=#{userid}" +
            "GROUP BY WORK_DATE ORDER BY WORK_DATE DESC LIMIT 0, 10")
    List<TabWorkRecords> findByUseridAndGroupWorkDate(@Param("userid") Object userid);

    @Select("SELECT ID, USERID, WORK_TYPE, WORK_TIME, WORK_DATE, BODY_TEMPERATURE, WORK_ID, WORK_BUILDING " +
            "FROM TAB_WORK_RECORDS " +
            "WHERE USERID=#{userid} ")
    List<TabWorkRecords> findByUserid(@Param("userid") Object userid);

    @Select("SELECT ID, USERID, WORK_TYPE, WORK_TIME, WORK_DATE, BODY_TEMPERATURE, WORK_ID, WORK_BUILDING " +
            "FROM TAB_WORK_RECORDS " +
            "WHERE USERID=#{userid} AND WORK_DATE=#{workDate} ORDER BY WORK_DATE DESC ")
    List<TabWorkRecords> findByUseridAndWorkDate(@Param("userid") Object userid, @Param("workDate") Object workDate);
}
