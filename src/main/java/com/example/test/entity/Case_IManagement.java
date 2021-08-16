package com.example.test.entity;

/**
 * @Author slyart
 * @create 2021/7/13 2:31 下午TestCaseMapper.xml
 * 接口用例中间表实体类
 */
public class Case_IManagement {
    private Integer id;
    private Integer iManagement_id;
    private Integer testCase_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getiManagement_id() {
        return iManagement_id;
    }

    public void setiManagement_id(Integer iManagement_id) {
        this.iManagement_id = iManagement_id;
    }

    public Integer getTestCase_id() {
        return testCase_id;
    }

    public void setTestCase_id(Integer testCase_id) {
        this.testCase_id = testCase_id;
    }
}
