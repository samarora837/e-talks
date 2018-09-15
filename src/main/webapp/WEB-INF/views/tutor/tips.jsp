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
<title><spring:message code="tutor.tips" /></title>
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
 <%@ include file="/WEB-INF/views/tutor/headerTutor.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="bcm-tutor-con tips-con">
          <h1 class="text-left"><spring:message code="tutor.tips" /></h1>
          
          <div class="tips-top">
          	<div class="dlFiles">
            	<div class="hdr"><spring:message code="download.files"/></div>
                <ul>
                	<li>
                    	<a href="<%=request.getContextPath()%>${pdf1.pdfURL}" download><img src="<%=request.getContextPath()%>/images/pdf-icon.png" alt=""></a>
                    	<div class="filesTxt"><a href="<%=request.getContextPath()%>${pdf1.pdfURL}" download>${pdf1.pdfTitle}</a></div>
                    </li>
                    <li>
                    	<a href="<%=request.getContextPath()%>${pdf2.pdfURL}" download><img src="<%=request.getContextPath()%>/images/pdf-icon.png" alt=""></a>
                    	<div class="filesTxt"><a href="<%=request.getContextPath()%>${pdf2.pdfURL}" download>${pdf2.pdfTitle}</a></div>
                    </li>
                    <li>
                    	<a href="<%=request.getContextPath()%>${pdf3.pdfURL}" download><img src="<%=request.getContextPath()%>/images/pdf-icon.png" alt=""></a>
                    	<div class="filesTxt"><a href="<%=request.getContextPath()%>${pdf3.pdfURL}" download>${pdf3.pdfTitle}</a></div>
                    </li>
                </ul>
            </div>
            <div class="tipsTxt">
            ${content}
            </div>
          </div>
          <div class="tips-vid">
          	<ul>
            	 <c:forEach var="video" items="${cmsVideos}">
            	<li>
                	<h3>${video.videoText}</h3>
                    <div class="video">
		                <video width="100%" controls>
						  <source src="<%=request.getContextPath()%>${video.videoURL}" type="video/mp4">
						</video>
                    </div>
                </li>
          </c:forEach>
            </ul>
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
</html>
