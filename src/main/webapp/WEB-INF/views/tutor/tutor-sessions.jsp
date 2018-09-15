<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%          response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title><spring:message code="tutor.MiProfeTutorSessions" /></title>
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



<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/jquery-ui.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/styleDate.css">

<!-- =============================for jquery full clendar============================ -->



<link href="<%=request.getContextPath()%>/css/calendar/fullcalendar.css" rel='stylesheet' />
<link href="<%=request.getContextPath()%>/css/calendar/fullcalendar.print.css" rel='stylesheet' media='print' />
<script src="<%=request.getContextPath()%>/js/calendar/fullcalendar.min.js"></script>
<script src="<%=request.getContextPath()%>/js/calendar/dateFormat.js"></script>
<!-- =============================for jquery full clendar============================ -->


 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
 <link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet">

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
	    
	    $('#myTable3').DataTable({
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
	    
	    
	});
 </script>
 <style type="text/css">
 .fc-agenda table tr{
 height:40px;
 }
 
 </style>
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
            <div class="ac-activity ac-activity-2 mt0">
           		<h1 class="text-left"><spring:message code="myscheduled.sessions"/></h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
                	<thead>
                      <tr>
                       <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="time"/></th>
                       
                        <th><spring:message code="student"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th><spring:message code="question"/> </th>
                        
                           <th><spring:message code="student.archive"></spring:message> </th>
                        
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${dtoBookingDetailList}" var="dtoBookingDetail">
                      <c:if test="${dtoBookingDetail.isDeleted eq 'N' && dtoBookingDetail.iscompleted eq 'N' && dtoBookingDetail.accepted eq 'Y'  && dtoBookingDetail.isSessionTimeExpire eq 'N'}">
                      <tr>
                       
                        <td>${dtoBookingDetail.dateSession }</td>
                        <td>${dtoBookingDetail.timeSession} </td>
                        <td>${dtoBookingDetail.studentFullName }</td>
                        <td>${dtoBookingDetail.subjectName }</td>
                        
                         <td ><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.question}">${dtoBookingDetail.question }</div></td>
                         <c:if test="${dtoBookingDetail.bookingDocPath eq 'NA'}">
                         <td>  <a href="javascript:void(0);">${dtoBookingDetail.bookingDocName}</a></td>
                         </c:if>
                         <c:if test="${dtoBookingDetail.bookingDocPath ne 'NA'}">
                         <td>  <a	href="<%=request.getContextPath()%>${dtoBookingDetail.bookingDocPath}" style="width:200px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.bookingDocName}" download>${dtoBookingDetail.bookingDocName}</a></td>
                         </c:if>
                         
                      <%--   <input type="text" maxlength="11"  value="${dtoBookingDetail.bookingStudentDate}" class="date-input"  readonly="readonly"> --%>
                        
                      </tr>
                      </c:if>
                       </c:forEach>
                       </tbody>
                    </table>
                </div>
            </div>
            <div class="tutor-prev-activity" style="  width: 100% !important;">
            <div class="ac-activity" style="  width: 100% !important;">
           		<h1 class="text-left"><spring:message code="myprevious.sessions"/> </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable1">
                	<thead>
                      <tr>
                       <th><spring:message code="student.date"/> </th>
                    <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="student"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th> <spring:message code="length"/></th>
                        <th><spring:message code="question"/> </th>
                        <th><spring:message code="student.archive"></spring:message> </th>
                      </tr>
                      </thead>
                      <tbody>
                       <c:forEach items="${dtoBookingDetailList}" var="dtoBookingDetail">
                      <c:if test="${dtoBookingDetail.iscompleted eq 'Y'}">
                      <tr>
                      <td>${dtoBookingDetail.newBookingDate }</td>
                      <td>${dtoBookingDetail.dateSession }</td>
                      <td>${dtoBookingDetail.studentFullName }</td>
                      <td>${dtoBookingDetail.subjectName }</td>
                      <td>${dtoBookingDetail.bookingDuration } <spring:message code="mins"/></td>
                      <td ><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.question}">${dtoBookingDetail.question }</div></td>
                      
                      <c:if test="${dtoBookingDetail.bookingDocPath eq 'NA'}">
                         <td>  <a href="javascript:void(0);">${dtoBookingDetail.bookingDocName}</a></td>
                         </c:if>
                         <c:if test="${dtoBookingDetail.bookingDocPath ne 'NA'}">
                         <td>  <a	href="<%=request.getContextPath()%>${dtoBookingDetail.bookingDocPath}" style="width:200px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.bookingDocName}" download>${dtoBookingDetail.bookingDocName}</a></td>
                         </c:if>
                      
                        
                      </tr>
                      </c:if>
                       </c:forEach>
                       </tbody>
                    </table>
                    
                </div>
                 
            </div>
            
             <%-- <div class="my-avlblity">
            	<h1 class="text-left"><spring:message code="my.availability"/></h1>
            	
            </div>  --%>
            
            </div>
            <div class="clr"></div>
            
 
 
 
 
        <!--      ========================== My  Scheduled Review WORK======================================== -->
             <div class="tutor-prev-activity" style="  width: 100% !important;">
            <div class="ac-activity" style="  width: 100% !important;">
           		<h1 class="text-left"> <spring:message code="my.scheduled.reviewwork"/>  </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable3">
                	<thead>
                      <tr>
                        
                        <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="student"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th> <spring:message code="equilavent.rate"/></th>                        
                        <th ><spring:message code="question"/> <spring:message code="login.student"/></th>
                        <th ><spring:message code="student.archive"></spring:message></th>
                        
                      </tr>
                      </thead>
                      
                     <tbody>
                      <c:forEach items="${dtoReviewDetailList}" var="dtoReviewDetail">
                      <c:if test="${dtoReviewDetail.iscompleted eq 'N' && dtoReviewDetail.accepted eq 'Y' && dtoReviewDetail.isCompletedByTutor eq 'N'  && dtoReviewDetail.isDeleted eq 'N'}">
                      <tr>
                      <td>${dtoReviewDetail.newBookingDate }</td>
                      <td>${dtoReviewDetail.dateSession }</td>
                      <td>${dtoReviewDetail.studentFullName }</td>
                      <td>${dtoReviewDetail.subjectName }</td>
                      <td>${dtoReviewDetail.reviewDuration } <spring:message code="mins"/></td>
                      <td><div style="width:200px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.reviewQuestion}">${dtoReviewDetail.reviewQuestion}</div></td>
                      
                      <c:if test="${dtoReviewDetail.studentDocPath eq 'NA'}">
                      <td ><a href="javascript:void(0);">${dtoReviewDetail.studentDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${dtoReviewDetail.studentDocPath ne 'NA'}">
                      <td ><a href="<%=request.getContextPath()%>${dtoReviewDetail.studentDocPath}" style="width:200px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.reviewQuestion}" download>${dtoReviewDetail.studentDocName}</a></td>
                      </c:if>
                   
                      </tr>
                       </c:if>
                       </c:forEach> 
                    
                       </tbody>

                       
                    </table>
                </div>
            </div>
            </div>
            <div class="clr"></div>
 
 
 
 
            
       <!--      ========================== My Previous Review Sessions======================================== -->
             <div class="tutor-prev-activity" style="  width: 100% !important;">
            <div class="ac-activity" style="  width: 100% !important;">
           		<h1 class="text-left"> <spring:message code="myprevious.reviewwork"/>  </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable2">
                	<thead>
                      <tr>
                        
                        <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="student"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th> <spring:message code="equilavent.rate"/></th>                        
                        <th><spring:message code="question"/> <spring:message code="login.student"/></th>
                        <th><spring:message code="student.archive"></spring:message></th>
                         <th><spring:message code="tutorcomment"></spring:message></th>
                         <th><spring:message code="tutorsolutionfile"></spring:message></th>
                        
                        
                      </tr>
                      </thead>
                      
                     <tbody>
                      <c:forEach items="${dtoReviewDetailList}" var="dtoReviewDetail">
                      <c:if test="${dtoReviewDetail.iscompleted eq 'Y'}">
                      <tr>
                      <td>${dtoReviewDetail.newBookingDate }</td>
                      <td>${dtoReviewDetail.dateSession }</td>
                      <td>${dtoReviewDetail.studentFullName }</td>
                      <td>${dtoReviewDetail.subjectName }</td>
                      <td>${dtoReviewDetail.reviewDuration } <spring:message code="mins"/></td>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.reviewQuestion}">${dtoReviewDetail.reviewQuestion}</div></td>
                      
                      <c:if test="${dtoReviewDetail.studentDocPath eq 'NA'}">
                      <td><a href="javascript:void(0);">${dtoReviewDetail.studentDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${dtoReviewDetail.studentDocPath ne 'NA'}">
                      <td ><a href="<%=request.getContextPath()%>${dtoReviewDetail.studentDocPath}" style="width:150px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.studentDocName}" download>${dtoReviewDetail.studentDocName}</a></td>
                      </c:if>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.tutorComments}">${dtoReviewDetail.tutorComments}</div></td>
                   
                        <c:if test="${dtoReviewDetail.tutorDocPath eq 'NA'}">
                      <td ><a href="javascript:void(0);">${dtoReviewDetail.tutorDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${dtoReviewDetail.tutorDocPath ne 'NA'}">
                      <td ><a href="<%=request.getContextPath()%>${dtoReviewDetail.tutorDocPath}" style="width:100px;" class="fl ellipsis ellipsis1" title="${dtoReviewDetail.tutorDocName}" download>${dtoReviewDetail.tutorDocName}</a></td>
                      </c:if>
                      </tr>
                       </c:if>
                       </c:forEach> 
                    
                       </tbody>

                       
                    </table>
                </div>
            </div>
             <div class="my-avlblity">
            	<h1 class="text-left"><spring:message code="my.availability"/></h1>
            </div> 
            </div>
            <div class="clr"></div>
            
            
            
          <div  id='calendar'></div>
        	
        </div>
      </div>
    </div>
       
  </section>
  <!--//Mid Section--> 
</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>
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
 
  

<%-- function acceptBooking(bookingId){
	var url='<%=request.getContextPath()%>/tutor/acceptAndCreateScribblarRoom';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingId:bookingId},
		success:function(response){
		}
		
	}); 
} --%>

