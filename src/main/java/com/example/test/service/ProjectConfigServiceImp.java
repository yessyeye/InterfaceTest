package com.example.test.service;

import com.example.test.dao.ProjectConfigMapper;
import com.example.test.entity.ProjectConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author slyart
 * @create 2021/6/28 4:26 下午
 */
@Service("ProjectConfig_Service")
public class ProjectConfigServiceImp implements ProjectConfig_Service {
    @Resource
    ProjectConfigMapper projectConfigMapper;

    @Override
    public int add(ProjectConfig projectConfig) {
        return projectConfigMapper.insert(projectConfig);
    }

    @Override
    public List<ProjectConfig> queryAll() {
        return projectConfigMapper.selectAll();
    }

    @Override
    public List<ProjectConfig> selectById(ProjectConfig id) {
        return projectConfigMapper.selectById(id);
    }

    @Override
    public void deleteId(ProjectConfig id) {
        projectConfigMapper.delete(id);
    }

    @Override
    public void update(ProjectConfig projectConfig) {
        projectConfigMapper.update(projectConfig);
    }
}
