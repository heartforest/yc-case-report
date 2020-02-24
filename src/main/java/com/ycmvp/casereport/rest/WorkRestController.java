package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.config.CaseReportConfig;
import com.ycmvp.casereport.entity.ccase.TabCaseUser;
import com.ycmvp.casereport.entity.ccase.TabUser;
import com.ycmvp.casereport.entity.ccase.TabWorkRecords;
import com.ycmvp.casereport.mapper.ccase.TabCaseUserMapper;
import com.ycmvp.casereport.mapper.ccase.TabUserMapper;
import com.ycmvp.casereport.mapper.ccase.TabWorkRecordsMapper;
import com.ycmvp.plugin.assistant.config.AssistantConfig;
import com.ycmvp.util.web.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Description class
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rest/work")
public class WorkRestController {

    @Autowired
    private TabUserMapper tabUserMapper;
    @Autowired
    private TabWorkRecordsMapper tabWorkRecordsMapper;
    @Autowired
    private TabCaseUserMapper tabCaseUserMapper;
    @Autowired
    private AssistantConfig assistantConfig;
    @Autowired
    private CaseReportConfig caseReportConfig;

    @GetMapping("/user/fuzzy/{key}")
    public Map getUserInfo(@PathVariable String key) {
        TabUser tabUser = tabUserMapper.fuzzyByKey(key);
        Map dataMap = new HashMap();
        dataMap.put("tabUser", tabUser);
        if(tabUser != null) {
            TabCaseUser tabCaseUser = tabCaseUserMapper.findByLastUserid(tabUser.getUserid());
            dataMap.put("tabCaseUser", tabCaseUser);
        }
        return Page.ok(dataMap);
    }

    @PostMapping("/save")
    public Map saveTabCaseTouching(@RequestBody Map data) {
        try {
            TabUser tabUser = caseReportConfig.getMapperLowerCamel().convertValue(data.get("user"), TabUser.class);
            TabWorkRecords tabWorkRecords = caseReportConfig.getMapperLowerCamel().convertValue(data.get("work"), TabWorkRecords.class);
            // 检索 tabUser 是否存在
            TabUser dbTabUser = tabUserMapper.findByUserCard(tabUser.getUserCard());
            if(dbTabUser == null && "招商银行银川分行".equals(tabUser.getPgroupname())) {
                //招行员工
                return Page.fail("提交失败:<br>您提交是招商银行银川分行员工!<br>但未检索到您的信息!<br>身份号码错误?<br>每日疫情填报您没填过?");
            } else if(dbTabUser == null) {
                // 重新建立
                tabUser.setUserid(assistantConfig.getPrimary());
                tabUser.setUserCity("宁夏回族自治区 银川市");
                tabUser.setUpdateKey("uid");
                tabUser.setUserStatus("work");
                tabUser.setSapid(tabUser.getUserid());
                tabUser.setAowid(tabUser.getUserid());
                tabUser.setGroupname("");
                tabUserMapper.create(tabUser);
            } else {
                // 补充信息
                dbTabUser.setSex(tabUser.getSex());
                dbTabUser.setUserPhone(tabUser.getUserPhone());
                dbTabUser.setUserCity("宁夏回族自治区 银川市");
                dbTabUser.setUserCommunity(tabUser.getUserCommunity());
                dbTabUser.setUserBuilding(tabUser.getUserBuilding());
                tabUserMapper.edit(dbTabUser);
            }
            tabWorkRecords.setId(assistantConfig.getPrimary());
            tabWorkRecords.setUserid(dbTabUser == null ? tabUser.getUserid() : dbTabUser.getUserid());
            tabWorkRecords.setWorkDate(LocalDate.now());
            tabWorkRecords.setWorkTime(LocalDateTime.now());
            tabWorkRecordsMapper.create(tabWorkRecords);
            //
            Map dataMap = new HashMap();
            dataMap.put("tabWorkRecords", tabWorkRecords);
            return Page.ok(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("--->>> error ---> {} <<<---", e.toString());
            return Page.fail("提交失败:".concat(e.toString().substring(0, 50)));
        }
    }

}
