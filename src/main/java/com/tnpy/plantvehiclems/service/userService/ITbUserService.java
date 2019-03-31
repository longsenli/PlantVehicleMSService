package com.tnpy.plantvehiclems.service.userService;

import com.tnpy.plantvehiclems.model.mysql.TbUser;

public interface ITbUserService {
	 public TbUser selectByPrimaryKey(String id);
}
