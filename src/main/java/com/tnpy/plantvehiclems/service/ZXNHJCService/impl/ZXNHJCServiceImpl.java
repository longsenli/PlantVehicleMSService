package com.tnpy.plantvehiclems.service.ZXNHJCService.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tnpy.common.utils.web.TNPYResponse;
import com.tnpy.plantvehiclems.mapper.mysql.CarDriveRecordMapper;
import com.tnpy.plantvehiclems.model.mysql.ZXNHJCBasicRecord;
import com.tnpy.plantvehiclems.service.ZXNHJCService.DBOperator;
import com.tnpy.plantvehiclems.service.ZXNHJCService.IZXNHJCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-12-27 9:08
 */
@Service("zxnhjcService")
public class ZXNHJCServiceImpl implements IZXNHJCService {



    public TNPYResponse getBasicIndicator(){
        TNPYResponse result = new TNPYResponse();
        try {

            String sqlString = " SELECT * FROM tn_basicindicatorinfo where status = '1'";

            List<Map<Object, Object>> mapList = DBOperator.selectData(sqlString);
            result.setStatus(1);
            result.setData(JSONObject.toJSON(mapList).toString());
            return result;
        } catch (Exception ex) {
            result.setMessage("查询出错！" + ex.getMessage());
            return result;
        }
    }
    public TNPYResponse updateBasicIndicator(String id,String newValue,String remark){
        TNPYResponse result = new TNPYResponse();
        try {

            String sqlString = " update  tn_basicindicatorinfo set basicData = '"+newValue+"',remark = '"+remark+"' where  id =  '" + id + "' ";

            int count = DBOperator.executeSingleSQL(sqlString);
            result.setStatus(1);
            result.setMessage("更新" + count + "条数据！");
            return result;
        } catch (Exception ex) {
            result.setMessage("新增出错！" + ex.getMessage());
            return result;
        }
    }

    public TNPYResponse addZXJCRecord(String jsonListString){
        TNPYResponse result = new TNPYResponse();
        try {

            String sqlString = " select id,name,statType from green_collectitemconfig ";
            List<Map<Object, Object>> mapList = DBOperator.selectData(sqlString);
            HashMap<String,String> hashMap = new HashMap<>() ;
            HashMap<String,String> hashType = new HashMap<>() ;
            for(int i =0 ;i<mapList.size();i++){
                hashMap.put(mapList.get(i).get("name").toString(),mapList.get(i).get("id").toString());
                hashType.put(mapList.get(i).get("name").toString(),mapList.get(i).get("statType").toString());
            }

         //   IncomingMaterialDetail incomingMaterialDetail=(IncomingMaterialDetail) JSONObject.toJavaObject(JSONObject.parseObject(jsonString), IncomingMaterialDetail.class);
        //    List<RewardingPunishmentDetail> rewardingPunishmentDetailList = JSON.parseArray(rewardingPunishmentDetailListJson, RewardingPunishmentDetail.class);
            List<ZXNHJCBasicRecord> zxnhjcBasicRecordList = JSON.parseArray(jsonListString, ZXNHJCBasicRecord.class);
         //   List<Map<Object, Object>> mapList = carDriveRecordMapper.getCarStayInPlant();
            List<String> sqlList = new ArrayList<>();


            for( int i =0 ;i< zxnhjcBasicRecordList.size();i++)
            {
//                sqlList.add(" insert into d_db_days (dno,lock_date,smsg,datasource,create_user,gmt_create )  values ( '" + zxnhjcBasicRecordList.get(i).getDno()
//                        +" ' , '" + zxnhjcBasicRecordList.get(i).getLock_date() + "','" + zxnhjcBasicRecordList.get(i).getSmsg() + "','" +zxnhjcBasicRecordList.get(i).getDatasource()
//                        + "','" + zxnhjcBasicRecordList.get(i).getCreate_user() +"',now() ); \r\n ");
                sqlList.add(" insert into green_handdata (id,corporationCode,dataId,statDate,dataValue,statType,stamp )  values (  REPLACE( uuid(),'-',''),'914109005860634614','"
                        + hashMap.get( zxnhjcBasicRecordList.get(i).getEmsg())
                        +" ' , '" + zxnhjcBasicRecordList.get(i).getLock_date() + "','" + zxnhjcBasicRecordList.get(i).getSmsg() + "','"+ hashType.get( zxnhjcBasicRecordList.get(i).getEmsg())+ "',now() ); \r\n ");
            }
            int count = DBOperator.executeBatchSQL(sqlList);
            result.setStatus(1);
            result.setMessage("新增" + count + "条数据！");
            return result;
        } catch (Exception ex) {
            result.setMessage("新增出错！" + ex.getMessage());
            return result;
        }
    }

