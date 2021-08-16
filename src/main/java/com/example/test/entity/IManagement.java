package com.example.test.entity;


import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/17 3:41 下午
 * 测试管理实体类
 */
public class IManagement {
    private Integer id;
    private String interfaceName;
    private String interfacePath;
    private String requestType;
    private String requestParameter;
    //服务id
    private Integer sid;
    private String interfaceHeader;
    private String parameterType;
    //处理器id（1.前置处理器  2.后置处理器）
    private Integer processorId;
    //参数化数据
    private String processorData;

    public Integer getid() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfacePath() {
        return interfacePath;
    }

    public void setInterfacePath(String interfacePath) {
        this.interfacePath = interfacePath;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestParameter() {
        return requestParameter;
    }

    public void setRequestParameter(String requestParameter) {
        this.requestParameter = requestParameter;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getInterfaceHeader() {
        return interfaceHeader;
    }

    public void setInterfaceHeader(String interfaceHeader) {
        this.interfaceHeader = interfaceHeader;
    }

    public Integer getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Integer processorId) {
        this.processorId = processorId;
    }

    public String getProcessorData() {
        return processorData;
    }

    public void setProcessorData(String processorData) {
        this.processorData = processorData;
    }
}
