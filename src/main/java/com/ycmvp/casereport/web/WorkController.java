package com.ycmvp.casereport.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description class
 **/
@Slf4j
@Controller
@RequestMapping("web/work")
public class WorkController {

    @GetMapping("/{workType}/{workid}")
    public ModelAndView goWork(@PathVariable("workType") String workType, @PathVariable("workid") String workid, ModelAndView mv) {
        mv.addObject("workid", workid);
        mv.addObject("workType", workType);
        mv.setViewName("work");
        return mv;
    }

}
