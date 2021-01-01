package com.tnpy.plantvehiclems.service.ZXNHJCService;

import com.tnpy.common.Enum.StatusEnum;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-12-28 13:44
 */

@Component //使spring管理
@EnableScheduling //定时任务注解
public class ZXNHJCReportTimer {
    @Scheduled(cron = "0 37 7 * * ?")
    public void automaticOrderStatus() {
        try {

            String sqlString = " SELECT * FROM tn_basicindicatorinfo where status = '1'";

            List<Map<Object, Object>> mapList = DBOperator.selectData(sqlString);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();//取时间

            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, -1);
            date = calendar.getTime();   //这个时间就是日期往后推一天的结果

             String dateString = dateFormat.format(date);
            Random r = new Random();
            List<String> sqlList = new ArrayList<>();
            for( int i =0 ;i< mapList.size();i++)
            {
                double dbTMP = Double.valueOf(mapList.get(i).get("basicData").toString() ) * ( 110 - r.nextInt(20) ) * 0.01;
                sqlList.add(" insert into green_handdata (id,corporationCode,dataId,statDate,dataValue,statType,stamp )  values (  REPLACE( uuid(),'-',''),'914109005860634614','"
                        + mapList.get(i).get("indicatorIDJK")
                        +" ' , '" + dateString + "','" + dbTMP   + "','"+mapList.get(i).get("statType") +"',now() ); \r\n ");
            }
            int count = DBOperator.executeBatchSQL(sqlList);

        System.out.println("生成成功：" + count);
      //      workorderMapper.finishOrder(timeFinish, StatusEnum.WorkOrderStatus.finished.getIndex() + "");
       //     workorderMapper.startOrder(timeStart, StatusEnum.WorkOrderStatus.doing.getIndex() + "");
        } catch (Exception ex) {
            System.out.println("生成失败：" + ex.getMessage());
        }
    }
}
