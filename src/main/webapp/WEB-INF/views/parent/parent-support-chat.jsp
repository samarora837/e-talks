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
<title><spring:message code="support.chat" /></title>
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

<script type="text/javascript" src="<%=request.getContextPath()%>/js/datetimeslider.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/timepicker.js"></script>
<%-- <link href="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.js"></script> --%>
<style type="text/css">

/* css for timepicker */
 .ui-timepicker-div .ui-widget-header {
    margin-bottom: 8px;
}
.ui-timepicker-div dl {
    text-align: left;
}
.ui-timepicker-div dl dt {
    float: left;
    clear:left;
    padding: 0 0 0 5px;
}
.ui-timepicker-div dl dd {
    margin: 0 10px 10px 45%;
}
.ui-timepicker-div td {
    font-size: 90%;
}
.ui-tpicker-grid-label {
    background: none;
    border: none;
    margin: 0;
    padding: 0;
}
.ui-timepicker-rtl {
    direction: rtl;
}
.ui-timepicker-rtl dl {
    text-align: right;
    padding: 0 5px 0 0;
}
.ui-timepicker-rtl dl dt {
    float: right;
    clear: right;
}
.ui-timepicker-rtl dl dd {
    margin: 0 45% 10px 10px;
}




</style>
 <script type="text/javascript">
  $(function() {
	  $( ".ellipsis1" ).tooltip({
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
            
       /*      .popup-box
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
            


        </style>
  
  
  <script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
  <%@ include file="/js/firebase/parent-firebase-support-script.jsp" %>
  
        <script type="text/javascript">
<%--             //this function can remove a array element.
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
            
            function register_popup(id, name,role)
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
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages">';
                
                element = element + ' <div class="db-chats-txt">'+ 
				'<div class="chat-inner" id="chatin'+studentId+'-'+id+'"><div id="position:relative;">'+ 
	               ' </div> <div id="position:relative;"></div></div><div class="chat-msg">'+ 
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

<script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

<script type="text/javascript">


 function sendMsg(fixit){ 
	 $( ".trying" ).keypress();
    var text = $('#chatMsg'+fixit).val();
  // // console.log("Text:", text);
    if(text!=null){
  	  window['newUserRef' + supportId ].push({name: "tutor", text: text});
    $('#chatMsg'+fixit).val('');
    
    
    
    var name = '${userFname}';
    var elementIdVal = fixit;
    var parentUserId = elementIdVal.substring(0, elementIdVal.indexOf('-'));
    var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
	  window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+parentUserId +':'+supportUserId);
    //var text = $('#'+elementIdVal).val();
   
     var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+elementIdVal).val();
    
    window['newUserRef' + supportId ].push({name: name, text: text});
 
    $('#'+elementIdVal).val('');  
    
    var url='<%=request.getContextPath()%>/parent/requestParentSupportChat';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{supportId:supportId},
		success:function(response){
		}
	    });
    
    }
    
  } 


var parentId1="0";
var supportId = "0";
var newUserRef ="";
function openchatwind(supportId,chatSatus,role){
	parentId1='${parentId}';
	$("#justtest").val(supportId);
	var url='<%=request.getContextPath()%>/parent/requestParentSupportChat';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{supportId:supportId,role:role},
		success:function(response){
			
		},
		complete:function(){
		
		var posfix =parentId1+"-"+supportId;
		
		
		
		if(chatSatus=='Y'){
			window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ parentId1 +':'+ supportId);
		 
		 window['newUserRef' + supportId ].on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
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
		          var name = '${userFname}';
		          var elementIdVal = $(e.target).attr("id");
		          var parentUserId = elementIdVal.substring(7, elementIdVal.indexOf('-'));
		          var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
		          
		    	  window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+parentUserId +':'+supportUserId);
		         // var text = $('#'+elementIdVal).val();
		         
		          var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+elementIdVal).val();
		         
		         window['newUserRef' + supportId ].push({name: name, text: text});
		       
		          $('#'+elementIdVal).val('');  
		          
		          var url='<%=request.getContextPath()%>/parent/requestParentSupportChat';
		      	$.ajax({
		      		url:url,
		      		method:'GET',
		      		async :false,
		      		data:{supportId:supportId},
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
		 window['newUserRef' + supportId ].on('child_added', function(DataSnapshot) {
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
		          var name = '${userFname}';
		          var elementIdVal = $(e.target).attr("id");
		          var parentUserId = elementIdVal.substring(7, elementIdVal.indexOf('-'));
		          var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
		    	  window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+parentUserId +':'+supportUserId);
		       //   var text = $('#'+elementIdVal).val();
		        
		        var nowDate = new Date();
	    	    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
	    	    var text = messageTime+"]: "+$('#'+elementIdVal).val();
		       
		       window['newUserRef' + supportId ].push({name: name, text: text});
		       
		          $('#'+elementIdVal).val('');  
		          
		          var url='<%=request.getContextPath()%>/parent/requestParentSupportChat';
		      	$.ajax({
		      		url:url,
		      		method:'GET',
		      		async :false,
		      		data:{supportId:supportId},
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

<body >
        
   <!--   ========================================== sidebar chat =========================== -->
    
  <%--   <div class="chat-sidebar">
    
   <div class="homeChat-hdr">Tutors Chat</div>
    
    <c:forEach var="subjectList" items="${subjectMasterName}">
    
    <h3> ${subjectList.subject_Type}</h3>
    
    <c:set var="tutorcount" value="0" scope="page" />
       <c:forEach var="tutorDetails" items="${tutorDetails}">
       
       <c:set var="subjectName" value="${subjectList.subject_Type}"/>
			<c:if test="${fn:contains(tutorDetails.subjectTypeMasterName, subjectName)}">
            <c:set var="tutorcount" value="${tutorcount + 1}" scope="page"/>
            
             
             <c:if test="${tutorcount ge 4}">
        <div class="sidebar-name subject${subjectList.subject_Type_Master_Id}" style="display: none;">
             </c:if>
             <c:if test="${tutorcount le 3}">
          <div class="sidebar-name ">
             </c:if>
             
             
                <!-- Pass username and display name to register popup -->
                <a href="javascript:register_popup('${tutorDetails.userId}', '${tutorDetails.name}','tutor');">
                  
                  <c:if test="${tutorDetails.login_status eq 'Y'}">
                  <img style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                  </c:if>
                  
                  <c:if test="${tutorDetails.login_status eq 'N'}">
                   <img  style="float:left; margin-top:7px;width:9px; height:9px;margin-left: -1px;margin-right: 3px;" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                  </c:if>
                  
                    <c:if test="${tutorDetails.imageName !=null}">
	              	<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/${tutorDetails.userId}/fileupload/${tutorDetails.userId}${tutorDetails.imageName}"/>
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
       </c:forEach>
       <c:if test="${tutorcount ge 4}">
        <div class="clr">
    <a onclick="showMoreRecord('${subjectList.subject_Type_Master_Id}')" id="show${subjectList.subject_Type_Master_Id}" value="showMore" class="chat-showMore">Show More</a>
    </div>
    </c:if>
       </c:forEach>
        </div>  --%>
 <!--   =========================== side bar chat end=============================================  -->       





<div id="main">
<%@ include file="/WEB-INF/views/parent/parent-inner-header.jsp" %>



  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
    
            <div class="my-info-tutor tutor-db-outr">
            <h1 class="text-left"><spring:message code="activeChats" /></h1>
            
<%--             
            <div class="stu-index-txt"  id="studentDetails">
            <div style="width: 100%; height: 400px; overflow: auto;">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoCustomerSupportDetails}" var="dtoCustomerSupportDetails">
                	 <c:if test="${dtoCustomerSupportDetails.isOnline eq 'Y'}">
                    	<li class="support${dtoCustomerSupportDetails.supportUserId}" >
                    	<c:set var="count" value="1" scope="page"/>
                    	 </c:if>
                    	 <c:if test="${dtoCustomerSupportDetails.isOnline eq 'N'}">
                    	<li class="support${dtoCustomerSupportDetails.supportUserId}"  style="display:none">
                    	 </c:if>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" >
                            	
                        	<img class="img${dtoCustomerSupportDetails.supportUserId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	
                            	
                            	</div>
                                <div class="tutor-img-txt">
                                ${dtoCustomerSupportDetails.supportName} 
                               
                                </div>
                                <div class="book-session">
                                	<a href="javascript:register_popup('${dtoCustomerSupportDetails.supportUserId}', '${dtoCustomerSupportDetails.supportName}', 'support');" name="chatTutor${dtoCustomerSupportDetails.supportUserId}" id="chatTutor${dtoCustomerSupportDetails.supportUserId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                                </div>
                            </div>
                        </li>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 id="noRecord" style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                        
                    </ul>
                    </div>
                </div>  --%>
  
  
  
              
            <div class="stu-index-txt"  id="studentDetails">
            <div style="width: 100%; height: 400px; overflow: auto;">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoCustomerSupportDetails}" var="dtoCustomerSupportDetails">
                	 
                    	<li class="support${dtoCustomerSupportDetails.supportUserId}" >
                    	<c:set var="count" value="1" scope="page"/>
                    	 
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" >
                      
                      <c:if test="${dtoCustomerSupportDetails.isOnline eq 'Y'}">      	
                        	<img class="img${dtoCustomerSupportDetails.supportUserId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                      </c:if>  	
                      
                       <c:if test="${dtoCustomerSupportDetails.isOnline eq 'N'}">      	
                        	<img class="img${dtoCustomerSupportDetails.supportUserId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                      </c:if> 
                      
                            	
                            	</div>
                                <div class="tutor-img-txt">
                                ${dtoCustomerSupportDetails.supportName} 
                               
                                </div>
                                <div class="book-session">
                                	<a href="javascript:register_popup('${dtoCustomerSupportDetails.supportUserId}', '${dtoCustomerSupportDetails.supportName}', 'support','${dtoCustomerSupportDetails.supportProfileId}', '${dtoCustomerSupportDetails.chatSessionId}');" name="chatTutor${dtoCustomerSupportDetails.supportUserId}" id="chatTutor${dtoCustomerSupportDetails.supportUserId}"  class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               
                               
                                </div>
                            </div>
                        </li>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 id="noRecord" style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                        
                    </ul>
                    </div>
                </div> 
            
            
            
            
          </div> 
    
    
    
        </div>
      </div>
    </div>
  </section>
  <!--//Mid Section--> 
</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>
        
</body>
<script type="text/javascript">


setInterval(function(){
	var url='<%=request.getContextPath()%>/parent/customerParentSupportLoginStatus';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null && response.modelMap.dtoCustomerSupportDetails!=null){
				var recordCounter=0;
				$.each( response.modelMap.dtoCustomerSupportDetails, function( key , value ) {
					if(value.readByParent=='N'){
						$("#chatTutor"+value.supportUserId).removeClass("grayBtn-normal");
						 $("#chatTutor"+value.supportUserId).addClass("greenBtn-normal");
					}
					if(value.readByParent=='Y'){
						$("#chatTutor"+value.supportUserId).removeClass("greenBtn-normal");
						 $("#chatTutor"+value.supportUserId).addClass("grayBtn-normal");
					}
					
					//===== for online status=====
					if(value.readByParent=='Y'){
						$(".support"+value.supportUserId).show();
						var online='<%=request.getContextPath()%>/images/green-bullet.png';
						$(".img"+value.userId).attr("src",online);
						$("#noRecord").hide();
						recordCounter++;
						
					}
					if(value.readByParent=='N'){
						var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
						$(".img"+value.userId).attr("src",offline);
						//$(".support"+value.supportUserId).hide();
					}
					if(recordCounter<=0){
						$("#noRecord").show();
					}
					
					
					
					 });
			}
			
		}
	});  
}, 5000);


<%--  setInterval(function(){
	var url='<%=request.getContextPath()%>/parent/customerParentSupportLoginStatus';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null){
				var recordCounter=0;
				$.each( response.modelMap.dtoCustomerSupportDetails, function( key , value ) {
					 
					if(value.readByParent=='Y'){
						$(".support"+value.supportUserId).show();
						var online='<%=request.getContextPath()%>/images/green-bullet.png';
						$(".img"+value.userId).attr("src",online);
						$("#noRecord").hide();
						recordCounter++;
						
					}
					if(value.readByParent=='N'){
						var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
						$(".img"+value.userId).attr("src",offline);
						//$(".support"+value.supportUserId).hide();
					}
					if(recordCounter<=0){
						$("#noRecord").show();
					}
					
					 });
				
			}
			
		}
	});  
}, 5000); --%>
 
</script>
</html>
