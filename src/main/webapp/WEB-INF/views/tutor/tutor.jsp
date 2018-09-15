<!DOCTYPE html>
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
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><spring:message code="MiProfeTutorDashboard" /></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
 <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,400italic,300' rel='stylesheet' type='text/css'>
<!--[if lt IE 9]>
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

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/jquery-1.9.1.js"></script> --%>




<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/jquery-ui.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/styleDate.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqueryAjaxValidate.js"></script>


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
            

        </style>
        
  <script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>
  <%@ include file="/js/firebase/tutor-firebase-home-script.jsp" %>
        



<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>
<body onload="getIp();">
<div id="main">
  <%@ include file="/WEB-INF/views/tutor/headerTutor.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="tutor-info-outr">
        
          <div class="tutr-screen">
            <h1 class="text-left"><spring:message code="currentSessionRequests" /></h1>
            <div class="tutor-db-txt" id="tutorbookingDetails" style="height: 290px; overflow: auto; margin-bottom: 15px;">
            
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tbody>
              
              <c:set var="count" value="0" scope="page"/>
              <c:if test="${listBookingDetails ne null }">
                <c:forEach items="${listBookingDetails}" var="listBookingDetails">
              <c:if test="${listBookingDetails.accepted == 'Y' && listBookingDetails.iscompleted == 'N' && listBookingDetails.isDeleted == 'N' && listBookingDetails.gotoMeetingFlag == 'Y' }">
                <tr>
                   <td width="13"><div class="indi-icon">
                   
                   <c:if test="${listBookingDetails.loginStatus eq 'Y'}">
                        	<img  class="status${listBookingDetails.userId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${listBookingDetails.loginStatus eq 'N'}">
                        	<img  class="status${listBookingDetails.userId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                   
                   </div></td>
                  <td><div class="tutor-name">${listBookingDetails.studentFullName}</div>
                    <div class="subj">${listBookingDetails.subjectName} ${listBookingDetails.eduType}</div>
                    <div class="university-utc">${listBookingDetails.levelName}</div>
                    <div class="university-utc">${listBookingDetails.timeZoneName}</div>
                    
                    
                    <div class="chatMsg">
                    <a href="javascript:register_popup('${listBookingDetails.userId}', '${listBookingDetails.studentFullName}', '${listBookingDetails.chatSessionId}');" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a>
                    
                     
                    <a href="javascript:;" onclick="openMessagePopUp('${listBookingDetails.userId}', '${listBookingDetails.studentFullName}');"  title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a> </div>
                    
                    </td>
                 
                 
                 <td style="width: 100%;"><div class="ques" style="width: 100%;">
                      <div class="panel-outr">
                        <div class="panel-hdr"><spring:message code="question" /></div> 
                        <div class="panel-body"> ${listBookingDetails.question} </div>
                      </div>
                    </div>
                    
                    
                       <div class="date-archive pt10">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="scheduled.sessions" /></div> 
                        <div class="panel-body date-archive-txt">
                          <div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt=""> ${listBookingDetails.bookingStudentDate}</div>
                          
                        </div>
                      </div>
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="student.archive" /></div> 
                        <div class="panel-body date-archive-txt">
                          <div class="s-archive"> ${listBookingDetails.bookingDocName} <a href="<%=request.getContextPath()%>${listBookingDetails.bookingDocPath}" download><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>
                        </div>
                      </div>
                    </div></td>
                  <td><div class="tutor-db-Btns">
                   <a class="greenBtn-normal" onclick="goToMeeting('${listBookingDetails.bookingId}');" id="session${listBookingDetails.bookingId}" href="javascript:;"><spring:message code="gotoClassroom" /></a> 
                   <a class="blueBtn-normal" href="javascript:;"  onclick="cancelBookeedMeeting('${listBookingDetails.bookingId}','${listBookingDetails.studentFullName}','${user}');" ><spring:message code="cancel" /></a> </div>
                   </td>
                </tr>
                <c:set var="count" value="1" scope="page"/>
                </c:if>
                 </c:forEach>
                </c:if>
                
                       
                <c:if test="${listBookingDetails ne null }">
                <c:forEach items="${listBookingDetails}" var="listBookingDetails">
                <c:if test="${listBookingDetails.accepted == 'N' && listBookingDetails.iscompleted == 'N'  && listBookingDetails.isDeleted == 'N' && listBookingDetails.acceptSessionFlag == 'Y' }">
                    <tr>
                   <td width="13"><div class="indi-icon">
                   
                   <c:if test="${listBookingDetails.loginStatus eq 'Y'}">
                        	<img  class="status${listBookingDetails.userId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${listBookingDetails.loginStatus eq 'N'}">
                        	<img  class="status${listBookingDetails.userId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                   
                   </div></td>
                  <td><div class="tutor-name">${listBookingDetails.studentFullName}</div>
                    <div class="subj">${listBookingDetails.subjectName} ${listBookingDetails.eduType}</div>
                    <div class="university-utc">${listBookingDetails.levelName}</div>
                    <div class="university-utc">${listBookingDetails.timeZoneName}</div>
                    
                    
                    <div class="chatMsg"><a href="javascript:register_popup('${listBookingDetails.userId}', '${listBookingDetails.studentFullName}', '${listBookingDetails.chatSessionId}');" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a> 
                    <a href="javascript:;" title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a> </div>
                    
                    </td>
                 
                 
                 <td><div class="ques" style="width: 100%;">
                      <div class="panel-outr">
                        <div class="panel-hdr"><spring:message code="question" /></div>
                        <div class="panel-body"> ${listBookingDetails.question} </div>
                      </div>
                    </div>
                    
                    
                       <div class="date-archive pt10">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="scheduled.sessions" /></div>
                        <div class="panel-body date-archive-txt">
                          <div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt=""> ${listBookingDetails.bookingStudentDate}</div>
                          
                        </div>
                      </div>
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="student.archive" /></div>
                        <div class="panel-body date-archive-txt">
                          <div class="s-archive"> ${listBookingDetails.bookingDocName} <a href="<%=request.getContextPath()%>${listBookingDetails.bookingDocPath}" download><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>
                        </div>
                      </div>
                    </div></td>
                 
                  <td><div class="tutor-db-Btns">
                   <a class="greenBtn-normal" href="javascript:;" onclick="acceptBooking('${listBookingDetails.bookingId}');" id="session${listBookingDetails.bookingId}"><spring:message code="scheduleit" /></a> 
                   <a class="blueBtn-normal" href="javascript:;"  onclick="cancelBookeedMeeting('${listBookingDetails.bookingId}','${listBookingDetails.studentFullName}','${user}');" ><spring:message code="cancel" /></a> </div></td>
                </tr>
                <c:set var="count" value="1" scope="page"/>
                </c:if>
                </c:forEach>
                </c:if>
                <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;"><spring:message code="norecord.found"/> </h3>
                </c:if>
            </tbody>
              </table>
            </div>
          </div>
       
       
       
       
       
    
    
      <!--     ===================== for new Review Session Request===============================   -->
  
  <div class="tutr-screen mt-20">
            <h1 class="text-left"><spring:message code="currentReviewRequests" />   </h1>
            <form id="reviewBookinForm" name="reviewBookinForm" enctype="multipart/form-data"  method="POST">
            <div class="tutor-db-txt" id="reviewSessionTable">
            
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
              	<tbody>
              	<c:set var="reviewCount" value="0" scope="page"/>
              	 <c:forEach items="${listReviewDetails}" var="listReviewDetails">
              	<c:if test="${listReviewDetails.isCompletedByTutor eq 'N'}">
                <tr>
                
                  <td width="13"><div class="indi-icon">
                  
                  <c:if test="${listReviewDetails.loginStatus eq 'Y'}">
                  <img  class="status${listReviewDetails.studentUserId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                  </c:if>
                  <c:if test="${listReviewDetails.loginStatus eq 'N'}">
                  <img  class="status${listReviewDetails.studentUserId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                  </c:if>
                  
                  
                  </div></td>
                  
                  <td><div class="tutor-name">${listReviewDetails.studentFullName}</div>
                    <div class="subj">${listReviewDetails.subjectName}</div>
                    <div class="university-utc">${listReviewDetails.levelName}</div>
                    <div class="sessionTime">${listReviewDetails.timeZoneName}</div>
                    <div class="chatMsg">
                    
                    <a href="javascript:register_popup('${listReviewDetails.studentUserId}', '${listReviewDetails.studentFullName}', '${listReviewDetails.chatSessionId}');" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a> 
                    
                    
                    
                    <a href="javascript:;" onclick="openMessagePopUp('${listReviewDetails.studentUserId}', '${listReviewDetails.studentFullName}');" title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a></div>
                    </td>
                    
                    <td style="width: 100%;">
                    <div class="date-archive">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="submitbefore"/></div>
                        <div class="panel-body date-archive-txt">
                          <div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt=""> ${listReviewDetails.bookingStudentDate}</div>
                          
                        </div>
                      </div>
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="studentfile" /></div>
                        <div class="panel-body date-archive-txt">
                          <div class="s-archive"> ${listReviewDetails.studentDocName} <a href="<%=request.getContextPath()%>${listReviewDetails.studentDocPath}" download>
                          <img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="ques pt10" style="width: 100%;">
                      <div class="panel-outr">
                        <div class="panel-hdr"><spring:message code="query" /></div>
                        <div class="panel-body"> ${listReviewDetails.reviewQuestion} </div>
                      </div>
                    </div>
                    
                    <div class="txtArea">
                      <c:if test="${listReviewDetails.accepted eq 'N'}">
                    	<textarea rows="3" readonly="readonly" placeholder="Tutor Comments..."  id="tutorComments${listReviewDetails.bookingId}" name="tutorComments${listReviewDetails.bookingId}"></textarea>
                    	</c:if>
                    	
                    	<c:if test="${listReviewDetails.accepted eq 'Y' && listReviewDetails.isCompletedByTutor eq 'N'}">
                    	<textarea rows="3"  placeholder="Tutor Comments..."  id="tutorComments${listReviewDetails.bookingId}" name="tutorComments${listReviewDetails.bookingId}"></textarea>
                    	</c:if>
                    	
                    	<c:if test="${listReviewDetails.accepted eq 'Y' && listReviewDetails.isCompletedByTutor eq 'Y'}">
                    	  <textarea rows="3"  title="${listReviewDetails.tutorComments}" id="tutorComments${listReviewDetails.bookingId}" name="tutorComments${listReviewDetails.bookingId}" value="${listReviewDetails.tutorComments}" placeholder="Comentario Profe"></textarea>
                    	</c:if>
                    </div>
                    
                    
                     <div class="date-archive pt10">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="tutorsolutionfile" /></div>
                        
                        <c:if test="${listReviewDetails.accepted eq 'N'}">
                        <div class="panel-body date-archive-txt"> Tutor File Examinar </div>
                        </c:if>	
                       <c:if test="${listReviewDetails.accepted eq 'Y' && listReviewDetails.isCompletedByTutor eq 'N'}">
                    <div class="panel-body date-archive-txt"> <input type="file" id="documentReview${listReviewDetails.bookingId}" name="documentReview" /> </div>
                    	</c:if>	
                     <c:if test="${listReviewDetails.accepted eq 'Y' && listReviewDetails.isCompletedByTutor eq 'Y'}">
                    	  <div class="s-archive"> ${listReviewDetails.tutorDocPath} <a href="<%=request.getContextPath()%>${listReviewDetails.tutorDocPath}" download>
                          <img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>
                    	</c:if>
                        
                      </div>
                      
                      
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="equilavent.rate" /></div>
                        <div class="panel-body date-archive-txt">
                          <div class="equi-rate">
                          <spring:message code="student.minutes" var="minutes"/>
                            <c:if test="${listReviewDetails.is_proposed_byTutor eq 'N'}">
                        <select id="proposedMinutes${listReviewDetails.bookingId}">
                       	<option value="0">Minutes</option>
						<option value="15">15 <spring:message code="mins" /></option>
						<option value="30">30 <spring:message code="mins" /></option>
						<option value="45">45 <spring:message code="mins"/></option>
						<option value="60">60 <spring:message code="mins"/></option>  
                        </select>
                        </c:if>
                        <c:if test="${listReviewDetails.is_proposed_byTutor eq 'Y'}">
                        <div class="panel-body"> ${listReviewDetails.tutorProposedTime}  ${minutes} </div>
                        </c:if>
                          </div>
                        </div>
                      </div>
                    </div>
                    </td>
                    
                 
                 <td>
                 
                 <div class="tutor-db-Btns">
                   <c:if test="${listReviewDetails.is_proposed_byTutor eq 'Y' && listReviewDetails.accepted eq 'N'}">
                  <a class="orangeBtn-normal" href="javascript:void(0);" id="proposedSession${listReviewDetails.bookingId}">
                    <spring:message code="pending.approval.student" /></a>
                   </c:if>
                   
                   <c:if test="${listReviewDetails.is_proposed_byTutor eq 'N'}">
                  <a class="orangeBtn-normal" href="javascript:void(0);" onclick="acceptReviewRequest('${listReviewDetails.bookingId}');" id="session${listReviewDetails.bookingId}">
                   <spring:message code="accepthomework" /></a>
                   </c:if>
                   
                   <c:if test="${listReviewDetails.is_proposed_byTutor eq 'Y' && listReviewDetails.accepted eq 'Y' && listReviewDetails.isCompletedByTutor eq 'N'}">
                  <a class="orangeBtn-normal" href="javascript:void(0);" onclick="submitReviewWork('${listReviewDetails.bookingId}');" id="session${listReviewDetails.bookingId}">
                   <spring:message code="post.review" /></a>
                   </c:if>
                   
                   
                   
                   <a class="blueBtn-normal"href="javascript:;"onclick="cancelReviewRequest('${listReviewDetails.bookingId}','${listReviewDetails.tutorFullName}','${listReviewDetails.studentFullName}');">
                   <spring:message code="cancel" /></a>
                   </div>
                   
                   </td>
                </tr>
                  	
                <c:set var="reviewCount" value="1" scope="page"/>
                </c:if>
                </c:forEach>
                 <c:if test="${reviewCount eq '0' }">
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                 </c:if>
                </tbody></table>
            </div>
             </form>
          </div>
    
      
  <!--     ===================== ===============================   -->
  
          
           <div class="my-info-tutor tutor-db-outr">
           <h1 class="text-left"><spring:message code="activeChats" /></h1>
            <div class="stu-index-txt"  id="studentDetails">
            <div style="width: 100%; height: 400px; overflow: auto;">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${dtoStudentDetails}" var="dtoStudentDetails">
                	 <c:if test="${dtoStudentDetails.userId ne 0}">
                    	<li>
                        	<div class="stud-img-outr">
                            	<div class="indi-icon" >
                            	
                            	<c:if test="${dtoStudentDetails.loginStatus eq 'Y'}">
                        	<img class="status${dtoStudentDetails.userId}" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${dtoStudentDetails.loginStatus eq 'N'}">
                        	<img  class="status${dtoStudentDetails.userId}" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                            	
                            	</div>
                                <div class="tutor-img-txt">
                                ${dtoStudentDetails.fullName} 
                               
                                </div>
                                <div class="book-session">
                                <c:if test="${dtoStudentDetails.tutorChatStatus eq 'Y'}">
                                	<a href="javascript:register_popup('${dtoStudentDetails.userId}', '${dtoStudentDetails.fullName}', '${dtoStudentDetails.chatSessionId}');" name="chatTutor${dtoStudentDetails.userId}" id="chatTutor${dtoStudentDetails.userId}"  class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
                               </c:if>
                               <c:if test="${dtoStudentDetails.tutorChatStatus eq 'N'}">
                                	<a href="javascript:register_popup('${dtoStudentDetails.userId}', '${dtoStudentDetails.fullName}', '${dtoStudentDetails.chatSessionId}');" name="chatTutor${dtoStudentDetails.userId}" id="chatTutor${dtoStudentDetails.userId}"  class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>
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
      </div>
    </div>
  </section>
  <!--//Mid Section--> 
