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
<title>MiProfe | <spring:message code="history.tutor"/> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body onload="navigationUpdate();" class="page-header-fixed page-quick-sidebar-over-content ">
<!-- BEGIN HEADER -->
<div class="page-header -i navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<%@ include file="/WEB-INF/views/views-admin/admin/header.jsp" %>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<%@ include file="/WEB-INF/views/views-admin/admin/nav-sidebar.jsp" %>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			<spring:message code="tutor"/> <small><spring:message code="history"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/history-tutors"><spring:message code="tutor"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/history-tutors"><spring:message code="history.tutor"/></a>
					</li>
				</ul>
			</div>
			
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="history.tutor"/>
							</div>
							
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										
									</div>
									
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
									 <spring:message code="mobilenumber"/> 
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
															
							</tr>
							</thead>
							<tbody>
							<c:forEach var="tutorList" items="${tutorList}">
							<tr>
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
								<c:if test="${tutorList.status=='Active'}">
								<td>
									<span class="label label-sm label-success">
									<spring:message code="active"/> </span>
								</td>
								</c:if>
								<c:if test="${tutorList.status=='Rejected'}">
								<td>
									<span class="label label-sm label-danger">
									<spring:message code="inactive"/>  </span>
								</td>
								</c:if>
									<c:if test="${tutorList.status=='Pending'}">
								<td>
									<span class="label label-sm label-warning">
									<spring:message code="pendingTutor"/>  </span>
								</td>
								</c:if>
								<td class="center">
								${tutorList.newJoinDate}
								</td>
								<td class="center">
								${tutorList.joinDate}
								</td>
						
								</tr>
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
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorDetails" method="post" action="updateTutorDetailsByAdmin" enctype="multipart/form-data">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"><span id="fname"></span> <span id="lname"></span> (<spring:message code="tutor"/>)</h3>
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
										<div class="col-md-4">
										
										<div class="form-group">
														<div class="fileinput fileinput-new" data-provides="fileinput">
															<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
              													<img alt="" src="" id="profileImage"/>
															</div>
															<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
															</div>
															<div>
																<span class="btn default btn-file">
																<span class="fileinput-new">
																<spring:message code="selectimage"/> </span>
																<span class="fileinput-exists">
																<spring:message code="change"/> </span>
																<form:input path="uploadFile" type="file" ></form:input>
																</span>
																<a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput">
																<spring:message code="remove"/> </a>
															</div>
														</div>
													</div>
										
										</div>
										<spring:message code="weare.KeenThemes" var="KeenThemes"/>
										
										<div class="col-md-8">
										<div class="form-group">
										<h4><spring:message code="university.college"/></h4>
											<p>
												<form:input path="college" id="college" name="college" class="form-control" type="text"></form:input>
											</p>
										</div>
										<div class="form-group">
											<h4><spring:message code="career"/></h4>
											<p>
												<form:input path="career" id="career" name="career" class="form-control" type="text"></form:input>
											</p>
											</div>
										<div class="form-group">
											<label class="control-label"><spring:message code="about"/></label>
											<form:textarea path="about" class="form-control" id="about" name="about" rows="3" placeholder="${KeenThemes}"></form:textarea>
										</div>
										<div class="form-group">
												<label class="control-label"><spring:message code="about.more"/></label>
											<form:textarea path="aboutMore" class="form-control" id="aboutMore" name="aboutMore" rows="3" placeholder="${KeenThemes}"></form:textarea>
										</div>
										</div>
									</div>
								</div>
								<form:input type="hidden" path="userId" id="userId" name="userId"></form:input>
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
		  <spring:message code="copyright" /> Tutorías Online LLC &copy; 2015
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
<!-- END FOOTER -->
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {   
	
	

	$('#sample_editable_1').DataTable({
		"order": [[ 4, "desc" ]],
	   	 "lengthMenu": [
	           [5,10, 15, 20, -1],
	           [5,10, 15, 20, "All"] 
	       ],
	       "aoColumns": [null,
	                     null,
	                     null,
	                     null,
	                       {"bVisible": false},
	                         {"iDataSort": 4}                              
	                        ]
	});
	   var tableWrapper = $("#sample_editable_1_wrapper");

	   tableWrapper.find(".dataTables_length select").select2({
	       showSearchInput: false //hide search box with special css class
	   });
	
	
	
	
	 var isIE = /*@cc_on!@*/false || !!document.documentMode; // At least IE6
	 if(isIE){
		setInterval(function(){
			var url='<%=request.getContextPath()%>/admin/getUnaprovedTutListIE';
			 $.ajax({
				url:url,
				method:'GET',
				async :false,
				success:function(response){
					$( "<span class='badge badge-default'>" +response.length+"<span>").insertAfter( ".icon-bell" );
					$("#unapprovedSize").html('');
					$("#unapprovedSize").append(response.length+' <spring:message code="pending"/>');
					$("#unaprovedTutList").html('');
					$.each( response, function( index , value ) {
					$("#unaprovedTutList").append('<li><a href="<%=request.getContextPath()%>/admin/view-tutor?user='+value.userId+'">'+
							'<span class="details"><span class="label label-sm label-icon label-success"><i class="fa fa-plus"></i></span>'+
							value.fname+'&nbsp;<spring:message code="registered"/> </span></a></li>');
					});
				}
			}); 
		}, 5000);  
	 }
		if (!!window.EventSource) {
			   // console.log("Event source available");
			   var source = new EventSource('<%=request.getContextPath()%>/admin/getUnaprovedTutList');

			   source.addEventListener('message', function(e) {
				   var obj = JSON.parse(e.data);
				   $('.badge-default').remove();
					$( "<span class='badge badge-default'>" +obj.message.length+"<span>").insertAfter( ".icon-bell" );
					$("#unapprovedSize").html('');
					$("#unapprovedSize").append(obj.message.length+' <spring:message code="pending"/>');
					$("#unaprovedTutList").html('');
					$.each( obj.message, function( index , value ) {
					$("#unaprovedTutList").append('<li><a href="<%=request.getContextPath()%>/admin/view-tutor?user='+value.userId+'">'+
							'<span class="details"><span class="label label-sm label-icon label-success"><i class="fa fa-plus"></i></span>'+
							value.fname+'&nbsp;<spring:message code="registered"/> </span></a></li>');
					});
			   });

			   source.addEventListener('open', function(e) {
			        //// console.log("Connection was opened.");
			   }, false);

			   source.addEventListener('error', function(e) {
			        if (e.readyState == EventSource.CLOSED) {
			           // // console.log("Connection was closed.");
			        } else {
			           // // console.log(e.readyState);    
			        }
			   }, false);
			} else {
			        // console.log("No SSE available");
			}
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	  // TableEditable.init();
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
function getTutorInfo(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/getTutorProfile';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{userId:userId},
		success:function(response){
			if(response!=null){
				$("#fname").text(response.modelMap.admininfo.fname);
				$("#lname").text(response.modelMap.admininfo.lname);
				$("#college").val(response.modelMap.admininfo.college);
				$("#career").val(response.modelMap.admininfo.career);
				$("#about").val(response.modelMap.admininfo.about);
				$("#aboutMore").val(response.modelMap.admininfo.aboutMore);
				$('#profileImage').attr('src', response.modelMap.admininfo.imageName);
				$("#userId").val(userId);
				$('#responsive').modal('show');
			return true;
		}
		else{
			
		}
		}
	});
}
function setActive(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/active';
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
	var url='<%=request.getContextPath()%>/admin/de-active';
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
function setDelete(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/tutor-del';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{userId:userId},
			success:function(response){
				if(response=="success"){
					
				return true;
			}
			else{
				
			}
			}
		});
}

</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#tutorMenu").addClass("start active open");
	$("#tutorSelect").addClass("selected");
	$("#tutorOpen").addClass("arrow open");
	$("#historyTutor").addClass("active");
}
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>