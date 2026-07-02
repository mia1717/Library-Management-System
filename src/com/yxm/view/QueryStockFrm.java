package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.model.Book;
import com.yxm.util.DbUtil;
import java.awt.event.ActionListener;

public class QueryStockFrm extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTxt;
	private JTable table;
	
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	private JTextField noteNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QueryStockFrm frame = new QueryStockFrm();
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
	public QueryStockFrm() {
		setTitle("库存查询");
		setFocusable(true);
		setBounds(100, 100, 360, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("图书名称");
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_1 = new JLabel("共");
		
		noteNumber = new JTextField();
		noteNumber.setColumns(10);
		
		JLabel label_2 = new JLabel("条记录");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
							.addComponent(button)))
					.addGap(43))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(174, Short.MAX_VALUE)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(noteNumber, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_2)
					.addGap(42))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addGap(20)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(noteNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u56FE\u4E66\u7F16\u53F7", "\u56FE\u4E66\u540D\u79F0", "\u56FE\u4E66\u6570\u91CF"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
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
				v.add(rs.getInt("stockNumber"));
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
