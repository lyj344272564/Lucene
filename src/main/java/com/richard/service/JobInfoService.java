package com.richard.service;

import com.richard.pojo.JobInfo;

import java.util.List;

public interface JobInfoService {

    public JobInfo selectById(Long id);

    public List<JobInfo> selectAll();
}
