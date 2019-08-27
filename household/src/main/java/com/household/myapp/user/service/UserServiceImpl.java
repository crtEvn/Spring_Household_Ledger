package com.household.myapp.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.household.myapp.sample.controller.SampleController;
import com.household.myapp.user.dao.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	protected Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource(name="userDAO")
    private UserDAO userDAO;
	
	// 회원가입 기능
	public String signUp(Map<String, Object> map) throws Exception{
		
		if(!(userDAO.selectUserID(map)==null)) {
			log.debug("아이디 중복 검사");
			return "이미 사용중인 아이디 입니다.";
		}else if(Integer.parseInt(userDAO.selectUserEmail(map))>3) {
			log.debug("이메일 중복 검사 : "+userDAO.selectUserEmail(map));
			return "이메일 최대 가입 횟수(3회)를 초과하였습니다";
		}else {
			userDAO.insertUser(map);
			log.debug("회원가입 성공");
			return "회원가입 성공";
		}
	}
	
	
	
}
