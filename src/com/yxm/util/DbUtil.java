package com.yxm.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库工具类
 *
 */

public class DbUtil {
	
	private String dbUrl="jdbc:mysql://localhost:3306/db_book1?useSSL=false&serverTimezone=UTC";
	private String dbUserName="root";//用户名12
	private String dbPassword="root";//密码
	private String jdbcName="com.mysql.cj.jdbc.Driver";//驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getConn() throws Exception{
		Class.forName(jdbcName);
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @throws Exception
	 */
	public void closeConn(Connection conn)throws Exception{
		if(conn != null){
			conn.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getConn();
			System.out.println("数据库连接成功!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
		
	}
}
