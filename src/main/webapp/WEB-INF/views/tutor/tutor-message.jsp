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
<%-- <title><spring:message code="student.register" /></title> --%>
<title><spring:message code="message.title"></spring:message></title>
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
 
  
 <script type="text/javascript">
  $(function() {
	  $( ".ellipsis" ).tooltip({
		  open: function (event, ui) {
		        ui.tooltip.css("word-break", "break-all");
		    },
	      position: {
	        my: "top",
	        at: "top+15"
	      }
	    });
  });
  </script>
 
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
	});
 
 
  $(document).ready(function(){
		
		 $.validator.addMethod('selectcheck', function (value) {
		        return (value != '0');
		    }, '<spring:message code="student.select"></spring:message>');
		 
 
		
		 $('#sendMessageForm').validate({
			
			rules:{
				
				toId: {
	                selectcheck: true,
	                required:true
	            },
	            message: {
	                required:true
	            }
	           		
				},

			
			 messages:{			
				
				 toId:{
					required:"<spring:message code='student.select'></spring:message>"
					}
			}, 
			submitHandler:function(form){
	    		form.submit();
	    	}
		});
		 
		 $('#replyMessageForm').validate({
				
				rules:{
					
					
		            message: {
		                required:true
		            }
		           		
					},

				
				 messages:{			
					
					 
				}, 
				submitHandler:function(form){
		    		form.submit();
		    	}
			});
		

	}); 
  
 
 
 </script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body>
<div id="main">
  <%@ include file="/WEB-INF/views/tutor/headerTutor.jsp" %>
  
  
  
 <c:if test="${showPopup eq 'true' }">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/tutor/messages"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code='messagesent.success'/>
            </section>
            <%-- <p><spring:message code="wehavesentyourrequesttoAdminforapproval" /></p> --%>
        </div>
        </div>
        </div>
   </c:if> 
  
  
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
            <div class="ac-activity ac-activity-2 mt0">
           		<h1 class="text-left"><spring:message code="parent.messages"/> <a onclick="openSendMessagePopUp();" class="text-right"> <spring:message code="send.newmessage"></spring:message></a></h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
                	<thead>
                      <tr>
                       <th width="150"><spring:message code="parent.date"/> </th>
                        <th width="150"><spring:message code="parent.date"/> </th>
                        <th width="175"> <spring:message code="to"/> </th>
                        <th width="175"><spring:message code="from"/></th>
                        <th width="400"><spring:message code="message"/></th>
                        <th width="100"><spring:message code="action"/></th>
                      </tr>
                      </thead>
                      <tbody>
                	<c:forEach items="${messgaList}" var="messgaList">
                     <tr>
                      <td>${messgaList.messageDateTest}</td>
                        <td>${messgaList.messageDate}</td>
                        <td>${messgaList.toName}</td>
                        <td>${messgaList.fromName}</td>
                        <td>
                       <div class="ellipsis" style="width:500px;" title="${messgaList.message}"> ${messgaList.message}</div>
                        </td>
                        <td class="center">
							<c:if test="${messgaList.fromName ne user}">
							<a onclick="openReplyPopUp('${messgaList.fromUserName}','${messgaList.toUserName}',${messgaList.toId});"><spring:message code="reply"/> </a>
							</c:if>
						</td>
                      </tr>
                      
                        </c:forEach> 
                        
                    </tbody>
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
 
 <div id="sendMessagepopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeSendMessagePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="sendMessageForm" id="sendMessageForm" method="post" commandName="dtoMessageDetail">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                  
                  <tr>
                   <th><spring:message code="to"/></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                   <form:select path="toId" class="bs-select form-control" id="toId" name="toId">
												
				  </form:select>
                    </div>
                    </td>
                  </tr>
             		<tr>
                   <th><spring:message code="from"/></th>
                    <td>:</td>  
                    <td><div id="fromName"></div></td>
                  </tr>
                  
                  
                   <tr>
                   <th class="v-alignTop"><spring:message code="message"/></th>
                    <td class="v-alignTop">:</td>  
                    <td class="v-alignTop"><div ><form:textarea name="message" id="message" rows="4" cols="20" path="message" maxlength="250"></form:textarea></div></td>
                  </tr>
                  
                  
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="send.message"></spring:message>' onclick="submitSendMessageForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form:form>
        	
        </div>
   </div>

