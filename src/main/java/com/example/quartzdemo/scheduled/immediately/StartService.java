package com.example.quartzdemo.scheduled.immediately;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 22:36
 */
@Component
@Order(value = 1)
public class StartService implements ApplicationRunner {
    @Autowired
    private ScheduledImmediately scheduledImmediately;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(new Date());
        scheduledImmediately.start();
    }
}
