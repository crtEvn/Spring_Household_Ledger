package com.household.myapp.common.service;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.household.myapp.common.dao.CommonDAO;

@Service("commonService")
public class CommonServiceImpl implements CommonService{
	
	private Log log = LogFactory.getLog(CommonServiceImpl.class);
	
	@Resource(name="commonDAO")
	private CommonDAO commonDAO;

	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return commonDAO.selectFileInfo(map);
	}

}
