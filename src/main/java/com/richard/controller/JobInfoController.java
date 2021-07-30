package com.richard.controller;


import com.richard.pojo.JobInfo;
import com.richard.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/jobInfo")
public class JobInfoController  {

    @Autowired
    private JobInfoService jobInfoService;


    @RequestMapping("/query/{id}")
    public JobInfo selectById(@PathVariable long id) {
        return jobInfoService.selectById(id);
    }

    @RequestMapping("/queryAll")
    public List<JobInfo> selectAll() {
        return jobInfoService.selectAll();
    }

}
