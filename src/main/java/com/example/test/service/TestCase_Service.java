package com.example.test.service;

import com.example.test.entity.TestCase;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/8 10:54 上午
 */
public interface TestCase_Service {
    int insert(TestCase testCase);

    List<TestCase> selectAll();

    List<TestCase> selectById(TestCase id);

    List<TestCase> selectBySid(TestCase sid);

    void delete(TestCase id);

    void update(TestCase testCase);

    List<TestCase> selectIManagement(TestCase id);

    List<TestCase> selectIManagementBySid(TestCase id);
}
