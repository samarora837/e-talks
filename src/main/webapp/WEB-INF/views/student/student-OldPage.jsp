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

<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js" type="text/javascript" ></script>

<link href="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.js"></script>






<script type="text/javascript">
function searchTutor(){
$("#searchForm").attr("action", "<%=request.getContextPath()%>/student/searchTutor");
	
	$("#searchForm").submit();
}

$(function(){
 	$('*[name=bookingDate]').appendDtpicker({
 		"closeOnSelected": true,
 		"minuteInterval": 15,
 		"futureOnly": true,
 		"dateFormat": "yyyy-MM-DD hh:mm"
 	});
 });

</script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>

  <div class="inner-row">
  	<div class="search-full">
    	<div class="search-con">
        	<input type="text" placeholder='<spring:message code="search.texttutor"></spring:message>' name="searchTutor" id="searchTutor"  onchange="getTutorByCriteria();"> 
            <a href="javascript:;" ><spring:message code="home.search"></spring:message></a>
        </div>
    </div>
  </div>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
            <div class="ac-activity ac-activity-2 mt0">
           		<h1 class="text-left"><spring:message code="student.mybookedtutor"></spring:message> </h1>
           		<div class="sessionBtns">
                    <a href="<%=request.getContextPath()%>/student/bookNewTutor" class="greenBtn-normal"><spring:message code="booknewtutor"></spring:message> </a>
                 </div>
           		
                <div class="stu-index-txt"  id="tutorDetails">
                	<ul>
                      <c:set var="count" value="0" scope="page"/>
                    
 					<c:forEach items="${listBookingDetails}" var="listBookingDetails">
 					<c:if test="${listBookingDetails.accepted eq 'Y' && listBookingDetails.iscompleted eq 'N'}">
                    	<li>
                        	<div class="tutor-img-outr">
                            	<div class="indi-icon"><img id='statusImg${listBookingDetails.tutorId}' src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                                <div class="tutor-img">
                                
                                   <c:if test="${listBookingDetails.imageName !=''}">
					              	<img alt="" src="<%=request.getContextPath()%>/profilePictures/${listBookingDetails.tutorId}/fileupload/${listBookingDetails.tutorId}${listBookingDetails.imageName}"/>
					              	</c:if>
					              	<c:if test="${listBookingDetails.imageName ==''}">
					              	<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
					              	</c:if>
					              	<c:if test="${listBookingDetails.imgUrl !=null}">
					              	<img alt="" src="${listBookingDetails.imgUrl}"/>
					              	</c:if>
                                </div>
                                <div class="tutor-img-txt">
                                	${listBookingDetails.fullName}
                                    <div class="subjects fl top-ellipsis">${listBookingDetails.subjectName}</div>
                                    <div class="stars">
                                    	<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                        <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                        <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                        <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">
                                        <img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">
                                    </div>
                                </div>
                            </div>
                            <div class="info-outr">
                            	<h5><spring:message code="student.info"></spring:message></h5>
                                <div>${listBookingDetails.aboutTutor}</div>
                                <p>${listBookingDetails.college}</p>
                                <div><a href="javascript:;"><spring:message code="student.seemore"></spring:message> </a></div>
                            </div>
                            <div class="session-schld-outr">
                            	<h5><spring:message code="student.sessionschedule"></spring:message></h5>
                                <input type="text" placeholder="${listBookingDetails.bookingDate}" value="${listBookingDetails.bookingDate}"  readonly="readonly">
                            </div> 
                            <div class="sessionBtns">
                            	<a href="javascript:;" class="greenBtn-normal" name="startSession"  onclick="goToMeeting('${listBookingDetails.bookingId}');" id="startSession${listBookingDetails.bookingId}"><spring:message code="student.gotoclassroom"></spring:message> </a>
                                <a href="<%=request.getContextPath()%>/student/sessionManage" class="blueBtn-normal"><spring:message code="student.reviewpastsession"></spring:message> </a>
                            </div>
                        </li>
                         <c:set var="count" value="1" scope="page"/>
                        </c:if>
                        </c:forEach>                       
                       
                       
                       <c:if test="${count eq '0' }">
                       <h3 style="color: #3a4e62;"><spring:message code="norecord.found"/> </h3>
                       </c:if>
                    </ul>
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
<%@ include file="/WEB-INF/views/footerOuter.jsp" %>
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

<!-- Booking Pop Up -->


		



 
<div id="bookingpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="" onclick="closeBookingPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
            
            <form:form id="bookinForm" name="bookingForm" commandName="dtoBookingDetail" method="POST">
            <input type="hidden" id="tutorId" name="tutorId">
            <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                  
                       <tr>
                   <th><spring:message code="subject.title"></spring:message></th>
                    <td align="right">:</td> 
                    <td> 
                    <div class="popUp-table-rt">
                    <form:select id="subjectTitle" name="subjectTitle" path="subjectTitleId" onchange="getSubjectType(this);">
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
                    <div class="popUp-table-rt">
                    <form:select id="subjectType" path="subjectTypeId" name="subjectType">
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
                    <td><div class="popUp-table-rt"> <form:input type="text" path="bookingDate" class="date-input" placeholder="2/8/2015" readonly="readonly" maxlength="11"  id="bookingDate" name="bookingDate" /></div></td>
                  </tr>
                  
                         <tr>
                   <th><spring:message code="duration"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div class="popUp-table-rt">
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
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="book.session"/>' onclick="submitBookingForm();" ></td>
                  </tr>
                  
            </tbody></table>
                </div>
            </form:form>
            
        </div>
   </div>
