package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.mapper.ccase.CommonMapper;
import com.ycmvp.util.web.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Description class
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rest/common")
public class CommonRestController {

    @Autowired
    private CommonMapper commonMapper;

    @GetMapping("/group/get/{caseDate}")
    public Map findGroupCount(@PathVariable String caseDate) {
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        Map dataMap = new HashMap();
        dataMap.put("groupCount", commonMapper.findGroupCount());
        dataMap.put("groupCountCase", commonMapper.findGroupCountCase(localDate));
        dataMap.put("groupCountExamine", commonMapper.findGroupCountExamine(localDate));
        dataMap.put("groupItems", commonMapper.findGroupItems(localDate));
        return Page.ok(dataMap);
    }

}
