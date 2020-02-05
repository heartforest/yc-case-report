package com.ycmvp.casereport.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/info")
    public Map getServiceInfo() {
        Map map = new HashMap();
        map.put("server.name", serverName);
        map.put("server.port", port);
        return map;
    }

}
