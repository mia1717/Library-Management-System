package com.yxm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yxm.model.Warehouse;
import com.yxm.util.StringUtil;

/**
 * 仓库Dao类
 */
public class WarehouseDao {

    /**
     * 添加仓库
     *
     * @param conn 数据库连接
     * @param warehouse 仓库对象
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    public int add(Connection conn, Warehouse warehouse) throws Exception {
        String sql = "INSERT INTO t_warehouse (warehouseName, warehouseLocation, warehouseCapacity, currentStock) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, warehouse.getWarehouseName());
        pstmt.setString(2, warehouse.getWarehouseLocation());
        pstmt.setInt(3, warehouse.getWarehouseCapacity());
        pstmt.setInt(4, warehouse.getCurrentStock());
        return pstmt.executeUpdate();
    }

    /**
     * 查询仓库列表
     *
     * @param conn 数据库连接
     * @param warehouse 仓库对象（用于条件查询）
     * @return 查询结果集
     * @throws Exception 数据库操作异常
     */
    public ResultSet list(Connection conn, Warehouse warehouse) throws Exception {
        StringBuilder sb = new StringBuilder("SELECT * FROM t_warehouse WHERE 1=1");
        if (StringUtil.isNotEmpty(warehouse.getWarehouseName())) {
            sb.append(" AND warehouseName LIKE '%" + warehouse.getWarehouseName() + "%'");
        }
        if (StringUtil.isNotEmpty(warehouse.getWarehouseLocation())) {
            sb.append(" AND warehouseLocation LIKE '%" + warehouse.getWarehouseLocation() + "%'");
        }
        PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    /**
     * 根据ID查询仓库
     *
     * @param conn 数据库连接
     * @param warehouseId 仓库ID
     * @return 仓库对象
     * @throws Exception 数据库操作异常
     */
    public Warehouse getById(Connection conn, int warehouseId) throws Exception {
        String sql = "SELECT * FROM t_warehouse WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, warehouseId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Warehouse(
                    rs.getInt("id"),
                    rs.getString("warehouseName"),
                    rs.getString("warehouseLocation"),
                    rs.getInt("warehouseCapacity"),
                    rs.getInt("currentStock")
            );
        }
        return null;
    }

    /**
     * 删除仓库
     *
     * @param conn 数据库连接
     * @param id 仓库ID
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    public int delete(Connection conn, String id) throws Exception {
        String sql = "DELETE FROM t_warehouse WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /**
     * 更新仓库信息
     *
     * @param conn 数据库连接
     * @param warehouse 仓库对象
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    public int update(Connection conn, Warehouse warehouse) throws Exception {
        String sql = "UPDATE t_warehouse SET warehouseName = ?, warehouseLocation = ?, warehouseCapacity = ?, currentStock = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, warehouse.getWarehouseName());
        pstmt.setString(2, warehouse.getWarehouseLocation());
        pstmt.setInt(3, warehouse.getWarehouseCapacity());
        pstmt.setInt(4, warehouse.getCurrentStock());
        pstmt.setInt(5, warehouse.getId());
        return pstmt.executeUpdate();
    }
}