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
					<span class="title"><spring:message code="dashboard"/></span>
					<span id="dashboardSelect"></span>
					<span id="dashboardOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="dashboard">
							<a href="<%=request.getContextPath()%>/admin/home">
							<!-- <i class="icon-bar-chart"></i> -->
							<spring:message code="dashboard"/></a>
						</li>
						<li id="myProfile">
							<a href="<%=request.getContextPath()%>/admin/profile">
							<!-- <i class="icon-bulb"></i> -->
						<spring:message code="my.profile"/></a>
						</li>
					</ul>
				</li>
				<li id="parentMenu">
					<a href="javascript:;">
					<!-- <i class="icon-basket"></i> -->
					<span class="title"><spring:message code="parents"/></span>
					<span id="parentSelect"></span>
					<span id="parentOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageParent">
							<a href="<%=request.getContextPath()%>/admin/manage-parents">
							<!-- <i class="icon-home"></i> -->
							<spring:message code="header.manageparents"/></a>
						</li>
					</ul>
				</li>
				<li id="tutorMenu">
					<a href="javascript:;">
					<!-- <i class="icon-rocket"></i> -->
					<span class="title"><spring:message code="tutor"/></span>
					<span id="tutorSelect"></span>
					<span id="tutorOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageTutor">
							<a href="<%=request.getContextPath()%>/admin/manage-tutors">
							<spring:message code="manage.tutor"/></a>
						</li>
						<li id="historyTutor">
							<a href="<%=request.getContextPath()%>/admin/history-tutors">
							<spring:message code="history"/></a>
						</li>
						<li id="tutorRating">
							<a href="<%=request.getContextPath()%>/admin/viewAllTutorRating">
							<spring:message code="tutor.rating"/></a>
						</li>
						
					</ul>
				</li>
				<li id="studentMenu">
					<a href="javascript:;">
					<!-- <i class="icon-diamond"></i> -->
					<span class="title"><spring:message code="students"/></span>
					<span id="studentSelect"></span>
					<span id="studentOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageStudent">
							<a href="<%=request.getContextPath()%>/admin/manage-students">
							<spring:message code="header.managestudent"/></a>
						</li>
						<!-- <li>
							<a href="#">
							<spring:message code="sessions"/></a>
						</li> -->
					</ul>
				</li>
				<li id="paymentMenu">
					<a href="javascript:;">
					<!-- <i class="icon-puzzle"></i> -->
					<span class="title"><spring:message code="payments"/></span>
					<span id="paymentSelect"></span>
					<span id="paymentOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="managePayment">
							<a href="<%=request.getContextPath()%>/admin/manageAllPayments">
							<spring:message code="manage.payments"/></a>
						</li>
					</ul>
				</li>
				<!-- <li class="heading">
					<h3 class="uppercase"><spring:message code="reporting"/></h3>
				</li> -->
				
				<li id="reporting">
					<a href="javascript:;">
					<!-- <i class="icon-puzzle"></i> -->
					<span class="title"><spring:message code="reporting"/></span>
					<span id="reportingSelect"></span>
					<span id="reportingOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="reportingPlan">
							<a href="<%=request.getContextPath()%>/admin/scheduledSessions">
							<spring:message code="scheduled.sessions"/></a>
						</li>
						<li id="reportingPendingSession">
							<a href="<%=request.getContextPath()%>/admin/unAcceptedSessions">
							<span class="title"><spring:message code="pendingsession"/></span>  <span class="badge badge-info" id="pendingSessions"></span>      </a> 
						</li>
						
						
						<li id="reportingCancelledSession">
							<a href="<%=request.getContextPath()%>/admin/cancelledSessions">
							<span class="title"><spring:message code="cancelled"/> <spring:message code="sessions"/> </span>  <span class="badge badge-danger" id="cancelledSessions"></span>      </a> 
							
						</li>
						
						
						<li id="reportingAcceptedSession">
							<a href="<%=request.getContextPath()%>/admin/acceptedSessions">
							<span class="title"><spring:message code="accepted"/> <spring:message code="sessions"/> </span>  <span class="badge badge-warning" id="acceptedSessions"></span>      </a>
						</li>
						
						<li id="reportingMessage">
							<a href="<%=request.getContextPath()%>/admin/messageReports">
							<span class="title"><spring:message code="user.message"/>  </span>  <span class="badge badge-danger" id="messagesRecord"></span></a>
						</li>
						<li id="reportingLoginLogout">
							<a href="<%=request.getContextPath()%>/admin/loginLogoutReports">
							<spring:message code="loginlogout"/></a>
						</li>

						<li id="reportingLoginLogoutSupport">
							<a href="<%=request.getContextPath()%>/admin/supportLoginLogoutReports">
							<spring:message code="support.loginlogout"/></a>
						</li>
						<li id="reportingStuDetail">
							<a href="<%=request.getContextPath()%>/admin/studentDetails">
							<spring:message code="student.details"/></a>
						</li>
					    <li id="reportingTutorDetail">
							<a href="<%=request.getContextPath()%>/admin/tutorDetails">
							<spring:message code="tutor.details"/></a>
						</li>
						
						<li id="reportingParentDetail">
							<a href="<%=request.getContextPath()%>/admin/parentReports">
							<spring:message code="parent.details"/></a>
						</li>
						
						<li id="reportingTutorPayment">
							<a href="<%=request.getContextPath()%>/admin/tutorPayments">
							<spring:message code="tutor.payment.report"/></a>
						</li>
						
						<li id="tutorRanking">
							<a href="<%=request.getContextPath()%>/admin/tutorRanking">
							<spring:message code="tutorranking"/></a>
						</li>
						<li id="studentRanking">
							<a href="<%=request.getContextPath()%>/admin/studentRanking">
							<spring:message code="student.ranking"/></a>
						</li>
						
						<li id="reportingStudentPayment">
							<a href="<%=request.getContextPath()%>/admin/studentPayments">
							<spring:message code="student.payment.report"/></a>
						</li>
						
						<li id="activeChatReports">
							<a href="<%=request.getContextPath()%>/admin/activeChatReports">
							<span class="title"><spring:message code="activeChats"/>  </span>  <span class="badge badge-danger" id="activeChatsRecord"></span></a>
						</li>
						
							<li id="reviewReports">
							<a href="<%=request.getContextPath()%>/admin/reviewSessions">
							<span class="title"><spring:message code="review.homework"/>  </span>  <span class="badge badge-danger" id="reviewHomeworkRecord"></span></a>
						</li>
				
					</ul>
				</li>
				
				<li id="promotionalMinuteAmount">
					<a href="javascript:;">
					<!-- <i class="icon-diamond"></i> -->
					<span class="title"><spring:message code="promotional.minuteamount"/></span>
					<span id="studentMinute"></span>
					<span id="studentMinuteOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageStudentMinute">
							<a href="<%=request.getContextPath()%>/admin/manage-students-minute">
							<spring:message code="student.promotiontitle"/></a>
						</li>
						<li id="manageTutorAmout">
							<a href="<%=request.getContextPath()%>/admin/manageTutorAmount">
							<spring:message code="tutor.promotiontitle"/></a>
						</li>
					</ul>
				</li>
				
			
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>
	
	
	
	
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
			var url='<%=request.getContextPath()%>/admin/getAllSessionsReadStatus';
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
	
	
	
	
	
	
	
	
	