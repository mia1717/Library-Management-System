package com.yxm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yxm.model.Manager;

/**
 * 管理员Dao类
 *
 */
public class ManagerDao {
	
	/**
	 * 登录验证
	 * @param conn
	 * @param manager
	 * @return
	 * @throws Exception
	 */
	public Manager login(Connection conn,Manager manager)throws Exception{
		Manager resultManager = null;
		String sql = "select * from t_manager where managerName = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, manager.getManagerName());
		pstmt.setString(2, manager.getManagerPassword());
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			resultManager = new Manager();
			resultManager.setId(rs.getInt("id"));
			resultManager.setManagerName(rs.getString("managerName"));
			resultManager.setManagerPassword(rs.getString("password"));
		}
		return resultManager;
	}
}
