<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.miprofe.facebook.FBConnectionStudent"%>
<%@page import="com.miprofe.facebook.FBConnection"%>


<%
FBConnectionStudent fbConnectionStudent = new FBConnectionStudent();
FBConnection fbConnection = new FBConnection();
%>



<%          response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>
 <!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title><spring:message code="signup.title"></spring:message></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,400italic,300' rel='stylesheet' type='text/css'>    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if gte IE 9]>
  <style type="text/css">
    .gradient {
       filter: none;
    }
  </style>
<![endif]-->


<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	
	$.validator.addMethod("emailId", function(value, element) {
	  	return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');
	
	 $.validator.addMethod("emailIdExist", function(value, element) {
		var flag = emailCheck();
	  	return this.optional(element) || flag;	   
		},'<spring:message code="email.exist"></spring:message>'); 
	 
	
	$('#registerForm').validate({
		rules:{
			email:{
				required:true,
				emailId:true,
				/* emailIdExist:true */
			},
			password:{
				required:true,
				minlength : 8,
				maxlength : 20
			},
			confirmPass:{
				required:true,
				equalTo: "#password"
				} 
		
			},

		
		 messages:{
			email:{
				required:"<spring:message code='email.required'></spring:message>"
			},
			password:{
				required:"<spring:message code='password.required'></spring:message>",
					minlength:"<spring:message code='atleast8charactersrequired'></spring:message>",
					maxlength:"<spring:message code='passwordistoolong'></spring:message>"
					
			},
			confirmPass:{
				required:"<spring:message code='confirm.passwordrequired'></spring:message>",
				equalTo:"<spring:message code='password.notmatch'></spring:message>"
			}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});

<%-- function emailCheck(){
	
	var email=$("#email").val();
	var flag = false;
	 var url='<%=request.getContextPath()%>/emailCheck';
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




function submitStudent(){
	
	$("#registerForm").attr("action", "<%=request.getContextPath()%>/student/studentregister");
	
	$("#registerForm").submit();
}

function submitParentForm(){
	$("#registerForm").attr("action", "<%=request.getContextPath()%>/parent/parentRegistration");
	$("#registerForm").submit();
	
} --%>


function emailCheck(){
	
	var email=$("#email").val();
	var flag = false;
	 var url='<%=request.getContextPath()%>/multipleRoleUseremailCheck';
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




function submitStudent(){
	
	var role = "student";
	var email=$("#email").val();
	 var url='<%=request.getContextPath()%>/multipleRoleUseremailCheck';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{email:email,role:role},
			success:function(response){
			if(response=="error"){
				alert("User already register with this role.");
				return false;
				}
			else{
				$("#role").val("student");
				$("#registerForm").attr("action", "<%=request.getContextPath()%>/student/studentregister");
				$("#registerForm").submit();
			}
			}
		});
}

function submitParentForm(){
	var role = "parent";
	var email=$("#email").val();
	 var url='<%=request.getContextPath()%>/multipleRoleUseremailCheck';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{email:email,role:role},
			success:function(response){
			if(response=="error"){
				alert("User already register with this role.");
				return false;
				}
			else{
				$("#role").val("parent");
				$("#registerForm").attr("action", "<%=request.getContextPath()%>/parent/parentRegistration");
				$("#registerForm").submit();
			}
			}
		});
}




function sendMsg(){
	 $(window).scrollTop(0);
	 $("#signupRequired").show();
}
function closePopUp(){
	$("#signupRequired").hide();
	$("#signupRequiredPending").hide();
	
}

</script>

<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>

<body>



<c:if test="${facebookRegister eq 'Y'}">
    <div class="form-tutor-popup" id="signupRequired">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="facebook.Alreadyregister"></spring:message>
            </div>
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/signup" class="greenBtn-normal"> <spring:message code="header.signup"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>
        <%session.removeAttribute("facebookRegister");%>   
</c:if>



<c:if test="${facebookRegisterPending eq 'Y'}">
    <div class="form-tutor-popup" id="signupRequiredPending">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="facebook.pendingregister"></spring:message>
            </div>
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/signup" class="greenBtn-normal"> <spring:message code="header.signup"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>
        <%session.removeAttribute("facebookRegisterPending");%>   
</c:if>



<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>
    <!--Mid Section-->
    <section class="container">
    	<div class="login-con">
        	<div class="login-hdr"><spring:message code="signup.register"></spring:message></div>
        	<form name="registerForm" id="registerForm" method="post">
            <div class="login-form">
            	<div class="login-form-row"><input type="text" name="email" value="${email}" id="email" placeholder='<spring:message code="login.email"></spring:message>' maxlength="80"></div>
                <div class="login-form-row"><input type="password" name="password" id="password" placeholder='<spring:message code="login.password"></spring:message>' maxlength="80"></div>
                <div class="login-form-row"><input type="password" name="confirmPass" id="confirmPass" placeholder='<spring:message code="signup.confirmpassword"></spring:message>' maxlength="80"></div>
                <div class="regis-btn-row">
                	<a href="#" onclick="submitStudent();"><spring:message code="signup.imstudent"></spring:message></a>
                    <a href="#" onclick="submitParentForm();"><spring:message code="signup.imparent"></spring:message> </a>
                </div>
                
                
                   <div class="signup-fb">
                	<div class="or"><spring:message code="or"/> </div>
                    <div class="regisTxt"><spring:message code="signup.registerfacebook"/></div>
                    <div class="regis-fbBtns">
                     	<a href="<%=fbConnectionStudent.getFBAuthUrl()%>" > <spring:message code="student.title"></spring:message></a>
                         <a href="<%=fbConnectionStudent.getFBAuthUrl()%>"><spring:message code="parent.title"></spring:message></a>
                        <a href="<%=fbConnection.getFBAuthUrl()%>"><spring:message code="login.tutor"></spring:message> </a>
                    </div>
                </div>
                
                
                
           <%--      <div class="bcm-a-tutor">
                    <a href="<%=fbConnection.getFBAuthUrl()%>"><spring:message code="tutor.registerwithyourFacebookAccount" /> <spring:message code="student.title"></spring:message></a>
                </div>
                <div class="bcm-a-tutor">
                    <a href="<%=fbConnection.getFBAuthUrl()%>"><spring:message code="tutor.registerwithyourFacebookAccount" /> <spring:message code="parent.title"></spring:message></a>
                </div>
               <div>
				<div class="bcm-a-tutor">
                	<p><spring:message code="signup.becometutor"></spring:message> </p>
                    <a href="<%=request.getContextPath()%>/become-tutor"><spring:message code="signup.registerfacebook"></spring:message> </a>
                </div>
                <div align="center"><spring:message code="donothavefacebook.account"></spring:message>
                	<a href="<%=request.getContextPath() %>/tutor/register"><u><spring:message code="click.here"></spring:message> </u></a>
                </div>
			</div> --%>
          <div class="clr"></div>
            </div>
            <input type="hidden" id="role" name="role" readonly="readonly"/>
            <input type="hidden" id="code" value="${code}" name="code" readonly="readonly"/>
            
            </form>
            
            <div class="clr"></div>
        </div>
    </section>
    <!--//Mid Section-->
   </div>
    <div class="clr"></div>
    <%@ include file="/WEB-INF/views/footerOuter.jsp" %>
    
        <div class="form-tutor-popup" id="signupRequired" style="display: none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="signupFirst"/>
            </div>
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/signup" class="greenBtn-normal"> <spring:message code="header.signup"></spring:message> </a>
                <a href="<%=request.getContextPath()%>/login" class="greenBtn-normal"> <spring:message code="header.login"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div> 
</body>
	</html>
