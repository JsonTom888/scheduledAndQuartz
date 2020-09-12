package com.example.quartzdemo.model;

import lombok.Data;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 15:05
 */
@Data
public class ScheduledCron {
    Integer id;
    String dateCron;
    String type;
}
