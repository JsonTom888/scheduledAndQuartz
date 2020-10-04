package com.example.quartzdemo.config;

import com.example.quartzdemo.job.CronJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:23
 */
@Component
public class CronScheduler {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 向容器中添加定时任务
     * @param cron
     * @throws SchedulerException
     */
    public void scheduleJobs(String cron) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduleJob(scheduler,cron);
    }

    /**
     * 添加一个定时任务
     * @param scheduler
     * @param cron
     * @throws SchedulerException
     */
    private void scheduleJob(Scheduler scheduler,String cron) throws SchedulerException{
        JobDetail jobDetail = JobBuilder.newJob(CronJob.class)
                .withIdentity("cronJob", "jobGroup")
                .build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "triggerGroup")
                .withSchedule(scheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }

}
