package com.ycmvp.casereport.rest;

import com.ycmvp.casereport.config.CaseReportConfig;
import com.ycmvp.casereport.entity.ccase.ViewCase;
import com.ycmvp.casereport.mapper.ccase.*;
import com.ycmvp.util.pub.PubCodeUtil;
import com.ycmvp.util.web.Page;
import com.ycmvp.util.web.StaCom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private ViewPut1Mapper viewPut1Mapper;
    @Autowired
    private ViewPut2Mapper viewPut2Mapper;
    @Autowired
    private ViewPut3Mapper viewPut3Mapper;
    @Autowired
    private ViewPut4Mapper viewPut4Mapper;

    @GetMapping("login/{timestamp}/{hashSha}")
    public Map login(@PathVariable("timestamp") String timestamp, @PathVariable("hashSha") String hashSha, HttpServletRequest request) {
        if(PubCodeUtil.sha256(caseReportConfig.getPasswordkey().concat(timestamp)).equals(hashSha)) {
            HttpSession session = request.getSession();
            session.setAttribute(StaCom.SESSION_USER, "passwordkey");
            return Page.ok("pwd is right");
        } else {
            return Page.fail("口令不对");
        }
    }

    @GetMapping("get")
    public Map getServiceInfo(HttpServletRequest request) {
        List<ViewCase> viewCases = viewCaseMapper.findAll();
        return Page.ok(viewCases);

//        HttpSession session = request.getSession();
//        if("passwordkey".equals(session.getAttribute(StaCom.SESSION_USER))) {
//            List<ViewCase> viewCases = viewCaseMapper.findAll();
//            return Page.ok(viewCases);
//        } else {
//            return Page.fail("请先输入口令");
//        }
    }

    @GetMapping("/put1/get/{caseDate}")
    public Map findPut1(@PathVariable String caseDate, HttpServletRequest request) {
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        List putdata1 = viewPut1Mapper.findPut1(localDate);
        return Page.ok(putdata1);


//        HttpSession session = request.getSession();
//        if("passwordkey".equals(session.getAttribute(StaCom.SESSION_USER))) {
//            List putdata1 = viewPut1Mapper.findPut1(localDate);
//            return Page.ok(putdata1);
//        } else {
//            return Page.fail("请先输入口令");
//        }
    }

    @GetMapping("/put2/get/{caseDate}")
    public Map findPut2(@PathVariable String caseDate, HttpServletRequest request) {
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        List putdata2 = viewPut2Mapper.findPut2(localDate);
        return Page.ok(putdata2);

//        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
//        HttpSession session = request.getSession();
//        if("passwordkey".equals(session.getAttribute(StaCom.SESSION_USER))) {
//            List putdata2 = viewPut2Mapper.findPut2(localDate);
//            return Page.ok(putdata2);
//        } else {
//            return Page.fail("请先输入口令");
//        }
    }

    @GetMapping("/put3/get/{caseDate}")
    public Map findPut3(@PathVariable String caseDate, HttpServletRequest request) {
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        List putdata3 = viewPut3Mapper.findPut3(localDate);
        return Page.ok(putdata3);

//        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
//        HttpSession session = request.getSession();
//        if("passwordkey".equals(session.getAttribute(StaCom.SESSION_USER))) {
//            List putdata2 = viewPut2Mapper.findPut2(localDate);
//            return Page.ok(putdata2);
//        } else {
//            return Page.fail("请先输入口令");
//        }
    }

    @GetMapping("/put4/get/{caseDate}")
    public Map findPut4(@PathVariable String caseDate, HttpServletRequest request) {
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        List putdata4 = viewPut4Mapper.findPut4(localDate);
        return Page.ok(putdata4);

//        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
//        HttpSession session = request.getSession();
//        if("passwordkey".equals(session.getAttribute(StaCom.SESSION_USER))) {
//            List putdata2 = viewPut2Mapper.findPut2(localDate);
//            return Page.ok(putdata2);
//        } else {
//            return Page.fail("请先输入口令");
//        }
    }

    @GetMapping("/autoview/{viewname}/{caseDate}")
    public Map findAutoViewname(@PathVariable String viewname, @PathVariable String caseDate) {
        LocalDate localDate = LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(caseDate));
        List datas = viewCaseMapper.findAutoViewname(viewname, localDate);
        return Page.ok(datas);
    }
}
