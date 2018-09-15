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
<title>MiProfe | <spring:message code="view.tutor"/> </title>
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
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title"><spring:message code="modal.title"/></h4>
						</div>
						<div class="modal-body">
							<spring:message code="widget.settings"/> 
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm hide">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						<spring:message code="customer.theme.color"/> </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-container="body" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-container="body" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-container="body" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-container="body" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-container="body" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-container="body" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
			<div class="theme-option">
						<span>
						<spring:message code="customer.layout"/>  </span>
						<select class="layout-option form-control input-sm">
							<option value="fluid" selected="selected"><spring:message code="customer.fluid"/> </option>
							<option value="boxed"><spring:message code="customer.boxed"/> </option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.header"/>  </span>
						<select class="page-header-option form-control input-sm">
							<option value="fixed" selected="selected"><spring:message code="customer.fixed"/> </option>
							<option value="default"><spring:message code="customer.default"/> </option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.topmenu"></spring:message></span>
						<select class="page-header-top-dropdown-style-option form-control input-sm">
							<option value="light" selected="selected"><spring:message code="customer.light"></spring:message></option>
							<option value="dark"> <spring:message code="customer.dark"></spring:message> </option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.sidebarmode"></spring:message> </span>
						<select class="sidebar-option form-control input-sm">
							<option value="fixed"> <spring:message code="customer.fixed"></spring:message> </option>
							<option value="default" selected="selected"> <spring:message code="customer.Default"></spring:message> </option>
						</select>
					</div>
						<div class="theme-option">
						<span>
						<spring:message code="customer.sidebarmenu"></spring:message> </span>
						<select class="sidebar-menu-option form-control input-sm">
							<option value="accordion" selected="selected"> <spring:message code="customer.accordion"></spring:message> </option>
							<option value="hover"> <spring:message code="customer.hover"></spring:message> </option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.sidebarstyle"/> </span>
						<select class="sidebar-style-option form-control input-sm">
							<option value="default" selected="selected"><spring:message code="customer.Default"/></option>
							<option value="light"><spring:message code="customer.light"/></option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.sidebarposition"/> </span>
						<select class="sidebar-pos-option form-control input-sm">
							<option value="left" selected="selected"><spring:message code="customer.left"/></option>
							<option value="right"><spring:message code="customer.right"/></option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.footer"/> </span>
						<select class="page-footer-option form-control input-sm">
							<option value="fixed"><spring:message code="customer.fixed"/></option>
							<option value="default" selected="selected"><spring:message code="customer.Default"/></option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
			<spring:message code="tutor.profile"/> <small><spring:message code="unregistered.tutor"/></small>
			</h3>
			<!-- <div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="index.html">Home</a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="#">My Profile</a>
					</li>
				</ul>
			</div> -->
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row margin-top-20">
				<div class="col-md-12">
					<!-- BEGIN PROFILE SIDEBAR -->
					<div class="profile-sidebar">
						<!-- PORTLET MAIN -->
						<div class="portlet light profile-sidebar-portlet">
							<!-- SIDEBAR USERPIC -->
							<div class="profile-userpic">
								<img src="${tutInfo.imageName}" class="img-responsive" alt="">
							</div>
							<!-- END SIDEBAR USERPIC -->
							<!-- SIDEBAR USER TITLE -->
							<div class="profile-usertitle">
								<div class="profile-usertitle-name">
									 ${tutInfo.fname} ${tutInfo.lname}
								</div>
								<div class="profile-usertitle-job">
									 <spring:message code="tutor"/>
								</div>
							</div>
							<!-- END SIDEBAR USER TITLE -->
							
						</div>
						<!-- END PORTLET MAIN -->
					</div>
					<!-- END BEGIN PROFILE SIDEBAR -->
					<!-- BEGIN PROFILE CONTENT -->
					<div class="profile-content">
						<div class="row">
							<div class="col-md-12">
								<div class="portlet light">
									<div class="portlet-title tabbable-line">
										<div class="caption caption-md">
											<i class="icon-globe theme-font hide"></i>
											<span class="caption-subject font-blue-madison bold uppercase"><spring:message code="profile.details"/></span>
										</div>
										<!-- <ul class="nav nav-tabs">
											<li class="active">
												<a href="#tab_1_1" data-toggle="tab">Personal Info</a>
											</li>
											<li>
												<a href="#tab_1_2" data-toggle="tab">Change Avatar</a>
											</li>
											<li>
												<a href="#tab_1_3" data-toggle="tab">Change Password</a>
											</li>
										</ul> -->
									</div>
									<div class="portlet-body">
										<div class="tab-content">
											<!-- PERSONAL INFO TAB -->
											<div class="tab-pane active" id="tab_1_1">
											<form:form name="myProfileForm" id="form_sample_1" action="setTutorActive" commandName="dtoTutorDetails" method="POST">
											<div class="row profile">
											<div class="col-md-12">
												<!--BEGIN TABS-->
												<div class="tabbable-line tabbable-full-width">
													<div class="tab-content">
														<div class="tab-pane active" id="tab_1_1">
																<div class="col-md-9">
																	<div class="row">
																		<div class="col-md-12 profile-info">
																			<ul class="list-unstyled profile-nav">
																		<li>
																			<a>
																			<spring:message code="full.name"/> : ${tutInfo.fname} ${tutInfo.lname}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="login.email"/> : ${tutInfo.email}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="streetName"/> : ${tutInfo.street}
																			</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="city"/> : ${tutInfo.city}
																			</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="country"/> : ${tutInfo.countryName}
																			</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="college"/> : ${tutInfo.college}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="career"/> : ${tutInfo.career}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="mobilenumber"/> : ${tutInfo.mobileNumber}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="subjects"/> : ${tutInfo.subjects}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="graduationDate"/> : ${tutInfo.graduationDate}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="about"/> : ${tutInfo.about}</a>
																		</li>
																		<li>
																			<a>
																			<spring:message code="about.more"/> : ${tutInfo.aboutMore}</a>
																		</li>
																		
																		<li>
																			<a>
																			<spring:message code="gpa"/> : ${tutInfo.gpa}
																			</a>
																		</li>
																		
																		<li>
																			<a>
																			<spring:message code="gpaScaleName"/> : ${tutInfo.gpaScale}
																			</a>
																		</li>
																		
																	</ul>
																		</div>
																	</div>
																	<!--end row-->
																</div>
															</div>
														</div>
													</div>
												</div>
												<form:input type="hidden" path="userId" value="${tutInfo.userId}" id="userId" name="userId"/>
												<input type="hidden" id="tutorStatus" name="tutorStatus"/>
												<!--END TABS-->
												<div class="margin-top-10">
													<button type="button" class="btn blue" onclick="setTurorStatus('accept');"><spring:message code="accept"/></button>
													<button type="button" class="btn blue" onclick="setTurorStatus('reject');"><spring:message code="reject"/></button>
													<button type="button" class="btn blue" onclick="setTurorStatus('pending');"><spring:message code="pendingTutor"/></button>
														<a href="<%=request.getContextPath()%>/admin/home" class="btn default">
													<spring:message code="cancel"/> </a>
												</div>
											</div>
											</form:form>
										</div>
											</div>
											<!-- END PERSONAL INFO TAB -->
											<!-- CHANGE AVATAR TAB -->
											<div class="tab-pane" id="tab_1_2">
												<!-- <p>
													 Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod.
												</p> -->
												<%-- <form:form name="superAdminEditForm" id="superAdminEditForm" method="post" commandName="dtoTutorDetails" action="updateImage" enctype="multipart/form-data" role="form">
													<div class="form-group">
													<form:input path="uploadFile" type="file" onchange="readURL(this);"></form:input>
														<div class="fileinput fileinput-new" data-provides="fileinput">
															<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
																<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>
															</div>
															<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
															</div>
															<div>
																<span class="btn default btn-file">
																<span class="fileinput-new">
																Select image </span>
																<span class="fileinput-exists">
																Change </span>
																<form:input path="uploadFile" type="file" ></form:input>
																<!-- <input type="file" name="..."> -->
																</span>
																<a href="javascript:;" class="btn default fileinput-exists" data-dismiss="fileinput">
																Remove </a>
															</div>
														</div>
														<div class="clearfix margin-top-10">
															<span class="label label-danger">NOTE! </span>
															<span>Attached image thumbnail is supported in Latest Firefox, Chrome, Opera, Safari and Internet Explorer 10 only </span>
														</div>
													</div>
													<div class="margin-top-10">
														<button type="submit" class="btn blue">Submit</button>
														<a href="<%=request.getContextPath()%>/sys-admin/profile" class="btn default">
														Cancel </a>
													</div>
												</form:form> --%>
											</div>
											<!-- END CHANGE AVATAR TAB -->
											<!-- CHANGE PASSWORD TAB -->
											<div class="tab-pane" id="tab_1_3">
												<%-- <form:form name="changePassForm" id="form_sample_pass" action="changePassword" commandName="dtoTutorDetails" method="POST">
												<div class="form-body">
													<div class="form-group">
														<label class="control-label">Current Password</label>
														<form:input path="currPass" name="currPass" type="password" class="form-control"/>
													</div>
													<div class="form-group">
														<label class="control-label">New Password</label>
														<form:input path="password" name="password" type="password" class="form-control" id="submit_form_password"/>
													</div>
													<div class="form-group">
														<label class="control-label">Re-type New Password</label>
														<input name="rpassword" type="password" class="form-control"/>
													</div>
													<div class="margin-top-10">
														<button type="submit" class="btn green">Submit</button>
														<a href="<%=request.getContextPath()%>/sys-admin/profile" class="btn default">
														Cancel </a>
													</div>
												</div>
												</form:form> --%>
											</div>
											<!-- END CHANGE PASSWORD TAB -->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END PROFILE CONTENT -->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>

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
<script type="text/javascript">
var contextPath='<%=request.getContextPath()%>';
</script>
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() { 
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
   // initiate layout and plugins
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
/* Profile.init(); */ // init page demo
FormValidation.init();
});
</script>
<script type="text/javascript">
function setTurorStatus(tutorStatus){
	
	var tutStatus=tutorStatus;
	
	$("#tutorStatus").val(tutStatus);
	
	document.forms["myProfileForm"].submit();
	
}

</script>
<!-- END JAVASCRIPTS -->
<script type="text/javascript">
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-37564768-1', 'keenthemes.com');
  ga('send', 'pageview');
</script>


</body>
<!-- END BODY -->
</html>