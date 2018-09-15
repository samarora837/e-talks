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
							<a href="<%=request.getContextPath()%>/cus-care/home">
							<!-- <i class="icon-bar-chart"></i> -->
							<spring:message code="dashboard"/></a>
						</li>
						<li id="myProfile">
							<a href="<%=request.getContextPath()%>/cus-care/profile">
							<!-- <i class="icon-bulb"></i> -->
							<spring:message code="my.profile"/></a>
						</li>
					</ul>
				</li>
				<li id="messageMenu">
					<a href="javascript:;">
					<span class="title"><spring:message code="manage.messages"/> </span><span class="badge badge-danger" id="messageCount"></span>
					<span id="messageSelect"></span>
					<span id="messageOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="manageMessage">
							<a href="<%=request.getContextPath()%>/cus-care/messages">
							<spring:message code="student.messages"/></a>
						</li>
					</ul>
				</li>
				
				<li id="chatMenu">
					<a href="javascript:;">
					<span class="title"><spring:message code="support.chat"/></span><span class="badge badge-danger" id="chatCount"></span>
					<span id="chatSelect"></span>
					<span id="chatOpen"></span>
					</a>
					<ul class="sub-menu">
						<li id="chatMessage">
							<a href="<%=request.getContextPath()%>/cus-care/chat">
							 <spring:message code="activeChats"/></a> 
						</li>
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
							<a href="<%=request.getContextPath()%>/cus-care/scheduledSessions">
							<spring:message code="scheduled.sessions"/></a>
						</li>
						<li id="reportingPendingSession">
							<a href="<%=request.getContextPath()%>/cus-care/unAcceptedSessions">
							<span class="title"><spring:message code="pendingsession"/></span>  <span class="badge badge-info" id="pendingSessions"></span>      </a> 
						</li>
						
								<li id="reportingCancelledSession">
							<a href="<%=request.getContextPath()%>/cus-care/cancelledSessions">
							<span class="title"><spring:message code="cancelled"/> <spring:message code="sessions"/> </span>  <span class="badge badge-danger" id="cancelledSessions"></span>      </a> 
						</li>
						
						
						<li id="reportingAcceptedSession">
							<a href="<%=request.getContextPath()%>/cus-care/acceptedSessions">
							<span class="title"><spring:message code="accepted"/> <spring:message code="sessions"/> </span>  <span class="badge badge-warning" id="acceptedSessions"></span>      </a>
						</li>
						<li id="reportingMessage">
							<a href="<%=request.getContextPath()%>/cus-care/messageReports">
							<span class="title"><spring:message code="user.message"/>  </span>  <span class="badge badge-danger" id="messagesRecord"></span></a>
						</li>
						<%-- 	<li id="reportingLoginLogout">
							<a href="<%=request.getContextPath()%>/cus-care/loginLogoutReports">
							<spring:message code="loginlogout"/></a>
						</li> --%>
						<li id="reportingLoginLogoutSupport">
							<a href="<%=request.getContextPath()%>/cus-care/supportLoginLogoutReports">
							<spring:message code="support.loginlogout"/></a>
						</li>
						
						<li id="activeChatReports">
							<a href="<%=request.getContextPath()%>/cus-care/activeChatReports">
							<span class="title"><spring:message code="activeChats"/>  </span>  <span class="badge badge-danger" id="activeChatsRecord"></span></a>
						</li>
						
						<li id="reviewReports">
							<a href="<%=request.getContextPath()%>/cus-care/reviewSessions">
							<span class="title"><spring:message code="review.homework"/>  </span>  <span class="badge badge-danger" id="reviewHomeworkRecord"></span></a>
						</li>
				
					</ul>
				</li>
				
				
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
	</div>




	
	 <script type="text/javascript">

	 
	
	 var soundAlertCount="0";
		var chatAlertCount="0";
		var soundurl='<%=request.getContextPath()%>/sound/beep';
	 
	 
  
	 setInterval(function(){
			var url='<%=request.getContextPath()%>/cus-care/getMessageCount';
			$.ajax({
				url:url,
				method:'GET',
				async :false,
				success:function(response){
					if(response != null){
						if(response=='0'){
							$("#messageCount").text("");
							$("#messageCount").hide();
						}
						if(response>'0'){
							
							$("#messageCount").show();
							$("#messageCount").text(response);
							
							 if(soundAlertCount!=response){
								$.playSound(soundurl);
								soundAlertCount=response;
							} 
							
							}
					}
				}
			});  
		}, 2000);
	 
	 
	 setInterval(function(){
		 
			var url='<%=request.getContextPath()%>/cus-care/getActiveChatCount';
			$.ajax({
				url:url,
				method:'GET',
				async :false,
				success:function(response){
					if(response != null){
						if(response=='0'){
							$("#chatCount").text("");
							$("#chatCount").hide();
						}
						if(response>'0'){
							
							$("#chatCount").show();
							$("#chatCount").text(response);
							
							 if(chatAlertCount!=response){
								$.playSound(soundurl);
								chatAlertCount=response;
							} 
							
							}
					}
				}
			});  
		}, 10000);
	 
	 
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
		var url='<%=request.getContextPath()%>/cus-care/getAllSessionsReadStatus';
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


	