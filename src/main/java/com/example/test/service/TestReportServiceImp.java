package com.example.test.service;

import com.example.test.dao.ResultDataMapper;
import com.example.test.dao.TestReportMapper;
import com.example.test.entity.TestReport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/8/10 5:57 下午
 */
@Service("TestReport_Service")
public class TestReportServiceImp implements TestReport_Service {
    @Resource
    TestReportMapper testReportMapper;

    @Override
    public int add(TestReport testReport) {
        return testReportMapper.insert(testReport);
    }

    @Override
    public List<TestReport> selectAll() {
        return testReportMapper.selectAll();
    }
}
