package com.example.quartzdemo.dao;

import com.example.quartzdemo.model.ScheduledCron;
import org.apache.ibatis.annotations.Select;

/**
 * @author tom
 * @version V1.0
 * @date 2020/9/12 15:07
 */
public interface ScheduledCronMapper {

    @Select("SELECT * FROM scheduled_cron WHERE type = #{type}")
//    @Results({
//            @Result(property = "dateCron",  column = "dateCron", javaType = String.class),
//            @Result(property = "type", column = "type", javaType = String.class)
//    })
    ScheduledCron select(String type);
}
