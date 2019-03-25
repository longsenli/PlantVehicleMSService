package com.tnpy.plantvehiclems.service.timedTask;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2019/1/21 14:30
 */

@Component //使spring管理
@EnableScheduling //定时任务注解
public class AutomaticSchedulingTimer {

    /**
     * 每天晚上21:50:30运行
     */
    @Scheduled(cron = "00 45 23 * * ?")
    public void automaticScheduling() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();//取时间

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            date = calendar.getTime();   //这个时间就是日期往后推一天的结果

        } catch (Exception ex) {

        }
    }


}
