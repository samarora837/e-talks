<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
<title> <spring:message code="support.chat"/> </title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<meta content="" name="description"/>
<meta content="" name="author"/>
<%@ include file="/WEB-INF/views/views-admin/common-css-include.jsp" %>


 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
 <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">





        <style type="text/css">
            @media only screen and (max-width : 540px) 
            {
                .chat-sidebar
                {
                    display: none !important;
                }
                
                .chat-popup
                {
                    display: none !important;
                }
            }
            
            body
            {
                background-color: #e9eaed;
            }
            
            .chat-sidebar
            {
                width: 200px;
                position: fixed;
                height: 100%;
                right: 0px;
                top: 0px;
                padding-top: 10px;
                padding-bottom: 10px;
                border: 1px solid rgba(29, 49, 91, .3);
            }
            
            .sidebar-name 
            {
                padding-left: 10px;
                padding-right: 10px;
                margin-bottom: 4px;
                font-size: 12px;
            }
            
            .sidebar-name span
            {
                padding-left: 5px;
            }
            
            .sidebar-name a
            {
                display: block;
                height: 100%;
                text-decoration: none;
                color: inherit;
            }
            
            .sidebar-name:hover
            {
                background-color:#e1e2e5;
            }
            
            .sidebar-name img
            {
                width: 32px;
                height: 32px;
                vertical-align:middle;
            }
            
 /*            .popup-box
            {
                display: none;
                position: fixed;
                bottom: 58px;
                right: 220px;
                height: 226px;
                background-color: rgb(237, 239, 244);
                width: 322px;
                border: 1px solid rgba(29, 49, 91, .3);
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
            
            
            .chat {
    width: 400px;
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
    margin: 5px 45px 5px 20px;       
   background-color: #dadada; 
  max-width: 228px;
  width:228px;
  overflow: hidden;
  word-break: break-word;
}

.me::before {
    box-shadow: -2px 2px 2px 0 rgba( 178, 178, 178, .4 );
    left: -9px;       
    background-color: #C4C4C4; display: none;    
}

.you {
    float: right;    
    margin: 5px 20px 5px 45px;    
      max-width: 228px;
  overflow: hidden;
  word-break: break-word;     
}

.you::before {
    box-shadow: 2px -2px 2px 0 rgba( 178, 178, 178, .4 );
    right: -9px;    
} */
            
        </style>
        
  <script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
  <%@ include file="/js/firebase/support-firebase-script.jsp" %>     
        
        <script type="text/javascript">
 <%--        //this function can remove a array element.
        Array.remove = function(array, from, to) {
            var rest = array.slice((to || from) + 1 || array.length);
            array.length = from < 0 ? array.length + from : from;
            return array.push.apply(array, rest);
        };
    
        //this variable represents the total number of popups can be displayed according to the viewport width
        var total_popups = 1;
        
        //arrays of popups ids
        var popups = [];
    
        //this is used to close a popup
            function close_popup(id,chatSessionId)
            {
                	$("#"+id).css("display", "none");
                        endChatSession(chatSessionId);
        		
            }
        
            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            var right = 220;
            var resetPostion=0;
            function display_popups()
            {
            	 
            	/* if(resetPostion==4){
            		right = 220;
            		resetPostion=0;
                } */
            	var right = 220;
            	
                var iii = 0;
                 for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                    	if(popups[iii]==tutId){
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + 320;
                        element.style.display = "block";
                        resetPostion++;
                    	}
                    }
                } 
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            //creates markup for a new popup. Adds the id to popups array.
          var tutId="0";
            var tutorUserId='${userId}';
            function register_popup(id, name,sessionId)
            {
            	if(sessionId=='0'){
            		var url='<%=request.getContextPath()%>/cus-care/requestChatBySupport';
                	$.ajax({
                		url:url,
                		method:'GET',
                		async :false,
                		data:{id:id},
                		success:function(response){
                			if(response.modelMap.chatSessionId!='0'){
                				sessionId=response.modelMap.chatSessionId;
                			}
                		}
                	    });
            	}
            	
            	var chatSatus="N";
            	if($("#"+id).length!=0){
            		$("#"+id).css("display", "block"); 
            	} 
            	
            	$("#justtest").val(id);
            	tutId=id;
                for(var iii = 0; iii < popups.length; iii++)
                {   
                    //already registered. Bring it to front.
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                    
                        popups.unshift(id);
                        
                        calculate_popups();
                        
                        
                        return;
                    }
                }               
                if($("#"+id).length==0){
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup('+ id +','+sessionId+');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages">';
                
                element = element + ' <div class="db-chats-txt">'+ 
				'<div class="chat-inner" id="chatin'+id+'-'+tutorUserId+'"><div id="position:relative;">'+ 
	               ' </div> <div id="position:relative;"></div></div><div class="chat-msg">'+ 
	               ' <div class="chat-msg-txt"><input type="text"  class="trying" id="chatMsg'+id+'-'+tutorUserId+'" placeholder="Type Something " >'+ 
	               ' <a href="javascript:;" class="chat-msg-submit" onclick="sendMsg('+id+'-'+tutorUserId+');" ><spring:message code="parent.submit" /></a> </div>'+ 
	                '<div class="clr"></div> </div></div></div></div>';
                
                chatSatus="Y"; 
                }
                
                
                
                
                document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
        
                popups.unshift(id);
                        
                if($("#"+id).length!=0){
            		calculate_popups();
            	}
                
              //  calculate_popups();
                
                openchatwind(id,chatSatus);
                
            }
            
            
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
               /*  if(width < 540)
                {
                    total_popups = 0;
                }
                else
                {
                    width = width - 200;
                    //320 is width of a single popup box
                    total_popups = parseInt(width/320);
                } */
                
                display_popups();
                
            }
            
            //recalculate when window is loaded and also when window is resized.
        //    window.addEventListener("resize", calculate_popups);
       //     window.addEventListener("load", calculate_popups);
            
        </script>



 <!-- //=================firebase========================= -->
