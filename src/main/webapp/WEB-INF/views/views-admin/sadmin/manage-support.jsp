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
<title>MiProfe | <spring:message code="manage.support"/></title>
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
			<spring:message code="support"/> <small><spring:message code="manage.allsupport"/></small>
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
						<a href="<%=request.getContextPath()%>/sys-admin/manage-support"><spring:message code="manage.support"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			<div class="alert alert-success" id="sucessMessage" style="display:none;">
								<strong><spring:message code="success"/></strong> <spring:message code="userDelete.success"/>
			</div>			
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box yellow">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.allsupport"/>
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
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group"><a href="<%=request.getContextPath()%>/sys-admin/signup-support">
											<button class="btn green">
											<spring:message code="add.new"/> <i class="fa fa-plus"></i>
											</button></a>
										</div>
									</div>
									<!-- <div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li>
													<a href="javascript:;">
													Print </a>
												</li>
												<li>
													<a href="javascript:;">
													Save as PDF </a>
												</li>
												<li>
													<a href="javascript:;">
													Export to Excel </a>
												</li>
											</ul>
										</div>
									</div> -->
								</div>
							</div>
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
								<th>
									 <spring:message code="full.name"/>
								</th>
								<th>
									<spring:message code="login.email"/> 
								</th>
								<th>
									 <spring:message code="status"/>
								</th>
								<th>
									 <spring:message code="join.date"/>
								</th>
								<th>
									 <spring:message code="join.date"/>
								</th>
								<th>
									<spring:message code="edit"/> 
								</th>
								<th>
									 <spring:message code="action"/>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="tutorList" items="${tutorList}">
							<tr>
								<td>
									 ${tutorList.fname}
								</td>
								<td>
									 <a href="mailto:${tutorList.email}">
									${tutorList.email} </a>
								</td>
								<c:if test="${tutorList.status=='Active'}">
								<td>
									<span class="label label-sm label-success">
									${tutorList.status} </span>
								</td>
								</c:if>
								<c:if test="${tutorList.status=='Inactive'}">
								<td>
									<span class="label label-sm label-warning">
									${tutorList.status} </span>
								</td>
								</c:if>
									<td class="center">
									${tutorList.newJoinDate}
								</td>
								<td class="center">
									${tutorList.joinDate}
								</td>
								<td>
									<a onclick="getAdminInfo('${tutorList.userId}');">
									<spring:message code="edit"/>  </a>
								</td>
								<td>
									<a class="delete12" onclick="confirmDelete('${tutorList.userId}');">
									<spring:message code="delete"/>  </a>
									
									<c:if test="${tutorList.status=='Active'}">
										<a onclick="setDeActive('${tutorList.userId}');">
											<spring:message code="block"/>  </a></c:if>
									<c:if test="${tutorList.status=='Inactive'}">
										<a onclick="setUserActive('${tutorList.userId}');">
											<spring:message code="unblock"/> </a>
									</c:if>
									
								</td>
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

<!-- Dialog start -->
<!-- responsive -->
						
							<div id="responsive" class="modal fade" tabindex="-1" data-width="760">
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorDetails" method="post" action="updateSupportDetails">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="update.supportprofiledetails"/></h4>
								</div>
								<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="havesome.error"></spring:message>
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.validation"></spring:message>
									</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-6">
										<div class="form-group">
										<h4><spring:message code="student.firstname"/></h4>
											<p>
												<form:input path="fname" id="fname" name="fname" class="form-control" type="text"></form:input>
											</p>
										</div>
										<div class="form-group">
											<h4><spring:message code="student.lastname"/></h4>
											<p>
												<form:input path="lname" id="lname" name="lname" class="form-control" type="text"></form:input>
											</p>
											</div>
											<div class="form-group">
											<h4><spring:message code="student.email"/></h4>
											<p>
												<form:input path="email" id="email" name="email" class="form-control" type="text"></form:input>
											</p>
											</div>
										</div>
										<div class="col-md-6">
										<div class="form-group">
											<h4><spring:message code="parent.password"/></h4>
											<p>
												<form:input path="password" id="submit_form_password" name="password" class="form-control" type="password"></form:input>
											</p>
											</div>
											<div class="form-group">
											<h4><spring:message code="signup.confirmpassword"/></h4>
											<p>
												<input id="rpassword" name="rpassword" class="form-control" type="password">
											</p>
											</div>
											<div class="form-group">
											<h4><spring:message code="contact.number"/></h4>
											<p>
												<form:input path="mobileNumber" id="contactNumber" name="contactNumber" class="form-control" type="text" maxlength="12"></form:input>
											</p>
											</div>
										</div>
									</div>
								</div>
								<form:input type="hidden" path="userId" id="userId" name="userId"></form:input>
								<input type="hidden" id="userEmail" name="userEmail"></input>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"/></button>
									<button type="submit" class="btn blue"><spring:message code="save.changes"/></button>
								</div>
								</div>
								</form:form>
							</div>
							
