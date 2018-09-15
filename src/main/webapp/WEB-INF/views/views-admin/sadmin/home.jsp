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
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MiProfe | <spring:message code="super.admin"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>

<link rel="shortcut icon" href="favicon.ico"/>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<body class="page-header-fixed page-quick-sidebar-over-content page-sidebar-closed-hide-logo page-container-bg-solid" onload="navigationUpdate();">
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
							<h4 class="modal-title"><spring:message code="modal.title"></spring:message></h4>
						</div>
						<div class="modal-body">
							<spring:message code="widget.settings"></spring:message>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue"><spring:message code="save.changes"></spring:message> </button>
							<button type="button" class="btn default" data-dismiss="modal"><spring:message code="admin.close"></spring:message> </button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/> </a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="dashboard"/></a>
					</li>
				</ul>
			
			</div>
			<h3 class="page-title">
			<spring:message code="dashboard"/> <small><spring:message code="reportand.statistics"/> </small>
			</h3>
			<!-- END PAGE HEADER-->
			<!-- BEGIN DASHBOARD STATS -->
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat grey-mint">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="number" id="activeChat">
								 0
							</div>
							<div class="desc">
								 <spring:message code="chatacive.betweenusers"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/activeChatReports">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat blue-madison">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number" id="activeMessage">
								 0
							</div>
							<div class="desc">
								<spring:message code="active.message"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/messageReports">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				
			</div>
			<!-- END DASHBOARD STATS -->
			
						<div class="row">
						
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat purple-plum">
						<div class="visual">
							<i class="fa fa-shopping-cart"></i>
						</div>
						<div class="details">
							<div class="number" id="pendingSession">
								 0
							</div>
							<div class="desc">
								<spring:message code="pendingsession"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/unAcceptedSessions">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat red-pink">
						<div class="visual">
							<i class="fa fa-globe"></i>
						</div>
						<div class="details">
							<div class="number" id="acceptedSession">
								 0
							</div>
							<div class="desc">
								<spring:message code="accepted"/> <spring:message code="sessions"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/acceptedSessions">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
						
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat yellow-saffron">
						<div class="visual">
							<i class="fa fa-comments"></i>
						</div>
						<div class="details">
							<div class="number" id=cancelledSession>
								 0
							</div>
							<div class="desc">
								 <spring:message code="cancelled"/> <spring:message code="sessions"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/cancelledSessions">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
	
				
			</div>
			
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green-jungle">
						<div class="visual">
							<i class="fa fa-bar-chart-o"></i>
						</div>
						<div class="details">
							<div class="number" id="loginLogoutUsers">
								 0
							</div>
							<div class="desc">
								 <spring:message code="loginlogout"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/loginLogoutReports">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
					<div class="dashboard-stat green-soft">
						<div class="visual">
							<i class="fa fa-shopping-cart"></i>
						</div>
						<div class="details">
							<div class="number" id="loginLogoutSupport">
								 0
							</div>
							<div class="desc">
								<spring:message code="support.loginlogout"/>
							</div>
						</div>
						<a class="more" href="<%=request.getContextPath()%>/sys-admin/supportLoginLogoutReports">
						<spring:message code="view.all"/> <i class="m-icon-swapright m-icon-white"></i>
						</a>
					</div>
				</div>
				
			</div>
			<!-- END DASHBOARD STATS -->
		</div>
	</div>
	<!-- END CONTENT -->
	<!-- BEGIN QUICK SIDEBAR -->
	
	<!-- END QUICK SIDEBAR -->
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
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>
<script type="text/javascript">
jQuery(document).ready(function() {    
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
   Index.init();   
   Index.initDashboardDaterange();
   Index.initJQVMAP(); // init index page's custom scripts
   Index.initCalendar(); // init index page's custom scripts
   Index.initCharts(); // init index page's custom scripts
   Index.initChat();
   Index.initMiniCharts();
   Tasks.initDashboardWidget();
});
</script>
<script type="text/javascript">
function navigationUpdate(){
	$("#dashboardMenu").addClass("start active open");
	$("#dashboardSelect").addClass("selected");
	$("#dashboardOpen").addClass("arrow open");
	$("#dashboard").addClass("active");
}
</script>

<script type="text/javascript">

	 /* $.extend({
		    playSound: function(){
		      return $("<embed src='"+arguments[0]+".mp3' hidden='true' autostart='true' loop='false' class='playSound'>" + "<audio autoplay='autoplay' style='display:none;' controls='controls'><source src='"+arguments[0]+".mp3' /><source src='"+arguments[0]+".ogg' /></audio>").appendTo('body');
		    }
		  });  */
	 
	 var pendingAlertCount="0";
	 var acceptedAlertCount="0";
	 var cancelledAlertCount="0";
	var soundurl='<%=request.getContextPath()%>/sound/beep';
  
	 setInterval(function(){
			var url='<%=request.getContextPath()%>/sys-admin/getAllSessionsReadStatus';
			$.ajax({
				url:url,
				method:'GET',
				async :false,
				success:function(response){
					if(response != null){
						if(response.modelMap.pendingCounter>'0'){
							//$("#pendingSession").show();
							$("#pendingSession").text(response.modelMap.pendingCounter);
						}
						
						if(response.modelMap.acceptedCounter>'0'){
							//$("#acceptedSession").show();
							$("#acceptedSession").text(response.modelMap.acceptedCounter);
						}
						
						if(response.modelMap.cancelledCounter>'0'){
							//$("#cancelledSession").show();
							$("#cancelledSession").text(response.modelMap.cancelledCounter);
						}
						
						if(response.modelMap.activeChatCounter>'0'){
							//$("#activeChat").show();
							$("#activeChat").text(response.modelMap.activeChatCounter);
						}
						
						if(response.modelMap.messgaeCounter>'0'){
							//$("#activeMessage").show();
							$("#activeMessage").text(response.modelMap.messgaeCounter);
						}
						if(response.modelMap.loginUser>'0'){
							//$("#activeMessage").show();
							$("#loginLogoutUsers").text(response.modelMap.loginUser);
						}
						if(response.modelMap.loginSupport>'0'){
							//$("#activeMessage").show();
							$("#loginLogoutSupport").text(response.modelMap.loginSupport);
						}
					}
				}
			});  
		}, 5000); 
	 
</script>	
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>