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
<title>MiProfe | <spring:message code="manage.tutor"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="content-type" content="text/plain; charset=ISO-8859-1"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>


<script type="text/javascript">


function emailCheck(email){
	//var email=$("#email").val();
	
	
	var email=$("#email").val();
	
	var tutorEmail=$("#emailOld").val();
	
	var flag = false;
	if(email==tutorEmail){
		flag=true;
	}
	else{
	
	var url='<%=request.getContextPath()%>/admin/tutorEmailCheck';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{email:email},
		success:function(response){
		if(response=="error"){
			flag = false;
			}
		else{
			flag = true;
		}
		}
		
	}); 
	}
	return flag;
}

</script>





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
			<spring:message code="tutor"/> <small><spring:message code="manage.alltutor"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manage-tutors"><spring:message code="tutor"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/admin/manage-tutors"><spring:message code="manage.tutor"/></a>
					</li>
				</ul>
			</div>

			
				<div class="alert alert-success" id="sucessMessage" style="display:none;">
								<strong><spring:message code="success"/></strong> <spring:message code="userDelete.success"/>
			</div>
<%-- 			<div class="alert alert-success" id="excelExport" style="display:none;">
			
								<strong><spring:message code="success"/></strong> Excel Export Successfully.
			</div> --%>
			<iframe id="downloadTutorList" style="display:none;"></iframe>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.alltutor"/>
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
								
								 <th>
									 <spring:message code="tutor.rating"/>
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
								
										<td><span class="badge badge-success"> ${tutorList.rating} </span>
							
									&nbsp;&nbsp;&nbsp;&nbsp;
									 <a class="btn default btn-xs green-stripe" onclick="viewTutorRating('${tutorList.userId}');"> 
									 <spring:message code="view.allratings"/> </a>
								</td> 
								
								
								
								<td>
									<a onclick="getTutorInfo('${tutorList.userId}');">
									<spring:message code="edit"/> </a>
								</td>
								<td>
									<%-- <a class="delete12" onclick="confirmDelete('${tutorList.userId}');">
									<spring:message code="delete"/> </a> --%>
									
									<c:if test="${tutorList.status=='Active'}">
										<a onclick="setDeActive('${tutorList.userId}');">
											<spring:message code="reject"/> </a>
											<a onclick="setPending('${tutorList.userId}');">
											<spring:message code="pendingTutor"/> </a></c:if>
									<c:if test="${tutorList.status=='Rejected'}">
										<a onclick="setActive('${tutorList.userId}');">
											<spring:message code="accept"/></a>
											<a onclick="setPending('${tutorList.userId}');">
											<spring:message code="pendingTutor"/></a>
									</c:if>
									
									<c:if test="${tutorList.status=='Pending'}">
										<a onclick="setActive('${tutorList.userId}');">
											<spring:message code="accept"/> </a>
											<a onclick="setDeActive('${tutorList.userId}');">
											<spring:message code="reject"/> </a></c:if>
											
									<a onclick="viewActivity('${tutorList.userId}');">
											<spring:message code="view.activities"></spring:message> </a>
									
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
						
							<div id="responsive" class="modal fade" tabindex="-1" data-width="860" style="height:100%;">
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoTutorDetails" method="post" action="updateTutorDetailsByAdmin" enctype="multipart/form-data">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"><span id="fname"></span> <span id="lname"></span> (<spring:message code="tutor"/>)</h3>
								</div>
							<%-- 	<div class="alert alert-danger display-hide" id="errorMessage">
										<button class="close" data-close="alert"></button>
										<spring:message code="havesome.error"></spring:message>
									</div> --%>
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
              													<img alt="" src="" id="profileImage" style="width: 200px; height: 150px;"/>
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
										<spring:message code="weare.KeenThemes1" var="KeenThemes1"/>
										
										<div class="col-md-4">
										<div class="form-group">
										<h4><spring:message code="student.firstname"/></h4>
											<p>
												<form:input path="fname" id="fname1" name="fname" class="form-control" type="text"></form:input>
											</p>
										</div>
										<div class="form-group">
										<h4><spring:message code="student.lastname"/></h4>
											<p>
												<form:input path="lname" id="lname1" name="lname" class="form-control" type="text"></form:input>
											</p>
										</div>
										
										<div class="form-group">
										<h4><spring:message code="student.email"/></h4>
											<p>
												<form:input path="email" id="email" name="email" class="form-control" type="text"></form:input>
											</p>
										</div>
										
										<div class="form-group">
										<h4><spring:message code="streetName"/></h4>
											<p>
												<form:input path="street" id="street" name="street" class="form-control" type="text"></form:input>
											</p>
										</div>
										<div class="form-group">
											<h4><spring:message code="country"></spring:message> </h4>
											<p>
											<form:select path="country" class="bs-select form-control" id="country" onchange="getTimeZone();">
												  <optgroup label='<spring:message code="spanishspeakingcountries"/>'>
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
											<h4><spring:message code="subjects"/></h4>
											<input type="text" class="form-control" id="subjects" name="subjects"  readonly="true" style="cursor: pointer;"/>
										</div>
										
											<div class="form-group">
											<h4><spring:message code="about"/></h4>
											<form:textarea path="about" class="form-control" id="about" name="about" rows="3" placeholder="${KeenThemes}" style="resize:none;"></form:textarea>
										</div>
										
										<div class="form-group">
										<h4><spring:message code="gpa"/></h4>
											<p>
												<form:input path="gpa" id="gpa" name="gpa" class="form-control" type="text"></form:input>
											</p>
										</div>
										
										</div>
										
										<div class="col-md-4">
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
											<h4><spring:message code="city"></spring:message> </h4>
											<p>
												<form:input path="city" id="city" name="city" class="form-control" type="text"></form:input>
											</p>
										</div>
											
										<div class="form-group">
											<h4><spring:message code="parent.time"></spring:message> </h4>
											<p>
												<form:select path="timeZone" class="bs-select form-control" id="timeZone">
											</form:select>
											</p>
										</div>
										<div class="form-group">
											<h4><spring:message code="graduationDate"/></h4>
											<input class="form-control" id="graduationDate" name="graduationDate"  readonly="true" style="cursor: pointer;"/>
										</div>
										<div class="form-group">
												<h4><spring:message code="about.more"/></h4>
											<form:textarea path="aboutMore" class="form-control" id="aboutMore" name="aboutMore" rows="3" placeholder="${KeenThemes1}" style="resize:none;"></form:textarea>
										</div>
										
										<div class="form-group">
										<h4><spring:message code="gpaScaleName"/></h4>
											<p>
												<form:input path="gpaScale" id="gpaScale" name="gpaScale" class="form-control" type="text"></form:input>
											</p>
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
<input type="hidden" id="emailOld" nmae="emailOld">
<form action="managePayments" method="post" id="viewActivityForm" name="viewActivityForm">
<input type="hidden" id="tutorID" name="tutorID"/>
</form> 