<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
<script type="text/javascript">


function endChatSession(chatSessionId){
	 var url='<%=request.getContextPath()%>/cus-care/EndSupportChatSession';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{chatSessionId:chatSessionId},
			success:function(){
			}
		});
	
}


function sendMsg(fixit){ 
	 $( ".trying" ).keypress();
    var text = $('#chatMsg'+fixit).val();
  // // console.log("Text:", text);
   
    if(text!=null){
  	  
  	  window['newUserRef' + studentId1 ].push({name: "tutor", text: text});
    $('#chatMsg'+fixit).val('');
    
    
    }
  }

var studentId1="";
var tutorId = "";

var posfix = "";
var newUserRef ="";
function openchatwind(id,chatSatus){
	studentId1=id;
	tutorId='${userId}';

		
		var posfix =studentId1+"-"+tutorId;
		
		
		
		if(chatSatus=='Y'){
			window['newUserRef' + studentId1 ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		
		
	//	 window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		 
		 window['newUserRef' + studentId1 ].on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      myDataRef.authWithPassword({
	    		 email    : '${firebaseUser}',
	    		 password : '${firebasePass}'
	    		}, function(error, authData) {
	    		  if (error) {
	    		    // console.log("Login Failed!", error);
	    		  } else {
	    		    // console.log("Authenticated successfully with payload:", authData);
	    		    // console.log("Token", authData.token);
	    		    // console.log("UserId is :",authData.uid);
	    		    authData3=authData;
	    		 
	    		  }},
	    		  {
	    			  remember:"sessionOnly"
	    		  
	    		  
	    		}); 
	
	      
	      
	      var postFix = posfix;
	      
	      $('.trying').keypress(function (e) {
	    	  var textBoxId = $(e.target).attr("id");
	    	  var msgVal = $("#"+textBoxId).val();
		        if ((e.keyCode == 13 || typeof e.which === "undefined") && msgVal.trim()!="") {
		          var name = '${userFname}';
		          var elementIdVal = $(e.target).attr("id");
		          var otherUsersId = elementIdVal.substring(7, elementIdVal.indexOf('-'));
		          var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
		          
		    	  window['newUserRef' + studentId1 ]=new Firebase('https://aloprofe.firebaseio.com/users/'+otherUsersId +':'+supportUserId);
		        //  var text = $('#'+elementIdVal).val();
		         
		         var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+elementIdVal).val();
		        
		        window['newUserRef' + studentId1 ].push({name: name, text: text});
		       
		          $('#'+elementIdVal).val('');  
		        
		        }
		      });
	      
		
	    function displayChatMessage(name, text) {
	        $('<div/>').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
	        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;
	      };
	       

	   }
	   
	   
		if(chatSatus=='N'){
		 
		 window['newUserRef' + studentId1 ].on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      myDataRef.authWithPassword({
	    		 email    : '${firebaseUser}',
	    		 password : '${firebasePass}'
	    		}, function(error, authData) {
	    		  if (error) {
	    		    // console.log("Login Failed!", error);
	    		  } else {
	    		    // console.log("Authenticated successfully with payload:", authData);
	    		    // console.log("Token", authData.token);
	    		    // console.log("UserId is :",authData.uid);
	    		    authData3=authData;
	    		 
	    		  }},
	    		  {
	    			  remember:"sessionOnly"
	    		  
	    		  
	    		}); 
	
	      
	      
	      var postFix = posfix;
	  	
		$('#chatMsg'+postFix).keypress(function (e) {
	        if (e.keyCode == 13) {
	        	alert("fffffffffffffff");
	          var name = '${userFname}';
	          var text = $('#chatMsg'+postFix).val();
	            
	         newUserRef.push({name: name, text: text});
	       
	          $('#chatMsg'+postFix).val('');  
	          
	          var url='<%=request.getContextPath()%>/student/requestTutorChat';
	      	$.ajax({
	      		url:url,
	      		method:'GET',
	      		async :false,
	      		data:{tutorId:tutorId},
	      		success:function(response){
	      		}
	      	    });
	          
	          
	        
	        }
	      });
		
	      
	      
	      $('.trying').keypress(function (e) {
	    	  var textBoxId = $(e.target).attr("id");
	    	  var msgVal = $("#"+textBoxId).val();
		        if ((e.keyCode == 13 || typeof e.which === "undefined") && msgVal.trim()!="") {
		          var name = '${userFname}';
		          var elementIdVal = $(e.target).attr("id");
		          var otherUsersId = elementIdVal.substring(7, elementIdVal.indexOf('-'));
		          var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
		    	  window['newUserRef' + studentId1 ]=new Firebase('https://aloprofe.firebaseio.com/users/'+otherUsersId +':'+supportUserId);
		       //   var text = $('#'+elementIdVal).val();
		       
		        var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+elementIdVal).val();
		       
		       window['newUserRef' + studentId1 ].push({name: name, text: text});
		       
		          $('#'+elementIdVal).val('');  
		          
		        }
		      });
	      
		
	    function displayChatMessage(name, text) {
	    	if(name=='${userFname}'){
	        $('<div class="bubble me" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
	        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;
	    	}
	    	else{
	    		$('<div class="bubble you" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
		        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;	
	    	}
	      };
	       

	   }	   
	   
		

} --%>

</script>
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
			<spring:message code="support.chat"/> <small><spring:message code="manage.allactivechat"/></small>
			</h3>
			<div class="page-bar">
				<ul class="page-breadcrumb">
					<li>
						<i class="fa fa-home"></i>
						<a href="<%=request.getContextPath()%>/cus-care/home"><spring:message code="student.hometext"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/cus-care/chat"><spring:message code="support.chat"/></a>
						<i class="fa fa-angle-right"></i>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/cus-care/chat"><spring:message code="activeChats"/></a>
					</li>
				</ul>
				
				
			</div>
		
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
								<i class="fa fa-edit"></i><spring:message code="activeChats"/>
							</div>
							
						</div>
						
						
						
						
						<div class="portlet-body">
							 
           <div class="cuxt-support">
            <div class="stu-index-txt"  id="studentDetails">
            <div style="width: 100%; height: 400px; overflow: auto;">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoStudentDetails}" var="dtoStudentDetails">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" >
                            	
                            	<c:if test="${dtoStudentDetails.loginStatus eq 'Y'}">
                        	<img class="status${dtoStudentDetails.userId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${dtoStudentDetails.loginStatus eq 'N'}">
                        	<img class="status${dtoStudentDetails.userId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                            	
                            	</div>
                                <div class="tutor-img-txt">
                                ${dtoStudentDetails.fullName} 
                                </div>
                                
                                <div class="emailId">
                                ${dtoStudentDetails.email}
                                </div>
                                
                                <div class="occup">
                                ${dtoStudentDetails.userRole}
                                </div>
                                
                                <div class="country">
                                ${dtoStudentDetails.country}
                                </div>
                                
                                
                                
                                <div class="book-session">
                                <c:if test="${dtoStudentDetails.readBySupport eq 'Y' }">
                                	<a href="javascript:register_popup('${dtoStudentDetails.userId}', '${dtoStudentDetails.fullName}', '${dtoStudentDetails.chatSessionId}');" name="chatTutor${dtoStudentDetails.userId}" id="chatTutor${dtoStudentDetails.userId}"  class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                               <c:if test="${dtoStudentDetails.readBySupport eq 'N' }">
                                	<a href="javascript:register_popup('${dtoStudentDetails.userId}', '${dtoStudentDetails.fullName}', '${dtoStudentDetails.chatSessionId}');" name="chatTutor${dtoStudentDetails.userId}" id="chatTutor${dtoStudentDetails.userId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                                </div>
                            </div>
                        </li>
                        <c:set var="count" value="1" scope="page"/>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                        
                    </ul>
                    </div>
                </div> 
              </div>
              <div class="clr"></div>
						</div>
						
						
						
						
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			
			
			
			
			<!-- =================== for all online users============================== -->
			
						<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box red">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-edit"></i><spring:message code="login.users"/>
							</div>
						
						</div>
						<div class="portlet-body">
							<%-- <div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group"><a href="<%=request.getContextPath()%>/sys-admin/signup-admin">
											<button class="btn green">
											Add New <i class="fa fa-plus"></i>
											</button></a>
										</div>
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
							</div> --%>
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
							
							    <th>
									 <spring:message code="parent.firstname"/>
								</th>
							
								<th>
									 <spring:message code="user.type"/> 
								</th>
								<th>
									 <spring:message code="country"/> 
								</th>
								
								<th>
									 <spring:message code="action"/>
								</th>
								
							</tr>
							</thead>
							<tbody>
							<c:forEach var="LoginUsersList" items="${LoginUsersList}">
							<c:if test="${LoginUsersList.loginstatus eq 'Y'}">
							<tr>
							<td>
									 ${LoginUsersList.name}
									 
								</td>
								<td>
										 ${LoginUsersList.typeofUser}
									 
								</td>
								
								<td>
										 ${LoginUsersList.country}
								</td>
								<td>

