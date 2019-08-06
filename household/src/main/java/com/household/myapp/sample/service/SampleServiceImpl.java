package com.household.myapp.sample.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.household.myapp.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	
	@Resource(name="sampleDAO")
    private SampleDAO sampleDAO;
     
    @Override
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
         
    	return sampleDAO.selectBoardList(map);
    }
    
    public void writeBoard(Map<String, Object> map) throws Exception {
        sampleDAO.writeBoard(map);
    }
    
    @Override
    public Map<String, Object> selectBoard(Map<String, Object> map) throws Exception {
        sampleDAO.updateHitCnt(map);
        return sampleDAO.selectBoard(map); 
    }
    
    @Override
    public void modifyBoard(Map<String, Object> map) throws Exception {
        sampleDAO.modifyBoard(map);
    }
    
    @Override
    public void deleteBoard(Map<String, Object> map) throws Exception {
        sampleDAO.deleteBoard(map);
    }
    
}
