package com.example.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.test.entity.ProjectConfig;
import com.example.test.service.ProjectConfig_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/6/28 4:50 下午
 */
@RestController
public class ProjectConfigController {
    private static Logger logger = Logger.getLogger(String.valueOf(com.example.test.controller.ProjectConfigController.class));
    @Autowired
    private ProjectConfig_Service projectConfig_service;

    @RequestMapping(value = "api/AddProjectConfig", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AddProjectConfig(@RequestBody ProjectConfig projectConfig) {
        logger.info(projectConfig.getProjectName());
        projectConfig_service.add(projectConfig);
        return "插入成功!";
    }

    @RequestMapping(value = "api/queryProjectConfig", method = RequestMethod.GET)
    @ResponseBody
    public String queryProjectConfig() {
        List<ProjectConfig> res = projectConfig_service.queryAll();
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/deleteProjectConfigId", method = RequestMethod.DELETE)
    public void deleteId(ProjectConfig id) {
        projectConfig_service.deleteId(id);
    }

    @RequestMapping(value = "api/queryProjectConfigById", method = RequestMethod.GET)
    @ResponseBody
    public String queryProjectConfigById(ProjectConfig id) {
        List<ProjectConfig> res = projectConfig_service.selectById(id);
        String Response = JSONArray.toJSONString(res);
        return Response;
    }

    @RequestMapping(value = "api/updateProjectConfig", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void update(@RequestBody ProjectConfig projectConfig) {
        projectConfig_service.update(projectConfig);
    }
}
