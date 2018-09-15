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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="student.session" /></title>
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

<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js" type="text/javascript" ></script>


  <link href="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.js"></script>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
 <link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet">

 
 <%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
 

<!--  <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script> -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jqueryAjaxValidate.js"></script>
 
</head>

<body>
<div id="main">
  <%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
        
            
            <div class="tutr-screen">
             <h1 class="text-left"><spring:message code="student.mybookedtutor"/></h1> 
            <div class="tutor-db-txt" id="bookedTutorDetails">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                <c:set var="count" value="0" scope="page"/>
 					<c:forEach items="${listBookingDetails}" var="listBookingDetails">
 					<c:if test="${listBookingDetails.iscompleted eq 'N' && listBookingDetails.isDeleted eq 'N'}">
                <tr>
                  <td width="13">
                  <div class="indi-icon">
                  <c:if test="${listBookingDetails.loginStatus eq 'Y'}">
                        	<img class="status${listBookingDetails.tutorId} defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
                        	</c:if>
                        	
                        	<c:if test="${listBookingDetails.loginStatus eq 'N'}">
                        	<img  class="status${listBookingDetails.tutorId} defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
                        	</c:if>
                  </div>
                  </td>
                  
                  <td><div class="s-stu-img-outr">
                  
                      <div class="tutor-img">
                                   <c:if test="${listBookingDetails.imageName !=null}">
					              	<img alt="" src="<%=request.getContextPath()%>/profilePictures/${listBookingDetails.tutorId}/fileupload/${listBookingDetails.tutorId}${listBookingDetails.imageName}"/>
					              	</c:if>
					              	<c:if test="${listBookingDetails.imageName ==null && listBookingDetails.imgUrl==null}">
					              	<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
					              	</c:if>
					              	<c:if test="${listBookingDetails.imgUrl !=null && listBookingDetails.imageName ==null}">
					              	<img alt="" src="${listBookingDetails.imgUrl}"/>
					              	</c:if>
                                </div>
                      
                     <div class="tutor-img-txt"> 
                     
                      ${listBookingDetails.fullName} 
                                <c:if test="${listBookingDetails.isFavourite eq 'N'}">
                                	 <span class="heartImg"><img onclick="setAsFavourite('${listBookingDetails.tutorId}');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>
                                	</c:if>
                                	<c:if test="${listBookingDetails.isFavourite eq 'Y'}">
                                	 <span class="heartImg"><img onclick="setAsFavourite('${listBookingDetails.tutorId}');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>
                                	</c:if>
                     
                        <div class="subjects ellipsis">${listBookingDetails.subjectName}</div>
                       <div class="stars">
                                    <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                    <c:when test="${i le listBookingDetails.tutorRating}">
                                    <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                    </c:when>
                                    <c:otherwise>
                                    <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">
                                    </c:otherwise>
                                    </c:choose>
                                    </c:forEach>
                               </div>      
                             
                      </div>
                      <div class="seeMore"><a onclick="openSeeMore('${listBookingDetails.tutorProfileId}');"><spring:message code="student.seemore"/></a></div> 
                    </div>
                    
                    <div class="chatMsg clr">
                    
                    <a href="javascript:;" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a>
     <!-- <a href="javascript:register_popup(\''+value.userId+'\', \''+value.name+'\',\''+receiverRole+'\', \''+value.tutorProfileId+'\',\''+value.chatSessionId+'\');" name="chatTutor'+value.userId+'" id="chatTutor'+value.userId+'"               
         -->            
                    
                     <a href="javascript:;" title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a>
                     
                      </div></td>
                     
                  <td style="width: 100%;">
                  <div class="ques" style="width: 100%;">
                      <div class="panel-outr">
                        <div class="panel-hdr"><spring:message code="question"></spring:message></div>
                        <div class="panel-body"> ${listBookingDetails.question} </div>
                      </div>
                    </div>
                    <div class="date-archive pt10">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="student.sessionschedule"></spring:message></div>
                        <div class="panel-body date-archive-txt">
                          <div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt=""> ${listBookingDetails.bookingStudentDate}</div>
                          
                        </div>
                      </div>
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="student.archive"></spring:message></div>
                        <div class="panel-body date-archive-txt">
                          <div class="s-archive">
                          <c:if test="${listBookingDetails.bookingDocName eq 'NA'}">
                            	${listBookingDetails.bookingDocName} <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> 
                            	</c:if>
                            	<c:if test="${listBookingDetails.bookingDocName ne 'NA'}">
                            	${listBookingDetails.bookingDocName}
                            	<a href="<%=request.getContextPath()%>${listBookingDetails.bookingDocPath}"  download><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a>
                             </c:if>
                           </div>
                           
                        </div>
                      </div>
                    </div></td>
                  <td>
                    <div class="tutor-db-Btns">
                     <c:if test="${listBookingDetails.accepted eq 'Y'}">
                      <a href="javascript:;" class="greenBtn-normal" name="startSession"  onclick="goToMeeting('${listBookingDetails.bookingId}');" id="startSession${listBookingDetails.bookingId}"><spring:message code="student.gotoclassroom"></spring:message> </a>
                      <a href="javascript:;" class="blueBtn-normal" onclick="cancelSession('${listBookingDetails.bookingId}','${listBookingDetails.fullName}','${user}');"><spring:message code="cancel"></spring:message> </a>
                 	 </c:if>
                 	<c:if test="${listBookingDetails.accepted eq 'N'}">
                     <a href="javascript:;" class="greenBtn-normal" ><spring:message code="approval.pending"></spring:message> </a>
                     <a href="javascript:;" class="blueBtn-normal" onclick="cancelUnapprovedSession('${listBookingDetails.bookingId}','${listBookingDetails.fullName}','${user}');"><spring:message code="cancel"></spring:message> </a>
                 	</c:if>
                     </div>
                    </td>
                </tr>
                
                <c:set var="count" value="1" scope="page"/>
                        </c:if>
                        </c:forEach>                       
                       <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                
                
              </tbody></table>
            </div>
          </div>
            
 
 <!--    ************************************ New code for REVIEW sessons view ***************************** -->
 

			<div class="tutr-screen mt-20">
			<h1 class="text-left">   My Review Sessions  </h1> 
			<div class="tutor-db-txt"  id="bookedReviewDetails">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
			<c:set var="count" value="0" scope="page"/>
			<c:forEach items="${listReviewBookingDetails}" var="listReviewBookingDetails">
			<c:if test="${listReviewBookingDetails.iscompleted eq 'N' && listReviewBookingDetails.isDeleted eq 'N'}">
			
			<tr>
			   <td width="13">  
			  <div class="indi-icon">
			  <c:if test="${listReviewBookingDetails.loginStatus eq 'Y'}">
			  <img class="status${listReviewBookingDetails.reviewTutorId} defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">
			  </c:if>
			
			 <c:if test="${listReviewBookingDetails.loginStatus eq 'N'}">
			  <img  class="status${listReviewBookingDetails.reviewTutorId} defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">
			 </c:if>
			         </div>
			         </td>
			
			       <td>
			  <div class="s-stu-img-outr">
			  
			   <div class="tutor-img"> 
			   
			      <c:if test="${listReviewBookingDetails.imageName !=null}">
			
			   <img alt="" src="<%=request.getContextPath()%>/profilePictures/${listReviewBookingDetails.reviewTutorId}/fileupload/${listReviewBookingDetails.reviewTutorId}${listReviewBookingDetails.imageName}"/>
			
			   </c:if>
			    <c:if test="${listReviewBookingDetails.imageName ==null && listReviewBookingDetails.imgUrl==null}">
			    <img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>  
			   
			     </c:if>
			      <c:if test="${listReviewBookingDetails.imgUrl !=null && listReviewBookingDetails.imageName ==null}">
			         <img alt="" src="${listReviewBookingDetails.imgUrl}"/>
			
			
			   </c:if>
			
			</div>
			                      <div class="tutor-img-txt">  ${listReviewBookingDetails.fullName} 
                      <c:if test="${listReviewBookingDetails.isFavourite eq 'N'}">
                                	 <span class="heartImg"><img onclick="setAsFavourite('${listReviewBookingDetails.reviewTutorId}');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>
                                	</c:if>
                                	<c:if test="${listReviewBookingDetails.isFavourite eq 'Y'}">
                                	 <span class="heartImg"><img onclick="setAsFavourite('${listReviewBookingDetails.reviewTutorId}');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>
                                	</c:if>
                      
                      <div class="subjects">${listReviewBookingDetails.subjectName}</div>
                       <div class="stars">
                                    <c:forEach var="i" begin="1" end="5">
                                    <c:choose>
                                    <c:when test="${i le listReviewBookingDetails.tutorRating}">
                                    <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                    </c:when>
                                    <c:otherwise>
                                    <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">
                                    </c:otherwise>
                                    </c:choose>
                                    </c:forEach>
                                    </div>  
                        
                    </div>  
                    
                       <div class="seeMore"><a onclick="openSeeMore('${listReviewBookingDetails.tutorProfileId}');"><spring:message code="student.seemore"/></a></div> 
                       
                       
                                </div>
                                
                    <div class="chatMsg clr">
                    
                    <a href="javascript:;" title="Chat">  <img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a> 
                    
                    <a href="javascript:;" title="Message"> <img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a> 
                     </div>
                     
                     </td>
                      
                        <td style="width: 100%;">
                                
                                
                       <div class="ques" style="width: 100%;">
                      <div class="panel-outr">
                            <div class="panel-hdr"><spring:message code="question"></spring:message></div>
                        <div class="panel-body">${listReviewBookingDetails.reviewQuestion} </div>
                       </div>
                     </div>  
                     
                     
                     
                     
                       <div class="date-archive pt10">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="student.sessionschedule"></spring:message></div>
                        <div class="panel-body date-archive-txt">
                        
                        
                        
                          <div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt="">${listReviewBookingDetails.bookingStudentDate}</div>
                         
                        </div>
                      </div>
                      
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="student.archive"></spring:message></div>
                        <div class="panel-body date-archive-txt">
						<div class="s-archive">
						<a href="<%=request.getContextPath()%>/${listReviewBookingDetails.studentFilePath }" title="${listReviewBookingDetails.studentFileName}" download>${listReviewBookingDetails.studentFileName}<img src="/miprofe/images/download-icon.png" alt=""></a> </div>

                        </div>
                      </div>
                    </div> 
                    
                    
                    
                    
                <div class="date-archive pt10">
                      <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="equivalent.rate"></spring:message></div>
                        <div class="panel-body">
                          
                          
                          
                          
                         
                           <c:if test="${listReviewBookingDetails.is_proposed_byTutor eq 'Y'}">
                           
                           <div class="equi-rate-txt"> <img src="<%=request.getContextPath()%>/images/pending-icon.png" alt=""/>${listReviewBookingDetails.tutorProposedTime}</div>
                          </c:if>
                          <c:if test="${listReviewBookingDetails.is_proposed_byTutor eq 'N'}">
                          
                          <div class="equi-rate-txt"> <img src="<%=request.getContextPath()%>/images/pending-icon.png" alt=""/>Pending    </div>
                          </c:if> 
                          
                 
                          
                                </div>
                      </div>
                    
                    
                    <div class="panel-outr panel-50">
                        <div class="panel-hdr"><spring:message code="tutor.archive"></spring:message> </div>
                        <div class="panel-body date-archive-txt">
                        
                        <c:if test="${listReviewBookingDetails.isCompletedByTutor eq 'Y'}">
                          <div class="s-archive"><a href="<%=request.getContextPath()%>/${listReviewBookingDetails.tutorDocPath }" title="${listReviewBookingDetails.tutorDocName}" download>${listReviewBookingDetails.tutorDocName}<img src="/miprofe/images/download-icon.png" alt=""></a> </div>
                            </c:if>
                              
                        <c:if test="${listReviewBookingDetails.isCompletedByTutor eq 'N'}">
                            <div class="s-archive"><spring:message code="pending"></spring:message> <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>  
                           </c:if>
                          </div>
                      </div>
                    </div> 
					        
                      <div class="txtArea pt10">
                       <c:if test="${listReviewBookingDetails.isCompletedByTutor eq 'N'}">
                         <textarea rows="3" placeholder="Comments..." readonly="readonly">
                         </textarea>
                          </c:if>	
                        
                           <c:if test="${listReviewBookingDetails.isCompletedByTutor eq 'Y'}">  
                            <textarea rows="3" placeholder="${listReviewBookingDetails.tutorComments}"  readonly="readonly">${listReviewBookingDetails.tutorComments}</textarea>
                              </c:if>	 
                    </div>  
                    
                    
                    
                    </td> 
           <td>
                  <div class="tutor-db-Btns">
                             
                             
                            <c:if test="${listReviewBookingDetails.is_proposed_byTutor eq 'Y' && listReviewBookingDetails.accepted eq 'N'}">
                            <a href="javascript:;" class="orangeBtn-normal"  onclick="approveProposal('${listReviewBookingDetails.reviewId}');" id="startSession${listReviewBookingDetails.reviewId}"><spring:message code="approve.proposal"></spring:message> </a>
                        	</c:if>
                        	
                        	<c:if test="${listReviewBookingDetails.is_proposed_byTutor eq 'N'}">
                            <a href="javascript:;" class="greenBtn-normal" ><spring:message code="approval.pending"></spring:message> </a>
                        	</c:if>
                        	
                        	<c:if test="${listReviewBookingDetails.is_proposed_byTutor eq 'Y' && listReviewBookingDetails.accepted eq 'Y' && listReviewBookingDetails.iscompleted eq 'N' && listReviewBookingDetails.isCompletedByTutor eq 'N'}">
                            <a href="javascript:;" class="orangeBtn-normal"  id="inprogressSession${listReviewBookingDetails.reviewId}"><spring:message code="revision.pending"></spring:message> </a>
                        	</c:if>
                        	
                        	<c:if test="${listReviewBookingDetails.is_proposed_byTutor eq 'Y' && listReviewBookingDetails.accepted eq 'Y' && listReviewBookingDetails.iscompleted eq 'N' && listReviewBookingDetails.isCompletedByTutor eq 'Y'}">
                            <a href="javascript:;" class="orangeBtn-normal" onclick="openMessagePopUp('${listReviewBookingDetails.reviewTutorId}','${listReviewBookingDetails.fullName}','${listReviewBookingDetails.reviewId}');"  id="rejectReviewWork${listReviewBookingDetails.reviewId}"><spring:message code="reject.review"></spring:message> </a>
                        	</c:if>
                        	
                        	<c:if test="${listReviewBookingDetails.isCompletedByTutor eq 'Y'}">
                            <a href="javascript:;" class="greenBtn-normal" onclick="rateTutorWork('${listReviewBookingDetails.reviewTutorId}','${listReviewBookingDetails.reviewId}','accept');"><spring:message code="evaluate.tutor"></spring:message> </a>
                        	</c:if>
                        	
                        	
                        	
                        	<c:if test="${listReviewBookingDetails.isCompletedByTutor eq 'N'}">
                        	<a href="javascript:;" class="blueBtn-normal" onclick="cancelReviewSession('${listReviewBookingDetails.reviewId}','${listReviewBookingDetails.fullName}','${user}');"><spring:message code="cancel"></spring:message> </a>
                            </c:if>	
                            </div> 
                            
                            
                          </tr>
                
                
                        </c:if>
                       </c:forEach>
                        
             </tbody> </table>
            </div>
          </div> 
  
 <!--    ************************************  REVIEW sessons view  ENDS ***************************** --> 
            
  <!--    ************************************ New code for sessons view ***************************** -->          
            <div class="ac-activity ac-activity-2">
           		<h1 class="text-left"><spring:message code="myprevious.sessions"/> </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable1">
                	<thead>
                      <tr>
                       <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="login.tutor"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th> <spring:message code="length"/></th>
                        
                        <th><spring:message code="question"/> </th>
                        <th ><spring:message code="student.archive"></spring:message></th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${dtoBookingDetailList}" var="dtoBookingDetail">
                      <c:if test="${dtoBookingDetail.iscompleted eq 'Y'}">
                      <tr>
                      <td>${dtoBookingDetail.newBookingDate }</td>
                      <td>${dtoBookingDetail.dateSession }</td>
                      <td>${dtoBookingDetail.tutorFullName }</td>
                      <td>${dtoBookingDetail.subjectName }</td>
                      <td>${dtoBookingDetail.bookingDuration } <spring:message code="mins"/></td>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.question}">${dtoBookingDetail.question}</div></td>
                      
                      <c:if test="${dtoBookingDetail.bookingDocPath eq 'NA'}">
                      <td ><a href="javascript:void(0);">${dtoBookingDetail.bookingDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${dtoBookingDetail.bookingDocPath ne 'NA'}">
                      <td><a href="<%=request.getContextPath()%>${dtoBookingDetail.bookingDocPath}" style="width:150px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.bookingDocName}" download>${dtoBookingDetail.bookingDocName}</a></td>
                      </c:if>
                     
                      </tr>
                       </c:if>
                       </c:forEach>
                    
                       </tbody>
                    </table>
                </div>
            </div>
            
            <!-- Previous Review Session Start-->
            
                       <div class="ac-activity ac-activity-2">
           		<h1 class="text-left"><spring:message code="myprevious.reviewwork"/> </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable2">
                	<thead>
                      <tr>
                       <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="login.tutor"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th> <spring:message code="equilavent.rate"/></th>                        
                        <th ><spring:message code="question"/> <spring:message code="login.student"/></th>
                        <th ><spring:message code="student.archive"></spring:message></th>
                         <th ><spring:message code="tutorcomment"></spring:message></th>
                         <th><spring:message code="tutorsolutionfile"></spring:message></th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${dtoReviewDetailList}" var="dtoReviewDetail">
                      <c:if test="${dtoReviewDetail.iscompleted eq 'Y'}">
                      <tr>
                      <td>${dtoReviewDetail.newBookingDate }</td>
                      <td>${dtoReviewDetail.dateSession }</td>
                      <td>${dtoReviewDetail.tutorFullName }</td>
                      <td>${dtoReviewDetail.subjectName }</td>
                      <td>${dtoReviewDetail.reviewDuration } <spring:message code="mins"/></td>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.reviewQuestion}">${dtoReviewDetail.reviewQuestion}</div></td>
                      
                      <c:if test="${dtoReviewDetail.studentDocPath eq 'NA'}">
                      <td ><a href="javascript:void(0);">${dtoReviewDetail.studentDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${dtoReviewDetail.studentDocPath ne 'NA'}">
                      <td ><a href="<%=request.getContextPath()%>${dtoReviewDetail.studentDocPath}" style="width:150px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.studentDocName}" download>${dtoReviewDetail.studentDocName}</a></td>
                      </c:if>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.tutorComments}">${dtoReviewDetail.tutorComments}</div></td>
                   
                        <c:if test="${dtoReviewDetail.tutorDocPath eq 'NA'}">
                      <td><a href="javascript:void(0);">${dtoReviewDetail.tutorDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${dtoReviewDetail.tutorDocPath ne 'NA'}">
                      <td ><a href="<%=request.getContextPath()%>${dtoReviewDetail.tutorDocPath}" style="width:150px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.tutorDocName}" download>${dtoReviewDetail.tutorDocName}</a></td>
                      </c:if>
                      </tr>
                       </c:if>
                       </c:forEach>
                    
                       </tbody>
                    </table>
                </div>
            </div>
            
            <!-- Previous Review Session End -->
            
        
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

<div id="editpopup" style="display : none;">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/student/sessionManage"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="popup.sessionnotstarted"/>
            </section>
        </div>
        </div>
        </div>
        </div>

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
        
        
  <div class="form-tutor-popup" id="confirmCancel" style="display:none;">
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
        </div>     
        
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
        

<!-- =============== reason form for Review Session cancel============================ -->
<div class="form-tutor-popup" id="cancelReasonReview" style="display:none;">
<form id="cancelReviewMessageForm" name="cancelReviewMessageForm"  method="POST">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="cancelDeleteReviewRequest();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
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
                    <td class="v-alignTop"><div ><textarea name="messageReviewReply" id="messageReviewReply" rows="4" cols="20"  maxlength="250"></textarea></div></td>
                  </tr>
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="send.message"></spring:message>' onclick="confirmCancelReviewSession();"></td>
                  </tr>
                </tbody></table>
                </div>
                <input type="hidden" id="reviewBookingId" name="reviewBookingId">
        </div>
   </div>
</form>
</div>        
 <!-- =============== reason form for Review Session cancel ENDS============================ -->       
             
        
<input type="hidden" id="deleteConfirmId" />  


  <div class="form-tutor-popup" id="deleteSuccess" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/student/sessionManage"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
                 <div class="rating-Txt" id="deleteMsg" >
            	  <spring:message code="sessionrequestDeleted"/>
            </div>
               
             
           <div class="book-session">
               <a href="<%=request.getContextPath()%>/student/sessionManage" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>  
        
        
        
        <!-- Rating PopUp For Review Session Start -->
        
         <div class="form-tutor-popup" id="ratingPopup" style="display:none;">
 <div class="popup-back"></div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="javascript:void(0);" onclick="hideRatingPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
           <div class="my-info-popup">
           	<div class="tRating">
            <h3><spring:message code="messagetoratetutor"/></h3>
			<div class="rate-con">
            	<div class="rate-conLt">
                	<div class="rateRow">
                    	<div class="rateTxt"><spring:message code="general.experienceReview"/></div>
                        <span id="ratingTutor">
                        	<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(1,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(2,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(3,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(4,'ratetutor');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(5,'ratetutor');">
                        </span>
                    </div>
                    <div class="rateRow">
                    	<div class="rateTxt"><spring:message code="knowledgeReview"/></div>
                        <span id="ratingTutorKnowledge">
                        	<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(1,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(2,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(3,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(4,'rateknowledge');">
                            <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="" onclick="changeRating(5,'rateknowledge');">
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
            <input type="button" class="greenBtn-normal" onclick="submitRating('Y');" value='<spring:message code="submit.rating"/>'>
            <input type="button" class="blueBtn-normal" onclick="submitRating('N');" value='<spring:message code="cancel"/>'>
            </p>
            
            </div>
           
                </div>
        	
        </div>
   </div>
 
 </div>
 
 
 <form name="submitRatingForm" id="submitRatingForm" method="POST" action="<%=request.getContextPath()%>/student/tutorReviewRating">
<input type="hidden" name="ratingId" id="ratingId">
<input type="hidden" name="ratingKnowledge" id="ratingKnowledge">

<input type="hidden" name="ratingRecomend" id="ratingRecomend">
<input type="hidden" name="comments" id="comments">
<input type="hidden" name="tutorUserId" id="tutorUserId" value="0">
<input type="hidden" name="reviewId" id="reviewId" value="0">
<input type="hidden" name="studentReply" id="studentReply" value="0">
<input type="hidden" name="reviewCancel" id="reviewCancel" value="0">
</form>



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

  <div class="form-tutor-popup" id="reviewSuccess"  style="display:none;" >
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeReviewSuccessPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
               <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
             <div class="rating-Txt" id="reviewAcceptMsg">
            </div>
           <div class="book-session">
               <a onclick="closeReviewSuccessPopUp();" class="greenBtn-normal"> <spring:message code="ok"></spring:message> </a>
                                
           </div>
        </div>
        </div>
        </div>


        
        <!-- Rating PopUp For Review Session End -->
             


</body>


<!--    ************************************ New code for sessons view ***************************** --> 
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
	
function cancelSession(bookingId,tutorName,studentName){
	$(window).scrollTop(0);
	$("#toUserName").text(tutorName);
	$("#fromUserName").text(studentName);
	$("#sessionBookingId").val(bookingId);
	$("#deleteConfirmId").val(bookingId);
	$("#cancelReason").show();
	}
	
	
function cancelUnapprovedSession(bookingId,tutorName,studentName){
	
	//alert("hi-----------");	
	
	$(window).scrollTop(0);
	$("#toUserName").text(tutorName);
	$("#fromUserName").text(studentName);
	$("#sessionBookingId").val(bookingId);
	$("#deleteConfirmId").val(bookingId);
	$("#cancelReason").show();
	}
	
// ========= for canceling Review Session===============

function cancelDeleteReviewRequest(bookingId,tutorName,studentName){
	$("#cancelReasonReview").hide();
	}	
	
function cancelReviewSession(bookingId,tutorName,studentName){
	$(window).scrollTop(0);
	$("#toReviewUserName").text(tutorName);
	$("#fromReviewUserName").text(studentName);
	$("#reviewBookingId").val(bookingId);
	$("#cancelReasonReview").show();
	}	
	
function confirmCancelReviewSession(){
	//$("#confirmCancel").hide();
	var message=$("#messageReviewReply").val();
	var bookingId=$("#reviewBookingId").val();
	if ($('#cancelReviewMessageForm').valid()) {
		$("#cancelReasonReview").hide();
	var url='<%=request.getContextPath()%>/student/cancelBookedReviewSessionwithMessage';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingId:bookingId,message:message},
		success:function(response){
			if(response.modelMap.deleteSuccess=='Y'){
				$(window).scrollTop(0);
				$("#deleteSuccess").show();
				
			}
			else{
			//	alert("Err");
			}
		}
	});
	}
	}		
//========= for canceling Review Session ENDS ===============	
	
	
function cancelDeleteSession(){
	$("#confirmCancel").hide();

	}
	
function cancelDeleteSessionRequest(){
	$("#cancelReason").hide();

	}
	
function confirmCancelSession(){
	$("#confirmCancel").hide();
	var message=$("#messageReply").val();
	var bookingId=$("#sessionBookingId").val();
	if ($('#cancelMessageForm').valid()) {
		$("#cancelReason").hide();
	var url='<%=request.getContextPath()%>/student/cancelBookedSessionwithMessage';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingId:bookingId,message:message},
		success:function(response){
			if(response.modelMap.cancelStatus==true){
				$(window).scrollTop(0);
				$("#deleteSuccess").show();
				if(response.modelMap.penaltyApplied=='Y'){
				$("#deleteMsg").html('<spring:message code="sessionDeletedWithPenalty"></spring:message>');
				}
				else{
					$("#deleteMsg").html('<spring:message code="sessionDeletedWithoutPenalty"></spring:message>');
				}
				
			}
			else{
			//	alert("Err");
			}
		}
	});
	}
	
	
	//var bookingId = $("#deleteConfirmId").val();
		<%-- var url='<%=request.getContextPath()%>/student/cancelBookedSessionwithTutor';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{bookingId:bookingId},
			success:function(response){
				if(response.modelMap.cancelStatus==true){
					$(window).scrollTop(0);
					$("#deleteSuccess").show();
					if(response.modelMap.penaltyApplied=='Y'){
					$("#deleteMsg").html('<spring:message code="sessionDeletedWithPenalty"></spring:message>');
					}
					else{
						$("#deleteMsg").html('<spring:message code="sessionDeletedWithoutPenalty"></spring:message>');
					}
					
				}
				else{
				//	alert("Err");
				}
			}
		}); --%> 
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
	
	
	
