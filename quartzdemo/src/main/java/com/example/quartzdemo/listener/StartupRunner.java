package com.example.quartzdemo.listener;

import com.example.quartzdemo.config.CronScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时触发定时任务
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:31
 */
@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    public CronScheduler scheduleJobs;

    @Override
    public void run(String... args) throws Exception {
//        scheduleJobs.scheduleJobs("0/6 * * * * ?");
//        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}
