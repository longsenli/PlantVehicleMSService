package com.tnpy.plantvehiclems.mapper.mysql;

import com.tnpy.plantvehiclems.model.mysql.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TbUserMapper {
	
	int deleteByPrimaryKey(String id);
	
    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    @Update(" update tb_user set password = #{password} where id = #{userID} ")
    int changePassword(String userID,String password );

    @Select(" select id,name from tb_user where status = '1' ${filter} order by id ")
    List<Map<Object, Object>> selectUserInfo(@Param("filter") String filter);

    @Select(      "update plantvehiclemsdb.tb_user set id =concat( id , '_del'),status ='-1' where id = #{userID} ")
    List<Map<Object, Object>> deleteChangeStatus( String userID);
}