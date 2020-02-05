package com.ycmvp.casereport.web;

import com.ycmvp.casereport.config.CaseReportConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("web/info")
public class InfoController {

    @Autowired
    private CaseReportConfig caseReportConfig;

    @GetMapping("")
    public ModelAndView main(ModelAndView mv) {
        mv.setViewName("info");
        mv.addObject("title", "( ^_^ )");
        return mv;
    }

}