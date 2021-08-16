package com.example.test.service;

import com.example.test.dao.TestCaseMapper;
import com.example.test.entity.TestCase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/8 10:55 上午
 */
@Service("TestCase_Service")
public class TestCaseServiceImp implements TestCase_Service {
    @Resource
    TestCaseMapper testCaseMapper;

    @Override
    public int insert(TestCase testCase) {
        return testCaseMapper.insert(testCase);
    }

    @Override
    public List<TestCase> selectAll() {
        return testCaseMapper.selectAll();
    }

    @Override
    public List<TestCase> selectById(TestCase id) {
        return testCaseMapper.selectById(id);
    }

    @Override
    public List<TestCase> selectBySid(TestCase sid) {
        return testCaseMapper.selectBySid(sid);
    }

    @Override
    public void delete(TestCase id) {
        testCaseMapper.delete(id);
    }

    @Override
    public void update(TestCase testCase) {
        testCaseMapper.update(testCase);
    }

    @Override
    public List<TestCase> selectIManagement(TestCase id) {
        return testCaseMapper.selectIManagement(id);
    }

    @Override
    public List<TestCase> selectIManagementBySid(TestCase id) {
        return testCaseMapper.selectIManagementBySid(id);
    }
}
