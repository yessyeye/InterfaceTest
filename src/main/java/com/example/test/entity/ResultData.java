package com.example.test.entity;


import java.io.Serializable;

/**
 * @Author slyart
 * @create 2021/7/27 1:55 下午
 * 结果返回实体类
 */
public class ResultData implements Serializable {
    private Integer id;
    private String result;
    private String runTime;
    private Integer iManagementId;
    private Integer testCaseId;
    private String resultCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public Integer getiManagementId() {
        return iManagementId;
    }

    public void setiManagementId(Integer iManagementId) {
        this.iManagementId = iManagementId;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
