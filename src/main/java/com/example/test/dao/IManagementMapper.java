package com.example.test.dao;

import com.example.test.entity.IManagement;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/17 3:49 下午
 */
public interface IManagementMapper {
    int insert(IManagement iManagement);

    List<IManagement> selectAll();

    List<IManagement> selectById(int id);

    void delete(IManagement id);

    void update(IManagement iManagement);
}
