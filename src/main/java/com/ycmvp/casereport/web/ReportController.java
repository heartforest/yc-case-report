package com.ycmvp.casereport.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description class
 **/
@Slf4j
@Controller
@RequestMapping("web/report")
public class ReportController {

    @GetMapping("")
    public ModelAndView sign(ModelAndView mv) {
        mv.setViewName("auto-report");
        return mv;
    }

    @GetMapping("auto-report")
    public ModelAndView goImport(ModelAndView mv) {
        mv.setViewName("auto-report");
        return mv;
    }

}
