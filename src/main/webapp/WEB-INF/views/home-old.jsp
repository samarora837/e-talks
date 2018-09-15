<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
<title><spring:message code="home.title"></spring:message></title>
<link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/favicon.ico">
 <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
 <link href="<%=request.getContextPath()%>/css/developer.css" rel="stylesheet">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700,400italic,300' rel='stylesheet' type='text/css'>    
      <script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js" type="text/javascript" ></script>
      
      
       <style type="text/css">
            @media only screen and (max-width : 540px) 
            {
                .chat-sidebar
                {
                    display: none !important;
                }
                
                .chat-popup
                {
                    display: none !important;
                }
            }
            
            body
            {
                background-color: #e9eaed;
            }
            
            .chat-sidebar
            {
                width: 200px;
                position: fixed;
                height: 100%;
                right: 0px;
                top: 0px;
                padding-top: 10px;
                padding-bottom: 10px;
                border: 1px solid rgba(29, 49, 91, .3);
                background-color: rgb(238, 237, 237);
            }
            
            .sidebar-name 
            {
                padding-left: 10px;
                padding-right: 10px;
                margin-bottom: 4px;
                font-size: 12px;
            }
            
            .sidebar-name span
            {
                padding-left: 5px;
            }
            
            .sidebar-name a
            {
                display: block;
                /* height: 100%; */
                text-decoration: none;
                color: inherit;
            }
            
            .sidebar-name:hover
            {
                background-color:#e1e2e5;
            }
            
            .sidebar-name img
            {
                width: 32px;
                height: 32px;
                vertical-align:middle;
            }
            
            .popup-box
            {
                display: none;
                position: fixed;
                bottom: 0px;
                right: 220px;
                height: 285px;
                background-color: rgb(237, 239, 244);
                width: 300px;
                border: 1px solid rgba(29, 49, 91, .3);
            }
            
            .popup-box .popup-head
            {
                background-color: #6d84b4;
                padding: 5px;
                color: white;
                font-weight: bold;
                font-size: 14px;
                clear: both;
            }
            
            .popup-box .popup-head .popup-head-left
            {
                float: left;
            }
            
            .popup-box .popup-head .popup-head-right
            {
                float: right;
                opacity: 0.5;
            }
            
            .popup-box .popup-head .popup-head-right a
            {
                text-decoration: none;
                color: inherit;
            }
            
            .popup-box .popup-messages
            {
                height: 100%;
                overflow-y: scroll;
            }
            


        </style>
        
        <script type="text/javascript">
            //this function can remove a array element.
            Array.remove = function(array, from, to) {
                var rest = array.slice((to || from) + 1 || array.length);
                array.length = from < 0 ? array.length + from : from;
                return array.push.apply(array, rest);
            };
        
            //this variable represents the total number of popups can be displayed according to the viewport width
            var total_popups = 0;
            
            //arrays of popups ids
            var popups = [];
        
            //this is used to close a popup
            function close_popup(id)
            {
                for(var iii = 0; iii < popups.length; iii++)
                {
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                        
                        document.getElementById(id).style.display = "none";
                        
                        calculate_popups();
                        
                        return;
                    }
                }   
            }
        
            //displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
            function display_popups()
            {
                var right = 220;
                
                var iii = 0;
                for(iii; iii < total_popups; iii++)
                {
                    if(popups[iii] != undefined)
                    {
                        var element = document.getElementById(popups[iii]);
                        element.style.right = right + "px";
                        right = right + 320;
                        element.style.display = "block";
                    }
                }
                
                for(var jjj = iii; jjj < popups.length; jjj++)
                {
                    var element = document.getElementById(popups[jjj]);
                    element.style.display = "none";
                }
            }
            
            //creates markup for a new popup. Adds the id to popups array.
            function register_popup(id, name)
            {
                
                for(var iii = 0; iii < popups.length; iii++)
                {   
                    //already registered. Bring it to front.
                    if(id == popups[iii])
                    {
                        Array.remove(popups, iii);
                    
                        popups.unshift(id);
                        
                        calculate_popups();
                        
                        
                        return;
                    }
                }               
                
                var element = '<div class="popup-box chat-popup" id="'+ id +'">';
                element = element + '<div class="popup-head">';
                element = element + '<div class="popup-head-left">'+ name +'</div>';
                element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div>';
                element = element + '<div style="clear: both"></div></div><div class="popup-messages"></div></div>';
                
                document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
        
                popups.unshift(id);
                        
                calculate_popups();
                
            }
            
            //calculate the total number of popups suitable and then populate the toatal_popups variable.
            function calculate_popups()
            {
                var width = window.innerWidth;
                if(width < 540)
                {
                    total_popups = 0;
                }
                else
                {
                    width = width - 200;
                    //320 is width of a single popup box
                    total_popups = parseInt(width/320);
                }
                
                display_popups();
                
            }
            
            //recalculate when window is loaded and also when window is resized.
            
        </script>      
      
      
  <%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>    
