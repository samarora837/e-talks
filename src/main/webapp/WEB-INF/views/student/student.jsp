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
<title><spring:message code="student.home" /></title>
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

<script src="http://malsup.github.com/jquery.form.js"></script>

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


/* .ui-datepicker .ui-datepicker-buttonpane button.ui-datepicker-current {
float:left;
background: #900;
display: none;
}  */





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

<script type="text/javascript">

function changeRating(rating,ratingName){
	var ratetutor="";
	var rateknowledge="";
	var rateexplain="";
	var ratequality="";
	if(ratingName=="ratetutor"){
	for(var i=1;i<=5;i++){
		
		if(i <= rating){
			
			ratetutor+='<img src="<%=request.getContextPath()%>/images/star-yellow.png"  alt=""  onclick="changeRating('+i+',\'ratetutor\');">';
		}
		else{
			ratetutor+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""   onclick="changeRating('+i+',\'ratetutor\');">';
		}
		
	}
	
	$("#ratingTutor").html("");
	$("#ratingTutor").html(ratetutor);
	$("#ratingId").val(rating);
	
	}
	
	if(ratingName=="rateknowledge"){
		for(var i=1;i<=5;i++){
			
			if(i <= rating){
				
				rateknowledge+='<img src="<%=request.getContextPath()%>/images/star-yellow.png"  alt=""  onclick="changeRating('+i+',\'rateknowledge\');">';
			}
			else{
				rateknowledge+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""   onclick="changeRating('+i+',\'rateknowledge\');">';
			}
			
		}
		
		$("#ratingTutorKnowledge").html("");
		$("#ratingTutorKnowledge").html(rateknowledge);
		$("#ratingKnowledge").val(rating);
		}
	
	if(ratingName=="rateexplain"){
		for(var i=1;i<=5;i++){
			
			if(i <= rating){
				
				rateexplain+='<img src="<%=request.getContextPath()%>/images/star-yellow.png"  alt=""  onclick="changeRating('+i+',\'rateexplain\');">';
			}
			else{
				rateexplain+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""   onclick="changeRating('+i+',\'rateexplain\');">';
			}
			
		}
		
		$("#ratingTutorExplain").html("");
		$("#ratingTutorExplain").html(rateexplain);
		$("#ratingExplain").val(rating);
		}
	
	if(ratingName=="ratequality"){
		for(var i=1;i<=5;i++){
			
			if(i <= rating){
				
				ratequality+='<img src="<%=request.getContextPath()%>/images/star-yellow.png"  alt=""  onclick="changeRating('+i+',\'ratequality\');">';
			}
			else{
				ratequality+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""  onclick="changeRating('+i+',\'ratequality\');">';
			}
			
		}
		
		$("#ratingTutorConnectionQuality").html("");
		$("#ratingTutorConnectionQuality").html(ratequality);
		$("#ratingQuality").val(rating);
		}
		
	
	
	
}

function recomendTutor(recomendStatus){
	var recomendImage="";
	var rating="";
	if(recomendStatus==1){
		recomendImage+='<img src="<%=request.getContextPath()%>/images/thumb-up.png" alt="" onclick="recomendTutor(1);">'+
		'<img src="<%=request.getContextPath()%>/images/thumb-down1.png" alt="" onclick="recomendTutor(2);">';
		rating="Y";
	}
	else
		{
		recomendImage+='<img src="<%=request.getContextPath()%>/images/thumb-up1.png" alt="" onclick="recomendTutor(1);">'+
		'<img src="<%=request.getContextPath()%>/images/thumb-down.png" alt="" onclick="recomendTutor(2);">';
		rating="N";
		}
	
	$("#recomendImage").html("");
	$("#recomendImage").html(recomendImage);
	$("#ratingRecomend").val(rating);
}

function submitRating(){
	
	var comments=$("#ratingComment").val();
	
	$("#comments").val(comments);
	
	$("#submitRatingForm").submit();
}
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
  <%@ include file="/js/firebase/student-firebase-home-script.jsp" %>
      
 
 
 <!--  **************************************************************** FIREBASE SCRIPT************************************************************************* -->
        
       <%--  <script type="text/javascript">
            //this function can remove a array element.
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
            function close_popup(id,chatsessionId,action)
            {
            //	alert(action);
            	$("#"+id).css("display", "none");
            	if(chatsessionId!='0'){
            		endChatSession(chatsessionId,action);
            	}
            	  
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
            var studentId='${studentId}';
            
            function register_popup(id, name,role,tutorProfileId,chatsessionId)
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
                element = element + '<div class="popup-head-right"> <a href="javascript:close_popup(\''+ id +'\','+chatsessionId+',\'minimize\');">_</a> <a href="javascript:close_popup(\''+ id +'\','+chatsessionId+',\'close\');">&#10005;</a></div>';
                if(tutorProfileId!='NA')
            	{
                element = element + '<div class="chat-bookSession"><a onclick="openBookingPopUp('+id+');" class="greenBtn-small"> <spring:message code="book.session"/>    </a></div>';
            	}
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
            
      function endChatSession(chatSessionId,action){
    	//  alert(action);
	   var url='<%=request.getContextPath()%>/student/EndStudentDashboardChatSession';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{chatSessionId:chatSessionId,action:action},
			success:function(){
			}
		}); 
	
}
      
      
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
    var studentUserId = targetId.substring(0, targetId.indexOf('-'));
    var tutorUserId = targetId.substring(targetId.indexOf('-')+1,targetId.length);
	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+studentUserId +':'+tutorUserId);
   // var text = $('#'+targetId).val();
    
    var nowDate = new Date();
    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
    var text = messageTime+"]: "+$('#'+targetId).val();
    
    
   window['newUserRef' + tutorId ].push({name: name, text: text});
 
    $('#'+targetId).val('');  
    
    var url='<%=request.getContextPath()%>/student/requestTutorChat';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{tutorId:tutorId,role:role},
		success:function(response){
		}
	    });
    
    }
    
  } 


