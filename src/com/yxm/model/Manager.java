package com.yxm.model;

/**
 * 管理员实体
 *
 */
public class Manager {
	
	private int id;//编号
	private String managerName;//管理员名
	private String managerPassword;//密码
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Manager(String managerName, String managerPassword) {
		super();
		this.managerName = managerName;
		this.managerPassword = managerPassword;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	
}
