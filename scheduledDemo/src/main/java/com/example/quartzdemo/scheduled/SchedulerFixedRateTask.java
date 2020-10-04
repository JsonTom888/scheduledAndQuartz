package com.example.quartzdemo.scheduled;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 14:04
 */
@Component
public class SchedulerFixedRateTask {
    private static final SimpleDateFormat dateForm =
            new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedRate = 6000 上⼀次开始执行时间点之后 6 秒再执行。
     * fixedDelay = 6000 上⼀次执⾏行行完毕时间点之后 6 秒再执⾏
     * initialDelay=1000 第一次延迟 1 秒后执⾏
     */
//    @Scheduled(fixedRate = 6000)
    public void schedulerFixedRateTask(){
        System.out.println("now time : "+dateForm.format(new Date()));
    }


}
