package com.example.test.controller;

import com.example.test.entity.Case_IManagement;
import com.example.test.entity.IManagement;
import com.example.test.service.Case_IManagement_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/13 2:44 下午
 * 接口用例中间表增删改查
 */
@RestController
public class Case_IManagementController {
    @Autowired
    Case_IManagement_Service case_iManagement_service;

    @RequestMapping(value = "api/CaseIManagement", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String AddCaseIManagement(@RequestBody List<Case_IManagement> case_iManagement) {
        case_iManagement_service.insertCaseIManagement(case_iManagement);
        return "查询成功!";
    }
}