</script>

<script type="text/javascript">


//	 function getNewBookingDetailsStatus(){
	var previousListSize="0";
	setInterval(function(){
	var userFullName='${user}';
	var url='<%=request.getContextPath()%>/student/getStudentBookingDetailsWithTutor';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			var status="0";
			var tutorData='<table width="100%" border="0" cellspacing="0" cellpadding="0"><tbody>';
			if(response != null){
				if(response.modelMap.listNewBookingDetailsize!=previousListSize){
				var res="";
				
				 $.each( response.modelMap.listNewBookingDetails, function(index,value) {
						status="1";
						if(value.iscompleted=="N" && value.isDeleted=="N")
					    	 {
							 tutorData+='<tr>';
							tutorData+='<td width="13">';
							tutorData+='<div class="indi-icon">';
							tutorData+='<img  class="status'+value.tutorId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
							tutorData+='</div> </td>';
							tutorData+='<td>';
							tutorData+='<div class="s-stu-img-outr">';
							
						tutorData+='<div class="tutor-img">';
							if(value.imageName!=null){
						tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.tutorId+'/fileupload/'+value.tutorId+value.imageName+'"/>';
		                            }	
							if(value.imageName==null && value.imgUrl==null){
						tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
		                            }
							if(value.imageName==null && value.imgUrl!=null){
						tutorData+=   '<img alt="" src="'+value.imgUrl+'"/>';
		                            }
						tutorData+='</div>';
                   
                  tutorData+='<div class="tutor-img-txt">'+value.fullName; 
						if(value.isFavourite=='N'){
					 tutorData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.tutorId+');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>';
								}
						if(value.isFavourite=='Y'){
					 tutorData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.tutorId+');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>';
								}				
									
					tutorData+='<div class="subjects ellipsis">'+value.subjectName+'</div>';				
					
                 tutorData+='<div class="stars">';
                         for(var i=1;i<=5;i++){
								  if(i <= value.tutorRating){
									  tutorData+='<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
								  }
								  else
									  {
									  tutorData+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
									  }
							  }		
					tutorData+='</div>';      
                 tutorData+='</div>';
					  
					tutorData+='<div class="seeMore"><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div></div>'; 
                 
					tutorData+='<div class="chatMsg clr"><a href="javascript:;" title="Chat"><img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a>'+
                  '<a href="javascript:;" title="Message"><img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a></div>'; 
					 
					tutorData+='</td>';
					tutorData+='<td style="width: 100%;">';
					
			   tutorData+='<div class="ques" style="width: 100%;">';
               tutorData+='<div class="panel-outr"><div class="panel-hdr"><spring:message code="question"></spring:message></div>';
               tutorData+='<div class="panel-body">'+value.question+'</div></div>';
               tutorData+='</div>';
				 tutorData+='<div class="date-archive pt10">';
				 tutorData+='<div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="student.sessionschedule"></spring:message></div>'+
                     '<div class="panel-body date-archive-txt">'+
                     '<div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt="">'+value.bookingStudentDate+'</div></div></div>';
				tutorData+='<div class="panel-outr panel-50">';
               tutorData+='<div class="panel-hdr"><spring:message code="student.archive"></spring:message></div>'+
                     '<div class="panel-body date-archive-txt"><div class="s-archive">';
						if(value.bookingDocPath=='NA'){
						tutorData+=value.bookingDocName+'<a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a>'; 		  
						  }
						  else
							  {
						tutorData+=value.bookingDocName+'<a href="<%=request.getContextPath()%>'+value.bookingDocPath+'" download><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a>';	  
							  }	 
							 
						tutorData+='</div>';
                        
                     tutorData+='</div>';
						tutorData+='</div>';
					tutorData+='</div>';
					tutorData+='</td>';
					tutorData+='<td>';
					tutorData+='<div class="tutor-db-Btns">';
                   if(value.accepted=='Y'){ 
                   tutorData+='<a href="javascript:;" class="greenBtn-normal" name="startSession" onclick="goToMeeting('+value.bookingId+');" id="startSession'+value.bookingId+'"><spring:message code="student.gotoclassroom"></spring:message></a>';
	 				  tutorData+='<a href="javascript:;" class="blueBtn-normal" onclick="cancelSession(\''+value.bookingId+'\',\''+value.fullName+'\',\''+userFullName+'\');"><spring:message code="cancel"></spring:message> </a>';
                              }																	
                 if(value.accepted=='N'){  
                     tutorData+='<a href="javascript:;" class="greenBtn-normal" name="startSession" id="startSession'+value.bookingId+'"><spring:message code="approval.pending"></spring:message></a>';
	 				  tutorData+='<a href="javascript:;" class="blueBtn-normal" onclick="cancelUnapprovedSession(\''+value.bookingId+'\',\''+value.fullName+'\',\''+userFullName+'\');"><spring:message code="cancel"></spring:message> </a>';
                                }
                  tutorData+='</div>';
					 tutorData+='</td>';
					 
					 tutorData+='</tr>';
					
					    	 }
		      });
				previousListSize=response.modelMap.listNewBookingDetailsize;
				if(status=='0'){
					tutorData+='<h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/></h3>';				
				}
                tutorData+= '</tbody></table>';
				$("#bookedTutorDetails").html(tutorData);
				} // check list size
			}
		}
	});  
    
	}, 8000); 
 
