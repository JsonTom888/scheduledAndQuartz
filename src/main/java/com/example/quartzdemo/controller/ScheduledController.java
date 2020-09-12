package com.example.quartzdemo.controller;

import com.example.quartzdemo.dao.ScheduledCronMapper;
import com.example.quartzdemo.scheduled.immediately.ScheduledImmediately;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 22:38
 */
@RestController
public class ScheduledController {
    @Autowired
    private ScheduledImmediately scheduledImmediately;
    @Autowired
    ScheduledCronMapper scheduledCronMapper;

    @GetMapping("start")
    public Object start(){
        scheduledImmediately.start();
        return "ok";
    }
    @GetMapping("stop")
    public Object stop(){
        scheduledImmediately.stop();
        return "ok";
    }

    @GetMapping("updateCron")
    public Object updateCron(String dateCron){
        int count = scheduledCronMapper.updateCron("scheduledCron",dateCron);
        if(count != 0){
            scheduledImmediately.stop();
            scheduledImmediately.start();
        }
        return "ok";
    }
}
