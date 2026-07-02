package com.yxm.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.yxm.dao.ManagerDao;
import com.yxm.model.Manager;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;
import java.awt.SystemColor;

public class LogOnfrm extends JFrame {

	private JPanel contentPane;
	private JTextField managerNameTxt;
	private JPasswordField passwordTxt;
	
	private DbUtil dbUtil = new DbUtil();
	private ManagerDao managerDao = new ManagerDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnfrm frame = new LogOnfrm();
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
	public LogOnfrm() {
		//更改默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
	
		setResizable(false);
		setTitle("管理员登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 339);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("新华书店管理系统");
		lblNewLabel.setBounds(78, 25, 290, 84);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 22));
		lblNewLabel.setIcon(new ImageIcon(LogOnfrm.class.getResource("/images/bookmanager.png")));
		
		JLabel lblNewLabel_1 = new JLabel("账  户");
		lblNewLabel_1.setBounds(116, 131, 47, 15);
		
		JLabel lblNewLabel_2 = new JLabel("密  码");
		lblNewLabel_2.setBounds(116, 172, 36, 15);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setBounds(128, 229, 69, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setBounds(230, 229, 67, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		managerNameTxt = new JTextField();
		managerNameTxt.setBounds(167, 128, 141, 21);
		managerNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(167, 169, 141, 21);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		contentPane.add(passwordTxt);
		contentPane.add(managerNameTxt);
		contentPane.add(lblNewLabel);
		
		//设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}

	/**
	 * 登录事件处理
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent evt) {
		String managerName = this.managerNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmptry(managerName)){
			JOptionPane.showConfirmDialog(null,"账户名不能为空！");
			return;
		}
		if(StringUtil.isEmptry(password)){
			JOptionPane.showConfirmDialog(null, "密码不能为空！");
			return;
		}
		Manager manager = new Manager(managerName,password);
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			Manager currentManager = managerDao.login(conn, manager);
			if(currentManager != null){
				dispose();
				new MainFrm().setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "账户名或者密码错误！");
			}
		} catch (Exception e) {	
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

	/**
	 * 重置事件处理
	 * @param evt
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		
		this.managerNameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
