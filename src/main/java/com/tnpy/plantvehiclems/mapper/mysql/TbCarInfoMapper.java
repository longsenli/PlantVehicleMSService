package com.tnpy.plantvehiclems.mapper.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;
import org.apache.ibatis.annotations.Update;

public interface TbCarInfoMapper {
    int deleteByPrimaryKey(String carlicence);

    int insert(TbCarInfo record);

    int insertSelective(TbCarInfo record);

    TbCarInfo selectByPrimaryKey(String carlicence);

    int updateByPrimaryKeySelective(TbCarInfo record);

    int updateByPrimaryKey(TbCarInfo record);
    @Select("select * from tb_carinfo where status != '-1'")
    List<TbCarInfo> listAll();
    @Update("update tb_carinfo set status = '-1' where carLicence = #{carlicence}")
    int deleteByChangeStatus(String carlicence);
}