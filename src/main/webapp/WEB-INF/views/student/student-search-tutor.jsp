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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="booktutor" /></title>
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
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.simple-dtpicker.js"></script>




<script type="text/javascript">
<%-- function searchTutor(){
$("#searchForm").attr("action", "<%=request.getContextPath()%>/student/searchTutor");
	
	$("#searchForm").submit();
} --%>

/* $(function(){
 	$('*[name=bookingDate]').appendDtpicker({
 		"closeOnSelected": true,
 		"minuteInterval": 15,
 		"futureOnly": true,
 		"dateFormat": "yyyy-MM-DD hh:mm"
 	});
 });
 */
</script>

<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>
<%-- <form name="searchForm" id="searchForm" method="get" > --%>

  <div class="inner-row">
  	<div class="search-full">
    	<div class="search-con">
        	<input type="text" placeholder='<spring:message code="search.texttutor"></spring:message>' name="searchTutor" id="searchTutor" onchange="getTutorByCriteria();"> 
            <a href="javascript:;" ><spring:message code="home.search"></spring:message></a>
        </div>
    </div>
  </div>
  <%-- </form> --%>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
            <div class="ac-activity ac-activity-2 mt0">
           		<h1 class="text-left"><spring:message code="student.tutors"></spring:message> </h1>
                <div class="stu-index-txt" id="tutorDetails">
                	<ul>
                	<c:set var="count" value="0" scope="page"/>
                	 <c:forEach items="${listDtoTutorRegistrations}" var="listDtoTutorRegistrations">
                    	<li>
                        	<div class="tutor-img-outr">
                            	<div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
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
                                
                                
                               <%--  <img src="<%=request.getContextPath()%>/images/bcm-tutor-img2.jpg" alt=""> --%>
                                
                                </div>
                                <div class="tutor-img-txt">
                                	${listDtoTutorRegistrations.name}
                                    <div class="subjects fl top-ellipsis">${listDtoTutorRegistrations.subjectNames}</div>
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
                                <div>${listDtoTutorRegistrations.about}</div>
                                <p>University of Florida</p>
                                <div><a href="javascript:;"><spring:message code="student.seemore"></spring:message> </a></div>
                            </div>
                             <div class="session-schld-outr">
                            	<h5><spring:message code="student.sessionschedule"></spring:message></h5>
                                <input type="text" placeholder="2/8/2015" maxlength="11" readonly="readonly">
                            </div> 
                            <div class="sessionBtns">
                            	<a href="javascript:;" name="bookTutor" id="bookTutor" onclick="openBookingPopUp('${listDtoTutorRegistrations.userId}');" class="greenBtn-normal"><spring:message code="student.booknewsession"></spring:message> </a>
                                <a href="javascript:;" class="blueBtn-normal"><spring:message code="student.reviewpastsession"></spring:message> </a>
                            </div>
                        </li>
                        <c:set var="count" value="1" scope="page"/>
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
</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>
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
                    <td><div > <form:input type="text" path="bookingDate" class="date-input" placeholder="2/8/2015" readonly="true" readonly="readonly"   id="bookingDate" name="bookingDate" /></div></td>
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






</body>


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

	$("#bookinForm").attr("action", "<%=request.getContextPath()%>/student/bookingTutor");
	
	$("#bookinForm").submit();
}


 function closeBookingPopUp(){
	$("label.error").hide();
	$("#bookingpopup").hide();
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
		}, 
		submitHandler:function(form){
    		form.submit();
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
                         
		                  if(value.imageName!=null){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+value.userId+'/fileupload/'+value.userId+value.imageName+'"/>';
		                            }	

		                  if(value.imageName==null && value.imgUrl==null){
		     tutorData+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
		                            }

		                  if(value.imageName==null && value.imgUrl!=null){
		     tutorData+=   '<img alt="" src="'+value.imgUrl+'"/>';
		                            }							
		                                   
							              	
							             
		     tutorData+=   '</div><div class="tutor-img-txt">'+value.name+'<div class="subjects fl top-ellipsis">'+value.subjectNames+'</div><div class="stars">';
		                          tutorData+=   '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">'+
		                                        '<img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""></div></div></div>';
		                                    
		     tutorData+=   '<div class="info-outr"><h5><spring:message code="student.info"></spring:message></h5><div>'+value.about+'</div><p>University of Florida</p>'+
		              '<div><a href="javascript:;"><spring:message code="student.seemore"></spring:message></a></div></div><div class="session-schld-outr">'+
		              '<h5><spring:message code="student.sessionschedule"></spring:message></h5><input type="text" readonly="readonly" placeholder="2/8/2015" maxlength="11"></div>'+ 
		              '<div class="sessionBtns">'+
		              '<a href="javascript:;" name="bookTutor'+value.userId+'" id="bookTutor'+value.userId+'" onclick="openBookingPopUp('+value.userId+');" class="greenBtn-normal"><spring:message code="student.booknewsession"></spring:message></a>';
		     tutorData+=   '<a href="javascript:;" class="blueBtn-normal"><spring:message code="student.reviewpastsession"></spring:message> </a></div></li></ul>';
						
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

</html>