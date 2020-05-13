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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	@Resource
	ITbUserService tbUserService;
	
	@Autowired
	TbCarInfoMapper tb;

	@Autowired
	TbUserMapper tbUserMapper;

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

	@RequestMapping(value = "/getCurrentVersion")
	public TNPYResponse getAppVersion(  ) {
		TNPYResponse response = new TNPYResponse();
		try
		{
			response.setStatus(StatusEnum.ResponseStatus.Success.getIndex());
			response.setMessage(tb.selectCurrentVersion());
			return  response;
		}
		catch (Exception ex)
		{
			response.setMessage("0");
			return  response;
		}
	}


	@RequestMapping(value = "/changePassword")
	public TNPYResponse changePassword( String userID,String newPassword,String oldPassword ) {
		TNPYResponse response = new TNPYResponse();
		try
		{
			Encryption encryption = new Encryption();
			TbUser tbUser = tbUserMapper.selectByPrimaryKey(userID);
			if(!oldPassword.trim().equals(tbUser.getPassword()) && !encryption.encrypt(oldPassword.trim(),null).equals(tbUser.getPassword()))
			{
				response.setMessage("修改密码失败！旧密码错误！"  );
				return  response;
			}
			tbUserMapper.changePassword(userID,encryption.encrypt(newPassword.trim(),null));
			response.setStatus(StatusEnum.ResponseStatus.Success.getIndex());
			response.setMessage("密码修改成功！！！");
			return  response;
		}
		catch (Exception ex)
		{
			response.setMessage("修改密码失败！" + ex.getMessage());
			return  response;
		}
	}



	@RequestMapping(value = "/addUserInfo")
	public TNPYResponse addUserInfo( String userID,String userName,String password ) {
		TNPYResponse response = new TNPYResponse();
		try
		{
			Encryption encryption = new Encryption();
			TbUser tbUser = new TbUser();
			tbUser.setId(userID);
			tbUser.setName(userName);
			tbUser.setPassword(encryption.encrypt(password.trim(),null));
			tbUser.setStatus("1");
			tbUserMapper.insertSelective(tbUser);
			response.setStatus(StatusEnum.ResponseStatus.Success.getIndex());
			response.setMessage("添加成功！！！");
			return  response;
		}
		catch (Exception ex)
		{
			response.setMessage("添加失败！" + ex.getMessage());
			return  response;
		}
	}


	@RequestMapping(value = "/selectUserInfo")
	public TNPYResponse selectUserInfo(String userID,String userName ) {
		TNPYResponse response = new TNPYResponse();
		try
		{
			String filter = " ";
			if(!"-1".equals(userID))
			{
				filter += " and id like '%"+ userID+"%'";
			}
			if(!"-1".equals(userName))
			{
				filter += " and name like '%"+ userName+"%'";
			}
			List<Map<Object, Object>> mapList = tbUserMapper.selectUserInfo(filter );
			response.setStatus(1);
			response.setData(JSONObject.toJSON(mapList).toString());
			return response;
		} catch (Exception ex) {
			response.setMessage("查询出错！" + ex.getMessage());
			return response;
		}
	}

	@RequestMapping(value = "/deleteUserInfo")
	public TNPYResponse deleteUserInfo(String userID ) {
		TNPYResponse response = new TNPYResponse();
		try
		{
			 tbUserMapper.deleteChangeStatus( userID);
			response.setStatus(1);
			return response;
		} catch (Exception ex) {
			response.setMessage("查询出错！" + ex.getMessage());
			return response;
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
		response.setMessage(myUser.getId() + " ### " + myUser.getName());
		return response;
	}
}
