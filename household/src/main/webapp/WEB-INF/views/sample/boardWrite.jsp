<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html>
<head>
<title>글쓰기</title>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
    <form id="frm" name="frm" enctype="multipart/form-data">
        <table class="board_view">
            <colgroup>
                <col width="15%" >
                <col width="*" >
            </colgroup>
            <caption>게시글 작성</caption>
            <tbody>
                <tr>
                    <th scope="row">제목</th>
                    <td><input type="text"  name="TITLE" class="wdp_90" /></td>
                </tr>
                <tr>
                    <th scope="row">내용</th>
                    <td><textarea cols="100" rows="20" id="CONTENTS" name="CONTENTS" title="내용"></textarea></td>
                </tr>
            </tbody>
        </table>
        
        <div id="fileDiv">
            <p>
                <input type="file" name="file_0"/>
                <a href="#this" class="btn" id="delete" name="delete">삭제</a>
            </p> 
        </div>
        
        <a href="#this" id="addFile" class="btn">파일 추가하기</a>
        <a href="#this" id="list" class="btn">목록으로</a>
        <a href="#this" id="write" class="btn">글쓰기</a>
    </form>
    <%@ include file="/WEB-INF/include/include-body.jspf" %>  
     
    <script type="text/javascript">
    	
    	var gfv_count = 1; // 파일 업로드 카운트
    
        $(document).ready(function(){
            $("#list").on("click",function(e){ // 목록으로 버튼
                e.preventDefault();
                fn_openBoardList();
            })
            $("#write").on("click",function(e){ // 글쓰기 버튼
                e.preventDefault();
                fn_writeBoard();
            })
            $("#addFile").on("click", function(e){ //파일 추가 버튼
            	e.preventDefault();
            	fn_addFile();
            });
            $("a[name='delete']").on("click", function(e){ //삭제 버튼
            	e.preventDefault();
            	fn_deleteFile($(this));
            });

        });
		   
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardList.do'/>");
            comSubmit.submit();
        }
         
        function fn_writeBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/sample/writeBoard.do'/>");
            comSubmit.submit();
        }
        
        function fn_addFile(){
        	var str = "<p><input type='file' name='file_"+(gfv_count++)+"'><a href='#this' class='btn' name='delete'>삭제</a></p>";
        	$("#fileDiv").append(str);
        	$("a[name='delete']").on("click", function(e){ //삭제 버튼
        		e.preventDefault(); fn_deleteFile($(this));
        	});
		}
        
        function fn_deleteFile(obj){
        	obj.parent().remove();
        }
    </script>
</body>
</html>