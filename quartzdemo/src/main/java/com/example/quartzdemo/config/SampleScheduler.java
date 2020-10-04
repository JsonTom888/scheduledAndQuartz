package com.example.quartzdemo.config;

import com.example.quartzdemo.job.SampleJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/4 14:06
 */
//@Configuration
public class SampleScheduler {

//    @Bean
    public JobDetail sampleJobDetail(){
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("SampleJobDetail")
                .usingJobData("name", "Hello sample job!")
                .storeDurably()
                .build();
    }

//    @Bean
    public Trigger sampleJobTrigger(){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleTrigger")
                .withSchedule(builder)
                .build();
    }

}
