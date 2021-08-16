package com.example.test.service;

import com.example.test.dao.Case_IManagementMapper;
import com.example.test.entity.Case_IManagement;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/13 2:40 下午
 */
@Service("Case_IManagement_Service")
public class Case_IManagementServiceImp implements Case_IManagement_Service {
    @Resource
    Case_IManagementMapper case_iManagementMapper;

    @Override
    public int insertCaseIManagement(List<Case_IManagement> list) {
        return case_iManagementMapper.insertCaseIManagement(list);
    }
}
