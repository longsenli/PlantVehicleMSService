
package com.tnpy.plantvehiclems.mapper.mysql;

import com.tnpy.plantvehiclems.model.mysql.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TbUserMapper {
	int deleteByPrimaryKey(String userid);

	int insert(TbUser record);

	int insertSelective(TbUser record);

	TbUser selectByPrimaryKey(String userid);

//	@Select("select u.*,(select r.role_name from tb_role r where r.role_id=u.role_id) as roleName from tb_user u ")
	List<TbUser> userList();

	int updateByPrimaryKeySelective(TbUser record);

	int updateByPrimaryKey(TbUser record);
}