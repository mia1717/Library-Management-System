package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.yxm.dao.PressDao;
import com.yxm.model.Press;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class PressAddFrm extends JFrame {

    private JTextField pressNameTxt;
    private JTextField pressAddressTxt;
    private JTextField pressPhoneTxt;

    private DbUtil dbUtil = new DbUtil();
    private PressDao pressDao = new PressDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PressAddFrm frame = new PressAddFrm();
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
    public PressAddFrm() {
        setFocusable(true);
        setTitle("出版社添加");
        setBounds(100, 100, 450, 350);

        JLabel lblNewLabel = new JLabel("出版社名称：");
        lblNewLabel.setBounds(36, 38, 72, 15);

        pressNameTxt = new JTextField();
        pressNameTxt.setBounds(106, 35, 108, 21);
        pressNameTxt.setColumns(10);

        JLabel label = new JLabel("出版社地址：");
        label.setBounds(36, 85, 72, 15);

        pressAddressTxt = new JTextField();
        pressAddressTxt.setBounds(106, 82, 108, 21);
        pressAddressTxt.setColumns(10);

        JLabel label_1 = new JLabel("出版社电话：");
        label_1.setBounds(36, 133, 72, 15);

        pressPhoneTxt = new JTextField();
        pressPhoneTxt.setBounds(106, 130, 108, 21);
        pressPhoneTxt.setColumns(10);

        JButton btnNewButton = new JButton("添加");
        btnNewButton.setBounds(145, 175, 66, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pressAddActionPerformed(e);
            }
        });

        JButton btnNewButton_1 = new JButton("重置");
        btnNewButton_1.setBounds(248, 175, 66, 23);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetValueActionPerformed(e);
            }
        });

        getContentPane().setLayout(null);
        getContentPane().add(lblNewLabel);
        getContentPane().add(pressNameTxt);
        getContentPane().add(label);
        getContentPane().add(pressAddressTxt);
        getContentPane().add(label_1);
        getContentPane().add(pressPhoneTxt);
        getContentPane().add(btnNewButton);
        getContentPane().add(btnNewButton_1);
    }

    /**
     * 重置事件处理
     * @param evt
     */
    private void resetValueActionPerformed(ActionEvent evt) {
        this.resetValue();
    }

    /**
     * 出版社添加事件处理
     * @param evt
     */
    private void pressAddActionPerformed(ActionEvent evt) {
        String pressName = this.pressNameTxt.getText();
        String pressAddress = this.pressAddressTxt.getText();
        String pressPhone = this.pressPhoneTxt.getText();

        if(StringUtil.isEmptry(pressName)){
            JOptionPane.showMessageDialog(null, "出版社名称不能为空！");
            return;
        }
        if(StringUtil.isEmptry(pressAddress)){
            JOptionPane.showMessageDialog(null, "出版社地址不能为空！");
            return;
        }
        if(StringUtil.isEmptry(pressPhone)){
            JOptionPane.showMessageDialog(null, "出版社电话不能为空！");
            return;
        }

        Press press = new Press(pressName, pressAddress, pressPhone);

        Connection conn = null;
        try{
            conn = dbUtil.getConn();
            int addNum = pressDao.add(conn, press);
            if(addNum == 1){
                JOptionPane.showMessageDialog(null, "出版社添加成功！");
                resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "出版社添加失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "出版社添加失败！");
        }finally{
            try {
                dbUtil.closeConn(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重置表单
     */
    private void resetValue() {
        this.pressNameTxt.setText("");
        this.pressAddressTxt.setText("");
        this.pressPhoneTxt.setText("");
    }
}