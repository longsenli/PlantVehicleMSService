package com.tnpy.plantvehiclems.service;

import java.util.List;

import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;

public interface IVehicleManageService {
	int insert(TbCarInfo tbcarinfo);
	 
	TbCarInfo selectByPrimaryKey(String id);
	
	List<TbCarInfo> listAll();
	
	int deleteByPrimaryKey(String userid);
	
	int updateByPrimaryKeySelective(TbCarInfo tbcarinfo);

	int updateByPrimaryKey(TbCarInfo tbcarinfo);
}
