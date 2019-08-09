package com.household.myapp.sample.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.household.myapp.common.common.CommandMap;
import com.household.myapp.sample.service.SampleService;



@Controller // 해당 class가 Controller라고 말해주는 어노테이션
public class SampleController {
	
    protected Log log = LogFactory.getLog(SampleController.class);
    //Logger log = Logger.getLogger(this.getClass());
    //Logger log = LoggerFactory.getLogger(this.getClass());
    
    // 클라이언트의 요청을 매핑하는 부분
    @RequestMapping(value="/testInterceptor.do")
    public ModelAndView testInterceptor(Map<String, Object> commandMap) throws Exception{
    	
        ModelAndView mv = new ModelAndView("");
        log.debug("인터셉터 테스트");
         
        return mv;
    }
    
    @Resource(name="sampleService")
    private SampleService sampleService;
    
    @RequestMapping(value="/sample/openBoardList.do")
    public ModelAndView openBoardList(Map<String, Object> commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardList");
        log.debug("보드 리스트 페이지");
        
        List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
        mv.addObject("list", list);
         
        return mv;
    }
    
    @RequestMapping(value="/sample/testMapArgumentResolver.do")
    public ModelAndView testMapArgumentResolver(CommandMap commandMap) throws Exception{
    	ModelAndView mv = new ModelAndView("");
    	log.debug("MapArgumentResolver 테스트");
    	
    	if(commandMap.isEmpty() == false){
    		Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
    		Entry<String,Object> entry = null; while(iterator.hasNext()){
    			entry = iterator.next();
    			log.debug("key : "+entry.getKey()+", value : "+entry.getValue()); 
    		}
    	}
    	return mv;
    }
    
    @RequestMapping(value="/sample/openBoardWrite.do")
    public ModelAndView openBoardWrite(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardWrite");
         
        return mv;          
    }
     
    @RequestMapping(value="/sample/writeBoard.do")
    public ModelAndView writeBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
        sampleService.writeBoard(commandMap.getMap(), request);
                 
        return mv;
    }
    
    @RequestMapping(value="/sample/openBoardDetail.do")
    public ModelAndView openBoardDetail(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardDetail");
        Map<String,Object> map = sampleService.selectBoard(commandMap.getMap());
        mv.addObject("map", map.get("map"));
        mv.addObject("list", map.get("list"));
        return mv;
    }
    
    @RequestMapping(value="/sample/openBoardModify.do")
    public ModelAndView openBoardModify(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/sample/boardModify");
        Map<String,Object> map = sampleService.selectBoard(commandMap.getMap());
        mv.addObject("map",map.get("map"));
        mv.addObject("list",map.get("list"));
        return mv;
    }
     
    @RequestMapping(value="/sample/modifyBoard.do")
    public ModelAndView modifyBoard(CommandMap commandMap, HttpServletRequest request) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/sample/openBoardDetail.do");
        sampleService.modifyBoard(commandMap.getMap(), request);
        
        mv.addObject("IDX", commandMap.get("IDX"));
        return mv;
    }
    
    @RequestMapping(value="/sample/deleteBoard.do")
    public ModelAndView deleteBoard(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("redirect:/sample/openBoardList.do");
        sampleService.deleteBoard(commandMap.getMap());
         
        return mv;
    }

}
