<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title><spring:message code="footer.aboutus" /></title>
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
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="bcm-tutor-con privacy-con">
          <h1 class="text-left"><spring:message code="footer.aboutus" /></h1>
          <div class="abt-lt">
			${content}
          </div>
          <div class="abt-rt">
            <div class="mm-video"> 
            	<c:if test="${not empty video1.videoURL }">
                	<video width="100%" controls>
				  		<source src="<%=request.getContextPath()%>${video1.videoURL}" type="video/mp4">
					</video>
				</c:if>
				<c:if test="${empty video1.videoURL }">
 						<img src="<%=request.getContextPath()%>${video1.imageURL}" alt="">
				</c:if>
            </div>
            <div class="mm-video"> 
            	<c:if test="${not empty video2.videoURL }">
                	<video width="100%" controls>
				  		<source src="<%=request.getContextPath()%>${video2.videoURL}" type="video/mp4">
					</video>
				</c:if>
				<c:if test="${empty video2.videoURL }">
 						<img src="<%=request.getContextPath()%>${video2.imageURL}" alt="">
				</c:if>
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
function closePopUp(){
	$("#signupRequired").hide();
}


function sendMsg(){
	 $(window).scrollTop(0);
	 $("#signupRequired").show();
}

</script>
</html>
