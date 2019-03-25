package com.tnpy.plantvehiclems.controller;

import com.tnpy.common.Enum.StatusEnum;
import com.tnpy.common.utils.encryption.Encryption;
import com.tnpy.common.utils.web.TNPYResponse;
import com.tnpy.plantvehiclems.service.userService.ITbUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	@Resource
	ITbUserService tbUserService;



	@RequestMapping(value = "/getappversion")
	public TNPYResponse getAppVersion( ) {
		TNPYResponse response = new TNPYResponse();
		try
		{

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


		//判断用户不存在

		return response;
	}
}
