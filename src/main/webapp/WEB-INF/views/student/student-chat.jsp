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



<link href="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.js"></script>
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






<!-- ================================================================================cccchat==================== -->
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
            
            .popup-box
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
            


        </style>
        
        <script type="text/javascript">
            //this function can remove a array element.
            Array.remove = function(array, from, to) {
                var rest = array.slice((to || from) + 1 || array.length);
                array.length = from < 0 ? array.length + from : from;
                return array.push.apply(array, rest);
            };
        
            //this variable represents the total number of popups can be displayed according to the viewport width
            var total_popups = 0;
            
            //arrays of popups ids
            var popups = [];
        
            //this is used to close a popup
            function close_popup(id)
            {
            	//alert(id+"------popups.length========"+popups.length);
            /* 	$("#"+id).css("display", "none");
            	
            	var chkPop = popups.length;
            	if(popups.length>4){
            		chkPop=1;
            	}
            	if(chkPop==1){
            		right = 220;
            	}
            	else if(chkPop==2){
            		right = right - 320;
            	}
            	else if(chkPop==3){
            		right = right - 320;
            	}
            	else if(chkPop==4){
            		right = right - 320;
            	} */
            	
            	
            	 
                 for(var iii = 0; iii < popups.length; iii++)
                {
                	 
                	 
                     if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                        
                      //  document.getElementById(id).style.display = "none";
                        
                    //    document.getElementById(id).remove();
                        $(".chat-popup"+id).remove();
                    //    $("#"+id).css("display", "none");
                        calculate_popups();
                        
                        return;
                    } 
                }     
            }
        
            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            var right = 220;
            var resetPostion=0;
            function display_popups()
            {
            	 
            	if(resetPostion==4){
            		right = 220;
            		resetPostion=0;
                }
            	
            	
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
            
            function register_popup(id, name)
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
                var element = '<div class="popup-box chat-popup'+id+'" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages">';
                
                element = element + ' <div class="db-chats-txt">'+ 
				'<div class="chat-inner chatin'+studentId+'-'+id+'" id="chatin'+studentId+'-'+id+'"><div id="position:relative;">'+ 
	               ' </div> <div id="position:relative;"></div></div><div class="chat-msg">'+ 
	               ' <div class="chat-msg-txt"><input type="text" class="trying" id="chatMsg'+studentId+'-'+id+'"  placeholder="<spring:message code="type.something" /> " >'+ 
	               ' <a href="javascript:;" class="chat-msg-submit" onclick="sendMsg('+studentId+'-'+id+');" ><spring:message code="parent.submit" /></a> </div>'+ 
	                '<div class="clr"></div> </div></div></div></div>';
                
                chatSatus="Y";
                document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element; 
                	}
                	
                	
                	popups.unshift(id);
                
                	if($("#"+id).length!=0){
                		calculate_popups();
                	} 
                	
                	openchatwind(id,chatSatus);
                
            }
            
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
                if(width < 540)
                {
                    total_popups = 0;
                }
                else
                {
                    width = width - 200;
                    //320 is width of a single popup box
                    total_popups = parseInt(width/320);
                }
                
                display_popups();
                
            }
            
            //recalculate when window is loaded and also when window is resized.
       //     window.addEventListener("resize", calculate_popups);
       //     window.addEventListener("load", calculate_popups);
            
        </script>


<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
<script type="text/javascript">


function sendMsg(fixit){ 	
    var text = $('#chatMsg'+fixit).val();
  // //// console.log("Text:", text);
   
    if(text!=null){
  	  
  	  
  	  window['newUserRef' + tutorId ].push({name: "tutor", text: text});
    $('#chatMsg'+fixit).val('');
    
    
    }
  }


