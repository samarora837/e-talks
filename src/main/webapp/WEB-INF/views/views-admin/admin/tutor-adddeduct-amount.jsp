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
<title>MiProfe | <spring:message code="tutor.promotiontitle"/></title>
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
			<spring:message code="promotional.minuteamount"/> <small><spring:message code="tutor.adddeduct"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manageTutorAmount"><spring:message code="promotional.minuteamount"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manageTutorAmount"><spring:message code="tutor.adddeduct"/></a>
					</li>
				</ul>
			</div>
			<c:if test="${sucessMessage eq 'Y' }">
			<div class="alert alert-success" id="sucessMessage">
								<strong><spring:message code="success"/></strong> <spring:message code="amountadded.success"/>
			</div>
			<%session.removeAttribute("sucessMessage");%>
			</c:if>
			<c:if test="${deductMessage eq 'Y' }">
			<div class="alert alert-success" id="deductMessage">
								<strong><spring:message code="success"/></strong> <spring:message code="amountdeducted.success"/>
			</div>
			<%session.removeAttribute("deductMessage");%>
			</c:if>
			
			<iframe id="downloadTutorList" style="display:none;"></iframe>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="tutor.adddeduct"/>
							</div>
						
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<%-- <div class="btn-group"><a href="<%=request.getContextPath()%>/sys-admin/signup-admin">
											<button class="btn green">
											Add New <i class="fa fa-plus"></i>
											</button></a>
										</div> --%>
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
								<th class="center" style="width:70px !important;">
									 <spring:message code="status"/>
								</th>
								<th >
									  <spring:message code="join.date"/>
								</th>
								<th style="width:100px !important;">
									  <spring:message code="join.date"/>
								</th>
								
								 <th style="width:100px !important;">
									 <spring:message code="country"/>
								</th>
								
								<th style="width:100px !important;">
									  <spring:message code="parent.time"/>
								</th>
								<th style="width:100px !important;">
									  <spring:message code="money.earned"/>
								</th>
								<th style="width:250px !important;">
									  <spring:message code="adddeduct.amount"/>
								</th>
								
							</tr>
							</thead>
							<tbody>
							<c:forEach var="tutorList" items="${tutorList}">
							<tr>
								<td>
									 ${tutorList.fullName}
								</td>
								<td>
									 <a href="mailto:${tutorList.email}">
									${tutorList.email} </a>
								</td>
								
								<c:if test="${tutorList.status=='Active'}">
								<td>
									<span class="label label-sm label-success">
									<spring:message code="active"/> </span>
								</td>
								</c:if>
								<c:if test="${tutorList.status=='Inactive'}">
								<td>
									<span class="label label-sm label-danger">
									<spring:message code="inactive"/>  </span>
								</td>
								</c:if>
								
								<td class="center">
									${tutorList.newJoinDate}
								</td>
								
								<td class="center">
									${tutorList.joinDate}
								</td>
								
								<td>
								${tutorList.countryName}
								
								</td> 
								<td>
									${tutorList.timezoneName}
								</td>
								<td>
									${tutorList.totalAmountEarned}
								</td>
								<td>
									<a onclick="addAmount('${tutorList.fullName}','${tutorList.tutorProfileId}');">
									<spring:message code="add.minute"></spring:message> </a>
									<a onclick="deductAmount('${tutorList.fullName}','${tutorList.tutorProfileId}');">
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
<!-- Dialog start -->
<!-- responsive -->
						
							
							
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




<!-- Start ADD Minute PopUp -->

	<div id="addAmountPopup" class="modal fade" tabindex="-1" data-width="500">
							<form name="form_sample_1" id="form_sample_1" method="post" action="<%=request.getContextPath()%>/admin/tutorAddAmount">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title green"> <spring:message code="adddeduct.amount"/></h3>
								</div>
								
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
										
										<div class="form-group">
											<h4> <spring:message code="to"/> : </h4>
											
										</div>
										
										<div class="form-group">
											<h4> <spring:message code="amount"/> : </h4>
											
										</div>
										
										</div>
										
								<div class="col-md-6">
								
									<div class="form-group">
											<h4></h4>
											<p id="tutorFullNameAdd">
											
											</p>
										</div>
								
									
										<div class="form-group">
										
											<p>
												<input type="text" name="amountAdd" id="amountAdd" />
											</p>
										</div>
										
							</div>
										
									</div>
								</div>
								
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="add.minute"/> <spring:message code="amount"/></button>
								</div>
								</div>
								
								<input type="hidden" id="toTutorProfileIdAdd" name="toTutorProfileIdAdd">
								</form>
			</div>


<!-- End Add Minute PopUp -->


<!-- Start Deduct Minute PopUp -->

	<div id="deductAmountPopup" class="modal fade" tabindex="-1" data-width="500">
							<form name="form_sample_11" id="form_sample_11" method="post" action="<%=request.getContextPath()%>/admin/tutorDeductAmount">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title green"> <spring:message code="adddeduct.amount"/></h3>
								</div>
								
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
										
										<div class="form-group">
											<h4> <spring:message code="to"/> : </h4>
											
										</div>
										
										<div class="form-group">
											<h4> <spring:message code="amount"/> : </h4>
											
										</div>
										
										</div>
										
								<div class="col-md-6">
								
									<div class="form-group">
											<h4></h4>
											<p id="tutorFullNameDeduct">
											
											</p>
										</div>
								
									
										<div class="form-group">
										
											<p>
												<input type="text" name="amountDeduct" id="amountDeduct" />
											</p>
										</div>
										
							</div>
										
									</div>
								</div>
								
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="deduct.minute"/> <spring:message code="amount"/></button>
								</div>
								</div>
								<input type="hidden" id="tostutorProfileIdDeduct" name="tostutorProfileIdDeduct"/>
								
								</form>
			</div>


<!-- End Deduct Minute PopUp -->





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
	 setTimeout(function(){
			$("#deductMessage").hide();
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
	   //TableEditable.init();
	   FormValidation.init();
});
</script>

<script type="text/javascript">
function navigationUpdate(){
	$("#promotionalMinuteAmount").addClass("start active open");
	$("#studentMinute").addClass("selected");
	$("#studentMinuteOpen").addClass("arrow open");
	$("#manageTutorAmout").addClass("active");
}





function excelExport(){
	var url='<%=request.getContextPath()%>/admin/tutorExcelExport';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		success:function(response){
			if(response=="success"){
				//$("#excelExport").show();
				var url = window.location.origin+'<%=request.getContextPath()%>/Tutors-List.xlsx';
				$("#downloadTutorList").attr('src',url);
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



function addAmount(tutorName,tutorProfileId){
	
	$("#tutorFullNameAdd").text(tutorName);
	$("#toTutorProfileIdAdd").val(tutorProfileId);
	
	$('#addAmountPopup').modal('show');
}

function deductAmount(tutorName,tutorProfileId){
	
	$("#tutorFullNameDeduct").text(tutorName);
	$("#tostutorProfileIdDeduct").val(tutorProfileId);
	
	$('#deductAmountPopup').modal('show');
}


</script>
<!-- END JAVASCRIPTS -->




</body>
<!-- END BODY -->
</html>