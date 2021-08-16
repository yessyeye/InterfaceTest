package com.example.test.service;

import com.example.test.entity.ResultData;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/27 3:10 下午
 */
public interface ResultData_Service {
    int add(ResultData resultData);

    List<ResultData> selectByIManagementId(Integer iManagementId);
}
