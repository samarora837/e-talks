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
<%-- <title><spring:message code="student.register" /></title> --%>
<title><spring:message code="tutor.myactivity"></spring:message></title>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tab.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/styleDate.css">
 
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
 <link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet">
 
 
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
 <link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet">

 <script type="text/javascript">
 $(document).ready(function(){
	    $('#myTable').DataTable({
			"order": [[ 0, "desc" ]],
		       "aoColumns": [
		                       {"bVisible": false},
		                         {"iDataSort": 0},                              
		                         null,
		                         null,
		                         null,
		                         null
		                        ]
		});
	   /*  $('#myTable1').DataTable(); */
	});
 </script>
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
           		<h1 class="text-left"><spring:message code="student.accountactivity"/> </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
                	<thead>
                      <tr>
                      <th><spring:message code="student.date"/></th>
                        <th><spring:message code="student.date"/></th>
                        <th><spring:message code="student.activity"/></th>
                        <th> <spring:message code="earned"/> <span class="currencyType"></span></th>
                        <th><spring:message code="student.minutes"/></th>
                        <th class="text-center"><spring:message code="status"/></th>
                      </tr>
                      </thead>
                      <tbody>
                      
                      <c:forEach items="${dtoTutorActivities}" var="dtoTutorActivities">
                      <tr>
                      <td>${dtoTutorActivities.newActivityDate} </td>
                        <td><fmt:formatDate  pattern="dd-MM-yy"  value="${dtoTutorActivities.activityDate}" /> </td>
                        <td>${dtoTutorActivities.activityName} </td>
                        <td> ${dtoTutorActivities.activityAmount}  </td>
                        <td>${dtoTutorActivities.activityMinutes} Min</td>
                        
                        
                        <c:set var="theString" value='${dtoTutorActivities.activityAmount}'/>
								
									<c:choose>
									<c:when test="${not fn:containsIgnoreCase(theString, '-')}">
									<c:if test="${dtoTutorActivities.activityStatus=='Canceled'}">
								<td>
									<spring:message code="cancelled"/> 
								</td>
								</c:if>
								<c:if test="${dtoTutorActivities.activityStatus=='Pending'}">
								<td>
									<spring:message code="pending"/> 
								</td>
								</c:if>
								
								<c:if test="${dtoTutorActivities.activityStatus=='Paid'}">
								<td>
									<spring:message code="paid"/> 
								</td>
								</c:if>
									
									</c:when>
									<c:otherwise>
									<td>
									<spring:message code="deducted"/>
									</td>
									
									</c:otherwise>
									
									</c:choose>
									
                        
                        
                        
                        
                      </tr>
                      </c:forEach>
                      </tbody>
                    </table>
                </div>
            </div>
            <div class="ac-activity tutorFee">
           		<h1 class="text-left"><spring:message code="tutor.feeperminute"/> </h1>
                <div class="ac-activity-txt">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <th><spring:message code="country"/></th>
                        <th><spring:message code="fee.perhour"/> <span class="currencyType"></span></th>
                        <th><spring:message code="availability"/></th>
                      </tr>
                      <c:forEach items="${tutorFeeByCountries}" var="country">
	                      <tr>
	                      
	                      <c:if test="${country.countryName eq 'otherCountries'}">
	                      <td><spring:message code="otherCountries"/></td>
	                      </c:if>
	                      <c:if test="${country.countryName ne 'otherCountries'}">
	                      <td>${country.countryName}</td>
	                      </c:if>
	                      
	                        
	                        <td>${country.fee}</td>
	                        <c:if test="${country.availabilityStatus != 'Y'}">
	                        	<td><input type="checkbox" name="availability" onchange="setAvailable('${country.feeId}');" id="${country.feeId}"> <spring:message code="able.to.teach.country"/> </td>
	                        </c:if>
	                        <c:if test="${country.availabilityStatus == 'Y'}">
	                        	<td><input type="checkbox" name="availability" onchange="setUnavailable('${country.feeId}');" checked="true" id="${country.feeId}"> <spring:message code="able.to.teach.country"/> </td>
	                        </c:if>
	                      </tr>
                      </c:forEach>

                    </table>
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
 


</body>

  
<script type="text/javascript">
function setAvailable(feeId)
{
	var url='<%=request.getContextPath()%>/tutor/setCountryAvailability/'+feeId;
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response!='true'){
				$('#'+feeId).attr('onchange','setUnavailable('+feeId+')');
			}else{
				$('#'+feeId).attr('checked','false');
			}
		
		}
	});
	
}
function setUnavailable(feeId)
{
	var url='<%=request.getContextPath()%>/tutor/setCountryUnavailability/'+feeId;
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			if(response!='true'){
				$('#'+feeId).attr('onchange','setAvailable('+feeId+')');
			}else{
				$('#'+feeId).attr('checked','true');
			}
		
		}
	});
	
}
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
 
 
 
//========== get tutor currency type by its country=======
 	  var url='<%=request.getContextPath()%>/tutor/getCurrencyType';
$.ajax({
	url:url,
	method:'GET',
	async :false,
	success:function(response){
	var currencyType = response.modelMap.currencyType;
			if(currencyType=="US"){
				$(".currencyType").text("US $");
			}
			else if(currencyType=="EURO"){
				$(".currencyType").text('\u20AC');
			}
			else if(currencyType=="MXN"){
				$(".currencyType").text("$");
			}
		
	}
});  


//-----------------------------------------------------------	

 

</script> 



</html>
