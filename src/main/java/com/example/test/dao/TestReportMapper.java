package com.example.test.dao;

import com.example.test.entity.TestReport;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/8/10 5:56 下午
 */
public interface TestReportMapper {
    int insert(TestReport testReport);

    List<TestReport> selectAll();
}
