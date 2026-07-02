package com.yxm.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.yxm.dao.BookDao;
import com.yxm.dao.BookTypeDao;
import com.yxm.dao.TradeDao;
import com.yxm.model.Book;
import com.yxm.model.SaleOrder;
import com.yxm.model.Trade;
import com.yxm.util.DbUtil;
import com.yxm.util.StringUtil;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktop;
	private JTextField bookNameTxt;
	private JTextField bookNumTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField dayProfitTxt;
	private JTextField monthProfitTxt;
	private JTextField yearProfitTxt;
	private JTable bookTable;

	private DbUtil dbUtil = new DbUtil();
	private BookTypeDao bookTypeDao = new BookTypeDao();
	private BookDao bookDao = new BookDao();
	private TradeDao tradeDao = new TradeDao();

	private JRadioButton originPrice;
	private JRadioButton discountPrice;
	private JTable tradeTable;
	private JTextField dayTxt;
	private JTextField monthTxt;
	private JTextField yearTxt;
	private JTextField inputDateTxt;
	private JTable selectedBooksTable;
	private DefaultTableModel selectedBooksModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("图书进货、销售管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("基本数据维护");
		menuBar.add(mnNewMenu);

		// 出版社管理
		JMenu mnNewMenu_4 = new JMenu("出版社管理");
		mnNewMenu.add(mnNewMenu_4);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("出版社添加");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PressAddFrm pressAddFrm = new PressAddFrm();
				pressAddFrm.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("出版社维护");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PressManageFrm pressManageFrm = new PressManageFrm();
				pressManageFrm.setVisible(true);
			}
		});
		mnNewMenu_4.add(mntmNewMenuItem_5);