</div>

<div id="editpopup" style="display:none;">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/tutor/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.sessionnotstarted"/>
            </section>
        </div>
        </div>
        </div>
        </div>

<form action="<%=request.getContextPath()%>/tutor/tutorClassroom" method="POST" id="meetingForm">
<input type="hidden" id="meetingId" name="meetingId" readonly="readonly">
</form>

 <%--  <div class="form-tutor-popup" id="cancelSuccess" style="display: none">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/tutor/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt">
            	<spring:message code="sessionrequestDeleted"/>
            </div>
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/tutor/home" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>  --%>


  <div class="form-tutor-popup" id="deleteSuccess" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/tutor/home"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" id="deleteMsg" >
            	 
            </div>
               
             
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/tutor/home" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div> 


  <div class="form-tutor-popup" id="showMinuteError" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="javascript:void(0);" onclick="hideMinuteError()"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" id="minuteMsg" >
            	  <spring:message code="select.minute.first"/>
            </div>
               
             
           <div class="book-session">
               <a href="javascript:void(0);" onclick="hideMinuteError()" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
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
        
    <div class="form-tutor-popup" id="workSubmitSuccess" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="javascript:void(0);" onclick="workSubmitSuccess()"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" id="submitSuccess" >
            	  <spring:message code="work.submit.success"/>
            </div>
               
             
           <div class="book-session">
               <a href="javascript:void(0);" onclick="workSubmitSuccess()" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>        
        
             
        
        



 <%--  <div class="form-tutor-popup" id="confirmCancel" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="cancelDeleteSession();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" >
            	 <spring:message code="studentSessionDelet"></spring:message>
            </div>
               
             
           <div class="book-session">
               <a onclick="confirmCancelSession();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                <a onclick="cancelDeleteSession();" class="greenBtn-normal"> <spring:message code="cancel"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div> --%>          
        
