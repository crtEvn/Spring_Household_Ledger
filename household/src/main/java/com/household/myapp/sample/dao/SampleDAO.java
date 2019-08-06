package com.household.myapp.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.household.myapp.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO{
	
	private Log log = LogFactory.getLog(SampleDAO.class);
	
	@SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		
		log.debug("SampleDAO > selectBoardList");
		
        return (List<Map<String,Object>>)selectList("sample.selectBoardList", map);
    }
	
	public void writeBoard(Map<String,Object> map) {
	    insert("sample.writeBoard", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> selectBoard(Map<String,Object> map){
		return (Map<String,Object>)selectOne("sample.selectBoard",map);
	}

	public void updateHitCnt(Map<String,Object> map) {
		update("sample.updateHitCnt",map);
	}
	
	public void modifyBoard(Map<String,Object> map) {
	    update("sample.modifyBoard",map);
	}
	
	public void deleteBoard(Map<String, Object> map) {
	    update("sample.deleteBoard",map);
	}
}
