package com.yxm.model;

import java.util.Date;

public class Trade {

	private int id;//编号
	private Integer bookId;//图书编号
	private Float priceIn;//图书进价
	private Float realSalePrice;//图书实际售价
	private Integer saleNumber;//销售数量
	private Float profit;//利润
	private Date saleDate;//销售时间
	private Integer discount;//折扣(10表示原价，其他值表示折扣比例，如8表示8折)
	private Integer orderId;//订单ID，关联t_sale_order表

	public Trade(Integer bookId, Float priceIn, Float realSalePrice, Integer saleNumber, Float profit,
				 Date saleDate) {
		super();
		this.bookId = bookId;
		this.priceIn = priceIn;
		this.realSalePrice = realSalePrice;
		this.saleNumber = saleNumber;
		this.profit = profit;
		this.saleDate = saleDate;
	}

	/**
	 * 查询日月年报表用到
	 * @param saleDate
	 */
	public Trade(Date saleDate) {
		super();
		this.saleDate = saleDate;
	}

	public Trade() {
		super();
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Float getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(Float priceIn) {
		this.priceIn = priceIn;
	}
	public Float getProfit() {
		return profit;
	}
	public void setProfit(Float profit) {
		this.profit = profit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Float getRealSalePrice() {
		return realSalePrice;
	}
	public void setRealSalePrice(Float realSalePrice) {
		this.realSalePrice = realSalePrice;
	}
	public Integer getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
}