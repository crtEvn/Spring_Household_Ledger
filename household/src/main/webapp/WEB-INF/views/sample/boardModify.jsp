<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>글 수정하기</title>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
</head>
<body>
    <form id="frm">
        <table class="board_view">
            <caption>글 수정하기</caption>
            <colgroup>
                <col width="15%">
                <col width="*">
            </colgroup>
            <tbody>
                <tr>
                    <th scope="row">글 번호</th>
                    <td>
                    	${map.IDX }
                    	<input type="hidden" id="IDX" name="IDX" value="${map.IDX }">
                    </td>
                    <th scope="row">조회수</th>
                    <td>${map.HIT_CNT }</td>
                </tr>
                <tr>
                    <th scope="row">작성자</th>
                    <td>${map.CREA_ID }</td>
                    <th scope="row">작성시간</th>
                    <td>${map.CREA_DTM }</td>
                </tr>
                <tr>
                    <th scope="row">제목</th>
                    <td colspan="3">
                        <input type="text" id="TITLE" name="TITLE" class="wdp_90" value="${map.TITLE }"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row">내용</th>
                    <td colspan="3" class="view_text">
                        <textarea rows="20" cols="100" title="내용" id="CONTENTS" name="CONTENTS">${map.CONTENTS }</textarea>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
    
    <a href="#this" id="list" class="btn">목록으로</a>
    <a href="#this" id="modify" class="btn">수정하기</a>
    <a href="#this" id="delete" class="btn">삭제하기</a>
     
    <%@ include file="/WEB-INF/include/include-body.jspf" %>
    
    <script type="text/javascript">
        $(document).ready(function(){
            $("#modify").on("click",function(e){ // '수정하기' 버튼
                e.preventDefault();
                fn_modifyBoard();
            })
            $("#list").on("click",function(e){ // '목록으로' 버튼
                e.preventDefault();
                fn_openBoardList();
            })
            $("#delete").on("click",function(e){ // '삭제하기' 버튼
                e.preventDefault();
                fn_deleteBoard();
            })
        })
         
        function fn_modifyBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/sample/modifyBoard.do'/>");
            comSubmit.submit();
        }
        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/sample/openBoardList.do'/>");
            comSubmit.submit();
        }
        function fn_deleteBoard(){
            var comSubmit = new ComSubmit("frm");
            comSubmit.setUrl("<c:url value='/sample/deleteBoard.do'/>");
            comSubmit.submit();
        }
    </script>
</body>
</html>