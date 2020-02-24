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
@RequestMapping("web/echart")
public class EchartsController {

    @GetMapping("/main")
    public ModelAndView goEcharts(ModelAndView mv) {
        mv.setViewName("echart");
        return mv;
    }

}
