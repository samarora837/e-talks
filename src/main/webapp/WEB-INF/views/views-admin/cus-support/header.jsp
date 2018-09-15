<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="<%=request.getContextPath()%>/cus-care/home">
			<img src="<%=request.getContextPath()%>/images/Final.png" alt="logo" class="logo-default" style="width: 96px;"/>
			</a>
			<div class="menu-toggler sidebar-toggler hide">
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN TOP NAVIGATION MENU -->
		<div class="top-menu">
			<ul class="nav navbar-nav pull-right">
				
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<li class="dropdown dropdown-user">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
					<img alt="" class="img-circle" src="<%=request.getContextPath()%>/admin/images/photo3.png"/>
					<span class="username username-hide-on-mobile">
					${dtoSuperAdminDetails.fname} </span>
					<i class="fa fa-angle-down"></i>
					</a>
					<ul class="dropdown-menu dropdown-menu-default">
						<li>
							<a href="<%=request.getContextPath()%>/cus-care/profile">
							<!-- <i class="icon-user"></i> --> <spring:message code="my.profile"/> </a>
						</li>
						
						<li>
							<a href="<c:url value="/j_spring_security_logout?name=cs" />">
							<!-- <i class="icon-key"></i> --> <spring:message code="parent.signout"/> </a>
						</li>
					</ul>
				</li>
				<!-- END USER LOGIN DROPDOWN -->
				<!-- BEGIN QUICK SIDEBAR TOGGLER -->
				<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
				<!-- <li class="dropdown dropdown-quick-sidebar-toggler">
					<a href="javascript:;" class="dropdown-toggle">
					<i class="icon-logout"></i>
					</a>
				</li> -->
				<!-- END QUICK SIDEBAR TOGGLER -->
			</ul>
		</div>
		<!-- END TOP NAVIGATION MENU -->
	</div>
	
	<script type="text/javascript">

var idleTime = 0;
$(document).ready(function () {
	
	
	var url='<%=request.getContextPath()%>/cus-care/setMeActive';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			
			
		}
	}); 
	
    //Increment the idle time counter every minute.
    var idleInterval = setInterval(timerIncrement, 120000); // 6 seconds

    //Zero the idle timer on mouse movement.
   $(this).mousemove(function (e) {
	
        idleTime = 0;
   });
    $(this).keypress(function (e) {
	
        idleTime = 0;
    });
});

function timerIncrement() {
    idleTime = idleTime + 1;
    if (idleTime >= 35) { 
       //alert("idle");
   // $("#signOut").click();
   // $("#signOut").trigger("click");
    window.location.href = "/j_spring_security_logout?name=cs";
    }
}


</script> 
	