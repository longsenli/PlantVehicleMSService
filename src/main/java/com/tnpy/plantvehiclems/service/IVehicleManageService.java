package com.tnpy.plantvehiclems.service;

import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface IVehicleManageService {
	int insert(TbCarInfo tbcarinfo);
	 
	TbCarInfo selectByPrimaryKey(String id);
	
	List<TbCarInfo> listAll(String filter);
	
	int deleteByPrimaryKey(String userid);
	
	int updateByPrimaryKeySelective(TbCarInfo tbcarinfo);

	int updateByPrimaryKey(TbCarInfo tbcarinfo);



}
