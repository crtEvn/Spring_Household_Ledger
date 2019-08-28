package com.household.myapp.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.household.myapp.common.common.CommandMap;
import com.household.myapp.sample.controller.SampleController;
import com.household.myapp.sample.service.SampleService;
import com.household.myapp.user.service.UserService;
import com.household.myapp.user.spring.UserInfo;


@Controller
public class UserController {
	
	protected Log log = LogFactory.getLog(SampleController.class);
	
	@Resource(name="userService")
    private UserService userService;
	
    // 로그인 페이지
    @RequestMapping(value="/user/openSignIn.do")
    public ModelAndView openSignIn(CommandMap commandMap) throws Exception{
        ModelAndView mv = new ModelAndView("/user/signIn");
        
        mv.addObject("login_error", commandMap.get("login_error"));
        
        return mv;
    }
    
    // 로그인 기능
    @RequestMapping(value="/user/signIn.do")
    public ModelAndView signIn(CommandMap commandMap, HttpSession session) throws Exception{
    	
    	ModelAndView mv = null;
    	UserInfo userInfo = userService.signIn(commandMap.getMap());
    	
    	if(!userInfo.isLogin_error()) {
    		session.setAttribute("userInfo", userInfo);
    		mv = new ModelAndView("redirect:/main/main.do");
    	}else {
    		mv = new ModelAndView("redirect:/user/openSignIn.do");
    		mv.addObject("login_error", userInfo.isLogin_error());
    	}
    	
    	return mv;
    }
    
    // 로그아웃 기능
    @RequestMapping(value="/user/logOut.do")
    public ModelAndView logOut(CommandMap commandMap, HttpSession session) throws Exception{
    	
    	ModelAndView mv = new ModelAndView("redirect:/user/openSignIn.do");
    	session.invalidate();
    	
    	return mv;
    }
    
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
