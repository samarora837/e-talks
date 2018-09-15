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
      background-color: #C4C4C4;  
}

.me::before {
    box-shadow: -2px 2px 2px 0 rgba( 178, 178, 178, .4 );
    left: -9px;       
    background-color: #C4C4C4;     
}

.you {
    float: right;    
    margin: 5px 20px 5px 45px;         
}

.you::before {
    box-shadow: 2px -2px 2px 0 rgba( 178, 178, 178, .4 );
    right: -9px;    
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
	               ' <div class="chat-msg-txt"><input type="text"  class="trying" id="chatMsg'+id+'-'+tutorUserId+'" placeholder="<spring:message code="type.something" /> " >'+ 
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
       //     window.addEventListener("resize", calculate_popups);
       //     window.addEventListener("load", calculate_popups);
            
        </script>



 <!-- //=================firebase========================= -->
<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
<script type="text/javascript">


function endChatSession(chatSessionId){
	 var url='<%=request.getContextPath()%>/tutor/EndChatSession';
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
	    	  
		        if (e.keyCode == 13) {
		          var name = '${userFname}';
		          var vvv = $(e.target).attr("id");
		          var ccccc = vvv.substring(7, vvv.indexOf('-'));
		          var dddd = vvv.substring(vvv.indexOf('-')+1,vvv.length);
		    	  window['newUserRef' + studentId1 ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ccccc +':'+dddd);
		          var text = $('#'+vvv).val();
		         window['newUserRef' + studentId1 ].push({name: name, text: text});
		       
		          $('#'+vvv).val('');  
		        
		        }
		      });
	      
		
	    function displayChatMessage(name, text) {
	        $('<div/>').text(text).prepend($('<em/>').text(name+': ')).appendTo($('#chatin'+postFix));
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
	  	
	<%-- 	$('#chatMsg'+postFix).keypress(function (e) {
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
	      }); --%>
		
	      
	      
	      $('.trying').keypress(function (e) {
	    	  
	    	  
		        if (e.keyCode == 13) {
		          var name = '${userFname}';
		          var vvv = $(e.target).attr("id");
		          var ccccc = vvv.substring(7, vvv.indexOf('-'));
		          var dddd = vvv.substring(vvv.indexOf('-')+1,vvv.length);
		    	  window['newUserRef' + studentId1 ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ccccc +':'+dddd);
		          var text = $('#'+vvv).val();
		         window['newUserRef' + studentId1 ].push({name: name, text: text});
		       
		          $('#'+vvv).val('');  
		          
		        }
		      });
	      
		
	    function displayChatMessage(name, text) {
	    	if(name=='${userFname}'){
	        $('<div class="bubble me" />').text(text).prepend($('<em/>').text(name+': ')).appendTo($('#chatin'+postFix));
	        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;
	    	}
	    	else{
	    		$('<div class="bubble you" />').text(text).prepend($('<em/>').text(name+': ')).appendTo($('#chatin'+postFix));
		        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;	
	    	}
	      };
	       

	   }	   
	   
		

}

</script>
<!-- ======================= -->



<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/tutor/headerTutor.jsp" %>



  <!--Mid Section-->
  <section class="container">
  
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
            <div class="stu_outr">
            <div class="stu_lt">
            <h1 class="text-left">Chat with Students</h1>
            <div class="stu-index-txt"  id="studentDetails">
            <div style="width: 100%; height: 400px; overflow: auto;">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoStudentDetails}" var="dtoStudentDetails">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" ><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                                <div class="tutor-img-txt">
                                ${dtoStudentDetails.firstName}
                               
                                </div>
                                <div class="book-session">
                                	<a href="javascript:register_popup('${dtoStudentDetails.userId}', '${dtoStudentDetails.firstName}', '${dtoStudentDetails.chatSessionId}');" name="chatTutor${dtoStudentDetails.userId}" id="chatTutor${dtoStudentDetails.userId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
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
        
        
</body>



<script type="text/javascript">
var listSize = '${fn:length(dtoStudentDetails)}';
 setInterval(function(){
  // alert("called=------------");
   
	var url='<%=request.getContextPath()%>/tutor/getTutorChatSessionDetails';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null){
				var newlistsize= response.modelMap.listNewDtoStudentDetailsize;
				
				if(listSize!=newlistsize){
					listSize=newlistsize;
				var studentData='<div style="width: 100%; height: 400px; overflow: auto;"><ul>';
				 $.each( response.modelMap.dtoStudentDetails, function(index,value) {
			   
			   studentData += '<li>'+
                	          '<div class="stud-img-outr">'+
                        	  '<div class="indi-icon" ><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>'+
                              '<div class="tutor-img-txt">'+
                               value.firstName+
                              '</div><div class="book-session">'+
                              '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.firstName+'\', \''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'" class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';
               studentData += '</div></div></li>';
                    
                	   			   
						 });
				 studentData += '</ul></div>';
				$("#studentDetails").html(studentData);
				}
			
			}
			if(response.modelMap.listNewDtoStudentDetailsize==0){
				var stuData='<div style="width: 100%; height: 400px; overflow: auto;"><ul><h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3></ul></div>';
				$("#studentDetails").html(stuData);
			}
		}
	});  
    
    
 }, 5000);  

</script>





</html>
