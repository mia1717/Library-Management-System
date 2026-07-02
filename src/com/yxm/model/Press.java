package com.yxm.model;

/**
 * 出版社实体
 *
 */
public class Press {

    private int id; // 编号
    private String pressName; // 出版社名称
    private String pressAddress; // 出版社地址
    private String pressPhone; // 出版社电话

    public Press() {
        super();
    }

    public Press(String pressName) { // 添加的构造器
        this.pressName = pressName;
    }

    public Press(String pressName, String pressAddress, String pressPhone) {
        super();
        this.pressName = pressName;
        this.pressAddress = pressAddress;
        this.pressPhone = pressPhone;
    }

    public Press(int id, String pressName, String pressAddress, String pressPhone) {
        super();
        this.id = id;
        this.pressName = pressName;
        this.pressAddress = pressAddress;
        this.pressPhone = pressPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    public String getPressAddress() {
        return pressAddress;
    }

    public void setPressAddress(String pressAddress) {
        this.pressAddress = pressAddress;
    }

    public String getPressPhone() {
        return pressPhone;
    }

    public void setPressPhone(String pressPhone) {
        this.pressPhone = pressPhone;
    }
}