<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>상세보기</title>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
    <table class="board_view">
        <caption>상세보기</caption>
        <colgroup>
            <col width="15%">
            <col width="35%">
            <col width="15%">
            <col width="*">
        </colgroup>
         
        <tbody>
            <tr>
                <th>제목</th>
                <td>${map.TITLE}</td>
                <th>조회수</th>
                <td>${map.HIT_CNT }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${map.CREA_ID }</td>
                <th>작성시간</th>
                <td>${map.CREA_DTM }</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    ${map.CONTENTS }
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <c:forEach items="${list}" var="row" >
                            <input type="hidden" value="${row.IDX }" id="IDX">
                            <a href="#this" name="file">${row.ORIGINAL_FILE_NAME }</a>
                            (${row.FILE_SIZE }Byte)
                    </c:forEach>
                </td>                          
            </tr>
        </tbody>
    </table>
    
    <a href="#this" id="list" class="btn">목록으로</a>
    <a href="#this" id="modify" class="btn">수정하기</a>
    
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
     
    <script type="text/javascript">
        $(document).ready(function(){
            $("#list").on("click",function(e){ // '목록으로' 버튼
                e.preventDefault();
                fn_openBoardList();
            })
            $("#modify").on("click",function(e){ // '수정하기' 버튼
                e.preventDefault();
                fn_openBoardModify();
            })
            $("a[name='file']").on("click",function(e){
                e.preventDefault();
                fn_fileDownload($(this));
            })
        })
         
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardList.do'/>");
            comSubmit.submit();
        }
        function fn_openBoardModify(){
            var idx = "${map.IDX}";
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardModify.do'/>");
            comSubmit.addParam("IDX",idx);
            comSubmit.submit();
        }
        function fn_fileDownload(obj){
        	var idx = obj.parent().find("#IDX").val();
        	var comSubmit = new ComSubmit();
        	comSubmit.setUrl("<c:url value='/common/downloadFile.do' />");
        	comSubmit.addParam("IDX", idx);
        	comSubmit.submit();
        	$("#commonForm").children().remove(); //다시 다운로드시, 방금 추가된 param 삭제
        }
    </script> 
</body>
</html>