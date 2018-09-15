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
<title><spring:message code="manageparent.title"></spring:message> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="content-type" content="text/plain; charset=ISO-8859-1"/>
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
			<spring:message code="parents"></spring:message> <small><spring:message code="manage.allparents"></spring:message> </small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"></spring:message> </a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manage-parents"><spring:message code="parents"></spring:message></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manage-parents"><spring:message code="header.manageparents"></spring:message> </a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
							<div class="alert alert-success" id="sucessMessage" style="display:none;">
								<strong><spring:message code="success"/></strong> <spring:message code="userDelete.success"/>
			</div>
			<iframe id="downloadParentList" style="display:none;"></iframe>
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.allparents"></spring:message>
							</div>
						
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
									</div>
									 <div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="tools"/><i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
											
												<li>
													<a href="javascript:;"  onclick="excelExport();">
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
									 <spring:message code="edit"/>
								</th>
								<th>
									 <spring:message code="action"/>
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
								<%-- <td>
									 ${tutorList.parentEmail}
								</td> --%>
								<c:if test="${tutorList.status=='Active'}">
								<td>
									<span class="label label-sm label-success">
									<spring:message code="active"/> </span>
								</td>
								</c:if>
								<c:if test="${tutorList.status=='Inactive'}">
								<td>
									<span class="label label-sm label-danger">
									<spring:message code="inactive"/> </span>
								</td>
								</c:if>
								<td class="center">
									 ${tutorList.newJoinDate}
								</td>
								<td class="center">
									 ${tutorList.joinDate}
								</td>
								<td>
									<a onclick="getTutorInfo('${tutorList.userId}');">
									<spring:message code="edit"/> </a>
								</td>
								<td>
									<a onclick="view('${tutorList.userId}');">
											<spring:message code="student.view"/> </a>
								<%-- 	<a class="delete12" onclick="confirmDelete('${tutorList.userId}');">
									<spring:message code="delete"/> </a> --%>
									
									<c:if test="${tutorList.status=='Active'}">
										<a onclick="setDeActive('${tutorList.userId}');">
											<spring:message code="block"/> </a></c:if>
									<c:if test="${tutorList.status=='Inactive'}">
										<a onclick="setActive('${tutorList.userId}');">
											<spring:message code="unblock"/></a>
									</c:if>
									
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
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorDetails" method="post" action="updateParentDetailsByAdmin" enctype="multipart/form-data">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"><span id="fname"></span> <span id="lname"></span> (<spring:message code="login.parent"/>)</h3>
								</div>
								<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="havesome.error"/>
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										<spring:message code="form.validation"></spring:message>
									</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-6">
										<div class="form-group">
										<h4><spring:message code="student.firstname"></spring:message> </h4>
											<p>
												<form:input path="fname" id="fname1" name="fname" class="form-control" type="text"></form:input>
											</p>
										</div>
										<div class="form-group">
											<h4><spring:message code="student.lastname"></spring:message> </h4>
											<p>
												<form:input path="lname" id="lname1" name="lname" class="form-control" type="text"></form:input>
											</p>
										</div>
										</div>
										<div class="col-md-6">
										<%-- <div class="form-group">
										<h4>Parent Email</h4>
											<p>
												<form:input path="parentEmail" id="parentEmail" name="parentEmail" class="form-control" type="text"></form:input>
											</p>
										</div> --%>
										<div class="form-group">
											<h4><spring:message code="country"></spring:message> </h4>
											<p>
											<form:select path="country" class="bs-select form-control" id="country" onchange="getTimeZone();">
												optgroup label='<spring:message code="spanishspeakingcountries"/>'>
												<c:forEach var="listSpanishCountry" items="${listSpanishCountry}">
                    										<option value='${listSpanishCountry.country_Id}'>${listSpanishCountry.country_Name}</option>
                    							</c:forEach>
												</optgroup>
												<optgroup label='<spring:message code="otherCountries"/>'>
												<c:forEach var="listOtherCountry" items="${listOtherCountry}">
                    										<option value='${listOtherCountry.country_Id}' >${listOtherCountry.country_Name}</option>
                    							</c:forEach>
												</optgroup>
											</form:select>
											</p>
										</div>
										<div class="form-group">
											<h4><spring:message code="parent.time"></spring:message> </h4>
											<p>
												<form:select path="timeZone" class="bs-select form-control" id="timeZone">
											</form:select>
											</p>
										</div>
										</div>
									</div>
								</div>
								<form:input type="hidden" path="userId" id="userId" name="userId"></form:input>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="save.changes"></spring:message></button>
								</div>
								</div>
								</form:form>
							</div>
							
