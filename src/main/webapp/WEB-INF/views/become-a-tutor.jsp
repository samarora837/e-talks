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
<%@page import="com.miprofe.facebook.FBConnection"%>
<%
	FBConnection fbConnection = new FBConnection();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title><spring:message code="tutor.becomeatutor" /></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
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
        	<div class="bcm-tutor-con">
            	<h1><spring:message code="tutor.becomeatutor1" /></h1>
                <p><spring:message code="tutor.desc" /></p>
                
                <ul>
                	<li>
                    	<div class="img"><img src="images/bcm-tutor-img1.jpg" alt=""></div>
                        <div class="img-txt"><spring:message code="tutor.desc1" /></div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/bcm-tutor-img2.jpg" alt=""></div>
                        <div class="img-txt"><spring:message code="tutor.desc2" /></div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/bcm-tutor-img3.jpg" alt=""></div>
                        <div class="img-txt"><spring:message code="tutor.desc3" /></div>
                    </li>
                    <li>
                    	<div class="img"><img src="images/bcm-tutor-img4.jpg" alt=""></div>
                        <div class="img-txt"><spring:message code="tutor.desc4" /></div>
                    </li>
                </ul>
                
                <div class="bcm-a-tutor">
                    <a href="<%=fbConnection.getFBAuthUrl()%>"><spring:message code="tutor.registerwithyourFacebookAccount" /></a>
                </div>
             <%--   <div align="center"><spring:message code="donothavefacebook.account"></spring:message>
                	<a href="<%=request.getContextPath() %>/tutor/register"><u><spring:message code="click.here"></spring:message> </u> </a>
                </div> --%>
            </div>
        </div>
    </section>
    <!--//Mid Section-->
   </div>
   <div class="clr"></div>
    <%@ include file="/WEB-INF/views/footerOuter.jsp" %>
</body>
</html>