// 仓库管理
		JMenu mnNewMenu_5 = new JMenu("仓库管理");
		mnNewMenu.add(mnNewMenu_5);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("仓库添加");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WarehouseAddFrm warehouseAddFrm = new WarehouseAddFrm();
				warehouseAddFrm.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_6);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("仓库维护");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WarehouseManageFrm warehouseManageFrm = new WarehouseManageFrm();
				warehouseManageFrm.setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_7);

		JMenu mnNewMenu_2 = new JMenu("图书类别管理");
		mnNewMenu.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("图书类别添加");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeAddFrm bookTypeAddFrm = new BookTypeAddFrm();
				bookTypeAddFrm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("图书类别维护");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookTypeManageFrm bookTypeManageFrm = new BookTypeManageFrm();
				bookTypeManageFrm.setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		JMenu mnNewMenu_3 = new JMenu("图书管理");
		mnNewMenu.add(mnNewMenu_3);

		JMenuItem menuItem = new JMenuItem("图书添加");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookAddFrm bookAddFrm = new BookAddFrm();
				bookAddFrm.setVisible(true);
			}
		});
		mnNewMenu_3.add(menuItem);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("图书维护");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookManageFrm bookManageFrm = new BookManageFrm();
				bookManageFrm.setVisible(true);
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_3);

		JMenu menu = new JMenu("查询统计");
		menuBar.add(menu);

		JMenuItem menuItem_1 = new JMenuItem("图书查询");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryAllFrm queryAllFrm = new QueryAllFrm();
				queryAllFrm.setVisible(true);
			}
		});
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("分类查询");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryByTypeFrm queryByTypeFrm = new QueryByTypeFrm();
				queryByTypeFrm.setVisible(true);
			}
		});
		menu.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("库存查询");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryStockFrm queryStockFrm = new QueryStockFrm();
				queryStockFrm.setVisible(true);
			}
		});
		menu.add(menuItem_3);

		JMenu menu_1 = new JMenu("数据管理");
		menuBar.add(menu_1);

		JMenuItem menuItem_5 = new JMenuItem("数据导出");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateExportFrm dateExportFrm = new DateExportFrm();
				dateExportFrm.setVisible(true);
			}
		});
		menu_1.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("数据导入");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateImportFrm dateImportFrm = new DateImportFrm();
				dateImportFrm.setVisible(true);
			}
		});
		menu_1.add(menuItem_6);

		JMenuItem menuItem_4 = new JMenuItem("安全退出");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
				if(result == 0)
					dispose();
			}
		});
		menuBar.add(menuItem_4);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		desktop = new JDesktopPane();
		desktop.setBackground(SystemColor.control);
		contentPane.add(desktop, BorderLayout.CENTER);
		desktop.setLayout(null);

		// 左侧标题区域
		JLabel lblNewLabel = new JLabel("销售");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
		lblNewLabel.setBounds(21, 22, 80, 60);
		desktop.add(lblNewLabel);

		// 销售操作区域
		JPanel saleOperationPanel = new JPanel();
		saleOperationPanel.setBounds(130, 22, 1000, 100);
		saleOperationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("销售操作"));
		desktop.add(saleOperationPanel);
		saleOperationPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("图书名称");
		lblNewLabel_1.setBounds(20, 40, 54, 15);
		saleOperationPanel.add(lblNewLabel_1);

		bookNameTxt = new JTextField();
		bookNameTxt.setBounds(80, 37, 150, 21);
		saleOperationPanel.add(bookNameTxt);
		bookNameTxt.setColumns(10);

		JButton searchButton = new JButton("搜索");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookSearchActionPerformed(e);
			}
		});
		searchButton.setBounds(240, 35, 93, 23);
		saleOperationPanel.add(searchButton);

		JLabel label = new JLabel("数量");
		label.setBounds(360, 40, 38, 15);
		saleOperationPanel.add(label);

		bookNumTxt = new JTextField();
		bookNumTxt.setBounds(400, 37, 66, 21);
		saleOperationPanel.add(bookNumTxt);
		bookNumTxt.setColumns(10);

		originPrice = new JRadioButton("原价");
		buttonGroup.add(originPrice);
		originPrice.setSelected(true);
		originPrice.setBounds(480, 35, 60, 23);
		saleOperationPanel.add(originPrice);

		discountPrice = new JRadioButton("折扣价");
		buttonGroup.add(discountPrice);
		discountPrice.setBounds(540, 35, 121, 23);
		saleOperationPanel.add(discountPrice);

		JButton addToSaleButton = new JButton("添加到销售列表");
		addToSaleButton.addActionListener(e -> addToSaleList());
		addToSaleButton.setBounds(670, 35, 150, 23);
		saleOperationPanel.add(addToSaleButton);

		JButton bookSaleButton = new JButton("售出");
		bookSaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				bookSaleActionPerformed(evt);
			}
		});
		bookSaleButton.setBounds(830, 35, 93, 23);
		saleOperationPanel.add(bookSaleButton);

		// 图书列表区域
		JPanel bookListPanel = new JPanel();
		bookListPanel.setBounds(130, 130, 1000, 200);
		bookListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("图书列表"));
		desktop.add(bookListPanel);
		bookListPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane bookTableScrollPane = new JScrollPane();
		bookListPanel.add(bookTableScrollPane, BorderLayout.CENTER);

		bookTable = new JTable();
		bookTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				bookTableMousePressed(evt);
			}
		});
		bookTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"图书编号", "图书名称", "作者", "出版社", "售价", "数量", "折扣", "图书备注", "图书类别"
				}
		));
		TableColumnModel bookTableColumnModel = bookTable.getColumnModel();
		bookTableColumnModel.getColumn(0).setPreferredWidth(63);
		bookTableColumnModel.getColumn(1).setPreferredWidth(83);
		bookTableColumnModel.getColumn(3).setPreferredWidth(120);
		bookTableColumnModel.getColumn(7).setPreferredWidth(149);
		bookTableScrollPane.setViewportView(bookTable);

		// 销售列表区域
		JPanel selectedBooksPanel = new JPanel();
		selectedBooksPanel.setBounds(130, 340, 1000, 150);
		selectedBooksPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("销售列表"));
		desktop.add(selectedBooksPanel);
		selectedBooksPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane selectedBooksScrollPane = new JScrollPane();
		selectedBooksPanel.add(selectedBooksScrollPane, BorderLayout.CENTER);

		initSelectedBooksTable();
		selectedBooksScrollPane.setViewportView(selectedBooksTable);

		JButton removeFromSaleButton = new JButton("从销售列表移除");
		removeFromSaleButton.addActionListener(e -> {
			int selectedRow = selectedBooksTable.getSelectedRow();
			if(selectedRow >= 0) {
				selectedBooksModel.removeRow(selectedRow);
			} else {
				JOptionPane.showMessageDialog(null, "请先选择要移除的图书！");
			}
		});
		removeFromSaleButton.setBounds(830, 65, 150, 25);
		selectedBooksPanel.add(removeFromSaleButton, BorderLayout.SOUTH);

		// 左侧记录标题区域
		JLabel label_1 = new JLabel("记录");
		label_1.setFont(new Font("华文行楷", Font.PLAIN, 40));
		label_1.setBounds(21, 500, 80, 60);
		desktop.add(label_1);

		// 销售记录区域
		JPanel tradeRecordPanel = new JPanel();
		tradeRecordPanel.setBounds(130, 500, 700, 250);
		tradeRecordPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("售书情况"));
		desktop.add(tradeRecordPanel);
		tradeRecordPanel.setLayout(new BorderLayout(0, 0));

		JPanel tradeRecordControlPanel = new JPanel();
		tradeRecordControlPanel.setLayout(null);
		tradeRecordPanel.add(tradeRecordControlPanel, BorderLayout.NORTH);

		JLabel lblYyyymmdd = new JLabel("yyyy-mm-dd");
		lblYyyymmdd.setBounds(20, 10, 84, 15);
		tradeRecordControlPanel.add(lblYyyymmdd);

		inputDateTxt = new JTextField();
		inputDateTxt.setBounds(110, 7, 80, 21);
		tradeRecordControlPanel.add(inputDateTxt);
		inputDateTxt.setColumns(10);

		JButton tradeRefreshButton = new JButton("查询");
		tradeRefreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				tradeTableRefreshActionPerformed(evt);
			}
		});
		tradeRefreshButton.setBounds(200, 5, 93, 23);
		tradeRecordControlPanel.add(tradeRefreshButton);

		JButton exportReportButton = new JButton("导出报表");
		exportReportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					exportReportSActionPerformed(evt);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		exportReportButton.setBounds(300, 5, 93, 23);
		tradeRecordControlPanel.add(exportReportButton);

		JScrollPane tradeTableScrollPane = new JScrollPane();
		tradeRecordPanel.add(tradeTableScrollPane, BorderLayout.CENTER);

		tradeTable = new JTable();
		tradeTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"销售编号", "图书编号", "进价", "实际售价", "销售数量", "利润", "销售日期"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tradeTableScrollPane.setViewportView(tradeTable);

		// 利润统计区域
		JPanel profitPanel = new JPanel();
		profitPanel.setBounds(840, 500, 290, 250);
		profitPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("利润统计"));
		desktop.add(profitPanel);
		profitPanel.setLayout(null);

		JLabel label_3 = new JLabel("日");
		label_3.setBounds(40, 40, 25, 15);
		profitPanel.add(label_3);

		JLabel label_4 = new JLabel("月");
		label_4.setBounds(40, 80, 25, 15);
		profitPanel.add(label_4);

		JLabel label_5 = new JLabel("年");
		label_5.setBounds(40, 120, 25, 15);
		profitPanel.add(label_5);

		JLabel lblNewLabel_2 = new JLabel("日期");
		lblNewLabel_2.setBounds(20, 20, 66, 15);
		profitPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("利润（元）");
		lblNewLabel_3.setBounds(140, 20, 66, 15);
		profitPanel.add(lblNewLabel_3);

		dayTxt = new JTextField();
		dayTxt.setBounds(70, 37, 80, 21);
		profitPanel.add(dayTxt);
		dayTxt.setColumns(10);

		monthTxt = new JTextField();
		monthTxt.setBounds(70, 77, 80, 21);
		profitPanel.add(monthTxt);
		monthTxt.setColumns(10);

		yearTxt = new JTextField();
		yearTxt.setBounds(70, 117, 80, 21);
		profitPanel.add(yearTxt);
		yearTxt.setColumns(10);

		dayProfitTxt = new JTextField();
		dayProfitTxt.setBounds(200, 37, 99, 21);
		profitPanel.add(dayProfitTxt);
		dayProfitTxt.setColumns(10);

		monthProfitTxt = new JTextField();
		monthProfitTxt.setColumns(10);
		monthProfitTxt.setBounds(200, 77, 99, 21);
		profitPanel.add(monthProfitTxt);

		yearProfitTxt = new JTextField();
		yearProfitTxt.setColumns(10);
		yearProfitTxt.setBounds(200, 117, 99, 21);
		profitPanel.add(yearProfitTxt);

		JButton refreshProfitButton = new JButton("刷新");
		refreshProfitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				refreshProfitActionPerformed(evt);
			}
		});
		refreshProfitButton.setBounds(140, 160, 99, 23);
		profitPanel.add(refreshProfitButton);

		// 设置最大化
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.fillBookTable(new Book());
		this.fillTradeTable(new Trade());
		this.refreshProfitActionPerformed(null);
	}

	// 添加初始化销售列表表格的方法
	private void initSelectedBooksTable() {
		selectedBooksModel = new DefaultTableModel(
				new Object[][]{},
				new String[]{"图书编号", "图书名称", "作者", "单价", "数量", "折扣", "小计"}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// 只有数量列可编辑
				return column == 4;
			}
		};

		selectedBooksTable = new JTable(selectedBooksModel);
		selectedBooksTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(new JTextField()));
	}

	// 添加将图书添加到销售列表的方法
	private void addToSaleList() {
		int selectedRow = bookTable.getSelectedRow();
		if(selectedRow < 0) {
			JOptionPane.showMessageDialog(null, "请先在图书列表中选择一行！");
			return;
		}

		try {
			// 安全地获取图书ID
			int bookId = Integer.parseInt(bookTable.getValueAt(selectedRow, 0).toString());

			// 安全地获取其他数值数据
			float priceOut = Float.parseFloat(bookTable.getValueAt(selectedRow, 4).toString());
			int discount = originPrice.isSelected() ? 10 : Integer.parseInt(bookTable.getValueAt(selectedRow, 6).toString());

			String saleNumberStr = bookNumTxt.getText();
			if(StringUtil.isEmptry(saleNumberStr)) {
				JOptionPane.showMessageDialog(null, "请输入销售数量！");
				return;
			}

			int saleNumber = Integer.parseInt(saleNumberStr);
			if(saleNumber <= 0) {
				throw new NumberFormatException();
			}

			// 检查是否已存在相同图书
			for(int i = 0; i < selectedBooksModel.getRowCount(); i++) {
				int existingBookId = Integer.parseInt(selectedBooksModel.getValueAt(i, 0).toString());
				if(existingBookId == bookId) {
					int currentQty = Integer.parseInt(selectedBooksModel.getValueAt(i, 4).toString());
					selectedBooksModel.setValueAt(currentQty + saleNumber, i, 4);
					selectedBooksModel.setValueAt(priceOut * (currentQty + saleNumber) * discount / 10, i, 6);
					bookNumTxt.setText("");
					return;
				}
			}

			// 计算实际价格和小计
			float realPrice = priceOut * discount / 10;
			float subtotal = realPrice * saleNumber;

			// 添加到销售列表
			selectedBooksModel.addRow(new Object[]{
					bookId,
					bookTable.getValueAt(selectedRow, 1), // 图书名称
					bookTable.getValueAt(selectedRow, 2), // 作者
					priceOut,
					saleNumber,
					discount + "折",
					subtotal
			});

			// 清空数量输入
			bookNumTxt.setText("");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "输入数据格式错误！请检查数量和折扣值。");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "发生错误：" + e.getMessage());
		}
	}

	/**
	 * 导出报表事件处理
	 * @param evt
	 * @throws IOException
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private void exportReportSActionPerformed(ActionEvent evt) throws IOException,
			RowsExceededException, WriteException {

		String path = null;//文件路径
		String name = null;//文件名
		int m = 0;
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
		try{
			m = jFileChooser.showOpenDialog(null);
		}
		catch(HeadlessException head){
			System.out.println("Open File Dialog ERROR!");
		}
		if(m == JFileChooser.APPROVE_OPTION){
			//获得该文件
			path = jFileChooser.getSelectedFile().getPath();
		}

		if(path != null){
			name = new SimpleDateFormat("yyyy-MM-dd-hh-mm").format(Calendar.getInstance().getTime());
			// 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件
			//文件命名为当前时间（具体到分钟）+report
			WritableWorkbook writeBook = Workbook.createWorkbook(new File(path+File.separator+name+"-report.xls"));

			// 2、新建工作表(sheet)对象，并声明其属于第几页
			WritableSheet dayProfitSheet = writeBook.createSheet("日销售", 0);// 第一个参数为工作簿的名称，第二个参数为页数
			WritableSheet monthProfitSheet = writeBook.createSheet("月销售", 1);
			WritableSheet yearProfitSheet = writeBook.createSheet("年销售", 2);// 第一个参数为工作簿的名称，第二个参数为页数

			// 3、创建单元格(Label)对象，
			Label biaotou = new Label(0,0,"报表");//表头
			dayProfitSheet.addCell(biaotou);
			dayProfitSheet.mergeCells(0,0, 6, 0);// 参数说明，前两个参数为待合并的起始单元格位置，后两个参数用来指定结束单元格位置（列和行）
			Label label1 = new Label(0, 1, "销售编号");// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容
			dayProfitSheet.addCell(label1);
			Label label2 = new Label(1, 1, "图书编号");
			dayProfitSheet.addCell(label2);
			Label label3 = new Label(2, 1, "进价");
			dayProfitSheet.addCell(label3);
			Label label4 = new Label(3, 1, "实际售价");
			dayProfitSheet.addCell(label4);
			Label label5 = new Label(4, 1, "销售数量");
			dayProfitSheet.addCell(label5);
			Label label6 = new Label(5, 1, "利润");
			dayProfitSheet.addCell(label6);
			Label label7 = new Label(6, 1, "销售日期");
			dayProfitSheet.addCell(label7);


			Connection conn = null;
			int j = 1;
			try{
				conn = dbUtil.getConn();
				ResultSet rs = tradeDao.list(conn, new Trade());
				while(rs.next()){
					int i = 0;
					int id = rs.getInt("id");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(id)));
					int bookId = rs.getInt("bookId");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(bookId)));
					Float priceIn = rs.getFloat("priceIn");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(priceIn)));
					Float realSalePrice = rs.getFloat("realSalePrice");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(realSalePrice)));
					int saleNumber = rs.getInt("saleNumber");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(saleNumber)));
					Float profit = rs.getFloat("profit");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(profit)));
					Date date = rs.getDate("saleDate");
					dayProfitSheet.addCell(new Label(i++, j,String.valueOf(date)));
					j++;
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

			// 4、打开流，开始写文件
			writeBook.write();

			// 5、关闭流
			writeBook.close();

			JOptionPane.showMessageDialog(null, "报表导出成功！");
		}

	}

	/**
	 * 利润计算处理事件
	 * @param evt
	 */
	private void refreshProfitActionPerformed(ActionEvent evt) {
		Trade trade = new Trade();
		Float dayProfit = (float)0;
		Float monthProfit = (float)0;
		Float yearProfit = (float)0;
		Connection conn = null;
		String currentDay = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		java.sql.Date cDay = java.sql.Date.valueOf(currentDay);
		String currentMonth = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
		String currentYear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
		try{
			conn = dbUtil.getConn();
			ResultSet rs = tradeDao.list(conn, trade);
			while(rs.next()){
				Date date = rs.getDate("saleDate");
				Float profit = rs.getFloat("profit");
				if(cDay.equals(date))
					dayProfit += profit;
				String sqlMonth = new SimpleDateFormat("yyyy-MM").format(rs.getDate("saleDate"));
				if(currentMonth.equals(sqlMonth))
					monthProfit += profit;
				String sqlYear = new SimpleDateFormat("yyyy").format(rs.getDate("saleDate"));
				if(currentYear.equals(sqlYear))
					yearProfit += profit;
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
		dayTxt.setText(currentDay);
		monthTxt.setText(currentMonth);
		yearTxt.setText(currentYear);
		dayProfitTxt.setText(String.valueOf(dayProfit));
		monthProfitTxt.setText(String.valueOf(monthProfit));
		yearProfitTxt.setText(String.valueOf(yearProfit));
	}

	/**
	 * 销售报表查询,返回全部，根据输入条件筛选填充表格
	 * @param evt
	 */
	private void tradeTableRefreshActionPerformed(ActionEvent evt) {
		String inputDate = inputDateTxt.getText();
		boolean isValidDate = true;

		if (StringUtil.isNotEmpty(inputDate)) {
			// 检查是否是有效的日期格式
			if (inputDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				// 完整日期格式 yyyy-MM-dd
				java.sql.Date sDate = java.sql.Date.valueOf(inputDate);
				this.fillTradeTable(new Trade(sDate));
			} else if (inputDate.matches("\\d{4}-\\d{2}")) {
				// 年月格式 yyyy-MM
				DefaultTableModel dtm = (DefaultTableModel) tradeTable.getModel();
				dtm.setRowCount(0);
				Connection conn = null;
				try {
					conn = dbUtil.getConn();
					ResultSet rs = tradeDao.list(conn, new Trade());
					while (rs.next()) {
						String sqlMonth = new SimpleDateFormat("yyyy-MM").format(rs.getDate("saleDate"));
						if (inputDate.equals(sqlMonth)) {
							Vector v = new Vector();
							v.add(rs.getInt("id"));
							v.add(rs.getInt("bookId"));
							v.add(rs.getFloat("priceIn"));
							v.add(rs.getFloat("realSalePrice"));
							v.add(rs.getInt("saleNumber"));
							v.add(rs.getFloat("profit"));
							v.add(rs.getDate("saleDate"));
							dtm.addRow(v);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						dbUtil.closeConn(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (inputDate.matches("\\d{4}")) {
				// 年格式 yyyy
				DefaultTableModel dtm = (DefaultTableModel) tradeTable.getModel();
				dtm.setRowCount(0);
				Connection conn = null;
				try {
					conn = dbUtil.getConn();
					ResultSet rs = tradeDao.list(conn, new Trade());
					while (rs.next()) {
						String sqlYear = new SimpleDateFormat("yyyy").format(rs.getDate("saleDate"));
						if (inputDate.equals(sqlYear)) {
							Vector v = new Vector();
							v.add(rs.getInt("id"));
							v.add(rs.getInt("bookId"));
							v.add(rs.getFloat("priceIn"));
							v.add(rs.getFloat("realSalePrice"));
							v.add(rs.getInt("saleNumber"));
							v.add(rs.getFloat("profit"));
							v.add(rs.getDate("saleDate"));
							dtm.addRow(v);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						dbUtil.closeConn(conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				isValidDate = false;
				JOptionPane.showMessageDialog(null, "请输入有效的日期格式：yyyy-mm-dd 或 yyyy-mm 或 yyyy");
			}
		} else {
			// 如果输入为空，显示全部记录
			this.fillTradeTable(new Trade());
		}
	}

	/**
	 * 图书销售事件处理
	 * 更新图书表信息，添加销售记录
	 * @param evt
	 */
	private void bookSaleActionPerformed(ActionEvent evt) {
		if(selectedBooksModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "销售列表为空，请先添加图书！");
			return;
		}

		Connection conn = null;
		boolean originalAutoCommit = true;

		try {
			conn = dbUtil.getConn();
			// 保存原始auto-commit状态
			originalAutoCommit = conn.getAutoCommit();
			// 关闭auto-commit以开启事务
			conn.setAutoCommit(false);

			// 创建订单
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			String orderNo = "SO" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			int discountType = originPrice.isSelected() ? 1 : 2;

			float totalAmount = 0;
			float totalProfit = 0;
			List<Trade> trades = new ArrayList<>();

			// 处理每种图书
			for(int i = 0; i < selectedBooksModel.getRowCount(); i++) {
				int bookId = Integer.parseInt(selectedBooksModel.getValueAt(i, 0).toString());
				int saleNumber = Integer.parseInt(selectedBooksModel.getValueAt(i, 4).toString());
				int discount = Integer.parseInt(selectedBooksModel.getValueAt(i, 5).toString().replace("折", ""));

				Book book = bookDao.getBookById(conn, bookId);
				if(book == null) {
					throw new Exception("图书ID " + bookId + " 不存在");
				}

				if(saleNumber > book.getStockNumber()) {
					throw new Exception("图书《" + book.getBookName() + "》库存不足");
				}

				float realPrice = book.getPriceOut() * discount / 10;
				float profit = (realPrice - book.getPriceIn()) * saleNumber;

				Trade trade = new Trade();
				trade.setBookId(bookId);
				trade.setPriceIn(book.getPriceIn());
				trade.setRealSalePrice(realPrice);
				trade.setSaleNumber(saleNumber);
				trade.setProfit(profit);
				trade.setSaleDate(java.sql.Date.valueOf(currentDate));
				trade.setDiscount(discount);
				trades.add(trade);

				totalAmount += realPrice * saleNumber;
				totalProfit += profit;

				book.setStockNumber(book.getStockNumber() - saleNumber);
				bookDao.update(conn, book);
			}

			SaleOrder order = new SaleOrder(orderNo, totalAmount, totalProfit,
					java.sql.Date.valueOf(currentDate),
					discountType, "批量销售");

			tradeDao.addOrderWithTrades(conn, order, trades);
			conn.commit(); // 提交事务

			JOptionPane.showMessageDialog(null, "销售成功！订单号：" + orderNo);
			selectedBooksModel.setRowCount(0);
			fillBookTable(new Book());
			refreshProfitActionPerformed(null);

			// 新增代码：刷新售书情况表格
			fillTradeTable(new Trade());  // 使用空Trade对象显示所有记录
			// 或者根据当前日期筛选
			// fillTradeTable(new Trade(java.sql.Date.valueOf(currentDate)));

		} catch(Exception e) {
			try {
				if(conn != null) {
					conn.rollback(); // 回滚事务
				}
				JOptionPane.showMessageDialog(null, "销售失败：" + e.getMessage());
				e.printStackTrace();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if(conn != null) {
					// 恢复原始auto-commit状态
					conn.setAutoCommit(originalAutoCommit);
					dbUtil.closeConn(conn);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
	}

	/**
	 * 初始化图书表格数据
	 * @param book
	 */
	private void fillBookTable(Book book){
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
	 * 图书查询事件处理
	 * @param e
	 */
	private void bookSearchActionPerformed(ActionEvent e) {
		String bookName = this.bookNameTxt.getText();
		Book book = new Book(bookName);
		this.fillBookTable(book);
	}

	/**
	 * 初始化销售表格数据
	 * @param trade
	 */
	private void fillTradeTable(Trade trade){
		DefaultTableModel dtm =  (DefaultTableModel)tradeTable.getModel();
		dtm.setRowCount(0);//设置成0行
		Connection conn = null;
		try{
			conn = dbUtil.getConn();
			ResultSet rs = tradeDao.list(conn, trade);
			while(rs.next()){
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(rs.getInt("bookId"));
				v.add(rs.getFloat("priceIn"));
				v.add(rs.getFloat("realSalePrice"));
				v.add(rs.getInt("saleNumber"));
				v.add(rs.getFloat("profit"));
				v.add(rs.getDate("saleDate"));
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
	 * 表格行点击事件处理
	 * @param evt
	 */
	private void bookTableMousePressed(MouseEvent evt) {
		int row = bookTable.getSelectedRow();
		if (row >= 0) {
			bookNameTxt.setText((String)bookTable.getValueAt(row, 1));
			bookNumTxt.setText("1");
		}
	}
}