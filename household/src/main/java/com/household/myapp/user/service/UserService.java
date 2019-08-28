package com.household.myapp.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.household.myapp.user.spring.UserInfo;

public interface UserService {
	
	// 회원가입 기능
	String signUp(Map<String,Object> map) throws Exception;
	
	// 로그인 기능
	UserInfo signIn(Map<String,Object> map) throws Exception;
	
}