</head>

<body>
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>
  <!--Banner-->
  <section class="banner-outr">
    <div class="inner-row">
      <div class="banner-txt">
        <div class="banner-txt-rt">
          <h2> <spring:message code="home.upgradenumber"></spring:message> <br>
            <spring:message code="home.connect"></spring:message> </h2>
          <form action="<%=request.getContextPath()%>/searchTutors" method="POST" id="searchTutors">
          <div class="banner-search">
            <input type="text" id="homeSearchTutor" name="homeSearchTutor" placeholder='<spring:message code="home.search.placeholder"></spring:message>' >
            <a href="javascript:;" onclick="submitForm();" class="searchBtn"><spring:message code="home.search"></spring:message> </a> </div>
            </form>
        </div>
        <div class="banner-txt-lt">
          <div class="banner-img"><img src="<%=request.getContextPath()%>/images/banner-img.png" alt=""></div>
        </div>
      </div>
    </div>
  </section>
  <!--//Banner--> 
  <!--Mid Section-->
  <section class="container">
    <div class="chat-teacher-con">
      <div class="chat-hdr">
        <h2><spring:message code="home.chatwithteacher"></spring:message></h2>
      </div>
      <div class="inner-row">
        <div class="chat-teacher-txt">
          <ul>
            <li>
              <div class="chat-teacher-hdr"><spring:message code="mathsteachers"></spring:message></div>
              <div class="chat-teacher-inner">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img1.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="hilaryswank"></spring:message></p>
                        <div><spring:message code="civilengineeringUC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img2.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="darrelharper"></spring:message></p>
                        <div><spring:message code="physicsPPC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/blue-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img3.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="danielscottr"></spring:message></p>
                        <div><spring:message code="maths"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img4.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="tinaharper"></spring:message></p>
                        <div><spring:message code="english"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                </table>
              </div>
            </li>
            <li>
              <div class="chat-teacher-hdr">Physics Teachers</div>
              <div class="chat-teacher-inner">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img1.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="hilaryswank"></spring:message></p>
                        <div><spring:message code="civilengineeringUC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img2.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="darrelharper"></spring:message></p>
                        <div><spring:message code="physicsPPC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/blue-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img3.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="danielscottr"></spring:message></p>
                        <div><spring:message code="maths"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img4.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="tinaharper"></spring:message></p>
                        <div><spring:message code="english"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                </table>
              </div>
            </li>
            <li>
              <div class="chat-teacher-hdr"><spring:message code="chemistryteachers"></spring:message></div>
              <div class="chat-teacher-inner">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img1.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="hilaryswank"></spring:message></p>
                        <div><spring:message code="civilengineeringUC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img2.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="darrelharper"></spring:message></p>
                        <div><spring:message code="physicsPPC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/blue-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img3.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="danielscottr"></spring:message></p>
                        <div><spring:message code="maths"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="images/star-yellow.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="images/chat-img4.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="tinaharper"></spring:message></p>
                        <div><spring:message code="english"></spring:message></div>
                        <div class="chat-img-stars"> <img src="images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                </table>
              </div>
            </li>
            <li>
              <div class="chat-teacher-hdr"><spring:message code="englishteachers"></spring:message></div>
              <div class="chat-teacher-inner">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img1.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="hilaryswank"></spring:message></p>
                        <div><spring:message code="civilengineeringUC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img2.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="darrelharper"></spring:message></p>
                        <div><spring:message code="physicsPPC"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/blue-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img3.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="danielscottr"></spring:message></p>
                        <div><spring:message code="maths"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> </div>
                      </div></td>
                  </tr>
                  <tr>
                    <td><div class="indi-icon"><img src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt=""></div>
                      <div class="chat-img"><img src="<%=request.getContextPath()%>/images/chat-img4.png" alt=""></div>
                      <div class="chat-img-txt">
                        <p><spring:message code="tinaharper"></spring:message></p>
                        <div><spring:message code="english"></spring:message></div>
                        <div class="chat-img-stars"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
                      </div></td>
                  </tr>
                </table>
              </div>
            </li>
          </ul>
        </div>
        <div class="chat-indications"> <span><img src="<%=request.getContextPath()%>/images/green-bullet.png" alt=""> <spring:message code="home.connectnow"></spring:message></span> <span><img src="<%=request.getContextPath()%>/images/blue-bullet.png" alt=""> <spring:message code="home.requestconnection"></spring:message></span> <span><img src="<%=request.getContextPath()%>/images/yellow-bullet.png" alt=""> <spring:message code="home.sessionscheduled"></spring:message> </span> </div>
      </div>
    </div>
    
    
    <div class="home-hw-it-works">
      <div class="inner-row">
        <div class="hw-it-works-txt">
          <h2><spring:message code="home.text"></spring:message></h2>
          <ul>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td> <img src="<%=request.getContextPath()%>/images/search-icon.png" alt=""></td></tr>
				</tbody></table>
              </div>
              <h4><spring:message code="home.searchsubject"></spring:message></h4>
              <div><spring:message code="namactortor"></spring:message></div>
            </li>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td> <img src="<%=request.getContextPath()%>/images/tutor-icon.png"  alt=""></td></tr>
				</tbody></table>
              
              
              </div>
              <h4><spring:message code="home.looktutor"></spring:message></h4>
              <div><spring:message code="namactortorfringilla"></spring:message></div>
            </li>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td> <img src="<%=request.getContextPath()%>/images/chat-icon.png"  alt=""></td></tr>
				</tbody></table>
              
              </div>
              <h4><spring:message code="home.chatwithtutor"></spring:message></h4>
              <div><spring:message code="namactortorfringillamassa"></spring:message></div>
            </li>
            <li>
              <div class="work-icons">
              <table cellspacing="0" cellpadding="0">
				<tbody><tr><td>  <img src="<%=request.getContextPath()%>/images/link-icon.png"  alt=""></td></tr>
				</tbody></table>
            
              </div>
              <h4><spring:message code="home.connecttutor"></spring:message></h4>
              <div><spring:message code="namactortorfringillamassainterdum"></spring:message></div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    
    
    <div class="recom-con">
      <div class="inner-row">
        <div class="recom-con-lt">
          <h3><spring:message code="home.manystudentparent"></spring:message></h3>
          <div class="recom-con-txt">
            <div class="recom-img"><img src="<%=request.getContextPath()%>/images/recom-img1.png" alt=""></div>
            <div class="recom-img-txt"> <spring:message code="naNamactortor"></spring:message>
              <div class="recom-img-name"> <spring:message code="marelynHaynes"></spring:message> <span><spring:message code="student"></spring:message></span> </div>
            </div>
          </div>
          <div class="recom-con-txt">
            <div class="recom-img"><img src="<%=request.getContextPath()%>/images/recom-img2.png" alt=""></div>
            <div class="recom-img-txt"> <spring:message code="naNamactortorfringilla"></spring:message>
              <div class="recom-img-name"> <spring:message code="alexanderphilip"></spring:message>, <span><spring:message code="parent"></spring:message></span> </div>
            </div>
          </div>
        </div>
        <div class="recom-con-news">
          <h3><spring:message code="home.news"></spring:message></h3>
          <div class="news-txt"> <spring:message code="loremIpsum"></spring:message>
            <p><spring:message code="universityofNewYork"></spring:message></p>
          </div>
          <div class="news-txt"> <spring:message code="loremIpsum"></spring:message>
            <p><spring:message code="universityofNewYork"></spring:message></p>
          </div>
          <div class="news-btn"> <a href="javascript:;"><spring:message code="home.readmore"></spring:message> </a> </div>
        </div>
      </div>
    </div>
    
    
    
  <!--   ========================================== sidebar chat =========================== -->
    
    <div class="chat-sidebar">
    
   
    
    <c:forEach var="subjectList" items="${subjectMasterName}">
    
    <h3> ${subjectList.subject_Type}</h3>
    
       <c:forEach var="tutorDetails" items="${tutorDetails}">
       
       
       <c:set var="subjectName" value="${subjectList.subject_Type}"/>
			<c:if test="${fn:contains(tutorDetails.subjectTypeMasterName, subjectName)}">
       
       <div class="sidebar-name">
                <!-- Pass username and display name to register popup -->
                <a href="javascript:register_popup('${tutorDetails.userId}', '${tutorDetails.name}');">
                    
                    <c:if test="${tutorDetails.imageName !=''}">
	              	<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/profilePictures/${tutorDetails.userId}/fileupload/${tutorDetails.userId}${tutorDetails.imageName}"/>
	              	</c:if>
	              	<c:if test="${tutorDetails.imageName ==''}">
	              	<img width="30" height="30" alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>
	              	</c:if>
	              	<c:if test="${tutorDetails.imgUrl !=null}">
	              	<img width="30" height="30" alt="" src="${tutorDetails.imgUrl}"/>
	              	</c:if>
                    
                    <span>${tutorDetails.name}</span>
                </a>
            </div>
			</c:if>
       </c:forEach>
       </c:forEach>
        </div> 
    
    
    
 <!--   =========================== side bar chat end=============================================  -->
    
    
    
  </section>
  <!--//Mid Section--> 
</div>


       

<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerOuter.jsp" %>
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


function submitForm(){
	$("#searchTutors").submit();
}

</script> 


</body>
</html>
