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
<title>MiProfe | <spring:message code="student.ranking"/></title>
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
			<spring:message code="student"/> <small><spring:message code="student.ranking"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/studentDetails"><spring:message code="reporting"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/studentRanking"><spring:message code="student.ranking"/></a>
					</li>
				</ul>
			</div>

			
			<iframe id="downloadStudentList" style="display:none;"></iframe>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="student.ranking"/>
							</div>
						
						</div>
						
						
						
						
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<form id="filterData" name="filterData" method="POST" action="<%=request.getContextPath()%>/admin/studentRankingFiltered">
							<div class="input-group input-large date-picker input-daterange  pull-left" data-date="10-11-15" data-date-format="dd-mm-yyyy">
												<input type="text" class="form-control"   onclick="hideDateError();" name="fromdate" id="fromdate"  value="${fromDate}" placeholder="Start Date" >
												<span class="input-group-addon">
												<spring:message code="to"/>  </span>
												<input type="text" class="form-control"   onclick="hideDateError();" name="todate" id="todate"  value="${toDate}" placeholder="End Date">
											</div>
							
							</form>
							<button class="btn green" onclick="getSelectedStudentRankingDetails();">
											<spring:message code="home.search"/> 
											</button>
											<span class="help-block" id="dateError" style="display:none;"> Please correct the error </span>
									</div>
									
									 <div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown"><spring:message code="tools"/> <i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
<!-- 												<li>
													<a href="javascript:;">
													Print </a>
												</li>
												<li>
													<a href="javascript:;">
													Save as PDF </a>
												</li> -->
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
								<th style="width:150px !important;">
									 <spring:message code="full.name"/> 
								</th>
								<th style="width:150px !important;">
									 <spring:message code="login.email"/> 
								</th>
								
								<th style="width:100px !important;">
									  <spring:message code="join.date"/>
								</th>
								
								<th style="width:100px !important;">
									  <spring:message code="join.date"/>
								</th>
								
								 <th style="width:100px !important;">
									 <spring:message code="country"/>
								</th>
								
								<th style="width:100px !important;">
									  <spring:message code="student.plan"/>
								</th>
								
								<th style="width:250px !important;">
									  <spring:message code="minutes.spent"/>
								</th>
								
								<!--th style="width:250px !important;">
									  <spring:message code="student.minuteBalance"/>
								</th-->
								
								<th style="width:250px !important;">
									  <spring:message code="money.spend"/>
								</th>
								
								<th style="width:250px !important;">
									  <spring:message code="currency"/>
								</th>
								<th style="width:250px !important;">
									 <spring:message code="action"/>
								</th>
								
								
								
								
							</tr>
							</thead>
							<tbody id="tdata">
							<c:forEach var="studentList" items="${studentList}">
							<tr>
								<td>
									 ${studentList.fullName}
								</td>
								<td>
									 <a href="mailto:${studentList.studentEmail}">
									${studentList.studentEmail} </a>
								</td>
								
								<td class="center">
									${studentList.newJoinDate}
								</td>
								
								<td class="center">
									${studentList.joinDate}
								</td>
								
								<td>
								${studentList.country}
								
								</td> 
								<td>
									${studentList.plan}
								</td>
								<td>
									${studentList.totalMinutesSpent}
								</td>
								<td>
									${studentList.minBalance}
								</td>
								<td>
									${studentList.totalMoneySpent}
								</td>
								<td>
									${studentList.currencyName}
								</td>
								<td>
									<a onclick="viewDetails('${studentList.studentProfileId}');">
									<spring:message code="parent.view"/> </a>
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
						
							






						<!-- Session Dialog Start-->
 						<div id="studentSessionDialog" class="modal fade" tabindex="-1" data-width="750">
							<%-- <form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorDetails" method="post" action="updateStudentDetailsByAdmin" enctype="multipart/form-data"> --%>
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"><spring:message code="student.activities"/></h3>
								</div>
								<div class="modal-body">
									<!-- <div class="row"> -->
										<div class="row profile st-session-dialog-mg">
											<div class="col-md-12">
												<!--BEGIN TABS-->
												<div class="tabbable-line tabbable-full-width">
													<!-- <div class="tab-content"> -->
														
														<!--tab_1_2-->
														<div class="tab-pane" id="tab_1_5">
															<div class="row">
																<table class="table table-striped table-hover table-bordered" id="sample_editable_3">
																<thead>
											                      <tr>
											                       <th>
																		 <spring:message code="parent.activity"/> 
																	</th>
																	<th>
																		  <spring:message code="student.date"/>
																	</th>
																	
																	<th>
																		  <spring:message code="student.date"/>
																	</th>
																	
																	<th>
																		  <spring:message code="student.minutes"/>
																	</th>
																	
																	<th>
																		  <spring:message code="amount"/>
																	</th>
																	
											                      </tr>
																</thead>
																<tbody id="session_details">
									
																</tbody>
																</table>
															</div>
														</div>
														<!--end tab-pane-->
													<!-- </div> -->
												</div>
												<!--END TABS-->
											</div>
										</div>
										
									<!-- </div> -->
								</div>
								<%-- <form:input type="hidden" path="userId" id="userId" name="userId"></form:input> --%>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
								</div>
								</div>
								<%-- </form:form> --%>
						</div>

						<!-- Session Dialog End  -->


							
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
		"order": [[ 2, "desc" ]],
	   	 "lengthMenu": [
	           [5,10, 15, 20, -1],
	           [5,10, 15, 20, "All"] 
	       ],
	       "aoColumns": [null,
	                     null,
	                       {"bVisible": false},
	                         {"iDataSort": 2},                              
	                         null,
	                         null,
	                         null,
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
	
	
	setTimeout(function(){
		$("#sucessMessage").hide();
	}, 5000);
	
	
	
	
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
	  /*  TableEditable.init(); */
	   FormValidation.init();
});
</script>

