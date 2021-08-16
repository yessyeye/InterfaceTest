package com.example.test.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/8/10 5:42 下午
 */
public class TestReport implements Serializable {
    private Integer id;
    private Integer testCaseId;
    private String runTime;
    private List<TestCase> testCase;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public List<TestCase> getTestCase() {
        return testCase;
    }

    public void setTestCase(List<TestCase> testCase) {
        this.testCase = testCase;
    }

    @Override
    public String toString() {
        return "TestReport{" +
                "id=" + id +
                ", testCaseId=" + testCaseId +
                ", runTime='" + runTime + '\'' +
                ", testCase=" + testCase +
                '}';
    }
}