<input type="hidden" id="deleteConfirmId" /> 

    <div class="form-tutor-popup" id="cancelReason" style="display:none;">
     <form id="cancelMessageForm" name="cancelMessageForm"  method="POST">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="cancelDeleteSessionRequest();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                  
                    <tr>
                   <th><spring:message code="to"/></th>
                    <td align="right">:</td> 
                    <td><div id="toUserName"></div></td>
                  </tr>
                  
             		<tr>
                   <th><spring:message code="from"/></th>
                    <td>:</td>  
                    <td><div id="fromUserName"></div></td>
                  </tr>
                  
                   <tr class="msg-row">
                   <th class="v-alignTop"><spring:message code="message"/></th>
                    <td class="v-alignTop">:</td>  
                    <td class="v-alignTop"><div ><textarea name="messageReply" id="messageReply" rows="4" cols="20"  maxlength="250"></textarea></div></td>
                  </tr>
                  
                  
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="send.message"></spring:message>' onclick="confirmCancelSession();"></td>
                  </tr>
                </tbody></table>
                </div>
                <input type="hidden" id="sessionBookingId" name="sessionBookingId">
        	
        </div>
   </div>
</form>
        </div>
        
        
     <div class="form-tutor-popup" id="reviewCancelReason" style="display:none;">
     <form id="reviewCancelForm" name="reviewCancelForm"  method="POST">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="cancelReviewDeleteRequest();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                  
                    <tr>
                   <th><spring:message code="to"/></th>
                    <td align="right">:</td> 
                    <td><div id="toReviewUserName"></div></td>
                  </tr>
                  
             		<tr>
                   <th><spring:message code="from"/></th>
                    <td>:</td>  
                    <td><div id="fromReviewUserName"></div></td>
                  </tr>
                  
                   <tr class="msg-row">
                   <th class="v-alignTop"><spring:message code="message"/></th>
                    <td class="v-alignTop">:</td>  
                    <td class="v-alignTop"><div ><textarea name="reviewMessageReply" id="reviewMessageReply" rows="4" cols="20"  maxlength="250"></textarea></div></td>
                  </tr>
                  
                  
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="send.message"></spring:message>' onclick="confirmReviewCancel();"></td>
                  </tr>
                </tbody></table>
                </div>
                <input type="hidden" id="reviewBookingId" name="reviewBookingId">
        	
        </div>
   </div>
