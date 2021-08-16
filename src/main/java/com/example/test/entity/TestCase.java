package com.example.test.entity;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/8 10:48 上午
 */
public class TestCase {
    private Integer id;
    private String testCaseName;
    private Integer sid;
    private List<IManagement> iManagements;
    private List<TestReport> testReports;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public List<IManagement> getiManagements() {
        return iManagements;
    }

    public void setiManagements(List<IManagement> iManagements) {
        this.iManagements = iManagements;
    }

    public List<TestReport> getTestReports() {
        return testReports;
    }

    public void setTestReports(List<TestReport> testReports) {
        this.testReports = testReports;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", testCaseName='" + testCaseName + '\'' +
                ", sid=" + sid +
                ", iManagements=" + iManagements +
                ", testReports=" + testReports +
                '}';
    }
}
