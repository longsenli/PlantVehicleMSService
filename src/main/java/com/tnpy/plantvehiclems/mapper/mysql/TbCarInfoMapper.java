package com.tnpy.plantvehiclems.mapper.mysql;

import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface TbCarInfoMapper {
    int deleteByPrimaryKey(String carlicence);

    int insert(TbCarInfo record);

    int insertSelective(TbCarInfo record);

    TbCarInfo selectByPrimaryKey(String carlicence);

    int updateByPrimaryKeySelective(TbCarInfo record);

    int updateByPrimaryKey(TbCarInfo record);


    //    @Select("SELECT a.carLicence,a.carType,a.carColor,a.driverName,a.driverPhone,a.emergencyContact,a.emergencyPhone,a.registrationTime,a.remark," +
//            " case when  b.endTime  is null then '未办通行证' else '已办通行证' end as status FROM tb_carinfo a left join " +
//            "( select carLicence as carLicencePasser,endTime  from  tb_laissezpasserinfo where status != '-1' and endTime > Now()) b " +
//            " on a.carLicence = b.carLicencePasser  where a.status != '-1'  ${filter} order by registrationTime desc limit 1000")
    @Select("SELECT carLicence, carType, carColor, driverName, driverPhone, emergencyContact, emergencyPhone, \n" +
            "  date_format(registrationTime,'%Y-%m-%d %H:%i:%s') as registrationTime  , status, registerName,remark, identityID, department, job FROM tb_carinfo    where  status != '-1'  ${filter}   limit 1000")
    List<TbCarInfo> listAll(@Param("filter") String filter);

    @Update("update tb_carinfo set carLicence = concat(carLicence,date_format(now(),'-%Y%m%d%H%i%s') ) ,status = '-1' where carLicence=#{id} ")
    int deleteByChangeStatus(String carlicence);

    @Select("SELECT carLicence, carType, carColor, driverName, driverPhone, emergencyContact, emergencyPhone, \n" +
            "  date_format(registrationTime,'%Y-%m-%d %H:%i:%s') as registrationTime  , status, registerName,remark, identityID, department, job FROM tb_carinfo    where  status != '-1'  ${filter}   limit 1000")
    List<Map<Object, Object>> getCarInfoList(@Param("filter") String filter);

    @Select(" select version from tb_softwareinfo where status ='1' order by updateTime desc limit 1 ")
    String selectCurrentVersion();
}