</form>
        </div>       


<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>


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
                <input type="hidden" id="studentMessageUserId" name="studentMessageUserId">
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
            
           
               <a href="javascript:void(0);" onclick="closeSendMessageSuccessPopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
          
            
        </div>
        </div>
        </div>



</body>


<script type="text/javascript">
function UpdateTableHeaders() {
    $(".accordian-subjects div section").each(function () {

        var el = $(this),
            offset = el.offset(),
            scrollTop = $(window).scrollTop(),
            floatingHeader = $(".floatingHeader", this)

            if ((scrollTop > offset.top) && (scrollTop < offset.top + el.height())) {
                floatingHeader.css({
                    "visibility": "visible"
                });
            } else {
                floatingHeader.css({
                    "visibility": "hidden"
                });
            };
    });
}

// DOM Ready      
$(function () {

    var clonedHeaderRow;

    $(".accordian-subjects div section").each(function () {
        clonedHeaderRow = $(".actog2", this);
        clonedHeaderRow.before(clonedHeaderRow.clone()).css("width", clonedHeaderRow.width()).addClass("floatingHeader");

    });

    $(window).scroll(UpdateTableHeaders).trigger("scroll");

});

jQuery(document).ready(function () {
    jQuery(".actog2").next(".accon2").hide();
    jQuery(".actog2").click(function () {
        $('.active').not(this).toggleClass('active').siblings('.accon2').slideToggle(500);
        $(this).toggleClass('active').siblings('.accon2').slideToggle(400);
    });
});
</script> 
<script type="text/javascript">
$(document).ready(function() {
	
	$('#cancelMessageForm').validate({
		rules:{
			messageReply: {
                required:true
            }
			},
		 messages:{
					messageReply:{
					}
		}
	});
	
	$('#reviewCancelForm').validate({
		rules:{
			reviewMessageReply: {
                required:true
            }
			},
		 messages:{
			 reviewMessageReply:{
					}
		}
	});
	
	
	
  function filterPath(string) {
  return string
    .replace(/^\//,'')
    .replace(/(index|default).[a-zA-Z]{3,4}$/,'')
    .replace(/\/$/,'');
  }
  var locationPath = filterPath(location.pathname);
  var scrollElem = scrollableElement('html', 'body');
 
  $('a[href*=#]').each(function() {
    var thisPath = filterPath(this.pathname) || locationPath;
    if (  locationPath == thisPath
    && (location.hostname == this.hostname || !this.hostname)
    && this.hash.replace(/#/,'') ) {
      var $target = $(this.hash), target = this.hash;
      if (target) {
        var targetOffset = $target.offset().top;
        $(this).click(function(event) {
          event.preventDefault();
          $(scrollElem).animate({scrollTop: targetOffset}, 900, function() {
            location.hash = target;
          });
        });
      }
    }
  });
 
  // use the first element that is "scrollable"
  function scrollableElement(els) {
    for (var i = 0, argLength = arguments.length; i <argLength; i++) {
      var el = arguments[i],
          $scrollElement = $(el);
      if ($scrollElement.scrollTop()> 0) {
        return el;
      } else {
        $scrollElement.scrollTop(1);
        var isScrollable = $scrollElement.scrollTop()> 0;
        $scrollElement.scrollTop(0);
        if (isScrollable) {
          return el;
        }
      }
    }
    return [];
  }
 
});

function acceptBooking(bookingId){
	var url='<%=request.getContextPath()%>/tutor/acceptAndCreateScribblarRoom';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingId:bookingId},
		success:function(response){
			location.reload();
			//var scribres = '${scribReturn}';
		//	alert(scribres);
			
		}
		
		
	}); 
}


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


