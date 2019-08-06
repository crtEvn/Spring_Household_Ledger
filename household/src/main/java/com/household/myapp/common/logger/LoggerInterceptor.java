package com.household.myapp.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	/* Interceptor : spring의 controller 호출 전에 동작하는 방식. 보통 로그인 체크 유무가 필요한 곳에 사용
	 * HandlerInterceptorAdapter를 상속 받음
	 */

	// Log4j의 Log 객체를 log라는 이름으로 생성
    protected Log log = LogFactory.getLog(LoggerInterceptor.class);
    
    // preHandle(): 전처리. 클라이언트로 부터 컨트롤러가 요청을 받아올 때 실행됨
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("======================================          START         ======================================");
            log.debug(" Request URI \t:  " + request.getRequestURI());
        }
        return super.preHandle(request, response, handler);
    }
    
    // postHandle(): 후처리. 컨트롤러가 클라이언트에 응답을 보낸 때 실행됨
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("======================================           END          ======================================\n");
        }
    }
}
