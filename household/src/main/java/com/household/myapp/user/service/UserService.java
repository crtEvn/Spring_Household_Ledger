package com.household.myapp.user.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
	
	// 회원가입 기능
	String signUp(Map<String,Object> map) throws Exception;
	
}
