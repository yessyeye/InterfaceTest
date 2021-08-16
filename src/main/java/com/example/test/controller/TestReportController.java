package com.example.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.test.entity.TestCase;
import com.example.test.entity.TestReport;
import com.example.test.service.TestCase_Service;
import com.example.test.service.TestReport_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/8/11 1:53 下午
 */
@RestController
public class TestReportController {
    private static Logger logger = Logger.getLogger(String.valueOf(com.example.test.controller.TestReportController.class));
    @Autowired
    TestReport_Service testReport_service;

    @RequestMapping(value = "api/queryTestReport", method = RequestMethod.GET)
    @ResponseBody
    public String queryTestReport() {
        List<TestReport> res = testReport_service.selectAll();
        String Response = JSONArray.toJSONString(res);
        return Response;
    }
}
