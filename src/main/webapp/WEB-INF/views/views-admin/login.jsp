<!DOCTYPE HTML>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<%          response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="login.title"></spring:message></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">


$(document).ready(function(){
	$.validator.addMethod("emailId", function(value, element) {
	  	return this.optional(element) || /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,3}$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');


	
	$('#loginForm').validate({
		rules:{
			j_username_email:{
				required:true,
				emailId:true,
			},
			j_password:{
				required:true,
				minlength : 6,
				maxlength : 20
			}
		},
		 messages:{
			 j_username_email:{
				required:"<spring:message code='email.required'></spring:message>"
			},
			j_password:{
				required:"<spring:message code='password.required'></spring:message>",
				minlength:"Atleast 6 characters required",
				maxlength:"<spring:message code='passwordistoolong'></spring:message>"
			}
		}, 
		submitHandler:function(form){
			form.submit();
    	}
	});
	
	
	
	
	
});
</script>
<script type="text/javascript">
$(document).ready(function(){
	
	<c:if test="${vcodeKey eq 'Y'}">
	$('#resetPopup').modal('show');
	 </c:if>
	 
	 <c:if test="${resetSuccess eq 'Y'}"> 
	 $('#resetSuccess').modal('show');
	 </c:if>
	
	
	<c:if test="${vCode eq 'error'}">
	$('#responsive').modal('show');
	 </c:if> 
	$.validator.addMethod("emailId", function(value, element) {
	  	return this.optional(element) || /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,3}$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');
	
	
	$.validator.addMethod("emailNotRegistered", function(value, element) {
        var flag = emailExistCheck();
        return this.optional(element) || flag;      
        },"Email address doesn't exists.");
	
	$('#resetPass').validate({
		rules:{
			email:{
				required:true,
				emailId:true,
				emailNotRegistered:true
			}
		},
		 messages:{
			 email:{
				required:"<spring:message code='email.required'></spring:message>"
			}
		}, 
		submitHandler:function(form){
			form.submit();
    	}
	});
});
</script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
	<a href="<%=request.getContextPath() %>/admin">
	<img src="<%=request.getContextPath()%>/images/Final.png" alt="" style="width:230px;"/>
	</a>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
	<!-- BEGIN LOGIN FORM -->
	
	<%-- <c:if test="${vCode eq 'error'}">   --%>
  <%--  <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/admin"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.vCodeExpire"/>
            </section>
        </div>
        </div>
        </div> --%>
        
        
        
        
   <%--  </c:if>  --%>
    
       <div id="responsive" class="modal fade" tabindex="-1" data-width="460">
							<div class="form-body">
								<div class="modal-header">
									<h2 class="modal-title"><spring:message code="popup.vCodeExpire"/></h2>
								</div>
								<div class="modal-footer">
									<a href="<%=request.getContextPath()%>/admin"><h3><spring:message code="ok"/></h3></a>
									<!-- <button type="button" data-dismiss="modal" class="btn btn-default">Close</button> -->
								</div>
								</div>
							</div>
							
	     <div id="resetPopup" class="modal fade" tabindex="-1" data-width="460">
							<div class="form-body">
								<div class="modal-header">
									<h3 class="modal-title"><spring:message code="popup.forgotlinksent"/></h3>
								</div>
								<div class="modal-footer">
									<a href="<%=request.getContextPath()%>/admin">
									<button type="button" class="btn btn-success uppercase"><spring:message code="ok"/> </button>
									</a>
									<!-- <button type="button" data-dismiss="modal" class="btn btn-default">Close</button> -->
								</div>
								</div>
							</div>	
							
				 			
			     <div id="resetSuccess" class="modal fade" tabindex="-1" data-width="460">
							<div class="form-body">
								<div class="modal-header">
									<h3 class="modal-title"><spring:message code="popup.vCodeSuccess"/></h3>
								</div>
								<div class="modal-footer">
									<a href="<%=request.getContextPath()%>/admin"><h3><spring:message code="ok"/></h3></a>
									<!-- <button type="button" data-dismiss="modal" class="btn btn-default">Close</button> -->
								</div>
								</div>
							</div>						
							
												
							
							
							
							     
	<form class="login-form" name="loginForm" id="loginForm" action="<c:url value="/j_spring_security_check" />" method="POST">
		<h3 class="form-title">Sign In</h3>
		 <c:if test="${error=='true' }"> 
            	<span id="errorMessage" style="text-align: center; color:#ff0000; font-size: 14px;"> <spring:message code="login.errormessage"></spring:message></span>
            </c:if>
            <span id="roleError" style="display: none; text-align: center; color:#ff0000; font-size: 14px;">
           		<spring:message code="yourcredentialsarenotvalidtryagian"></spring:message>
            </span>
		<div class="alert alert-danger display-hide">
			<button class="close" data-close="alert"></button>
			<span>
			<spring:message code="enter.usernamepassword"/> </span>
		</div>
		<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9"><spring:message code="username"/> </label>
			<input name="j_username_email" id="j_username_email" class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder='<spring:message code="username"/>' name="username" onfocus="clrError();"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9"><spring:message code="login.password"/></label>
			<input name="j_password" id="password" class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder='<spring:message code="login.password"/>' name="password" onfocus="clrError();"/>
		</div>
		<div class="form-actions">
			<button type="button" name="sadmin" id="sadmin" onclick="checkRole('SysAdmin');" class="btn btn-success uppercase"><spring:message code="super.admin"/> </button>
			<button type="button" name="admin" id="admin" onclick="checkRole('Admin');" class="btn btn-success uppercase"><spring:message code="admin"/> </button>
			<button type="button" name="support" id="support" onclick="checkRole('Support');" class="btn btn-success uppercase"><spring:message code="header.support"/> </button>
			
			<input name="submit" type="submit" id="submitButton" style="display: none;"   />
			
			<!-- <label class="rememberme check">
			<input type="checkbox" name="remember" value="1"/>Remember </label> -->
			<a href="javascript:;" id="forget-password" class="forget-password"><spring:message code="forgotpassword"/>?</a>
		</div>
		<input type="hidden" name="j_username" id="userRole">
	</form>
	<!-- END LOGIN FORM -->
	<!-- BEGIN FORGOT PASSWORD FORM -->
	<form:form class="forget-form" name="resetPass" id="resetPass" action="forgotPass" commandName="dtoForgotPassword" method="post">
		<h3><spring:message code="forgotpassword"/> ?</h3>
		<p>
			 <spring:message code="enteremail.resetyourpassword"></spring:message>
		</p>
		<div class="form-group">
			<form:input path="email" class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" id="email"></form:input>
		</div>
		<div class="form-actions">
			 <button type="button" id="back-btn" class="btn btn-success uppercase pull-left"><spring:message code="cancel"/></button> 
			<button type="submit" class="btn btn-success uppercase pull-right"><spring:message code="parent.submit"/></button>
		</div>
	</form:form>
	<!-- END FORGOT PASSWORD FORM -->
</div>
<div class="copyright">
	 <spring:message code="copyright" /> TutoríasOnline LLC &copy; 2015
</div>
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {     
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
Login.init();
Demo.init();
});
</script>
<script type="text/javascript">
 function checkRole(role){
	 var email = $("#j_username_email").val();
	 var password = $("#password").val();
	 if(email!="" && password!=""){
	 $("#loginForm").validate().element("#j_username_email");
	 $("#loginForm").validate().element("#password");
	 var url='<%=request.getContextPath()%>/verifyUserRole';
	 
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{role:role,email:email},
			success:function(response){
			if(response=="success"){
				$("#userRole").val(role+","+email);
				$("#submitButton").trigger("click");
				
				}
			else{
				$("#roleError").show();
				setTimeout(function() { $("#roleError").hide(); }, 3000);
			}
			}
		}); 
	 }
	 else{
		 $("#submitButton").trigger("click");
	 }
} 
</script>
<script type="text/javascript">
function clrError(){
	$("#errorMessage").html("");
}


function emailExistCheck(){
    
    var email=$("#email").val();
    var flag = false;
    var url='<%=request.getContextPath()%>/emailExistCheck';
    $.ajax({
           url:url,
           method:'GET',
           async :false,
           data:{email:email},
           success:function(response){
           if(response=="error"){
                 flag = false;
                 }
           else{
                 flag = true;
           }
           }
           
    });  
    return flag;
}




</script>



</body>
<!-- END BODY -->
</html>