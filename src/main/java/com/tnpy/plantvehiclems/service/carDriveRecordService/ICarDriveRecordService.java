package com.tnpy.plantvehiclems.service.carDriveRecordService;

import com.tnpy.common.utils.web.TNPYResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-05-10 13:28
 */
public interface ICarDriveRecordService {

    public TNPYResponse getCarBasicInfo( String id);
    public TNPYResponse addCarBasicInfo( String jsonStr);
    public TNPYResponse deleteCarBasicInfo(String id);



    public TNPYResponse getCarDriveRecord( String id,String startTime,String endTime) ;
    public TNPYResponse addCarDriveRecord(String carID,String recorderID,String recorderName,String driveType );
    public TNPYResponse deleteCarDriveRecord(String id);
    public TNPYResponse getCarStayInPlant();
}