</div>



 <div id="replyPopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeReplyMessagePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="replyMessageForm" id="replyMessageForm" method="post" commandName="dtoMessageDetail">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                  
                    <tr>
                   <th><spring:message code="to"/></th>
                    <td align="right">:</td> 
                    <td><div id="toUserName"></div></td>
                  </tr>
                  
             		<tr>
                   <th><spring:message code="from"/></th>
                    <td>:</td>  
                    <td><div id="fromUserName"></div></td>
                  </tr>
                  
                   <tr class="msg-row">
                   <th class="v-alignTop"><spring:message code="message"/></th>
                    <td class="v-alignTop">:</td>  
                    <td class="v-alignTop"><div ><form:textarea name="messageReply" id="messageReply" rows="4" cols="20" path="message" maxlength="250"></form:textarea></div></td>
                  </tr>
                  
                  
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="send.message"></spring:message>' onclick="submitReplyMessageForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                <input type="hidden" id="toIdReply" name="toIdReply">
                </form:form>
        	
        </div>
   </div>

</div>


</body>

  
<script type="text/javascript">


function openSendMessagePopUp()
{
	$(window).scrollTop(0);
	$( "#toId" ).focus();
	
	var url='<%=request.getContextPath()%>/tutor/getAllEmail';
	$.ajax({
		url:url,
		method:'POST',
		async :false,
		success:function(response){
			if(response!=null){
				$("#fromName").text(response.modelMap.messageDetails.fromName);
				
				
				$('#toId').children().remove();
				$('#toId').append('<option value="0"><spring:message code="selectany.value"/></option>')
				//$('#toId').append('<optgroup label="Tutors">');
				 //$.each( response.modelMap.messageDetails.tutorEmail, function( key , value ) {
				//$('#toId').append('<option value="'+key+'">'+value+'</option>');
				// });
				// $('#toId').append('</optgroup'); 
				 
				 $('#toId').append('<optgroup label="<spring:message code='parents'/>">');
				 $.each( response.modelMap.messageDetails.parentEmail, function( key , value ) {
				$('#toId').append('<option value="'+value+'">'+key+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $('#toId').append('<optgroup label="<spring:message code='students'/>">');
				 $.each( response.modelMap.messageDetails.studentEmail, function( key , value ) {
				$('#toId').append('<option value="'+value+'">'+key+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $('#toId').append('<optgroup label="<spring:message code='customer.support'/>">');
				 $.each( response.modelMap.messageDetails.supportEmail, function( key , value ) {
				$('#toId').append('<option value="'+value+'">'+key+'</option>');
				 });
				 $('#toId').append('</optgroup');
				 
				 $("#sendMessagepopup").show();
		}
		
		}
	});
	
}

function closeSendMessagePopUp(){
	 $("#message").val("");
	 $("label.error").hide();
	 
	$("#sendMessagepopup").hide();
}


function submitSendMessageForm(){
	 
	  
	$("#sendMessageForm").attr("action", "<%=request.getContextPath()%>/tutor/sendMessage");
	$("#sendMessageForm").submit();
	 
}


function openReplyPopUp(fromUserName,toUserName,toId){
	
	$('#fromUserName').text(fromUserName);
	$('#toUserName').text(toUserName);
	$('#toIdReply').val(toId);
	$(window).scrollTop(0);
	$('#replyPopup').show();
}



function closeReplyMessagePopUp(){
	 $("#messageReply").val("");
	 $("label.error").hide();
	 
	$("#replyPopup").hide();
}

function submitReplyMessageForm(){
	 
	  
	$("#replyMessageForm").attr("action", "<%=request.getContextPath()%>/tutor/sendReply");
	$("#replyMessageForm").submit();
	 
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
 

</script> 



</html>
