package com.tnpy.plantvehiclems.controller;

import com.alibaba.fastjson.JSONObject;
import com.tnpy.common.Enum.StatusEnum;
import com.tnpy.common.utils.encryption.Encryption;
import com.tnpy.common.utils.web.TNPYResponse;
import com.tnpy.plantvehiclems.mapper.mysql.TbCarInfoMapper;
import com.tnpy.plantvehiclems.mapper.mysql.TbUserMapper;
import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;
import com.tnpy.plantvehiclems.model.mysql.TbUser;
import com.tnpy.plantvehiclems.service.userService.ITbUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	@Resource
	ITbUserService tbUserService;
	
	@Autowired
	TbCarInfoMapper tb;

	@RequestMapping(value = "/getappversion")
	public TNPYResponse getAppVersion(TbCarInfo tbcar ) {
		TNPYResponse response = new TNPYResponse();
		try
		{
			tb.insert(tbcar);
			response.setStatus(StatusEnum.ResponseStatus.Success.getIndex());
			response.setMessage("1.0.5");
			return  response;
		}
		catch (Exception ex)
		{
			response.setMessage("0");
			return  response;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public TNPYResponse login(HttpServletRequest request,@RequestParam(value = "username") String username,
							  @RequestParam(value = "password") String password) {
		//System.out.println("======="+ request.getRemoteAddr());
		TNPYResponse response = new TNPYResponse();
		Encryption encryption = new Encryption();
		//判断用户信息为空
		if ("".equals(username.trim()) || "".equals(password.trim())) {
			response.setMessage("传入的用户名/密码为空！");
			return response;
		}
		
		//根据客户用户名查找数据库的usre对象
		TbUser myUser = tbUserService.selectByPrimaryKey(username);
//
//		//判断用户不存在
		if (null == myUser) {
			response.setMessage("用户不存在");
			return response;
		}
		//判断用户密码
		if (!password.trim().equals(myUser.getPassword()) && !encryption.encrypt(password.trim(),null).equals(myUser.getPassword())) {
			response.setMessage("密码不正确");
			return response;
		}

		response.setStatus(StatusEnum.ResponseStatus.Success.getIndex());
		response.setMessage("登陆成功");
		return response;
	}
}
