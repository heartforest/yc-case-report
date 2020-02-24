package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.mapper.ccase.ViewCaseMapper;
import com.ycmvp.util.web.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description class
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rest/echart")
public class EchartsRestController {

    @Autowired
    private ViewCaseMapper viewCaseMapper;

    @GetMapping("/autoview/{viewnames}")
    public Map findAutoViewname(@PathVariable String viewnames) {
        List<String> vs = Arrays.asList(viewnames.split(","));
        Map dataMap = new HashMap();
        vs.forEach(viewname -> {
            dataMap.put(viewname, viewCaseMapper.findByViewname(viewname));
        });
        return Page.ok(dataMap);
    }

}
