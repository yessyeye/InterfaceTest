package com.example.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.test.entity.TestCase;
import com.example.test.service.TestCase_Service;
import com.example.test.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/7/8 10:59 上午
 */
@RestController
public class TestCaseController {
    private static Logger logger = Logger.getLogger(String.valueOf(com.example.test.controller.TestCaseController.class));
    @Autowired
    TestCase_Service testCase_service;

    @RequestMapping(value = "api/AddTestCase", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AddTestCase(@RequestBody TestCase testCase) {
        testCase_service.insert(testCase);
        return "插入成功!";
    }

    @RequestMapping(value = "api/queryTestCase", method = RequestMethod.GET)
    @ResponseBody
    public String queryTestCase() {
        List<TestCase> res = testCase_service.selectAll();
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/deleteTestCaseId", method = RequestMethod.DELETE)
    public void deleteId(TestCase id) {
        testCase_service.delete(id);
    }

    @RequestMapping(value = "api/queryTestCaseById", method = RequestMethod.GET)
    @ResponseBody
    public String queryTestCaseById(TestCase id) {
        List<TestCase> res = testCase_service.selectById(id);
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/updateTestCase", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void update(@RequestBody TestCase testCase) {
        testCase_service.update(testCase);
    }

    @RequestMapping(value = "api/selectIManagement", method = RequestMethod.GET)
    @ResponseBody
    public String selectIManagement(TestCase id) {
        List<TestCase> res = testCase_service.selectIManagement(id);
        logger.info(res.toString());
        String Response = JSONObject.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/RunCaseTest", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String RunCaseTest(@RequestBody TestCase id) throws IOException {
        RequestUtil requestUtil = new RequestUtil();
        String Response = requestUtil.RunAll(id);
        return Response;
    }

    @RequestMapping(value = "api/AllRunCaseTest", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AllRunCaseTest(@RequestBody ArrayList<TestCase> id) throws IOException {
        RequestUtil requestUtil = new RequestUtil();
        String Response = requestUtil.RunAllTest(id);
        return Response;
    }
}
