<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="ISO-8859-1"%>
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
<title><spring:message code="manage.about.us"/> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<link href="<%=request.getContextPath()%>/css/styleCMS.css" rel="stylesheet" type="text/css"/>
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
							<button type="button" class="btn blue"><spring:message code="save.changes"/></button>
							<button type="button" class="btn default" data-dismiss="modal"><spring:message code="admin.close"/> </button>						</div>
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
						<spring:message code="customer.theme.color"/>  </span>
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
			<spring:message code="manage.about.us"/>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/manageCms/about"><spring:message code="CMSVideos"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/manageCms/about"><spring:message code="manage.about.us"/> </a>
					</li>
				</ul>
			</div>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row margin-top-20">
				<div class="col-md-12">
					<!-- BEGIN EXTRAS PORTLET-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i><spring:message code="manage.about.us"/>
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="${request.pagecontext.contextpath}updateCms/about" class="form-horizontal form-bordered" enctype="multipart/form-data" method="post">
								<div class="form-body">
									<div class="form-group last">
										<h1 class="text-left col-md-12"><spring:message code="footer.aboutus" /></h1>
										<div class="col-md-9">
											<textarea class="ckeditor form-control" name="editor1" rows="20">${cmsContent}</textarea>
										</div>
										<div class="col-md-3 abt-rt">
								            <div class="mm-video"> <img src="<%=request.getContextPath()%>${video1.imageURL}" alt="">
								              <div id="mmVideoBtnRow">
								                <!-- <input class="greenBtn-small" value="Upload" type="button"> -->
								                <!-- <input class="blueBtn-small" value="View" type="button"> -->
													<div class="fileinput fileinput-new" data-provides="fileinput">
														<span class="btn green btn-file">
														<span class="fileinput-new">
														<spring:message code="select.file" /> </span>
														<span class="fileinput-exists">
														<spring:message code="change" /> </span>
														<input type="file" name="video1">
														</span>
														<span class="fileinput-filename" style="display:none;">
														</span>
														&nbsp; <a href="javascript:;" class="close fileinput-exists" style="display:none;" data-dismiss="fileinput">
														</a>
													</div>
								              </div>
								            </div>
								            <div class="mm-video"> <img src="<%=request.getContextPath()%>${video2.imageURL}" alt="">
								              <div id="mmVideoBtnRow">
								               <!--  <input class="greenBtn-small" value="Upload" type="button"> -->
								                <!-- <input class="blueBtn-small" value="View" type="button"> -->
													<div class="fileinput fileinput-new" data-provides="fileinput">
														<span class="btn green btn-file">
														<span class="fileinput-new">
														<spring:message code="select.file" /> </span>
														<span class="fileinput-exists">
														<spring:message code="change" /> </span>
														<input type="file" name="video2">
														</span>
														<span class="fileinput-filename" style="display:none;">
														</span>
														&nbsp; <a href="javascript:;" class="close fileinput-exists" style="display:none;" data-dismiss="fileinput">
														</a>
													</div>
								              </div>
								            </div>
								            <div class="mm-video" style="border:none;"> 
												<spring:message code="note.video.and.image" />
								            </div>
								         </div>
									</div>
								</div>
								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-5 col-md-9">
											<button type="submit" class="btn green"><i class="fa fa-check"></i><spring:message code="parent.submit" /></button>
											<button type="button" class="btn default"><spring:message code="cancel" /></button>
										</div>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END EXTRAS PORTLET-->
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
   // initiate layout and plugins
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
/* Profile.init(); */ // init page demo
FormValidation.init();

});
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
<script type="text/javascript">
function navigationUpdate(){
	$("#cmsMenu").addClass("start active open");
	$("#cmsSelect").addClass("selected");
	$("#cmsOpen").addClass("arrow open");
	$("#manageAbout").addClass("active");
	
}
</script>
</body>
<!-- END BODY -->
</html>