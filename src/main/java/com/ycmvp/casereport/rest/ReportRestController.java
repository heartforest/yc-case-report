package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.config.CaseReportConfig;
import com.ycmvp.casereport.mapper.ccase.ViewCaseMapper;
import com.ycmvp.util.pub.PubCodeUtil;
import com.ycmvp.util.web.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * Description class
 **/
@Slf4j
@RestController
@RequestMapping(value = "/rest/report")
public class ReportRestController {

    @Autowired
    private CaseReportConfig caseReportConfig;
    @Autowired
    private ViewCaseMapper viewCaseMapper;

    @GetMapping("login/{timestamp}/{tokens}")
    public Map login(@PathVariable("timestamp") String timestamp, @PathVariable("tokens") String tokens) {
        if(PubCodeUtil.sha256(caseReportConfig.getPasswordkey().concat(timestamp)).equals(tokens)) {
            return Page.ok("all");
        } else if(PubCodeUtil.sha256(caseReportConfig.getPasswordkey2().concat(timestamp)).equals(tokens)) {
                return Page.ok("part2");
        } else {
            return Page.fail("口令不对");
        }
    }

    @GetMapping("/autoview/{viewname}/{caseDate}")
    public Map findAutoViewname(@PathVariable String viewname, @PathVariable String caseDate,
                                @RequestParam String timestamp, @RequestParam String tokens) {
        if(!(boolean) login(timestamp, tokens).get("success")) {
            return Page.fail("请先输入验证码!");
        }
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        List datas = viewCaseMapper.findAutoViewname(viewname, localDate);
        return Page.ok(datas);
    }
}
