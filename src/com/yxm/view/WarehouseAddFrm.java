package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.yxm.dao.WarehouseDao;
import com.yxm.model.Warehouse;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class WarehouseAddFrm extends JFrame {

    private JTextField warehouseNameTxt;
    private JTextField warehouseLocationTxt;
    private JTextField warehouseCapacityTxt;
    private JTextField currentStockTxt;

    private DbUtil dbUtil = new DbUtil();
    private WarehouseDao warehouseDao = new WarehouseDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WarehouseAddFrm frame = new WarehouseAddFrm();
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
    public WarehouseAddFrm() {
        setFocusable(true);
        setTitle("仓库添加");
        setBounds(100, 100, 450, 350);

        JLabel lblNewLabel = new JLabel("仓库名称：");
        lblNewLabel.setBounds(36, 38, 72, 15);

        warehouseNameTxt = new JTextField();
        warehouseNameTxt.setBounds(106, 35, 108, 21);
        warehouseNameTxt.setColumns(10);

        JLabel label = new JLabel("仓库位置：");
        label.setBounds(36, 85, 72, 15);

        warehouseLocationTxt = new JTextField();
        warehouseLocationTxt.setBounds(106, 82, 108, 21);
        warehouseLocationTxt.setColumns(10);

        JLabel label_1 = new JLabel("仓库容量：");
        label_1.setBounds(36, 133, 72, 15);

        warehouseCapacityTxt = new JTextField();
        warehouseCapacityTxt.setBounds(106, 130, 108, 21);
        warehouseCapacityTxt.setColumns(10);

        JLabel label_2 = new JLabel("当前库存总量：");
        label_2.setBounds(224, 133, 84, 15);

        currentStockTxt = new JTextField();
        currentStockTxt.setBounds(310, 130, 66, 21);
        currentStockTxt.setColumns(10);

        JButton btnNewButton = new JButton("添加");
        btnNewButton.setBounds(145, 175, 66, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                warehouseAddActionPerformed(e);
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
        getContentPane().add(warehouseNameTxt);
        getContentPane().add(label);
        getContentPane().add(warehouseLocationTxt);
        getContentPane().add(label_1);
        getContentPane().add(warehouseCapacityTxt);
        getContentPane().add(label_2);
        getContentPane().add(currentStockTxt);
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
     * 仓库添加事件处理
     * @param evt
     */
    private void warehouseAddActionPerformed(ActionEvent evt) {
        String warehouseName = this.warehouseNameTxt.getText();
        String warehouseLocation = this.warehouseLocationTxt.getText();
        String warehouseCapacity = this.warehouseCapacityTxt.getText();
        String currentStock = this.currentStockTxt.getText();

        if(StringUtil.isEmptry(warehouseName)){
            JOptionPane.showMessageDialog(null, "仓库名称不能为空！");
            return;
        }
        if(StringUtil.isEmptry(warehouseLocation)){
            JOptionPane.showMessageDialog(null, "仓库位置不能为空！");
            return;
        }
        if(StringUtil.isEmptry(warehouseCapacity)){
            JOptionPane.showMessageDialog(null, "仓库容量不能为空！");
            return;
        }
        if(StringUtil.isEmptry(currentStock)){
            JOptionPane.showMessageDialog(null, "当前库存总量不能为空！");
            return;
        }

        Warehouse warehouse = new Warehouse(warehouseName, warehouseLocation, Integer.parseInt(warehouseCapacity), Integer.parseInt(currentStock));

        Connection conn = null;
        try{
            conn = dbUtil.getConn();
            int addNum = warehouseDao.add(conn, warehouse);
            if(addNum == 1){
                JOptionPane.showMessageDialog(null, "仓库添加成功！");
                resetValue();
            }else{
                JOptionPane.showMessageDialog(null, "仓库添加失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "仓库添加失败！");
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
        this.warehouseNameTxt.setText("");
        this.warehouseLocationTxt.setText("");
        this.warehouseCapacityTxt.setText("");
        this.currentStockTxt.setText("");
    }
}