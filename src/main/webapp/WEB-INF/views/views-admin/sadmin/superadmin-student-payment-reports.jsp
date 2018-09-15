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
<title><spring:message code="student.payment.report"></spring:message> </title>
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
			<spring:message code="student"></spring:message> <small><spring:message code="student.payment.report"></spring:message> </small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
						<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/parentReports"><spring:message code="reporting"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/studentPayments"><spring:message code="student.payment.report"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			
			
			<iframe id="downloadStudentPaymentReport" style="display:none;"></iframe>
			
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="student.payment.report"></spring:message>
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
									 <spring:message code="student.email"/> 
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
							</tr>
							</thead>
							<tbody>
							<c:forEach var="dtoStudentActivities" items="${dtoStudentActivities}">
							<tr>
								<td>
									 ${dtoStudentActivities.studentEmail}
								</td>
								
								<td>
									${dtoStudentActivities.activityName}
								</td>
								<td>
									${dtoStudentActivities.newActivitydate}
								</td>
								<td>
								<fmt:formatDate  pattern="dd-MM-yy"  value="${dtoStudentActivities.activityDate}" />
								</td>
								<td>
									${dtoStudentActivities.activityAmount}
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
		 "order": [[ 2, "desc" ]],
		 "lengthMenu": [
		 	           [5,10, 15, 20, -1],
		 	           [5,10, 15, 20, "All"] 
		 	       ],
		 	      "aoColumns": [null,
			                     null,
			                       {"bVisible": false},
			                         {"iDataSort": 2},                              
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
	 /*   TableEditable.init(); */
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




</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#reporting").addClass("start active open");
	$("#reportingSelect").addClass("selected");
	$("#reportingOpen").addClass("arrow open");
	$("#reportingStudentPayment").addClass("active");
}

function excelExport(){
	var url='<%=request.getContextPath()%>/sys-admin/studentPaymentExcelExport';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		success:function(response){
			if(response=="success"){
				//$("#excelExport").show();
				var url = window.location.origin+'<%=request.getContextPath()%>/Student-Payment-List.xlsx';
				$("#downloadStudentPaymentReport").attr('src',url);
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