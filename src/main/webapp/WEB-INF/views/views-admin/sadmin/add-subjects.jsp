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
<title>MiProfe | <spring:message code="manage.subjects"/> </title>
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
			<spring:message code="subjects"/> <small><spring:message code="manage.allsubjects"/> </small>
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
						<a href="<%=request.getContextPath()%>/sys-admin/add-subjects"><spring:message code="manage.subjects"/></a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-8">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.allsubjects"/>
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
									<!-- <div class="col-md-6">
										<div class="btn-group pull-right">
											<button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li>
													<a href="javascript:;">
													Print </a>
												</li>
												<li>
													<a href="javascript:;">
													Save as PDF </a>
												</li>
												<li>
													<a href="javascript:;">
													Export to Excel </a>
												</li>
											</ul>
										</div>
									</div> -->
								</div>
							</div>
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
								<th>
									 <spring:message code="subject.name"/>
								</th>
								<th>
									 <spring:message code="edit"/> 
								</th>
								<th>
									 <spring:message code="Actions.SubSubject"/> 
								</th>
							</tr>
							</thead>
							<tbody>
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="tutorList" items="${masterSubjectList}">
							<tr>
								<td>
									 ${tutorList.subject_Type}
								</td>
								<td>
								<%-- <a onclick="addSubSubjects('${tutorList.subject_Type_Master_Id}');">
									Add </a> --%>
								<a onclick="editSubject('${tutorList.subject_Type_Master_Id}');">
									<spring:message code="edit"/> </a>
								</td>
								<td>
								<a onclick="addSubSubjects('${tutorList.subject_Type_Master_Id}');">
									<spring:message code="add"/> </a>
								<a onclick="editSubSubject('${tutorList.subject_Type_Master_Id}');">
									<spring:message code="edit"/> </a>
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
				
				
				
				<div class="col-md-4">
				<!-- BEGIN ACCORDION PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><spring:message code="subjects"/>
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
							<div class="panel-group accordion" id="accordion1">
								 <c:set var="count" value="0" scope="page"/>
					                <c:forEach items="${subjectList}" var="entry" varStatus="loop">
					                <c:set var="count" value="${count+1}" scope="page"/>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#${count}">
										${entry.key} </a>
										</h4>
									</div>
									<div id="${count}" class="panel-collapse collapse">
										<div class="panel-body" style="height:150px; overflow-y:auto;">
											<c:forEach var="list" items="${entry.value}">
											<p>
												 ${list.value}
											</p>
											 </c:forEach>
										</div>
									</div>
								</div>
								</c:forEach>
								
							</div>
						</div>
					</div>
					<!-- END ACCORDION PORTLET-->
				</div>
				
				
				
				
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>

<!-- Dialog start -->
<!-- responsive -->
						
							<div id="responsive" class="modal fade" tabindex="-1" data-width="460">
							<form:form name="updateAdmin" id="form_sample_1" commandName="dtoSubject" method="post" action="saveNewSubject">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close clearFormFields" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="add.newsubject"/> </h4>
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
										<h4><spring:message code="subject.name"/> </h4>
											<p>
												<form:input path="subjectName" id="subjectName" name="subjectName" class="form-control" type="text"></form:input>
											</p>
										</div>
										</div>
									</div>
								</div>
								<%-- <form:input type="hidden" path="userId" id="userId" name="userId"></form:input> --%>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default clearFormFields"><spring:message code="admin.close"/></button>
									<button type="submit" class="btn blue"><spring:message code="save.changes"/></button>
								</div>
								</div>
								</form:form>
							</div>
							
<!-- Dialog end -->

<!-- Dialog start -->
<!-- responsive -->
						
							<div id="editSubject" class="modal fade" tabindex="-1" data-width="460">
							<form:form name="updateAdmin" id="form_sample_pass" commandName="dtoSubject" method="post" action="saveOrUpdateSubject">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="edit.subject"/> </h4>
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
										<h4><spring:message code="subject.name"/></h4>
											<p>
												<form:input path="subjectName" id="subjectName1" name="subjectName" class="form-control" type="text"></form:input>
											</p>
										</div>
										</div>
									</div>
								</div>
								<form:input type="hidden" path="subjectId" id="subjectId" name="subjectId"></form:input>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"/></button>
									<button type="submit" class="btn blue"><spring:message code="update.changes"></spring:message> </button>
								</div>
								</div>
								</form:form>
							</div>
							
