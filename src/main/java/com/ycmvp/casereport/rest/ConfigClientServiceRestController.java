package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.config.CaseReportConfig;
import com.ycmvp.util.web.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description class
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rest/casereport/service")
public class ConfigClientServiceRestController {

    @Value("${server.name}")
    private String serverName;
    @Value("${server.port}")
    private String port;
    @Autowired
    private CaseReportConfig caseReportConfig;

    @GetMapping("/info")
    public Map getServiceInfo() {
        Map map = new HashMap();
        map.put("server.name", serverName);
        map.put("server.port", port);
        return map;
    }

    @GetMapping("/debug/{debug}")
    public Map setDebug(@PathVariable String debug) {
        caseReportConfig.setDebug(debug);
        return Page.ok("change");
    }
}
