package com.example.quartzdemo.scheduled;

import com.example.quartzdemo.dao.ScheduledCronMapper;
import com.example.quartzdemo.model.ScheduledCron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 14:59
 */
@Configuration
@EnableScheduling
public class SchedulerChangeCronTask implements SchedulingConfigurer {

    private static String cron = "*/3 * * * * ?";

    @Autowired
    ScheduledCronMapper scheduledCronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        ScheduledCron scheduledCron = scheduledCronMapper.select("scheduledCron");
        if(scheduledCron != null){
            cron = scheduledCron.getDateCron();
        }
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                //其中configureTasks()是需要执行的任务
                () -> System.out.println("执行动态定时任务: " +cron),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    System.out.println("数据库cron="+cron);
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
