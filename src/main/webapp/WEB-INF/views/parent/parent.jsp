<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="parent.home" /></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
  
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,400italic,300' rel='stylesheet' type='text/css'>    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--[if gte IE 9]>
  <style type="text/css">
    .gradient {
       filter: none;
    }
  </style>
<![endif]-->

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/jquery-ui.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/styleDate.css">



<link href="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.js"></script>

<script type="text/javascript">
  $(function() {
	  $( ".ellipsis" ).tooltip({
		  open: function (event, ui) {
		        ui.tooltip.css("word-break", "break-all");
		    },
	      position: {
	        my: "top",
	        at: "top+15"
	      }
	    });
  });
  </script>
  
  
  
  
  
  
  
  
 <!--cccchat-->
        <style type="text/css">
@media only screen and (max-width : 540px) {
.chat-sidebar {
	display: none !important;
	
}
.chat-popup {
	display: none !important;
}
}
body {
	background-color: #e9eaed;
}
.chat-sidebar {
	width: 200px;
	position: fixed;
	height: 100%;
	right: 0px;
	top: 0px;
	padding-top: 0px;
	padding-bottom: 20px;
	border: 1px solid rgba(29, 49, 91, .3);
	background-color:#fafafa; overflow:auto;
	-webkit-box-sizing:border-box; -moz-box-sizing:border-box; box-sizing:border-box;
}
.chat-sidebar h3 {color: #333; font-size:14px; border-bottom:1px solid #d8d8d8; padding:5px 8px; margin-bottom:5px; background-color: #eaeaea;}

.sidebar-name {
	padding-left: 10px;
	padding-right: 10px;
	margin-bottom: 4px;
	font-size: 12px;
}
.sidebar-name span {
	padding-left: 5px;
}
.sidebar-name a {
	display: block;
	/* height: 100%; */
	text-decoration: none;
	color: inherit;
}
.sidebar-name:hover {
	background-color:#B5CCE8;
}
.sidebar-name img {
	width: 32px;
	height: 32px;
	vertical-align: middle;
}
            
/*             .popup-box
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
                 margin-top: -3px;
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
            
#openChatBar { 
    position: fixed; 
    bottom:0%;
    width:100%; 
    background-color: #0060a8; 
    opacity: 1;
    width: 200px;
    right: 0px;
    text-indent: 7px;
    font-size: 15px;
}

        </style>
  
    <script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
  <%@ include file="/js/firebase/parent-firebase-home-script.jsp" %>
      
 
 
 <!--  **************************************************************** FIREBASE SCRIPT************************************************************************* -->
        
        <script type="text/javascript">
   <%--          //this function can remove a array element.
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
            function close_popup(id)
            {

            	$("#"+id).css("display", "none");
            	/* alert("popups.length====="+popups.length);
                 for(var iii = 0; iii < popups.length; iii++)
                {
                	 
                	 
                     if(id == popups[iii])
                    {
                    	alert("closing"+iii);
                        Array.remove(popups, iii);
                        
                      //  document.getElementById(id).style.display = "none";
                        
                       // document.getElementById(id).remove();
                        $("#"+id).css("display", "none");
                        calculate_popups();
                        
                        return;
                    } 
                }  */   
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
            var studentId='${parentId}';
            
            function register_popup(id, name,role,tutorProfileId)
            {
            	var chatSatus="N";
            	if($("#"+id).length!=0){
            		$("#"+id).css("display", "block"); 
            	} 
            	
            //	$("#justtest").val(id);
            	tutId=id;
                 for(var iii = 0; iii <= popups.length; iii++)
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
                
                	
               // 	$("#chatTutor"+id).removeAttr('href');
                //	$("#chatTutor"+id).css("background-color", "#B2B3B2");
                	  
                	if($("#"+id).length==0){
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                if(tutorProfileId!='NA')
                	{
                element = element + '<div class="chat-seeMore"><a onclick="openSeeMore('+tutorProfileId+');"><spring:message code="student.seemore"/></a></div>';
                	}
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                /* if(tutorProfileId!='NA')
            	{
                element = element + '<div class="chat-bookSession"><a onclick="openBookingPopUp('+id+');" class="greenBtn-small"> <spring:message code="book.session"/>    </a></div>';
            	} */
                element = element + '<div style="clear: both"></div></div><div class="popup-messages">';
                
                element = element + ' <div class="db-chats-txt">'+ 
				'<div class="chat-inner" id="chatin'+studentId+'-'+id+'"><div style="position:relative;">'+ 
	               ' </div> <div style="position:relative;"></div></div><div class="chat-msg">'+ 
	               ' <div class="chat-msg-txt"><input type="text" class="trying" id="chatMsg'+studentId+'-'+id+'"  placeholder="Type Something " >'+ 
	               ' <a href="javascript:;" class="chat-msg-submit" onclick="sendMsg('+studentId+'-'+id+');" ><spring:message code="parent.submit" /></a> </div>'+ 
	                '<div class="clr"></div> </div></div></div></div>';
                
                chatSatus="Y";
                	}
                	
                	document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element; 
                	popups.unshift(id);
                
                	if($("#"+id).length!=0){
                		calculate_popups();
                	} 
                	
                	openchatwind(id,chatSatus,role);
            }
            
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
              /*   if(width < 540)
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
       //     window.addEventListener("resize", calculate_popups);
      //      window.addEventListener("load", calculate_popups);
            
        </script>

<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

<script type="text/javascript">

 function sendMsg(fixit){ 
	 $( ".trying" ).keypress();
    var text = $('#chatMsg'+fixit).val();
  // // console.log("Text:", text);
    if(text!=null){
  	  window['newUserRef' + tutorId ].push({name: "tutor", text: text});
    $('#chatMsg'+fixit).val('');
    var role =	$("#receipentRoleName").val();
    var name = '${userFname}';
    var targetId = fixit;
    var parentUserId = targetId.substring(0, targetId.indexOf('-'));
    var tutorUserId = targetId.substring(targetId.indexOf('-')+1,targetId.length);
	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+parentUserId +':'+tutorUserId);
   // var text = $('#'+targetId).val();
   
    var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+targetId).val();
   
   window['newUserRef' + tutorId ].push({name: name, text: text});
 
    $('#'+targetId).val('');  
    
    var url='<%=request.getContextPath()%>/parent/requestTutorChatByParent';
	$.ajax({
		url:url,
		method:'GET',
		async : true,
		data:{tutorId:tutorId,role:role},
		success:function(response){
		}
	    });
    
    }
    
  } 


var studentId1="0";
var tutorId = "0";
var stId='${parentId}';
//var posfix = stId+"-";
var newUserRef ="";
function openchatwind(tutorId,chatSatus,role){
	$("#receipentRoleName").val(role);
	studentId1='${parentId}';
	$("#justtest").val(tutorId);
	var url='<%=request.getContextPath()%>/parent/requestTutorChatByParent';
	$.ajax({
		url:url,
		method:'GET',
		async : true,
		data:{tutorId:tutorId,role:role},
		success:function(response){
			
		},
		complete:function(){
		
		var posfix =studentId1+"-"+tutorId;
		
		
		
		if(chatSatus=='Y'){
			
			
			window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		 
		 window['newUserRef' + tutorId ].on('child_added', function(DataSnapshot) {
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      var stuId = '${parentId}';
	      myDataRef.authWithPassword({
	    		 email    : '${firebaseUser}',
	    		 password : '${firebasePass}'
	    		}, function(error, authData) {
	    		  if (error) {
	    			  
	    		  } else {
	    			  
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
		         var role =	$("#receipentRoleName").val();
		          var name = '${userFname}';
		          var targetId = $(e.target).attr("id");
		          var parentUserId = targetId.substring(7, targetId.indexOf('-'));
		          var tutorUserId = targetId.substring(targetId.indexOf('-')+1,targetId.length);
		          
		    	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+parentUserId +':'+tutorUserId);
		        //  var text = $('#'+targetId).val();
		         
		            var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+targetId).val();
		        
		        window['newUserRef' + tutorId ].push({name: name, text: text});
		       
		          $('#'+targetId).val('');  
		          
		          var url='<%=request.getContextPath()%>/parent/requestTutorChatByParent';
		      	$.ajax({
		      		url:url,
		      		method:'GET',
		      		async : true,
		      		data:{tutorId:tutorId,role:role},
		      		success:function(response){
		      		}
		      	    });
		        
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
	   
		if(chatSatus=='N'){
		 window['newUserRef' + tutorId ].on('child_added', function(DataSnapshot) {
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      var stuId = '${parentId}';
	      myDataRef.authWithPassword({
	    		 email    : '${firebaseUser}',
	    		 password : '${firebasePass}'
	    		}, function(error, authData) {
	    		  if (error) {
	    		  } else {
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
		        	var role =	$("#receipentRoleName").val();
		          var name = '${userFname}';
		          var targetId = $(e.target).attr("id");
		          var parentUserId = targetId.substring(7, targetId.indexOf('-'));
		          var tutorUserId = targetId.substring(targetId.indexOf('-')+1,targetId.length);
		    	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+parentUserId +':'+tutorUserId);
		       //   var text = $('#'+targetId).val();
		         
		       
		           var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+targetId).val();
		       
		       window['newUserRef' + tutorId ].push({name: name, text: text});
		       
		          $('#'+targetId).val('');  
		          
		          var url='<%=request.getContextPath()%>/parent/requestTutorChatByParent';
		      	$.ajax({
		      		url:url,
		      		method:'GET',
		      		async : true,
		      		data:{tutorId:tutorId,role:role},
		      		success:function(response){
		      		}
		      	    });
		          
		          
		        
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
	   
		
	}  ////complete ends
		
		
	
	}); // ajax ends
} --%>

</script> 
  
  
  
  
  
  
  
  
  
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body onload="getIp();" >




        
   <!--   ========================================== sidebar chat =========================== -->
    
   
    <div class="chat-sidebar" id="chatBar" style="display:none;">
   <div class="homeChat-hdr"><spring:message code="tutors.chat"/>   <a onclick="closeChatBar();" style="float: right;margin-right: 5px; color: white;"><img  style="width:18px; position:relative; top:1px;"  alt="" src="<%=request.getContextPath()%>/images/arrow_down.png"/></a></div>
   <div id="testbar">
    <img  style="margin-top: 100%;height: 100px;width: 100px;margin-left: 46px;"  alt="" src="<%=request.getContextPath()%>/images/timerLoader.gif"/>
    </div>
   
    
   <%--  <c:forEach var="subjectList" items="${subjectMasterName}">
    
    <h3> ${subjectList.subject_Type}</h3>
    
    <c:set var="tutorcount" value="0" scope="page" />
       <c:forEach var="tutorDetails" items="${tutorDetails}">
       
       <c:set var="subjectName" value="${subjectList.subject_Type}"/>
       <c:if test="${tutorcount le 11}">
			<c:if test="${fn:contains(tutorDetails.subjectTypeMasterName, subjectName)}">
            <c:set var="tutorcount" value="${tutorcount + 1}" scope="page"/>
            
             
             <c:if test="${tutorcount ge 4}">
        <div class="sidebar-name subject${subjectList.subject_Type_Master_Id}" style="display: none;">
             </c:if>
             <c:if test="${tutorcount le 3}">
          <div class="sidebar-name ">
             </c:if>
             
             
                <!-- Pass username and display name to register popup -->
                <a href="javascript:register_popup('${tutorDetails.userId}', '${tutorDetails.name}','tutor', '${tutorDetails.tutorProfileId}');">
                  
                  <c:if test="${tutorDetails.login_status eq 'Y'}">
                  <img  class="status${tutorDetails.userId} defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                  </c:if>
                  
                  <c:if test="${tutorDetails.login_status eq 'N'}">
                   <img  class="status${tutorDetails.userId} defaultStatus"  style="float:left; margin-top:7px;width:9px; height:9px;margin-left: -1px;margin-right: 3px;" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                  </c:if>
                  
                    <c:if test="${tutorDetails.imageName !=null}">
	              	<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/profilePictures/${tutorDetails.userId}/fileupload/${tutorDetails.userId}${tutorDetails.imageName}"/>
	              	</c:if>
	              	<c:if test="${tutorDetails.imageName ==null && tutorDetails.imgUrl ==null}">
	              	<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
	              	</c:if>
	              	<c:if test="${tutorDetails.imgUrl !=null && tutorDetails.imageName ==null}">
	              	<img width="30" height="30" alt="" src="${tutorDetails.imgUrl}"/>
	              	</c:if>
                    
                    <span>${tutorDetails.name}
                    <span>${tutorDetails.college}</span>
                    <span class="stars">
			        <c:forEach var="i" begin="1" end="5">
                        <c:choose>
                        <c:when test="${i le tutorDetails.tutorRating}">
                        <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                        </c:when>
                        <c:otherwise>
                        <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">
                        </c:otherwise>
                        </c:choose>
                    </c:forEach>
		    	    </span>
                    </span>
                    <!-- //======== code for likes -->
				    <!-- <font class="likesOutr">
				    	<i class="thumbsup-tiny-icon"></i>
				       127
				    </font> -->
                   <!-- //======== code for likes --> 
                </a>
            </div>
            	   
			</c:if>
			</c:if>
       </c:forEach>
       <c:if test="${tutorcount ge 4}">
        <div class="clr">
    <a onclick="showMoreRecord('${subjectList.subject_Type_Master_Id}')" id="show${subjectList.subject_Type_Master_Id}" value="showMore" class="chat-showMore">Show More</a>
    </div>
    </c:if>
       </c:forEach> --%>
        </div> 
 <!--   =========================== side bar chat end=============================================  -->       




<div id="main">
<%@ include file="/WEB-INF/views/parent/parent-inner-header.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
            <div class="stu_outr">
            <div class="stu_lt">
            <h1 class="text-left"><spring:message code="looking.tutor"></spring:message></h1>
            <div class="search-full">
                <div class="search-con">
                  <input type="text" placeholder='<spring:message code="search.texttutor"></spring:message>' name="searchTutor" id="searchTutor" onchange="getTutorByCriteria();"> 
                    <a href="javascript:;"><spring:message code="home.search"></spring:message></a>
                </div>
            </div>
            <div class="stu-index-txt"  id="tutorDetails">
             <div style="width: 100%;  max-height: 660px; overflow: auto;">
                	<ul>
                	
                <%-- 	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${listDtoTutorRegistrations}" var="listDtoTutorRegistrations">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon">
                            	
                            	 <c:if test="${listDtoTutorRegistrations.login_status eq 'Y'}">
                        	<img class="status${listDtoTutorRegistrations.userId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${listDtoTutorRegistrations.login_status eq 'N'}">
                        	<img class="status${listDtoTutorRegistrations.userId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                            	
                            	</div>
                                <div class="tutor-img" id="immg${listDtoTutorRegistrations.userId}">
                                <c:if test="${listDtoTutorRegistrations.imageName !=null }">
					              	<img alt="" src="<%=request.getContextPath()%>/${listDtoTutorRegistrations.userId}/fileupload/${listDtoTutorRegistrations.userId}${listDtoTutorRegistrations.imageName}"/>
					              	</c:if>
					              	<c:if test="${listDtoTutorRegistrations.imageName ==null && listDtoTutorRegistrations.imgUrl==null}">
					              	<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
					              	</c:if>
					              	<c:if test="${listDtoTutorRegistrations.imgUrl !=null && listDtoTutorRegistrations.imageName ==null}">
					              	<img alt="" src="${listDtoTutorRegistrations.imgUrl}"/>
					              	</c:if>
                                </div>
                                
                                
                                <div class="tutor-img-txt">
                                	${listDtoTutorRegistrations.name}
                                    <div class="subjects ellipsis" title="${listDtoTutorRegistrations.subjectNames}">${listDtoTutorRegistrations.subjectNames}</div>
                                    <div class="stars">
                                    <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                    <c:when test="${i le listDtoTutorRegistrations.tutorRating}">
                                    <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                    </c:when>
                                    <c:otherwise>
                                    <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">
                                    </c:otherwise>
                                    </c:choose>
                                    </c:forEach>
                                    </div>
                                </div>
                                
                                
                                
                                <div class="book-session">
                                	<a href="javascript:;" name="bookTutor" id="bookTutor" onclick="openSuggestPopUp('${listDtoTutorRegistrations.userId}');" class="greenBtn-normal"><spring:message code="student.suggestTutor"></spring:message> </a>
                                
                                </div>
                            </div>
                            <div class="info-outr">
                                <div class="ellipsis" title="${listDtoTutorRegistrations.about}">${listDtoTutorRegistrations.about}</div>
                                <p class="ellipsis" title="${listDtoTutorRegistrations.college}">${listDtoTutorRegistrations.college}</p>
                               <p >${listDtoTutorRegistrations.career}</p>
                                <div><a onclick="openSeeMore('${listDtoTutorRegistrations.tutorProfileId}');"><spring:message code="student.seemore"/></a></div> 
                            </div>
                        </li>
                        <c:set var="count" value="1" scope="page"/>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;"><spring:message code="norecord.found"/> </h3>
                       </c:if> --%>
                        
                    </ul>
                    </div>
                </div>
            </div>
             <div class="stu_lt stu_rt">
              <h1 class="text-left"><spring:message code="parent.mysuggestedTutor"/></h1> 
              <div class="stu_rt-txt">
              	<div class="stu-index-txt"  id="bookedTutorDetails">
              	 <div style="width: 100%;  max-height: 660px; overflow: auto;">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
 				    <c:forEach items="${listSuggestedTutorDetails}" var="listDtoTutorRegistrations">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon">
                            	
                            	 <c:if test="${listDtoTutorRegistrations.login_status eq 'Y'}">
                        	<img class="status${listDtoTutorRegistrations.userId} defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${listDtoTutorRegistrations.login_status eq 'N'}">
                        	<img class="status${listDtoTutorRegistrations.userId} defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                            	
                            	</div>
                                <div class="tutor-img" id="immg${listDtoTutorRegistrations.userId}">
                                <c:if test="${listDtoTutorRegistrations.imageName !=null}">
					              	<img alt="" src="<%=request.getContextPath()%>/profilePictures/${listDtoTutorRegistrations.userId}/fileupload/${listDtoTutorRegistrations.userId}${listDtoTutorRegistrations.imageName}"/>
					              	</c:if>
					              	<c:if test="${listDtoTutorRegistrations.imageName ==null && listDtoTutorRegistrations.imgUrl ==null}">
					              	<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
					              	</c:if>
					              	<c:if test="${listDtoTutorRegistrations.imgUrl !=null && listDtoTutorRegistrations.imageName ==null}">
					              	<img alt="" src="${listDtoTutorRegistrations.imgUrl}"/>
					              	</c:if>
                                </div>
                                
                                
                                <div class="tutor-img-txt">
                                	${listDtoTutorRegistrations.name}
                                    <div class="subjects ellipsis" title="${listDtoTutorRegistrations.subjectNames}">${listDtoTutorRegistrations.subjectNames}</div>
                                     <div class="stars">
                                    <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                    <c:when test="${i le listDtoTutorRegistrations.tutorRating}">
                                    <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                    </c:when>
                                    <c:otherwise>
                                    <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">
                                    </c:otherwise>
                                    </c:choose>
                                    </c:forEach>
                                    </div>
                                </div>
                               
                            </div>
                            <div class="info-outr">
                                <div><spring:message code="suggestedto"/>:</div>
                                <p>${listDtoTutorRegistrations.studentFullname}</p>
                            </div>
                            
                              <div class="book-session chat-Btns">
                                 <a href="javascript:register_popup('${listDtoTutorRegistrations.userId}', '${listDtoTutorRegistrations.name}','tutor', '${listDtoTutorRegistrations.tutorProfileId}','${listDtoTutorRegistrations.chatSessionId}');"  id="chatTutor'+${listDtoTutorRegistrations.userId}+'"  class="greenBtn-normal">
			                    <img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a>
			                    
			                    <a  class="orangeBtn-normal" onclick="openMessagePopUp('${listDtoTutorRegistrations.userId}', '${listDtoTutorRegistrations.name}');">
			                    <img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>
			                                
                                	<a href="javascript:;" name="bookTutor" id="bookTutor" onclick="deleteSuggestedTutor('${listDtoTutorRegistrations.suggested_tutor_Id}');" 
                                	class="greyBtn-normal"><spring:message code="remove.suggestedTutor"></spring:message> </a>
                                </div>
                                
                                
                        </li>
                        <c:set var="count" value="1" scope="page"/>
                        </c:forEach>                       
                       <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62; padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                    </ul>
                    </div>
                </div>
                
           
                
                
                
                
                
                
                
                
              </div>
              
                   
                  <div class="stu_rt">
              <h1 class="text-left"><spring:message code="activeChats"/></h1> 
              <div class="stu_rt-txt">
               
                <div class="stu-index-txt parent-active-chat"  id="chatTutorDetails">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoTutorDetails}" var="dtoTutorDetails">
                	 
                	 <c:if test="${dtoTutorDetails.tutorUserId ne 0}">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon">
                            	
                            	<c:if test="${dtoTutorDetails.loginStatus eq 'Y'}">
                        	<img class="status${dtoTutorDetails.tutorUserId} defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${dtoTutorDetails.loginStatus eq 'N'}">
                        	<img  class="status${dtoTutorDetails.tutorUserId} defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                            	
                            	</div>
                                <div class="tutor-img-txt">
                                ${dtoTutorDetails.fullName} 
                               
                                </div>
                                <div class="book-session" style="float:right">
                                
                                <c:if test="${dtoTutorDetails.readByParent eq 'Y'}">
                                	<a href="javascript:register_popup('${dtoTutorDetails.tutorUserId}', '${dtoTutorDetails.fullName}','tutor', '${dtoTutorDetails.tutorProfileId}', '${dtoTutorDetails.chatSessionId}');" name="chatTutor${dtoTutorDetails.tutorUserId}" id="chatTutor${dtoTutorDetails.tutorUserId}"  class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                               <c:if test="${dtoTutorDetails.readByParent eq 'N'}">
                                	<a href="javascript:register_popup('${dtoTutorDetails.tutorUserId}', '${dtoTutorDetails.fullName}','tutor', '${dtoTutorDetails.tutorProfileId}', '${dtoTutorDetails.chatSessionId}');" name="chatTutor${dtoTutorDetails.tutorUserId}" id="chatTutor${dtoTutorDetails.tutorUserId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                                </div>
                            </div>
                        </li>
                        <c:set var="count" value="1" scope="page"/>
                        </c:if>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                    </ul>
                </div>
                </div>
                </div>
                
             </div> 
             
             
             
             
             
               <!--   ============= chat support section ============================== -->
             
           	  <%--  <div class="stu_rt">
              <h1 class="text-left"><spring:message code="activeChats"/></h1> 
              <div class="stu_rt-txt">
              <div class="stu-index-txt"  id="chatTutorDetails">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoTutorDetails}" var="dtoTutorDetails">
                	 
                	 <c:if test="${dtoTutorDetails.tutorUserId ne 0}">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" style="float: left; margin: 4px 5px 0 5px;" >
                            	
                            	<c:if test="${dtoTutorDetails.loginStatus eq 'Y'}">
                        	<img class="status${dtoTutorDetails.tutorUserId} defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${dtoTutorDetails.loginStatus eq 'N'}">
                        	<img  class="status${dtoTutorDetails.tutorUserId} defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                            	
                            	</div>
                                <div class="tutor-img-txt">
                                ${dtoTutorDetails.fullName} 
                               
                                </div>
                                <div class="book-session" style="float:right">
                                
                                <c:if test="${dtoTutorDetails.readByParent eq 'Y'}">
                                	<a href="javascript:register_popup('${dtoTutorDetails.tutorUserId}', '${dtoTutorDetails.fullName}','tutor', '${dtoTutorDetails.tutorProfileId}', '${dtoTutorDetails.chatSessionId}');" name="chatTutor${dtoTutorDetails.tutorUserId}" id="chatTutor${dtoTutorDetails.tutorUserId}"  class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                               <c:if test="${dtoTutorDetails.readByParent eq 'N'}">
                                	<a href="javascript:register_popup('${dtoTutorDetails.tutorUserId}', '${dtoTutorDetails.fullName}','tutor', '${dtoTutorDetails.tutorProfileId}', '${dtoTutorDetails.chatSessionId}');" name="chatTutor${dtoTutorDetails.tutorUserId}" id="chatTutor${dtoTutorDetails.tutorUserId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                                </div>
                            </div>
                        </li>
                        <c:set var="count" value="1" scope="page"/>
                        </c:if>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                    </ul>
                </div> 
              </div>
             </div>  
              --%>
             <!--   ============= chat support section ============================== -->        
             
            </div>
        </div>
      </div>
    </div>
  </section>
  <!--//Mid Section--> 
<form action="<%=request.getContextPath()%>/student/studentClassroom" method="POST" id="meetingForm">
<input type="hidden" id="meetingId" name="meetingId" readonly="readonly">
</form>
</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>
<div id="editpopup" style="display:none;">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/student/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.sessionnotstarted"/>
            </section>
        </div>
        </div>
        </div>
        </div>
        
        
<!-- Booking Pop Up -->
<div id="bookingpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeBookingPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <form id="suggestionForm" name="suggestionForm"  method="POST">
            <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                   <tr>
                   <th><spring:message code="select.child"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <select id="childName" name="childName" >
                		<%-- <c:forEach var="listLevelMaster" items="${listLevelMaster}">                   	
		             <option value='${listLevelMaster.level_Id}' >${listLevelMaster.level}</option>             
                    	 	</c:forEach> --%>
                    </select>
                    </div>
                    </td>
                  </tr>
                  <input type="hidden" readonly="readonly" name="tutorUserId" id="tutorUserId"/>
                     
                    <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="parent.submit"></spring:message>' onclick="submitSuggestionForm();" ></td>
                  </tr>
                  
            </tbody></table>
                </div>
            </form>
        </div>
   </div>
</div> 
<!-- End Booking Pop Up -->        
     
     
 <div class="form-tutor-popup" id="deleteSuccess" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/parent/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt">
            	<spring:message code="suggested.removed.success"/>
            </div>
               
             
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/parent/home" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>    

<c:if test="${addsuccess eq 'no'}">
 <div class="form-tutor-popup" id="suggestedAlreadyAdded" >
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/parent/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt">
            	<spring:message code="suggested.already.exist"/>
            </div>
               
             
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/parent/home" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div> 

</c:if>        

   <div class="form-tutor-popup" id="seeMorePopUp" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr seeMoreBox">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeseeMorePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>      
        
             <div class="my-info-tutor-txt seemoretutorDetails" id="seetutorDetails"> 
              

            </div>
        
        </div>
        </div>
        </div> 

<div class="homeChat-hdr" id="openChatBar" style="display:none;"><spring:message code="tutors.chat"/>  <a onclick="openChatBar();" style="float: right;margin-right: 5px; color: white;"><img  style="width:18px; position:relative; top:1px;"  alt="" src="<%=request.getContextPath()%>/images/arrow_up.png"/></a></div>        
        
        
         
        <%-- Message PopUp Start --%>
        
         <div id="sendMessagepopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeSendMessagePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="sendMessageForm" id="sendMessageForm" method="post">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                  
                    <tr>
                   <th><spring:message code="to"/></th>
                    <td align="right">:</td> 
                    <td><div id="toName"></div>           
                    </td>
                  </tr>
                  
             		<tr>
                   <th><spring:message code="from"/></th>
                    <td>:</td>  
                    <td><div id="fromName"></div></td>
                  </tr>
                  
                   <tr class="msg-row">
                   <th class="v-alignTop"><spring:message code="message"/></th>
                    <td class="v-alignTop">:</td>  
                    <td class="v-alignTop"><div ><textarea  name="message" id="message" rows="4" cols="20" maxlength="250"></textarea></div></td>
                  </tr>
                  
                  
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="send.message"></spring:message>' onclick="submitSendMessageForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                <input type="hidden" id="tutorMessageUserId" name="tutorMessageUserId">
                </form:form>
        	
        </div>
   </div>

</div>


   <div id="sendMessageSuccess" class="form-tutor-popup" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeSendMessageSuccessPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code='messagesent.success'/>
            </section>
            <%-- <p><spring:message code="wehavesentyourrequesttoAdminforapproval" /></p> --%>
        </div>
        </div>
        </div>


        
        <%-- Message Popup End --%>
        
  <input type="hidden" id="receipentRoleName" name="receipentRoleName"/>      
        
</body>

<script type="text/javascript">

$(document).ready(function(){
	 $('#sendMessageForm').validate({
		rules:{
          message: {
              required:true
          }
			},
		 messages:{			
			 toId:{
				required:"<spring:message code='student.select'></spring:message>"
				}
		}
	});
	 
	 //===sidebar without delay=======
			var receiverRole="tutor";
	 		var chatSessionId="0";
	 
		var url='<%=request.getContextPath()%>/getAllChatBarDetailsWithCriteria';
		var ab="";
		$.ajax({
			url:url,
			method:'GET',
			async :true,
			success:function(response){

				$.each(response.modelMap.subjectMasterName, function(index,value) {

			 ab+='<h3>'+ value.subject_Type+ '</h3>';
			 var subjectName = value.subject_Type; 
			 var tutorcount ="0";
			 $.each( response.modelMap.tutorDetails, function(tutorindex,tutorvalue) {
			 var tutorsubjectList= tutorvalue.subjectTypeMasterName;

			   if(tutorcount<="11"){
				  var subjectFound = tutorsubjectList.search(subjectName);
				  if(subjectFound>="0"){
				  tutorcount++;
				  if(tutorcount>="4"){
					ab+='<div class="sidebar-name subject'+value.subject_Type_Master_Id+'" style="display: none;">';
			    //    ab +=   '<a href="javascript:register_popup('+tutorvalue.userId+', '+tutorvalue.name+');">';  
			     ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\',\''+receiverRole+'\', \''+tutorvalue.tutorProfileId+'\',\''+chatSessionId+'\');">';
			     
			      if(tutorvalue.login_status=='Y'){
			        ab += '<img  class="status'+tutorvalue.userId+' defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
			                  }				  
			      if(tutorvalue.login_status=='N'){
			        ab += '<img  class="status'+tutorvalue.userId+' defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
			                  }
			      if(tutorvalue.imageName!=null){
			        ab += '<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/profilePictures/'+tutorvalue.userId+'/fileupload/'+tutorvalue.userId+tutorvalue.imageName+'"/>';
							  }
				  if(tutorvalue.imageName==null && tutorvalue.imgUrl==null){
					ab+=   '<img width="30" height="30"  alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
												                            }
		 		  if(tutorvalue.imageName==null && tutorvalue.imgUrl!=null){
					ab+=   '<img width="30" height="30"  alt="" src="'+tutorvalue.imgUrl+'"/>';
												                            }
								
					ab+=  '<span>'+tutorvalue.name+'<span>'+tutorvalue.college+'</span><span class="stars">';
				  for(var i=1;i<=5;i++){
				  if(i <= tutorvalue.tutorRating){
					ab+=  '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
							  }
				  else{
					ab+=  '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
							  }
							  }
					ab+=  '</span></span></a></div>';
			                   }	
				  else{
					ab+='<div class="sidebar-name ">';
				//	ab +='<a href="javascript:register_popup('+tutorvalue.userId+', '+tutorvalue.name+');">';  
					 ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\',\''+receiverRole+'\', \''+tutorvalue.tutorProfileId+'\',\''+chatSessionId+'\');">';
		 
		 		 if(tutorvalue.login_status=='Y'){
					ab += '<img  class="status'+tutorvalue.userId+' defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
		                                         }				  
		         if(tutorvalue.login_status=='N'){
					ab += '<img  class="status'+tutorvalue.userId+' defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
		 										}
		  		 if(tutorvalue.imageName!=null){
		   			ab+=  '<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/profilePictures/'+tutorvalue.userId+'/fileupload/'+tutorvalue.userId+tutorvalue.imageName+'"/>';
			                                   }
			     if(tutorvalue.imageName==null && tutorvalue.imgUrl==null){
					ab+=   '<img width="30" height="30"  alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
							                            }
				 if(tutorvalue.imageName==null && tutorvalue.imgUrl!=null){
			        ab+=   '<img width="30" height="30"  alt="" src="'+tutorvalue.imgUrl+'"/>';
							                            }
				    ab+=  '<span>'+tutorvalue.name+'<span>'+tutorvalue.college+'</span><span class="stars">';
		         for(var i=1;i<=5;i++){
		         if(i <= tutorvalue.tutorRating){
				    ab+=  '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
		 										}
		 		 else{
		            ab+=  '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
												 }
												 }
		 			ab+=  '</span></span></a></div>';
												}
												  } // if ends
												  } // 12 records if ends
			  									 }); // inner loop
			   if(tutorcount>="4"){
				   ab+=  '<div class="clr">';
				   ab+= '<a onclick="showMoreRecord('+value.subject_Type_Master_Id+')" id="show'+value.subject_Type_Master_Id+'" value="showMore" class="chat-showMore">';
				   ab+= '<spring:message code="show.more"></spring:message> </a></div>';
				   }
					 }); // outer loop
					 $("#testbar").html(ab);
						}
						}); // ajax ends

});


function openMessagePopUp(tutorId,tutorName){
	
	var studentName='${user}';
	
	$("#tutorMessageUserId").val(tutorId);
	$("#toName").text(tutorName);
	$("#fromName").text(studentName);
	$(window).scrollTop(0)
	$("#sendMessagepopup").show();
}


function closeSendMessagePopUp(){
	 $("#message").val("");
	 $("label.error").hide();
	 
	$("#sendMessagepopup").hide();
}

function closeSendMessageSuccessPopUp(){
	$("#sendMessageSuccess").hide();
}


function submitSendMessageForm(){
	if ($('#sendMessageForm').valid()) {
        var tutorMessageUserId =  $("#tutorMessageUserId").val();
        var messageText = $("#message").val();
        
    	var url='<%=request.getContextPath()%>/parent/sendMessageHome';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async : true,
    		data:{tutorMessageUserId:tutorMessageUserId,messageText:messageText},
    		success:function(response){
    			if(response=="Y"){
    			$(window).scrollTop(0);
    			$("#sendMessagepopup").hide();
    			$("#sendMessageSuccess").show();
    			}
    			
    		},
    	}); 
    }
	  
	 
}



function closeChatBar(){
	$("#chatBar").hide();
	$("#openChatBar").show();
	 $("body").css("padding-right", "0px");
}

function openChatBar(){
	$("#chatBar").show();
	$("#openChatBar").hide();
	 $("body").css("padding-right", "198px");
}


$(document).ready(function(){
	$("#openChatBar").show();
});
</script>

<script type="text/javascript">

function openSuggestPopUp(tutorId)
{
	$(window).scrollTop(0);
	var tutorId=tutorId;
	
	$("#tutorUserId").val(tutorId);

	var url='<%=request.getContextPath()%>/parent/getParentChildsList';
	$.ajax({
		url:url,
		method:'GET',
		async : true,
		success:function(response){
			 $('#childName').children().remove();
			$('#childName').append('<option value="0"><spring:message code="selectany.value"/></option>');
			 $.each( response.modelMap.childDetails, function( key , value ) {
			$('#childName').append('<option value="'+value.userId+'">'+value.fullName+'</option>');
			 
			 });
			
			$("#bookingpopup").show();
		},
	});  
	
	
	
	

}


function deleteSuggestedTutor(suggestionId)
{
	var url='<%=request.getContextPath()%>/parent/deleteSuggestedTutor';
	$.ajax({
		url:url,
		method:'GET',
		async : true,
		data:{suggestionId:suggestionId},
		success:function(response){
			$(window).scrollTop(0);
			$("#deleteSuccess").show(); 
		}
	}); 
} 

function submitSuggestionForm(){

	$("#suggestionForm").attr("action", "<%=request.getContextPath()%>/parent/setSuggestedTutor");
	
	$("#suggestionForm").submit();
}


 function closeBookingPopUp(){
	$("label.error").hide();
	$("#bookingpopup").hide();
}
 
 
 
 function openSeeMore(tutorProfileId){
	 $(window).scrollTop(0);
		var url='<%=request.getContextPath()%>/parent/getTutorProfileDetails';
		$.ajax({
			url:url,
			method:'GET',
			async : true,
			data:{tutorProfileId:tutorProfileId},
			success:function(response){
				var tutorDetails = '<table width="70%" border="0" cellspacing="0" cellpadding="0"><tr><th width="130"><spring:message code="parent.name" /></th>';
	            tutorDetails += '<td>:</td><td>'+ response.modelMap.dtoTutorRegistrationDetail.name +'</td></tr>';
				tutorDetails += '<tr><th><spring:message code="country" /></th>'+
                             '<td>:</td><td>'+ response.modelMap.dtoTutorRegistrationDetail.countryName +'</td></tr>';
				tutorDetails += '<tr><th><spring:message code="parent.time" /></th>'+
                             '<td>:</td><td>'+ response.modelMap.dtoTutorRegistrationDetail.timezoneName +'</td></tr>';
				tutorDetails += '<tr><th><spring:message code="college" /></th>'+
	                            '<td>:</td><td>'+ response.modelMap.dtoTutorRegistrationDetail.college +'</td></tr>';
	            tutorDetails += '<tr><th><spring:message code="myExperience" /></th>'+
	                            '<td>:</td><td><div style="max-height:130px;overflow:auto">'+ response.modelMap.dtoTutorRegistrationDetail.about +'</div></td></tr>';
	            tutorDetails += '<tr><th><spring:message code="iLike" /></th>'+
	                            '<td>:</td><td><div style="max-height:130px;overflow:auto">'+ response.modelMap.dtoTutorRegistrationDetail.aboutMore +'</div></td></tr>';
	            tutorDetails += '<tr><th><spring:message code="subjectsITeach" /></th>'+
	                            '<td>:</td><td>'+ response.modelMap.dtoTutorRegistrationDetail.subjectNames +'</td></tr>'+	                            
	                            '</table>';
	            tutorDetails += '<div class="img">';
								if(response.modelMap.dtoTutorRegistrationDetail.imageName!=null){
								 tutorDetails+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+response.modelMap.dtoTutorRegistrationDetail.userId+'/fileupload/'+response.modelMap.dtoTutorRegistrationDetail.userId+response.modelMap.dtoTutorRegistrationDetail.imageName+'"/>';
										                }	

								if(response.modelMap.dtoTutorRegistrationDetail.imageName==null && response.modelMap.dtoTutorRegistrationDetail.imgUrl==null){
								tutorDetails+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
										                }

								if(response.modelMap.dtoTutorRegistrationDetail.imageName==null && response.modelMap.dtoTutorRegistrationDetail.imgUrl!=null){
								 tutorDetails+=   '<img alt="" src="'+response.modelMap.dtoTutorRegistrationDetail.imgUrl+'"/></div>';				
								}
								
			  /*  tutorDetails +=	'<div class="edit-icon" style="position: inherit;cursor:pointer;" title="Edit Photo" onclick="openPhotoPopup();"></div>'; */
			   
	             tutorDetails +=   '</div><div class="stars">';
				 var ratings = response.modelMap.dtoTutorRegistrationDetail.tutorRating;
                                 for(var i=1;i<=5;i++){
                                if(i<=ratings){
              tutorDetails +=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
									}
                                 else{
              tutorDetails +=    ' <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
                                 }
									}
              tutorDetails +=  '</div>';
								
								$("#seetutorDetails").html(tutorDetails);
								$("#seeMorePopUp").show();
			}
			
		});
		
	}

