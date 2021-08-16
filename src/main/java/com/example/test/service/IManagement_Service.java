package com.example.test.service;

import com.example.test.entity.IManagement;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/17 3:57 下午
 */
public interface IManagement_Service {
    int add(IManagement iManagement);

    List<IManagement> queryAll();

    List<IManagement> selectById(int id);

    void deleteId(IManagement id);

    void update(IManagement iManagement);

}