function cancelBookeedMeeting(bookingId,tutorName,studentName){
	$(window).scrollTop(0);
	$("#toUserName").text(tutorName);
	$("#fromUserName").text(studentName);
	$("#sessionBookingId").val(bookingId);
	$("#deleteConfirmId").val(bookingId);
	$("#cancelReason").show();
	}

	
function cancelDeleteSessionRequest(){
//	$("#confirmCancel").hide();
	$("#cancelReason").hide();

	}
	

		function confirmCancelSession(){
			
		//	$("#confirmCancel").hide();
			var bookingId = $("#deleteConfirmId").val();
			var message=$("#messageReply").val();
			var bookingId=$("#sessionBookingId").val();
			if ($('#cancelMessageForm').valid()) {
				$("#cancelReason").hide();
			var url='<%=request.getContextPath()%>/tutor/cancelBookedSession';
			$.ajax({
				url:url,
				method:'GET',
				async :false,
				data:{bookingId:bookingId,message:message},
				success:function(response){
					if(response.modelMap.deleteSuccess==true){
					$(window).scrollTop(0);
					$("#deleteSuccess").show();
					if(response.modelMap.penaltyApplied=='Y'){
					$("#deleteMsg").html('<spring:message code="sessionDeletedWithPenalty"></spring:message>');
					}
					else{
						$("#deleteMsg").html('<spring:message code="sessionDeletedWithoutPenalty"></spring:message>');
					}
					
					}
				}
			});
			}
		}
		

</script> 

<script type="text/javascript">
var listSize = '${fn:length(listBookingDetails)}';
var userFullName='${user}';
 setInterval(function(){
	var url='<%=request.getContextPath()%>/tutor/getTutorBookingDetailsWithStudent';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			var status="0";
			if(response != null){
				var newlistsize= response.modelMap.listNewBookingDetailsize;
				if(listSize!=newlistsize){
				var tutorData="";
				tutorData+=  '<table width="100%" border="0" cellspacing="0" cellpadding="0"><tbody>';
				 $.each( response.modelMap.listNewBookingDetails, function(index,value) {
						status="1";
						  
			                        
						  if(value.accepted=="Y" && value.iscompleted=="N" && value.isDeleted=="N")
						  {
			  tutorData+=   '<tr><td width="13"><div class="indi-icon">';
			  
			  if(value.loginStatus=='Y'){
				  tutorData+=	'<img class="status'+value.userId+'" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
				}
				else{
					tutorData+= '<img class="status'+value.userId+'" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
				}
			  
			  tutorData+= '</div></td>'+
			                '<td><div class="tutor-name">'+value.fullName+'</div><div class="subj">'+value.subjectName+' '+value.eduType+'</div>'+
			                '<div class="university-utc">'+value.levelName+'</div><div class="university-utc">'+value.timeZoneName+'<div class="chatMsg">'+
			                '<a href="javascript:register_popup(\''+value.userId+'\',\''+value.fullName+'\',\''+value.chatSessionId+'\');" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a>'+
			                '<a href="javascript:;" onclick="openMessagePopUp(\''+value.userId+'\', \''+value.fullName+'\');"  title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a></div></td>'+
			                
			                '<td style="width: 100%;"><div class="ques" style="width: 100%;"><div class="panel-outr"><div class="panel-hdr"><spring:message code="question" /></div></div><div class="panel-body">'+value.question+'</div>'+
		                      '</div></div><div class="date-archive pt10"><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="scheduled.sessions" /></div>'+
		                       '<div class="panel-body date-archive-txt"><div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt="">'+
		                       value.bookingStudentDate+'</div>'+
		                        '</div></div>'+
		                        
                        '<div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="student.archive" /></div><div class="panel-body date-archive-txt"><div class="s-archive">';
                 if(value.bookingDocPath=='NA'){
     			 tutorData+=    '<a href="javascript:void(0);" >'+value.bookingDocName+'</a>';
     			                }
     			                else
     			                	{
              	tutorData+=value.bookingDocName+'<a href="<%=request.getContextPath()%>'+value.bookingDocPath+'" download><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a>';
     			                	}
                 tutorData+='</div></div></div></div></td>'+
			                
                        '<td><div class="tutor-db-Btns"><a class="greenBtn-normal" onclick="goToMeeting('+value.bookingId+');" id="session'+value.bookingId+'" href="javascript:;">'+
                        '<spring:message code="gotoClassroom" /></a>'+ 
                        '<a class="blueBtn-normal" href="javascript:;"  onclick="cancelBookeedMeeting(\''+value.bookingId+'\',\''+value.levelName+'\',\''+userFullName+'\');" ><spring:message code="cancel" /></a> </div></td></tr>';
			                }
			               
			               if(value.accepted=="N" && value.iscompleted=="N" && value.isDeleted=="N")
						   {
			                
			            	   tutorData+=   '<tr><td width="13"><div class="indi-icon">';
			     			  
			     			  if(value.loginStatus=='Y'){
			     				  tutorData+=	'<img class="status'+value.userId+'" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
			     				}
			     				else{
			     					tutorData+= '<img class="status'+value.userId+'" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
			     				}
			     			  
			     			  tutorData+= '</div></td>'+
			     			                '<td><div class="tutor-name">'+value.fullName+'</div><div class="subj">'+value.subjectName+' '+value.eduType+'</div>'+
			     			                '<div class="university-utc">'+value.levelName+'</div><div class="university-utc">'+value.timeZoneName+'<div class="chatMsg">'+
			     			                
			     			                '<a href="javascript:register_popup(\''+value.userId+'\',\''+value.fullName+'\',\''+value.chatSessionId+'\');" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a>'+
			     			                '<a href="javascript:;" title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a></div></td>'+
			     			                
			     			                '<td><div class="ques" style="width: 100%;"><div class="panel-outr"><div class="panel-hdr"><spring:message code="question" /></div><div class="panel-body">'+value.question+'</div>'+
			     		                      '</div></div><div class="date-archive pt10"><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="scheduled.sessions" /></div>'+
			     		                       '<div class="panel-body date-archive-txt"><div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt="">'+
			     		                       value.bookingStudentDate+'</div>'+
			     		                        '</div></div>'+
			     		                        
			                             '<div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="student.archive" /></div><div class="panel-body date-archive-txt"><div class="s-archive">';
			                      if(value.bookingDocPath=='NA'){
			          			 tutorData+=    '<a href="javascript:void(0);" >'+value.bookingDocName+'</a>';
			          			                }
			          			                else
			          			                	{
			                   	tutorData+=value.bookingDocName+'<a href="<%=request.getContextPath()%>'+value.bookingDocPath+'" download><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a>';
			          			                	}
			                      tutorData+='</div></div></div></div></td>'+
			     			                
                         '<td><div class="tutor-db-Btns"><a class="greenBtn-normal"  onclick="acceptBooking('+value.bookingId+');" id="session'+value.bookingId+'"><spring:message code="scheduleit" /></a>'+ 
                         '<a class="blueBtn-normal" href="javascript:;"  onclick="cancelBookeedMeeting(\''+value.bookingId+'\',\''+value.levelName+'\',\''+userFullName+'\');" ><spring:message code="cancel" /></a> </div></td></tr>';
			     			                                
						   }
			   
						 });
				 tutorData+=  '</tbody></table>';
				$("#tutorbookingDetails").html(tutorData);
				if(listSize<newlistsize){
					//alert("1246");
					$.playSound(soundurl);
				}
					listSize=newlistsize;
				
				}
			
			}
		}
	});  
}, 5000); 
 
 