<!-- Dialog end -->
<!-- New Dialog start -->
						<div id="studentParentDialog" class="modal fade" tabindex="-1" data-width="660">
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorDetails" method="post" action="updateStudentDetailsByAdmin" enctype="multipart/form-data">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"><spring:message code="profile"/> </h3>
								</div>
								<div class="modal-body">
									<!-- <div class="row"> -->
										<div class="row profile">
											<div class="col-md-12">
												<!--BEGIN TABS-->
												<div class="tabbable-line tabbable-full-width">
													<ul class="nav nav-tabs">
														<li class="active">
															<a href="#tab_1_1" data-toggle="tab">
															<spring:message code="login.parent"/> </a>
														</li>
														<li>
															<a href="#tab_1_3" data-toggle="tab">
															<spring:message code="login.student"/> </a>
														</li>
													</ul>
													<div class="tab-content">
														<div class="tab-pane active" id="tab_1_1">
															<div class="row">
																<div class="col-md-3">
																	<ul class="list-unstyled profile-nav">
																		<li>
																			<img src="<%=request.getContextPath()%>/admin/images/photo3.png" class="img-responsive" alt=""/>
																		</li>
																	</ul>
																</div>
																<div class="col-md-9">
																	<div class="row">
																		<div class="col-md-12 profile-info">
																			<ul class="list-unstyled profile-nav">
																		<li>
																			<a>
																			<spring:message code="full.name"/> : <span id="parentFName"></span> <span id="parentLName"></span></a>
																		</li>
																		<li>
																			<a>
																		<spring:message code="country"/> : <span id="parentCountry"></span></a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="parent.time"/> : <span id="parentTimeZone"></span></a>
																		</li>
																	</ul>
																		</div>
																	</div>
																	<!--end row-->
																</div>
															</div>
														</div>
														<!--tab_1_2-->
														<div class="tab-pane" id="tab_1_3">
															<div class="row">
																<div class="col-md-3">
																	<ul class="list-unstyled profile-nav">
																		<li>
																			<img src="<%=request.getContextPath()%>/admin/images/photo3.png" class="img-responsive" alt=""/>
																		</li>
																	</ul>
																</div>
																<div class="col-md-9">
																	<div class="row">
																		<div class="col-md-12 profile-info">
																			<ul class="list-unstyled profile-nav">
																		<li>
																			<a>
																			<spring:message code="full.name"/> : <span id="studentFName"></span> <span id="studentLName"></span></a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="date.ofbirth"/> : <span id="studentDOB"></span>
																			</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="country"/> : <span id="studentCountry"></span></a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="parent.time"/> : <span id="studentTimeZone"></span></a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="education.type"/> : <span id="studentEducationType"></span></a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="student.grade"/> : <span id="studentLevel"></span></a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="career"/> : <span id="studentCareer"></span></a>
																		</li>
																		<!-- <li>
																			<a>
																			Plan : <span id="studentPlan"></span></a>
																		</li> -->
																	</ul>
																		</div>
																	</div>
																	<!--end row-->
																</div>
															</div>
														</div>
														<!--end tab-pane-->
													</div>
												</div>
												<!--END TABS-->
											</div>
										</div>
										
									<!-- </div> -->
								</div>
								<form:input type="hidden" path="userId" id="userId" name="userId"></form:input>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
								</div>
								</div>
								</form:form>
						</div>
						<!-- New Dialog end -->
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
	   ComponentsDropdowns.init();
	   ComponentsPickers.init();
});
</script>
<script type="text/javascript">
/* var tableToExcel = (function() {
	  var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
	})(); */