<%-- function goToMeeting(bookingId){
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
	
	
} --%>
</script> 



<div id="editpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeEditPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="editpopupExperience">
                  <tbody>
                  
                   <tr>
                    <td class="edit-exp-hdr"><spring:message code="metting.details"></spring:message></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="session.date"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="sessionDate" name="sessionDate" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="subject.name"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="subject" name="subject" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="student.name"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="student" name="student" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="student.intime"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="studentIn" name="studentIn" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="tutor.intime"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="tutorIn" name="tutorIn" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="session.duration"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="duration" name="duration" /></div></td>
                  </tr>
                  
                  <tr>
                    <th><spring:message code="session.endtime"></spring:message> </th>
                    <td>:</td> 
                  <td><div class="popUp-table-rt"><input type="text" placeholder=""  id="endTime" name="endTime" /></div></td>
                  </tr>
                 
                </tbody></table>
                </div>
		        </div>
		   </div>
		</div>



</body>


<script type="text/javascript">

$(document).ready(function() {
	
    var date = new Date();
    
  $('#calendar').fullCalendar({
    	
    	defaultView: 'agendaWeek',
    	height: 410,
    	width: 300,
    //	slotEventOverlap: false,
    	
        
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek'
                },
            selectable: true,
            selectHelper: true,
            timeFormat: {
                agenda: 'H:mm{ - h:mm}'
            },
            
            eventSources: [
                           {
                               url: '<%=request.getContextPath()%>/tutor/getCAlData',
                               type: 'GET',
                                   
                               error: function () {
                                }
                               }
                   ]  
         
            });
 
    });

</script>

<script type="text/javascript">

function viewMeetingDetails(bookingId){
	$(window).scrollTop(0);
	var url='<%=request.getContextPath()%>/tutor/viewPreviousSessionDetails';
	$.ajax({
		url:url,
		method:'GET',
		dataType: "json",
		contentType: "application/json",
		data:{bookingId:bookingId},
		success:function(response){
			if(response != null){
				
			$("#sessionDate").val(response.modelMap.dtoBookingDetail.bookingDate);
			$("#subject").val(response.modelMap.dtoBookingDetail.subjectName);
			$("#student").val(response.modelMap.dtoBookingDetail.fullName);
			$("#studentIn").val(response.modelMap.dtoBookingDetail.studentInTime);
			$("#tutorIn").val(response.modelMap.dtoBookingDetail.tutorInTime);
			$("#duration").val(response.modelMap.dtoBookingDetail.bookingDuration);
			$("#endTime").val(response.modelMap.dtoBookingDetail.sessionEndTime);
			
			$("#editpopup").show();
			}}
	});
}

	function closeEditPopUp(){
		$("#editpopup").hide();
	}

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
  
  
  
  </script>
</html>
