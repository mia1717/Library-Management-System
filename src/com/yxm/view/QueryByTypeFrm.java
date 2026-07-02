package com.yxm.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.model.Book;
import com.yxm.model.BookType;
import com.yxm.util.DbUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class QueryByTypeFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	
	private JComboBox s_bookTypeJcb;
	private JLabel label_1;
	private JTextField noteNumber;
	private JLabel label_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryByTypeFrm frame = new QueryByTypeFrm();
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
	public QueryByTypeFrm() {
		setTitle("分类查询");
		setFocusable(true);
		setBounds(100, 100, 360, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("图书分类");
		
		s_bookTypeJcb = new JComboBox();
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		label_1 = new JLabel("共");
		
		noteNumber = new JTextField();
		noteNumber.setColumns(10);
		
		label_2 = new JLabel("条记录");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addComponent(label)
							.addGap(20)
							.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(button))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(47)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(45, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(182, Short.MAX_VALUE)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(noteNumber, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2)
					.addGap(45))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(26)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(button)))
					.addGap(16)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(noteNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u56FE\u4E66\u7C7B\u522B", "\u56FE\u4E66\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		this.fillBookType();
		this.fillTable(new Book());
		
	}
	
	/**
	 * 初始化图书类型下拉框
	 * @param type：下拉框类型
	 */
	private void fillBookType(){
		Connection conn = null;
		BookType bookType = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn, new BookType());
			while(rs.next()){
				bookType = new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				this.s_bookTypeJcb.addItem(bookType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 初始化表格数据
	 * @param book
	 */
	private void fillTable(Book book){
		int count = 0;
		DefaultTableModel dtm =  (DefaultTableModel)table.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = bookDao.list(conn, book);
			while(rs.next()){
				count ++;
				Vector v = new Vector();
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookName"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		noteNumber.setText(String.valueOf(count));
	}
	
	/**
	 * 图书查询事件处理
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {	
		BookType bookType = (BookType)this.s_bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		Book book = new Book(bookTypeId);
		this.fillTable(book);
	}
	
}
