package com.household.myapp.common.resolver;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.household.myapp.common.common.CommandMap;

/* 
 * 
 * supportsParameter 메서드:
 * 	- Resolver가 적용 가능한지 검사하는 역할.
 * 	- 컨트롤러의 파라미터가 CommandMap 클래스인지 검사
 * 	- 바인딩할 클래스를 지정해주는 곳. 여기서 지정한 클래스를 resolveArgument 메소드에서 처리
 * resolverArgument 메서드:
 * 	- 파라미터와 기타 정보를 받아서 실제 객체를 반환
 * 	- 바인딩된 클래스의 파라미터들을 수정하거나 값을 가져옴
 * 	- CommandMap 클래스를 새로 만들어 파라미터 안의 값들을 CommandMap에 할당하고 그 값을 리턴
 * 
 */

public class CustomMapArgumentResolver implements HandlerMethodArgumentResolver {
	
	private Log log = LogFactory.getLog(CustomMapArgumentResolver.class);
	
    @Override
    public boolean supportsParameter(MethodParameter para) {
        return CommandMap.class.isAssignableFrom(para.getParameterType());
    }
	
	@Override
    public Object resolveArgument(MethodParameter para, ModelAndViewContainer mvc, NativeWebRequest webReq,
            WebDataBinderFactory binderFactory) throws Exception {
         
        CommandMap commandMap = new CommandMap();
         
        HttpServletRequest req = (HttpServletRequest)webReq.getNativeRequest();
        Enumeration<?> enumeration = req.getParameterNames();
         
        String key = null;
        String[] values = null;
        
        // request에 있는 값을 iterator를 이용해  하나씩 가져옴
        while(enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            values = req.getParameterValues(key);
            
            // request에 담겨있는 모든 Key, Value 값을 commandMap에 저장
            if(values!=null ) {
                commandMap.put(key, (values.length>1)? values : values[0]);
            }
        }
         
        return commandMap;
    }
	
}
