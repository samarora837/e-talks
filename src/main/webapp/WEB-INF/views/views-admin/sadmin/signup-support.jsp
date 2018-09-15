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
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MiProfe | <spring:message code="support.signup"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>

<script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content " onload="navigationUpdate();">
<!-- BEGIN HEADER -->
<div class="page-header -i navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<%@ include file="/WEB-INF/views/views-admin/sadmin/header.jsp" %>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<%@ include file="/WEB-INF/views/views-admin/sadmin/nav-sidebar.jsp" %>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			<spring:message code="customer.support"/><small><spring:message code="registeration.form"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/manage-support"><spring:message code="support"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/signup-support"><spring:message code="create.newsupport"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable-line boxless tabbable-reversed">
						<div class="tab-pane active" id="tab_0">
								<div class="portlet box yellow">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i><spring:message code="create.newsupport"/>
										</div>
										<!-- <div class="tools">
											<a href="javascript:;" class="collapse">
											</a>
											<a href="#portlet-config" data-toggle="modal" class="config">
											</a>
											<a href="javascript:;" class="reload">
											</a>
											<a href="javascript:;" class="remove">
											</a>
										</div> -->
									</div>
									<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form:form action="save-support" method="POST" id="form_sample_1" class="form-horizontal" commandName="dtoTutorDetails">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="havesome.error"></spring:message>
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="customer.createdsuccussfully"/>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="student.firstname"/> <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<form:input type="text" path="fname" name="fname" data-required="1" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="student.lastname"/> <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<form:input type="text" path="lname" name="lname" data-required="1" class="form-control"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="student.email"/> <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<form:input name="email" path="email" type="text" class="form-control"/>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="contact.number"/> <span class="required">
										* </span>
										</label>
										<div class="col-md-4">
											<form:input name="number" path="mobileNumber" type="text" class="form-control" maxlength="12"/>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="parent.password"/> <span class="required">
										* </span>
											</label>
												<div class="col-md-4">
													<form:input type="password" path="password" class="form-control" name="password" id="submit_form_password"/>
												<!-- <span class="help-block">
											Provide your password. </span> -->
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3"><spring:message code="signup.confirmpassword"/> <span class="required">
										* </span>
											</label>
												<div class="col-md-4">
													<input type="password" class="form-control" name="rpassword"/>
												<!-- <span class="help-block">
											Confirm your password </span> -->
										</div>
									</div>
									
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green"><spring:message code="parent.submit"/></button>
											<a href="<%=request.getContextPath()%>/sys-admin/manage-support" class="btn default">
											<!-- <button type="button" class="btn default"> --><spring:message code="cancel"/><!-- </button> --></a>
										</div>
									</div>
								</div>
								</div>
							</form:form>
							<!-- END FORM-->
						</div>
					</div>
								</div>
							</div>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
		  <spring:message code="copyright" /> TutoríasOnline LLC &copy; 2015
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
</script>
<!-- END FOOTER -->
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {    
   // initiate layout and plugins
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
   FormSamples.init();
   UIToastr.init();
   FormValidation.init();
   /* FormWizard.init(); */
});
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#supportMenu").addClass("start active open");
	$("#supportSelect").addClass("selected");
	$("#supportOpen").addClass("arrow open");
	$("#newSupport").addClass("active");
}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>