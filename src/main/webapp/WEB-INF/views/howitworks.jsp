<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.miprofe.facebook.FBConnectionStudent"%>
<%@page import="com.miprofe.facebook.FBConnection"%>


<%
FBConnectionStudent fbConnectionStudent = new FBConnectionStudent();
FBConnection fbConnection = new FBConnection();
%>

<%          response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
            response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
            response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>
 <!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
<title><spring:message code="how.title"></spring:message> </title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
  
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
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body onload="getFBURl();">
<div id="main">
    
    <%@ include file="/WEB-INF/views/headerOuter.jsp" %>
    
    <!--Mid Section-->
    ${content}
<%--     <section class="container">
    	<div class="works-con">
 			<div class="inner-row">
        	<h1><spring:message code="home.text" /></h1>
             <div class="work-steps">
             	<div class="steps-vid"><img src="images/video-img.jpg" alt=""></div>
                
                <div class="steps-lt">
                	<div class="steps-lt-row">
                    	<div class="steps-lt-hdr">
                        	<span>1</span>
                            <spring:message code="home.searchsubject"></spring:message>
                        </div>
                        <p><spring:message code="namactortorPellentesque"></spring:message></p>
                    </div>
                    <div class="steps-lt-row">
                    	<div class="steps-lt-hdr">
                        	<span>2</span>
                           <spring:message code="home.looktutor"></spring:message>
                        </div>
                        <p><spring:message code="Namactortorfringillatellus"></spring:message></p>
                    </div>
                    <div class="steps-lt-row">
                    	<div class="steps-lt-hdr">
                        	<span>3</span>
                            <spring:message code="home.chatwithtutor"></spring:message>
                        </div>
                        <p><spring:message code="Namactortorfringillamassamalesuada"></spring:message></p>
                    </div>
                    <div class="steps-lt-row">
                    	<div class="steps-lt-hdr">
                        	<span>4</span>
                            <spring:message code="home.connecttutor"></spring:message>
                        </div>
                        <p><spring:message code="Namactortorfringillamassainterdumurna"></spring:message></p>
                    </div>
                </div>
             
             </div>
            </div>
            
            <div class="inner-row">
            <div class="regis-stu-tutor">
            	<div class="regis-stu-tutor-lt">
                	<h2><spring:message code="how.improvegrade"></spring:message> </h2>
					
                    <a href="<%=request.getContextPath()%>/signup"><spring:message code="how.registernow"></spring:message></a>
                </div>
                <div class="regis-stu-tutor-rt">
                	<h2><spring:message code="how.becometutor"></spring:message></h2>
					 <a href="<%=fbConnection.getFBAuthUrl()%>"><spring:message code="how.registertutor"></spring:message> </a>
                </div>
            
            </div>
            </div>
        
        </div>
    </section> --%>
    <!--//Mid Section-->
</div>

    
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
<script type="text/javascript">
function getFBURl(){

	var url ='<%=fbConnection.getFBAuthUrl()%>';
	$("#fbURL").attr("href",url);
	var videoPath ='<%=request.getContextPath()%>${video1.videoURL}';
	if(videoPath!=''){
		$('#video').html('<video width="100%" controls><source src="'+videoPath+'" type="video/mp4"></video>');
	}
	
		

}

</script>
</html>
