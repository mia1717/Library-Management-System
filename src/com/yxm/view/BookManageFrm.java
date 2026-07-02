package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.model.Book;
import com.yxm.model.BookType;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class BookManageFrm extends JFrame {
	private JTextField s_bookNameTxt;
	private JTextField s_authorTxt;
	private JComboBox s_bookTypeJcb;
	private JComboBox discountJcb;
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	private JTextField idTxt;
	private JTextField bookNameTxt;
	private JTextField PressTxt;
	private JTextField priceInTxt;
	private JTextField priceOutTxt;
	private JTextArea bookDescTxt;
	private JComboBox bookTypeJcb;
	private JTable bookTable;
	private JTextField authorTxt;
	private JTextField stockNumberTxt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookManageFrm frame = new BookManageFrm();
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
	public BookManageFrm() {
		setFocusable(true);
		setTitle("图书管理");
		setBounds(100, 100, 800, 620);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 764, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		
		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTableMousePressed(e);
			}
		});
		bookTable.setModel(new DefaultTableModel(
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
		bookTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		bookTable.getColumnModel().getColumn(1).setPreferredWidth(69);
		bookTable.getColumnModel().getColumn(2).setPreferredWidth(46);
		bookTable.getColumnModel().getColumn(3).setPreferredWidth(79);
		bookTable.getColumnModel().getColumn(4).setPreferredWidth(47);
		bookTable.getColumnModel().getColumn(5).setPreferredWidth(44);
		bookTable.getColumnModel().getColumn(6).setPreferredWidth(40);
		bookTable.getColumnModel().getColumn(7).setPreferredWidth(43);
		bookTable.getColumnModel().getColumn(8).setPreferredWidth(120);
		bookTable.getColumnModel().getColumn(9).setPreferredWidth(62);
		scrollPane.setViewportView(bookTable);
		
		JLabel lblNewLabel = new JLabel("编号");
		lblNewLabel.setBounds(16, 40, 34, 15);
		
		idTxt = new JTextField();
		idTxt.setBounds(50, 37, 66, 21);
		idTxt.setEditable(false);
		idTxt.setText("");
		idTxt.setColumns(10);
		
		JLabel label_3 = new JLabel("图书名称");
		label_3.setBounds(126, 40, 66, 15);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setBounds(189, 37, 91, 21);
		bookNameTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("作者");
		label_4.setBounds(297, 40, 42, 15);
		
		PressTxt = new JTextField();
		PressTxt.setBounds(487, 37, 199, 21);
		PressTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("进价");
		lblNewLabel_1.setBounds(16, 79, 34, 15);
		
		priceInTxt = new JTextField();
		priceInTxt.setBounds(50, 76, 66, 21);
		priceInTxt.setColumns(10);
		
		JLabel label_5 = new JLabel("售价");
		label_5.setBounds(140, 79, 34, 15);
		
		priceOutTxt = new JTextField();
		priceOutTxt.setBounds(188, 76, 66, 21);
		priceOutTxt.setColumns(10);
		
		JLabel label_6 = new JLabel("数量");
		label_6.setBounds(297, 79, 39, 15);
		
		bookTypeJcb = new JComboBox();
		bookTypeJcb.setBounds(624, 76, 111, 21);
		
		JLabel label_7 = new JLabel("图书备注");
		label_7.setBounds(16, 110, 65, 15);
		
		bookDescTxt = new JTextArea();
		bookDescTxt.setBounds(74, 107, 661, 56);
		
		JButton button_1 = new JButton("修改");
		button_1.setBounds(74, 178, 71, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookUpdateActionPerformed(evt);
			}
		});
		
		JButton button_2 = new JButton("删除");
		button_2.setBounds(183, 178, 71, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookDeleteActionPerformed(evt);
			}
		});
		
		JLabel label_8 = new JLabel("出版社");
		label_8.setBounds(435, 40, 48, 15);
		
		authorTxt = new JTextField();
		authorTxt.setBounds(335, 37, 66, 21);
		authorTxt.setColumns(10);
		
		JLabel label_9 = new JLabel("图书类别");
		label_9.setBounds(569, 79, 57, 15);
		
		JLabel label_10 = new JLabel("折扣");
		label_10.setBounds(435, 79, 42, 15);
		
		stockNumberTxt = new JTextField();
		stockNumberTxt.setBounds(335, 76, 66, 21);
		stockNumberTxt.setColumns(10);
		
		discountJcb = new JComboBox();
		discountJcb.setBounds(487, 76, 57, 21);
		
		JLabel label = new JLabel("图书名称：");
		
		JLabel label_1 = new JLabel("图书作者：");
		
		JLabel label_2 = new JLabel("图书类别：");
		
		s_bookNameTxt = new JTextField();
		s_bookNameTxt.setColumns(10);
		
		s_authorTxt = new JTextField();
		s_authorTxt.setColumns(10);
		
		s_bookTypeJcb = new JComboBox();
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button)
					.addContainerGap(191, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_2)
						.addComponent(label_1)
						.addComponent(s_bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_authorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(s_bookTypeJcb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		//设置文本域边框
		bookDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185),1,false));
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel);
		panel_1.add(idTxt);
		panel_1.add(label_3);
		panel_1.add(bookNameTxt);
		panel_1.add(label_4);
		panel_1.add(authorTxt);
		panel_1.add(lblNewLabel_1);
		panel_1.add(priceInTxt);
		panel_1.add(label_5);
		panel_1.add(priceOutTxt);
		panel_1.add(label_6);
		panel_1.add(stockNumberTxt);
		panel_1.add(label_10);
		panel_1.add(discountJcb);
		panel_1.add(label_9);
		panel_1.add(bookTypeJcb);
		panel_1.add(label_8);
		panel_1.add(PressTxt);
		panel_1.add(label_7);
		panel_1.add(bookDescTxt);
		panel_1.add(button_1);
		panel_1.add(button_2);
		
		this.fillBookType("search");
		this.fillBookType("modify");
		this.fillTable(new Book());
		this.fillDiscount();

	}
	
	/**
	 * 表格行点击事件处理
	 * @param evt
	 */
	private void bookTableMousePressed(MouseEvent evt) {
		int row = bookTable.getSelectedRow();
		idTxt.setText((String)bookTable.getValueAt(row, 0));
		bookNameTxt.setText((String)bookTable.getValueAt(row, 1));
		authorTxt.setText((String)bookTable.getValueAt(row, 2));
		PressTxt.setText((String)bookTable.getValueAt(row, 3));
		priceInTxt.setText(String.valueOf(bookTable.getValueAt(row, 4)));
		priceOutTxt.setText(String.valueOf(bookTable.getValueAt(row, 5)));
		stockNumberTxt.setText(String.valueOf(bookTable.getValueAt(row, 6)));
		discountJcb.setSelectedItem(String.valueOf(bookTable.getValueAt(row, 7)));
		bookDescTxt.setText((String)bookTable.getValueAt(row, 8));
		bookTypeJcb.setSelectedItem((String)bookTable.getValueAt(row, 9));
	}

	/**
	 * 图书删除事件处理
	 * @param evt
	 */
	private void bookDeleteActionPerformed(ActionEvent evt) {
		String id = idTxt.getText();
		if(StringUtil.isEmptry(id)){
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除该记录吗？");
		if(n == 0){
			Connection conn = null;
			try{
				conn = dbUtil.getConn();
				int deleteNum = bookDao.delete(conn, id);
				if(deleteNum == 1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new Book());
				}else{
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}finally{
				try {
					dbUtil.closeConn(conn);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 图书修改事件处理
	 * @param evt
	 */
	private void bookUpdateActionPerformed(ActionEvent evt) {
		String id = this.idTxt.getText();
		if(StringUtil.isEmptry(id)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
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
		
		Book book = new Book(Integer.parseInt(id),bookName, author,Press, Float.parseFloat(priceIn),
				Float.parseFloat(priceOut),Integer.parseInt(stockNumber),
				discount,bookTypeId,bookDesc);
		
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			int addNum = bookDao.update(conn, book);
			if(addNum == 1){
				JOptionPane.showMessageDialog(null, "图书修改成功！");
				resetValue();
				this.fillTable(new Book());
			}else{
				JOptionPane.showMessageDialog(null, "图书修改失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "图书修改失败！");
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
		this.idTxt.setText("");
		this.bookNameTxt.setText("");
		this.priceOutTxt.setText("");
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
	 * 图书查询事件处理
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {
		String bookName = this.s_bookNameTxt.getText();
		String author = this.s_authorTxt.getText();
		BookType bookType = (BookType)this.s_bookTypeJcb.getSelectedItem();
		int bookTypeId = bookType.getId();
		
		Book book = new Book(bookName,author,bookTypeId);
		this.fillTable(book);
	}

	/**
	 * 初始化图书类型下拉框
	 * @param type：下拉框类型
	 */
	private void fillBookType(String type){
		Connection conn = null;
		BookType bookType = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn, new BookType());
			if("search".equals(type)){
				bookType = new BookType();
				bookType.setBookTypeName("请选择……");
				bookType.setId(1);
				this.s_bookTypeJcb.addItem(bookType);
			}
			while(rs.next()){
				bookType = new BookType();
				bookType.setBookTypeName(rs.getString("bookTypeName"));
				bookType.setId(rs.getInt("id"));
				if("search".equals(type)){
					this.s_bookTypeJcb.addItem(bookType);
				}else if("modify".equals(type)){
					this.bookTypeJcb.addItem(bookType);
				}
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
		DefaultTableModel dtm =  (DefaultTableModel)bookTable.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = bookDao.list(conn, book);
			while(rs.next()){
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
				v.add(rs.getString("bookTypeName"));
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
	}
	
	/**
	 * 初始化折扣下拉框
	 * @param type：下拉框类型
	 */
	private void fillDiscount(){
		for(int i = 10;i > 0;i --)
			this.discountJcb.addItem(i);
	} 

}
