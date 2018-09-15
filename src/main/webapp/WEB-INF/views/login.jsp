<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
<title><spring:message code="login.title"></spring:message></title>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/jquery-ui.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/styleDate.css">
 <script type="text/javascript"> 

function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 

document.onkeypress = stopRKey; 

</script>
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
				minlength : 8,
				maxlength : 20
			}
		},
		
		 messages:{
			 j_username_email:{
				required:"<spring:message code='email.required'></spring:message>"
			},
			j_password:{
				required:"<spring:message code='password.required'></spring:message>",
				minlength:"<spring:message code='atleast8charactersrequired'></spring:message>",
				maxlength:"<spring:message code='passwordistoolong'></spring:message>"
			}
		}, 
		submitHandler:function(form){
			
			form.submit();
    	}
	});
	
	 $.validator.addMethod("emailNotRegistered", function(value, element) {
			var flag = emailExistCheck();
		  	return this.optional(element) || flag;	   
			},"Email is not registered."); 

	$('#recoveryForm').validate({
			rules:{
				recoveryEmail:{
					required:true,
					emailId:true,
					emailNotRegistered:true
					
				}
			},
			
			 messages:{
				 recoveryEmail:{
					required:"Email is required"
				}
			}, 
			submitHandler:function(form){
	 		
				form.submit();
	 	}
		});
	
	
});



function clrError(){
	$("#errorMessage").html("");
}

</script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>

    <c:if test="${vCode eq 'error'}">  
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/login"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.vCodeExpire"/>
            </section>
        </div>
        </div>
        </div>
    </c:if> 
    
   <c:if test="${vCode eq 'success'}">  
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/login"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.vCodeSuccess"/>
            </section>
        </div>
        </div>
        </div>
    </c:if> 
    
       <c:if test="${vcodeKey eq 'Y'}">  
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/login"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.forgotlinksent"/>
            </section>
        </div>
        </div>
        </div>
    </c:if>
   
    


    <!--Mid Section-->
    <section class="container">
    	<div class="login-con">
        	<div class="login-hdr"><spring:message code="login.tomiprofe"></spring:message> </div>
            <div class="login-form">
            
            <c:if test="${error=='true' }"> 
            
            <h3 id="errorMessage" class="login-error"> <spring:message code="login.errormessage"></spring:message></h3>
            
            </c:if>
            <h3 id="roleError" style="display: none;" class="login-error">
           <spring:message code="yourcredentialsarenotvalidtryagian"></spring:message>
            </h3>
            <form name="loginForm" id="loginForm" action="<c:url value="/j_spring_security_check" />" method="POST">
            
            	<div class="login-form-row"><input name="j_username_email" id="j_username_email" maxlength="80" type="text" placeholder='<spring:message code="login.email"></spring:message>' onfocus="clrError();"></div>
                <div class="login-form-row"><input name="j_password" id="password" maxlength="80" type="password" placeholder='<spring:message code="login.password"></spring:message>' onfocus="clrError();"></div>
                <div class="login-btn-row">
                 <input name="submit"  type="button" id="student" onclick="checkRole('student');" value='<spring:message code="login.student"></spring:message>' />
                  <input name="submit"  type="button" onclick="checkRole('parent');" value='<spring:message code="login.parent"></spring:message>' />
                   <input name="submit" type="button" onclick="checkRole('tutor');" value='<spring:message code="login.tutor"></spring:message>' />
                   
                      <input name="submit" type="submit" id="submitButton" value='<spring:message code="login.student"></spring:message>' style="display: none;"   />
                </div>
                <div class="login-not-regis">
                	<p><spring:message code="login.notregister"></spring:message></p> 
                    <a href="<%=request.getContextPath()%>/signup"><spring:message code="login.register"></spring:message> </a>
                    
                </div>
                <div align="center" style="font-size: 16px;">
                <a href="#" onclick="forgotPass();"><spring:message code="forgotpassword"></spring:message> </a></div>
                <input type="hidden" name="j_username" id="userRole">
                
             </form>
                
                <div class="clr"></div>
            </div>
            
            <div class="clr"></div>
        </div>
    </section>
    <!--//Mid Section-->
    
    <div id="forgotPassword" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="" onclick="closeEditPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form name="recoveryForm" id="recoveryForm" method="POST" action="forgotPassword"   >
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  <tr>
                   <th><spring:message code="login.email"></spring:message></th>
                    <td>:</td>  
                    <td><div ><input type="text" placeholder='<spring:message code="login.email"/>'  id="recoveryEmail" name="recoveryEmail" maxlength="80"/></div></td>
                  </tr>
                   
                   
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="submit" class="greenBtn-normal" value='<spring:message code="parent.submit"></spring:message>'  ></td>
                  </tr>
                </tbody></table>
                </div>
                </form>
        	
        </div>
   </div>

</div>
    
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

<%-- <script type="text/javascript">
 function checkRole(role){
	// alert("hii"+role);
	 /* var userEmail=document.getElementById("j_username_email").value;
		document.getElementById("mobileWithCCode").value=userRole+","+userEmail; */
	 
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
</script> --%>

<script type="text/javascript">


function closePopUp(){
	$("#signupRequired").hide();
}


function sendMsg(){
	 $(window).scrollTop(0);
	 $("#signupRequired").show();
}

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
 
 function forgotPass(){
	 $("#forgotPassword").show();
	 
 }
 
 
 function emailExistCheck(){
		
		var email=$("#recoveryEmail").val();
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

</html>

