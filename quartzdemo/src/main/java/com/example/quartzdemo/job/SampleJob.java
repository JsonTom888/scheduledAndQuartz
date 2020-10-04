package com.example.quartzdemo.job;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 简单定时任务
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:03
 */
@Data
public class SampleJob extends QuartzJobBean {

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext context) {
//        System.out.println(String.format("sampleJob name:%s !",this.name));
    }

}
