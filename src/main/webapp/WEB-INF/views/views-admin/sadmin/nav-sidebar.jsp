<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<div class="page-sidebar-wrapper">
		<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
		<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
		<div class="page-sidebar navbar-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->
			<!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
			<!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
			<!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
			<!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<ul class="page-sidebar-menu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
				<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
				<li class="sidebar-toggler-wrapper">
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler">
					</div>
					<!-- END SIDEBAR TOGGLER BUTTON -->
				</li>
				<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
				<li class="sidebar-search-wrapper">
					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
					<!-- DOC: Apply "sidebar-search-bordered" class the below search form to have bordered search box -->
					<!-- DOC: Apply "sidebar-search-bordered sidebar-search-solid" class the below search form to have bordered & solid search box -->
					<!-- <form class="sidebar-search " action="extra_search.html" method="POST">
						<a href="javascript:;" class="remove">
						<i class="icon-close"></i>
						</a>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
							<a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
							</span>
						</div>
					</form> -->
					<!-- END RESPONSIVE QUICK SEARCH FORM -->
				</li>
				<li id="dashboardMenu">
					<a href="javascript:;">
					<!-- <i class="icon-home"></i> -->
					<span class="title"><spring:message code="dashboard"/> </span>
					<span id="dashboardSelect"></span>
					<span id="dashboardOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="dashboard">
							<a href="<%=request.getContextPath()%>/sys-admin/home">
							<!-- <i class="icon-bar-chart"></i> -->
							<spring:message code="dashboard"/> </a>
						</li>
						<li id="myProfile">
							<a href="<%=request.getContextPath()%>/sys-admin/profile">
							<!-- <i class="icon-bulb"></i> -->
							<spring:message code="my.profile"/></a>
						</li>
					</ul>
				</li>
				<li id="adminMenu">
					<a href="javascript:;">
					<!-- <i class="icon-basket"></i> -->
					<span class="title"><spring:message code="admin"/></span>
					<span id="adminSelect"></span>
					<span id="adminOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageAdmin">
							<a href="<%=request.getContextPath()%>/sys-admin/manage-admin">
							<!-- <i class="icon-home"></i> -->
							<spring:message code="manage.admin"/></a>
						</li>
						<li id="newAdmin">
							<a href="<%=request.getContextPath()%>/sys-admin/signup-admin">
							<!-- <i class="icon-basket"></i> -->
							<spring:message code="create.newadmin"/></a>
						</li>
					</ul>
				</li>
				<li id="supportMenu">
					<a href="javascript:;">
					<!-- <i class="icon-rocket"></i> -->
					<span class="title"><spring:message code="customer.support"/></span>
					<span id="supportSelect"></span>
					<span id="supportOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageSupport">
							<a href="<%=request.getContextPath()%>/sys-admin/manage-support">
							<spring:message code="manage.customersupport"/></a>
						</li>
						<li id="newSupport">
							<a href="<%=request.getContextPath()%>/sys-admin/signup-support">
							<spring:message code="create.newsupport"/></a>
						</li>
					</ul>
				</li>
				<%-- <li>
					<a href="javascript:;">
					<i class="icon-diamond"></i>
					<span class="title">Tutors</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="<%=request.getContextPath()%>/sys-admin/manage-tutors">
							Manage Tutors</a>
						</li>
					</ul>
				</li> --%>
				<li id="masterDataMenu">
					<a href="javascript:;">
					<!-- <i class="icon-diamond"></i> -->
					<span class="title"><spring:message code="master.data"/></span>
					<span id="mDataSelect"></span>
					<span id="mDataOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageMData">
							<a href="<%=request.getContextPath()%>/sys-admin/add-subjects">
							<spring:message code="manage.subjects"/></a>
						</li>
						<li id="manageFeeData">
							<a href="<%=request.getContextPath()%>/sys-admin/manage-fee">
							<spring:message code="manage.tutorfee"/></a>
						</li>
					</ul>
				</li>
				<li id="planMenu">
					<a href="javascript:;">
					<!-- <i class="icon-puzzle"></i> -->
					<span class="title"><spring:message code="student.plan"/></span>
					<span id="planSelect"></span>
					<span id="planOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="managePlan">
							<a href="<%=request.getContextPath()%>/sys-admin/manage-plan">
							<spring:message code="manage.plans"/></a>
						</li>
						<!-- <li>
							<a href="components_pickers.html">
							Create Plan</a>
						</li> -->
					</ul>
				</li>
				<li id="cmsMenu">
					<a href="javascript:;">
					<!-- <i class="icon-puzzle"></i> -->
					<span class="title"><spring:message code="CMSVideos"/></span>
					<span id="cmsSelect"></span>
					<span id="cmsOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageHome">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/home">
							<spring:message code="manage.home"/></a>
						</li>
						<li id="manageHow">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/howitworks">
							<spring:message code="manage.HowWorks"/></a>
						</li>
						<li id="manageAbout">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/about">
							<spring:message code="manage.about.us"/></a>
						</li>
						<li id="manageFaq">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/faq">
							<spring:message code="manage.faq" /></a>
						</li>
						<li id="manageTnc">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/tnc">
							<spring:message code="manage.tnc"/></a>
						</li>
						<li id="manageTutorTnc">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/tutortnc">
							<spring:message code="manage.tutor.tnc"/></a>
						</li>
						<li id="managePrivacy">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/privacy">
							<spring:message code="manage.privacy"/></a>
						</li>
						<li id="manageHonor">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/honor">
							<spring:message code="manage.honor"/></a>
						</li>
						<li id="manageTutor">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/tutor">
							<spring:message code="manage.become.tutor"/></a>
						</li>
						<li id="manageTips">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/tips">
							<spring:message code="manage.tips"/></a>
						</li>
						<li id="manageParent">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/parent">
							<spring:message code="manage.parent"/></a>
						</li>
						<li id="manageStudent">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/student">
							<spring:message code="manage.student"/></a>
						</li>
						<li id="manageOrg">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCms/org">
							<spring:message code="manage.org"/></a>
						</li>
						<li id="manageNews">
							<a href="<%=request.getContextPath()%>/sys-admin/manageNews">
							<spring:message code="manage.news"/></a>
						</li>
						<li id="manageJob">
							<a href="<%=request.getContextPath()%>/sys-admin/manageJob">
							<spring:message code="manage.job"/></a>
						</li>
						<li id="manageImage">
							<a href="<%=request.getContextPath()%>/sys-admin/manageCmsImages">
							<spring:message code="manage.images"/></a>
						</li>
