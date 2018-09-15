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
<title>MiProfe | <spring:message code="reporting"/></title>
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
	<%@ include file="/WEB-INF/views/views-admin/cus-support/header.jsp" %>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<%@ include file="/WEB-INF/views/views-admin/cus-support/nav-sidebar.jsp" %>
	<!-- END SIDEBAR -->
	<!-- BEGIN CONTENT -->
	
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			 <small><spring:message code="review.homework"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/cus-care/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/cus-care/scheduledSessions"><spring:message code="reporting"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/cus-care/reviewSessions"><spring:message code="review.homework"/></a>
					</li>
				</ul>
			</div>

		
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="review.homeworkrecords"/>
							</div>
						
						</div>
						<div class="portlet-body">
							
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
								<th>
									 <spring:message code="deliverytime.review"/> 
								</th>
								<th>
									 <spring:message code="deliverytime.review"/> 
								</th>
								<th>
									 <spring:message code="status"/>
								</th>
								
								
								<th>
									 <spring:message code="login.tutor"/>
								</th>
								
								<th>
									 <spring:message code="tutorCountry"/>
								</th>
								
								<th>
									 <spring:message code="students"/>
								</th>
								
								<th>
									 <spring:message code="studentCountry"/>
								</th>
								
								
								<th>
									 <spring:message code="subject"/> 
								</th>
								<th>
									 <spring:message code="equilavent.rate"/> 
								</th>
								<th>
									 <spring:message code="student.reviewquestion"/>
								</th>
								<th>
									 <spring:message code="studentfile"/>
								</th>
								
								<th>
									 <spring:message code="tutorcomment"/>
								</th>
								
								<th>
									  <spring:message code="tutorsolutionfile"/>
								</th>
								
								
								
								
							</tr>
							</thead>
							<tbody>
							<c:forEach var="reviewSessionReportDetails" items="${reviewSessionReportDetails}">
							<tr>
							<td>
									 ${reviewSessionReportDetails.newBookingDate}
									 
								</td>
								<td>
									 ${reviewSessionReportDetails.dateSession}
									 
								</td>
								
								<c:if test="${reviewSessionReportDetails.reviewSessionStatus=='completed'}">
								<td>
									<span class="label label-sm label-success">
									<spring:message code="completed"/>  </span>
								</td>
								</c:if>
								<c:if test="${reviewSessionReportDetails.reviewSessionStatus=='accepted'}">
								<td>
									<span class="label label-sm label-warning">
									<spring:message code="accepted"/>  </span>
								</td>
								</c:if>
								<c:if test="${reviewSessionReportDetails.reviewSessionStatus=='pending'}">
								<td>
									<span class="label label-sm label-info">
									<spring:message code="pending"/>  </span>
								</td>
								</c:if> 
								
								<c:if test="${reviewSessionReportDetails.reviewSessionStatus=='deleted'}">
								<td>
									<span class="label label-sm label-danger">
									<spring:message code="deleted"/>  </span>
								</td>
								</c:if> 
								
								
								<td>
									${reviewSessionReportDetails.tutorEmail} 
								</td>
								
								<td>
									${reviewSessionReportDetails.tutorCountry} 
								</td>
								
								<td>
									${reviewSessionReportDetails.studentEmail} 
								</td>
								<td>
									 ${reviewSessionReportDetails.studentCountry}
								</td>
								
								<td class="center">
									${reviewSessionReportDetails.subjectName}
								</td>
								
								<td>
									 ${reviewSessionReportDetails.reviewDuration}
								</td>
								
								<td>
									 ${reviewSessionReportDetails.reviewQuestion}
								</td>
								
								<td class="text-center">
								<c:if test="${reviewSessionReportDetails.studentDocPath eq 'NA'}">
								<a href="javascript:void(0);" >${reviewSessionReportDetails.studentDocName}</a>
								</c:if>
								<c:if test="${reviewSessionReportDetails.studentDocPath ne 'NA'}">
								<a href="<%=request.getContextPath()%>${reviewSessionReportDetails.studentDocPath}" download>${reviewSessionReportDetails.studentDocName}</a>
								</c:if>
								</td>
								
								<td>
									 ${reviewSessionReportDetails.tutorComments}
								</td>
								
								<td class="text-center">
								<c:if test="${reviewSessionReportDetails.tutorDocPath eq 'NA'}">
								<a href="javascript:void(0);" >${reviewSessionReportDetails.tutorDocName}</a>
								</c:if>
								<c:if test="${reviewSessionReportDetails.tutorDocPath ne 'NA'}">
								<a href="<%=request.getContextPath()%>${reviewSessionReportDetails.tutorDocPath}" download>${reviewSessionReportDetails.tutorDocName}</a>
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
<
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
function navigationUpdate(){
	$("#reporting").addClass("start active open");
	$("#reportingSelect").addClass("selected");
	$("#reportingOpen").addClass("arrow open");
	$("#reviewReports").addClass("active");
}




</script>

<script type="text/javascript">
jQuery(document).ready(function() {
	
	
	 
	
	
	$('#sample_editable_1').DataTable({
		 "order": [[ 0, "desc" ]],
	   	 "lengthMenu": [
	           [5,10, 15, 20, -1],
	           [5,10, 15, 20, "All"] 
	       ],
	       "aoColumns": [
	                       {"bVisible": false},
	                         {"iDataSort": 0},                              
	                         null,
	                         null,
	                         null,
	                         null,
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
	
	
	
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	  // TableEditable.init();
	   FormValidation.init();
});
</script>

<!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>