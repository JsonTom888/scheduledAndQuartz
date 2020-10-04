package com.example.quartzdemo.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:21
 */
public class CronJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("Cron job is running ...");
    }

}
