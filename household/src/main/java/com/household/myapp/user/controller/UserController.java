package com.household.myapp.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.household.myapp.common.common.CommandMap;
import com.household.myapp.sample.controller.SampleController;
import com.household.myapp.sample.service.SampleService;
import com.household.myapp.user.service.UserService;


@Controller
public class UserController {
	
	protected Log log = LogFactory.getLog(SampleController.class);
	
	@Resource(name="userService")
    private UserService userService;
	
    // 로그인 페이지
    @RequestMapping(value="/user/openSignIn.do")
    public ModelAndView openSignIn(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/user/signIn");
        return mv;
    }
    
    // 로그인 기능
    
    // 회원가입 페이지
    @RequestMapping(value="/user/openSignUp.do")
    public ModelAndView openSignUn(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/user/signUp");
        
        mv.addObject("error", commandMap.get("error"));
        
        return mv;
    }
    
    // 회원가입 기능
    @RequestMapping(value="/user/signUp.do")
    public ModelAndView signUp(CommandMap commandMap) throws Exception{
    	
    	String error = userService.signUp(commandMap.getMap());
    	ModelAndView mv = null;
    	
    	if(error.equals("완료")) {
    		// main 페이지로 이동
    		mv = new ModelAndView("redirect:/user/openSignIn.do");
    	} else {
    		mv = new ModelAndView("redirect:/user/openSignUp.do");
    		mv.addObject("error",error);
    	}
                 
        return mv;
    }
    

}