<%-- 						<li id="manageNews1">
							<a href="<%=request.getContextPath()%>/sys-admin/manageNews/news1">
							<spring:message code="manage.news.one"/></a>
						</li>
						<li id="manageNews2">
							<a href="<%=request.getContextPath()%>/sys-admin/manageNews/news2">
							<spring:message code="manage.news.two"/></a>
						</li>
						<li id="manageNews3">
							<a href="<%=request.getContextPath()%>/sys-admin/manageNews/news3">
							<spring:message code="manage.news.three"/></a>
						</li>
						<li id="manageNews4">
							<a href="<%=request.getContextPath()%>/sys-admin/manageNews/news4">
							<spring:message code="manage.news.four"/></a>
						</li> 
 						<li id="manageJob1">
							<a href="<%=request.getContextPath()%>/sys-admin/manageJob/job1">
							<spring:message code="manage.job.one"/></a>
						</li>
						<li id="manageJob2">
							<a href="<%=request.getContextPath()%>/sys-admin/manageJob/job2">
							<spring:message code="manage.job.two"/></a>
						</li>
						<li id="manageJob3">
							<a href="<%=request.getContextPath()%>/sys-admin/manageJob/job3">
							<spring:message code="manage.job.three"/></a>
						</li>
						<li id="manageJob4">
							<a href="<%=request.getContextPath()%>/sys-admin/manageJob/job4">
							<spring:message code="manage.job.four"/></a>
						</li> --%>
						
					</ul>
				
				
				</li>
					<li id="reporting">
					<a href="javascript:;">
					<!-- <i class="icon-puzzle"></i> -->
					<span class="title"><spring:message code="reporting"/></span>
					<span id="reportingSelect"></span>
					<span id="reportingOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="reportingPlan">
							<a href="<%=request.getContextPath()%>/sys-admin/scheduledSessions">
							<spring:message code="scheduled.sessions"/></a>
						</li>
						<li id="reportingPendingSession">
							<a href="<%=request.getContextPath()%>/sys-admin/unAcceptedSessions">
							<span class="title"><spring:message code="pendingsession"/></span>  <span class="badge badge-info" id="pendingSessions"></span>      </a> 
						</li>
						
								<li id="reportingCancelledSession">
							<a href="<%=request.getContextPath()%>/sys-admin/cancelledSessions">
							<span class="title"><spring:message code="cancelled"/> <spring:message code="sessions"/> </span>  <span class="badge badge-danger" id="cancelledSessions"></span>      </a> 
						</li>
						
						
						<li id="reportingAcceptedSession">
							<a href="<%=request.getContextPath()%>/sys-admin/acceptedSessions">
							<span class="title"><spring:message code="accepted"/> <spring:message code="sessions"/> </span>  <span class="badge badge-warning" id="acceptedSessions"></span>      </a>
						</li>
						
						<li id="reportingMessage">
							<a href="<%=request.getContextPath()%>/sys-admin/messageReports">
							<span class="title"><spring:message code="user.message"/>  </span>  <span class="badge badge-danger" id="messagesRecord"></span></a>
						</li>
							<li id="reportingLoginLogout">
							<a href="<%=request.getContextPath()%>/sys-admin/loginLogoutReports">
							<spring:message code="loginlogout"/></a>
						</li>
						</li>
							<li id="reportingLoginLogoutSupport">
							<a href="<%=request.getContextPath()%>/sys-admin/supportLoginLogoutReports">
							<spring:message code="support.loginlogout"/></a>
						</li>
						<li id="reportingStuDetail">
							<a href="<%=request.getContextPath()%>/sys-admin/studentDetails">
							<spring:message code="student.details"/></a>
						</li>
					    <li id="reportingTutorDetail">
							<a href="<%=request.getContextPath()%>/sys-admin/tutorDetails">
							<spring:message code="tutor.details"/></a>
						</li>
						
						<li id="reportingParentDetail">
							<a href="<%=request.getContextPath()%>/sys-admin/parentReports">
							<spring:message code="parent.details"/></a>
						</li>
						
						<li id="reportingTutorPayment">
							<a href="<%=request.getContextPath()%>/sys-admin/tutorPayments">
							<spring:message code="tutor.payment.report"/></a>
						</li>
						<li id="reportingStudentPayment">
							<a href="<%=request.getContextPath()%>/sys-admin/studentPayments">
							<spring:message code="student.payment.report"/></a>
						</li>
						
						<li id="activeChatReports">
							<a href="<%=request.getContextPath()%>/sys-admin/activeChatReports">
							<span class="title"><spring:message code="activeChats"/>  </span>  <span class="badge badge-danger" id="activeChatsRecord"></span></a>
						</li>
						
						<li id="reviewReports">
							<a href="<%=request.getContextPath()%>/sys-admin/reviewSessions">
							<span class="title"><spring:message code="review.homework"/>  </span>  <span class="badge badge-danger" id="reviewHomeworkRecord"></span></a>
						</li>
				
					</ul>
				</li>
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	
	<script type="text/javascript">
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
							$("#pendingSessions").show();
							$("#pendingSessions").text(response.modelMap.pendingCounter);
							
						}
						
						if(response.modelMap.acceptedCounter>'0'){
							$("#acceptedSessions").show();
							$("#acceptedSessions").text(response.modelMap.acceptedCounter);
							
						}
						
						if(response.modelMap.cancelledCounter>'0'){
							$("#cancelledSessions").show();
							$("#cancelledSessions").text(response.modelMap.cancelledCounter);
							
						}
						
						if(response.modelMap.activeChatCounter>'0'){
							$("#activeChatsRecord").show();
							$("#activeChatsRecord").text(response.modelMap.activeChatCounter);
						}
						if(response.modelMap.messgaeCounter>'0'){
							$("#messagesRecord").show();
							$("#messagesRecord").text(response.modelMap.messgaeCounter);
						}
					}
				}
			});  
		}, 5000);
	</script>