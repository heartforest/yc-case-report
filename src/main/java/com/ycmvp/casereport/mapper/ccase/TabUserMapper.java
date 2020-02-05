package com.ycmvp.casereport.mapper.ccase;

import com.ycmvp.casereport.entity.ccase.TabUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface TabUserMapper {

    @Insert("INSERT INTO TAB_USER(USERID, NAME, UPDATE_KEY, PGROUPNAME, GROUPNAME, USER_PHONE, USER_CARD) " + 
            "VALUES(#{userid}, #{name}, #{updateKey}, #{pgroupname}, #{groupname}, #{userPhone}, #{userCard})")
    int create(TabUser tabUser);

    @Update("UPDATE TAB_USER SET USERID=#{userid}, NAME=#{name}, UPDATE_KEY=#{updateKey}, PGROUPNAME=#{pgroupname}, GROUPNAME=#{groupname}, USER_PHONE=#{userPhone}, USER_CARD=#{userCard} " + 
            "WHERE USERID=#{userid}")
    int edit(TabUser tabUser);

    @Delete("DELETE FROM TAB_USER " + 
            "WHERE USERID=#{userid}")
    int remove(@Param("userid") Object userid);

    //--->>> select <---

    @Select("SELECT USERID, NAME, UPDATE_KEY, PGROUPNAME, GROUPNAME, USER_PHONE, USER_CARD FROM TAB_USER " +
            "WHERE USERID=#{userid}")
    TabUser find(@Param("userid") Object userid);

    @Select("SELECT USERID, NAME, UPDATE_KEY, PGROUPNAME, GROUPNAME, USER_PHONE, USER_CARD FROM TAB_USER")
    List<TabUser> findAll();

    @Select("SELECT count(*) FROM TAB_USER")
    int count();

    //--->>> others <---
    @Select("SELECT USERID, NAME, UPDATE_KEY, PGROUPNAME, GROUPNAME, USER_PHONE, USER_CARD " +
            "FROM TAB_USER " +
            "WHERE NAME=#{name} AND PGROUPNAME=#{pgroupname} AND GROUPNAME=#{groupname}")
    TabUser findByNameAndPgroupnameAndGroupname(@Param("name") Object name, @Param("pgroupname") Object pgroupname, @Param("groupname") Object groupname);

    @Select("SELECT USERID, NAME, UPDATE_KEY, PGROUPNAME, GROUPNAME, USER_PHONE, USER_CARD " +
            "FROM TAB_USER " +
            "WHERE USERID=#{userid}")
    TabUser findByUserid(@Param("userid") Object userid);

    @Select("SELECT USERID, NAME, UPDATE_KEY, PGROUPNAME, GROUPNAME, USER_PHONE, USER_CARD " +
            "FROM TAB_USER " +
            "WHERE USER_CARD=#{userCard}")
    TabUser findByUserCard(@Param("userCard") Object userCard);

    @Select("SELECT USERID, NAME, PGROUPNAME, GROUPNAME, USER_TYPE " +
            "FROM TAB_USER " +
            "WHERE PGROUPNAME=#{pgroupname} AND GROUPNAME=#{groupname}")
    List<TabUser> findByPgroupnameAndGroupname(@Param("pgroupname") Object pgroupname, @Param("groupname") Object groupname);
}
