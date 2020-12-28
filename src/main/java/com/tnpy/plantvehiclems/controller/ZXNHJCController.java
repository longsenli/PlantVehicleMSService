package com.tnpy.plantvehiclems.controller;

import com.tnpy.common.utils.web.TNPYResponse;
import com.tnpy.plantvehiclems.service.ZXNHJCService.IZXNHJCService;
import com.tnpy.plantvehiclems.service.carDriveRecordService.ICarDriveRecordService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-12-27 9:01
 */
@RestController
@RequestMapping(value = "/api/zxnhjc")
public class ZXNHJCController {
    @Resource
    IZXNHJCService zxnhjcService ;

    @RequestMapping(value = "/addZXJCRecord")
    public TNPYResponse addZXJCRecord(String jsonListString) {
        return  zxnhjcService.addZXJCRecord(jsonListString);
    }


    @RequestMapping(value = "/selectZXJCRecord")
    public TNPYResponse selectZXJCRecord(String dno,String startTime,String endTime) {
        return  zxnhjcService.selectZXJCRecord(dno,startTime,endTime);
    }

//    @RequestMapping(value = "/updateZXJCRecord")
//    public TNPYResponse updateZXJCRecord(String jsonString) {
//        return  zxnhjcService.updateZXJCRecord( jsonString);
//    }
//
//    @RequestMapping(value = "/deleteZXJCRecord")
//    public TNPYResponse deleteZXJCRecord(String jsonString) {
//        return  zxnhjcService.deleteZXJCRecord( jsonString);
//    }

    @RequestMapping(value = "/updateZXJCRecord")
    public TNPYResponse updateZXJCRecord(String id,String newValue) {
        return  zxnhjcService.updateZXJCRecord( id,newValue);
    }

    @RequestMapping(value = "/deleteZXJCRecord")
    public TNPYResponse deleteZXJCRecord(String id) {
        return  zxnhjcService.deleteZXJCRecord( id);
    }

    @RequestMapping(value = "/getBasicIndicator")
    public TNPYResponse getBasicIndicator() {
        return  zxnhjcService.getBasicIndicator( );
    }
    @RequestMapping(value = "/updateBasicIndicator")
    public TNPYResponse updateBasicIndicator(String id,String newValue,String remark) {
        return  zxnhjcService.updateBasicIndicator(id,newValue,remark );
    }
}
