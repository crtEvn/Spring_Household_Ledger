package com.household.myapp.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.household.myapp.common.dao.AbstractDAO;


@Repository("userDAO")
public class UserDAO extends AbstractDAO{
	
	private Log log = LogFactory.getLog(UserDAO.class);
	
	// 회원가입
	public void insertUser(Map<String,Object> map) throws Exception{
	    insert("user.insertUser", map);
	}
	
	// ID 중복 체크
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectUserID(Map<String,Object> map) {
		return (Map<String,Object>)selectOne("user.selectUserID", map);
	}
	
	// Email 중복 체크
	public String selectUserEmail(Map<String,Object> map) {
		return (String)selectOne("user.selectUserEmail",map);
	}
	


}
