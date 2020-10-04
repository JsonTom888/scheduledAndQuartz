package com.example.quartzdemo.scheduled;

import org.springframework.stereotype.Component;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 13:55
 */
@Component
public class SchedulerCronTask {

    private int count = 1;

    /**
     * cron 一共有七位，最后⼀位是年;
     * 第一位，表示秒，取值 0 ~ 59;
     * 第二位，表示分，取值 0 ~ 59;
     * 第三位，表示小时，取值 0 ~ 23;
     * 第四位，日期(天/日)，取值 1 ~ 31;
     * 第五位，日期(⽉份)，取值 1~12;
     * 第六位，星期，取值 1 ~ 7，星期⼀，星期⼆...，注，不是第 1 周、第 2 周的意思，
     * 另外，1 表示星期天，2 表示星期⼀;
     * 第七位，年年份，可以留留空，取值 1970 ~ 2099;
     */
//    @Scheduled(cron = "*/8 * * * * ?")
    private void schedulierTask(){
        System.out.println("The scheduler task is running "+(count++));
    }

}