// var chatlistSize = '${fn:length(dtoStudentDetails)}';
var chatlistSize = '${dtoStudentDetailsSize}'; 
 setInterval(function(){
	var url='<%=request.getContextPath()%>/tutor/getTutorChatSessionDetails';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null   && response.modelMap.dtoStudentDetails!=null){
				
				var newlistsize= response.modelMap.listNewDtoStudentDetailsize;
		//		if(chatlistSize!=newlistsize){
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
                        	  
                        	  
                        	  studentData+=      '</div><div class="tutor-img-txt">'+value.fullName+'</div><div class="book-session">';
                         
                              if(value.tutorChatStatus=='Y'){
                            	  studentData+= '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.fullName+'\', \''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'" class="greenBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>';
                              }
                              else{
                            	  studentData+= '<a href="javascript:register_popup(\''+value.userId+'\', \''+value.fullName+'\', \''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'" class="grayBtn-normal"><spring:message code="chatWithTutor"></spring:message> </a>'; 
                              }                     
                              
                              studentData += '</div></div></li>'; 
                    
                	   			   
						 });
				 studentData += '</ul></div>';
				$("#studentDetails").html(studentData);
			//	if(chatlistSize<newlistsize){
			//	$.playSound(soundurl);
			//	}
			//	chatlistSize=newlistsize;
			//	}
			
				
				 $.each( response.modelMap.dtoStudentDetails, function(index,value) {
					 if(value.loginStatus=='Y'){
		            	   var online='<%=request.getContextPath()%>/images/green-bullet.png';
							$(".status"+value.userId).attr("src",online);
						}
						else{
							var offline='<%=request.getContextPath()%>/images/yellow-bullet.png';
							$(".status"+value.userId).attr("src",offline);
						}
				 });
			}
			if(response.modelMap.listNewDtoStudentDetailsize==0){
				var stuData='<div style="width: 100%; height: 400px; overflow: auto;"><ul><h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3></ul></div>';
				$("#studentDetails").html(stuData);
			}
		}
	});  
    
    
 }, 5000);  

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
    	var url='<%=request.getContextPath()%>/tutor/saveUserIP';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :false,
    		data:{userIP:userIP},
    		success:function(response){
    			    			
    		},
    	});
    	
        //alert(response.ip);
    } 
    
    
    
//=============== for review accept==============
function acceptReviewRequest(bookingid)
{
var proposedMinutes = $("#proposedMinutes"+bookingid+" option:selected").val();
alert(proposedMinutes);
    if(proposedMinutes!="0"){
   	var url='<%=request.getContextPath()%>/tutor/proposedminutesSave';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingid:bookingid,proposedMinutes:proposedMinutes},
		success:function(response){
			if(response.modelMap.updateSuccess=="Y")
			{
				location.reload();
			}
			else{
				alert("error");
			}
		}
	}); 
       } 
    else{
    	$(window).scrollTop(0);
    	$("#showMinuteError").show();
    }
} 

function hideMinuteError(){
	$("#showMinuteError").hide();
}

<%-- function cancelReviewRequest(bookingid){
   	var url='<%=request.getContextPath()%>/tutor/cancelReviewRequest';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingid:bookingid},
		success:function(response){
			if(response.modelMap.deleteSuccess=="Y")
			{
				alert("REview Session Deleted Successfully");
			}
			else{
				alert("error");
			}
		}
	}); 
} --%>


function cancelReviewRequest(bookingId,tutorName,studentName){
	$(window).scrollTop(0);
	$("#toReviewUserName").text(studentName);
	$("#fromReviewUserName").text(tutorName); 
	$("#reviewBookingId").val(bookingId);
	$("#reviewCancelReason").show();
	}
	
