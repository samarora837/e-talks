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
<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MiProfe | <spring:message code="forgotpassword"></spring:message></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#resetPassForm').validate({
		rules:{
			 password: {
                 minlength: 5,
                 required: true
             },
             rpassword: {
                 minlength: 5,
                 required: true,
                 equalTo: "#submit_form_password"
             },
		},
		 messages:{
			 password:{
				required:"<spring:message code='email.required'></spring:message>"
			},
			rpassword:{
				required:"<spring:message code='password.required'></spring:message>",
				/* minlength:"<spring:message code='atleast8charactersrequired'></spring:message>",
				maxlength:"<spring:message code='passwordistoolong'></spring:message>" */
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
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12 coming-soon-header">
			<a class="brand" href="<%=request.getContextPath()%>/admin/login">
			<img src="<%=request.getContextPath()%>/images/Final.png" alt="logo" style="width:230px;"/>
			</a>
		</div>
	</div>
	<div class="row">
		<div class="coming-soon-content">
			<h1><spring:message code="change.password"></spring:message> </h1>
			<p>
				 At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi vehicula sem ut volutpat. Ut non libero magna fusce condimentum eleifend enim a feugiat.
			</p>
			<br>
			<%-- <form:form class="form-inline" name="resetPassForm" id="resetPassForm" modelAttribute="dtoForgotPassword" action="saveNewPassword" method="POST">
			<div class="content">
				<div class="form-group">
			<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
			<label class="control-label visible-ie8 visible-ie9">Password</label>
			<form:input path="password" class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="New Password" name="password"/>
		</div>
		<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">Confirm Password</label>
			<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Confirm New Password" name="rpassword" id="submit_form_password"/>
		</div>
			</div>	
					<form:input type="hidden" path="userId" id="userID" name="userID" readonly="readonly" value="${userID}"/>
		            <form:input type="hidden" path="vCode" id="vCode" name="vCode" readonly="readonly" value="${vCode}"/>
            
            <div class="modal-footer">
				<button type="submit" class="btn blue">Save changes</button>
			</div>
			</form:form>  --%>
			
			
			<spring:message code="new.password" var="newPassword"></spring:message>
			
			<form:form action="saveNewPassword" method="POST" id="form_sample_1" class="form-horizontal" commandName="dtoForgotPassword">
								<div class="form-body">
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="new.password"></spring:message> <span class="required">
										* </span>
											</label>
												<div class="col-md-4">
													<form:input path="password" class="form-control form-control-solid placeholder-no-fix" id="submit_form_password" type="password" autocomplete="off" placeholder='${newPassword}' name="password"/>
													<%-- <form:input type="password" path="password" class="form-control" name="password" id="submit_form_password"/> --%>
												<!-- <span class="help-block">
											Provide your password. </span> -->
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="confirmnew.password"></spring:message> <span class="required">
										* </span>
											</label>
												<div class="col-md-4">
													<input type="password" class="form-control form-control-solid placeholder-no-fix" name="rpassword" placeholder='<spring:message code="confirmnew.password"></spring:message>'/>
												<!-- <span class="help-block">
											Confirm your password </span> -->
										</div>
									</div>
					<form:input type="hidden" path="userId" id="userID" name="userID" readonly="readonly" value="${userID}"/>
		            <form:input type="hidden" path="vCode" id="vCode" name="vCode" readonly="readonly" value="${vCode}"/>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green"><spring:message code="parent.submit"></spring:message></button>
											
										</div>
									</div>
								</div>
								</div>
							</form:form>
			
			
			
			
			
			<!-- 
			<ul class="social-icons margin-top-20">
				<li>
					<a href="javascript:;" data-original-title="Feed" class="rss">
					</a>
				</li>
				<li>
					<a href="javascript:;" data-original-title="Facebook" class="facebook">
					</a>
				</li>
				<li>
					<a href="javascript:;" data-original-title="Twitter" class="twitter">
					</a>
				</li>
				<li>
					<a href="javascript:;" data-original-title="Goole Plus" class="googleplus">
					</a>
				</li>
				<li>
					<a href="javascript:;" data-original-title="Pinterest" class="pintrest">
					</a>
				</li>
				<li>
					<a href="javascript:;" data-original-title="Linkedin" class="linkedin">
					</a>
				</li>
				<li>
					<a href="javascript:;" data-original-title="Vimeo" class="vimeo">
					</a>
				</li>
			</ul> -->
		</div>
		<!-- <div class="col-md-6 coming-soon-countdown">
			<div id="defaultCountdown">
			</div>
		</div> -->
	</div>
	<!--/end row-->
	<div class="row">
		<div class="col-md-12 coming-soon-footer">
			 <spring:message code="copyright" /> TutoríasOnline LLC &copy; 2015
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<!-- END PAGE LEVEL SCRIPTS -->
<script type="text/javascript">
jQuery(document).ready(function() {     
Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
FormValidation.init();
ComingSoon.init();
 
  // init background slide images
    $.backstretch([
            "../admin/images/1.jpg",
            "../admin/images/2.jpg",
            "../admin/images/3.jpg",
    		"../admin/images/4.jpg"
        ], {
        fade: 1000,
        duration: 10000
   });
});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>