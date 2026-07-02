package com.yxm.view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.model.Book;
import com.yxm.util.DbUtil;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QueryAllFrm extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	private JTextField bookNameTxt;
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
					QueryAllFrm frame = new QueryAllFrm();
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
	public QueryAllFrm() {
		setTitle("图书查询");
		setFocusable(true);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 70, 754, 329);
		
		JLabel label = new JLabel("图书名称");
		label.setBounds(15, 31, 65, 15);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setBounds(90, 28, 108, 21);
		bookNameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.setBounds(226, 27, 80, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		
		label_1 = new JLabel("共");
		label_1.setBounds(607, 420, 24, 15);
		
		noteNumber = new JTextField();
		noteNumber.setBounds(629, 417, 66, 21);
		noteNumber.setColumns(10);
		
		label_2 = new JLabel("条记录");
		label_2.setBounds(714, 420, 55, 15);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u4F5C\u8005", "\u51FA\u7248\u793E", "\u8FDB\u4EF7", "\u552E\u4EF7", "\u6570\u91CF", "\u6298\u6263", "\u56FE\u4E66\u5907\u6CE8", "\u56FE\u4E66\u7C7B\u522B"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(3).setPreferredWidth(137);
		table.getColumnModel().getColumn(4).setPreferredWidth(59);
		table.getColumnModel().getColumn(5).setPreferredWidth(58);
		table.getColumnModel().getColumn(6).setPreferredWidth(57);
		table.getColumnModel().getColumn(7).setPreferredWidth(58);
		table.getColumnModel().getColumn(8).setPreferredWidth(143);
		table.getColumnModel().getColumn(9).setPreferredWidth(67);
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(bookNameTxt);
		contentPane.add(button);
		contentPane.add(label_1);
		contentPane.add(noteNumber);
		contentPane.add(label_2);
		contentPane.add(scrollPane);
		
		this.fillTable(new Book());
		
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
				v.add(rs.getString("id"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("author"));
				v.add(rs.getString("Press"));
				v.add(rs.getFloat("priceIn"));
				v.add(rs.getFloat("priceOut"));
				v.add(rs.getInt("stockNumber"));
				v.add(rs.getInt("discount"));
				v.add(rs.getString("bookDesc"));
				v.add(rs.getString("bookTypeId"));
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
		String bookName = this.bookNameTxt.getText();
		
		Book book = new Book(bookName);
		this.fillTable(book);
	}
	
}