function confirmReviewCancel(){
		var bookingId = $("#reviewBookingId").val();
		var message=$("#reviewMessageReply").val();
		if ($('#cancelMessageForm').valid()) {
			$("#reviewCancelReason").hide();
		var url='<%=request.getContextPath()%>/tutor/cancelReviewRequest';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{bookingId:bookingId,message:message},
			success:function(response){
				if(response.modelMap.deleteSuccess=='Y'){
				$(window).scrollTop(0);
				$("#deleteSuccess").show();
				$("#deleteMsg").html('<spring:message code="sessionrequestDeleted"></spring:message>');
				}
			}
		});
		}
	}	

function cancelReviewDeleteRequest(){
	$("#reviewCancelReason").hide();
	}

var idleRefreshTime = 0;
$(document).ready(function () {
    //Increment the idle time counter every minute.
    var idleInterval = setInterval(timerIncrement, 10000); // 10 seconds

    //Zero the idle timer on mouse movement.
   $(this).mousemove(function (e) {
	
	   idleRefreshTime = 0;
   });
 //Zero the idle timer on keypress event.
    $(this).keypress(function (e) {
	
    	idleRefreshTime = 0;
    });
});

function timerIncrement() {
	idleRefreshTime = idleRefreshTime + 1;
    if (idleRefreshTime >= 2) {
    	reloadReviewList();
    }
}



//var reviewSessionlistSize = '${listReviewDetailSize}'; 
//setInterval(function(){
	function reloadReviewList(){
	var url='<%=request.getContextPath()%>/tutor/getReviewSessionDetailRequests';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response != null   && response.modelMap.listReviewDetailsList!=null){
				
		//		var newlistsize= response.modelMap.listNewReviewDetailSize;
			//	if(reviewSessionlistSize!=newlistsize){
					
				var reviewSessionData='<table width="100%" border="0" cellspacing="0" cellpadding="0"><tbody>';
				
				
				//================================================================================
				 $.each(response.modelMap.listReviewDetailsList, function(index,value) {
						if(value.isCompletedByTutor=='N')
						{
						
						 reviewSessionData+='<tr><td width="13"><div class="indi-icon">';
						 if(value.loginStatus=='Y'){
			                reviewSessionData+='<img  class="status'+value.studentUserId+'" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
							  }
							   if(value.loginStatus=='N'){
			                reviewSessionData+='<img  class="status'+value.studentUserId+'" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
							  }
							  
						 reviewSessionData+='</div></td>'+
							
						'<td><div class="tutor-name">'+value.studentFullName+'</div>'+
							 '<div class="subj">'+value.subjectName+'</div> <div class="university-utc">'+value.levelName+'</div>'+
							 '<div class="sessionTime">'+value.timeZoneName+'</div>'+
							 '<div class="chatMsg"><a href="javascript:register_popup(\''+value.studentUserId+'\',\''+value.studentFullName+'\',\''+value.chatSessionId+'\');" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a>'+ 
							'<a href="javascript:;" onclick="openMessagePopUp(\''+value.studentUserId+'\', \''+value.studentFullName+'\');" title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a></div></td>';
							 
					//================================================		
							 
					reviewSessionData+='<td style="width: 100%;">'+	
						
				  '<div class="date-archive"><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="submitbefore"/></div>'+
                    '<div class="panel-body date-archive-txt"><div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt="">'+
					value.bookingStudentDate+'</div></div></div><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="studentfile" /></div>'+
                    '<div class="panel-body date-archive-txt"><div class="s-archive">'+ value.studentDocName+
					  '<a href="<%=request.getContextPath()%>'+value.studentDocPath+'" download>'+
                      '<img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div></div> </div> </div>'+ 
					  
					  
					  '<div class="ques pt10" style="width: 100%;"> <div class="panel-outr"><div class="panel-hdr"><spring:message code="query" /></div>'+
                    '<div class="panel-body"> '+value.reviewQuestion+' </div></div></div>';
					
					
                   reviewSessionData+='<div class="txtArea">';
					
                   if(value.accepted=='N')
					 {
					 reviewSessionData+='<textarea rows="3" readonly="readonly" placeholder="Tutor Comments..." id="tutorComments'+value.bookingId+'" name="tutorComments'+value.bookingId+'"></textarea>';
										
                  	}
						
						if(value.accepted=='Y' && value.isCompletedByTutor=='N')
					 {
					 reviewSessionData+='<textarea rows="3"   title="'+value.tutorComments+'" id="tutorComments'+value.bookingId+'" name="tutorComments'+value.bookingId+'" placeholder="Comentario Profe"></textarea>';
					 
                  	}
						
					if(value.accepted=='Y' && value.isCompletedByTutor=='Y')
					 {
						reviewSessionData+='<textarea rows="3"  title="'+value.tutorComments+'" id="tutorComments'+value.bookingId+'" name="tutorComments'+value.bookingId+'" value="'+value.tutorComments+'" placeholder="Comentario Profe"></textarea>';
                  	}
					
					reviewSessionData+='</div>'; 
							
							
							
						reviewSessionData+='<div class="date-archive pt10">'+
						
						
                        '<div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="tutorsolutionfile" /></div>';
                        if(value.accepted=='N')
							{
		                    reviewSessionData+='<div class="panel-body date-archive-txt"> Tutor File Examinar </div>';	
		                    }
							if(value.accepted=='Y' && value.isCompletedByTutor=='N')
							 {	
		                    reviewSessionData+='<div class="panel-body date-archive-txt"> <input type="file"  id="documentReview'+value.bookingId+'" name="documentReview'+value.bookingId+'" /> </div>';	
		                    	}
		                     if(value.accepted=='Y' && value.isCompletedByTutor=='Y')
							 {
							reviewSessionData+='<div class="s-archive">'+value.tutorDocName+'<a href="<%=request.getContextPath()%>/'+value.tutorDocPath+'" download>'+
                           '<img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>';	
		                    }
						reviewSessionData+='</div>'+
					  
					   '<div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="equilavent.rate" /></div>'+
                        '<div class="panel-body date-archive-txt"><div class="equi-rate"><spring:message code="student.minutes" var="minutes"/>';
						
						
						if(value.is_proposed_byTutor=='N')
								{
		                        reviewSessionData+='<select id="proposedMinutes'+value.bookingId+'">'+
		                       	'<option value="0">Minutes</option>'+
								'<option value="15">15 <spring:message code="mins" /></option>'+
								'<option value="30">30 <spring:message code="mins" /></option>'+
								'<option value="45">45 <spring:message code="mins"/></option>'+
								'<option value="60">60 <spring:message code="mins"/></option>'+ 
		                        '</select>';
		                        }
		                        
								if(value.is_proposed_byTutor=='Y')
								{
		                        reviewSessionData+='<div class="panel-body">'+value.tutorProposedTime+'<spring:message code="student.minutes"/></div>';
								
		                        }
						reviewSessionData+='</div></div></div></div></td>';
						
						
						//============================
						
							
						reviewSessionData+='<td><div class="tutor-db-Btns">';
							if(value.is_proposed_byTutor=='Y' && value.accepted=='N')
						  {
		                  reviewSessionData+='<a class="orangeBtn-normal" href="javascript:void(0);" id="proposedSession'+value.bookingId+'">'+
		                    '<spring:message code="pending.approval.student" /></a>';
		                  }
		                   
		                   if(value.is_proposed_byTutor=='N')
						  {
		                  reviewSessionData+='<a class="orangeBtn-normal" href="javascript:void(0);" onclick="acceptReviewRequest(\''+value.bookingId+'\');" id="session'+value.bookingId+'">';
		                  reviewSessionData+=' <spring:message code="accepthomework" /></a>';
		                   }
		                   
						   if(value.is_proposed_byTutor=='Y' && value.accepted=='Y' && value.isCompletedByTutor=='N')
						   {
		                  reviewSessionData+='<a class="orangeBtn-normal" href="javascript:void(0);" onclick="submitReviewWork(\''+value.bookingId+'\');" id="session'+value.bookingId+'">'+
		                   '<spring:message code="post.review" /></a>';
		                   }
		                  reviewSessionData+='<a class="blueBtn-normal"href="javascript:;"onclick="cancelReviewRequest(\''+value.bookingId+'\',\''+value.tutorFullName+'\',\''+value.studentFullName+'\');">'+
												'<spring:message code="cancel" /></a></div></td></tr>';
		              
						}  
				 
						 });	
					
					
					
			
				 reviewSessionData+='</tbody></table>';
				//==================================================================================== 
				 
				$("#reviewSessionTable").html(reviewSessionData);
			//	reviewSessionlistSize=newlistsize;
			//	}
			}
			
			 if(response.modelMap.listNewReviewDetailSize==0){
				var reviewSessionData='<div style="width: 100%; height: 400px; overflow: auto;"><ul><h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3></ul></div>';
				$("#reviewSessionTable").html(reviewSessionData);
			}
		}
	});  
   
	}
