package com.example.quartzdemo.listener;

import com.example.quartzdemo.config.CronScheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Scheduled + quartz实现特定时间启动定时任务
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:28
 */
@Configuration
@EnableScheduling
@Component
public class SchedulerListener {

    @Autowired
    public CronScheduler scheduleJobs;

//    @Scheduled(cron = "0 30 08 14 8 ?")
    public void schedule() throws SchedulerException {
        scheduleJobs.scheduleJobs("0/6 * * * * ?");
    }

}
