package com.example.test.service;

import com.example.test.dao.ServiceConfigMapper;
import com.example.test.entity.ServiceConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/30 2:01 下午
 */
@Service("ServiceConfig_Service")
public class ServiceConfigServiceImp implements ServiceConfig_Service {
    @Resource
    ServiceConfigMapper serviceConfigMapper;

    @Override
    public int add(ServiceConfig serviceConfig) {
        return serviceConfigMapper.insert(serviceConfig);
    }

    @Override
    public List<ServiceConfig> queryAll() {
        return serviceConfigMapper.selectAll();
    }

    @Override
    public ServiceConfig selectById(int id) {
        return serviceConfigMapper.selectById(id);
    }

    @Override
    public List<ServiceConfig> selectBypid(ServiceConfig pid) {
        return serviceConfigMapper.selectBypid(pid);
    }

    @Override
    public void deleteId(ServiceConfig id) {
        serviceConfigMapper.delete(id);
    }

    @Override
    public void update(ServiceConfig serviceConfig) {
        serviceConfigMapper.update(serviceConfig);
    }

    @Override
    public List<ServiceConfig> selectServicePath(int sid) {
        return serviceConfigMapper.selectServicePath(sid);
    }
}

