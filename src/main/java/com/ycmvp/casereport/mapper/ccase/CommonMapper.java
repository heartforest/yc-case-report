package com.ycmvp.casereport.mapper.ccase;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Description class
 **/
@Mapper
@Component
public interface CommonMapper {

    @Select("SELECT GROUPNAME, count(*) org_cnt " +
            "FROM TAB_USER tu" +
            "    LEFT JOIN TAB_CASE_USER tcu ON tu.USERID = tcu.USERID " +
            "WHERE USER_STATUS = '正常' AND tcu.CASE_DATE = #{caseDate} " +
            "GROUP BY GROUPNAME ")
    List<Map> findGroupCountCase(@Param("caseDate") LocalDate caseDate);

    @Select("SELECT GROUPNAME, count(*) org_cnt " +
            "FROM TAB_USER " +
            "WHERE USER_STATUS = '正常' GROUP BY GROUPNAME")
    List<Map> findGroupCount();

    @Select("SELECT GROUPNAME, tu.name, tcu.case_date " +
            "FROM TAB_USER tu " +
            "    LEFT JOIN TAB_CASE_USER tcu ON tu.USERID = tcu.USERID AND tcu.CASE_DATE = #{caseDate} " +
            "WHERE USER_STATUS = '正常' ")
    List<Map> findGroupItems(@Param("caseDate") LocalDate caseDate);

}
