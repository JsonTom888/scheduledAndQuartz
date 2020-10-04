package com.example.quartzdemo.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.HashMap;

/**
 * 通过web页面创建的定时任务job
 * @author tom
 * @version V1.0
 * @date 2020/10/4 15:30
 */
public class WebJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        HashMap<String,String> map = new HashMap<>();
        for(Object o:dataMap.keySet()){
            map.put((String)o,dataMap.getString((String)o));
        }
        System.out.println("my web job prams: "+map.toString());
    }

}
