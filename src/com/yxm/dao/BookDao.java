package com.yxm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yxm.model.Book;
import com.yxm.util.StringUtil;

/**
 * 图书Dao类
 *
 */
public class BookDao {
	
	/**
	 * 图书添加
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,Book book)throws Exception{
		String sql = "insert into t_book values(null,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPress());
		pstmt.setFloat(4, book.getPriceIn());
		pstmt.setFloat(5, book.getPriceOut());
		pstmt.setInt(6, book.getStockNumber());
		pstmt.setFloat(7, book.getDiscount());
		pstmt.setString(8, book.getBookDesc());
		pstmt.setInt(9, book.getBookTypeId());
		return pstmt.executeUpdate();
	}
	public Book getBookById(Connection conn, int bookId) throws Exception {
		String sql = "select * from t_book where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bookId);
		ResultSet rs = pstmt.executeQuery();

		if(rs.next()) {
			return new Book(
					rs.getInt("id"),
					rs.getString("bookName"),
					rs.getString("author"),
					rs.getString("Press"),
					rs.getFloat("priceIn"),
					rs.getFloat("priceOut"),
					rs.getInt("stockNumber"),
					rs.getInt("discount"),
					rs.getInt("bookTypeId"),
					rs.getString("bookDesc")
			);
		}
		return null;
	}
	/**
	 * 图书信息查询(图书表+图书类别表 多表查询）
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection conn,Book book)throws Exception{
		
		StringBuffer sb = new StringBuffer("select * from t_book b,t_bookType bt "
				+ "where b.bookTypeId = bt.id");
		if(StringUtil.isNotEmpty(book.getBookName())){
			sb.append(" and b.bookName like '%"+book.getBookName()+"%'");
		}
		if(StringUtil.isNotEmpty(book.getAuthor())){
			sb.append(" and b.author like '%"+book.getAuthor()+"%'");
		}
		if(book.getBookTypeId() != null && book.getBookTypeId() != -1){
			sb.append(" and b.bookTypeId ="+book.getBookTypeId());
		}
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 图书信息查询(单表 图书表）
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public ResultSet listt(Connection conn,Book book)throws Exception{
		
		StringBuffer sb = new StringBuffer("select * from t_book where bookName like '%"+book.getBookName()+"%'");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	/**
	 * 图书信息删除
	 * @param conn
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection conn,String id)throws Exception{
		String sql = "delete from t_book where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();		
	}

	/**
	 * 图书信息修改
	 * @param conn
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public int update(Connection conn,Book book)throws Exception{
		String sql = "update t_book set bookName=?,author=?,Press=?,priceIn=?,"
				+ "priceOut=?,stockNumber=?,discount=?,bookDesc=?,bookTypeId=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, book.getBookName());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getPress());
		pstmt.setFloat(4, book.getPriceIn());
		pstmt.setFloat(5, book.getPriceOut());
		pstmt.setInt(6, book.getStockNumber());
		pstmt.setInt(7, book.getDiscount());
		pstmt.setString(8, book.getBookDesc());
		pstmt.setInt(9, book.getBookTypeId());
		pstmt.setInt(10, book.getId());
		System.out.println(pstmt.toString());
		return pstmt.executeUpdate();
	}
	
	/**
	 * 指定图书类别下是否存在图书
	 * @param conn
	 * @param bookTypeId
	 * @return
	 * @throws Exception
	 */
	public boolean existBookByBookTypeId(Connection conn,String bookTypeId)throws Exception{
		String sql = "select * from t_book where bookTypeId =?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bookTypeId);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}
}