<a href="javascript:register_popup('${LoginUsersList.userId}', '${LoginUsersList.name}', '${LoginUsersList.chatSessionId}');" name="chatTutor${LoginUsersList.userId}" id="chatTutor${LoginUsersList.userId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>									
									
									
								</td>
							</tr>
							</c:if>
						 </c:forEach> 
							</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
		<!-- 	========================= for all online users ================================= -->
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
function openReplyPopUp(fromUserName,toUserName,toId){
	
	$('#fromUserName').text(fromUserName);
	$('#toUserName').text(toUserName);
	$('#toIdReply').val(toId);
	$('#replyPopup').modal('show');
}




function openSendMessagePopUp()
{
	
	var url='<%=request.getContextPath()%>/cus-care/getAllEmail';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response!=null){
				$("#fromUserNameSend").text(response.modelMap.messageDetails.fromName);
				
				
				$('#toId').children().remove();
				$('#toId').append('<option value=""><spring:message code="selectany.value"/></option>')
				$('#toId').append('<optgroup label="<spring:message code='tutor'/>">');
				 $.each( response.modelMap.messageDetails.tutorEmail, function( key , value ) {
				$('#toId').append('<option value="'+key+'">'+value+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $('#toId').append('<optgroup label="<spring:message code='parents'/>">');
				 $.each( response.modelMap.messageDetails.parentEmail, function( key , value ) {
				$('#toId').append('<option value="'+key+'">'+value+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $('#toId').append('<optgroup label="<spring:message code='students'/>">');
				 $.each( response.modelMap.messageDetails.studentEmail, function( key , value ) {
				$('#toId').append('<option value="'+key+'">'+value+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $('#sendMessagePopup').modal('show');
		}
		
		}
	
	
	
	});
	
}


 jQuery(document).ready(function() {   
	
	 
	 $.extend({
		    playSound: function(){
		      return $("<embed src='"+arguments[0]+".mp3' hidden='true' autostart='true' loop='false' class='playSound'>" + "<audio autoplay='autoplay' style='display:none;' controls='controls'><source src='"+arguments[0]+".mp3' /><source src='"+arguments[0]+".ogg' /></audio>").appendTo('body');
		    }
		  });
	 
	 
	   Metronic.init(); // init metronic core components
	   Layout.init(); // init current layout
	   QuickSidebar.init(); // init quick sidebar
	   Demo.init(); // init demo features
	   TableEditable.init();
	   FormValidation.init();
	   ComponentsDropdowns.init();
	   ComponentsPickers.init();
});

function navigationUpdate(){
	$("#chatMenu").addClass("start active open");
	$("#chatSelect").addClass("selected");
	$("#chatOpen").addClass("arrow open");
	$("#chatMessage").addClass("active");
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


<script type="text/javascript">
var chatlistSize = '${fn:length(dtoStudentDetails)}';
setInterval(function(){
//	alert("called for change");
	var url='<%=request.getContextPath()%>/cus-care/getSupportChatSessionDetails';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null && response.modelMap.dtoStudentDetails!=null){
				var newlistsize= response.modelMap.listNewDtoStudentDetailsize;
			//	if(chatlistSize!=newlistsize){
				var studentData='<div style="width: 100%; height: 400px; overflow: auto;"><ul>';
				 $.each( response.modelMap.dtoStudentDetails, function(index,value) {
			    studentData += '<li>'+
               	          '<div class="stud-img-outr">'+
                       	  '<div class="indi-icon" >';
                       	  
                       	 <%--  <img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""> --%>
                       	  
                       	  if(value.loginStatus=='Y'){
                       		  studentData+=	'<img class="status'+value.userId+'" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
     						}
     						else{
     							studentData+= '<img class="status'+value.userId+'" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
     						}
                       	  
                       	  
                       	  studentData+=      '</div><div class="tutor-img-txt">'+
                              value.fullName+'</div>';
                              
                              
                              studentData+=    '<div class="emailId">'+value.email+'</div>';
                              
                              
                             studentData+=      '<div class="occup">'+
                             value.userRole+
                            '</div>'+
                            
                            '<div class="country">'+value.country+'</div>'+
                             
                             '<div class="book-session">';
                             
                             if(value.readBySupport=='Y'){
                            	 studentData+=    	 '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.fullName+'\', \''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'" class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';                   	 
                             }
                             else{
                            	 studentData+=	 '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.fullName+'\', \''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'" class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';	 
                             }
                             
                             
              studentData += '</div></div></li>'; 
                   
               	   			   
						 });
				 studentData += '</ul></div>';
				$("#studentDetails").html(studentData);
				
				/* if(chatlistSize<newlistsize){
					$.playSound(soundurl);
				} */
					chatlistSize=newlistsize;
				
	//			}
			
			}
			if(response.modelMap.listNewDtoStudentDetailsize==0){
				var stuData='<div style="width: 100%; height: 400px; overflow: auto;"><ul><h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3></ul></div>';
				$("#studentDetails").html(stuData);
			}
		}
	});  
   
   
}, 10000); 


setInterval(function(){
	   
	var url='<%=request.getContextPath()%>/cus-care/allUsersLoginStatus';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null){
				
				$.each( response.modelMap.usersLoginstatus, function( key , value ) {
					 
					if(value.login_status=='Y'){
						var online='<%=request.getContextPath()%>/images/green-bullet.png';
						$(".status"+value.userId).attr("src",online);
					}
					if(value.login_status=='N'){
						var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
						$(".status"+value.userId).attr("src",offline);
					}
					
					 });
				
			}
			
		}
	});  
}, 2000);

<%-- setInterval(function(){
	var url='<%=request.getContextPath()%>/cus-care/getAllUserChatMessageStatus';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null && response.modelMap.dtoCustomerSupportDetails != null){
				$.each( response.modelMap.dtoCustomerSupportDetails, function( key , value ) {
					 
					 if(value.readBySupport=='N'){
						 alert("n=========="+value.readBySupport);
						 $("#chatTutor"+value.commonUserId).removeClass("grayBtn-normal");
						 $("#chatTutor"+value.commonUserId).addClass("greenBtn-normal");
						 
                     }
                     else{
                    	 alert("y=========="+value.commonUserId);
                    	 $("#chatTutor"+value.commonUserId).removeClass("greenBtn-normal");
						 $("#chatTutor"+value.commonUserId).addClass("grayBtn-normal");
                    	 
                     } 
					
					 });
			}
		}
	});  
}, 5000); --%>



</script>

</body>

		
<!-- END BODY -->
</html>