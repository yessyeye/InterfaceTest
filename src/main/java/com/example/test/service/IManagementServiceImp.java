package com.example.test.service;

import com.example.test.dao.IManagementMapper;
import com.example.test.dao.ServiceConfigMapper;
import com.example.test.entity.IManagement;
import com.example.test.entity.ServiceConfig;
import com.example.test.util.HttpClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author slyart
 * @create 2021/6/17 3:58 下午
 */
@Service("IManagement_Service")
public class IManagementServiceImp implements IManagement_Service {
    @Resource
    IManagementMapper iManagementMapper;

    public int add(IManagement iManagement) {
        return iManagementMapper.insert(iManagement);
    }

    @Override
    public List<IManagement> queryAll() {
        return iManagementMapper.selectAll();
    }

    @Override
    public List<IManagement> selectById(int id) {
        return iManagementMapper.selectById(id);
    }

    public void deleteId(IManagement id) {
        iManagementMapper.delete(id);
    }

    @Override
    public void update(IManagement iManagement) {
        iManagementMapper.update(iManagement);
    }

}
