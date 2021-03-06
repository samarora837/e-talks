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
<title>MiProfe | <spring:message code="student.promotiontitle"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="content-type" content="text/plain; charset=ISO-8859-1"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet" type="text/css"/>
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
			<spring:message code="promotional.minuteamount"/> <small><spring:message code="student.adddeduct"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manage-students-minute"><spring:message code="promotional.minuteamount"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manage-students-minute"><spring:message code="student.adddeduct"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<c:if test="${sucessMessage eq 'Y' }">
			<div class="alert alert-success" id="sucessMessage">
								<strong><spring:message code="success"/></strong> <spring:message code="minuteadded.success"/>
			</div>
			<%session.removeAttribute("sucessMessage");%>
			</c:if>
			<c:if test="${deductMessage eq 'Y' }">
			<div class="alert alert-success" id="deductMessage">
								<strong><spring:message code="success"/></strong> <spring:message code="minutededucted.success"/>
			</div>
			<%session.removeAttribute("deductMessage");%>
			</c:if>
	         
			<iframe id="downloadStudentList" style="display:none;"></iframe>
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="student.adddeduct"/>
							</div>
							
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
									</div>
									<div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="tools"/> <i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
											
												<li>
													<a href="javascript:;" onclick="excelExport();">
													<spring:message code="export.toexcel"/> </a>
												</li>
											</ul>
										</div>
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
								<!-- <th>
									 Parent Email
								</th> -->
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
									 <spring:message code="country"/>
								</th>
								<th>
									 <spring:message code="parent.time"/>
								</th>
								<th>
									 <spring:message code="parent.minBalance"/>
								</th>
								<th>
									 <spring:message code="adddeduct.minute"/>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="studentList" items="${studentList}">
							<tr>
								<td>
									 ${studentList.fullName}
								</td>
								<td>
									 <a href="mailto:${studentList.studentEmail}">
									${studentList.studentEmail} </a>
								</td>
								<%-- <td>
									 ${tutorList.parentEmail}
								</td> --%>
								
								
								<c:if test="${studentList.loginStatus=='Active'}">
								<td>
									<span class="label label-sm label-success">
									<spring:message code="active"/> </span>
								</td>
								</c:if>
								<c:if test="${studentList.loginStatus=='Inactive'}">
								<td>
									<span class="label label-sm label-danger">
									<spring:message code="inactive"/>  </span>
								</td>
								</c:if>
								
									<td class="center">
								
								${studentList.newJoinDate}
								
								</td>
								<td class="center">
								
								${studentList.joinDate}
								
								</td>
								<td class="center">
									${studentList.country}
								</td>
								<td>
									${studentList.timeZone}
								</td>
								<td>
									${studentList.minBalance}
									
								</td>
								<td>
									<a onclick="addMinute('${studentList.fullName}','${studentList.studentProfileId}');">
									<spring:message code="add.minute"></spring:message> </a>
									<a onclick="deductMinute('${studentList.fullName}','${studentList.studentProfileId}');">
									<spring:message code="deduct.minute"></spring:message> </a>
									
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
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
		 <spring:message code="copyright" /> Tutor�as Online LLC &copy; 2015
	</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
<!-- END FOOTER -->




<!-- Start ADD Minute PopUp -->

	<div id="addMinutePopup" class="modal fade" tabindex="-1" data-width="500">
							<form name="form_sample_1" id="form_sample_1" method="post" action="<%=request.getContextPath()%>/admin/studentAddMinute">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title green"> <spring:message code="adddeduct.minute"/></h3>
								</div>
								
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
										
										<div class="form-group">
											<h4> <spring:message code="to"/> : </h4>
											
										</div>
										
										<div class="form-group">
											<h4> <spring:message code="minutes"/> : </h4>
											
										</div>
										
										</div>
										
								<div class="col-md-6">
								
									<div class="form-group">
											<h4></h4>
											<p id="studentFullNameAdd">
											
											</p>
										</div>
								
									
										<div class="form-group">
										
											<p>
												<input type="text" name="minutesAdd" id="minutesAdd" />
											</p>
										</div>
										
							</div>
										
									</div>
								</div>
								
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="add.minute"/> <spring:message code="minutes"/></button>
								</div>
								</div>
								
								<input type="hidden" id="tostudentProfileIdAdd" name="tostudentProfileIdAdd">
								</form>
			</div>


<!-- End Add Minute PopUp -->


<!-- Start Deduct Minute PopUp -->

	<div id="deductMinutePopup" class="modal fade" tabindex="-1" data-width="500">
							<form name="form_sample_11" id="form_sample_11" method="post" action="<%=request.getContextPath()%>/admin/studentDeductMinute">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title green"> <spring:message code="adddeduct.minute"/></h3>
								</div>
								
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
										
										<div class="form-group">
											<h4> <spring:message code="to"/> : </h4>
											
										</div>
										
										<div class="form-group">
											<h4> <spring:message code="minutes"/> : </h4>
											
										</div>
										
										</div>
										
								<div class="col-md-6">
								
									<div class="form-group">
											<h4></h4>
											<p id="studentFullNameDeduct">
											
											</p>
										</div>
								
									
										<div class="form-group">
										
											<p>
												<input type="text" name="minutesDeduct" id="minutesDeduct" />
											</p>
										</div>
										
							</div>
										
									</div>
								</div>
								
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="deduct.minute"/> <spring:message code="minutes"/></button>
								</div>
								</div>
								<input type="hidden" id="tostudentProfileIdDeduct" name="tostudentProfileIdDeduct"/>
								
								</form>
			</div>


<!-- End Deduct Minute PopUp -->

<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>

<!-- END JAVASCRIPTS -->
</body>

<script type="text/javascript">
 jQuery(document).ready(function() {  
	 
	 setTimeout(function(){
			$("#sucessMessage").hide();
		}, 5000);
	 setTimeout(function(){
			$("#deductMessage").hide();
		}, 5000);
	 
	 
	 
	 

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
		                         null,
		                         null,
		                         null
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
	   //TableEditable.init();
	   FormValidation.init();
	   ComponentsDropdowns.init();
	   ComponentsPickers.init();
});
 
</script>

<script type="text/javascript">
function navigationUpdate(){
	$("#promotionalMinuteAmount").addClass("start active open");
	$("#studentMinute").addClass("selected");
	$("#studentMinuteOpen").addClass("arrow open");
	$("#manageStudentMinute").addClass("active");
}



function excelExport(){
	var url='<%=request.getContextPath()%>/admin/studentExcelExport';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		success:function(response){
			if(response=="success"){
				//$("#excelExport").show();
				var url = window.location.origin+'<%=request.getContextPath()%>/Students-List.xlsx';
				$("#downloadStudentList").attr('src',url);
				/* setTimeout(function(){
					location.reload();
				}, 3000); */
				
				
			return true;
		}
		else{
			
		}
		}
	});
}


function addMinute(studentName,studentProfileId){
	
	$("#studentFullNameAdd").text(studentName);
	$("#tostudentProfileIdAdd").val(studentProfileId);
	
	$('#addMinutePopup').modal('show');
}

function deductMinute(studentName,studentProfileId){
	
	$("#studentFullNameDeduct").text(studentName);
	$("#tostudentProfileIdDeduct").val(studentProfileId);
	
	$('#deductMinutePopup').modal('show');
}

</script>


<!-- END BODY -->
</html>