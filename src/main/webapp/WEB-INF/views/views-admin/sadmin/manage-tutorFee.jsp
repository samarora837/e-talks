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
<title>MiProfe | <spring:message code="manage.tutorfee"/> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
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
			<spring:message code="tutorfee"/> <small><spring:message code="manage.tutorallfee"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/add-subjects"><spring:message code="master.data"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/manage-fee"><spring:message code="manage.tutorfee"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.tutorallfee"/>
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
										<div class="btn-group">
											<button class="btn green" onclick="addNewSubject();">
											<spring:message code="add.new"/> <i class="fa fa-plus"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
								<th>
									 <spring:message code="country.name"/>
								</th>
								<th>
								 <spring:message code="fee"/> (US $)
								</th>
								
								
								<th>
									 <spring:message code="fee"/> (MXN $)
								</th>
								
								<th>
									 <spring:message code="fee"/> (EURO) 
								</th>
								
								
								<th>
									 <spring:message code="action"/>
								</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="tutorList" items="${tutorFeePerCountries}">
							<c:if test="${tutorList.countryMaster ne null}">
							<tr>
								<td>
									 ${tutorList.countryMaster.country_Name}
								</td>
								<td>
								${tutorList.fee}
								</td>
								<td>
								${tutorList.fee_Mxn}
								</td>
								<td>
								${tutorList.fee_Europe}
								</td>
								<td>
								<a onclick="editFee('${tutorList.id}');">
									<spring:message code="edit"/> </a>
								<a class="delete" onclick="setDelete('${tutorList.id}');">
									<spring:message code="delete"/> </a>
								</td>
							</tr>
							</c:if>
							<c:if test="${tutorList.countryMaster eq null}">
							<tr>
								<td>
									 <spring:message code="otherCountries"/>
								</td>
								<td>
								${tutorList.fee}
								</td>
								<td>
								${tutorList.fee_Mxn}
								</td>
								<td>
								${tutorList.fee_Europe}
								</td>
								<td>
								<a onclick="editFee('${tutorList.id}');">
									<spring:message code="edit"/> </a>
								
								</td>
							</tr>
							</c:if>
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
						
							<div id="addNewFee" class="modal fade" tabindex="-1" data-width="460">
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorFeeByCountry" method="post" action="saveNewFee">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close clearFormFields" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="addfee"/> </h4>
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
										<div class="col-md-12">
										<div class="form-group">
											<h4><spring:message code="country"/></h4>
											<p>
											<form:select path="countryId" class="bs-select form-control" id="countryId" name="countryName">
												<optgroup label='<spring:message code="spanishspeakingcountries"/>' id="spanishCountries">
												<%-- <c:forEach var="listSpanishCountry" items="${listSpanishCountry}">
                    										<option value='${listSpanishCountry.country_Id}'>${listSpanishCountry.country_Name}</option>
                    							</c:forEach> --%>
												</optgroup>
												<optgroup label='<spring:message code="otherCountries"/>' id="otherCountries">
												<%-- <c:forEach var="listOtherCountry" items="${listOtherCountry}">
                    										<option value='${listOtherCountry.country_Id}' >${listOtherCountry.country_Name}</option>
                    							</c:forEach> --%>
												</optgroup>
											</form:select>
											</p>
										<h4><spring:message code="fee"/> (US $)</h4>
											<p>
												<form:input path="fee" id="fee" name="fee" class="form-control" type="text"></form:input>
											</p>
											
											
											<h4><spring:message code="fee"/> (MXN $)</h4>
											<p>
												<form:input path="mxnFee" id="mxnFee" name="mxnFee" class="form-control" type="text"></form:input>
											</p>
											
											<h4><spring:message code="fee"/> (EURO)</h4>
											<p>
												<form:input path="euroFee" id="euroFee" name="euroFee" class="form-control" type="text"></form:input>
											</p>
											
										</div>
										</div>
									</div>
								</div>
								<%-- <form:input type="hidden" path="userId" id="userId" name="userId"></form:input> --%>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default clearFormFields"><spring:message code="admin.close"/></button>
									<button type="submit" class="btn blue"><spring:message code="save"/></button>
								</div>
								</div>
								</form:form>
							</div>
							
<!-- Dialog end -->

<!-- Dialog start -->
<!-- responsive -->
						
							<div id="editNewFee" class="modal fade" tabindex="-1" data-width="460">
							<form:form name="updateAdmin" id="form_sample_pass" commandName="dtoTutorFeeByCountry" method="post" action="saveOrUpdateFee">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="update"/> <spring:message code="fee"/> <span id="countyName"></span> </h4>
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
										<div class="col-md-12">
										<div class="form-group">
										<h4><spring:message code="fee"/> (US $)</h4>
											<p>
												<form:input path="fee" id="feeVal" name="fee" class="form-control" type="text"></form:input>
											</p>
											
											
											<h4><spring:message code="fee"/> (MXN $)</h4>
											<p>
												<form:input path="mxnFee" id="mxnFeeVal" name="mxnFee" class="form-control" type="text"></form:input>
											</p>
											
											<h4><spring:message code="fee"/> (EURO)</h4>
											<p>
												<form:input path="euroFee" id="euroFeeVal" name="euroFee" class="form-control" type="text"></form:input>
											</p>
											
											
										</div>
										</div>
									</div>
								</div>
								<form:input type="hidden" path="feeId" id="feeId" name="feeId"></form:input>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"/></button>
									<button type="submit" class="btn blue"><spring:message code="update"/></button>
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
<!-- END FOOTER -->
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {    
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   /* TableManaged.init(); */
	   TableEditable.init();
	   FormValidation.init();
});
</script>
<script type="text/javascript">
function addNewSubject(){
	
	var url='<%=request.getContextPath()%>/sys-admin/getUpdatedCountry';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		/* data:{feeId:feeId}, */
		success:function(response){
			if(response!=null){
				$.each( response.modelMap.map, function( key , value ) {
					$('#spanishCountries').append('<option value="'+key+'">'+value+'</option>');
		    	}); 
				$.each( response.modelMap.map1, function( key , value ) {
					$('#otherCountries').append('<option value="'+key+'">'+value+'</option>');
		    	}); 
				
				
				$('#addNewFee').modal('show');
			return true;
		}
		else{
			
		}
		}
	});
	
	
}
function editFee(feeId){
	$("#feeId").val(feeId);
	var url='<%=request.getContextPath()%>/sys-admin/getFeePerCountry';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{feeId:feeId},
		success:function(response){
			if(response!=null){
				$("#feeVal").val(response.modelMap.feeByCountry.fee);
				$("#mxnFeeVal").val(response.modelMap.feeByCountry.mxnFee);
				$("#euroFeeVal").val(response.modelMap.feeByCountry.euroFee);
				if(response.modelMap.feeByCountry.countryName=="otherCountries"){
					$("#countyName").text('<spring:message code="otherCountries"/>');
				}
				else{
				$("#countyName").text(response.modelMap.feeByCountry.countryName);
				}
				$('#editNewFee').modal('show');
			return true;
		}
		else{
			
		}
		}
	});
	
}
function setDelete(feeId){
	var url='<%=request.getContextPath()%>/sys-admin/fee-del';
		$.ajax({
			url:url,
			method:'POST',
			async :false,
			data:{feeId:feeId},
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
	$("#masterDataMenu").addClass("start active open");
	$("#mDataSelect").addClass("selected");
	$("#mDataOpen").addClass("arrow open");
	$("#manageFeeData").addClass("active");
}

$(".clearFormFields").click(function(){
	$("#form_sample_1")[0].reset();
	
});

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>