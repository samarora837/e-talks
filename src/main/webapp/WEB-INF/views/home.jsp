<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
response.setHeader("Cache-Control","max-age=3600"); //no-cache Forces caches to obtain a new copy of the page from the origin server
response.setHeader("Cache-Control","max-age=3600"); //no-store Directs caches not to store the page under any circumstance
//response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
//response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
%>
<!DOCTYPE HTML>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

<title><spring:message code="home.title"></spring:message></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
 <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,400italic,300' rel='stylesheet' type='text/css'>    
      <script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js" type="text/javascript" ></script>
        
 <style type="text/css">
@media only screen and (max-width : 540px) {
.chat-sidebar {display: none !important;}
.chat-popup {display: none !important;}
}
body {background-color: #e9eaed;}
.popup-box {	display: none;position: fixed;bottom: 0px;right: 220px;height: 285px;background-color: rgb(237, 239, 244);width: 300px;border: 1px solid rgba(29, 49, 91, .3);
}
.popup-box .popup-head {background-color: #0060A8;padding: 5px;color: white;font-weight: bold;font-size: 14px;
	clear: both;}
.popup-box .popup-head .popup-head-left {float: left;}
.popup-box .popup-head .popup-head-right {float: right;opacity: 0.5;}
.popup-box .popup-head .popup-head-right a {text-decoration: none;color: inherit;}
.popup-box .popup-messages {height: 100%;overflow-y: scroll;overflow-x: hidden;}



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





<script type="text/javascript">

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
                for(var iii = 0; iii < popups.length; iii++)
                {
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                        
                        document.getElementById(id).style.display = "none";
                        
                        calculate_popups();
                        
                        return;
                    }
                }   
            }
        
            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            function display_popups()
            {
                var right = 220;
                
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + 320;
                        element.style.display = "block";
                    }
                }
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            //creates markup for a new popup. Adds the id to popups array.
            function register_popup(id, name)
            {
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
                
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="chat-seeMore"><a onclick="openSeeMore(\''+ id +'\');"><spring:message code="student.seemore"/></a></div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages">';
				
				element = element + ' <div class="db-chats-txt" style="border:none;">'+ 
				'<div class="chat-inner" style="height:204px;" id="chatin'+id+'"><div id="position:relative;">'+ 
	               ' </div> <div id="position:relative;"></div></div><div class="chat-msg" style="padding:5px 0 0;">'+ 
	               ' <div class="chat-msg-txt"><input type="text" class="trying" onkeypress="enterMsg();" id="chatMsg'+id+'"  placeholder="<spring:message code="type.something" /> " >'+ 
	               ' <a href="javascript:;" class="chat-msg-submit" onclick="sendMsg();"  ><spring:message code="parent.submit" /></a> </div>'+ 
	                '<div class="clr"></div> </div></div></div></div>';
				
				
				
                
                document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
        
                popups.unshift(id);
                        
                calculate_popups();
                
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
            
            
            
            
            
            
            
            
            
            
            
            function showNews(){
            	var newsHtml ='<h3><spring:message code="home.news"/></h3>';
            	var url='<%=request.getContextPath()%>/newsList';
            	$.ajax({
            		url:url,
            		method:'GET',
            		async :false,
            		success:function(response){
            			if(response.length>0){
            				var i=0;
		            		$.each(response,function(index,value){
		            			if(i<3){
		            				newsHtml = newsHtml+"<div class='news-txt'>"+value.newsText+"</div>";
		            			}
		            			i++;
		            		});
		            		newsHtml = newsHtml+'<div class="news-btn" id="newsReadMore"><a href="<%=request.getContextPath()%>/cms/news"><spring:message code="home.readmore"/> </a></div>';
		            		}
            			
            			}
            	}); 
            	$('#news').html(newsHtml);
            	$('#news').show();
            }
        </script>
        
        <%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
        </head>
<!-- style="padding-right:198px;" -->
        <body  onload="showNews();">
    
    <div class="chat-sidebar" id="chatBar" style="display:none;">
    
    <div class="homeChat-hdr"> <spring:message code="tutors.chat"/>   <a onclick="closeChatBar();" style="float: right;margin-right: 5px; color: white;"><img  style="width:18px; position:relative; top:1px;"  alt="" src="<%=request.getContextPath()%>/images/arrow_down.png"/></a></div>
    <div id="testbar">
    <img  style="margin-top: 100%;height: 100px;width: 100px;margin-left: 46px;"  alt="" src="<%=request.getContextPath()%>/images/timerLoader.gif"/>
    </div>
   <%-- <div class="homeChat-hdr"> <spring:message code="tutors.chat"/>  <a onclick="closeChatBar();" style="float: right;margin-right: 5px; color: white;">▼</a></div>
    
    <c:forEach var="subjectList" items="${subjectMasterName}">
    
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
                <a href="javascript:register_popup('${tutorDetails.userId}', '${tutorDetails.name}');">
                  
                  <c:if test="${tutorDetails.login_status eq 'Y'}">
                  <img class="status${tutorDetails.userId} defaultStatus" style="float:left; margin-top:7px; width:9px; height:9px; margin-left: -1px; margin-right: 3px;" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                  </c:if>
                  
                  <c:if test="${tutorDetails.login_status eq 'N'}">
                   <img class="status${tutorDetails.userId} defaultStatus" style="float:left; margin-top:7px;width:9px; height:9px;margin-left: -1px;margin-right: 3px;" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
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
    <a onclick="showMoreRecord('${subjectList.subject_Type_Master_Id}')" id="show${subjectList.subject_Type_Master_Id}" value="showMore" class="chat-showMore"> <spring:message code="show.more"></spring:message> </a>
    </div>
    </c:if>
       </c:forEach> --%>
        </div> 
 <!--   =========================== side bar chat end=============================================  -->      
        
<!--Chat Esection End here-->
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>
          <!--Banner-->${content}
<%--   <section class="banner-outr">
    <div class="inner-row">
      <div class="banner-txt">
        <div class="banner-txt-rt">
          <h2> <spring:message code="home.upgradenumber"></spring:message> <br>
            <spring:message code="home.connect"></spring:message> </h2>
          <form action="<%=request.getContextPath()%>/searchTutors" method="POST" id="searchTutors">
          <div class="banner-search">
            <input type="text" id="homeSearchTutor" name="homeSearchTutor" placeholder='<spring:message code="home.search.placeholder"></spring:message>' >
            <a href="javascript:;" onclick="submitForm();" class="searchBtn"><spring:message code="home.search"></spring:message> </a> </div>
            </form>
        </div>
        <div class="banner-txt-lt">
          <div class="banner-img"><img src="<%=request.getContextPath()%>/images/banner-img.png" alt=""></div>
        </div>
      </div>
    </div>
  </section> --%>
          <!--//Banner--> 
          <!--Mid Section-->
<%--           <section class="container">
    <div class="home-hw-it-works">
      <div class="inner-row">
        <div class="hw-it-works-txt">
          <h2><spring:message code="home.text"></spring:message></h2>
          <ul>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td> <img src="<%=request.getContextPath()%>/images/search-icon.png" alt=""></td></tr>
				</tbody></table>
              </div>
              <h4><spring:message code="home.searchsubject"></spring:message></h4>
              <div><spring:message code="namactortor"></spring:message></div>
            </li>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td> <img src="<%=request.getContextPath()%>/images/tutor-icon.png"  alt=""></td></tr>
				</tbody></table>
              
              
              </div>
              <h4><spring:message code="home.looktutor"></spring:message></h4>
              <div><spring:message code="namactortorfringilla"></spring:message></div>
            </li>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td> <img src="<%=request.getContextPath()%>/images/chat-icon.png"  alt=""></td></tr>
				</tbody></table>
              
              </div>
              <h4><spring:message code="home.chatwithtutor"></spring:message></h4>
              <div><spring:message code="namactortorfringillamassa"></spring:message></div>
            </li>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td>  <img src="<%=request.getContextPath()%>/images/link-icon.png"  alt=""></td></tr>
				</tbody></table>
            
              </div>
              <h4><spring:message code="home.connecttutor"></spring:message></h4>
              <div><spring:message code="namactortorfringillamassainterdum"></spring:message></div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="recom-con">
      <div class="inner-row">
        <div class="recom-con-lt">
          <h3><spring:message code="home.manystudentparent"></spring:message></h3>
          <div class="recom-con-txt">
            <div class="recom-img"><img src="<%=request.getContextPath()%>/images/recom-img1.png" alt=""></div>
            <div class="recom-img-txt"> <spring:message code="naNamactortor"></spring:message>
              <div class="recom-img-name"> <spring:message code="marelynHaynes"></spring:message> <span><spring:message code="student"></spring:message></span> </div>
            </div>
          </div>
          <div class="recom-con-txt">
            <div class="recom-img"><img src="<%=request.getContextPath()%>/images/recom-img2.png" alt=""></div>
            <div class="recom-img-txt"> <spring:message code="naNamactortorfringilla"></spring:message>
              <div class="recom-img-name"> <spring:message code="alexanderphilip"></spring:message>, <span><spring:message code="parent"></spring:message></span> </div>
            </div>
          </div>
        </div>
        <div class="recom-con-news">
          <h3><spring:message code="home.news"></spring:message></h3>
          <div class="news-txt"> <spring:message code="loremIpsum"></spring:message>
            <p><spring:message code="universityofNewYork"></spring:message></p>
          </div>
          <div class="news-txt"> <spring:message code="loremIpsum"></spring:message>
            <p><spring:message code="universityofNewYork"></spring:message></p>
          </div>
          <div class="news-btn"> <a href="javascript:;"><spring:message code="home.readmore"></spring:message> </a> </div>
        </div>
      </div>
    </div>
  </section> --%>
          <!--//Mid Section--> 
        </div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerOuter.jsp" %>

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

 <div class="homeChat-hdr" id="openChatBar" style="display:none;"><spring:message code="tutors.chat"/>  <a onclick="openChatBar();" style="float: right;margin-right: 5px; color: white;"><img  style="width:18px; position:relative; top:1px;"  alt="" src="<%=request.getContextPath()%>/images/arrow_up.png"/></a></div>

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


</body>

<script type="text/javascript">

 $(document).ready(function(){
		$("#openChatBar").show();
		
		// sidebar without delay
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
				        
				        ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\');">';
				        
				        
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
						ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\');">';
			 
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

function submitForm(){
	$("#searchTutors").submit();
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

function showMoreRecord(id){
	
	var buttonAction = $("#show"+id).attr("value");
	
	if(buttonAction=='showMore'){
		$(".subject"+id).show();
		$("#show"+id).html('<spring:message code="show.less"/>');
		$("#show"+id).attr("value","showLess"); 
	}
	if(buttonAction=='showLess'){
		$(".subject"+id).hide();
		$("#show"+id).html('<spring:message code="show.more"/>');
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

</script>

<script type="text/javascript">

<%-- setTimeout(function(){ 
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
	        
	        ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\');">';
	        
	        
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
			ab +=   '<a href="javascript:register_popup(\''+tutorvalue.userId+'\', \''+tutorvalue.name+'\');">';
 
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
			}, 5000); --%>
			
			
function openSeeMore(tutorProfileId){
	
	 $(window).scrollTop(0);
		var url='<%=request.getContextPath()%>/getTutorProfileDetails';
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


</html>
