package com.tnpy.plantvehiclems.service.carDriveRecordService.impl;

import com.alibaba.fastjson.JSONObject;
import com.tnpy.common.utils.web.TNPYResponse;
import com.tnpy.plantvehiclems.mapper.mysql.CarDriveRecordMapper;
import com.tnpy.plantvehiclems.model.mysql.CarDriveRecord;
import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;
import com.tnpy.plantvehiclems.service.carDriveRecordService.ICarDriveRecordService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-05-10 13:29
 */
@Service("carDriveRecordService")
public class CarDriveRecordServiceImpl implements ICarDriveRecordService {

    @Autowired
    private CarDriveRecordMapper carDriveRecordMapper;

    @Override
    public TNPYResponse getCarBasicInfo(String id) {
        return null;
    }

    @Override
    public TNPYResponse addCarBasicInfo(String jsonStr) {
        return null;
    }

    @Override
    public TNPYResponse deleteCarBasicInfo(String id) {
        return null;
    }

    public TNPYResponse getCarStayInPlant()
    {
        TNPYResponse result = new TNPYResponse();
        try {

            List<Map<Object, Object>> mapList = carDriveRecordMapper.getCarStayInPlant();
            result.setStatus(1);
            result.setData(JSONObject.toJSON(mapList).toString());
            return result;
        } catch (Exception ex) {
            result.setMessage("查询出错！" + ex.getMessage());
            return result;
        }
    }
    @Override
    public TNPYResponse getCarDriveRecord(String id, String startTime, String endTime) {
        TNPYResponse result = new TNPYResponse();
        try {
            String carFilter ="";
            if(!"-1".equals(id))
            {
                carFilter = " and carID = '" + id + "' ";
            }
            List<Map<Object, Object>> mapList = carDriveRecordMapper.selectRecordByFilter(carFilter, startTime, endTime);
            result.setStatus(1);
            result.setData(JSONObject.toJSON(mapList).toString());
            return result;
        } catch (Exception ex) {
            result.setMessage("查询出错！" + ex.getMessage());
            return result;
        }
    }

    @Override
    public TNPYResponse addCarDriveRecord(String carID, String recorderID, String recorderName, String driveType) {
        TNPYResponse result = new TNPYResponse();
        try {

            List<Map<Object, Object>> carExist  = carDriveRecordMapper.judgeCarIDExist(carID);
            if(carExist.size()< 1)
            {
                result.setMessage("无效二维码，请核对！" + carID);
                return result;
            }
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();//取时间
            Calendar calendar = new GregorianCalendar();
            String timeCompare = dateFormat2.format(date);
            calendar.setTime(date);

            calendar.add(Calendar.SECOND, -30);
            date = calendar.getTime();   //这个时间就是日期往后推一天的结果
            timeCompare = dateFormat2.format(date);
            if ("come".equals(driveType)) {
                List<Map<Object, Object>> recordList = carDriveRecordMapper.judgeRepetitionComeRecord(carID, timeCompare);
                if (recordList.size() < 1) {
                    carDriveRecordMapper.updateCarInOutStats(carID);
                    CarDriveRecord carDriveRecord = new CarDriveRecord();
                    carDriveRecord.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                    carDriveRecord.setCarid(carID);
                    carDriveRecord.setCometime(new Date());
                    carDriveRecord.setComerecorder(recorderName);
                    carDriveRecord.setComerecorderid(recorderID);
                    carDriveRecord.setStatus("1");
                    carDriveRecordMapper.insert(carDriveRecord);

                    result.setStatus(1);
                    result.setMessage("车辆入厂！ " + carID + "   " + dateFormat2.format(new Date()));
                    return result;

                }

                result.setMessage("该电动车已经扫码！" + recordList.get(0).get("id") + "  " + recordList.get(0).get("comeTime"));
                return result;
            } else if ("go".equals(driveType)) {
                List<Map<Object, Object>> carComeRecord = carDriveRecordMapper.getLatestComeRecord(carID);
                if (carComeRecord.size() < 0) {
                    CarDriveRecord carDriveRecord = new CarDriveRecord();
                    carDriveRecord.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                    carDriveRecord.setCarid(carID);
                    carDriveRecord.setGotime(new Date());
                    carDriveRecord.setCometime(new Date());
                    carDriveRecord.setComerecorder(recorderName);
                    carDriveRecord.setComerecorderid(recorderID);
                    carDriveRecord.setGorecorder(recorderName);
                    carDriveRecord.setGorecorderid(recorderID);
                    carDriveRecord.setStatus("4");
                    carDriveRecordMapper.insert(carDriveRecord);
                    result.setStatus(1);
                    result.setMessage("车辆出厂，但未找到入厂记录！ " + carID + "   " + dateFormat2.format(new Date()));
                    return result;
                } else {
                    if (StringUtils.isEmpty(carComeRecord.get(0).get("goTime"))) {
                        CarDriveRecord carDriveRecord = new CarDriveRecord();
                        carDriveRecord.setId(carComeRecord.get(0).get("id").toString());
                        carDriveRecord.setGotime(new Date());
                        carDriveRecord.setGorecorder(recorderName);
                        carDriveRecord.setGorecorderid(recorderID);
                        carDriveRecord.setStatus("2");
                        carDriveRecordMapper.updateByPrimaryKeySelective(carDriveRecord);
                        result.setStatus(1);
                        result.setMessage("车辆出厂!入厂时间： " + carComeRecord.get(0).get("comeTime") + "  \r\n" + carID + "  出厂时间： " + dateFormat2.format(new Date()));
                        return result;
                    }
                    Date goTime = dateFormat2.parse(carComeRecord.get(0).get("goTime").toString());
                    Date now = new Date();
                    long diff = Math.abs(now.getTime() - goTime.getTime());
                    long secords = diff / (1000);
                    if (secords < 30) {
                        result.setStatus(1);
                        result.setMessage("车辆已出厂! 请勿重复扫码！ " + "  \r\n" + carID + "  出厂时间： " + dateFormat2.format(new Date()));
                        return result;
                    } else {
                        CarDriveRecord carDriveRecord = new CarDriveRecord();
                        carDriveRecord.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
                        carDriveRecord.setCarid(carID);
                        carDriveRecord.setGotime(new Date());
                        carDriveRecord.setCometime(new Date());
                        carDriveRecord.setComerecorder(recorderName);
                        carDriveRecord.setComerecorderid(recorderID);
                        carDriveRecord.setGorecorder(recorderName);
                        carDriveRecord.setGorecorderid(recorderID);
                        carDriveRecord.setStatus("4");
                        carDriveRecordMapper.insert(carDriveRecord);
                        result.setStatus(1);
                        result.setMessage("车辆出厂，但未找到入厂记录！ " + carID + "   " + dateFormat2.format(new Date()));
                        return result;
                    }
                }
            } else {
                result.setMessage("不识别的操作，请重试！" + carID);
                return result;
            }

        } catch (Exception ex) {
            result.setMessage("查询出错！" + ex.getMessage());
            return result;
        }
    }

    @Override
    public TNPYResponse deleteCarDriveRecord(String id) {
        return null;
    }
}
