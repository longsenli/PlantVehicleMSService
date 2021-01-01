package com.tnpy.plantvehiclems.service.ZXNHJCService;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: LLS
 * @Date: 2020-12-27 9:32
 */
public class DBOperator {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.82.248:3306/bootdo";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root";

    static Connection conn = null;

    public Connection getMysqlConnection() {
        try {
            if (conn == null) {
                // 注册 JDBC 驱动
              //  Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            if (conn.isClosed()) {
                // 注册 JDBC 驱动
                //  Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            return conn;
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " getMysqlConnection Error");
            return null;
        }
    }

    public static List<Map<Object, Object>> selectData(String sqlString) {
        try {
            if (conn == null) {
                // 注册 JDBC 驱动
              //  Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }

            if (conn.isClosed()) {
                // 注册 JDBC 驱动
                //  Class.forName(JDBC_DRIVER);
                System.out.println("close");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);
            ResultSetMetaData data = rs.getMetaData();
            List<Map<Object, Object>> MapList = new ArrayList<>();
            while (rs.next()) {
                HashMap<Object, Object> map = new HashMap<Object, Object>();
                for (int i = 1; i <= data.getColumnCount(); i++) {// 数据库里从 1 开始

                    map.put(data.getColumnName(i), rs.getString(data.getColumnName(i)));
                }
                MapList.add(map);
            }
            return MapList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage()  + " selectData Error");
            return new ArrayList<>();
        }
    }

    public static int executeSingleSQL(String sqlString) {
        try {
            if (conn == null) {
                // 注册 JDBC 驱动
               // Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }

            if (conn.isClosed()) {
                // 注册 JDBC 驱动
                //  Class.forName(JDBC_DRIVER);
                System.out.println("close");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            Statement stmt = conn.createStatement();
            int i = stmt.executeUpdate(sqlString);
            return i;
        } catch (Exception ex) {
            System.out.println(ex.getMessage()  + " executeSingle Error");
            return 0;
        }

    }

    public static int executeBatchSQL(List<String> sqlStringList) {
        try {
            if (conn == null) {
                // 注册 JDBC 驱动
                // Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }

            if (conn.isClosed()) {
                // 注册 JDBC 驱动
                //  Class.forName(JDBC_DRIVER);
                System.out.println("close");
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            Statement stmt = conn.createStatement();

            for(int i =0;i< sqlStringList.size();i++)
            {
                stmt.addBatch(sqlStringList.get(i));

            }


            int[] count =  stmt.executeBatch();
            int numberSum = 0;
            for(int i = 0;i<count.length;i++){
                numberSum += count[i];
            }
            return numberSum;
        } catch (Exception ex) {
            System.out.println(ex.getMessage()  + " excuteBatch Error");
            return 0;
        }

    }
//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//        try{
//            // 注册 JDBC 驱动
//            Class.forName(JDBC_DRIVER);
//
//            // 打开链接
//            System.out.println("连接数据库...");
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//            // 执行查询
//            System.out.println(" 实例化Statement对象...");
//            stmt = conn.createStatement();
//            String sql;
//            sql = "SELECT dno, smsg FROM d_db_days";
//            ResultSet rs = stmt.executeQuery(sql);
//
//            // 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
//                String dno  = rs.getString("dno");
//                String smsg = rs.getString("smsg");
//
//                // 输出数据
//                System.out.print("ID: " + dno);
//                System.out.print(", 站点名称: " + smsg);
//
//                System.out.print("\n");
//            }
//
//
//            Statement st=conn.createStatement();
//
//            String sql="drop table test1a  ";
//            int i=st.executeUpdate(sql);
//            if(i==0){
//                System.out.println("成功删除了表！");
//            }
//
//            /*String sql="create table test1a(id int(4),name varchar(20))";
//            int i=st.executeUpdate(sql);
//            if(i==0){
//                System.out.println("成功创建了表！");
//            }*/
//
//
//            /*String sql="delete from classmate where id=200 or id=16 ";
//            int i=st.executeUpdate(sql);
//            if(i>0){
//                System.out.println("成功删除了"+i+"条记录");
//            }
//            */
//
//            // 完成后关闭
//            rs.close();
//            stmt.close();
//            conn.close();
//        }catch(SQLException se){
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        }catch(Exception e){
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        }finally{
//            // 关闭资源
//            try{
//                if(stmt!=null) stmt.close();
//            }catch(SQLException se2){
//            }// 什么都不做
//            try{
//                if(conn!=null) conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Goodbye!");
//
//
//    }
}
