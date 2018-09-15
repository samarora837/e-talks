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

 <script type="text/javascript">
 
 var numberofChild = "${fn:length(dtoallChildBookingDeails)}";

 $(document).ready(function(){
	 $('#myTable').DataTable({
			"order": [[ 1, "desc" ]],
		       "aoColumns": [
		                       {"bVisible": false},
		                         {"iDataSort": 0},                              
		                         null,
		                         null,
		                         null,
		                         null,
		                         null,
		                         null
		                        ]
		});
	 
	 $('#myTableReviewWork').DataTable({
			"order": [[ 1, "desc" ]],
		       "aoColumns": [
		                       {"bVisible": false},
		                         {"iDataSort": 0},                              
		                         null,
		                         null,
		                         null,
		                         null,
		                         null,
		                         null
		                        ]
		});
	 
	 
	 
	 
	 
	 
	 for(var i=1;i<=numberofChild;i++){
	    $('#myTable'+i).DataTable({
			"order": [[ 1, "desc" ]],
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
	 }
	 
	 for(var i=1;i<=numberofChild;i++){
		    $('#myTablereview'+i).DataTable({
				"order": [[ 1, "desc" ]],
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
		 }
	 
	});
 </script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/parent/parent-inner-header.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
            <div class="ac-activity ac-activity-2 mt0">
           		<h1 class="text-left"><spring:message code="child.scheduled.session"/></h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
                	<thead>
                      <tr>
                      <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="time"/></th>
                        <th><spring:message code="child.name"/></th>
                        <th><spring:message code="login.tutor"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <%-- <th class="text-center"><spring:message code="question"/> </th> --%>
                        <th><spring:message code="question"/> </th>
                        <th><spring:message code="student.archive"/> </th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${dtoBookingDetailList}" var="dtoBookingDetail">
                      <c:if test="${dtoBookingDetail.isDeleted eq 'N' && dtoBookingDetail.iscompleted eq 'N' && dtoBookingDetail.accepted eq 'Y' && dtoBookingDetail.isSessionTimeExpire eq 'N' }">
                      <tr>
                       <td>${dtoBookingDetail.newBookingDate }</td>
                        <td>${dtoBookingDetail.dateSession }</td>
                        <td>${dtoBookingDetail.timeSession }</td>
                        <td>${dtoBookingDetail.studentFullName }</td>
                        <td>${dtoBookingDetail.tutorFullName }</td>
                        <td>${dtoBookingDetail.subjectName }</td>
                        <%-- <td class="text-center"><input type="text" maxlength="11" value='${dtoBookingDetail.bookingStudentDate}' class="date-input" readonly="readonly"></td> --%>
                    <td ><div style="width:200px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.question}">${dtoBookingDetail.question }</div></td>
                    
                     <c:if test="${dtoBookingDetail.bookingDocPath eq 'NA'}">                      
                      <td><a href="javascript:void(0);">${dtoBookingDetail.bookingDocName}</a></td>
                      </c:if>
                        <c:if test="${dtoBookingDetail.bookingDocPath ne 'NA'}">                      
                     <td><a href="<%=request.getContextPath()%>${dtoBookingDetail.bookingDocPath}" style="width:200px;" class="fl ellipsis ellipsis1" title="${dtoBookingDetail.bookingDocName}" download>${dtoBookingDetail.bookingDocName}</a></td>
                      </c:if>
                    
                      </tr>
                      </c:if>
                       </c:forEach>
                        
                       </tbody>
                    </table>
                </div>
            </div>
            
            <br>
                  <div class="ac-activity ac-activity-2 mt0">
           		<h1 class="text-left"><spring:message code="child.scheduled.reviewwork"/></h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTableReviewWork">
                	<thead>
                      <tr>
                      <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="time"/></th>
                        <th><spring:message code="child.name"/></th>
                        <th><spring:message code="login.tutor"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <%-- <th class="text-center"><spring:message code="question"/> </th> --%>
                        <th><spring:message code="question"/> </th>
                        <th><spring:message code="student.archive"/> </th>
                      </tr>
                      </thead>
                      <tbody>
                                          
                       
                      <c:forEach items="${dtoallChildReviewDetails}" var="dtoallChildReviewDetails">
                      <c:forEach items="${dtoallChildReviewDetails}" var="ChildReviewDeails">
                      <c:if test="${ChildReviewDeails.iscompleted eq 'N' && ChildReviewDeails.accepted eq 'Y' && ChildReviewDeails.isCompletedByTutor eq 'N'  && ChildReviewDeails.isDeleted eq 'N'}">
                      <tr>
                       <td>${ChildReviewDeails.newBookingDate }</td>
                        <td>${ChildReviewDeails.dateSession }</td>
                        <td>${ChildReviewDeails.timeSession }</td>
                        <td>${ChildReviewDeails.studentFullName }</td>
                        <td>${ChildReviewDeails.tutorFullName }</td>
                        <td>${ChildReviewDeails.subjectName }</td>
                        
                    <td ><div style="width:200px;" class="fl ellipsis ellipsis1" title="${ChildReviewDeails.reviewQuestion}">${ChildReviewDeails.reviewQuestion }</div></td>
                    
                     <c:if test="${ChildReviewDeails.studentDocPath eq 'NA'}">                      
                      <td><a href="javascript:void(0);">${ChildReviewDeails.studentDocName}</a></td>
                      </c:if>
                        <c:if test="${ChildReviewDeails.studentDocPath ne 'NA'}">                      
                     <td><a href="<%=request.getContextPath()%>${ChildReviewDeails.studentDocPath}" style="width:200px;" class="fl ellipsis ellipsis1" title="${ChildReviewDeails.studentDocName}" download>${ChildReviewDeails.studentDocName}</a></td>
                      </c:if>
                    
                      </tr>
                      </c:if>
                      </c:forEach>
                       </c:forEach>
                       
                       
                        
                       </tbody>
                    </table>
                </div>
            </div>
            
            
            
            
            <c:set var="counter" value="1" scope="page"/>
            <c:forEach items="${dtoallChildBookingDeails}" var="dtoallChildBookingDeails">
            <c:set var="count" value="0" scope="page"/>
             <div class="ac-activity ac-activity-2">
           		
           		<c:forEach items="${dtoallChildBookingDeails}" var="ChildBookingDeails">
           		<c:if test="${count eq '0' }">
           		<h1 class="text-left"><spring:message code="previous.sessions"/> - ${ChildBookingDeails.studentFullName }</h1>
          <c:set var="count" value="1" scope="page"/>
          </c:if>
           		</c:forEach>
                <div class="ac-activity-txt mesg-tab">
                
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id='myTable${counter}'>
                	<thead>
                      <tr>
                      <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="student.date"/> </th>
                        <th><spring:message code="login.tutor"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th> <spring:message code="length"/></th>
                        <%-- <th class="text-center"><spring:message code="student.view"/> </th> --%>
                        <th><spring:message code="question"/> </th>
                         <th><spring:message code="student.archive"/> </th>
                      </tr>
                      </thead>
                      <tbody>
                       
                      <c:forEach items="${dtoallChildBookingDeails}" var="ChildBookingDeails">
                      <c:if test="${ChildBookingDeails.iscompleted eq 'Y'}">
                      <tr>
                      <td>${ChildBookingDeails.newBookingDate }</td>
                      <td>${ChildBookingDeails.dateSession }</td>
                      <td>${ChildBookingDeails.tutorFullName }</td>
                      <td>${ChildBookingDeails.subjectName }</td>
                      <td>${ChildBookingDeails.bookingDuration } <spring:message code="mins"/></td>
                        <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${ChildBookingDeails.question}">${ChildBookingDeails.question}</div></td>
                      <%--   <td class="text-center"><a href="javascript:;" class="greenBtn-small"><spring:message code="student.view"/></a></td> --%>
                      <c:if test="${ChildBookingDeails.bookingDocPath eq 'NA'}">                      
                      <td><a href="javascript:void(0);">${ChildBookingDeails.bookingDocName}</a></td>
                      </c:if>
                        <c:if test="${ChildBookingDeails.bookingDocPath ne 'NA'}">                      
                     <td><a href="<%=request.getContextPath()%>${ChildBookingDeails.bookingDocPath}" style="width:200px;" class="fl ellipsis ellipsis1" title="${ChildBookingDeails.bookingDocName}" download>${ChildBookingDeails.bookingDocName}</a></td>
                      </c:if>
                       
                      </tr>
                       </c:if>
                       </c:forEach>
                       
                       
                   
                       </tbody>
                    </table>
                    
                </div>
            </div>
              <c:set var="counter" value="${counter + 1}" scope="page"/> 
          </c:forEach>
          
          
          
           <c:set var="counter" value="1" scope="page"/>
            <c:forEach items="${dtoallChildReviewDetails}" var="dtoallChildReviewDetails">
            <c:set var="count" value="0" scope="page"/>
             <div class="ac-activity ac-activity-2">
           		
           		<c:forEach items="${dtoallChildReviewDetails}" var="ChildReviewDeails">
           		<c:if test="${count eq '0' }">
           		<h1 class="text-left"><spring:message code="previous.reviewwork"/> - ${ChildReviewDeails.studentFullName }</h1>
          <c:set var="count" value="1" scope="page"/>
          </c:if>
           		</c:forEach>
                <div class="ac-activity-txt mesg-tab">
                
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id='myTablereview${counter}'>
                	<thead>
                      <tr>
                       <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="deliverytime.review"/> </th>
                        <th><spring:message code="login.tutor"/></th>
                        <th><spring:message code="subject"/>  </th>
                        <th><spring:message code="equilavent.rate"/></th>                        
                        <th><spring:message code="question"/> <spring:message code="login.student"/></th>
                        <th><spring:message code="student.archive"></spring:message></th>
                         <th><spring:message code="tutorcomment"></spring:message></th>
                         <th><spring:message code="tutorsolutionfile"></spring:message></th>
                      </tr>
                      </thead>
                      <tbody>
                       
                      <c:forEach items="${dtoallChildReviewDetails}" var="ChildReviewDeails">
                      <c:if test="${ChildReviewDeails.iscompleted eq 'Y'}">
                      <tr>
                      <td>${ChildReviewDeails.newBookingDate }</td>
                      <td>${ChildReviewDeails.dateSession }</td>
                      <td>${ChildReviewDeails.tutorFullName }</td>
                      <td>${ChildReviewDeails.subjectName }</td>
                      <td>${ChildReviewDeails.reviewDuration } <spring:message code="mins"/></td>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${ChildReviewDeails.reviewQuestion}">${ChildReviewDeails.reviewQuestion}</div></td>
                      
                      <c:if test="${ChildReviewDeails.studentDocPath eq 'NA'}">
                      <td><a href="javascript:void(0);">${ChildReviewDeails.studentDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${ChildReviewDeails.studentDocPath ne 'NA'}">
                      <td><a href="<%=request.getContextPath()%>${ChildReviewDeails.studentDocPath}" style="width:100px;" class="fl ellipsis ellipsis1" title="${ChildReviewDeails.studentDocName}" download>${ChildReviewDeails.studentDocName}</a></td>
                      </c:if>
                      <td><div style="width:100px;" class="fl ellipsis ellipsis1" title="${ChildReviewDeails.tutorComments}">${ChildReviewDeails.tutorComments}</div></td>
                   
                        <c:if test="${ChildReviewDeails.tutorDocPath eq 'NA'}">
                      <td class="text-center"><a href="javascript:void(0);">${ChildReviewDeails.tutorDocName}</a></td>
                      </c:if>
                      
                      <c:if test="${ChildReviewDeails.tutorDocPath ne 'NA'}">
                      <td class="text-center"><a href="<%=request.getContextPath()%>${ChildReviewDeails.tutorDocPath}" style="width:100px;" class="fl ellipsis ellipsis1" title="${ChildReviewDeails.tutorDocName}" download>${ChildReviewDeails.tutorDocName}</a></td>
                      </c:if>
                      </tr>
                       </c:if>
                       </c:forEach>
                       
                       
                   
                       </tbody>
                    </table>
                    
                </div>
            </div>
              <c:set var="counter" value="${counter + 1}" scope="page"/> 
          </c:forEach>
           
        
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
</body>
</html>
