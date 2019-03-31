package com.tnpy.plantvehiclems.service.userService.impl;

import com.tnpy.plantvehiclems.mapper.mysql.TbUserMapper;
import com.tnpy.plantvehiclems.model.mysql.TbUser;
import com.tnpy.plantvehiclems.service.userService.ITbUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserServiceImpl implements ITbUserService {
	
	@Autowired
	TbUserMapper tbuserMapper ;

	@Override
	public TbUser selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return tbuserMapper.selectByPrimaryKey(id);
	}


}
