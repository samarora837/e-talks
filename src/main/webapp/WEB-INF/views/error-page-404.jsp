<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"
    uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>MiProfe: 404 Error</title>
</head>
<body>
<!--Outerwrapper Starts -->
<div class="outerwrapper">

    <!--Content Section Starts -->
    <div class="content-section">
    
    <div class="commom-div mt20 pl5" align="center">
    
    <img alt="error 404" src="<%=request.getContextPath()%>/images/error-404.jpg"/>
    <h5 style="color: black;padding-top: 20px;">Sorry, this web-address doesn't exists or expired. Click <a href="<%=request.getContextPath()%>/">Here</a> to return to the MiProfe home page. </h5>
	</div>
	</div>
	
	
</div>
</body>

</html> --%>
<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"
    uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="error.404"/> </title>
<link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body class="page-404-full-page">
<!--Outerwrapper Starts -->
<div class="row">
	<div class="col-md-12 page-404">
		<div class="number">
			 404
		</div>
		
		<div class="details">
			<p>
				 AlóProfe
			</p>
			<h2><spring:message code="youarelost"/> </h2>
			<p>
				 <spring:message code="pagelookingfor"/> <br/>
				<a href="<%=request.getContextPath()%>/">
				<spring:message code="returnhome"/> </a>
			</p>
		</div>
	</div>
</div>
</body>

</html>