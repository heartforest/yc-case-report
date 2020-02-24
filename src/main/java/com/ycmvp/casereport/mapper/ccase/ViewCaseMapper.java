package com.ycmvp.casereport.mapper.ccase;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface ViewCaseMapper {

    @Select("SELECT * " +
            "FROM ${viewname} tu " +
            "WHERE CASE_DATE=#{caseDate} ")
    List<Map> findAutoViewname(@Param("viewname") String viewname, @Param("caseDate") LocalDate caseDate);

    @Select("SELECT * " +
            "FROM ${viewname} v ")
    List<Map> findByViewname(@Param("viewname") String viewname);
}