<!-- Dialog end -->

<!-- Dialog start -->
<!-- responsive -->
						
							<div id="addSubSubjects" class="modal fade" tabindex="-1" data-width="460">
							<form:form name="updateAdmin" id="form_sample_11" commandName="dtoSubject" method="post" action="saveNewSubSubject">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close clearFormFields" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="add.subsubject"/> </h4>
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
										<h4><spring:message code="parent.name"/> </h4>
											<p>
												<form:input path="subjectName" id="subjectName2" name="subjectName" class="form-control" type="text"></form:input>
											</p>
										</div>
										</div>
									</div>
								</div>
								<form:input type="hidden" path="subjectId" id="subjectId2" name="subjectId"></form:input>
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
						
							<div id="updateSubSubjects" class="modal fade" tabindex="-1" data-width="460">
							<form:form name="updateAdmin" id="form_sample_111" commandName="dtoSubject" method="post" action="saveOrUpdateSubSubject">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h4 class="modal-title"><spring:message code="add.subsubject"/></h4>
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
										<!-- <h4>Name</h4> -->
											
											<div id="subjectName3"></div>
												<%-- <form:input path="subjectName" id="subjectName3" name="subjectName" class="form-control" type="text"></form:input> --%>
											
										</div>
										</div>
									</div>
								</div>
								<input type="hidden"  id="subjectId3" name="subjectId3"></input>
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"/></button>
									<button type="submit" class="btn blue"><spring:message code="save"/></button>
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
	$('#responsive').modal('show');
	<%-- var url='<%=request.getContextPath()%>/sys-admin/getAdminProfile';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{userId:userId},
		success:function(response){
			if(response!=null){
				/* $("#fname").val(response.modelMap.admininfo.fname);
				$("#lname").val(response.modelMap.admininfo.lname);
				$("#email").val(response.modelMap.admininfo.email);
				$("#contactNumber").val(response.modelMap.admininfo.mobileNumber);
				$("#submit_form_password").val(response.modelMap.admininfo.password);
				$("#userId").val(userId); */
				$('#responsive').modal('show');
			return true;
		}
		else{
			
		}
		}
	}); --%>
}
function editSubject(subjectId){
	$("#subjectId").val(subjectId);
	var url='<%=request.getContextPath()%>/sys-admin/getSelectedSubject';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		data:{subjectId:subjectId},
		success:function(response){
			if(response!=null){
				$("#subjectName1").val(response.modelMap.dtoSubject.subjectName);
				$('#editSubject').modal('show');
			return true;
		}
		else{
			
		}
		}
	}); 
}
function addSubSubjects(subjectId){
	$("#subjectId2").val(subjectId);
	$('#addSubSubjects').modal('show');
}
function editSubSubject(subjectId){
	$("#subjectId3").val(subjectId);
	var url='<%=request.getContextPath()%>/sys-admin/getSelectedSubSubject';
	$.ajax({
		url:url,
		/* contentType: 'application/json', */
		/* dataType: json, */
		method:'POST',
		async :false,
		data:{subjectId:subjectId},
		success:function(response){
			if(response!=null){
				$('#subjectName3').children().remove();
				 $.each( response.modelMap.map, function( key , value ) {
					 $('#subjectName3').append('<p><input name="'+key+'" id="'+key+'" value="'+value+'" class="form-control" type="text"></input></p>');
		    });  
				$('#updateSubSubjects').modal('show');
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
	$("#manageMData").addClass("active");
}


$(".clearFormFields").click(function(){
	$("#form_sample_1")[0].reset();
	$("#form_sample_11")[0].reset();
	
});

</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>