function closeseeMorePopUp(){
		$("#seeMorePopUp").hide();
	}
 
 
 
 
 
 
</script>


<script type="text/javascript">
$(document).ready(function(){
	var currentTime = '${currentTime}';
	
 	$('*[name=bookingDate]').appendDtpicker({
 		"closeOnSelected": true,
 		"minuteInterval": 15,
 		//"futureOnly": true,
 		//"showTimezone": true,
 		//"timezone": "+0200",
        'minTime': currentTime,
 		'minDate': new Date(),
 		
 		"dateFormat": "yyyy-MM-DD hh:mm"
 	});
	
	 
	 $.validator.addMethod('selectcheck', function (value) {
	        return (value != '0');
	    }, '<spring:message code="student.select"></spring:message>');
	
	$('#suggestionForm').validate({
		rules:{
			
			childName: {
                selectcheck: true,
                required:true
            }
			
			},

		
		 messages:{
			 
			 childName:{
					required:"<spring:message code='student.select'></spring:message>"
					}
		     
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});
</script>


<script type="text/javascript">
 function getTutorByCriteria(){
	
	var searchPattern = $("#searchTutor").val().trim();
    if(searchPattern!=""){
	var url='<%=request.getContextPath()%>/parent/getFilteredTutorList';
	$.ajax({
		url:url,
		method:'GET',
		async : true,
		data:{searchPattern:searchPattern},
		success:function(response){
			var status="0";
			if(response != null){
				var receiverRole="tutor";
				var alltutorsData="<div style='width: 100%; max-height:660px; overflow: auto;'><ul>";
				
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {

						status="1";
						alltutorsData+=   '<li><div class="stud-img-outr"><div class="indi-icon">';
						
						if(value.login_status=='Y'){
							alltutorsData+=	'<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
						}
						else{
							alltutorsData+= '<img class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
						}
						
						alltutorsData+= '</div><div class="tutor-img" id="immg'+value.userId+'">';
							                         
									                  if(value.imageName!=null){
									     alltutorsData+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.userId+'/fileupload/'+value.userId+value.imageName+'"/>';
									                            }	

									                  if(value.imageName==null && value.imgUrl==null){
									     alltutorsData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
									                            }

									                  if(value.imageName==null && value.imgUrl!=null){
									     alltutorsData+=   '<img alt="" src="'+value.imgUrl+'"/>';
									                            }							
									                                   
														              	
														             
									     alltutorsData+=   '</div><div class="tutor-img-txt">'+value.name+'<div class="subjects ellipsis" title="'+value.subjectNames+'">'+value.subjectNames+'</div><div class="stars">';
									     
									     
									     for(var i=1;i<=5;i++){
				                        	  if(i <= value.tutorRating){
				                        		  alltutorsData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
				                        	  }
				                        	  else
				                        		  {
				                        		  alltutorsData+=   '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
				                        		  }
				                          }
									     
									                          alltutorsData+=   '</div></div></div>';
									     alltutorsData+=   '<div class="info-outr"><div class="ellipsis" title="'+value.about+'">'+value.about+'</div><p class="ellipsis" title="'+value.college+'">'+value.college+'</p>'+
									                        '<p >'+value.career+'</p>'+
			                                '<div><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div>'+
			                                '</div><div class="book-session chat-Btns">'+
			                                
			                                
			                                '<span id="initiateChat'+value.userId+'"><a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\',\''+receiverRole+'\', \''+value.tutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"  class="greenBtn-normal">'+
			                                '<img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a></span>'+
			                                
			                                
			                                '<a  class="orangeBtn-normal" onclick="openMessagePopUp(\''+value.userId+'\', \''+value.name+'\');">'+
			                                '<img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>'+
											'<a href="javascript:;" name="bookTutor'+value.userId+'" id="bookTutor'+value.userId+'" onclick="openSuggestPopUp('+value.userId+');" class="blueBtn-normal"><spring:message code="student.suggestTutor"></spring:message></a>'+
											'</div></li>'; 
						
						 });
				 alltutorsData+= '</ul></div>';
				$("#tutorDetails").html(alltutorsData);
				}
			if(status=="0"){
				$("#tutorDetails").html('<spring:message code="norecord.found"/>');
			}
			
			//==== call another ajax method for favorite and chat sesison Id==========
			getFavoriteAndChatRecord(searchPattern);	
			
		}
		
	});  
    }
    if(searchPattern==""){
    	location.reload();
    }
	
}
	
	function showMoreRecord(id){
		
		var buttonAction = $("#show"+id).attr("value");
		
		if(buttonAction=='showMore'){
			$(".subject"+id).show();
			$("#show"+id).html("show less");
			$("#show"+id).attr("value","showLess"); 
		}
		if(buttonAction=='showLess'){
			$(".subject"+id).hide();
			$("#show"+id).html("show more");
			$("#show"+id).attr("value","showMore"); 
		}
	}	
	
 
    var url='<%=request.getContextPath()%>/getTutorsLoginStatus';
	var eventSource = new EventSource(url);
	eventSource.addEventListener('online', function(event) {
		var online='<%=request.getContextPath()%>/images/green-bullet.png';
		var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
		$(".defaultStatus").attr("src",offline);
		$.each(event.data.split(",").slice(0,-1), function(index, item) {
		$(".status"+item).attr("src",online); 
		});
    }, false);
 
 
eventSource.addEventListener('offline', function(event) {
    
    }, false);	
 
</script>

<script type="text/javascript">
function getFavoriteAndChatRecord(searchPattern){
	 var receiverRole="tutor";
	 var url='<%=request.getContextPath()%>/parent/getChatRecordIds';
		$.ajax({
			url:url,
			method:'GET',
			async : true,
			success:function(response){
				if(response!=null){
					if(response.modelMap.dtoTutorChatDetailList!=null){
					$.each( response.modelMap.dtoTutorChatDetailList, function(index,value) {
						
	$("#initiateChat"+value.chatSessionTutorUserId).html('<a href="javascript:register_popup(\''+value.chatSessionTutorUserId+'\', \''+value.tutorName+'\',\''+receiverRole+'\', \''+value.chatSessionTutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.chatSessionTutorUserId+'" id="chatTutor'+value.chatSessionTutorUserId+'"  class="greenBtn-normal">'+
                    '<img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a>');
	
					});
					}
					}
			}
			
		});  
		
		//==== call another ajax to fetch rest of the records==========
		getRemainingRecords(searchPattern);
} 


//==================== fetch rest of records ==============================
 function getRemainingRecords(searchPattern){
	var url='<%=request.getContextPath()%>/parent/getRemainingFilteredTutorList';
	$.ajax({
		url:url,
		method:'GET',
		async : true,
		data:{searchPattern:searchPattern},
		success:function(response){
			var status="0";
			if(response != null){
				var receiverRole="tutor";
				var alltutorsData="";
				
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {

						status="1";
						alltutorsData+=   '<li><div class="stud-img-outr"><div class="indi-icon">';
						
						if(value.login_status=='Y'){
							alltutorsData+=	'<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
						}
						else{
							alltutorsData+= '<img class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
						}
						
						alltutorsData+= '</div><div class="tutor-img" id="immg'+value.userId+'">';
							                         
									                  if(value.imageName!=null){
									     alltutorsData+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.userId+'/fileupload/'+value.userId+value.imageName+'"/>';
									                            }	

									                  if(value.imageName==null && value.imgUrl==null){
									     alltutorsData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
									                            }

									                  if(value.imageName==null && value.imgUrl!=null){
									     alltutorsData+=   '<img alt="" src="'+value.imgUrl+'"/>';
									                            }							
									                                   
														              	
														             
									     alltutorsData+=   '</div><div class="tutor-img-txt">'+value.name+'<div class="subjects ellipsis" title="'+value.subjectNames+'">'+value.subjectNames+'</div><div class="stars">';
									     
									     
									     for(var i=1;i<=5;i++){
				                        	  if(i <= value.tutorRating){
				                        		  alltutorsData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
				                        	  }
				                        	  else
				                        		  {
				                        		  alltutorsData+=   '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
				                        		  }
				                          }
									     
									                          alltutorsData+=   '</div></div></div>';
									     alltutorsData+=   '<div class="info-outr"><div class="ellipsis" title="'+value.about+'">'+value.about+'</div><p class="ellipsis" title="'+value.college+'">'+value.college+'</p>'+
									                        '<p >'+value.career+'</p>'+
			                                '<div><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div>'+
			                                '</div><div class="book-session chat-Btns">'+
			                                
			                                
			                                '<span id="initiateChat'+value.userId+'"><a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\',\''+receiverRole+'\', \''+value.tutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"  class="greenBtn-normal">'+
			                                '<img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a></span>'+
			                                
			                                
			                                '<a  class="orangeBtn-normal" onclick="openMessagePopUp(\''+value.userId+'\', \''+value.name+'\');">'+
			                                '<img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>'+
											'<a href="javascript:;" name="bookTutor'+value.userId+'" id="bookTutor'+value.userId+'" onclick="openSuggestPopUp('+value.userId+');" class="blueBtn-normal"><spring:message code="student.suggestTutor"></spring:message></a>'+
											'</div></li>'; 
						
						 });
				$("#tutorDetails ul").append(alltutorsData);
				}
			if(status=="0"){
			}
			
			//==== call another ajax method for favorite and chat sesison Id==========
			getRemainingFavoriteAndChatRecord();	
			
		}
		
	});  
	
}

 function getRemainingFavoriteAndChatRecord(){
	 var receiverRole="tutor";
	 var url='<%=request.getContextPath()%>/parent/getChatRecordIds';
		$.ajax({
			url:url,
			method:'GET',
			async : true,
			success:function(response){
				if(response!=null){
					if(response.modelMap.dtoTutorChatDetailList!=null){
					$.each( response.modelMap.dtoTutorChatDetailList, function(index,value) {
						
	$("#initiateChat"+value.chatSessionTutorUserId).html('<a href="javascript:register_popup(\''+value.chatSessionTutorUserId+'\', \''+value.tutorName+'\',\''+receiverRole+'\', \''+value.chatSessionTutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.chatSessionTutorUserId+'" id="chatTutor'+value.chatSessionTutorUserId+'"  class="greenBtn-normal">'+
                    '<img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a>');
	
					});
					}
					}
			}
			
		});  
}




</script>

<script type="application/javascript" src="http://jsonip.appspot.com/?callback=getip"></script>
<script type="text/javascript">
    function getIp() {
        var script = document.createElement("script");
       
        script.type = "text/javascript";
        script.src = "http://www.telize.com/jsonip?callback=DisplayIP";
        document.getElementsByTagName("head")[0].appendChild(script);
    }
    function DisplayIP(response) {
    	var userIP=response.ip;
    	var url='<%=request.getContextPath()%>/parent/saveUserIP';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async : true,
    		data:{userIP:userIP},
    		success:function(response){
    			    			
    		},
    	});
    	
    } 
