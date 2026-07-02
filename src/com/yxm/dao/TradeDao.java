package com.yxm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.yxm.model.SaleOrder;
import com.yxm.model.Trade;

public class TradeDao {
	// 添加新方法用于添加订单和交易记录
	public int addOrderWithTrades(Connection conn, SaleOrder order, List<Trade> trades) throws Exception {
		PreparedStatement orderPstmt = null;
		PreparedStatement tradePstmt = null;
		ResultSet rs = null;

		try {
			// 1. 添加订单
			String orderSql = "insert into t_sale_order(order_no, total_amount, total_profit, sale_date, discount_type, remark) values(?,?,?,?,?,?)";
			orderPstmt = conn.prepareStatement(orderSql, PreparedStatement.RETURN_GENERATED_KEYS);
			orderPstmt.setString(1, order.getOrderNo());
			orderPstmt.setFloat(2, order.getTotalAmount());
			orderPstmt.setFloat(3, order.getTotalProfit());
			orderPstmt.setDate(4, new java.sql.Date(order.getSaleDate().getTime()));
			orderPstmt.setInt(5, order.getDiscountType());
			orderPstmt.setString(6, order.getRemark());
			orderPstmt.executeUpdate();

			// 获取生成的订单ID
			int orderId = 0;
			rs = orderPstmt.getGeneratedKeys();
			if(rs.next()) {
				orderId = rs.getInt(1);
			}

			// 2. 添加交易明细
			String tradeSql = "insert into t_trade(order_id, bookId, discount, priceIn, realSalePrice, saleNumber, profit, saleDate) values(?,?,?,?,?,?,?,?)";
			tradePstmt = conn.prepareStatement(tradeSql);

			for(Trade trade : trades) {
				tradePstmt.setInt(1, orderId);
				tradePstmt.setInt(2, trade.getBookId());
				tradePstmt.setInt(3, trade.getDiscount());
				tradePstmt.setFloat(4, trade.getPriceIn());
				tradePstmt.setFloat(5, trade.getRealSalePrice());
				tradePstmt.setInt(6, trade.getSaleNumber());
				tradePstmt.setFloat(7, trade.getProfit());
				tradePstmt.setDate(8, new java.sql.Date(trade.getSaleDate().getTime()));
				tradePstmt.addBatch();
			}

			int[] batchResult = tradePstmt.executeBatch();
			return 1;

		} finally {
			// 确保资源被释放
			if(rs != null) try { rs.close(); } catch(SQLException e) {}
			if(orderPstmt != null) try { orderPstmt.close(); } catch(SQLException e) {}
			if(tradePstmt != null) try { tradePstmt.close(); } catch(SQLException e) {}
		}
	}
	/**
	 * 查询订单及其明细
	 * @param conn
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public ResultSet listOrdersWithDetails(Connection conn, Date startDate, Date endDate) throws Exception {
		String sql = "SELECT o.id as orderId, o.order_no, o.total_amount, o.total_profit, " +
				"o.sale_date, o.discount_type, o.remark, " +
				"t.id as tradeId, t.bookId, t.priceIn, t.realSalePrice, " +
				"t.saleNumber, t.profit " +
				"FROM t_sale_order o " +
				"LEFT JOIN t_trade t ON o.id = t.order_id " +
				"WHERE o.sale_date BETWEEN ? AND ? " +
				"ORDER BY o.sale_date DESC, o.id, t.id";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setDate(1, new java.sql.Date(startDate.getTime()));
		pstmt.setDate(2, new java.sql.Date(endDate.getTime()));
		return pstmt.executeQuery();
	}
	/**
	 * 销售信息添加
	 * @param conn
	 * @param trade
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,Trade trade)throws Exception{
		String sql = "insert into t_trade values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, trade.getBookId());
		pstmt.setFloat(2, trade.getPriceIn());
		pstmt.setFloat(3, trade.getRealSalePrice());
		pstmt.setInt(4,trade.getSaleNumber());
		pstmt.setFloat(5, trade.getProfit());
		pstmt.setDate(6,(java.sql.Date) trade.getSaleDate());
		System.out.println(pstmt.toString());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 销售信息查询
	 * @param conn
	 * @param trade
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection conn,Trade trade)throws Exception{	
		StringBuffer sb = new StringBuffer("select * from t_trade");
		if(trade.getSaleDate() != null){
			sb.append(" where saleDate like '%"+trade.getSaleDate()+"%'");
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		//System.out.println(sb.toString());
		return pstmt.executeQuery();
	}
	
	
	/**
	 *销售信息删除
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection conn,String id)throws Exception{
		String sql = "delete from t_trade where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();		
	}

}
