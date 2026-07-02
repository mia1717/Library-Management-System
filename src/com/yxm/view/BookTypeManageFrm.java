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
import com.yxm.model.BookType;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class BookTypeManageFrm extends JFrame {

	private JTable bookTypeTable;	
	private JTextArea bookTypeDescTxt;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	BookDao bookDao = new BookDao();
	private JTextField s_bookTypeNameTxt;
	private JTextField idTxt;
	private JTextField bookTypeNameTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTypeManageFrm frame = new BookTypeManageFrm();
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
	public BookTypeManageFrm() {
		setFocusable(true);
		setTitle("图书类别管理");
		setBounds(100, 100, 450, 480);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel(" 图书类别名称");
		
		s_bookTypeNameTxt = new JTextField();
		s_bookTypeNameTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeSearchActionPerformed(e);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(32)
							.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("编号");
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("图书类别名称");
		
		bookTypeNameTxt = new JTextField();
		bookTypeNameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("备注：");
		
		bookTypeDescTxt = new JTextArea();
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeUpdateActionEvent(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookTypeDeleteActionEvent(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookTypeNameTxt, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(bookTypeDescTxt, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(66)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(58))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(bookTypeNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(bookTypeDescTxt, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		bookTypeTable = new JTable();
		bookTypeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				bookTypeTableMousePressed(e);
			}
		});
		bookTypeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u56FE\u4E66\u7C7B\u522B\u540D\u79F0", "\u56FE\u4E66\u7C7B\u522B\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(bookTypeTable);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new BookType());
		
		//设置文本域边框
		bookTypeDescTxt.setBorder(new LineBorder(new java.awt.Color(127, 157, 185),1,false));
	}
	
	/**
	 * 图书类别删除事件处理
	 * @param evt
	 */
	private void bookTypeDeleteActionEvent(ActionEvent evt) {
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
				boolean flag = bookDao.existBookByBookTypeId(conn, id);
				if(flag){
					JOptionPane.showMessageDialog(null, "当前图书类别下有图书，不能删除此类别");
					return;
				}
				int deleteNum = bookTypeDao.delete(conn, id);
				if(deleteNum == 1){
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new BookType());
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
	 * 图书类别修改事件处理
	 * @param evt
	 */
	private void bookTypeUpdateActionEvent(ActionEvent evt) {
		String id = idTxt.getText();
		String bookTypeName = bookTypeNameTxt.getText();
		String bookTypeDesc = bookTypeDescTxt.getText();
		if(StringUtil.isEmptry(id)){
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		if(StringUtil.isEmptry(bookTypeName)){
			JOptionPane.showMessageDialog(null, "图书类别名称不能为空");
			return;
		}
		BookType bookType = new BookType(Integer.parseInt(id),bookTypeName,bookTypeDesc);
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			int modifyNum = bookTypeDao.update(conn, bookType);
			if(modifyNum == 1){
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new BookType());
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "修改失败");
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
	 * 重置数据
	 */
	private void resetValue(){
		this.idTxt.setText("");
		this.bookTypeNameTxt.setText("");
		this.bookTypeDescTxt.setText("");
	}
	
	/**
	 * 表格行点击事件处理
	 * @param evt
	 */
	private void bookTypeTableMousePressed(MouseEvent evt) {
		
		int row = bookTypeTable.getSelectedRow();
		idTxt.setText((String)bookTypeTable.getValueAt(row, 0));
		bookTypeNameTxt.setText((String)bookTypeTable.getValueAt(row, 1));
		bookTypeDescTxt.setText((String)bookTypeTable.getValueAt(row, 2));
	}

	/**
	 * 图书类别搜索事件处理
	 * @param evt
	 */
	private void bookTypeSearchActionPerformed(ActionEvent evt) {
		String s = this.s_bookTypeNameTxt.getText();
		BookType bookType = new BookType();
		bookType.setBookTypeName(s);
		this.fillTable(bookType);
	}

	/**
	 * 初始化表格
	 */
	private void fillTable(BookType bookType){
		DefaultTableModel dtm =  (DefaultTableModel)bookTypeTable.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = bookTypeDao.list(conn, bookType);
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("bookTypeName"));
				v.add(rs.getString("bookTypeDesc"));
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
}