var studentId1="0";
var tutorId = "0";
var stId='${studentId}';
//var posfix = stId+"-";
var newUserRef ="";
function openchatwind(tutorId,chatSatus,role){
	$("#receipentRoleName").val(role);
	studentId1='${studentId}';
	$("#justtest").val(tutorId);
	var url='<%=request.getContextPath()%>/student/requestTutorChat';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{tutorId:tutorId,role:role},
		success:function(response){
			
		},
	//	complete:function(){
		
		var posfix =studentId1+"-"+tutorId;
		
		
		
		if(chatSatus=='Y'){
			window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		 
		 window['newUserRef' + tutorId ].on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      var stuId = '${studentId}';
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
		         var role =	$("#receipentRoleName").val();
		          var name = '${userFname}';
		          var targetId = $(e.target).attr("id");
		          var studentUserId = targetId.substring(7, targetId.indexOf('-'));
		          var tutorUserId = targetId.substring(targetId.indexOf('-')+1,targetId.length);
		          
		    	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+studentUserId +':'+tutorUserId);
		        //  var text = $('#'+targetId).val();
		          
		          
		          var nowDate = new Date();
			      var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
			      var text = messageTime+"]: "+$('#'+targetId).val();
		          
		         window['newUserRef' + tutorId ].push({name: name, text: text});
		       
		          $('#'+targetId).val('');  
		          
		          var url='<%=request.getContextPath()%>/student/requestTutorChat';
		      	$.ajax({
		      		url:url,
		      		method:'GET',
		      		async :false,
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
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      var stuId = '${studentId}';
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
		        	var role =	$("#receipentRoleName").val();
		          var name = '${userFname}';
		          var targetId = $(e.target).attr("id");
		          var studentUserId = targetId.substring(7, targetId.indexOf('-'));
		          var tutorUserId = targetId.substring(targetId.indexOf('-')+1,targetId.length);
		    	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+studentUserId +':'+tutorUserId);
		    	  
		    	  var nowDate = new Date();
			      var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
			      var text = messageTime+"]: "+$('#'+targetId).val();
			      
		          window['newUserRef' + tutorId ].push({name: name, text: text});
		       
		          $('#'+targetId).val('');  
		          
		          var url='<%=request.getContextPath()%>/student/requestTutorChat';
		      	$.ajax({
		      		url:url,
		      		method:'GET',
		      		async :false,
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
	   
		
//	}  ////complete ends
		
		
	
//	}); // ajax ends
}
 --%>
 
<!--  **************************************************************** FIREBASE SCRIPT************************************************************************* -->
 
 
<script type="text/javascript">

 $(document).ready(function(){
	 $('#sendMessageForm').validate({
		rules:{
           message: {
               required:true
           }
			}
	});
	 
	 // sidebar script without delay======
	 var chatlistSize = '${dtoTutorDetailsSize}';
	    setInterval(function(){
	   	var url='<%=request.getContextPath()%>/student/getStudentChatSessionDetails'; 
	   	$.ajax({
	   		url:url,
	   		method:'GET',
	   		async :true,
	   		success:function(response){
	   		if(response != null  && response.modelMap.dtoTutorDetails!=null){
	   			 	var newlistsize= response.modelMap.listNewDtoTutorDetailsize;
	   			 var TutorData='<ul>';
	   		//		if(chatlistSize!=newlistsize){
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
	                                  
                      
                      TutorData+= '<div class="book-session"  style="float:right">';
	                                
                      if(value.studentChatStatus=='Y'){
                    	  TutorData+= '<a href="javascript:register_popup(\''+value.tutorUserId+'\', \''+value.fullName+'\', \'tutor\', \''+value.tutorProfileId+'\',  \''+value.chatSessionId+'\');" name="chatTutor'+value.tutorUserId+'" id="chatTutor'+value.tutorUserId+'" class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';
                      }
                      else{
                    	  TutorData+=  '<a href="javascript:register_popup(\''+value.tutorUserId+'\', \''+value.fullName+'\', \'tutor\', \''+value.tutorProfileId+'\',  \''+value.chatSessionId+'\');" name="chatTutor'+value.tutorUserId+'" id="chatTutor'+value.tutorUserId+'" class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';  
                      }
                      TutorData += '</div></div></li>'; 
	                   	   			   
	   						 });
	   				 TutorData += '</ul>';
	   				$("#chatTutorDetails").html(TutorData);
	   				if(chatlistSize<newlistsize){
	   		//		$.playSound(soundurl);
	   				}
	   				chatlistSize=newlistsize;
	   		//		}
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

<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body onload="getIp();">
        
   <!--   ========================================== sidebar chat =========================== -->
    
    <div class="chat-sidebar" id="chatBar" style="display:none;">
    
   <div class="homeChat-hdr"><spring:message code="tutors.chat"/>   <a onclick="closeChatBar();" style="float: right;margin-right: 5px; color: white;"> 
   <img  style="width:18px; position:relative; top:1px;"  alt="" src="<%=request.getContextPath()%>/images/arrow_down.png"/></a></div>
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
       </c:forEach>
          --%>
        </div>
 <!--   =========================== side bar chat end=============================================  -->       

<div id="main">
<%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>


 <c:if test="${showConfirmPopup eq 'Y' }">
   <div class="form-tutor-popup" id="ratingPopup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/student/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt">
            	<spring:message code="thankyouforrating"/>
            </div>
               
             
           <div class="book-session">
               <a href="javascript:;" class="greenBtn-normal" onclick="openReviewPopUp('${tutorId}');">  <spring:message code="newhomework.review"></spring:message> </a>
               <a href="javascript:;" class="greenBtn-normal" onclick="openBookingPopUp('${tutorId}');">  <spring:message code="student.booknewsession"></spring:message> </a>
           </div>
          
        </div>
        </div>
        </div>
   </c:if> 


 <c:if test="${showRating eq 'Y' }">
 <div class="form-tutor-popup" id="ratingPopup">
 <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/student/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
           <div class="my-info-popup">
           	<div class="tRating">
            <h3><spring:message code="messagetoratetutor"/></h3>
			<div class="rate-con">
            	<div class="rate-conLt">
                	<div class="rateRow">
                    	<div class="rateTxt"><spring:message code="general.experience"/></div>
                        <span id="ratingTutor">
                        	<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(1,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(2,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(3,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(4,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(5,'ratetutor');">
                        </span>
                    </div>
                    <div class="rateRow">
                    	<div class="rateTxt"><spring:message code="knowledge"/></div>
                        <span id="ratingTutorKnowledge">
                        	<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(1,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(2,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(3,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(4,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(5,'rateknowledge');">
                        </span>
                    </div>
                    <div class="rateRow">
                    	<div class="rateTxt"><spring:message code="explains.well"/></div>
                        <span id="ratingTutorExplain">
                          	<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(1,'rateexplain');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(2,'rateexplain');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(3,'rateexplain');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(4,'rateexplain');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(5,'rateexplain');">
                        </span>
                    </div>
                     <div class="rateRow">
                    	<div class="rateTxt"><spring:message code="connection.quality"/></div>
                        <span id="ratingTutorConnectionQuality">
                          	<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(1,'ratequality');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(2,'ratequality');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(3,'ratequality');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(4,'ratequality');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(5,'ratequality');">
                        </span>
                    </div>
                </div>
                <div class="rate-conrt">
                	<div><spring:message code="doyou.recommend"/></div>
                    <div class="thumgImg" id="recomendImage">
                    	<img src="<%=request.getContextPath()%>/images/thumb-up1.png" alt="" onclick="recomendTutor(1);">
                    	<img src="<%=request.getContextPath()%>/images/thumb-down1.png" alt="" onclick="recomendTutor(2);">
                    </div>
                </div>
            </div>
            
            <div class="comment-con">
            <h5><spring:message code="comments"/></h5>
            <textarea rows="5" cols="20" style="resize:none;" id="ratingComment" name="ratingComment" maxlength="150"> </textarea>
            </div>
            
            
            <p align="center">
            <input type="button" class="greenBtn-normal" onclick="submitRating();" value='<spring:message code="submit.rating"/>'>
            <input type="button" class="blueBtn-normal" onclick="openBookingPopUp('${tutorId}');" value='<spring:message code="student.booknewsession"/>'>
            </p>
            
            </div>
           
                </div>
        	
        </div>
   </div>
 
 </div>
 
 
   </c:if> 
<form name="submitRatingForm" id="submitRatingForm" method="POST" action="<%=request.getContextPath()%>/student/tutorRating">
<input type="hidden" name="ratingId" id="ratingId">
<input type="hidden" name="ratingKnowledge" id="ratingKnowledge">
<input type="hidden" name="ratingExplain" id="ratingExplain">
<input type="hidden" name="ratingQuality" id="ratingQuality">
<input type="hidden" name="ratingRecomend" id="ratingRecomend">
<input type="hidden" name="comments" id="comments">
<input type="hidden" name="tutorUserId" id="tutorUserId" value="${tutorId}">
</form>

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
            <div style="width: 100%; height: 400px; overflow: auto;">
                	<ul>
                	
                	<%-- <c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${listDtoTutorRegistrations}" var="listDtoTutorRegistrations">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" ><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                                <div class="tutor-img" id="immg${listDtoTutorRegistrations.userId}">
                                <c:if test="${listDtoTutorRegistrations.imageName !=null}">
					              	<img alt="" src="<%=request.getContextPath()%>/${listDtoTutorRegistrations.userId}/fileupload/${listDtoTutorRegistrations.userId}${listDtoTutorRegistrations.imageName}"/>
					              	</c:if>
					              	<c:if test="${listDtoTutorRegistrations.imageName ==null && listDtoTutorRegistrations.imgUrl ==null}">
					              	<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
					              	</c:if>
					              	<c:if test="${listDtoTutorRegistrations.imgUrl !=null && listDtoTutorRegistrations.imageName==null}">
					              	<img alt="" src="${listDtoTutorRegistrations.imgUrl}"/>
					              	</c:if>
                                </div>
                                
                                
                                <div class="tutor-img-txt">
                                ${listDtoTutorRegistrations.name}
                                	<c:if test="${listDtoTutorRegistrations.isFavourite eq 'N'}">
                                	 <span class="heartImg"><img onclick="setAsFavourite('${listDtoTutorRegistrations.userId}');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>
                                	</c:if>
                                	<c:if test="${listDtoTutorRegistrations.isFavourite eq 'Y'}">
                                	 <span class="heartImg" ><img onclick="setAsFavourite('${listDtoTutorRegistrations.userId}');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>
                                	</c:if>
                                	
                                    <div class="subjects ellipsis ellipsis1" title="${listDtoTutorRegistrations.subjectNames}">${listDtoTutorRegistrations.subjectNames}</div>
                                    
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
                                	<a href="javascript:;" name="bookTutor" id="bookTutor" onclick="openBookingPopUp('${listDtoTutorRegistrations.userId}');" class="greenBtn-normal"><spring:message code="student.booknewsession"></spring:message> </a>
                              <a href="javascript:register_popup('${listDtoTutorRegistrations.userId}', '${listDtoTutorRegistrations.name}');" name="chatTutor${listDtoTutorRegistrations.userId}" id="chatTutor${listDtoTutorRegistrations.userId}"  class="blueBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                                </div>
                                
               
                                
                            </div>
                            <div class="info-outr">
                                <div class="ellipsis ellipsis1" title="${listDtoTutorRegistrations.about}">${listDtoTutorRegistrations.about}</div>
                                <p class="ellipsis ellipsis1" title="${listDtoTutorRegistrations.college}">${listDtoTutorRegistrations.college}</p>
                                <p >${listDtoTutorRegistrations.career}</p>
                                <div><a onclick="openSeeMore('${listDtoTutorRegistrations.tutorProfileId}');"><spring:message code="student.seemore"/></a></div> 
                            </div>
                        </li>
                        <c:set var="count" value="1" scope="page"/>
                        </c:forEach>
                        <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if> --%>
                        
                    </ul>
                    </div>
                </div>
            </div>
            
          <!--   ============= chat support section ============================== -->
             
              <div class="stu_rt">
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
                                
                                <c:if test="${dtoTutorDetails.studentChatStatus eq 'Y'}">
                                	<a href="javascript:register_popup('${dtoTutorDetails.tutorUserId}', '${dtoTutorDetails.fullName}','tutor', '${dtoTutorDetails.tutorProfileId}', '${dtoTutorDetails.chatSessionId}');" name="chatTutor${dtoTutorDetails.tutorUserId}" id="chatTutor${dtoTutorDetails.tutorUserId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                               <c:if test="${dtoTutorDetails.studentChatStatus eq 'N'}">
                                	<a href="javascript:register_popup('${dtoTutorDetails.tutorUserId}', '${dtoTutorDetails.fullName}','tutor', '${dtoTutorDetails.tutorProfileId}', '${dtoTutorDetails.chatSessionId}');" name="chatTutor${dtoTutorDetails.tutorUserId}" id="chatTutor${dtoTutorDetails.tutorUserId}"  class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
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
            <form:form id="bookinForm" name="bookinForm" commandName="dtoBookingDetail" enctype="multipart/form-data"  method="POST">
            <input type="hidden" id="tutorId" name="tutorId">
            <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                       <tr>
                   <th><spring:message code="subject.title"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <form:select id="subjectTitle" name="subjectTitleId" path="subjectTitleId" onchange="getSubjectType(this);">
                		<%-- <c:forEach var="listLevelMaster" items="${listLevelMaster}">                   	
		             <option value='${listLevelMaster.level_Id}' >${listLevelMaster.level}</option>             
                    	 	</c:forEach> --%>
                    </form:select>
                    </div>
                    </td>
                  </tr>
                  
                      <tr>
                   <th><spring:message code="subject.type"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <form:select id="subjectType" path="subjectTypeId" name="subjectTypeId">
                		<%-- <c:forEach var="listLevelMaster" items="${listLevelMaster}">                   	
		             <option value='${listLevelMaster.level_Id}' >${listLevelMaster.level}</option>             
                    	 	</c:forEach> --%>
                    </form:select>
                    </div>
                    </td>
                  </tr> 
                  
                  <tr>
                    <th><spring:message code="question"/></th>
                    <td>:</td> 
                    <td><div > <form:textarea  path="question" style="height:50px;"  id="question" name="question" maxlength="250" /></div></td>
                  </tr>
                  
                  
                  <tr>
                    <th><spring:message code="sessionfile"/></th>
                    <td>:</td> 
                    <td><div > <form:input type="file" path="sessionDocument" id="sessionDocument" name="sessionDocument" /> </div></td>
                  </tr>
                  
                  
                  <tr>
                    <th><spring:message code="bookingdate.student"/></th>
                    <td>:</td> 
                   <!--  onchange="getTutorDate();" cal this method for tutor timezone-->
                    <td><div > <form:input type="text" class="date-input" path="bookingDate"   placeholder="yyyy-mm-dd hh:mm" onblur="getTutorDate();" readonly="true"  id="bookingDate" name="bookingDate" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="bookingdate.tutor"/></th>
                    <td>:</td> 
                    <td><div > <label  placeholder="yyyy-mm-dd hh:mm" readonly="true"  id="tutorBookingDate" name="tutorBookingDate" >yyyy-mm-dd hh:mm</label></div></td>
                  </tr>
                  
                         <tr>
                   <th><spring:message code="duration"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <form:select id="bookingDuration" path="bookingDuration" name="bookingDuration">
                	                  	
		             <option value='15'>15 <spring:message code="mins"/></option>
		             <option value='30'>30 <spring:message code="mins"/></option>
		             <option value='45'>45 <spring:message code="mins"/></option>
		             <option value='60'>60 <spring:message code="mins"/></option>             
                    	 	
                    </form:select>
                    </div>
                    </td>
                  </tr> 
                    <tr>
                   <th colspan="2">&nbsp;</th>
                     <td>
                 
                     <input type="button" id="bookingButton" class="greenBtn-normal" value='<spring:message code="book.session"></spring:message>' onclick="submitBookingForm();" >
                     
                     
                     <!-- <input type="submit" id="bookingButton123" class="greenBtn-normal" value='fsdfsf'  > -->
                     
                     </td>
                  </tr>
                  
            </tbody></table>
                </div>
            </form:form>
        </div>
   </div>
</div> 
<!-- End Booking Pop Up -->  

<!-- Review Pop Up -->
<div id="reviewpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeReviewPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <form id="reviewForm" name="reviewForm" action="<%=request.getContextPath()%>/student/studentReviewRequest"  method="POST"  enctype="multipart/form-data">
            <input type="hidden" id="reviewTutorId" name="reviewTutorId">
            <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                       <tr>
                   <th><spring:message code="subject.title"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <select id="subjectTitleReview" name="subjectTitleReview"  onchange="getSubjectTypeReview(this);">
                		
                    </select>
                    </div>
                    </td>
                  </tr>
                  
                      <tr>
                   <th><spring:message code="subject.type"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <select id="subjectTypeReview"  name="subjectTypeReview">
                		
                    </select>
                    </div>
                    </td>
                  </tr> 
                  
                  <tr>
                    <th><spring:message code="question"/></th>
                    <td>:</td> 
                    <td><div > <textarea style="height:50px;"  id="reviewQuestion" name="reviewQuestion"></textarea></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="documenrReview"/></th>
                    <td>:</td> 
                    <td><div > <input type="file" id="documentReview" name="documentReview" /> </div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="deliverytime.review"/></th>
                    <td>:</td> 
                <!--     onchange="getTutorDate();" call this method for tutor timezone time -->
                    <td><div > <input type="text" class="date-input"  placeholder="yyyy-mm-dd hh:mm" readonly="true" onblur="getTutorDate();"  id="reviewDate" name="reviewDate" /></div></td>
                  </tr>
                  
                    <tr>
                    <th><spring:message code="bookingdate.tutor"/></th>
                    <td>:</td> 
                    <td><div > <label  placeholder="yyyy-mm-dd hh:mm" readonly="true"  id="reviewDateTutor" name="reviewDateTutor" >yyyy-mm-dd hh:mm</label></div></td>
                  </tr>
                  <%-- 
                         <tr>
                   <th><spring:message code="duration"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <select id="reviewDuration"  name="reviewDuration">
                	                  	
		             <option value='15'>15 <spring:message code="mins"/></option>
		             <option value='30'>30 <spring:message code="mins"/></option>
		             <option value='45'>45 <spring:message code="mins"/></option>
		             <option value='60'>60 <spring:message code="mins"/></option>             
                    	 	
                    </select>
                    </div>
                    </td>
                  </tr>  --%>
                    <tr>
                   <th colspan="2">&nbsp;</th>
                     <td>
                 
                    <%--  <input type="button" id="reviewButton" class="greenBtn-normal" value='<spring:message code="book.session"></spring:message>' onclick="submitReviewForm();" > --%>
                      <%-- <input type="submit" id="reviewButton" class="greenBtn-normal" value='<spring:message code="bookreview.session"></spring:message>'> --%>
                      <input type="button" id="reviewButton"  onclick="submitReviewForm();" class="greenBtn-normal" value='<spring:message code="bookreview.session"></spring:message>'>
                     </td>
                  </tr>
                  
            </tbody></table>
                </div>
            </form>
        </div>
   </div>
</div> 
<!-- End review Pop Up -->      
  
  
  <div class="form-tutor-popup" id="favouriteTutor" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeFavouritePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" id="responseMessage">
            	
            </div>
               
             
           <div class="book-session">
               <a onclick="closeFavouritePopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>  
        
        
        
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
        
   <%--  <c:if test="${bookingSuccess eq 'Y'}">    --%>
  <div class="form-tutor-popup" id="bookSuccess" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closebookSuccessPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="bookingSuccess"/>
            </div>
           <div class="book-session">
               <a onclick="closebookSuccessPopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>  
        
   <div class="form-tutor-popup" id="bookFailure" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closebookFailurePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="booking.failure"/>
            </div>
           <div class="book-session">
               <a onclick="closebookFailurePopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>  
        
   <c:if test="${reviewSessionBooked eq 'Y'}"> 
  <div class="form-tutor-popup" id="reviewSuccess" >
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeReviewSuccessPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="bookingSuccess"/>
            </div>
           <div class="book-session">
               <a onclick="closeReviewSuccessPopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>        
        </c:if>
        
        <c:if test="${reviewSessionBooked eq 'N'}"> 
           <div class="form-tutor-popup" id="reviewFailure" >
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeReviewFailurePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="booking.failure"/>
            </div>
           <div class="book-session">
               <a onclick="closeReviewFailurePopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div> 
        </c:if>
        
        <% session.removeAttribute("reviewSessionBooked"); %>
        
        
        <%-- Message PopUp Start --%>
        
         <div id="sendMessagepopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeSendMessagePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form name="sendMessageForm" id="sendMessageForm" method="post">
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
                </form>
        	
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
        
    <div class="form-tutor-popup" id="fileError" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="javascript:void(0);" onclick="hideFileError()"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" id="fileMsg" >
            	  <spring:message code="file.missing.error"/>
            </div>
               
             
           <div class="book-session">
               <a href="javascript:void(0);" onclick="hideFileError()" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>        


        
        <%-- Message Popup End --%>
        
        
        
       <%--  <%session.removeAttribute("bookingSuccess");%>       --%> 
<%--  </c:if>  --%>       
        
        <input type="hidden" id="receipentRoleName" name="receipentRoleName" readonly="readonly"/>
 <div class="homeChat-hdr" id="openChatBar" style="display:none;"><spring:message code="tutors.chat"/>  <a onclick="openChatBar();" style="float: right;margin-right: 5px; color: white;"> 
 <img  style="width:18px; position:relative; top:1px;"  alt="" src="<%=request.getContextPath()%>/images/arrow_up.png"/></a></div>        
</body>



<script type="text/javascript">

function openMessagePopUp(tutorId,tutorName){
	
	var studentName='${user}';
	
	$("#tutorMessageUserId").val(tutorId);
	$("#toName").text(tutorName);
	$("#fromName").text(studentName);
	$(window).scrollTop(0);
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
        
    	var url='<%=request.getContextPath()%>/student/sendMessageHome';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :true,
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

function openBookingPopUp(tutorId)
{
	// ======== variable used in timepicker.js to change calendar slider according to time===============
	resetCalTime ="bookingPopUp";
	
	$("#ui-datepicker-div").remove();
	$(".date-input").removeClass('hasDatepicker').datetimepicker( {
		addSliderAccess: true,
		sliderAccessArgs: { touchonly: false }
	}); 
	
	$('.date-input').datetimepicker('destroy');
	
 	$(".date-input").datetimepicker(
	        {
		addSliderAccess: true,
		sliderAccessArgs: { touchonly: false }
	}); 
 	
 	$(".date-input").datetimepicker('setDate',new Date(new Date().getTime()));
 	$( ".date-input" ).datetimepicker("refresh");
	
 	
 	$('<style type="text/css"> .ui-datepicker-current { display: block; } </style>').appendTo("head");
 	
 	//$(".date-input").datetimepicker("setDate",d2);
	$(window).scrollTop(0);
	$("#tutorId").val(tutorId);
	var url='<%=request.getContextPath()%>/student/getTutorSubjectList';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{tutorId:tutorId},
		success:function(response){
			$('#subjectTitle').children().remove();
			$('#subjectTitle').append('<option value="0"><spring:message code="selectany.value"/></option>');
			 $.each( response.modelMap.allSubjectTitleList, function( key , value ) {
			$('#subjectTitle').append('<option value="'+key+'">'+value+'</option>');
			 });
		}
	});  
	$("#ratingPopup").hide();
	$("#bookingpopup").show();
}


function openReviewPopUp(tutorId)
{
	// ======== variable used in timepicker.js to change calendar slider according to time===============
	resetCalTime ="reviewPopUp";
	
 	$("#ui-datepicker-div").remove();
	
 	$(".date-input").removeClass('hasDatepicker').datetimepicker( {
		addSliderAccess: true,
		sliderAccessArgs: { touchonly: false },
	});  
	
 	$('.date-input').datetimepicker('destroy');
 	
	 $(".date-input").datetimepicker(
	        {
		addSliderAccess: true,
		sliderAccessArgs: { touchonly: false }
	});   
	 
	$(".date-input").datetimepicker('setDate', new Date(new Date().getTime()+1/2*3600*1000));
	$( ".date-input" ).datetimepicker("refresh");
	$('<style type="text/css"> .ui-datepicker-current { display: none; } </style>').appendTo("head");
 	
 	
	$(window).scrollTop(0);
	$("#reviewTutorId").val(tutorId);
	$("#tutorId").val(tutorId);
	var url='<%=request.getContextPath()%>/student/getTutorSubjectList';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{tutorId:tutorId},
		success:function(response){
			$('#subjectTitleReview').children().remove();
			$('#subjectTitleReview').append('<option value="0"><spring:message code="selectany.value"/></option>');
			 $.each( response.modelMap.allSubjectTitleList, function( key , value ) {
			$('#subjectTitleReview').append('<option value="'+key+'">'+value+'</option>');
			 });
		}
	});  
	$("#ratingPopup").hide();
	$("#reviewpopup").show();
}

function closeReviewPopUp(){
	$("label.error").hide();
	$("#reviewpopup").hide();
}


function getSubjectType(subjectTitleId)
{
	var tutorId=$("#tutorId").val();
	var subjectitleId=subjectTitleId.value;
	
	var url='<%=request.getContextPath()%>/student/getTutorSubjectTypeList';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{subjectitleId:subjectitleId,tutorId:tutorId},
		success:function(response){
			 $('#subjectType').children().remove();
			 $.each( response.modelMap.allSubjectTypeList, function( key , value ) {
				 
			$('#subjectType').append('<option value="'+key+'">'+value+'</option>');
			 }); 
		}
	}); 
}

function getSubjectTypeReview(subjectTitleId)
{
	var tutorId=$("#tutorId").val();
	var subjectitleId=subjectTitleId.value;
	
	var url='<%=request.getContextPath()%>/student/getTutorSubjectTypeList';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{subjectitleId:subjectitleId,tutorId:tutorId},
		success:function(response){
			 $('#subjectTypeReview').children().remove();
			 $.each( response.modelMap.allSubjectTypeList, function( key , value ) {
				 
			$('#subjectTypeReview').append('<option value="'+key+'">'+value+'</option>');
			 }); 
		}
	}); 
}



function submitBookingForm(){
	
	if ($('#bookinForm').valid()) {
		
		$("#bookingpopup").hide();
	
        var subjectType =  $("#subjectType").val();
        var subjectTitleId = $("#subjectTitle").val();
        var bookingDate =    $("#bookingDate").val();
        var bookingDuration =$("#bookingDuration").val();
        var tutorId =$("#tutorId").val();
        var question = $("#question").val();
        
        var formData = new FormData();
        
       var file_data = $('input[type="file"]')[0].files; // for multiple files
        for(var i = 0;i<file_data.length;i++){
        	formData.append("file", file_data[i]);
            
        }
    //    var file_data = $("#sessionDocument").prop("files")[0];
        formData.append("subjectType", subjectType);
        formData.append("tutorId", tutorId);
        
        formData.append("subjectTitleId", subjectTitleId);
        formData.append("bookingDate", bookingDate);
        formData.append("bookingDuration", bookingDuration);
        formData.append("question", question);
        
        var file_data = $("#sessionDocument").prop("files")[0];
        var url="";
        if(typeof(file_data)==='undefined'){
        	url = "<%=request.getContextPath()%>/student/studentBookingTutor";
        }  
        else{
        	 
        	 url = "<%=request.getContextPath()%>/student/studentBookingTutorWithFile";
        }
        
        
        $.ajax({
            url: url,
            data: formData,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function(response){
            	if(response.modelMap.sessionBooked=="Y"){
        			$(window).scrollTop(0);
        			$("#bookingpopup").hide();
        			$("#bookSuccess").show();
        			}
        			else if(response.modelMap.sessionBooked=="N"){
        				$(window).scrollTop(0);
        				$("#bookingpopup").hide();
        				$("#bookFailure").show();
        			}
            }
        });
        
    }   
}

function submitReviewForm(){
	if ($('#reviewForm').valid()) {
		
        var file_data = $("#documentReview").prop("files")[0];
        if(typeof(file_data)==='undefined'){
        	$(window).scrollTop(0);
        	$("#fileError").show();
        }  
        else{
        	$("#reviewpopup").hide();
        	$('#reviewForm').submit();
        }
    }   
}

function hideFileError(){
	 $("#fileError").hide();
}

 function closeBookingPopUp(){
	$("label.error").hide();
	$("#bookingpopup").hide();
}
 
 function openSeeMore(tutorProfileId){
	 $(window).scrollTop(0);
		
		var url='<%=request.getContextPath()%>/student/viewTutorProfileDetails';
		$.ajax({
			url:url,
			method:'GET',
			async :true,
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
	                            ' <td>:</td><td><div style="max-height:130px;overflow:auto">'+ response.modelMap.dtoTutorRegistrationDetail.about +'</div></td></tr>';
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
	//alert(currentTime);
	
/*  	$('*[name=bookingDate]').appendDtpicker({
 		"closeOnSelected": true,
 		"minuteInterval": 15,
 		//"futureOnly": true,
 		//"showTimezone": true,
 		//"timezone": "+0200",
        'minTime': currentTime,
 		'minDate': new Date(),
 		
 		"dateFormat": "yyyy-MM-DD hh:mm"
 	}); */
	
	 
	 $.validator.addMethod('selectcheck', function (value) {
	        return (value != '0');
	    }, '<spring:message code="student.select"></spring:message>');
	
	$('#bookinForm').validate({
		rules:{
			
			subjectTitleId: {
                selectcheck: true,
                required:true
            },
			/* subjectTypeId: {
                selectcheck: true,
                required:true
            }, */		
            bookingDate:{
				required:true
			},
			 question:{
					required:true
				}
			
			
		
			},

		
		 messages:{
			 
			 subjectTitleId:{
					required:"<spring:message code='student.select'></spring:message>"
					},
		     /* subjectTypeId:{
					required:"<spring:message code='student.select'></spring:message>"
					}, */
			bookingDate:{
					required:"<spring:message code='dateisrequired'></spring:message>"
					},
					question:{
						required:"<spring:message code='questionrequired'></spring:message>"
						}
		} 
		/* submitHandler:function(form){
    		form.submit();
    	} */
	});
	
	
//========== validate Review Form=====================
	$('#reviewForm').validate({
		rules:{
			
			subjectTitleReview: {
                selectcheck: true,
                required:true
            },
				
            reviewDate:{
				required:true
			},
			reviewQuestion:{
					required:true
				}
			},
		
		 messages:{
			 
			 subjectTitleReview:{
					required:"<spring:message code='student.select'></spring:message>"
					},
		    
					reviewDate:{
					required:"<spring:message code='dateisrequired'></spring:message>"
					},
					reviewQuestion:{
						required:"<spring:message code='questionrequired'></spring:message>"
						}
		} 
		
	});
	
	

});
</script>


<script type="text/javascript">
 function getTutorByCriteria(){
	var searchPattern = $("#searchTutor").val().trim();
    if(searchPattern!=""){
	var url='<%=request.getContextPath()%>/student/getFilteredTutorList';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{searchPattern:searchPattern},
		success:function(response){
			var status="0";
			if(response != null){
				var alltutorsData="<ul>";
				var receiverRole="tutor";
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {

						status="1";
						alltutorsData+=   '<li><div class="stud-img-outr"><div class="indi-icon">';
						if(value.login_status=='Y'){
							alltutorsData+=	'<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
						}
						else{
							alltutorsData+= '<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
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
									                            
									                                   
														              	
														             
									     alltutorsData+=   '</div><div class="tutor-img-txt">'+value.name;
									  //   if(value.isFavourite=='N'){
									    	 alltutorsData+= '<span class="heartImg" id="heartImg'+value.userId+'"><img onclick="setAsFavourite('+value.userId+');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>';
	                                //	}
									 //    if(value.isFavourite=='Y'){
									    //	 alltutorsData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.userId+');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>';
	                                	//}
									     
									     alltutorsData+=    '</span><div class="subjects ellipsis ellipsis1" title="'+value.subjectNames+'">'+value.subjectNames+'</div><div class="stars">';
									     
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
			                                    
			     alltutorsData+=   '<div class="info-outr"><div class="ellipsis ellipsis1" title="'+value.about+'">'+value.about+'</div><p class="ellipsis ellipsis1" title="'+value.college+'">'+value.college+'</p>'+
			                        '<p >'+value.career+'</p>'+
                             '<div><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div></div>';
                             
                             alltutorsData+='<div class="book-session chat-Btns">'+
                             '<span id="initiateChat'+value.userId+'"><a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\',\''+receiverRole+'\', \''+value.tutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"  class="greenBtn-normal">'+
                             '<img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a></span>'+
                             '<a  class="orangeBtn-normal" onclick="openMessagePopUp(\''+value.userId+'\', \''+value.name+'\');">'+
                             '<img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>'+
					'<a href="javascript:;" name="bookTutor'+value.userId+'" id="bookTutor'+value.userId+'" onclick="openBookingPopUp('+value.userId+');" class="blueBtn-normal btnBs"><spring:message code="student.booknewsession"></spring:message></a>'+
					'<a href="javascript:;" name="review'+value.userId+'" id="review'+value.userId+'" onclick="openReviewPopUp('+value.userId+');" class="blueBtn-normal btnBs"><spring:message code="review.session"></spring:message></a>'+
					'</div></li>'; 
									              
						
						 });
				 alltutorsData+= '</ul>';
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
 
</script>

<script type="text/javascript">
function getFavoriteAndChatRecord(searchPattern){
	 var receiverRole="tutor";
	 var url='<%=request.getContextPath()%>/student/getFavoriteAndChatRecord';
		$.ajax({
			url:url,
			method:'GET',
			async :true,
			success:function(response){
				if(response!=null){
					if(response.modelMap.dtoFavoriteTutorDetailList != null){
					$.each( response.modelMap.dtoFavoriteTutorDetailList, function(index,value) {
					
						$("#heartImg"+value.favoriteTutorUserId).html('<img src="<%=request.getContextPath()%>/images/heart-red.png"/>');
					});
					}
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
	var url='<%=request.getContextPath()%>/student/getRemainingFilteredTutorList';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{searchPattern:searchPattern},
		success:function(response){
			var status="0";
			if(response != null){
				var alltutorsData="";
				var receiverRole="tutor";
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {

						status="1";
						alltutorsData+=   '<li><div class="stud-img-outr"><div class="indi-icon">';
						if(value.login_status=='Y'){
							alltutorsData+=	'<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
						}
						else{
							alltutorsData+= '<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
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
									                                   
														              	
														             
									     alltutorsData+=   '</div><div class="tutor-img-txt">'+value.name;
									    	 alltutorsData+= '<span class="heartImg" id="heartImg'+value.userId+'"><img onclick="setAsFavourite('+value.userId+');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>';
									     alltutorsData+=    '</span><div class="subjects ellipsis ellipsis1" title="'+value.subjectNames+'">'+value.subjectNames+'</div><div class="stars">';
									     
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
			                                    
			     alltutorsData+=   '<div class="info-outr"><div class="ellipsis ellipsis1" title="'+value.about+'">'+value.about+'</div><p class="ellipsis ellipsis1" title="'+value.college+'">'+value.college+'</p>'+
			                        '<p >'+value.career+'</p>'+
                             '<div><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div></div>';
                             
                             alltutorsData+='<div class="book-session chat-Btns">'+
                             '<span id="initiateChat'+value.userId+'"><a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\',\''+receiverRole+'\', \''+value.tutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"  class="greenBtn-normal">'+
                             '<img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png"><spring:message code="chatWithTutor"></spring:message> </a></span>'+
                             '<a  class="orangeBtn-normal" onclick="openMessagePopUp(\''+value.userId+'\', \''+value.name+'\');">'+
                             '<img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>'+
					'<a href="javascript:;" name="bookTutor'+value.userId+'" id="bookTutor'+value.userId+'" onclick="openBookingPopUp('+value.userId+');" class="blueBtn-normal btnBs"><spring:message code="student.booknewsession"></spring:message></a>'+
					'<a href="javascript:;" name="review'+value.userId+'" id="review'+value.userId+'" onclick="openReviewPopUp('+value.userId+');" class="blueBtn-normal btnBs"><spring:message code="review.session"></spring:message></a>'+
					'</div></li>'; 
									              
						
						 });
				$("#tutorDetails  ul").append(alltutorsData);
				
				}
			
			//==== call another ajax method for Remaining favorite and chat sesison Id==========
			getRemainingFavoriteAndChatRecord();
		}
		
	});  
	
}


 function getRemainingFavoriteAndChatRecord(){
	 var receiverRole="tutor";
	 var url='<%=request.getContextPath()%>/student/getFavoriteAndChatRecord';
		$.ajax({
			url:url,
			method:'GET',
			async :true,
			success:function(response){
				if(response!=null){
					if(response.modelMap.dtoFavoriteTutorDetailList != null){
					$.each( response.modelMap.dtoFavoriteTutorDetailList, function(index,value) {
					
						$("#heartImg"+value.favoriteTutorUserId).html('<img src="<%=request.getContextPath()%>/images/heart-red.png"/>');
					});
					}
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


<script type="text/javascript">
<%-- var listSize = '${fn:length(listBookingDetails)}';
 setInterval(function(){
   
	var url='<%=request.getContextPath()%>/student/getStudentBookingDetailsWithTutor';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			var status="0";
			if(response != null){
				var newlistsize= response.modelMap.listNewBookingDetailsize;
			//	alert("listsize----"+listSize+"----------newlsitsize=---------"+newlistsize);
				if(listSize!=newlistsize){
					listSize=newlistsize;
				var tutorData="<ul>";
				//alert(response.modelMap.dtoBookingDetail.bookingDate);
				var res="";
				 $.each( response.modelMap.listNewBookingDetails, function(index,value) {
						status="1";
						
					     if(value.accepted=="Y" && value.iscompleted=="N")
					    	 {
		     tutorData+=    '<li><div class="bTut-img-outr"><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div><div class="tutor-img">';
                        
		                  if(value.imageName!=''){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/'+value.tutorId+'/fileupload/'+value.tutorId+value.imageName+'"/>';
		                            }	

		                  if(value.imageName==''){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
		                            }

		                  if(value.imageName==null){
		     tutorData+=   '<img alt="" src="'+value.imgUrl+'"/>';
		                            }							
		                                   
							              	
							             
		     tutorData+=   '</div>     <div class="tutor-img-txt">'+value.fullName;
		     
		     
		     if(value.isFavourite=='N'){
		    	 tutorData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.tutorId+');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>';
        	}
		     if(value.isFavourite=='Y'){
		    	 tutorData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.tutorId+');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>';
        	}
		     
		     
		                          tutorData+=  '<div class="subjects">'+value.subjectName+'</div><div class="stars">';
		                 
		                          
		                          
		                          for(var i=1;i<=5;i++){
		                        	  if(i <= value.tutorRating){
		                        		  tutorData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
		                        	  }
		                        	  else
		                        		  {
		                        		  tutorData+=   '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
		                        		  }
		                          }
		                          
		                          tutorData+=   '</div>'+
												'<div class="seeMore"><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div>'+
												'</div></div>'+
								                '<div class="session-schld-outr"><h5><spring:message code="student.sessionschedule"></spring:message></h5>'+
                                               '<input type="text" placeholder="2/8/2015" value="'+value.bookingDate+'"  readonly="readonly"></div>'+				
												
												'<div class="sessionBtns">'+
		              '<a href="javascript:;" class="greenBtn-normal" name="startSession" onclick="goToMeeting('+value.bookingId+');" id="startSession'+value.bookingId+'"><spring:message code="student.gotoclassroom"></spring:message></a>';
		     tutorData+=   '<a href="<%=request.getContextPath()%>/student/sessionManage" class="blueBtn-normal"><spring:message code="student.reviewpastsession"></spring:message> </a></div></li>';
				 }
		     
						 });
				 tutorData+= '</ul>';
				$("#bookedTutorDetails").html(tutorData);
				}
			
			}
		}
	});  
    
    
}, 5000);  --%> 

</script> 

<script type="text/javascript">
function setAsFavourite(tutorId){
	var url='<%=request.getContextPath()%>/student/saveFavouriteTutor';
	$.ajax({
		url:url,
		method:'GET',
		async :true,
		data:{tutorId:tutorId},
		success:function(response){
			if(response=="success"){
			$(window).scrollTop(0);
			$("#responseMessage").html('<spring:message code="favourite.tutor.added"/>');
			$("#favouriteTutor").show();
			}
			else{
				$(window).scrollTop(0);
				$("#responseMessage").html('<spring:message code="already.addedFavourite"/>');
				$("#favouriteTutor").show();
			}
		},
	}); 
}

function closeFavouritePopUp(){
	$("#favouriteTutor").hide();
	location.reload();
}

function closebookSuccessPopUp(){
	$("#bookSuccess").hide();
}

function closebookFailurePopUp(){
	$("#bookFailure").hide();
}

function closeReviewSuccessPopUp(){
	$("#reviewSuccess").hide();
}

function closeReviewFailurePopUp(){
	$("#reviewFailure").hide();
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
	
	$(".date-input").datetimepicker(
        {
	addSliderAccess: true,
	sliderAccessArgs: { touchonly: false }
});
});

<%-- setInterval(function(){
	   
	var url='<%=request.getContextPath()%>/tutorLoginStatus';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null){
				
				$.each( response.modelMap.tutorLoginstatus, function( key , value ) {
					 
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
}, 2000); --%>

var url='<%=request.getContextPath()%>/getTutorsLoginStatus';
var eventSource = new EventSource(url);
eventSource.addEventListener('online', function(event) {
	//alert("online");
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



<!-- //**************************** script for sidebar chat******************************* -->

<script type="text/javascript">

// setTimeout(function(){ 
	var receiverRole="tutor";
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
	      //  ab +=   '<a href="javascript:register_popup('+tutorvalue.userId+', '+tutorvalue.name+');">';  
	        ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\',\''+receiverRole+'\', \''+tutorvalue.tutorProfileId+'\',\'0\');">'; 
	        
	        
	        
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
 ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\',\''+receiverRole+'\', \''+tutorvalue.tutorProfileId+'\',\'0\');">'; 
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
		//	}, 5000); 
</script>

<!-- //**************************** script for sidebar chat ends******************************* -->



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
    	var url='<%=request.getContextPath()%>/student/saveUserIP';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :true,
    		data:{userIP:userIP},
    		success:function(response){
    			    			
    		},
    	});
    	
        //alert(response.ip);
    } 
    
</script>
<script type="text/javascript">
<%--  var chatlistSize = '${dtoTutorDetailsSize}';
    setInterval(function(){
   	var url='<%=request.getContextPath()%>/student/getStudentChatSessionDetails'; 
   	$.ajax({
   		url:url,
   		method:'GET',
   		async :false,
   		success:function(response){
   		if(response != null  && response.modelMap.dtoTutorDetails!=null){
   			 	var newlistsize= response.modelMap.listNewDtoTutorDetailsize;
   			 var TutorData='<ul>';
   				if(chatlistSize!=newlistsize){
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
                                  value.fullName+
                                 '</div><div class="book-session"  style="float:right">'+
                                 '<a href="javascript:register_popup(\''+value.tutorUserId+'\', \''+value.fullName+'\', \'tutor\', \''+value.tutorProfileId+'\',  \''+value.chatSessionId+'\');" name="chatTutor'+value.tutorUserId+'" id="chatTutor'+value.tutorUserId+'" class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';
                  TutorData += '</div></div></li>'; 
                   	   			   
   						 });
   				 TutorData += '</ul>';
   				$("#chatTutorDetails").html(TutorData);
   				if(chatlistSize<newlistsize){
   				$.playSound(soundurl);
   				}
   				chatlistSize=newlistsize;
   				}
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
       
       
    }, 10000); --%>
    
   function getTutorDate(){
    	var studentSessionDate=$("#bookingDate").val();
    	var studenReviewtDate=$("#reviewDate").val();
    	
    	var studentDate="";
    	if(studentSessionDate!=""){
    		studentDate=studentSessionDate;
    	}
    	
    	if(studenReviewtDate!=""){
    		studentDate=studenReviewtDate;
    	}
    	
    	var tutorId=$("#tutorId").val();
    	
    	var url='<%=request.getContextPath()%>/student/getTutorTimeZoneDate';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :true,
    		data:{studentDate:studentDate,tutorId:tutorId},
    		success:function(response){
    			if(studentSessionDate!=""){
    				$("#tutorBookingDate").text(response);
    	    	}
    	    	
    	    	if(studenReviewtDate!=""){
    	    		$("#reviewDateTutor").text(response);
    	    	}
    			
    			
    		},
    	});
    	
    	
    	
    } 
   
  
    
</script>
</html>
