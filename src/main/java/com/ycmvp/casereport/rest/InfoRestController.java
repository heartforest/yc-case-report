package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.entity.ccase.TabCaseTouching;
import com.ycmvp.casereport.entity.ccase.TabCaseUser;
import com.ycmvp.casereport.entity.ccase.TabUser;
import com.ycmvp.casereport.mapper.ccase.TabCaseTouchingMapper;
import com.ycmvp.casereport.mapper.ccase.TabCaseUserMapper;
import com.ycmvp.casereport.mapper.ccase.TabUserMapper;
import com.ycmvp.plugin.assistant.config.AssistantConfig;
import com.ycmvp.util.web.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description class
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rest/info")
public class InfoRestController {

    @Autowired
    private TabCaseUserMapper tabCaseUserMapper;
    @Autowired
    private TabUserMapper tabUserMapper;
    @Autowired
    private TabCaseTouchingMapper tabCaseTouchingMapper;
    @Autowired
    private AssistantConfig assistantConfig;

    @PostMapping("/save/user")
    public Map saveTabUser(@RequestBody TabUser tabUser) {
        try {
            TabUser tu = tabUserMapper.findByNameAndPgroupnameAndGroupname(tabUser.getName(), tabUser.getPgroupname(), tabUser.getGroupname());
            if(tu == null) {
                tu = tabUserMapper.findByUserid(tabUser.getUserid());
            }
            if(tu == null) {
                // 初次建立
                tabUser.setUserid(assistantConfig.getPrimary());
                tabUserMapper.create(tabUser);
            } else if(tu.getUserCard() == null || "".equals(tu.getUserCard()) || tu.getUserCard().equals(tabUser.getUserCard())) {
                tabUser = editUserData(tu, tabUser);
            } else if(tu.getUpdateKey().equals(tabUser.getUpdateKey())) {
                tabUser = editUserData(tu, tabUser);
            } else {
                tabUser.setUserid(tu.getUserid());
            }
            return Page.ok(tabUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("--->>> error ---> {} <<<---", e.toString());
            return Page.fail("提交失败:".concat(e.toString().substring(0, 50)));
        }
    }

    private TabUser editUserData(TabUser srcTabUser, TabUser tagTabUser) {
        tagTabUser.setUserStatus(srcTabUser.getUserStatus());
        tagTabUser.setSapid(srcTabUser.getSapid());
        tagTabUser.setAowid(srcTabUser.getAowid());
        tagTabUser.setSex(srcTabUser.getSex());
        tagTabUser.setOrgSrc(srcTabUser.getOrgSrc());
        tagTabUser.setUserType(srcTabUser.getUserType());
        tagTabUser.setUserPost(srcTabUser.getUserPost());
        tagTabUser.setUserJob(srcTabUser.getUserJob());
        tagTabUser.setUserAge(srcTabUser.getUserAge());
        tagTabUser.setUserid(srcTabUser.getUserid());
        tabUserMapper.edit(tagTabUser);
        return tagTabUser;
    }

    @PostMapping("/save/case-user")
    public Map saveTabCaseUser(@RequestBody TabCaseUser tabCaseUser) {
        try {
            if(tabCaseUser == null) {
                return Page.fail("信息不全:TabCaseUser");
            } else if(tabCaseUser.getUserid() == null) {
                return Page.fail("信息不全:userid");
            }
            TabUser tu = tabUserMapper.find(tabCaseUser.getUserid());
            if(tu == null) {
                return Page.fail("基本信息不存在:TabUser");
            }
            TabCaseUser tcu = tabCaseUserMapper.findByUseridAndCaseDate(tabCaseUser.getUserid(), tabCaseUser.getCaseDate());
            tabCaseUser.setOptTime(LocalDateTime.now());
            if(tcu == null) {
                // 初次建立
                tabCaseUser.setId(assistantConfig.getPrimary());
                tabCaseUserMapper.create(tabCaseUser);
            } else {
                tabCaseUser.setId(tcu.getId());
                tabCaseUserMapper.edit(tabCaseUser);
            }
            return Page.ok(tabCaseUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("--->>> error ---> {} <<<---", e.toString());
            return Page.fail("提交失败:".concat(e.toString().substring(0, 50)));
        }
    }

    @PostMapping("/save/case-touching")
    public Map saveTabCaseTouching(@RequestBody TabCaseTouching tabCaseTouching) {
        try {
            if(tabCaseTouching == null) {
                return Page.fail("信息不全:TabCaseTouching is null");
            } else if(tabCaseTouching.getUserid() == null) {
                return Page.fail("信息不全:tabCaseTouching.userid ");
            } else if(tabCaseTouching.getFkCaseUserId() == null) {
                return Page.fail("信息不全:tabCaseTouching.fkCaseUserId ");
            }
            TabCaseUser tabCaseUser = tabCaseUserMapper.find(tabCaseTouching.getFkCaseUserId());
            if(tabCaseUser == null) {
                return Page.fail("个人填报信息不存在:TabCaseUser");
            }
            TabCaseTouching tut = tabCaseTouchingMapper.findByFkCaseUserId(tabCaseTouching.getFkCaseUserId());
            if(tut == null) {
                // 初次建立
                tabCaseTouching.setId(assistantConfig.getPrimary());
                tabCaseTouchingMapper.create(tabCaseTouching);
            } else {
                tabCaseTouching.setId(tut.getId());
                tabCaseTouchingMapper.edit(tabCaseTouching);
            }
            return Page.ok(tabCaseTouching);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("--->>> error ---> {} <<<---", e.toString());
            return Page.fail("提交失败:".concat(e.toString().substring(0, 50)));
        }
    }

    @GetMapping("/get/case-user/{name}/{pgroupname}/{groupname}")
    public Map getCaseInfo(@PathVariable String name, @PathVariable String pgroupname, @PathVariable String groupname) {
        TabUser tabUser = tabUserMapper.findByNameAndPgroupnameAndGroupname(name, pgroupname, groupname);
        if(tabUser == null) {
            return Page.fail("没有信息");
        }
        return getCaseInfo(tabUser);
    }

    @GetMapping("/get/case-user/{userCard}")
    public Map getCaseInfo(@PathVariable String userCard) {
        TabUser tabUser = tabUserMapper.findByUserCard(userCard);
        if(tabUser == null) {
            return Page.fail("没有信息-tabUser");
        }
        return getCaseInfo(tabUser);
    }

    @GetMapping("/get/user/{userid}")
    public Map getUserReportInfo(@PathVariable String userid) {
        List<TabCaseUser> tabCaseUsers = tabCaseUserMapper.findCaseDateByUserid(userid);
        return Page.ok(tabCaseUsers);
    }

    private Map getCaseInfo(TabUser tabUser) {
        try {
            // 获取昨天数据
            LocalDate yesterday = LocalDate.now().minusDays(1);
            TabCaseUser tabCaseUser = tabCaseUserMapper.findByUseridAndCaseDate(tabUser.getUserid(), LocalDate.now());
            if(tabCaseUser == null) {
                tabCaseUser = tabCaseUserMapper.findByUseridAndCaseDate(tabUser.getUserid(), yesterday);
            }
            if(tabCaseUser == null) {
                return Page.fail("没有信息-tabCaseUser");
            }
            TabCaseTouching tabCaseTouching = tabCaseTouchingMapper.findByFkCaseUserId(tabCaseUser.getId());

            Map dataMap = new HashMap();
            dataMap.put("tabUser", tabUser);
            dataMap.put("tabCaseUser", tabCaseUser);
            dataMap.put("tabCaseTouching", tabCaseTouching);
            //
            return Page.ok(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("--->>> error ---> {} <<<---", e.toString());
            return Page.fail("查询失败:".concat(e.toString().substring(0, 50)));
        }
    }

}
