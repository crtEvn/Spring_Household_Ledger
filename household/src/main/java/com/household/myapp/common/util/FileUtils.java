package com.household.myapp.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.household.myapp.common.dao.AbstractDAO;
import com.household.myapp.sample.service.SampleServiceImpl;

@Component("fileUtils")
public class FileUtils {
	
	protected Log log = LogFactory.getLog(FileUtils.class);
	
	private static final String filePath = "C:\\dev\\file\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
		
		log.debug("파일 저장 경로"+filePath);
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();
		Map<String, Object> fileListMap = null;
		
		String boardIdx = (String)map.get("IDX").toString();
		log.debug("boardIdx: "+boardIdx);
		File file = new File(filePath);
		
		if(file.exists() == false){
			file.mkdirs();
		}
		
		while(iterator.hasNext()){
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			
			if(multipartFile.isEmpty() == false){
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				fileListMap = new HashMap<String,Object>();
				fileListMap.put("BOARD_IDX", boardIdx);
				fileListMap.put("ORIGINAL_FILE_NAME", originalFileName);
				fileListMap.put("STORED_FILE_NAME", storedFileName);
				fileListMap.put("FILE_SIZE", multipartFile.getSize());
				
				fileList.add(fileListMap);
			}
		}
		
		return fileList;
	}
}
