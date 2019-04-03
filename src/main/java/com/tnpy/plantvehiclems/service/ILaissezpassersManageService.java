package com.tnpy.plantvehiclems.service;

import java.util.List;

import com.tnpy.plantvehiclems.model.mysql.TbLaissezPasserInfo;

public interface ILaissezpassersManageService {
	int insert(TbLaissezPasserInfo tbLaissezPasserInfo);
	 
	TbLaissezPasserInfo selectByPrimaryKey(String id);
	
	List<TbLaissezPasserInfo> listAll(String filter);

	String selectByCarLicenceTime(String carLicence);
	
	int deleteByPrimaryKey(String id);
	
	int updateByPrimaryKeySelective(TbLaissezPasserInfo tbLaissezPasserInfo);

	int updateByPrimaryKey(TbLaissezPasserInfo tbLaissezPasserInfo);
}
