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
<title>MiProfe | <spring:message code="activeChats"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="content-type" content="text/plain; charset=ISO-8859-1"/>
<meta content="" name="description"/>
<meta content="" name="author"/>


<style>


           .popup-box
            {
                display: none;
                position: fixed;
                bottom: 58px;
                right: 220px;
                min-height:350px;
                background-color: rgb(237, 239, 244);
                width: 322px;
                border: 1px solid rgba(29, 49, 91, .3);
                bottom:10px;
            }
            
            .popup-box .popup-head
            {
                background-color: #0060a8;
                padding: 5px;
                color: white;
                font-weight: bold;
                font-size: 14px;
                clear: both;
            }
            
            .popup-box .popup-head .popup-head-left
            {
                float: left;
            }
            
            .popup-box .popup-head .popup-head-right
            {
                float: right;
                opacity: 0.5;
            }
            
            .popup-box .popup-head .popup-head-right a
            {
                text-decoration: none;
                color: inherit;
            }
            
            .popup-box .popup-messages
            {
                height: 100%;
            }
 
 

.bubble{
    background-color: #F2F2F2;
    border-radius: 5px;
    box-shadow: 0 0 6px #B2B2B2;
    display: inline-block;
    padding: 10px 18px;
    position: relative;
    vertical-align: top;
}

.bubble::before {
    background-color: #F2F2F2;
    content: "\00a0";
    display: block;
    height: 16px;
    position: absolute;
    top: 11px;
    transform:             rotate( 29deg ) skew( -35deg );
        -moz-transform:    rotate( 29deg ) skew( -35deg );
        -ms-transform:     rotate( 29deg ) skew( -35deg );
        -o-transform:      rotate( 29deg ) skew( -35deg );
        -webkit-transform: rotate( 29deg ) skew( -35deg );
    width:  20px;
}

.me {
    float: left;   
    margin: 5px 30px 5px 20px;       
   /*background-color: #C4C4C4;  */
   background-color: #dadada; 
  max-width: 228px;
  width:228px;
  overflow: hidden;
  word-break: break-word;
  -webkit-border-radius:5px;  -moz-border-radius:5px;  border-radius:5px;
}

.me::before {
    box-shadow: -2px 2px 2px 0 rgba( 178, 178, 178, .4 );
    left: -9px;       
    background-color: #C4C4C4; display: none;    
}

.you {
    float: right;    
    margin: 5px 20px 5px 30px;    
      max-width: 228px;
  overflow: hidden;
  word-break: break-word;      -webkit-border-radius:5px;  -moz-border-radius:5px;  border-radius:5px;
}

.you::before {
    box-shadow: 2px -2px 2px 0 rgba( 178, 178, 178, .4 );
    right: -9px;    
}

.db-chats-txt{width:100%; border:none; padding:0;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box; min-height:100px; max-height:300px;
 float:left; position:relative; }
