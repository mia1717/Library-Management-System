package com.yxm.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.yxm.dao.WarehouseDao;
import com.yxm.model.Warehouse;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class WarehouseManageFrm extends JFrame {
    private JTextField s_warehouseNameTxt;
    private DbUtil dbUtil = new DbUtil();
    private WarehouseDao warehouseDao = new WarehouseDao();
    private JTextField idTxt;
    private JTextField warehouseNameTxt;
    private JTextField warehouseLocationTxt;
    private JTextField warehouseCapacityTxt;
    private JTextField currentStockTxt;
    private JTable warehouseTable;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WarehouseManageFrm frame = new WarehouseManageFrm();
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
    public WarehouseManageFrm() {
        setFocusable(true);
        setTitle("仓库管理");
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

        warehouseTable = new JTable();
        warehouseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                warehouseTableMousePressed(e);
            }
        });
        warehouseTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u7F16\u53F7", "\u4F9B\u5E93\u540D\u79F0", "\u4F9B\u5E93\u4F4D\u7F6E", "\u4F9B\u5E93\u5BB9\u91CF", "\u5F53\u524D\u5E93\u5B58"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        warehouseTable.getColumnModel().getColumn(0).setPreferredWidth(35);
        warehouseTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        warehouseTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        warehouseTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        warehouseTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        scrollPane.setViewportView(warehouseTable);

        JLabel lblNewLabel = new JLabel("编号");
        lblNewLabel.setBounds(16, 40, 34, 15);

        idTxt = new JTextField();
        idTxt.setBounds(50, 37, 66, 21);
        idTxt.setEditable(false);
        idTxt.setText("");
        idTxt.setColumns(10);

        JLabel label_3 = new JLabel("仓库名称");
        label_3.setBounds(126, 40, 72, 15);

        warehouseNameTxt = new JTextField();
        warehouseNameTxt.setBounds(189, 37, 108, 21);
        warehouseNameTxt.setColumns(10);

        JLabel label_4 = new JLabel("仓库位置");
        label_4.setBounds(307, 40, 72, 15);

        warehouseLocationTxt = new JTextField();
        warehouseLocationTxt.setBounds(389, 37, 108, 21);
        warehouseLocationTxt.setColumns(10);

        JLabel label_5 = new JLabel("仓库容量");
        label_5.setBounds(507, 40, 72, 15);

        warehouseCapacityTxt = new JTextField();
        warehouseCapacityTxt.setBounds(589, 37, 66, 21);
        warehouseCapacityTxt.setColumns(10);

        JLabel label_6 = new JLabel("当前库存总量");
        label_6.setBounds(667, 40, 84, 15);

        currentStockTxt = new JTextField();
        currentStockTxt.setBounds(750, 37, 66, 21);
        currentStockTxt.setColumns(10);

        JButton button_1 = new JButton("修改");
        button_1.setBounds(74, 85, 71, 23);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                warehouseUpdateActionPerformed(evt);
            }
        });

        JButton button_2 = new JButton("删除");
        button_2.setBounds(183, 85, 71, 23);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                warehouseDeleteActionPerformed(evt);
            }
        });

        JLabel label = new JLabel("仓库名称：");

        s_warehouseNameTxt = new JTextField();
        s_warehouseNameTxt.setColumns(10);

        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                warehouseSearchActionPerformed(e);
            }
        });
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(label)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(s_warehouseNameTxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(button)
                                .addContainerGap(534, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(s_warehouseNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);

        panel_1.setLayout(null);
        panel_1.add(lblNewLabel);
        panel_1.add(idTxt);
        panel_1.add(label_3);
        panel_1.add(warehouseNameTxt);
        panel_1.add(label_4);
        panel_1.add(warehouseLocationTxt);
        panel_1.add(label_5);
        panel_1.add(warehouseCapacityTxt);
        panel_1.add(label_6);
        panel_1.add(currentStockTxt);
        panel_1.add(button_1);
        panel_1.add(button_2);

        this.fillTable(new Warehouse());
    }

    /**
     * 表格行点击事件处理
     * @param evt
     */
    private void warehouseTableMousePressed(MouseEvent evt) {
        int row = warehouseTable.getSelectedRow();
        idTxt.setText((String)warehouseTable.getValueAt(row, 0));
        warehouseNameTxt.setText((String)warehouseTable.getValueAt(row, 1));
        warehouseLocationTxt.setText((String)warehouseTable.getValueAt(row, 2));
        warehouseCapacityTxt.setText(String.valueOf(warehouseTable.getValueAt(row, 3)));
        currentStockTxt.setText(String.valueOf(warehouseTable.getValueAt(row, 4)));
    }

    /**
     * 仓库删除事件处理
     * @param evt
     */
    private void warehouseDeleteActionPerformed(ActionEvent evt) {
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
                int deleteNum = warehouseDao.delete(conn, id);
                if(deleteNum == 1){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new Warehouse());
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
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 仓库修改事件处理
     * @param evt
     */
    private void warehouseUpdateActionPerformed(ActionEvent evt) {
        String id = this.idTxt.getText();
        if(StringUtil.isEmptry(id)){
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
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

        Warehouse warehouse = new Warehouse(); // 使用无参构造器
        warehouse.setId(Integer.parseInt(id));
        warehouse.setWarehouseName(warehouseName);
        warehouse.setWarehouseLocation(warehouseLocation);
        warehouse.setWarehouseCapacity(Integer.parseInt(warehouseCapacity));
        warehouse.setCurrentStock(Integer.parseInt(currentStock));

        Connection conn = null;
        try{
            conn = dbUtil.getConn();
            int updateNum = warehouseDao.update(conn, warehouse);
            if(updateNum == 1){
                JOptionPane.showMessageDialog(null, "仓库修改成功！");
                resetValue();
                this.fillTable(new Warehouse());
            }else{
                JOptionPane.showMessageDialog(null, "仓库修改失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "仓库修改失败！");
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
        this.idTxt.setText("");
        this.warehouseNameTxt.setText("");
        this.warehouseLocationTxt.setText("");
        this.warehouseCapacityTxt.setText("");
        this.currentStockTxt.setText("");
    }

    /**
     * 仓库查询事件处理
     * @param e
     */
    private void warehouseSearchActionPerformed(ActionEvent e) {
        String warehouseName = this.s_warehouseNameTxt.getText();
        Warehouse warehouse = new Warehouse(warehouseName);
        this.fillTable(warehouse);
    }

    /**
     * 初始化表格数据
     * @param warehouse
     */
    private void fillTable(Warehouse warehouse){
        DefaultTableModel dtm =  (DefaultTableModel)warehouseTable.getModel();
        dtm.setRowCount(0);//设置成0行
        Connection conn = null;
        try{
            conn = dbUtil.getConn();
            ResultSet rs = warehouseDao.list(conn, warehouse);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("warehouseName"));
                v.add(rs.getString("warehouseLocation"));
                v.add(rs.getInt("warehouseCapacity"));
                v.add(rs.getInt("currentStock"));
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