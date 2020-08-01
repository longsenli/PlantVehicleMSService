package com.tnpy.plantvehiclems.controller;

import com.tnpy.common.utils.web.TNPYResponse;
import com.tnpy.plantvehiclems.service.carDriveRecordService.ICarDriveRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-05-10 13:28
 */

@RestController
@RequestMapping(value = "/api/carDriveRecord")
public class CarDriveRecordController {
    @Resource
    ICarDriveRecordService carDriveRecordService ;

    @RequestMapping(value = "/getCarBasicInfo")
    public TNPYResponse getCarBasicInfo( String id) {
        return  carDriveRecordService.getCarBasicInfo(id);
    }

    @RequestMapping(value = "/addCarBasicInfo")
    public TNPYResponse addCarBasicInfo(@RequestBody String jsonStr) {
        return  carDriveRecordService.addCarBasicInfo(jsonStr);
    }

    @RequestMapping(value = "/deleteCarBasicInfo")
    public TNPYResponse deleteCarBasicInfo( String id) {
        return  carDriveRecordService.deleteCarBasicInfo(id);
    }


    @RequestMapping(value = "/getCarDriveRecord")
    public TNPYResponse getCarDriveRecord( String id,String startTime,String endTime) {
        return  carDriveRecordService.getCarDriveRecord(id,startTime,endTime);
    }

    @RequestMapping(value = "/addCarDriveRecord")
    public TNPYResponse addCarDriveRecord(String carID,String recorderID,String recorderName,String driveType ) {
        return  carDriveRecordService.addCarDriveRecord(carID,recorderID,recorderName,driveType);
    }


    @RequestMapping(value = "/deleteCarDriveRecord")
    public TNPYResponse deleteCarDriveRecord(String id) {
        return  carDriveRecordService.deleteCarDriveRecord(id);
    }


    @RequestMapping(value = "/getCarStayInPlant")
    public TNPYResponse getCarStayInPlant() {
        return  carDriveRecordService.getCarStayInPlant();
    }


}
