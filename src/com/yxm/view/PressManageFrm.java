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
import javax.swing.JComboBox;
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

import com.yxm.dao.PressDao;
import com.yxm.model.Press;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class PressManageFrm extends JFrame {
    private JTextField s_pressNameTxt;
    private DbUtil dbUtil = new DbUtil();
    private PressDao pressDao = new PressDao();
    private JTextField idTxt;
    private JTextField pressNameTxt;
    private JTextField pressAddressTxt;
    private JTextField pressPhoneTxt;
    private JTable pressTable;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PressManageFrm frame = new PressManageFrm();
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
    public PressManageFrm() {
        setFocusable(true);
        setTitle("出版社管理");
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

        pressTable = new JTable();
        pressTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressTableMousePressed(e);
            }
        });
        pressTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u7F16\u53F7", "\u51FA\u7248\u793E\u540D\u79F0", "\u51FA\u7248\u793E\u5730\u5740", "\u51FA\u7248\u793E\u7535\u8bdd"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        pressTable.getColumnModel().getColumn(0).setPreferredWidth(35);
        pressTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        pressTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        pressTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        scrollPane.setViewportView(pressTable);

        JLabel lblNewLabel = new JLabel("编号");
        lblNewLabel.setBounds(16, 40, 34, 15);

        idTxt = new JTextField();
        idTxt.setBounds(50, 37, 66, 21);
        idTxt.setEditable(false);
        idTxt.setText("");
        idTxt.setColumns(10);

        JLabel label_3 = new JLabel("出版社名称");
        label_3.setBounds(126, 40, 72, 15);

        pressNameTxt = new JTextField();
        pressNameTxt.setBounds(189, 37, 108, 21);
        pressNameTxt.setColumns(10);

        JLabel label_4 = new JLabel("出版社地址");
        label_4.setBounds(307, 40, 72, 15);

        pressAddressTxt = new JTextField();
        pressAddressTxt.setBounds(389, 37, 108, 21);
        pressAddressTxt.setColumns(10);

        JLabel label_5 = new JLabel("出版社电话");
        label_5.setBounds(507, 40, 72, 15);

        pressPhoneTxt = new JTextField();
        pressPhoneTxt.setBounds(589, 37, 108, 21);
        pressPhoneTxt.setColumns(10);

        JButton button_1 = new JButton("修改");
        button_1.setBounds(74, 85, 71, 23);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pressUpdateActionPerformed(evt);
            }
        });

        JButton button_2 = new JButton("删除");
        button_2.setBounds(183, 85, 71, 23);
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pressDeleteActionPerformed(evt);
            }
        });

        JLabel label = new JLabel("出版社名称：");

        s_pressNameTxt = new JTextField();
        s_pressNameTxt.setColumns(10);

        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pressSearchActionPerformed(e);
            }
        });
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(label)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(s_pressNameTxt, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(s_pressNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        getContentPane().setLayout(groupLayout);

        panel_1.setLayout(null);
        panel_1.add(lblNewLabel);
        panel_1.add(idTxt);
        panel_1.add(label_3);
        panel_1.add(pressNameTxt);
        panel_1.add(label_4);
        panel_1.add(pressAddressTxt);
        panel_1.add(label_5);
        panel_1.add(pressPhoneTxt);
        panel_1.add(button_1);
        panel_1.add(button_2);

        this.fillTable(new Press());
    }

    /**
     * 表格行点击事件处理
     * @param evt
     */
    private void pressTableMousePressed(MouseEvent evt) {
        int row = pressTable.getSelectedRow();
        idTxt.setText((String)pressTable.getValueAt(row, 0));
        pressNameTxt.setText((String)pressTable.getValueAt(row, 1));
        pressAddressTxt.setText((String)pressTable.getValueAt(row, 2));
        pressPhoneTxt.setText((String)pressTable.getValueAt(row, 3));
    }

    /**
     * 出版社删除事件处理
     * @param evt
     */
    private void pressDeleteActionPerformed(ActionEvent evt) {
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
                int deleteNum = pressDao.delete(conn, id);
                if(deleteNum == 1){
                    JOptionPane.showMessageDialog(null, "删除成功");
                    this.resetValue();
                    this.fillTable(new Press());
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
     * 出版社修改事件处理
     * @param evt
     */
    private void pressUpdateActionPerformed(ActionEvent evt) {
        String id = this.idTxt.getText();
        if(StringUtil.isEmptry(id)){
            JOptionPane.showMessageDialog(null, "请选择要修改的记录");
            return;
        }
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

        Press press = new Press(); // 使用无参构造器
        press.setId(Integer.parseInt(id));
        press.setPressName(pressName);
        press.setPressAddress(pressAddress);
        press.setPressPhone(pressPhone);

        Connection conn = null;
        try{
            conn = dbUtil.getConn();
            int updateNum = pressDao.update(conn, press);
            if(updateNum == 1){
                JOptionPane.showMessageDialog(null, "出版社修改成功！");
                resetValue();
                this.fillTable(new Press());
            }else{
                JOptionPane.showMessageDialog(null, "出版社修改失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "出版社修改失败！");
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
        this.pressNameTxt.setText("");
        this.pressAddressTxt.setText("");
        this.pressPhoneTxt.setText("");
    }

    /**
     * 出版社查询事件处理
     * @param e
     */
    private void pressSearchActionPerformed(ActionEvent e) {
        String pressName = this.s_pressNameTxt.getText();
        Press press = new Press(pressName);
        this.fillTable(press);
    }

    /**
     * 初始化表格数据
     * @param press
     */
    private void fillTable(Press press){
        DefaultTableModel dtm =  (DefaultTableModel)pressTable.getModel();
        dtm.setRowCount(0);//设置成0行
        Connection conn = null;
        try{
            conn = dbUtil.getConn();
            ResultSet rs = pressDao.list(conn, press);
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("pressName"));
                v.add(rs.getString("pressAddress"));
                v.add(rs.getString("pressPhone"));
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