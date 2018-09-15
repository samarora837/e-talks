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
<title> <spring:message code="message.title"/> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>




</head>
<body onload="navigationUpdate();" class="page-header-fixed page-quick-sidebar-over-content ">
<!-- BEGIN HEADER -->
<div class="page-header -i navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<%@ include file="/WEB-INF/views/views-admin/cus-support/header.jsp" %>
	<!-- END HEADER INNER -->
</div>
<!-- END HEADER -->
<div class="clearfix">
</div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN SIDEBAR -->
	<%@ include file="/WEB-INF/views/views-admin/cus-support/nav-sidebar.jsp" %>
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
							<button type="button" class="btn blue"><spring:message code="customer.save.changes"></spring:message> </button>
							<button type="button" class="btn default" data-dismiss="modal"><spring:message code="customer.close"></spring:message> </button>
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
						<spring:message code="customer.theme.color"></spring:message> </span>
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
						<spring:message code="customer.layout"></spring:message> </span>
						<select class="layout-option form-control input-sm">
							<option value="fluid" selected="selected"> <spring:message code="customer.fluid"></spring:message> </option>
							<option value="boxed"> <spring:message code="customer.boxed" ></spring:message> </option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.header"></spring:message> </span>
						<select class="page-header-option form-control input-sm">
							<option value="fixed" selected="selected"> <spring:message code="customer.fixed"></spring:message> </option>
							<option value="default"> <spring:message code="customer.default"></spring:message> </option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						<spring:message code="customer.topmenu"></spring:message> </span>
						<select class="page-header-top-dropdown-style-option form-control input-sm">
							<option value="light" selected="selected"> <spring:message code="customer.light"></spring:message> </option>
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
			<spring:message code="manage.messages"/> <small><spring:message code="manage.allmessage"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
				<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/cus-care/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						
						<a href="<%=request.getContextPath()%>/cus-care/messages"><spring:message code="manage.messages"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/cus-care/messages"><spring:message code="message"/></a>
					</li>
				</ul>
				
				
			</div>
			<c:if test="${sucessMessage eq 'Y' }">
			<div class="alert alert-success" id="sucessMessage">
								<strong><spring:message code="success"/></strong> <spring:message code="messagesent.success"/>
			</div>
			<%session.removeAttribute("sucessMessage");%>
			</c:if>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row margin-top-20">
				<div class="col-md-12">
					
					<!-- BEGIN PROFILE CONTENT -->
						<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box purple">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="manage.messages"/>
							</div>
							
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-12" align="right">
									
									<button type="button" class="btn blue" onclick="openSendMessagePopUp();"> <spring:message code="send.newmessage"/></button>
									</div>
									
								</div>
							</div>
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
							 <th width="150"><spring:message code="parent.date"/> </th>
								<th width="150">
									 <spring:message code="parent.date"/>
								</th>
								<th width="175">
									 <spring:message code="to"/>
								</th>
								<th width="175">
									 <spring:message code="from"/>
								</th>
								
								
								<th width="400">
									 <spring:message code="message"/>
								</th>
							
								<th width="100">
									 <spring:message code="action"/>
								</th>
							</tr>
							</thead>
							 <tbody>
							<c:forEach var="messgaList" items="${messgaList}">
							<tr>
							<td>${messgaList.messageDateTest}</td>
								<td>
									 ${messgaList.messageDate}
								</td>
								<td>
									${messgaList.toName}
								</td>
								<td>
									 ${messgaList.fromName}
								</td>
								
								
								<td>
									 ${messgaList.message}
								</td>
								
								<td class="center">
								<c:if test="${messgaList.fromName ne dtoSuperAdminDetails.fullName}">
									<a onclick="openReplyPopUp('${messgaList.fromUserName}','${messgaList.toUserName}',${messgaList.toId});"><spring:message code="reply"/> </a>
									</c:if>
								</td>
								
							</tr>
							</c:forEach>
						
							
							</tbody> 
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
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
<%@ include file="/WEB-INF/views/views-admin/common-js-include.jsp" %>

<script type="text/javascript">


 $(document).ready(function(){
    $('#sample_editable_1').DataTable({
    	"order": [[ 0, "desc" ]],
    	 "lengthMenu": [
            [5,10, 15, 20, -1],
            [5,10, 15, 20, "All"] 
        ],
        "aoColumns": [
                        {"bVisible": false},
                          {"iDataSort": 0},                              
                          null,
                          null,
                          null,
                          null                              
                         ]
});
    var tableWrapper = $("#sample_editable_1_wrapper");

    tableWrapper.find(".dataTables_length select").select2({
        showSearchInput: false //hide search box with special css class
    });
}); 



function openReplyPopUp(fromUserName,toUserName,toId){
	
	$('#fromUserName').text(fromUserName);
	$('#toUserName').text(toUserName);
	$('#toIdReply').val(toId);
	$('#replyPopup').modal('show');
}




