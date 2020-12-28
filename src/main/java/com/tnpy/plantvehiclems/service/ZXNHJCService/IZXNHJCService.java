package com.tnpy.plantvehiclems.service.ZXNHJCService;

import com.tnpy.common.utils.web.TNPYResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-12-27 9:07
 */
public interface IZXNHJCService {

    public TNPYResponse getBasicIndicator();
    public TNPYResponse updateBasicIndicator(String id,String newValue,String remark);


    public TNPYResponse addZXJCRecord(String jsonListString);
    public TNPYResponse selectZXJCRecord(String dno,String startTime,String endTime);
//    public TNPYResponse updateZXJCRecord(String jsonString);
//    public TNPYResponse deleteZXJCRecord(String jsonString);

    public TNPYResponse updateZXJCRecord(String id,String newValue);
    public TNPYResponse deleteZXJCRecord(String id);
}
