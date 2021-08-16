package com.example.test.service;

import com.example.test.dao.ResultDataMapper;
import com.example.test.entity.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/27 3:11 下午
 */
@Service("ResultData_Service")
public class ResultDataServiceImp implements ResultData_Service {
    @Resource
    ResultDataMapper resultDataMapper;

    @Override
    public int add(ResultData resultData) {
        return resultDataMapper.insert(resultData);
    }

    @Override
    public List<ResultData> selectByIManagementId(Integer iManagementId) {
        return resultDataMapper.selectByIManagementId(iManagementId);
    }
}
