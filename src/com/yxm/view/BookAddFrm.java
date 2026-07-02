package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.model.Book;
import com.yxm.model.BookType;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class BookAddFrm extends JFrame {

	private JTextField bookNameTxt;
	private JTextField authorTxt;
	private JTextField PressTxt;
	private JLabel label_3;
	private JLabel label_4;
	private JTextArea bookDescTxt;
	private JTextField priceInTxt;
	private JComboBox bookTypeJcb;
	private JComboBox discountJcb;
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	private JTextField priceOutTxt;
	private JTextField stockNumberTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookAddFrm frame = new BookAddFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookAddFrm() {
		setFocusable(true);
		setTitle("图书添加");
		setBounds(100, 100, 450, 425);
		
		JLabel lblNewLabel = new JLabel("图书名称：");
		lblNewLabel.setBounds(36, 38, 66, 15);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setBounds(106, 35, 108, 21);
		bookNameTxt.setColumns(10);
		
		JLabel label = new JLabel("图书作者：");
		label.setBounds(224, 38, 68, 15);
		
		authorTxt = new JTextField();
		authorTxt.setBounds(296, 35, 86, 21);
		authorTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("出版社：");
		label_1.setBounds(36, 85, 66, 15);
		
		PressTxt = new JTextField();
		PressTxt.setBounds(106, 82, 108, 21);
		PressTxt.setColumns(10);
		
		JLabel label_2 = new JLabel("图书进价：");
		label_2.setBounds(224, 85, 68, 15);
		
		label_3 = new JLabel("图书数量：");
		label_3.setBounds(36, 133, 66, 15);
		
		label_4 = new JLabel("图书类别：");
		label_4.setBounds(36, 172, 66, 15);
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setBounds(106, 206, 256, 126);
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setBounds(106, 169, 108, 21);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.setBounds(145, 355, 66, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setBounds(248, 355, 66, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		priceInTxt = new JTextField();
		priceInTxt.setBounds(296, 82, 66, 21);
		priceInTxt.setColumns(10);
		
		//设置文本域边框
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185),1,false));
		getContentPane().setLayout(null);
		getContentPane().add(label_4);
		getContentPane().add(bookDescTxt);
		getContentPane().add(lblNewLabel);
		getContentPane().add(bookNameTxt);
		getContentPane().add(label_3);
		getContentPane().add(label_1);
		getContentPane().add(PressTxt);
		getContentPane().add(bookTypeJcb);
		getContentPane().add(label);
		getContentPane().add(authorTxt);
		getContentPane().add(label_2);
		getContentPane().add(priceInTxt);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnNewButton_1);
		
		JLabel label_5 = new JLabel("图书售价：");
		label_5.setBounds(224, 133, 68, 15);
		getContentPane().add(label_5);
		
		priceOutTxt = new JTextField();
		priceOutTxt.setColumns(10);
		priceOutTxt.setBounds(296, 130, 66, 21);
		getContentPane().add(priceOutTxt);
		
		JLabel label_6 = new JLabel("图书折扣：");
		label_6.setBounds(224, 172, 68, 15);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("图书备注：");
		label_7.setBounds(36, 209, 66, 15);
		getContentPane().add(label_7);
		
		stockNumberTxt = new JTextField();
		stockNumberTxt.setColumns(10);
		stockNumberTxt.setBounds(106, 130, 108, 21);
		getContentPane().add(stockNumberTxt);
		
		discountJcb = new JComboBox();
		discountJcb.setBounds(296, 169, 86, 21);
		getContentPane().add(discountJcb);
		
		fillBookType();
		fillDiscount();

	}
	
	/**
	 * 重置事件处理
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}

	/**
	 * 图书添加事件处理
	 * @param evt
	 */
	private void bookAddActionPerformed(ActionEvent evt) {

		String bookName = this.bookNameTxt.getText();
		String author = this.authorTxt.getText();
		String Press = this.PressTxt.getText();
		String priceIn = this.priceInTxt.getText();
		String priceOut = this.priceOutTxt.getText();
		String stockNumber = this.stockNumberTxt.getText();
		String bookDesc = this.bookDescTxt.getText();
		if(StringUtil.isEmptry(bookName)){
			JOptionPane.showMessageDialog(null, "图书名称不能为空格！");
			return;
		}
		if(StringUtil.isEmptry(author)){
			JOptionPane.showMessageDialog(null, "图书作者不能为空格！");
			return;
		}
		if(StringUtil.isEmptry(Press)){
			JOptionPane.showMessageDialog(null, "图书出版社不能为空格！");
			return;
		}
		if(StringUtil.isEmptry(priceIn)){
			JOptionPane.showMessageDialog(null, "图书进价不能为空格！");
			return;
		}if(StringUtil.isEmptry(priceOut)){
			JOptionPane.showMessageDialog(null, "图书售价不能为空格！");
			return;
		}if(StringUtil.isEmptry(stockNumber)){
			JOptionPane.showMessageDialog(null, "图书数量不能为空格！");
			return;
		}
		
		BookType bookType = (BookType)bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		
		int discount = (Integer)discountJcb.getSelectedItem();
		
		Book book = new Book(bookName, author,Press, Float.parseFloat(priceIn),
				Float.parseFloat(priceOut),Integer.parseInt(stockNumber),
				discount,bookTypeId,bookDesc);
		
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			int addNum = bookDao.add(conn, book);
			if(addNum == 1){
				JOptionPane.showMessageDialog(null, "图书添加成功！");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "图书添加失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书添加失败！");
		}finally{
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重置表单
	 */
	private void resetValue() {
		this.bookNameTxt.setText("");
		this.authorTxt.setText("");
		this.PressTxt.setText("");
		this.priceInTxt.setText("");
		this.priceOutTxt.setText("");
		this.stockNumberTxt.setText("");
		this.bookDescTxt.setText("");
		if(this.discountJcb.getItemCount() > 0){
			this.discountJcb.setSelectedIndex(0);
		}
		if(this.bookTypeJcb.getItemCount() > 0){
			this.bookTypeJcb.setSelectedIndex(0);
		}
	}

	/**
	 * 初始化图书类别下拉框
	 */
	private void fillBookType(){
		Connection conn = null;
		BookType bookType = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn,new BookType());
			while(rs.next()){
				bookType = new BookType();
				bookType.setId(rs.getInt("id"));
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				this.bookTypeJcb.addItem(bookType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	private void fillDiscount(){
		for(int i = 10;i > 0;i --)
			this.discountJcb.addItem(i);
	} 

}
