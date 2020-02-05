package com.ycmvp.casereport.web;

import com.ycmvp.util.web.StaCom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description class
 **/
@Slf4j
@Controller
@RequestMapping("web/report")
public class ReportController {

    @GetMapping("")
    public ModelAndView sign(HttpServletRequest request, ModelAndView mv) {
        HttpSession session = request.getSession();
        if("passwordkey".equals(session.getAttribute(StaCom.SESSION_USER))) {
            mv.addObject("login", "hide");
        } else {
            mv.addObject("login", "show");
        }
        mv.setViewName("report");
        return mv;
    }

    @GetMapping("auto-report")
    public ModelAndView goImport(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("auto-report");
        return mv;
    }

}
