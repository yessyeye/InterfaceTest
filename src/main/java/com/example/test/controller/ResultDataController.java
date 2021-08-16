package com.example.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.test.entity.ResultData;
import com.example.test.service.ResultData_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @Author slyart
 * @create 2021/7/28 12:58 下午
 */
@RestController
public class ResultDataController {
    private static Logger logger = Logger.getLogger(String.valueOf(com.example.test.controller.ResultDataController.class));

    @Autowired
    ResultData_Service resultData_service;

    @RequestMapping(value = "api/selectByIManagementId", method = RequestMethod.GET)
    @ResponseBody
    public String selectByIManagementId(Integer iManagementId) {
        List<ResultData> resultData = resultData_service.selectByIManagementId(iManagementId);
        String Response = JSONArray.toJSONString(resultData);
        return Response;
    }
}