function viewChatHistory(bookingId){

var url='<%=request.getContextPath()%>/getChatHistoryDetails';
$.ajax({
	url:url,
	method:'GET',
	async :false,
	data:{bookingId:bookingId},
	success:function(response){
		location.reload();
	}
}); 

}
 

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


  <script type="text/javascript">
 $(document).ready(function(){
	 
	    $('#myTable').DataTable();
	    $('#myTable1').DataTable({
			"order": [[ 0, "desc" ]],
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
	    $('#myTable2').DataTable({
			"order": [[ 0, "desc" ]],
		       "aoColumns": [
		                       {"bVisible": false},
		                         {"iDataSort": 0},                              
		                         null,
		                         null,
		                         null,
		                         null,
		                         null,
		                         null,
		                         null
		                        ]
		});
	    
	    
		 
		 
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
	    
	    
	});
 </script>
 
  <script type="text/javascript">
  $(function() {
	  $( ".ellipsis1" ).tooltip({
		  open: function (event, ui) {
		        ui.tooltip.css("word-break", "break-all");
		        ui.tooltip.css("font-size", "12px");
		    },
	      position: {
	        my: "top",
	        at: "top+15"
	      }
	    });
  });
  
  
 function approveProposal(bookingId){
	 var url='<%=request.getContextPath()%>/student/acceptReviewProposal';
	 $.ajax({
	 	url:url,
	 	method:'GET',
	 	async :false,
	 	data:{bookingId:bookingId},
	 	success:function(response){
	 		var message="";
	 		if(response.modelMap.updateSuccess=='Y'){
	 			message='<spring:message code="tutor.proposal.accepted"/>';
	 		}
	 		else{
	 			message='<spring:message code="booking.failure"/>';
	 		}
	 		$("#reviewAcceptMsg").html(message);
	 		$("#reviewSuccess").show();
	 		$(window).scrollTop(0);
	 	}
	 }); 
	 
 } 
 
 function closeReviewSuccessPopUp(){
		$("#reviewSuccess").hide();
		location.reload();
	}
  
  </script>
  
  <script type="text/javascript">
  
  function rateTutorWork(tutorUserId,reviewId,studentReply){
	  
	  $("#tutorUserId").val(tutorUserId);
	  $("#reviewId").val(reviewId);
	  $("#studentReply").val(studentReply);
	  
	  
		 $(window).scrollTop(0);
		$("#ratingPopup").show();
		 
	 }
	  
	  

function changeRating(rating,ratingName){
	var ratetutor="";
	var rateknowledge="";
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

function submitRating(reviewCancel){
	
	var comments=$("#ratingComment").val();
	
	$("#comments").val(comments);
	$("#reviewCancel").val(reviewCancel);
	
	$("#submitRatingForm").submit();
}


function openMessagePopUp(tutorId,tutorName,reviewId){
	
	var studentName='${user}';
	
	$("#tutorMessageUserId").val(tutorId);
	$("#reviewId").val(reviewId);
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

function hideRatingPopUp(){
	$("#ratingPopup").hide();
}


function submitSendMessageForm(){
	if ($('#sendMessageForm').valid()) {
        var tutorMessageUserId =  $("#tutorMessageUserId").val();
        var reviewId=$("#reviewId").val();
        var messageText = $("#message").val();
        
    	var url='<%=request.getContextPath()%>/student/sendMessageHome';
    	$.ajax({
    		url:url,
    		method:'GET',
    		async :false,
    		data:{tutorMessageUserId:tutorMessageUserId,messageText:messageText},
    		success:function(response){
    			if(response=="Y"){
    			$(window).scrollTop(0);
    			$("#sendMessagepopup").hide();
    			rateTutorWork(tutorMessageUserId,reviewId,'reject');
    			}
    			
    		},
    	}); 
    }
	  
	 
}


//=================== reload review session data =================

	 setInterval(function(){
		var url='<%=request.getContextPath()%>/student/getReviewSessionDetailRequests';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response)
			{
				var reviewSessionData='<table width="100%" border="0" cellspacing="0" cellpadding="0"><tbody>';
				
			if(response != null && response.modelMap.listReviewDetailsList!=null)
				{
			 $.each(response.modelMap.listReviewDetailsList, function(index,value){		
			if(value.iscompleted=='N' && value.isDeleted=='N')
			      {
			
			reviewSessionData+=	'<tr><td width="13"> <div class="indi-icon">';
					
					if(value.loginStatus=='Y')
					{
				reviewSessionData+=	'<img class="status'+value.reviewTutorId+' defaultStatus" src="<%=request.getContextPath()%>/images/green-bullet.png" alt="">';
				    }
					
					if(value.loginStatus=='N')
					{
				reviewSessionData+='<img  class="status'+value.reviewTutorId+' defaultStatus" src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt="">';
					}
					
					
				reviewSessionData+='</div></td><td><div class="s-stu-img-outr"><div class="tutor-img">';
				
						 
						 
						 
						 
				if(value.imageName!=null)
				      {
					
					  
					
				     reviewSessionData+='<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.reviewTutorId+'/fileupload/'+value.reviewTutorId+value.imageName+'"/>';
				                            }	

				                  if(value.imageName==null && value.imgUrl==null)
				                  {
				     reviewSessionData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
				                            }

				                  if(value.imageName==null && value.imgUrl!=null)
				                  {
				     reviewSessionData+=   '<img alt="" src="'+value.imgUrl+'"/>';
				     
				    
				                            }
							
							
						
							
							
						reviewSessionData+='</div><div class="tutor-img-txt">'+value.fullName;
						
						if(value.isFavourite=='N')
						{
						reviewSessionData+='<span class="heartImg"><img onclick="setAsFavourite('+value.reviewTutorId+');" src="<%=request.getContextPath()%>/images/heart-grey.png"/></span>';
						}
						
						if(value.isFavourite=='Y')
						{
						reviewSessionData+= '<span class="heartImg"><img onclick="setAsFavourite('+value.reviewTutorId+');" src="<%=request.getContextPath()%>/images/heart-red.png"/></span>';
						}
							
							
						reviewSessionData+='<div class="subjects">'+value.subjectName+'</div><div class="stars">';
							
							
							for(var i=1;i<=5;i++)
							{
							 if(i <= value.tutorRating)
							 {
							reviewSessionData+='<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
							  }
							  else
								  {
								  reviewSessionData+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
								  }
						       }
							
							  reviewSessionData+='</div>';
							  
							  reviewSessionData+='</div>';
							  
							  reviewSessionData+='<div class="seeMore"><a onclick="openSeeMore('+value.tutorProfileId+');"><spring:message code="student.seemore"/></a></div>';
							  
		 reviewSessionData+='<div class="chatMsg clr"><a href="javascript:;" title="Chat">  <img src="<%=request.getContextPath()%>/images/chat-icon2.png" alt=""></a><a href="javascript:;" title="Message"> <img src="<%=request.getContextPath()%>/images/msg-icon2.png" alt=""></a></div></td>';
 
		reviewSessionData+=	'<td style="width: 100%;"><div class="ques" style="width: 100%;"><div class="panel-outr"><div class="panel-hdr"><spring:message code="question"></spring:message></div><div class="panel-body">'+value.reviewQuestion+'</div></div></div> <div class="date-archive pt10"><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="student.sessionschedule"></spring:message></div><div class="panel-body date-archive-txt"><div class="date"><img src="<%=request.getContextPath()%>/images/cal-icon-blue.png" alt="">'+value.bookingStudentDate+'</div></div></div>'; 

		
		reviewSessionData+='<div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="student.archive"></spring:message></div><div class="panel-body date-archive-txt"><div class="s-archive"><a href="<%=request.getContextPath()%>/'+value.studentFilePath+'" title="'+value.studentFileName+'" download>'+value.studentFileName+'<img src="/miprofe/images/download-icon.png" alt=""></a> </div></div></div></div><div class="date-archive pt10"><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="equivalent.rate"></spring:message></div><div class="panel-body">';

		  if(value.is_proposed_byTutor =='Y')
		        {
			reviewSessionData+='<div class="equi-rate-txt"> <img src="<%=request.getContextPath()%>/images/pending-icon.png" alt=""/>'+value.tutorProposedTime+'</div>';
						}
		  
		  
		  
		if(value.is_proposed_byTutor =='N')
		        {
			reviewSessionData+='<div class="equi-rate-txt"> <img src="<%=request.getContextPath()%>/images/pending-icon.png" alt=""/>Pending</div>';
					}
					
		  
		
					reviewSessionData+='</div></div><div class="panel-outr panel-50"><div class="panel-hdr"><spring:message code="tutor.archive"></spring:message></div><div class="panel-body date-archive-txt">';
					
			if(value.isCompletedByTutor =='Y')
		        {
				
				reviewSessionData+='<div class="s-archive"><a href="<%=request.getContextPath()%>/'+value.tutorDocPath+'" title="'+value.tutorDocName+'" download>'+value.tutorDocName+'<img src="/miprofe/images/download-icon.png" alt=""></a> </div>';
				
 				   }	
		
			if(value.isCompletedByTutor =='N')
		        {
			reviewSessionData+='<div class="s-archive"> <spring:message code="pending"></spring:message>  <a href="javascript:void(0);"><img src="<%=request.getContextPath()%>/images/download-icon.png" alt=""></a> </div>';
				}		
				
			
			
					reviewSessionData+='</div></div></div>';
					
			
					
					reviewSessionData+='<div class="txtArea pt10">';
		                       if(value.isCompletedByTutor == 'N')
							   {
		                    reviewSessionData+='<textarea rows="3" placeholder="Comments..." readonly="readonly"></textarea>';
					        }
					 
					 if(value.isCompletedByTutor == 'Y')
					 {
					reviewSessionData+='<textarea rows="3" placeholder="'+value.tutorComments+'" readonly="readonly">'+value.tutorComments+'</textarea>';
					 }
					 
					 
					
					  
					  reviewSessionData+='</div></td><td><div class="tutor-db-Btns">';
					  
					  if(value.is_proposed_byTutor=='Y' && value.accepted=='N')
					  {
					reviewSessionData+='<a href="javascript:;" class="orangeBtn-normal"  onclick="approveProposal('+value.reviewId+');" id="startSession'+value.reviewId+'">'+'<spring:message code="approve.proposal"></spring:message> </a>';
					  }
					
					  
					if(value.is_proposed_byTutor=='N')
					 {
					reviewSessionData+='<a href="javascript:;" class="greenBtn-normal" ><spring:message code="approval.pending"></spring:message> </a>';
					}
					
					if(value.is_proposed_byTutor=='Y' && value.accepted=='Y' && value.iscompleted=='N' && value.isCompletedByTutor=='N')
					 {
					reviewSessionData+='<a href="javascript:;" class="orangeBtn-normal"  id="inprogressSession'+value.reviewId+'"><spring:message code="revision.pending"></spring:message> </a>';
					}
					
					
	
					if(value.is_proposed_byTutor=='Y' && value.accepted=='Y' && value.iscompleted=='N' && value.isCompletedByTutor=='Y')
					 {
					reviewSessionData+='<a href="javascript:;" class="orangeBtn-normal"  onclick="openMessagePopUp(\''+value.reviewTutorId+'\',\''+value.fullName+'\',\''+value.reviewId+'\');"  id="rejectReviewWork'+value.reviewId+'"><spring:message code="reject.review"></spring:message> </a>';
					}
					
					
					if(value.isCompletedByTutor=='Y')
					 {
						var stuResponse='accept';
					reviewSessionData+='<a href="javascript:;" class="greenBtn-normal" onclick="rateTutorWork(\''+value.reviewTutorId+'\',\''+value.reviewId+'\',\''+stuResponse+'\');">'+'<spring:message code="evaluate.tutor"></spring:message> </a>';
					}
					
				
					
					if(value.isCompletedByTutor=='N')
					 {
					reviewSessionData+='<a href="javascript:;" class="blueBtn-normal" onclick="cancelReviewSession(\''+value.reviewId+'\',\''+value.fullName+'\',\''+value.studentFullName+'\');">';
					reviewSessionData+='<spring:message code="cancel"></spring:message> </a></div></tr></tbody>';
					}	
		                              
								 }             
				
				 });

						$("#bookedReviewDetails").html(reviewSessionData);			
				 }
				
							 if(response.modelMap.listNewReviewDetailSize==0)
							 {
								var reviewSessionData='<div style="width: 100%; height: 400px; overflow: auto;"><ul><h3 style="color: #3a4e62;padding:10px;"><spring:message code="norecord.found"/> </h3></ul></div>';
								$("#bookedReviewDetails").html(reviewSessionData);
							} 
						}
					});
				   
					},8000); 


</script>

</html>
