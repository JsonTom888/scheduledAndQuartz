package com.example.quartzdemo.dao;

import com.example.quartzdemo.model.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author tom
 * @version V1.0
 * @date 2020/10/5 18:38
 */
public interface QuartzInfoRepository extends JpaRepository<ScheduleInfo,Long> {

    ScheduleInfo findByJobDetailName(String jobDetailName);

    void deleteByJobDetailName(String jobDetailName);

}