<script type="text/javascript">
function navigationUpdate(){
	$("#reporting").addClass("start active open");
	$("#reportingSelect").addClass("selected");
	$("#reportingOpen").addClass("arrow open");
	$("#studentRanking").addClass("active");
}





function excelExport(){
	var fromdate='${fromDate}';
	var todate=	'${toDate}';
	var url='<%=request.getContextPath()%>/admin/studentFilteredRankingExcelExport';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{fromdate:fromdate,todate:todate},
		success:function(response){
			if(response=="success"){
				var url = window.location.origin+'<%=request.getContextPath()%>/Student-Activity-List-Filtered.xlsx';
				$("#downloadStudentList").attr('src',url);
			return true;
		}
		else{
		}
		}
	});
}


 
 function getSelectedStudentRankingDetails(){
		
	 var fromDate = $("#fromdate").val();
	 var toDate = $("#todate").val();
	   
	 
	 if((fromDate!="") && (toDate!="")){
		 var pattern = /(\d{2})\-(\d{2})\-(\d{4})/;
		 fromDate = new Date(fromDate.replace(pattern,'$3-$2-$1'));
		 toDate = new Date(toDate.replace(pattern,'$3-$2-$1'));
		 if(fromDate > toDate){
			 
			 $("#dateError").show();
		 }
		 
		 else{
			 
			 
			 $("#filterData").submit();
		 }
	 }
	 
	 else if((fromDate=="") || (toDate=="")){
		 
		 $("#dateError").show();
	 }
	 
	// $("#filterData").submit();
}
 function hideDateError(){
	 $("#dateError").hide();
 }

</script>

<script type="text/javascript">
function viewDetails(studentProfileId){
	var tableData="";
	$('#sample_editable_3').dataTable().fnDestroy();
	var url='<%=request.getContextPath()%>/admin/getStudentAllActivityDetails';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{studentProfileId:studentProfileId},
		success:function(response){
			if(response!=null){
				$('#session_details').html('');
				$.each(response.modelMap.studentActivityList, function(index,value) {
						tableData+='<tr>'+
						'<td>'+ value.activityName +'</td>'+
						'<td>'+ value.newActivityDate +'</td>'+
						'<td>'+ value.activityDate +'</td>'+
						'<td>'+ value.activityMinutes +'</td>'+
						'<td>'+ value.activityAmout +'</td>'+
						'</tr>';
					});
					 $('#session_details').html(tableData);
			}
			else{
			}
			$('#sample_editable_3').dataTable().fnDestroy();
			
			$('#sample_editable_3').DataTable({
				"order": [[ 1, "desc" ]],
			   	 "lengthMenu": [
			           [5,10, 15, 20, -1],
			           [5,10, 15, 20, "All"] 
			       ],
			       "aoColumns": [null,
			                       {"bVisible": false},
			                         {"iDataSort": 1},                              
			                         null,
			                         null
			                        ]
			});
			   var tableWrapper = $("#sample_editable_3_wrapper");

			   tableWrapper.find(".dataTables_length select").select2({
			       showSearchInput: false //hide search box with special css class
			   });
			
			
			$('#studentSessionDialog').modal('show');
		}
	});
} 
</script>

<!-- END JAVASCRIPTS -->




</body>
<!-- END BODY -->
</html>