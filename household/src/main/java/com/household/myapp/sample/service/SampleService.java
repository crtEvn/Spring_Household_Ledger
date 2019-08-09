package com.household.myapp.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SampleService {

	List<Map<String,Object>> selectBoardList(Map<String,Object> map) throws Exception;
	
	void writeBoard(Map<String,Object> map, HttpServletRequest req) throws Exception;
	
	Map<String,Object> selectBoard(Map<String,Object> map)throws Exception;
	
	void modifyBoard(Map<String,Object> map, HttpServletRequest request) throws Exception;
	
	void deleteBoard(Map<String,Object> map) throws Exception;
}