<form action="viewTutorRating" method="post" id="viewRatingForm" name="viewRatingForm">
<input type="hidden" id="tutorUserID" name="tutorUserID"/>
</form>

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
	                         {"iDataSort": 4},                              
	                         null,
	                         null,
	                         null
	                        ]
	});
	   var tableWrapper = $("#sample_editable_1_wrapper");

	   tableWrapper.find(".dataTables_length select").select2({
	       showSearchInput: false //hide search box with special css class
	   });
	
	
	
	$.validator.addMethod("emailId", function(value, element) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
	},"<spring:message code='enter.email'/>");

 $.validator.addMethod("emailIdExist", function(value, element) {
	var flag = emailCheck(value);
  	return this.optional(element) || flag;	   
	},"<spring:message code='email.exist'/>"); 
 

$('#form_sample_1').validate({
	rules:{
		email:{
			required:true,
			emailId:true,
			emailIdExist:true
		}	
	},
	 messages:{
		 	email:{
				required:"<spring:message code='email.required'/>"
			}
	}, 
	submitHandler:function(form){
		form.submit();
	}
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
	   //TableEditable.init();
	   FormValidation.init();
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
				$("#fname1").val(response.modelMap.admininfo.fname);
				$("#lname1").val(response.modelMap.admininfo.lname);
				$("#country").val(response.modelMap.admininfo.country); 
				$('#timeZone').children().remove();
				
				$.each(response.modelMap.admininfo.zoneMap, function( key , value ) {
				$('#timeZone').append('<option value="'+key+'">'+value+'</option>');
				 });
				$("#subjects").val(response.modelMap.admininfo.subjects);
				$("#graduationDate").val(response.modelMap.admininfo.graduationDate);
				$("#timeZone").val(response.modelMap.admininfo.timeZone);
				$("#college").val(response.modelMap.admininfo.college);
				$("#career").val(response.modelMap.admininfo.career);
				$("#about").val(response.modelMap.admininfo.about);
				$("#aboutMore").val(response.modelMap.admininfo.aboutMore);
				$('#profileImage').attr('src', response.modelMap.admininfo.imageName);
				$("#userId").val(userId);
				
				$("#street").val(response.modelMap.admininfo.street);
				$("#city").val(response.modelMap.admininfo.city);
				$("#gpa").val(response.modelMap.admininfo.gpa);
				$("#gpaScale").val(response.modelMap.admininfo.gpaScale);
				$("#email").val(response.modelMap.admininfo.email);
				$("#emailOld").val(response.modelMap.admininfo.email);

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
function setPending(userId){
	var userId = userId;
	var url='<%=request.getContextPath()%>/admin/pending';
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




function confirmDelete(userId){
	var r = confirm('<spring:message code="sure.deleterecord"/>');
	if (r == true) {
		setDelete(userId);
		
	}
	
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
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#tutorMenu").addClass("start active open");
	$("#tutorSelect").addClass("selected");
	$("#tutorOpen").addClass("arrow open");
	$("#manageTutor").addClass("active");
}


function viewActivity(tutorId){
	$("#tutorID").val(tutorId);
	$("#viewActivityForm").submit();
}


function viewTutorRating(userId){
	$("#tutorUserID").val(userId);
	$("#viewRatingForm").submit();
	
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

</script>
<!-- END JAVASCRIPTS -->




</body>
<!-- END BODY -->
</html>