</div> 




<!-- End Booking Pop Up -->

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

function openBookingPopUp(tutorId)
{
	
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
	
	
	
	$("#bookingpopup").show();
	/* var start = new Date();
	start.setFullYear(start.getFullYear() - 70);
	var end = new Date();
	end.setFullYear(end.getFullYear());
	$('#bookingDate').datepicker({changeYear:true, changeMonth:true, showMonthAfterYear:true,  maxDate: end, yearRange: start.getFullYear() + ':' + end.getFullYear()}); */

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

	$("#bookinForm").attr("action", "<%=request.getContextPath()%>/student/bookingTutor");
	
	$("#bookinForm").submit();
}


 function closeBookingPopUp(){
	
	$("#bookingpopup").hide();
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
</script>




<script type="text/javascript">

function getTutorByCriteria(){
	
	var searchPattern = $("#searchTutor").val().trim();
    if(searchPattern!=""){
	var url='<%=request.getContextPath()%>/student/getStudentsFilteredTutorList';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{searchPattern:searchPattern},
		success:function(response){
			var status="0";
			if(response != null){
				var tutorData="";
				
				//alert(response.modelMap.dtoBookingDetail.bookingDate);
				var res="";
				 $.each( response.modelMap.listDtoTutorRegistrationsFiltered, function(index,value) {
						//alert(value.name);

						status="1";
					     tutorData+=   '<ul><li><div class="tutor-img-outr"><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div><div class="tutor-img">';
                         
		                  if(value.imageName!=''){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.userId+'/fileupload/'+value.userId+value.imageName+'"/>';
		                            }	

		                  if(value.imageName==''){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
		                            }

		                  if(value.imageName==null){
		     tutorData+=   '<img alt="" src="'+value.imgUrl+'"/>';
		                            }							
		                                   
							              	
							             
		     tutorData+=   '</div><div class="tutor-img-txt">'+value.name+'<div class="subjects fl top-ellipsis">'+value.subjectNames+'</div><div class="stars">';
		                          tutorData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""></div></div></div>';
		                                    
		     tutorData+=   '<div class="info-outr"><h5><spring:message code="student.info"></spring:message></h5><div>'+value.about+'</div><p>'+value.college+'</p>'+
		              '<div><a href="javascript:;"><spring:message code="student.seemore"></spring:message></a></div></div><div class="session-schld-outr">'+
		              '<h5><spring:message code="student.sessionschedule"></spring:message></h5><input type="text" readonly="readonly"  placeholder="2/8/2015" maxlength="11"></div>'+ 
		              '<div class="sessionBtns">'+
		              '<a href="javascript:;" name="bookTutor'+value.userId+'" id="bookTutor'+value.userId+'"  class="greenBtn-normal"><spring:message code="student.gotoclassroom"></spring:message></a>';
		     tutorData+=   '<a href="<%=request.getContextPath()%>/student/sessionManage" class="blueBtn-normal"><spring:message code="student.reviewpastsession"></spring:message> </a></div></li></ul>';
						
						 });
				$("#tutorDetails").html(tutorData);
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
var listSize = '${fn:length(listBookingDetails)}';
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
				if(listSize!=newlistsize){
					listSize=newlistsize;
				var tutorData="";
				var res="";
				 $.each( response.modelMap.listNewBookingDetails, function(index,value) {
						status="1";
					     tutorData+=   '<ul>';
					     if(value.accepted=="Y" && value.iscompleted=="N")
					    	 {
		     tutorData+=    '<li><div class="tutor-img-outr"><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div><div class="tutor-img">';
                         
		                  if(value.imageName!=''){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.tutorId+'/fileupload/'+value.tutorId+value.imageName+'"/>';
		                            }	

		                  if(value.imageName==''){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
		                            }

		                  if(value.imageName==null){
		     tutorData+=   '<img alt="" src="'+value.imgUrl+'"/>';
		                            }							
		                                   
							              	
							             
		     tutorData+=   '</div><div class="tutor-img-txt">'+value.fullName+'<div class="subjects fl top-ellipsis">'+value.subjectName+'</div><div class="stars">';
		                          tutorData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""></div></div></div>';
		                                    
		     tutorData+=   '<div class="info-outr"><h5><spring:message code="student.info"></spring:message></h5><div>'+value.aboutTutor+'</div><p>'+value.college+'</p>'+
		              '<div><a href="javascript:;"><spring:message code="student.seemore"></spring:message></a></div></div><div class="session-schld-outr">'+
		              '<h5><spring:message code="student.sessionschedule"></spring:message></h5><input type="text" value="'+value.bookingDate+'" placeholder="2/8/2015" readonly="readonly" maxlength="11"></div>'+ 
		              
		              '<div class="sessionBtns">'+
		              '<a href="javascript:;" class="greenBtn-normal" name="startSession" onclick="goToMeeting('+value.bookingId+');" id="startSession'+value.bookingId+'"><spring:message code="student.gotoclassroom"></spring:message></a>';
		     tutorData+=   '<a href="<%=request.getContextPath()%>/student/sessionManage" class="blueBtn-normal"><spring:message code="student.reviewpastsession"></spring:message> </a></div></li>';
				 }
		     tutorData+= '</ul>';
						 });
				$("#tutorDetails").html(tutorData);
				}
			
			}
		}
	});  
    
    
}, 5000);  

</script>

</html>