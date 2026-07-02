package com.yxm.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.model.Book;
import com.yxm.util.DbUtil;

import jxl.Sheet;
import jxl.Workbook;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.awt.event.ActionEvent;

import java.io.IOException;

import jxl.read.biff.BiffException;

public class DateImportFrm extends JFrame {

	private JPanel contentPane;
	
	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateImportFrm frame = new DateImportFrm();
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
	public DateImportFrm() {
		setTitle("数据导入");
		setFocusable(true);
		setBounds(100, 100, 420, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("选择要导入的表：");
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton button = new JButton("图书类别表");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookTypeImportActionPerformed(evt);
			}
		});
		
		JButton button_1 = new JButton("图书表");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					bookImportActionPerformed(evt);
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton button_2 = new JButton("销售表");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(115)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(137)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addGap(18)
					.addComponent(button_1)
					.addGap(18)
					.addComponent(button_2)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	
	private void bookTypeImportActionPerformed(ActionEvent evt) {
		JFileChooser jFileChooser = new JFileChooser();
        int i = jFileChooser.showOpenDialog(null);
        if(i== jFileChooser.APPROVE_OPTION){ //打开文件
            String path = jFileChooser.getSelectedFile().getAbsolutePath();
            String name = jFileChooser.getSelectedFile().getName();
            System.out.println("当前文件路径："+path+";\n当前文件名："+name);
        }else{
            System.out.println("没有选中文件");
        }
	}

	private void bookImportActionPerformed(ActionEvent evt)throws BiffException, IOException {
		
		String path = null;
		String name = null;
		
		JFileChooser jFileChooser = new JFileChooser();
        int m = jFileChooser.showOpenDialog(null);
        if(m == jFileChooser.APPROVE_OPTION){ //打开文件
            path = jFileChooser.getSelectedFile().getAbsolutePath();
            name = jFileChooser.getSelectedFile().getName();
            System.out.println("当前文件路径："+path+";\n当前文件名："+name);
        }else{
            System.out.println("没有选中文件");
        }
        
        if(path != null){
        	// 1、构造excel文件输入流对象   
            InputStream is = new FileInputStream(path);  
            // 2、声明工作簿对象  
            Workbook rwb = Workbook.getWorkbook(is);  
            // 3、获得工作簿的个数,对应于一个excel中的工作表个数  
            rwb.getNumberOfSheets();  
      
            Sheet oFirstSheet = rwb.getSheet(0);// 使用索引形式获取第一个工作表，也可以使用rwb.getSheet(sheetName);其中sheetName表示的是工作表的名称  
            //System.out.println("工作表名称：" + oFirstSheet.getName());  
            int rows = oFirstSheet.getRows();//获取工作表中的总行数  
            int columns = oFirstSheet.getColumns();//获取工作表中的总列数  
            
            String bookName = null;
    		String author = null;
    		String Press = null;
    		String priceIn = null;
    		String priceOut = null;
    		String stockNumber = null;
    		String discount = null;
    		String bookDesc = null;
    		String bookTypeId = null;
            
            for (int i = 1; i < rows; i++) { 
            	int j = 0;
                while(j < columns) {  
                	//需要注意的是这里的getCell方法的参数，第一个是指定第几列，第二个参数才是指定第几行  
                	bookName =oFirstSheet.getCell(j++,i).getContents();
            		author = oFirstSheet.getCell(j++,i).getContents();
            		Press = oFirstSheet.getCell(j++,i).getContents();
            		priceIn = oFirstSheet.getCell(j++,i).getContents();
            		priceOut = oFirstSheet.getCell(j++,i).getContents();
            		stockNumber = oFirstSheet.getCell(j++,i).getContents();
            		discount = oFirstSheet.getCell(j++,i).getContents();
            		bookDesc = oFirstSheet.getCell(j++,i).getContents();
            		bookTypeId = oFirstSheet.getCell(j++,i).getContents();
                }  
                Book book = new Book(bookName,author,Press, Float.parseFloat(priceIn),
        				Float.parseFloat(priceOut),Integer.parseInt(stockNumber),
        				Integer.parseInt(discount),Integer.parseInt(bookTypeId),bookDesc);
                
                Connection conn = null;
        		try{
        			conn = dbUtil.getConn();
        			bookDao.add(conn, book);
     
        		}catch(Exception e){
        			e.printStackTrace();
        			JOptionPane.showMessageDialog(null, "图书表导入失败！");
        		}finally{
        			try {
        				dbUtil.closeConn(conn);
        			} catch (Exception e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
            }  
            JOptionPane.showMessageDialog(null, "图书表导入成功！");
        }
    }
}
