package com.household.myapp.user.spring;

import java.sql.Timestamp;

public class UserInfo {
	
	// 멤버 변수
	private int user_idx;
	private String user_id;
	private String user_pw;
	private String user_email;
	private String user_state;
	private Timestamp reg_date;
	private int cnt_visit;
	private boolean login_error;
	
	// UserInfo Method
	public UserInfo(boolean login_error) {
		this.login_error=login_error;
	}
	public UserInfo(int user_idx, String user_id, String user_email) {
		this.user_idx = user_idx;
		this.user_id = user_id;
		this.user_email = user_email;
	}
	
	// getter & setter method
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getCnt_visit() {
		return cnt_visit;
	}
	public void setCnt_visit(int cnt_visit) {
		this.cnt_visit = cnt_visit;
	}
	public boolean isLogin_error() {
		return login_error;
	}
	public void setLogin_error(boolean login_error) {
		this.login_error = login_error;
	}
	
	
	

}
