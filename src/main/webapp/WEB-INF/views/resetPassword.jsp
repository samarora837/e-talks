<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.miprofe.facebook.FBConnection"%>

<%
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
<title><spring:message code="title.resetPassword"></spring:message></title>
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
	
	
	$('#resetForm').validate({
		rules:{
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


</script>


<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>
    <!--Mid Section-->
    <section class="container">
    	<div class="login-con">
        	<div class="login-hdr"><spring:message code="reset.password"/> </div>
        	<form name="resetForm" id="resetForm" method="GET" action="saveNewPassword">
            <div class="login-form">
                <div class="login-form-row"><input type="password" name="password" id="password" placeholder='<spring:message code="login.password"></spring:message>' maxlength="80"></div>
                <div class="login-form-row"><input type="password" name="confirmPass" id="confirmPass" placeholder='<spring:message code="signup.confirmpassword"></spring:message>' maxlength="80"></div>
                <div class="regis-btn-row">
                	<!-- <a href="#" onclick="submitStudent();">submit</a> -->
                	<input type="submit" class="greenBtn-big" value='<spring:message code="parent.submit"></spring:message>'/>
                </div>
                
               <div>
			</div>
          <div class="clr"></div>
            </div>
            <input type="hidden" id="userID" name="userID" readonly="readonly" value="${userID}"/>
            <input type="hidden" id="vCode" name="vCode" readonly="readonly" value="${vCode}"/>
            </form>
            
            <div class="clr"></div>
        </div>
    </section>
    <!--//Mid Section-->
   </div>
    <div class="clr"></div>
    <%@ include file="/WEB-INF/views/footerOuter.jsp" %>
</body>
	</html>
