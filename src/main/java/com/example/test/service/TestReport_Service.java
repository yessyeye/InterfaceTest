package com.example.test.service;

import com.example.test.entity.IManagement;
import com.example.test.entity.TestReport;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/8/10 5:57 下午
 */
public interface TestReport_Service {
    int add(TestReport testReport);

    List<TestReport> selectAll();
}
