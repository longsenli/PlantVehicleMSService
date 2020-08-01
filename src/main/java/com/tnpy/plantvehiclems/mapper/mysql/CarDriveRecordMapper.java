package com.tnpy.plantvehiclems.mapper.mysql;

import com.tnpy.plantvehiclems.model.mysql.CarDriveRecord;
import com.tnpy.plantvehiclems.model.mysql.TbCarInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CarDriveRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(CarDriveRecord record);

    int insertSelective(CarDriveRecord record);

    CarDriveRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CarDriveRecord record);

    int updateByPrimaryKey(CarDriveRecord record);

    @Select( "select carID,driverName, carType, department, driverPhone, comeTime,comeRecorder,goTime,goRecorder from (\n" +
            "SELECT carID,date_format(comeTime,'%Y-%m-%d %H:%i:%s') as comeTime,comeRecorder,date_format(goTime,'%Y-%m-%d %H:%i:%s') as goTime ,goRecorder\n" +
            " FROM tb_cardriverecord where  status = '1'   \n" +
            ") a left join tb_carinfo b on a.carID = b.carLicence order by carID, comeTime ")
    List<Map<Object, Object>>  getCarStayInPlant( );

    @Select( "select carID,driverName, carType, department, driverPhone, comeTime,comeRecorder,goTime,goRecorder from (\n" +
            "SELECT carID,date_format(comeTime,'%Y-%m-%d %H:%i:%s') as comeTime,comeRecorder,date_format(goTime,'%Y-%m-%d %H:%i:%s') as goTime ,goRecorder\n" +
            " FROM tb_cardriverecord where  status != '-1'  ${carFilter} and comeTime >= #{startTime}  and comeTime <= #{endTime}\n" +
            ") a left join tb_carinfo b on a.carID = b.carLicence order by goTime desc  ")
    List<Map<Object, Object>>  selectRecordByFilter(@Param("carFilter") String id,String startTime,String endTime );

    @Select(" SELECT id,date_format(comeTime,'%Y-%m-%d %H:%i:%s') as comeTime  FROM tb_cardriverecord where carID = #{id}  and comeTime >=  #{startTime} ")
    List<Map<Object, Object>>  judgeRepetitionComeRecord(String id,String startTime  );


    @Select(" SELECT *  FROM tb_carinfo where carLicence = #{carID}  and status != '-1' ")
    List<Map<Object, Object>>  judgeCarIDExist(String carID );

    @Update(" update tb_cardriverecord set status = '3',goTime = now()  where carID = #{carID} and goTime is null ")
   int updateCarInOutStats(String carID );

    @Select(" SELECT id,date_format(comeTime,'%Y-%m-%d %H:%i:%s') as comeTime,date_format(goTime,'%Y-%m-%d %H:%i:%s') as goTime  " +
            " FROM tb_cardriverecord where carID = #{id}  order by comeTime desc limit 1  ")
    List<Map<Object, Object>>  getLatestComeRecord(String id   );
}