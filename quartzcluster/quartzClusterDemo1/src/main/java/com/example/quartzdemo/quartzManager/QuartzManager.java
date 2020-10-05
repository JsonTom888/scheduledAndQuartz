package com.example.quartzdemo.quartzManager;

import com.example.quartzdemo.dao.QuartzInfoRepository;
import com.example.quartzdemo.model.ScheduleInfo;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 实现定时任务的增删改查
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:59
 */
@Component
public class QuartzManager {
    //任务组名
    public static final String JOB_GROUP_NAME = "cron_group";
    //触发器组名
    public static final String TRIGGER_GROUP_NAME = "cron_trigger";
    //触发器组名
    public static final String DEFAULT_GROUP_NAME = "DEFAULT";
    //调度工厂
    @Autowired
    private SchedulerFactoryBean schedulerFactory;
    @Autowired
    QuartzInfoRepository quartzInfoRepository;


    public Scheduler getScheduled(){
        return schedulerFactory.getScheduler();
    }

    /**
     * 添加定时任务
     * @param jobName 任务名称
     * @param jobClazz 任务job
     * @param cron 执行时间之cron表达式
     * @param params 任务job参数
     */
    public void addJob(String jobName , Class jobClazz , String cron, HashMap<String,String> params){
        try {
            //获取Scheduler
            Scheduler scheduler = getScheduled();
            // 任务名，任务组，任务执行类
            JobDetail jobDetail= JobBuilder.newJob(jobClazz).withIdentity(jobName, JOB_GROUP_NAME).build();
            // 传参（可选）
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            if(params != null ){
                for (String key:params.keySet()){
                    jobDataMap.put(key, params.get(key));
                }
            }
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(jobName, TRIGGER_GROUP_NAME);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            // 创建Trigger对象
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            // 调度容器设置JobDetail和Trigger
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
            quartzInfoRepository.save(new ScheduleInfo(jobName,JOB_GROUP_NAME,
                                                       jobName,TRIGGER_GROUP_NAME,0));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     * @param jobName 任务名称
     */
    public void removeJob(String jobName) {
        try {
            Scheduler scheduler = getScheduled();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));// 删除任务
            System.out.println("删除定时任务"+jobName);
            ScheduleInfo quartzInfo = quartzInfoRepository.findByJobDetailName(jobName);
            if(quartzInfo != null){
                quartzInfoRepository.deleteById(quartzInfo.getId());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除定时任务
     * @param jobName 任务名称
     */
    public void removeDefaultJob(String jobName) {
        try {
            Scheduler scheduler = getScheduled();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, DEFAULT_GROUP_NAME);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, DEFAULT_GROUP_NAME));// 删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动任务
     */
    public void startJobs() {
        try {
            Scheduler scheduler = getScheduled();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭任务
     */
    public void shutdownJobs() {
        try {
            Scheduler scheduler = getScheduled();
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有任务
     */
    public List<String> getAllJob(){
        List<String> jobs = new ArrayList<>();
        try {
            Set<JobKey> jobKeys = getScheduled().getJobKeys(GroupMatcher.anyGroup());
            for(JobKey jobKey:jobKeys){
                jobs.add(jobKey.getName());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
