<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
<title>MiProfe | <spring:message code="manage.payments"/> </title>
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
			<spring:message code="payments"/> <small><spring:message code="manage.payments"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manageAllPayments"><spring:message code="payments"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manageAllPayments"><spring:message code="manage.payments"/></a>
					</li>
				</ul>
			</div>

			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.payments"/>
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
									 <spring:message code="tutor.email"/> 
								</th>
								
								<th>
									 <spring:message code="activity.name"/> 
								</th>
								<th>
									 <spring:message code="student.activity"/> <spring:message code="student.date"/> 
								</th>
								<th>
									 <spring:message code="student.activity"/> <spring:message code="student.date"/> 
								</th>
								<th>
									 <spring:message code="activity.amount"/>
								</th>
								
								<th>
									  <spring:message code="action"/>
								</th>
							</tr>
							</thead>
							<tbody>
						<c:forEach var="tutorAccountActivities" items="${tutorAccountActivities}">
							<c:if test="${tutorAccountActivities.isDeleted eq 'N'}"> 
							<tr>
								<td>
									 ${tutorAccountActivities.tutorEmail}
								</td>
								
								<td>
									${tutorAccountActivities.activityName}
								</td>
								<td>
									${tutorAccountActivities.newActivityDate}
								</td>
								<td>
								<fmt:formatDate  pattern="dd-MM-yy"  value="${tutorAccountActivities.activityDate}" />
								</td>
								<td>
									${tutorAccountActivities.activityAmount}
								</td>
								
								
								<td>
								 <c:set var="theString" value='${tutorAccountActivities.activityAmount}'/>
							<c:if test="${not fn:containsIgnoreCase(theString, '-')}">
								<a onclick="cancelPayment('${tutorAccountActivities.activityId}');">
								<spring:message code="cancel"/> </a> &nbsp;&nbsp;&nbsp;
								<a onclick="payNow('${tutorAccountActivities.activityId}');">
								<spring:message code="pay"/> </a>
							</c:if>
								
								</td>
							</tr>
							</c:if>
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
		 <spring:message code="copyright" /> Tutorías Online LLC &copy; 2015
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
		 //   TableEditable.init(); 
		   FormValidation.init();
		    ComponentsDropdowns.init();
		   ComponentsPickers.init(); 
		   
	
			
});
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#paymentMenu").addClass("start active open");
	$("#paymentSelect").addClass("selected");
	$("#paymentOpen").addClass("arrow open");
	$("#managePayment").addClass("active");
}

function cancelPayment(acivityId){
	var url='<%=request.getContextPath()%>/admin/cancelTutorPayment';
	 $.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{acivityId:acivityId},
		success:function(response){
			location.reload();
		}
	});
} 


function payNow(acivityId){
	var url='<%=request.getContextPath()%>/admin/payTutorPayment';
	 $.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{acivityId:acivityId},
		success:function(response){
			location.reload();
		}
	});
}

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>