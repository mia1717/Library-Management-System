package com.yxm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DateExportFrm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateExportFrm frame = new DateExportFrm();
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
	public DateExportFrm() {
		setTitle("数据导出");
		setFocusable(true);
		setBounds(100, 100, 420, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("选择要导出的表：");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button = new JButton("图书类别表");
		
		JButton button_1 = new JButton("图书表");
		
		JButton button_2 = new JButton("销售表");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(134)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addGap(18)
					.addComponent(button_1)
					.addGap(18)
					.addComponent(button_2)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
