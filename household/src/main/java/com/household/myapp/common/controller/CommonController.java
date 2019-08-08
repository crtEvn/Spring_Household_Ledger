package com.household.myapp.common.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.household.myapp.common.common.CommandMap;
import com.household.myapp.common.service.CommonService;

@Controller
public class CommonController {
	
	protected Log log = LogFactory.getLog(CommonController.class);
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(CommandMap commandMap, HttpServletResponse response) throws Exception{
		
		Map<String,Object> map = commonService.selectFileInfo(commandMap.getMap());
		
		String storedFileName = (String)map.get("STORED_FILE_NAME");
		String originalFileName = (String)map.get("ORIGINAL_FILE_NAME");
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\dev\\file\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	/* <설명>
	 * 
	 * response.setContentType("application/octet-stream");
	 * -> 웹서버는 브라우저로 전송될 페이지가 html 인경우 text/html을 표준 MIME 타입으로 지정함.
	 *    그러나 필요에 의해서 이 MIME 타입을 변경하고자 할 경우 또는 캐릭터의 인코딩셋을 변경하고자 할때 setContentType 메소드를 사용.
	 *    octet-stream은 이름 그대로 8비트 바이너리 배열을 의미하며 http나 이메일상에서 application 형식이 지정되지 않았거나 형식을 모를때 사용.
	 *    결국 브라우저는 octet-stream 으로 MIME 타입이 지정된 경우 단지 바이너리 데이터로서 다운로드만 가능하게 처리하게 됨.
	 *    
	 * response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
	 * -> Content-Disposition 속성을 이용하여 데이터의 형식을 지정. attachment로 지정되어 있는건 첨부 파일을 의미
	 *    attachment로 지정되어 있는건 첨부 파일을 의미
	 *    fileName이라고 적혀있는 것은 다운로드 할 때 기본으로 적혀있던 파일 이름
	 *    URLEncoder.encode 부분은 한글파일의 경우 이름이 깨지기 때문에 UTF-8로 인코딩
	 *    attachment;와 fileName 사이에는 띄워쓰기를 꼭 해주어야 하고 인코드하는 뒤 부분 "\" 부분도 꼭 써줘야 다운로드가 가능
	 *    
	 * response.setHeader("Content-Transfer-Encoding", "binary"); 
	 * -> Content-Transfer-Encoding 는 전송되는 데이터의 안의 내용물들의 인코딩 방식을 말하며, 여기에선 binary 방식을 택함
	 * 
	 * response.getOutputStream().write(fileByte);
	 * -> 위에서 byte[] 타입으로 변환한 파일을 response를 통해 클라이언트로 보내준다.
	 * 
	 * response.getOutputStream().flush();
	 * response.getOutputStream().close();
	 * -> response를 중지하고 닫아준다.
	 */

	
}
