<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
 <!DOCTYPE HTML>
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
		        ui.tooltip.css("max-height", "200px");
		        ui.tooltip.css("overflow", "auto");
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
            var total_popups = 1;
            
            //arrays of popups ids
            var popups = [];
        
            //this is used to close a popup
            function close_popup(id)
            {
            	//alert(id+"------popups.length========"+popups.length);
            	$("#"+id).css("display", "none");
            	/* alert("popups.length====="+popups.length);
                 for(var iii = 0; iii < popups.length; iii++)
                {
                	 
                	 
                     if(id == popups[iii])
                    {
                    //	alert("closing"+iii);
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
            function register_popup(id, name)
            {
            	var chatSatus="N";
            	if($("#"+id).length!=0){
            		$("#"+id).css("display", "block"); 
            	} 
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
				'<div class="chat-inner" id="chatin'+id+'"><div id="position:relative;">'+ 
	               ' </div> <div id="position:relative;"></div></div><div class="chat-msg">'+ 
	               ' <div class="chat-msg-txt"><input type="text" class="trying" onkeypress="enterMsg();" id="chatMsg'+id+'"  placeholder="<spring:message code="type.something" /> " >'+ 
	               ' <a href="javascript:;" class="chat-msg-submit" onclick="sendMsg();" ><spring:message code="parent.submit" /></a> </div>'+ 
	                '<div class="clr"></div> </div></div></div></div>';
                
                chatSatus="Y";
                	}
                	
                	document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element; 
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
            
        </script>


<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

<%-- <script type="text/javascript">


function sendMsg(fixit){ 	
    var text = $('#chatMsg'+fixit).val();
  // // console.log("Text:", text);
   
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
			
		}
		
	});
		
}
</script> --%>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>





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
            <div style="width: 100%; max-height: 660px; overflow: auto;">
                	<ul>
                	
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${listDtoTutorRegistrationsFiltered}" var="listDtoTutorRegistrations">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" >
                            	
                            	
                            	  <c:if test="${listDtoTutorRegistrations.login_status eq 'Y'}">
                  <img class="status${listDtoTutorRegistrations.userId} defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                  </c:if>
                  
                  <c:if test="${listDtoTutorRegistrations.login_status eq 'N'}">
                   <img class="status${listDtoTutorRegistrations.userId} defaultStatus" style="float:left; margin-top:7px;width:9px; height:9px;margin-left: -1px;margin-right: 3px;" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                  </c:if>
                            	
                            	<%-- <img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""> --%>
                            	
                            	</div>
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
                                <div class="ellipsis" title="${listDtoTutorRegistrations.about}">${listDtoTutorRegistrations.about}</div>
                                <p class="ellipsis" title="${listDtoTutorRegistrations.college}">${listDtoTutorRegistrations.college}</p>
                                <p >${listDtoTutorRegistrations.career}</p>
                                <div><a onclick="openSeeMore('${listDtoTutorRegistrations.tutorProfileId}');"><spring:message code="student.seemore"/></a></div> 
                            </div>
                            
                             <div class="book-session chat-BtnsParent">
                              <a href="javascript:register_popup('${listDtoTutorRegistrations.userId}', '${listDtoTutorRegistrations.name}');"
                               name="chatTutor${listDtoTutorRegistrations.userId}" id="chatTutor${listDtoTutorRegistrations.userId}"  
                              class="greenBtn-normal">
                              <img class="chat" src="<%=request.getContextPath()%>/images/ico-chat.png">
                              <spring:message code="chatWithTutor"></spring:message> </a>
                              <a  class="orangeBtn-normal" onclick="sendMsg();">
                              <img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>
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

</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerOuter.jsp" %>

        
  
        
        
        
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
        
  
  
    <div class="form-tutor-popup" id="signupRequired" style="display: none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="signupFirst"/>
            </div>
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/signup" class="greenBtn-normal"> <spring:message code="header.signup"></spring:message> </a>
               <a href="<%=request.getContextPath()%>/login" class="greenBtn-normal"> <spring:message code="header.login"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div> 
       
        
</body>



<script type="text/javascript">

 
 function openSeeMore(tutorProfileId){
	 $(window).scrollTop(0);
		
		var url='<%=request.getContextPath()%>/viewTutorDetails';
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
 function getTutorByCriteria(){
	
	var homeSearchTutor = $("#searchTutor").val().trim();
    if(homeSearchTutor!=""){
	var url='<%=request.getContextPath()%>/searchFilteredTutors';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{homeSearchTutor:homeSearchTutor},
		success:function(response){
			var status="0";
			if(response != null){
				var alltutorsData="<ul>";
				
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {

						status="1";
						alltutorsData+=   '<li><div class="stud-img-outr"><div class="indi-icon">';
						
						if(value.login_status=='Y'){
							alltutorsData+=	'<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
						}
						else{
							alltutorsData+= '<img  class="status'+value.userId+' defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
						}
						
						alltutorsData+=	'</div><div class="tutor-img" id="immg'+value.userId+'">';
							                         
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
									     
									     
									                          alltutorsData+=   '</div></div></div>';
									                                    
									     alltutorsData+=   '<div class="info-outr"><div class="ellipsis" title="'+value.about+'">'+value.about+'</div><p class="ellipsis" title="'+value.college+'">'+value.college+'</p>'+
									                        '<p >'+value.career+'</p>'+
			                                '<div><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div></div>'+
			                                '<div class="book-session chat-BtnsParent">'+											
			                                '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>'+
			                                 '<a  class="orangeBtn-normal" onclick="sendMsg();"><img src="<%=request.getContextPath()%>/images/mesg-icon.png"> <spring:message code="message"></spring:message> </a>'+
			                                '</div></li>'; 
									              
						
						 });
				 alltutorsData+= '</ul>';
				$("#tutorDetails").html(alltutorsData);
				}
			if(status=="0"){
				$("#tutorDetails").html('<spring:message code="norecord.found"/>');
			}
			
			
		}
		
	});  
    }
    if(homeSearchTutor==""){
    	location.reload();
    }
	
}

 
 function sendMsg(){
	 $(window).scrollTop(0);
	 $("#signupRequired").show();
 }
 
 function closePopUp(){
		$("#signupRequired").hide();
	}
 
 function enterMsg(){
		if (event.keyCode == 13) {
	   	 $("#signupRequired").show();
	   }
	}
 
 
<%--  setInterval(function(){
	   
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
		eventSource.onmessage = function(event) {
			//document.getElementById('foo').innerHTML = event.data;
			//alert(event.data.login_status);
			//alert(event.data);
			
			var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
				$(".defaultStatus").attr("src",offline);
				
			$.each(event.data.split(",").slice(0,-1), function(index, item) {
				var online='<%=request.getContextPath()%>/images/green-bullet.png';
				$(".status"+item).attr("src",online); 
			});
			
				 
			
				 
			
			
		};
	
 
 
</script>


</html>
