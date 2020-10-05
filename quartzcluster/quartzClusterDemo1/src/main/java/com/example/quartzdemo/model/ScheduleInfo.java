package com.example.quartzdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/5 18:32
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String jobDetailName;

    private String jobDetailGroup;

    private String triggerName;

    private String triggerGroup;

    private Integer executeCount;

    public ScheduleInfo(String jobDetailName,String jobDetailGroup,String triggerName,
                      String triggerGroup,Integer executeCount){
        this.jobDetailName = jobDetailName;
        this.jobDetailGroup = jobDetailGroup;
        this.triggerName = triggerName;
        this.triggerGroup = triggerGroup;
        this.executeCount = executeCount;
    }

}
