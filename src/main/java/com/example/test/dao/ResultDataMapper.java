package com.example.test.dao;

import com.example.test.entity.ProjectConfig;
import com.example.test.entity.ResultData;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/7/27 3:06 下午
 */
public interface ResultDataMapper {
    int insert(ResultData resultData);

    List<ResultData> selectByIManagementId(Integer iManagementId);
}
