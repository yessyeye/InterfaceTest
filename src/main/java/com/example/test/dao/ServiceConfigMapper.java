package com.example.test.dao;


import com.example.test.entity.ServiceConfig;
import com.example.test.entity.TestCase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/30 1:57 下午
 */
public interface ServiceConfigMapper {
    int insert(ServiceConfig serviceConfig);

    List<ServiceConfig> selectAll();

    ServiceConfig selectById(int id);

    List<ServiceConfig> selectBypid(ServiceConfig pid);

    void delete(ServiceConfig id);

    void update(ServiceConfig serviceConfig);

    List<ServiceConfig> selectServicePath(int sid);
}
