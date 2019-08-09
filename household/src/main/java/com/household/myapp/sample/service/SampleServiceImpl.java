package com.household.myapp.sample.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.household.myapp.common.util.FileUtils;
import com.household.myapp.sample.controller.SampleController;
import com.household.myapp.sample.dao.SampleDAO;

@Service("sampleService")
public class SampleServiceImpl implements SampleService{
	
	protected Log log = LogFactory.getLog(SampleServiceImpl.class);
	
	@Resource(name="sampleDAO")
    private SampleDAO sampleDAO;
	
	@Resource(name="fileUtils")
	private FileUtils fileUtils;

    @Override
    public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
         
    	return sampleDAO.selectBoardList(map);
    }
    
	/*
	 * public void writeBoard(Map<String, Object> map) throws Exception {
	 * sampleDAO.writeBoard(map); }
	 */
    
    @Override
    public Map<String, Object> selectBoard(Map<String, Object> map) throws Exception {
        sampleDAO.updateHitCnt(map);
        
        Map<String, Object> resultMap = new HashMap<String,Object>();
        resultMap.put("map", sampleDAO.selectBoard(map));
        
        List<Map<String,Object>> list = sampleDAO.selectFileList(map);
        resultMap.put("list", list);
        
        return resultMap; 
    }
    
    @Override
    public void modifyBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
        sampleDAO.modifyBoard(map);
        sampleDAO.deleteFileList(map);
        List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
        Map<String,Object> tempMap = null;

        for(int i=0, size=list.size(); i<size; i++){
        	tempMap = list.get(i);
        	if(tempMap.get("IS_NEW").equals("Y")){
        		sampleDAO.insertFile(tempMap);
        	} else {
        		sampleDAO.updateFile(tempMap);
        	}
        }

    }
    
    @Override
    public void deleteBoard(Map<String, Object> map) throws Exception {
        sampleDAO.deleteBoard(map);
    }
    
    @Override
    public void writeBoard(Map<String, Object> map, HttpServletRequest request) throws Exception {
        
    	sampleDAO.writeBoard(map);
        
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map,request);
		
		for(int i=0; i<list.size();i++) {
			sampleDAO.insertFile(list.get(i));
		}
		 
          
		/* // log 확인하는 코드
		 * MultipartHttpServletRequest multipartHttpServletRequest =
		 * (MultipartHttpServletRequest)request; Iterator<String> iterator =
		 * multipartHttpServletRequest.getFileNames(); MultipartFile multipartFile =
		 * null;
		 * 
		 * while(iterator.hasNext()){ multipartFile =
		 * multipartHttpServletRequest.getFile(iterator.next());
		 * 
		 * if(multipartFile.isEmpty() == false){
		 * log.debug("------------- file start -------------");
		 * log.debug("name : "+multipartFile.getName());
		 * log.debug("filename : "+multipartFile.getOriginalFilename());
		 * log.debug("size : "+multipartFile.getSize());
		 * log.debug("-------------- file end --------------\n"); } }
		 */
    }
}
