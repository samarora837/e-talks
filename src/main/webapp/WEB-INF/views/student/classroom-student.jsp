<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title><spring:message code="student.classroom" /> </title>
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

<!-- <LINK href="http://media.muchosmedia.com/brainwave/style.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="http://media.muchosmedia.com/scribblar/scripts/includes.js"></script>

<script src="<%=request.getContextPath()%>/js/timer/jquery-backward-timer.min.js"></script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>

<div id="main">
<%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>
<!--Mid Section-->
<section class="container">
  <div class="inner-row">
    <div class="classroom-stu-top">
      <ul>
        <li style="width:30%; text-align: left;"><span><spring:message code="tutor" /> :</span> ${dtoScribblarMeeting.tutorFormattedName}</li>
        <li style="width:23%; text-align: left;"><span><spring:message code="subject" /> :</span> ${dtoScribblarMeeting.subjectName}</li>
        <li style="width:23%; text-align: left;"><span><spring:message code="student.date" /> :</span> ${dtoScribblarMeeting.formattedBookingDate}</li>
        <li style="width:24%; text-align: left;"><span><spring:message code="session.time" />  :</span> ${dtoScribblarMeeting.bookingDuration} <spring:message code="mins"></spring:message></li>
        <li id="remainTime" style="width:30%; text-align: left;"><span><spring:message code="remaining.time" /> :</span> <span id="meetingTimer" class="timer"></span></li>
       
        <li style="text-align: left;"><span><a id="endsessionscribblar" style="display:none;"  onclick="endSession();" class="mis-sessions scribLink"><spring:message code="end.session" /></a> </span></li>
        <li style="text-align: left;"><span><a id="startsessionscribblar" style="display:none;"  onclick="startSession('${dtoScribblarMeeting.bookingId}');" class="mis-sessions scribLink"><spring:message code="start.session" /></a> </span></li>
      </ul>
    </div>
    <div class="form-tutor-con" id="scribblarBoard">
      <div class="tutor-info-outr">
        <div class="my-info-tutor tutor-db-outr" style="width: 100% !important; ">
          <div class="classroom-stu-txt"  id="scribSection" >
           <!--  <h3>White Board Implementation</h3> -->
            <div class="cRoom-graph" id="alternate" >
            <a href="http://www.adobe.com/go/getflashplayer"><spring:message code="latest.adobe.require.msg" /><br>
  <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" border="0" alt="Get Adobe Flash Player" /> </a> 
            
            <%-- <img src="<%=request.getContextPath()%>/images/classmate-graph.jpg" alt=""> --%></div>
          </div>
        </div>
        
      </div>
   </div>
  </div>
  </div>
</section>

<div id="confirmpopup" style="display:none;">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closePopup();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"> <spring:message code="scribblar.confirmMsg"/></div>
            <section>
            	<%-- <spring:message code="popup.sessionnotstarted"/> --%>
            	<input type="button" class="greenBtn-normal" value='<spring:message code="yes"/>' onclick="confirmDelete()" >
            	<input type="button" class="greenBtn-normal" value='<spring:message code="no"/>'  onclick="closePopup();" >
            </section>
        </div>
        </div>
        </div>
        </div>
        
        
<div id="recharge" style="display:none;">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="confirmDelete();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"> <spring:message code="your.sessiontimeend"/></div>
            <section>
            	<%-- <spring:message code="popup.sessionnotstarted"/> --%>
            	<input type="button" class="greenBtn-normal" value='<spring:message code="ok"/>' onclick="confirmDelete()" >
            </section>
        </div>
        </div>
        </div>
        </div>        
        

<!--//Mid Section-->
    <form action="<%=request.getContextPath()%>/student/endScribblarSession" method="GET" id="endSessionForm">
<input type="hidden" id="bookingId" name="bookingId" readonly="readonly">
</form>
</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	
	
	window.onbeforeunload = function() {
	    return 'Are you sure you want to navigate away from this page?';
	};
	
	
	var studentJoined = '${dtoScribblarMeeting.isStudentJoined}';
	if(studentJoined=='Y'){
		$("#endsessionscribblar").show();
		$("#startsessionscribblar").hide();
		$("#scribblarBoard").css("visibility", "visible");
	}
	else{
		$("#endsessionscribblar").hide();
		$("#startsessionscribblar").show();
		$("#scribblarBoard").css("visibility", "hidden");
	}
	
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
</script> 
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/scrolltop/arrow23.js"></script>
<noscript>
<spring:message code="not.seeing"></spring:message> <a href="http://www.scrolltotop.com/"><spring:message code="scroll.topbutton"></spring:message> </a><spring:message code="goto.faqpage"></spring:message>
</noscript> --%>
</body>



<script type="text/javascript">


function endSession(){
	$("#scribSection").css("visibility", "hidden"); 
	$("#confirmpopup").show();
	 
}

function closePopup(){
	$("#scribSection").css("visibility", "visible"); 
	$("#confirmpopup").hide();
}

