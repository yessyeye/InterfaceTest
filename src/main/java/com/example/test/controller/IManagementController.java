package com.example.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.test.entity.IManagement;
import com.example.test.service.IManagement_Service;
import com.example.test.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/6/17 4:06 下午
 */
@RestController
public class IManagementController {
    private static Logger logger = Logger.getLogger(String.valueOf(com.example.test.controller.IManagementController.class));
    @Autowired
    private IManagement_Service iManagement_service;

    @RequestMapping(value = "api/AddIManagement", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AddIManagement(@RequestBody IManagement iManagement) {
        iManagement_service.add(iManagement);
        return "插入成功!";
    }

    @RequestMapping(value = "api/queryIManagement", method = RequestMethod.GET)
    @ResponseBody
    public String queryIManagement() {
        List<IManagement> res = iManagement_service.queryAll();
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/deleteId", method = RequestMethod.DELETE)
    public void deleteId(IManagement id) {
        iManagement_service.deleteId(id);
    }

    @RequestMapping(value = "api/queryIManagementById", method = RequestMethod.GET)
    @ResponseBody
    public String queryIManagementById(int id) {
        List<IManagement> res = iManagement_service.selectById(id);
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/updateiManagement", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void update(@RequestBody IManagement iManagement) {
        logger.info(iManagement.getInterfaceName() + iManagement.getInterfacePath());
        iManagement_service.update(iManagement);
    }

    @RequestMapping(value = "api/run", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String run(@RequestBody IManagement iManagement) throws Exception {
        RequestUtil requestUtil = new RequestUtil();
        String Response = requestUtil.run(iManagement);
        return Response;
    }
}
