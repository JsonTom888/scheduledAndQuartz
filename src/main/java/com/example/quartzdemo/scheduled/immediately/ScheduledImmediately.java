package com.example.quartzdemo.scheduled.immediately;

import com.example.quartzdemo.dao.ScheduledCronMapper;
import com.example.quartzdemo.model.ScheduledCron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * 立即生效的定时任务
 *
 * @author tom
 * @version V1.0
 * @date 2020/9/12 22:22
 */

@Configuration
@EnableScheduling
public class ScheduledImmediately implements SchedulerObjectInterface {

    private static String cron="0 */1 * * * ?";

    private ScheduledFuture future;

    @Autowired
    ScheduledCronMapper scheduledCronMapper;

    @Autowired
    private TaskScheduler scheduler;

    @Override
    public void start() {
        future = scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println( "  Hello World! " + new Date());
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

    }

    @Override
    public void stop() {
        future.cancel(false);
    }

    /**
     * 查询数据库
     *
     * @return
     */
    public String getDatabaseRcon(){
        ScheduledCron scheduledCron = scheduledCronMapper.select("scheduledCron");
        return scheduledCron != null ? scheduledCron.getDateCron():ScheduledImmediately.cron;
    }
}
