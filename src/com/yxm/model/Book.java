package com.yxm.model;

/**
 * 图书实体
 *
 */
public class Book {

	private int id;//编号
	private String bookName;//图书名称
	private String author;//作者
	private String Press;//出版社
	private Float priceIn;//图书进价
	private Float priceOut;//图书售价
	private Integer stockNumber;//图书数量
	private Integer discount;//图书折扣
	private Integer bookTypeId;//图书类别Id
	private String bookTypeName;//图书类别名称
	private String bookDesc;//图书简介
	

	/**
	 * 查询统计模块：图书查询用到QueryAll
	 * @param bookName
	 */
	public Book(String bookName) {
		super();
		this.bookName = bookName;
	}
	
	
	/**
	 * 查询统计模块：分类查询用到QueryByType
	 * @param bookTypeId
	 */
	public Book(Integer bookTypeId) {
		super();
		this.bookTypeId = bookTypeId;
	}

	public Book( String bookName, String author, String press, Float priceIn, Float priceOut,
			Integer stockNumber, Integer discount, Integer bookTypeId,String bookDesc) {
		super();
		this.bookName = bookName;
		this.author = author;
		Press = press;
		this.priceIn = priceIn;
		this.priceOut = priceOut;
		this.stockNumber = stockNumber;
		this.discount = discount;
		this.bookTypeId = bookTypeId;
		this.bookDesc = bookDesc;
	}

	public Book(int id, String bookName, String author, String press, Float priceIn, Float priceOut,
			Integer stockNumber,Integer discount, Integer bookTypeId, String bookDesc) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		Press = press;
		this.priceIn = priceIn;
		this.priceOut = priceOut;
		this.stockNumber = stockNumber;
		this.discount = discount;
		this.bookTypeId = bookTypeId;
		this.bookDesc = bookDesc;
	}
	
	public Book(String bookName, String author, Integer bookTypeId) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.bookTypeId = bookTypeId;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Float getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(Float priceIn) {
		this.priceIn = priceIn;
	}
	public Float getPriceOut() {
		return priceOut;
	}
	public void setPriceOut(Float priceOut) {
		this.priceOut = priceOut;
	}
	public Integer getStockNumber() {
		return stockNumber;
	}
	public void setStockNumber(Integer stockNumber) {
		this.stockNumber = stockNumber;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return Press;
	}
	public void setPress(String press) {
		Press = press;
	}
	
	public Integer getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
	
	
}
