package com.richard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.richard.mapper.JobInfoMapper;
import com.richard.pojo.JobInfo;
import com.richard.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoMapper jobInfoMapper;


    public JobInfo selectById(Long id) {
        return jobInfoMapper.selectById(id);
    }

    public List<JobInfo> selectAll() {
        QueryWrapper<JobInfo> queryWrapper = new QueryWrapper<>();
        return jobInfoMapper.selectList(queryWrapper);
    }
}
