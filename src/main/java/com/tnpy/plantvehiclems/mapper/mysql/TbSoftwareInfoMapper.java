package com.tnpy.plantvehiclems.mapper.mysql;

import com.tnpy.plantvehiclems.model.mysql.TbSoftwareInfo;

public interface TbSoftwareInfoMapper {

	int deleteByPrimaryKey(String id);

    int insert(TbSoftwareInfo record);

    int insertSelective(TbSoftwareInfo record);

    TbSoftwareInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbSoftwareInfo record);

    int updateByPrimaryKey(TbSoftwareInfo record);
}