var studentId1="0";
var tutorId = "0";
var stId='${studentId}';
//var posfix = stId+"-";
var newUserRef ="";
function openchatwind(tutorId,chatSatus){
	studentId1='${studentId}';
	$("#justtest").val(tutorId);
	var url='<%=request.getContextPath()%>/student/requestTutorChat';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{tutorId:tutorId},
		success:function(response){
			
			
		},
		complete:function(){
		
		var posfix =studentId1+"-"+tutorId;
		
		
		
		if(chatSatus=='Y'){
			window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		
		
	//	 window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		 
		 window['newUserRef' + tutorId ].on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      var stuId = '${studentId}';
	   //   if(stuId=='45'){
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
	  	
	<%-- 	$('#chatMsg'+postFix).keypress(function (e) {
	        if (e.keyCode == 13) {
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
	      }); --%>
		
	      
	      
	      $('.trying').keypress(function (e) {
	    	  
	    	  
		        if (e.keyCode == 13) {
		          var name = '${userFname}';
		          var vvv = $(e.target).attr("id");
		          var ccccc = vvv.substring(7, vvv.indexOf('-'));
		          var dddd = vvv.substring(vvv.indexOf('-')+1,vvv.length);
		          
		          
		    	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ccccc +':'+dddd);
		         
		    	  var nowDate = new Date();
			      var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
			      var text = messageTime+"]: "+$('#'+vvv).val();
		    	  
		    	 // var text = $('#'+vvv).val();
		         window['newUserRef' + tutorId ].push({name: name, text: text});
		       
		          $('#'+vvv).val('');  
		          
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
	      
		
	    function displayChatMessage(name, text) {
	        $('<div/>').text(text).prepend($('<em/>').text(name+' [')).appendTo($('.chatin'+postFix));
	        $('.chatin'+postFix)[0].scrollTop = $('.chatin'+postFix)[0].scrollHeight;
	      };
	       

	   }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
		if(chatSatus=='N'){
			//window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		
		
	//	 window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
		 
		 window['newUserRef' + tutorId ].on('child_added', function(DataSnapshot) {
	    	  // console.log("Name of snapshot is",DataSnapshot.key().toString());
	        var message = DataSnapshot.val();
	        displayChatMessage(message.name, message.text);

	      });
		
		
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      
	      var stuId = '${studentId}';
	   //   if(stuId=='45'){
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
	  	
	<%-- 	$('#chatMsg'+postFix).keypress(function (e) {
	        if (e.keyCode == 13) {
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
	      }); --%>
		
	      
	      
	      $('.trying').keypress(function (e) {
	    	  
	    	  
		        if (e.keyCode == 13) {
		          var name = '${userFname}';
		          var vvv = $(e.target).attr("id");
		          var ccccc = vvv.substring(7, vvv.indexOf('-'));
		          var dddd = vvv.substring(vvv.indexOf('-')+1,vvv.length);
		    	  window['newUserRef' + tutorId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ccccc +':'+dddd);
		        //  var text = $('#'+vvv).val();
		          
		          var nowDate = new Date();
			      var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
			      var text = messageTime+"]: "+$('#'+vvv).val();
		          
		         window['newUserRef' + tutorId ].push({name: name, text: text});
		       
		          $('#'+vvv).val('');  
		          
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
	      
		
	    function displayChatMessage(name, text) {
	        $('<div/>').text(text).prepend($('<em/>').text(name+' [')).appendTo($('.chatin'+postFix));
	        $('.chatin'+postFix)[0].scrollTop = $('.chatin'+postFix)[0].scrollHeight;
	      };
	       

	   }	   
	   
	   
		
	}  ////complete ends
		
		
	//}, ////complete ends
	
	}); // ajax ends
}

/* function setItDown(studentId1,tutorId){
	
	newUserRef=new Firebase('https://aloprofe.firebaseio.com/users/'+ studentId1 +':'+ tutorId);
     
     var posfix = studentId1+"-"+tutorId;
     var postFix = posfix;
 	
	$('#chatMsg'+postFix).keypress(function (e) {
       if (e.keyCode == 13) {
         var name = '${userFname}';
         var text = $('#chatMsg'+postFix).val();
           
        newUserRef.push({name: name, text: text});
      
         $('#chatMsg'+postFix).val('');  
         
         
         
       
       }
     });
	
	
} */
	 
    
     

</script>
<!-- ======================= -->

<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>




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
                	
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${listDtoTutorRegistrations}" var="listDtoTutorRegistrations">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" ><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                                <div class="tutor-img" id="immg${listDtoTutorRegistrations.userId}">
                                <c:if test="${listDtoTutorRegistrations.imageName !=null}">
					              	<img alt="" src="<%=request.getContextPath()%>/profilePictures/${listDtoTutorRegistrations.userId}/fileupload/${listDtoTutorRegistrations.userId}${listDtoTutorRegistrations.imageName}"/>
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
                                	<c:if test="${listDtoTutorRegistrations.isFavourite eq 'N'}">
                                	 <span class="heartImg"><img onclick="setAsFavourite('${listDtoTutorRegistrations.userId}');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>
                                	</c:if>
                                	<c:if test="${listDtoTutorRegistrations.isFavourite eq 'Y'}">
                                	 <span class="heartImg" ><img onclick="setAsFavourite('${listDtoTutorRegistrations.userId}');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>
                                	</c:if>
                                	
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
                                	<a href="javascript:register_popup('${listDtoTutorRegistrations.userId}', '${listDtoTutorRegistrations.name}');" name="chatTutor${listDtoTutorRegistrations.userId}" id="chatTutor${listDtoTutorRegistrations.userId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                                
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
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                        
                    </ul>
                    </div>
                </div>
            </div>
            
           <input type="hidden" id="justtest" name="justtest" value="0"/> 
         
             
             
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
            <form:form id="bookinForm" name="bookinForm" commandName="dtoBookingDetail" method="POST">
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
                    <th><spring:message code="booking.date"/></th>
                    <td>:</td> 
                    <td><div > <form:input type="text" path="bookingDate" class="date-input" placeholder="2/8/2015" readonly="true"  id="bookingDate" name="bookingDate" /></div></td>
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
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="book.session"></spring:message>' onclick="submitBookingForm();" ></td>
                  </tr>
                  
            </tbody></table>
                </div>
            </form:form>
        </div>
   </div>
</div> 
<!-- End Booking Pop Up -->        
  
  
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
        
    <c:if test="${bookingSuccess eq 'Y'}">   
  <div class="form-tutor-popup" id="bookSuccess">
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
        <%session.removeAttribute("bookingSuccess");%>       
 </c:if>        
        
</body>

<script type="text/javascript">
function goToMeeting(bookingId){
	 $(window).scrollTop(0);
		var url='<%=request.getContextPath()%>/checkSessionStartTime';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{bookingId:bookingId},
			success:function(response){
				if(response==true){
					$("#meetingId").val(bookingId);
					$("#meetingForm").submit();	
				}
				else{
					
					$("#editpopup").show();
				}
			}
		});
	}
</script>

<script type="text/javascript">

function openBookingPopUp(tutorId)
{
	$(window).scrollTop(0);
	var tutorId=tutorId;
	
	$("#tutorId").val(tutorId);

	var url='<%=request.getContextPath()%>/student/getTutorSubjectList';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
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


function getSubjectType(subjectTitleId)
{
	
	var tutorId=$("#tutorId").val();
	var subjectitleId=subjectTitleId.value;
	
	
	var url='<%=request.getContextPath()%>/student/getTutorSubjectTypeList';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{subjectitleId:subjectitleId,tutorId:tutorId},
		success:function(response){
			 $('#subjectType').children().remove();
			 $.each( response.modelMap.allSubjectTypeList, function( key , value ) {
				 
			$('#subjectType').append('<option value="'+key+'">'+value+'</option>');
			 }); 
			
	
		}
		
	}); 
	
}

function submitBookingForm(){

	<%-- $("#bookinForm").attr("action", "<%=request.getContextPath()%>/student/bookingTutor");
	$("#bookinForm").submit(); --%>
	
	if ($('#bookinForm').valid()) {
        var subjectTypeId =  $("#subjectType").val();
        var subjectTitleId = $("#subjectTitle").val();
        var bookingDate =    $("#bookingDate").val();
        var bookingDuration =$("#bookingDuration").val();
        var tutorId = $("#tutorId").val();
        
    	var url='<%=request.getContextPath()%>/student/studentBookingTutor';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :false,
    		data:{tutorId:tutorId,subjectTypeId:subjectTypeId,subjectTitleId:subjectTitleId,bookingDate:bookingDate,bookingDuration:bookingDuration},
    		success:function(response){
    			if(response.modelMap.sessionBooked=="Y"){
    			$(window).scrollTop(0);
    			$("#bookingpopup").hide();
    			$("#bookSuccess").show();
    			}
    			
    		},
    	}); 
    } 
	
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
			async :false,
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

								if(response.modelMap.dtoTutorRegistrationDetail.imageName==null && value.imgUrl==null){
								tutorDetails+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
										                }

								if(response.modelMap.dtoTutorRegistrationDetail.imageName==null && value.imgUrl!=null){
								 tutorDetails+=   '<img alt="" src="'+value.imgUrl+'"/></div>';				
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
					}
		}
		/* submitHandler:function(form){
    		form.submit();
    	} */
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
		async :false,
		data:{searchPattern:searchPattern},
		success:function(response){
			var status="0";
			if(response != null){
				var alltutorsData='<div style="width: 100%; height: 400px; overflow: auto;"><ul>';
				
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {

						status="1";
						alltutorsData+=   '<li><div class="stud-img-outr"><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div><div class="tutor-img" id="immg'+value.userId+'">';
							                         
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
									     if(value.isFavourite=='N'){
									    	 alltutorsData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.userId+');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>';
	                                	}
									     if(value.isFavourite=='Y'){
									    	 alltutorsData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.userId+');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>';
	                                	}
									     
									     alltutorsData+=    '</span><div class="subjects ellipsis" title="'+value.subjectNames+'">'+value.subjectNames+'</div><div class="stars">';
									     
									     for(var i=1;i<=5;i++){
				                        	  if(i <= value.tutorRating){
				                        		  alltutorsData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
				                        	  }
				                        	  else
				                        		  {
				                        		  alltutorsData+=   '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
				                        		  }
				                          }
									     
									     
									              alltutorsData+=   '</div></div><div class="book-session">'+
						                                            '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>'+
				                                                    '</div></div>';
									                                    
									     alltutorsData+=   '<div class="info-outr"><div class="ellipsis" title="'+value.about+'">'+value.about+'</div><p class="ellipsis" title="'+value.college+'">'+value.college+'</p>'+
									                        '<p >'+value.career+'</p>'+
			                                '<div><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div></li>'; 
									              
						
						 });
				 alltutorsData+= '</ul></div>';
				$("#tutorDetails").html(alltutorsData);
				}
			if(status=="0"){
				$("#tutorDetails").html('<spring:message code="norecord.found"/>');
			}
			
			
		}
		
	});  
    }
    if(searchPattern==""){
    	location.reload();
    }
	
}

</script>


<script type="text/javascript">
function setAsFavourite(tutorId){
	var url='<%=request.getContextPath()%>/student/saveFavouriteTutor';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
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


</script>

</html>
