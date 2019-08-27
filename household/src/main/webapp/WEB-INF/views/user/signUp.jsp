<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="/WEB-INF/include/include-header.jspf" %>
<%@ include file="/WEB-INF/include/user/user-header.jspf" %>
  
</head>
<body class="hold-transition login-page">

<div class="register-box">
  <div class="register-logo">
    <a href="#"><b>Admin</b>LTE</a>
  </div>

  <div class="register-box-body">
    <p class="login-box-msg">회원가입</p>

    <form id="frm" name="frm">
      
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="아이디" name="user_id"">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="비밀번호" name="user_pw">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="비밀번호 확인" name="user_pw_retype">
        <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
      </div>
      
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="이메일" name="user_email">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      
      <c:if test="${! empty error}">
      	${error }
      </c:if>
      
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"><a href="#">약관</a>에 동의합니다.
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <!-- <button type="submit" class="btn btn-primary btn-block btn-flat">회원가입</button> -->
          <a href="#this" id="btn_sign_up" class="btn btn-primary btn-block btn-flat">회원가입</a>
        </div>
        <!-- /.col -->
      </div>
    </form>

    <div class="social-auth-links text-center">
      <p>- OR -</p>
      <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i>페이스북으로 가입하기</a>
      <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i>Google+로 가입하기</a>
    </div>

    <a href="/myapp/user/openSignIn.do" class="text-center">로그인 하기</a>
  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

<%@ include file="/WEB-INF/include/include-body.jspf" %>
<%@ include file="/WEB-INF/include/user/user-body.jspf" %>

<script type="text/javascript">
	
	$(document).ready(function(){
		$("#btn_sign_up").on("click",function(e){
			e.preventDefault();
			fn_signUp();
		})
	});
	
	function fn_signUp(){
		var comSubmit = new ComSubmit("frm");
		comSubmit.setUrl("<c:url value='/user/signUp.do'/>");
		comSubmit.submit();
	}
	
</script>

</body>
</html>