function getTutorInfo(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/getParentProfile';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{userId:userId},
		success:function(response){
			if(response!=null){
				$("#fname").text(response.modelMap.admininfo.fname);
				$("#lname").text(response.modelMap.admininfo.lname);
				$("#fname1").val(response.modelMap.admininfo.fname);
				$("#lname1").val(response.modelMap.admininfo.lname);
				/* $("#dob").val(response.modelMap.admininfo.dob); */
				$("#country").val(response.modelMap.admininfo.country); 
				
				$('#timeZone').children().remove();
				 $.each( response.modelMap.admininfo.zoneMap, function( key , value ) {
				$('#timeZone').append('<option value="'+key+'">'+value+'</option>');
				 });
				
				 $("#timeZone").val(response.modelMap.admininfo.timeZone); 
				
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
	//var userId = userId;
	//alert("userid===="+userId);
	var url='<%=request.getContextPath()%>/admin/active';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{userId:userId},
			success:function(response){
				if(response=="success"){
					//alert("Activated");
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


/* function confirmDelete(userId){
	var r = confirm('<spring:message code="sure.deleterecord"/>');
	if (r == true) {
		setDelete(userId);
		
	}
	
} */

<%-- function setDelete(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/tutor-del';
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
} --%>
function getTimeZone() {
	var country = $("#country").val();
	var url='<%=request.getContextPath()%>/getTimeZone';
	$.ajax({
		dataType: "json",
		contentType: "application/json",
		url:url,
		method:'GET',
		async :false,
		data:{country:country},
		success:function(response){
			
			$('#timeZone').children().remove();
			 $.each( response.modelMap.zoneList, function( key , value ) {
			$('#timeZone').append('<option value="'+key+'">'+value+'</option>');
			 });
			
		}
		
	}); 
}
function view(userId){
	$('#studentParentDialog').modal('show');
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/getParentStudentProfile';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{userId:userId},
		success:function(response){
			if(response!=null){
				$("#studentFName").text(response.modelMap.dtoStudentParentRelationship.studentFName);
				$("#studentLName").text(response.modelMap.dtoStudentParentRelationship.studentLName);
				$("#studentDOB").text(response.modelMap.dtoStudentParentRelationship.studentDOB);
				$("#studentCountry").text(response.modelMap.dtoStudentParentRelationship.studentCountry);
				$("#studentTimeZone").text(response.modelMap.dtoStudentParentRelationship.studentTimeZone);
				$("#studentEducationType").text(response.modelMap.dtoStudentParentRelationship.studentEducationType);
				$("#studentLevel").text(response.modelMap.dtoStudentParentRelationship.studentLevel);
				$("#studentCareer").text(response.modelMap.dtoStudentParentRelationship.studentCareer);
				/* $("#studentPlan").text(response.modelMap.dtoStudentParentRelationship.studentPlan); */
				
				
				$("#parentFName").text(response.modelMap.dtoStudentParentRelationship.parentFName);
				$("#parentLName").text(response.modelMap.dtoStudentParentRelationship.parentLName);
				$("#parentCountry").text(response.modelMap.dtoStudentParentRelationship.parentCountry);
				$("#parentTimeZone").text(response.modelMap.dtoStudentParentRelationship.parentTimezone);
				
				$('#studentParentDialog').modal('show');
			return true;
		}
		
		}
	});
}
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#parentMenu").addClass("start active open");
	$("#parentSelect").addClass("selected");
	$("#parentOpen").addClass("arrow open");
	$("#manageParent").addClass("active");
}

function excelExport(){
	var url='<%=request.getContextPath()%>/admin/parentExcelExport';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		success:function(response){
			if(response=="success"){
				//$("#excelExport").show();
				var url = window.location.origin+'<%=request.getContextPath()%>/Parents-List.xlsx';
				$("#downloadParentList").attr('src',url);
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

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>