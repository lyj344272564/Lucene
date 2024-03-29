package com.richard.pojo;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "job_info")
public class JobInfo {

  @Id
  private long id;
  private String companyName;
  private String companyAddr;
  private String companyInfo;
  private String jobName;
  private String jobAddr;
  private String jobInfo;
  private int salaryMin;
  private int salaryMax;
  private String url;
  private String time;

}