<!-- Dialog end -->
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
	
	
	 $('#sample_editable_1').DataTable({
		 "order": [[ 3, "desc" ]],
	   	 "lengthMenu": [
	           [5,10, 15, 20, -1],
	           [5,10, 15, 20, "All"] 
	       ],
	       "aoColumns": [null,
	                     null,
	                     null,
	                       {"bVisible": false},
	                         {"iDataSort": 3},                              
	                         null,
	                         null                                                      
	                        ]
	});
	   var tableWrapper = $("#sample_editable_1_wrapper");

	   tableWrapper.find(".dataTables_length select").select2({
	       showSearchInput: false //hide search box with special css class
	   });
	
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   /* TableManaged.init(); */
	   //TableEditable.init();
	   FormValidation.init();
});
</script>
<script type="text/javascript">

<%-- function submitAdmin(){
	
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
} --%>
function setUserActive(userId){
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
				return true;
			}
			else{
				
			}
			}
		});
}

function confirmDelete(userId){
	var r = confirm('<spring:message code="sure.deleterecord"/>'); 
	if (r == true) {
		setDelete(userId);
		
	}
	
}

function setDelete(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/sys-admin/admin-del';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{userId:userId},
			success:function(response){
				if(response=="success"){
$("#sucessMessage").show();
					
					setTimeout(function(){
						location.reload();
					}, 3000);
				return true;
			}
			else{
				
			}
			}
		});
}
<%-- function updateAdminDetails(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/sys-admin/admin-update';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{userId:userId},
			success:function(response){
				if(response=="success"){
					
					location.reload();
				return true;
			}
			else{
				
			}
			}
		});
} --%>
function getAdminInfo(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/sys-admin/getSupportProfile';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{userId:userId},
		success:function(response){
			if(response!=null){
				$("#fname").val(response.modelMap.admininfo.fname);
				$("#lname").val(response.modelMap.admininfo.lname);
				$("#email").val(response.modelMap.admininfo.email);
				$("#userEmail").val(response.modelMap.admininfo.email);
				$("#contactNumber").val(response.modelMap.admininfo.mobileNumber);
				$("#submit_form_password").val(response.modelMap.admininfo.password);
				$("#userId").val(userId);
				$('#responsive').modal('show');
			return true;
		}
		else{
			
		}
		}
	});
}
<%-- function submitInfoForm(){
	alert("Saving");
	$("#updateAdmin").attr("action", "<%=request.getContextPath()%>/sys-admin/updateAdminDetails");
    $("#updateAdmin").submit();
} --%>
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#supportMenu").addClass("start active open");
	$("#supportSelect").addClass("selected");
	$("#supportOpen").addClass("arrow open");
	$("#manageSupport").addClass("active");
}

var userstatus = '${createFirebaseUser}';
if(userstatus=="Y"){
	
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      //var authData=myDataRef.getAuth();
	      
	      currentUser= myDataRef.createUser({
	      email    : '${createUser}',
	      password : '${createPass}'
	    }, function(error, userData) {
	      if (error) {
	        // console.log("Error creating user:", error);
	      } else {
	        // console.log("Successfully created user account with uid:", userData.uid);
	      }
	    });   
}

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>