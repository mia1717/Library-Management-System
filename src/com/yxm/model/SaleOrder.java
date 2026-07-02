package com.yxm.model;

import java.util.Date;
import java.util.List;

public class SaleOrder {
    private int id;
    private String orderNo;
    private float totalAmount;
    private float totalProfit;
    private Date saleDate;
    private int discountType; // 1原价/2折扣价
    private String remark;
    private List<Trade> tradeList;

    // 构造方法、getter和setter
    public SaleOrder() {}

    public SaleOrder(String orderNo, float totalAmount, float totalProfit,
                     Date saleDate, int discountType, String remark) {
        this.orderNo = orderNo;
        this.totalAmount = totalAmount;
        this.totalProfit = totalProfit;
        this.saleDate = saleDate;
        this.discountType = discountType;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(float totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Trade> getTradeList() {
        return tradeList;
    }

    public void setTradeList(List<Trade> tradeList) {
        this.tradeList = tradeList;
    }
}