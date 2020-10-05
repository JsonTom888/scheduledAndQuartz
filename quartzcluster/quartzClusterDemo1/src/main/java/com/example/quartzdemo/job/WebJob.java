package com.example.quartzdemo.job;

import com.example.quartzdemo.dao.QuartzInfoRepository;
import com.example.quartzdemo.model.ScheduleInfo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 通过web页面创建的定时任务job
 * @author tom
 * @version V1.0
 * @date 2020/10/4 15:30
 */
@Component
public class WebJob implements Job {

    @Autowired
    QuartzInfoRepository quartzInfoRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        HashMap<String,String> map = new HashMap<>();
        for(Object o:dataMap.keySet()){
            map.put((String)o,dataMap.getString((String)o));
        }

        String jobName = context.getJobDetail().getKey().getName();
        ScheduleInfo quartzInfo = quartzInfoRepository.findByJobDetailName(jobName);
        if(quartzInfo != null){
            quartzInfo.setExecuteCount(quartzInfo.getExecuteCount()+1);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowDate = df.format(new Date());
            System.out.println(nowDate+ " my web job prams: "+map.toString()+quartzInfo.getExecuteCount());
            quartzInfoRepository.save(quartzInfo);
        }
    }
}