</script>

 <script type="text/javascript">
$(document).ready(function(){
	 // sidebar script without delay======
	// var chatlistSize = '${dtoTutorDetailsSize}';
	var url='<%=request.getContextPath()%>/parent/getParentChatSessionDetails';
	    setInterval(function(){
	   	$.ajax({
	   		url:url,
	   		method:'GET',
	   		async :true,
	   		success:function(response){
	   		if(response != null  && response.modelMap.dtoTutorDetails!=null){
	   			 var TutorData='<ul>';
	   				 $.each( response.modelMap.dtoTutorDetails, function(index,value) {
	   			    TutorData += '<li>'+
	                   	          '<div class="stud-img-outr">'+
	                           	  '<div class="indi-icon" style="float: left;margin: 4px 5px 0 5px;" >';
	                           	  if(value.loginStatus=='Y'){
	                           		  TutorData+=	'<img class="status'+value.tutorUserId+'" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
	         						}
	         						else{
	         							TutorData+= '<img class="status'+value.tutorUserId+'" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
	         						}
	                           	  TutorData+=      '</div><div class="tutor-img-txt">'+
	                                  value.fullName+'</div>';
	                                  
                	  if(value.studentChatStatus=='Y'){
                		  TutorData+=	'<div class="indi-icon" style="float: right;margin: 4px 5px 0 5px;" ><img class="status'+value.tutorUserId+'" src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>';
				}
                      
                      
                      TutorData+= '<div class="book-session"  style="float:right">';
	                                
                      if(value.readByParent=='Y'){
                    	  TutorData+= '<a href="javascript:register_popup(\''+value.tutorUserId+'\', \''+value.fullName+'\', \'tutor\', \''+value.tutorProfileId+'\',  \''+value.chatSessionId+'\');" name="chatTutor'+value.tutorUserId+'" id="chatTutor'+value.tutorUserId+'" class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';
                      }
                      else{
                    	  TutorData+=  '<a href="javascript:register_popup(\''+value.tutorUserId+'\', \''+value.fullName+'\', \'tutor\', \''+value.tutorProfileId+'\',  \''+value.chatSessionId+'\');" name="chatTutor'+value.tutorUserId+'" id="chatTutor'+value.tutorUserId+'" class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';  
                      }
                      TutorData += '</div></div></li>'; 
	   						 });
	   				 TutorData += '</ul>';
	   				$("#chatTutorDetails").html(TutorData);
	   				
	   				 $.each( response.modelMap.dtoTutorDetails, function(index,value) {
	   					 if(value.loginStatus=='Y'){
	   		            	   var online='<%=request.getContextPath()%>/images/green-bullet.png';
	   							$(".status"+value.tutorUserId).attr("src",online);
	   						}
	   						else{
	   							var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
	   							$(".status"+value.tutorUserId).attr("src",offline);
	   						}
	   				 }); 
	   		}
	   			 if(response.modelMap.listNewDtoTutorDetailsize=='0'){
	   				 var stuData='<ul><h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3></ul>';
	   				$("#chatTutorDetails").html(stuData); 
	   				chatlistSize='0';
	   			} 
	   		}
	   	});  
	       
	     }, 5000);	  
}); 
</script> 

</html>