function confirmDelete(){
	var bookingId = '${dtoScribblarMeeting.bookingId}';
	 $("#bookingId").val(bookingId);
	 $("#endSessionForm").submit();
}

function startSession(bookingId){
	
	 var url='<%=request.getContextPath()%>/student/startStudentSessionTime';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{bookingId:bookingId},
		success:function(response){
			if(response=="success"){
				$("#scribblarBoard").css("visibility", "visible");
				$("#endsessionscribblar").show();
				$("#startsessionscribblar").hide();
			}
		}
	});
}

</script>





 <script type="text/javascript">
 
/*  function chkendd(){
	 var countertime = $("#meetingTimer").html();
	 var matchtime =" 0:00:00";
	 if(countertime===matchtime){
		 alert("done");
		 confirmDelete();
	 }
 } */
 
  setInterval(function(){
	  var countertime = $("#meetingTimer").html();
	  var currenttime = $("#meetingTimer").html();
		 var matchtime =" 0:00:00";
		 
	countertime = countertime.split(/:/);
	countertime = (countertime[0] * 3600 + countertime[1] * 60 + countertime[2]);
    if(countertime<="15200"){
    	$("#meetingTimer").css("color", "red");
    }
		 
		 if(currenttime===matchtime){
			 $(window).scrollTop(0);
			 $("#scribSection").css("visibility", "hidden"); 
			  $("#recharge").show();
		 }
 }, 2000);  
 
 
  var bookingId = '${dtoScribblarMeeting.bookingId}';
  var refreshStatusFlag="1";
  if(refreshStatusFlag==1){
// setTimeout(function(){ 
 var refreshId = setInterval(function(){
	 var url='<%=request.getContextPath()%>/checkStudentTutorAvailability';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			data:{bookingId:bookingId},
			success:function(response){
				if(response.modelMap.meetingStatus=='Y'){
					clearInterval(refreshId);
					$("#endsessionscribblar").show();
					$("#startsessionscribblar").hide();
					$("#scribblarBoard").css("visibility", "visible");
					
					 $('#meetingTimer').backward_timer('start');
					 $('#meetingTimer').backward_timer({
						// specify timer's timeout value
						 seconds: response.modelMap.remainingTime, 
					//	seconds: 1, 
						// timer's step (in seconds)
						step: 1,
						value_setter: undefined,
						// Set output format
						format: ' h%:m%:s%', 
						// Handle event of exhausting
						on_exhausted: function(timer) {}, 
						on_tick: function(timer) {} 
						});
				}
				else{
					if(response.modelMap.tutorJoined=='Y'){
					$('#meetingTimer').html('<spring:message code="tutorConnected"/>');
					}else{
						$('#meetingTimer').html('<spring:message code="waitingfortutor"/>');
					}
				}
			}
		});
	 
 }, 5000); 
 
 // }, 20000); 
  }
 
 
 
 
	 var statusId = setInterval(function(){
		 var url='<%=request.getContextPath()%>/checkStudentTutorAvailabilityStatus';
			$.ajax({
				url:url,
				method:'GET',
				async :false,
				data:{bookingId:bookingId},
				success:function(response){
					if(response!=null){
					if(response.modelMap.leaveflag==true){
						clearInterval(statusId);
					  //  $('#meetingTimer').html('Tutor is not longer connected');
					    $('#remainTime').html('<span><spring:message code="tutor.notconnected" /> </span> ');
					    refreshStatusFlag="0";
					}
					}
				}
			});
		 
	 }, 7000); 
	 
 </script>

 <script type="text/javascript">

var targetID = "scribblar";
var flashvars = {};
/* pass necessary variables to the SWF */
flashvars.userid = '${dtoScribblarMeeting.userScribblarId}';											/* to allow an anonymous guest pass 0 */
flashvars.roomid = '${dtoScribblarMeeting.roomId}';									/* the roomid for the room you'd like to access - substitute this for a valid roomid */
flashvars.preferredLocales = "es-AR";   					/* sets the default language - if in doubt leave as en_US */
/* optional: if you pass userid=0 you may also pass a username to skip the username prompt and log the 
user in using that username 
flashvars.username = "John";	*/
flashvars.username = '${dtoScribblarMeeting.studentFormattedName}';
//flashvars.password="f26plcd9311q";
//flashvars.allowguests = 0;
var params = {};
params.allowscriptaccess = "always";


var attributes = {};
attributes.id = "scribblar";
attributes.name = "scribblar";
swfobject.embedSWF("http://media.muchosmedia.com/scribblar/v4/main.swf", "alternate", "100%", "100%", "11.1.0", "http://media.muchosmedia.com/swfobject/expressInstall.swf", flashvars, params, attributes);

</script>
 
  
  <script type="text/javascript">
  setInterval(function(){
	   
		var url='<%=request.getContextPath()%>/student/setMeActive';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response){
				
				
			}
		});  
	    
	    
	}, 100000);
  
  </script>




</html>
