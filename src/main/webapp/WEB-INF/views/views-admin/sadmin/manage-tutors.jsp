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
<title>MiProfe | <spring:message code="manage.tutor"/> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body class="page-header-fixed page-quick-sidebar-over-content ">
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
			<spring:message code="tutor"/> <small><spring:message code="manage.alltutor"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="index.html"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#"><spring:message code="tutor"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#"><spring:message code="manage.tutor"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i><spring:message code="manage.alltutor"/>
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
						<div class="portlet-body">
							<table class="table table-striped table-bordered table-hover" id="sample_1">
							<thead>
							<tr>
								<th class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes"/>
								</th>
								<th>
									 <spring:message code="username"/> 
								</th>
								<th>
									 <spring:message code="login.email"/> 
								</th>
								<th>
									 <spring:message code="mobilenumber"/> 
								</th>
								<th>
									 <spring:message code="join.date"/>
								</th>
								<th>
									 <spring:message code="status"/>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="tutorList" items="${tutorList}">
							<tr class="odd gradeX">
								<td>
									<input type="checkbox" class="checkboxes" value="1"/>
								</td>
								 <td>
									 ${tutorList.fname}
								</td>
								<td>
									<a href="mailto:${tutorList.email}">
									${tutorList.email} </a>
								</td>
								<td>
									 ${tutorList.mobileNumber}
								</td>
								<td class="center">
									${tutorList.joinDate}
								</td>
								<c:if test="${tutorList.status=='Active'}">
								<td>
								<a onclick="setDeActive('${tutorList.userId}');">
									<span class="label label-sm label-success">
									${tutorList.status} </span></a>
								</td>
								</c:if>
								<c:if test="${tutorList.status=='De-Active'}">
								<td>
								<a onclick="setActive('${tutorList.userId}');">
									<span class="label label-sm label-warning">
									${tutorList.status} </span></a>
								</td>
								</c:if>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page"/>
							</c:forEach>
							</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
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
<!-- END FOOTER -->
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   TableManaged.init();
});
</script>
<script type="text/javascript">

function submitAdmin(){
	
	var email = $("#username").val();
	var password= $("#password").val();
	var url='<%=request.getContextPath()%>/sys-admin/save-admin';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{email:email,password:password},
			success:function(response){
				if(response=="success"){
					$('#saveAdminForm')[0].reset();
					alert("Saved Success");
				
				return true;
			}
			else{
				
			}
			}
		});
}
function setActive(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/sys-admin/active';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{userId:userId},
			success:function(response){
				if(response=="success"){
					
					location.reload();
					//alert("Activated");
				return true;
			}
			else{
				
			}
			}
		});
}
function setDeActive(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/sys-admin/de-active';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{userId:userId},
			success:function(response){
				if(response=="success"){
					
					location.reload();
					//alert("De-Activated");
				return true;
			}
			else{
				
			}
			}
		});
}


</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>