.db-chats-txt .chat-hdr{width:100%; padding:0px 8px; background-color:#0060a8; font-size:16px; color:#ffffff; font-weight:700; height:35px; line-height:35px; 
-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;}
.db-chats-txt .chat-hdr span{font-weight:400;}
.db-chats-txt .chat-inner{width:100%; height:304px; overflow:auto; padding:5px 0;}
.db-chats-txt .chat-msg{width:100%; padding:5px; background-color:#e6e6e6;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;}
.db-chats-txt .chat-msg .chat-msg-txt{width:100%; float:left; border:1px solid #b8b8b8; background-color:#ffffff; overflow:hidden; height:33px;}
.db-chats-txt .chat-msg .chat-msg-txt input[type="text"]{padding:8px; border:none; font-size:13px; color:#838383; width:240px;}




</style>




<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>
<link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet" type="text/css"/>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body onload="navigationUpdate();" class="page-header-fixed page-quick-sidebar-over-content ">
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
			 <small><spring:message code="activeChats"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/sys-admin/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
					<a href="<%=request.getContextPath()%>/sys-admin/scheduledSessions"><spring:message code="reporting"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/sys-admin/activeChatReports"><spring:message code="activeChats"/></a>
					</li>
				</ul>
			</div>

			
			<iframe id="downloadTutorList" style="display:none;"></iframe>
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="activeChats"/>
							</div>
						
						</div>
						
						
						
						
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
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
							
								<th style="width:150px !important;">
									 <spring:message code="student.date"/> 
								</th>
								<th style="width:150px !important;">
									 <spring:message code="student.date"/> 
								</th>
								<th style="width:150px !important;">
									 <spring:message code="user1.email"/> 
								</th>
								<th style="width:150px !important;">
									 <spring:message code="user.type"/> 
								</th>
								<th class="center" style="width:70px !important;">
									 <spring:message code="user2.email"/>
								</th>
								<th >
									  <spring:message code="user.type"/>
								</th>
								
								<th style="width:250px !important;">
									 <spring:message code="action"/>
								</th>
								
								
								
								
							</tr>
							</thead>
							<tbody id="tdata">
							<c:forEach var="dtoActiveChatDetailList" items="${dtoActiveChatDetailList}">
							<tr>
							
								<td>
									${dtoActiveChatDetailList.lastChatTimeFormatted} 
								</td>
							    <td>
									${dtoActiveChatDetailList.lastChatTime} 
								</td>
							
								<td>
									 <a href="mailto:${dtoActiveChatDetailList.user1Email}">
									${dtoActiveChatDetailList.user1Email} </a>
								</td>
								<td>
									 ${dtoActiveChatDetailList.user1Role}
								</td>
								<td>
									 <a href="mailto:${dtoActiveChatDetailList.user2Email}">
									${dtoActiveChatDetailList.user2Email} </a>
								</td>
								<td>
									 ${dtoActiveChatDetailList.user2Role}
								</td>
								
								<td>
								
							<!-- 	register_popup(\''+value.userId+'\', \''+value.name+'\',\''+receiverRole+'\', \''+value.tutorProfileId+'\');" -->
								
								 <a onclick="viewDetails('${dtoActiveChatDetailList.user1UserId}', '${dtoActiveChatDetailList.user2UserId}', '${dtoActiveChatDetailList.user1EditedName}', '${dtoActiveChatDetailList.user2EditedName}');"> 
									<spring:message code="parent.view"/> <!-- </a> -->
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
			
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- Dialog start -->
<!-- responsive -->
						

						<!-- Session Dialog Start-->
 						 <div id="studentSessionDialog" class="popup-box fade" tabindex="-1"  style=" display: none;"> 
 						 
 						 <div class="popup-head">
 						 <div class="popup-head-left"></div>
 						 <!-- <div class="chat-seeMore"><a onclick="openSeeMore(88);">See More</a></div> -->
 						  <div class="popup-head-right"  data-dismiss="modal"  ><a>X</a></div> 
 						  
 						<div style="clear: both"></div>
 						 </div>
	                
	                <div class="db-chats-txt"> 
				<div class="chat-inner" id="chatin"><div style="position:relative;">
	               </div> <div style="position:relative;"></div></div><div class="chat-msg">
	                <div class="clr"></div> </div></div>
	                
						 </div>
						<!-- Session Dialog End  -->
							
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
	
	
	$('#sample_editable_1').DataTable({
		 "order": [[ 1, "desc" ]],
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
	                         null,
	                         null
	                        ]
	});
	   var tableWrapper = $("#sample_editable_1_wrapper");

	   tableWrapper.find(".dataTables_length select").select2({
	       showSearchInput: false //hide search box with special css class
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
	//   TableEditable.init();
	   FormValidation.init();
});
</script>

<script type="text/javascript">
function navigationUpdate(){
	$("#reporting").addClass("start active open");
	$("#reportingSelect").addClass("selected");
	$("#reportingOpen").addClass("arrow open");
	$("#activeChatReports").addClass("active");
}





function excelExport(){
	var url='<%=request.getContextPath()%>/sys-admin/activeChatExcelExport';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		success:function(response){
			if(response=="success"){
				//$("#excelExport").show();
				var url = window.location.origin+'<%=request.getContextPath()%>/ActiveChat-List.xlsx';
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

<script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

  <script type="text/javascript">
	function viewDetails(user1,user2,user1Name,user2Name){
		
		$("#chatin").html(" ");
		$('#studentSessionDialog').modal('show');
	
		$(".popup-head-left").html(user1Name+" - "+user2Name);
		
		
		// Get a database reference to our posts
		//var ref = new Firebase("https://docs-examples.firebaseio.com/web/saving-data/fireblog/posts");
		
		var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      myDataRef.authWithPassword({
	    		 email    : 'stu@india.com',
	    		 password : '5oanh1fih9pg'
	    		}, function(error, authData) {
	    		  if (error) {
	    		    // console.log("Login Failed!", error);
	    		  } else {
	    		 //   // console.log("Authenticated successfully with payload:", authData);
	    		 //   // console.log("Token", authData.token);
	    		//    // console.log("UserId is :",authData.uid);
	    		//    authData3=authData;
	    		  }},
	    		  {
	    			  remember:"sessionOnly"
	    		});
		
		var ref = new Firebase('https://aloprofe.firebaseio.com/users/'+ user1 +':'+ user2);
		ref.on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text,user1Name,user2Name);

	      });
		} // function ends
		
	    function displayChatMessage(name, text,user1Name,user2Name) {
			
			         if(name==user1Name){
				        $('<div class="bubble me" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'));
				        $('#chatin')[0].scrollTop = $('#chatin')[0].scrollHeight;
				    	}
				    	else{
				    		$('<div class="bubble you" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'));
					        $('#chatin')[0].scrollTop = $('#chatin')[0].scrollHeight;	
				    	} 
	      }

</script>

<!-- END JAVASCRIPTS -->




</body>
<!-- END BODY -->
</html>