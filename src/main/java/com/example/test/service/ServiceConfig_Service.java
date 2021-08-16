package com.example.test.service;


import com.example.test.entity.ServiceConfig;
import com.example.test.entity.TestCase;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/30 2:00 下午
 */
public interface ServiceConfig_Service {
    int add(ServiceConfig serviceConfig);

    List<ServiceConfig> queryAll();

    ServiceConfig selectById(int id);

    List<ServiceConfig> selectBypid(ServiceConfig pid);

    void deleteId(ServiceConfig id);

    void update(ServiceConfig serviceConfig);

    List<ServiceConfig> selectServicePath(int sid);
}
