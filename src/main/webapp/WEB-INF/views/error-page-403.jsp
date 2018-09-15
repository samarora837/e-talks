<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=ISO-8859-1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"
    uri="http://java.sun.com/jsp/jstl/fmt" %>
    <!DOCTYPE HTML>
   
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="error.403"></spring:message></title>
<link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body class="page-404-full-page">
<!--Outerwrapper Starts -->
<div class="row">
	<div class="col-md-12 page-404">
		<div class="number">
			 403
		</div>
		
		<div class="details">
			<p>
				 AlóProfe
			</p>
			<h2><spring:message code="access.denied"></spring:message> </h2>
			<p>
				 <spring:message code="notallowedaccesspage"></spring:message>
			</p>
		</div>
	</div>
</div>
</body>

</html>