function openSendMessagePopUp()
{
	
	var url='<%=request.getContextPath()%>/cus-care/getAllEmail';
	$('#toId').html('');
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response!=null){
				$("#fromUserNameSend").text(response.modelMap.messageDetails.fromName);
				
				
				$('#toId').children().remove();
				$('#toId').append('<option value=""><spring:message code="selectany.value"/></option>');
				$('#toId').append('<optgroup label="<spring:message code='tutor'/>">');
				 $.each( response.modelMap.messageDetails.tutorEmail, function( key , value ) {
				$('#toId').append('<option value="'+value+'">'+key+'</option>');
				 });
				 
				$('#toId').append('</optgroup');
				 
			  $('#toId').append('<optgroup label="<spring:message code='parents'/>">');
				 $.each( response.modelMap.messageDetails.parentEmail, function( key , value ) {
				$('#toId').append('<option value="'+value+'">'+key+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $('#toId').append('<optgroup label="<spring:message code='students'/>">');
				 $.each( response.modelMap.messageDetails.studentEmail, function( key , value ) {
				$('#toId').append('<option value="'+value+'">'+key+'</option>');
				 });
				 $('#toId').append('</optgroup'); 
				 
				 $('#sendMessagePopup').modal('show');
		}
		
		}
	
	
	
	});
	
}

<%-- function submitSendMessageForm(){
	 
	  
	$("#sendMessageForm").attr("action", "<%=request.getContextPath()%>/cus-care/sendMessage");
	$("#sendMessageForm").submit();
	 
}
 --%>
 jQuery(document).ready(function() {   
	 
	 $.extend({
		    playSound: function(){
		      return $("<embed src='"+arguments[0]+".mp3' hidden='true' autostart='true' loop='false' class='playSound'>" + "<audio autoplay='autoplay' style='display:none;' controls='controls'><source src='"+arguments[0]+".mp3' /><source src='"+arguments[0]+".ogg' /></audio>").appendTo('body');
		    }
		  });
	 
	setTimeout(function(){
		$("#sucessMessage").hide();
	}, 5000);
	
	
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   //TableEditable.init();
	   FormValidation.init();
	   ComponentsDropdowns.init();
	   ComponentsPickers.init();
});

function navigationUpdate(){
	$("#messageMenu").addClass("start active open");
	$("#messageSelect").addClass("selected");
	$("#messageOpen").addClass("arrow open");
	$("#manageMessage").addClass("active");
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

			<div id="replyPopup" class="modal fade" tabindex="-1" data-width="500">
							<form name="form_sample_1" id="form_sample_1" method="post" action="<%=request.getContextPath()%>/cus-care/sendReply">
							
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title green"> <spring:message code="message"/></h3>
								</div>
								
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
										
										<div class="form-group">
											<h4> <spring:message code="to"/> : </h4>
											
										</div>
										
										<div class="form-group">
										<h4><spring:message code="from"/> : </h4>
										</div>
										
										<div class="form-group">
											<h4> <spring:message code="message"/> : </h4>
											
										</div>
										
										</div>
										
								<div class="col-md-6">
								
									<div class="form-group">
											<h4></h4>
											<p id="toUserName">
											
											</p>
										</div>
								
										<div class="form-group">
										<h4></h4>
											<p id="fromUserName">
											
											</p>
										</div>
									
										<div class="form-group">
										
											<p>
												<textarea name="message" id="message" rows="6" cols="35"  maxlength="250" style="resize:none;" ></textarea>
											</p>
										</div>
										
							</div>
										
									</div>
								</div>
								
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="send.message"></spring:message></button>
								</div>
								</div>
								
								<input type="hidden" id="toIdReply" name="toIdReply">
								</form>
			</div>
			
			
			
				<div id="sendMessagePopup" class="modal fade" tabindex="-1" data-width="500">
							<form:form name="form_sample_11" id="form_sample_11" method="post" action="sendMessage" commandName="dtoMessageDetail">
							<div class="form-body">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
									<h3 class="modal-title"> <spring:message code="message"/></h3>
								</div>
								
								<div class="modal-body">
									<div class="row">
										<div class="col-md-3">
										
											<div class="form-group">
											<h4> <spring:message code="to"/> :</h4>
											<p>&nbsp;</p>
										</div>
										
										<div class="form-group">
										<h4><spring:message code="from"/> :</h4>
										</div>
										
										
										<div class="form-group">
											<h4> <spring:message code="message"/> :</h4>
											
										</div>
										
										</div>
										
								<div class="col-md-6">
								
									<div class="form-group">
											<h4></h4>
											<p>
											<form:select path="toId" class="bs-select form-control" id="toId">
												
				  								</form:select>
				  								
											</p>
											
										</div>
								
										<div class="form-group">
										<h4></h4>
											<p id="fromUserNameSend">
											
											</p>
										</div>
									
										<div class="form-group">
										
											<p style="margin-top: 30px;">
												<form:textarea  id="messageSend" rows="6" cols="35"  maxlength="250" path="message" style="resize:none;"></form:textarea>
											</p>
										</div>
										
							</div>
										
									</div>
								</div>
								
								<div class="modal-footer">
									<button type="button" data-dismiss="modal" class="btn btn-default"><spring:message code="admin.close"></spring:message></button>
									<button type="submit" class="btn blue"><spring:message code="send.message"></spring:message></button>
								</div>
								</div>
								
								</form:form>
			</div>


		
<!-- END BODY -->
</html>