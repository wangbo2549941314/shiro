package com.ysd.entity;

import org.springframework.stereotype.Component;

@Component
public class User {
	private Integer id;
	private String name;
	private String password;
	private String quanxian;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, String password, String quanxian) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.quanxian = quanxian;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuanxian() {
		return quanxian;
	}
	public void setQuanxian(String quanxian) {
		this.quanxian = quanxian;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", quanxian=" + quanxian + "]";
	}
	
}
