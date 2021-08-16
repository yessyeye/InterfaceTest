package com.example.test.service;

import com.example.test.entity.IManagement;
import com.example.test.entity.ProjectConfig;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/28 4:25 下午
 */
public interface ProjectConfig_Service {
    int add(ProjectConfig projectConfig);

    List<ProjectConfig> queryAll();

    List<ProjectConfig> selectById(ProjectConfig id);

    void deleteId(ProjectConfig id);

    void update(ProjectConfig projectConfig);
}
