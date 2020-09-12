package com.example.quartzdemo.scheduled;

import com.example.quartzdemo.dao.ScheduledCronMapper;
import com.example.quartzdemo.model.ScheduledCron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

/**
 * 该方法能够改变定时任务执行时间，但是不能立即生效
 *
 * @author tom
 * @version V1.0
 * @date 2020/9/12 14:59
 */
//@Configuration
//@EnableScheduling
public class SchedulerChangeCronTask implements SchedulingConfigurer {

    private static String cron = "*/3 * * * * ?";

    @Autowired
    ScheduledCronMapper scheduledCronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                //其中configureTasks()是需要执行的任务
                () -> System.out.println("执行不立即生效的定时任务: " +cron),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    ScheduledCron scheduledCron = scheduledCronMapper.select("scheduledCron");
                    if(scheduledCron != null){
                        if("*/10 * * * * ?".equals(scheduledCron.getDateCron())){
                            System.out.println("10秒不能生效，数据库查到的数据："+scheduledCron.getDateCron());
                        }else{
                            cron = scheduledCron.getDateCron();
                            System.out.println("数据库查到的数据："+cron);
                        }
                    }else{
                        System.out.println("数据库没有查到的数据啦。。。。。");
                    }
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
