package com.example.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.test.entity.IManagement;
import com.example.test.entity.ServiceConfig;
import com.example.test.service.ServiceConfig_Service;
import com.example.test.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/6/30 2:04 下午
 */
@RestController
public class ServiceConfigController {
    private static Logger logger = Logger.getLogger(String.valueOf(com.example.test.controller.ServiceConfigController.class));
    @Autowired
    private ServiceConfig_Service serviceConfig_service;

    @RequestMapping(value = "api/AddServiceConfig", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AddProjectConfig(@RequestBody ServiceConfig serviceConfig) {
        logger.info(serviceConfig.getServiceName());
        serviceConfig_service.add(serviceConfig);
        return "插入成功!";
    }

    @RequestMapping(value = "api/queryServiceConfig", method = RequestMethod.GET)
    @ResponseBody
    public String queryServiceConfig() {
        List<ServiceConfig> res = serviceConfig_service.queryAll();
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/deleteServiceConfigId", method = RequestMethod.DELETE)
    public void deleteId(ServiceConfig id) {
        serviceConfig_service.deleteId(id);
    }

    @RequestMapping(value = "api/queryServiceConfigById", method = RequestMethod.GET)
    @ResponseBody
    public String queryServiceConfigById(int id) {
        ServiceConfig res = serviceConfig_service.selectById(id);
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/queryServiceConfigBypid", method = RequestMethod.GET)
    @ResponseBody
    public String queryServiceConfigBypid(ServiceConfig pid) {
        List<ServiceConfig> res = serviceConfig_service.selectBypid(pid);
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/updateServiceConfig", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void update(@RequestBody ServiceConfig serviceConfig) {
        serviceConfig_service.update(serviceConfig);
    }
}
