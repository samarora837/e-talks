<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title><spring:message code="tutor.classroom" /> </title>
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

<!-- <LINK href="http://media.muchosmedia.com/brainwave/style.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="http://media.muchosmedia.com/scribblar/scripts/includes.js"></script>

<script src="<%=request.getContextPath()%>/js/timer/jquery-backward-timer.min.js"></script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>

<body>

        
        

<div id="main">
<%@ include file="/WEB-INF/views/tutor/headerTutor.jsp" %>
<!--Mid Section-->
<section class="container">
  <div class="inner-row">
    <div class="classroom-stu-top">
      <ul>
        <li style="width:100%; text-align: center;"><span class="text-blue"><spring:message code="demoRoomText" /> </span></li>
     
      </ul>
    </div>
    <div class="form-tutor-con"  id="scribblarBoard" >
      <div class="tutor-info-outr">
        <div class="my-info-tutor tutor-db-outr" style="width: 100% !important;   min-height: 500px;">
          <div class="classroom-stu-txt"  id="scribSection">
           <!--  <h3>White Board Implementation</h3> -->
            <div class="cRoom-graph" id="alternate" >
            <a href="http://www.adobe.com/go/getflashplayer"><spring:message code="latest.adobe.require.msg" /><br>
            <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" border="0" alt="Get Adobe Flash Player" /> </a> 
            
            <%-- <img src="<%=request.getContextPath()%>/images/classmate-graph.jpg" alt=""> --%></div>
          </div>
        </div>
    
      
      </div>
   </div>
  </div>
  </div>
</section>
        
<!--//Mid Section-->
 <form action="<%=request.getContextPath()%>/tutor/endTutorScribblarSession" method="GET" id="endSessionForm">
<input type="hidden" id="bookingId" name="bookingId" readonly="readonly">
</form>

</div>
<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	
	window.onbeforeunload = function() {
	    return 'Are you sure you want to navigate away from this page?';
	};
	
	
	
	

</script> 

</body>


<script type="text/javascript">
var targetID = "scribblar";
var flashvars = {};
/* pass necessary variables to the SWF */
flashvars.userid = "0";											/* to allow an anonymous guest pass 0 */
flashvars.roomid = "d3rh3rf";									/* the roomid for the room you'd like to access - substitute this for a valid roomid */
flashvars.preferredLocales = "es-AR";   					/* sets the default language - if in doubt leave as en_US */
/* optional: if you pass userid=0 you may also pass a username to skip the username prompt and log the 
user in using that username 
flashvars.username = "John";	*/
flashvars.username = '${user}';
//flashvars.password="f26plcd9311q";
//flashvars.allowguests = 0;
var params = {};
params.allowscriptaccess = "always";


var attributes = {};
attributes.id = "scribblar";
attributes.name = "scribblar";
swfobject.embedSWF("http://media.muchosmedia.com/scribblar/v4/main.swf", "alternate", "100%", "100%", "11.1.0", "http://media.muchosmedia.com/swfobject/expressInstall.swf", flashvars, params, attributes);

</script>



</html>