    public TNPYResponse selectZXJCRecord(String dno,String startTime,String endTime){
        TNPYResponse result = new TNPYResponse();
        try {

            String sqlString = " SELECT a.id,a.statDate,a.dataValue,a.statType,a.stamp,a.dataId ,b.name,b.dataCode FROM bootdo.green_handdata a left join green_collectitemconfig b on a.dataId = b.id where " +
                    "  statDate >=  '" + startTime + "' and statDate <= '" + endTime + "' ";
            if(!"-1".equals(dno))
            {
                sqlString += " and b.dataCode = '" + dno + "' ";
            }
            sqlString += " order by statDate,dataId ";
             List<Map<Object, Object>> mapList = DBOperator.selectData(sqlString);
            result.setStatus(1);
            result.setData(JSONObject.toJSON(mapList).toString());
            return result;
        } catch (Exception ex) {
            result.setMessage("查询出错！" + ex.getMessage());
            return result;
        }
    }

//    public TNPYResponse updateZXJCRecord(String jsonString)
//    {
//        TNPYResponse result = new TNPYResponse();
//        try {
//            ZXNHJCBasicRecord zxnhjcBasicRecord=(ZXNHJCBasicRecord) JSONObject.toJavaObject(JSONObject.parseObject(jsonString), ZXNHJCBasicRecord.class);
//
//            String sqlString = " update  d_db_days set smsg = '"+zxnhjcBasicRecord.getSmsg()+"' where  lock_date =  '" + zxnhjcBasicRecord.getLock_date() + "' and dno = '" + zxnhjcBasicRecord.getDno() + "' ";
//
//            int count = DBOperator.executeSingleSQL(sqlString);
//            result.setStatus(1);
//            result.setMessage("更新" + count + "条数据！");
//            return result;
//        } catch (Exception ex) {
//            result.setMessage("新增出错！" + ex.getMessage());
//            return result;
//        }
//    }
//    public TNPYResponse deleteZXJCRecord(String jsonString)
//    {
//        TNPYResponse result = new TNPYResponse();
//        try {
//            ZXNHJCBasicRecord zxnhjcBasicRecord=(ZXNHJCBasicRecord) JSONObject.toJavaObject(JSONObject.parseObject(jsonString), ZXNHJCBasicRecord.class);
//
//            String sqlString = " update  d_db_days set dno =  concat(dno,'-delete')  where  lock_date =  '" + zxnhjcBasicRecord.getLock_date() + "' and dno = '" + zxnhjcBasicRecord.getDno() + "' ";
//
//            int count = DBOperator.executeSingleSQL(sqlString);
//            result.setStatus(1);
//            result.setMessage("删除" + count + "条数据！");
//            return result;
//        } catch (Exception ex) {
//            result.setMessage("新增出错！" + ex.getMessage());
//            return result;
//        }
//    }

    public TNPYResponse updateZXJCRecord(String id,String newValue)
    {
        TNPYResponse result = new TNPYResponse();
        try {

            String sqlString = " update  green_handdata set dataValue = '"+newValue+"' where  id =  '" + id + "' ";

            int count = DBOperator.executeSingleSQL(sqlString);
            result.setStatus(1);
            result.setMessage("更新" + count + "条数据！");
            return result;
        } catch (Exception ex) {
            result.setMessage("新增出错！" + ex.getMessage());
            return result;
        }
    }
    public TNPYResponse deleteZXJCRecord(String id)
    {
        TNPYResponse result = new TNPYResponse();
        try {

            String sqlString = " delete from   green_handdata    where  id =  '" + id + "'   ";

            int count = DBOperator.executeSingleSQL(sqlString);
            result.setStatus(1);
            result.setMessage("删除" + count + "条数据！");
            return result;
        } catch (Exception ex) {
            result.setMessage("新增出错！" + ex.getMessage());
            return result;
        }
    }

}
