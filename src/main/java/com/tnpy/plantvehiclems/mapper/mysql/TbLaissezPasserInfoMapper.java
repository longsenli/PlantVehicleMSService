package com.tnpy.plantvehiclems.mapper.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tnpy.plantvehiclems.model.mysql.TbLaissezPasserInfo;

public interface TbLaissezPasserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TbLaissezPasserInfo record);

    int insertSelective(TbLaissezPasserInfo record);

    TbLaissezPasserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbLaissezPasserInfo record);

    int updateByPrimaryKey(TbLaissezPasserInfo record);
    
    @Select("select * from tb_laissezpasserinfo")
    List<TbLaissezPasserInfo> listAll();
}