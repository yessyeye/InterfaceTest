package com.example.test.dao;

import com.example.test.entity.IManagement;
import com.example.test.entity.ProjectConfig;

import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/28 4:23 下午
 */
public interface ProjectConfigMapper {
    int insert(ProjectConfig projectConfig);

    List<ProjectConfig> selectAll();

    List<ProjectConfig> selectById(ProjectConfig id);

    void delete(ProjectConfig id);

    void update(ProjectConfig projectConfig);
}
