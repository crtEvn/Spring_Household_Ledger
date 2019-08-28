package com.household.myapp.user.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.household.myapp.sample.controller.SampleController;
import com.household.myapp.user.dao.UserDAO;
import com.household.myapp.user.spring.UserInfo;

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
	
	// 로그인 기능
	public UserInfo signIn(Map<String,Object> map) throws Exception{
		
		UserInfo userInfo = null;
		Map<String,Object> userMap = userDAO.selectUserID(map);

		if(userMap != null) { // id 일치
			if(userMap.get("user_pw").equals(map.get("user_pw").toString())) {
				// pw 일치
				userInfo = new UserInfo(Integer.parseInt(userMap.get("user_idx").toString()), userMap.get("user_id").toString(), userMap.get("user_email").toString());
				userInfo.setLogin_error(false);
				
				return userInfo;
			}
		}
		
		// id or pw 불일치
		userInfo = new UserInfo(true);
		return userInfo;
	}
	
	
	
}