//}, 5000);     
    
</script>

<script type="text/javascript">
  $(function() {
	  $( ".ellipsis1" ).tooltip({
		  open: function (event, ui) {
		        ui.tooltip.css("word-break", "break-all"),
		        ui.tooltip.css("font-size", "12px");
		    },
	      position: {
	        my: "top",
	        at: "top+15"
	      }
	    });
  });
  
  
 function submitReviewWork(bookingId){
			
	        var tutorComments = $("textarea#tutorComments"+bookingId).val();
 	       var formData = new FormData();
	        
	       var file_data = $('input[type="file"]')[0].files; // for multiple files
	        for(var i = 0;i<file_data.length;i++){
	        	formData.append("file", file_data[i]);
	        }
	        formData.append("tutorComments", tutorComments);
	        formData.append("bookingId", bookingId);
	        
	        var file_data = $("#documentReview"+bookingId).prop("files")[0]; 
	        var url = "<%=request.getContextPath()%>/tutor/tutorSubmitReviewWorkWithDocument";
	        
	        if(typeof(file_data)==='undefined'){
	        	$(window).scrollTop(0);
	        	$("#fileError").show();
	        } 
	        else{
	        $.ajax({
	            url: url,
	            data: formData,
	            contentType: false,
	            processData: false,
	            type: 'POST',
	            success: function(response){
	            	$(window).scrollTop(0);
	            	$("#workSubmitSuccess").show();
	            }
	        });
 }  
 } 
  
 function hideFileError(){
	 $("#fileError").hide();
 }
 
function workSubmitSuccess(){
	$("#workSubmitSuccess").hide();
	location.reload();
}
  
</script> 
  
<script type="text/javascript">
function openMessagePopUp(studentId,studentName){
    
    var tutorName='${user}';
    
    $("#studentMessageUserId").val(studentId);
    $("#toName").text(studentName);
    $("#fromName").text(tutorName);
    $(window).scrollTop(0);
    $("#sendMessagepopup").show();
}


function closeSendMessageSuccessPopUp(){
    $("#sendMessageSuccess").hide();
}


function submitSendMessageForm(){
    
    if ($('#sendMessageForm').valid()) {
var studentMessageUserId =  $("#studentMessageUserId").val();
var messageText = $("#message").val();

    var url='<%=request.getContextPath()%>/tutor/sendMessageHome';
    $.ajax({
                    url:url,
                    method:'GET',
                    async :true,
                    data:{studentMessageUserId:studentMessageUserId,messageText:messageText},
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

function closeSendMessagePopUp(){
    $("#message").val("");
    $("label.error").hide();
    
    $("#sendMessagepopup").hide();
}

$(document).ready(function(){
    $('#sendMessageForm').validate({
                    rules:{
message: {
  required:true
       }
                                    }
    });
});

</script>  
  


</html>