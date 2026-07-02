package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.yxm.dao.BookTypeDao;
import com.yxm.model.BookType;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class BookTypeAddFrm extends JFrame {

	private JTextField bookTypeNameTxt;
	private JTextArea bookTypeDescTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeAddFrm frame = new BookTypeAddFrm();
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
	public BookTypeAddFrm() {
		setFocusable(true);
		setTitle("图书类别添加");
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("图书类别名称");
		
		JLabel lblNewLabel_1 = new JLabel("图书类别备注");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
							.addComponent(btnNewButton_1)
							.addGap(117))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(bookTypeDescTxt, Alignment.LEADING)
								.addComponent(bookTypeNameTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
							.addContainerGap(90, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(50, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addGap(26))
		);
		getContentPane().setLayout(groupLayout);
		
		//设置文本域边框
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185),1,false));

	}
	
	/**
	 * 图书类别事件添加处理
	 * @param evt
	 */
	private void bookTypeAddActionPerformed(ActionEvent evt) {
		String bookTypeName = this.bookTypeNameTxt.getText();
		String bookTypeDesc = this.bookTypeDescTxt.getText();
		if(StringUtil.isEmptry(bookTypeName)){
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空格！");
			return;
		}
		BookType bookType = new BookType(bookTypeName, bookTypeDesc);
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			int n = bookTypeDao.add(conn, bookType);
			if(n == 1){
				JOptionPane.showMessageDialog(null, "图书添加成功!");
				resetValue();
			}else{
				JOptionPane.showMessageDialog(null, "图书添加失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书添加失败!");
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
	 * 重置事件处理
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.resetValue();
	}

	/**
	 * 重置表单
	 */
	private void resetValue(){
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
}
