package com.yxm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yxm.model.Press;
import com.yxm.util.StringUtil;

/**
 * 出版社Dao类
 */
public class PressDao {

    /**
     * 添加出版社
     *
     * @param conn 数据库连接
     * @param press 出版社对象
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    public int add(Connection conn, Press press) throws Exception {
        String sql = "INSERT INTO t_press (pressName, pressAddress, pressPhone) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, press.getPressName());
        pstmt.setString(2, press.getPressAddress());
        pstmt.setString(3, press.getPressPhone());
        return pstmt.executeUpdate();
    }

    /**
     * 查询出版社列表
     *
     * @param conn 数据库连接
     * @param press 出版社对象（用于条件查询）
     * @return 查询结果集
     * @throws Exception 数据库操作异常
     */
    public ResultSet list(Connection conn, Press press) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT * FROM t_press WHERE 1=1");
        if (StringUtil.isNotEmpty(press.getPressName())) {
            sb.append(" AND pressName LIKE '%" + press.getPressName() + "%'");
        }
        if (StringUtil.isNotEmpty(press.getPressAddress())) {
            sb.append(" AND pressAddress LIKE '%" + press.getPressAddress() + "%'");
        }
        if (StringUtil.isNotEmpty(press.getPressPhone())) {
            sb.append(" AND pressPhone LIKE '%" + press.getPressPhone() + "%'");
        }
        PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    /**
     * 根据ID查询出版社
     *
     * @param conn 数据库连接
     * @param pressId 出版社ID
     * @return 出版社对象
     * @throws Exception 数据库操作异常
     */
    public Press getById(Connection conn, int pressId) throws Exception {
        String sql = "SELECT * FROM t_press WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pressId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Press(
                    rs.getInt("id"),
                    rs.getString("pressName"),
                    rs.getString("pressAddress"),
                    rs.getString("pressPhone")
            );
        }
        return null;
    }

    /**
     * 删除出版社
     *
     * @param conn 数据库连接
     * @param id 出版社ID
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    public int delete(Connection conn, String id) throws Exception {
        String sql = "DELETE FROM t_press WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /**
     * 更新出版社信息
     *
     * @param conn 数据库连接
     * @param press 出版社对象
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    public int update(Connection conn, Press press) throws Exception {
        String sql = "UPDATE t_press SET pressName = ?, pressAddress = ?, pressPhone = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, press.getPressName());
        pstmt.setString(2, press.getPressAddress());
        pstmt.setString(3, press.getPressPhone());
        pstmt.setInt(4, press.getId());
        return pstmt.executeUpdate();
    }
}