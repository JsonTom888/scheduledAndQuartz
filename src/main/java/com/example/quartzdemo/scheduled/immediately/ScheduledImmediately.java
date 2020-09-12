package com.example.quartzdemo.scheduled.immediately;

import com.example.quartzdemo.dao.ScheduledCronMapper;
import com.example.quartzdemo.model.ScheduledCron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * 立即生效的定时任务
 * 教程链接https://blog.csdn.net/qq_37342720/article/details/108417179
 * @author tom
 * @version V1.0
 * @date 2020/9/12 22:22
 */

@Component
@EnableScheduling
public class ScheduledImmediately implements SchedulerObjectInterface {

    private static String cron="0 */1 * * * ?";

    private ScheduledFuture future;

    @Autowired
    ScheduledCronMapper scheduledCronMapper;

    @Autowired
    private TaskScheduler scheduler;

    private boolean started;

    @Override
    public void start() {
        if(started){
            System.out.println("任务已存在");
            return;
        }
        System.out.println("开启一个定时任务");
        future = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println( "立即生效的定时任务 Hello World! " + new Date());
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                String cron = getDatabaseRcon();
                System.out.println("cron++++"+cron);
                CronTrigger trigger = new CronTrigger(cron);
                return trigger.nextExecutionTime(triggerContext);
            }
        });
        started = true;
    }

    @Override
    public void stop() {
        if(future != null){
            future.cancel(false);
            started = false;
            System.out.println("任务已停止");
        }

    }

    /**
     * 查询数据库
     *
     * @return
     */
    public String getDatabaseRcon(){
        ScheduledCron scheduledCron = scheduledCronMapper.select("immediately");
        return scheduledCron != null ? scheduledCron.getDateCron():ScheduledImmediately.cron;
    }
}
