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
            response.setDateHeader("Expires", 1); //Causes the proxy cache to see the page as "stale"
            response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
 %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <meta http-equiv="refresh" content="10;url=<c:url value="/j_spring_security_logout?name=parent" />"> --%>
<title><spring:message code="title.parentsaccount"/> </title>
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

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
 <link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet">

 <script type="text/javascript">
 
 var numberofChild = "${fn:length(studentAccountActivities)}";

 $(document).ready(function(){
	 for(var i=1;i<=numberofChild;i++){
	    $('#myTable'+i).DataTable();
	 }
	});
 </script> 
<script type="text/javascript">
$(document).ready(function(){
	
	
	$.validator.addMethod("emailId", function(value, element) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');
	
	 $.validator.addMethod("emailIdExist", function(value, element) {
			var flag = emailCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.alreadyuseParent"></spring:message>'); 
	 
	 $.validator.addMethod("nametype", function(value, element) {
		  	return this.optional(element) || /^[A-Za-z ]{2,30}$/i.test(value);
			},'<spring:message code="student.validname"></spring:message>');
	 
	 $.validator.addMethod("passwordExist", function(value, element) {
			var flag = passwordCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="current.passwordnotcorrect"></spring:message>'); 
	 
	$('#parentEditForm').validate({
		rules:{
			email:{
				required:true,
				emailId:true,
				 emailIdExist:true 
			},
			password:{
				required:true,
				minlength : 8,
				maxlength : 20,
				passwordExist:true
			},
			newPassword:{
				required:true,
				minlength : 8,
				maxlength : 20
			},
			confirmPassword:{
				required:true,
				equalTo: "#newPassword"
			},
			/* parentEmail:{
				//ageBelow18:true,
				
				emailId:true,
				parentEmailIdExist:true
			}, */
		
			firstName:{
				required:true
				//nametype:true
			},
			lastName:{
				required:true
				//nametype:true
			}
			},

		
		 messages:{
			 email:{
					required:'<spring:message code="parent.emailRequired" />'
				},
				password:{
					required:"<spring:message code='password.required'></spring:message>",
					minlength:"<spring:message code='atleast8charactersrequired'></spring:message>",
					maxlength:"<spring:message code='passwordistoolong'></spring:message>"
				},
				newPassword:{
					required:"<spring:message code='newpassword.required'></spring:message>",
					minlength:"<spring:message code='atleast8charactersrequired'></spring:message>",
					maxlength:"<spring:message code='passwordistoolong'></spring:message>"
				},
				confirmPassword:{
					required:"<spring:message code='confirm.passwordrequired'></spring:message>",
					equalTo:"<spring:message code='password.notmatch'></spring:message>"
				},
			 firstName:{
				required:'<spring:message code="parent.fnameRequired" />'
			},
			lastName:{
				required:'<spring:message code="parent.lnameRequired" />'
			}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});


function emailCheck(){
	
	var email=$("#email").val();
	
	var paretnEmail='${dtoParentRegistration.email}';
	
	var flag = false;
	
	if(email==paretnEmail){
		flag=true;
	}
	
	else{
	 var url='<%=request.getContextPath()%>/emailCheck';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{email:email},
		success:function(response){
		if(response=="error"){
			flag = false;
			}
		else{
			flag = true;
		}
		}
		
	});  
	}
	return flag;
	
}


function passwordCheck(){
	
	var pass=$("#password").val();
	var flag = false;
	 var url='<%=request.getContextPath()%>/parent/passwordCheck';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{pass:pass},
		success:function(response){
		if(response=="error"){
			flag = false;
			}
		else{
			flag = true;
		}
		}
		
	});  
	return flag;
}
</script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>

</head>
<body>
<div id="main">
<%@ include file="/WEB-INF/views/parent/parent-inner-header.jsp" %>


  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
       		<div class="my-info">
            	<h1 class="text-left"><spring:message code="my.info"></spring:message></h1>
                <div class="my-info-txt">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <th width="130"><spring:message code="parent.name" /></th>
                    <td>:</td>
                    <td> <div class="word-wrap"> ${dtoParentRegistration.firstName} ${dtoParentRegistration.lastName}</div></td>
                  </tr>
                  <tr>
                   <th><spring:message code="parent.email" /></th>
                    <td>:</td>
                    <td> <div class="word-wrap"> ${dtoParentRegistration.email}</div></td>
                  </tr>
                   <tr>
                    <th><spring:message code="parent.password" /></th>
                    <td>:</td>
                    <td> ${dtoParentRegistration.passwordLength}</td>
                  </tr>
                  <tr>
                   <th><spring:message code="parent.paySystem" /></th>
                    <td>:</td>
                    <td> NA</td>
                  </tr>
                   <tr>
                   <th colspan="3"><input type="button" value='<spring:message code="parent.editInfo" />' class="edit-infoBtn" onclick="openEditPopUp();" /></th>
                  </tr>
                </table>
                </div>
            
            </div>
            
            <div class="my-kids">
           		<h1 class="text-left"> <spring:message code="parent.myKidsAt" /></h1>
                <div class="my-kids-txt">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                	
                	 <c:set var="count" value="0" scope="page"/>
                	<c:forEach items="${dtoStudentDetailList}" var="dtoStudentDetailList">
                	
                	   <tr>
                        <td>
                        	<div class="nameTxt">${dtoStudentDetailList.fullName}</div>                            
                            <div class="planTxt"><spring:message code="student.plan"/> : ${dtoStudentDetailList.planName}</div>
                            <div class="emailTxt"><spring:message code="student.mimbalance"/> : ${dtoStudentDetailList.minBalance} <spring:message code="mins"/></div>
                        </td>
                        <td class="text-right"><a onclick="submitChanagePlanForm('${dtoStudentDetailList.userId}');" class="blueBtn-normal"><spring:message code="parent.changePlaneOrBuy" /></a></td>
                      </tr>
                	
                	
                	
                	
                	
                	 <c:set var="count" value="1" scope="page"/>
                        </c:forEach> 
                        
                        <c:if test="${count eq '0' }">
                        
                           <tr>
                        <td>
                        	<div class="nameTxt"><spring:message code="norecord.found"/></div>
                            <div class="emailTxt"></div>
                            <div class="planTxt"></div>
                        </td>
                        <td class="text-right"></td>
                      </tr>
                        
                       </c:if>
                     
                
                    </table>
                </div>
            </div>
                  <form action="changePlan" method="get" name="changePlanForm" id="changePlanForm">
                	<input type="hidden" id="studentId" name="studentId"/>
                	</form> 
          <c:set var="counter" value="1" scope="page"/>
            
         <c:forEach items="${studentAccountActivities}" var="studentAccountActivities">
          <c:set var="count" value="0" scope="page"/>
          
         <div class="ac-activity ac-activity-2">
        
          <c:forEach items="${studentAccountActivities}" var="childName">
          <c:if test="${count eq '0' }">
          <c:set var="string2" value="${fn:substring(childName.studentProfileDetail.last_Name, 0, 1)}" />
           		<h1 class="text-left"> <spring:message code="parent.accountActivity" /> - ${childName.studentProfileDetail.first_Name} ${fn:toUpperCase(string2)}.</h1>
          <c:set var="count" value="1" scope="page"/>
          </c:if>
         </c:forEach> 
         
                <div class="ac-activity-txt mesg-tab">
                
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id='myTable${counter}'>
                	<thead>
                      <tr>
                        <th><spring:message code="parent.date" /> </th>
                        <th><spring:message code="parent.activity" /></th>
                        <th width="70"> $ </th>
                        <th> <spring:message code="parent.minutes" /></th>
                        <th> <spring:message code="parent.minBalance" />  </th>
                        <%-- <th class="text-center"> <spring:message code="parent.view" /></th> --%>
                      </tr>
                      </thead>
                      <tbody>
        		 <c:forEach items="${studentAccountActivities}" var="childActivities">
         
                      <tr>
                        <td><fmt:formatDate pattern="dd-MM-yy" value="${childActivities.activity_Date }"/></td>
                        <td>${childActivities.activity_Name }</td>
                        <td>${childActivities.amount }</td>
                        <td>${childActivities.activity_Minute } Mins</td>
                        <td>${childActivities.min_Balance } Mins</td>
                      <%--   <td class="text-center"><a  class="greenBtn-small"><spring:message code="parent.view" /></a></td> --%>
                      </tr>
         
         		</c:forEach>
         </tbody>
           </table>
             </div>
              </div>
               <c:set var="counter" value="${counter + 1}" scope="page"/>
         </c:forEach>
            
        </div>
      </div>
    </div>
  </section>
  <!--//Mid Section--> 
</div>


<div class="clr"></div>
 <%@ include file="/WEB-INF/views/footerInner.jsp" %>



<div id="editpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="" onclick="closeEditPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="parentEditForm" id="parentEditForm" method="post"  commandName="dtoParentRegistration" >
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody><tr>
                    <th><spring:message code="parent.firstname"/> </th>
                    <td>:</td> 
                    <td><div ><form:input type="text" placeholder="" path="firstName" id="firstName" name="firstName" maxlength="30" minlength="2"/></div></td>
                  </tr>
                  <tr>
                    <th><spring:message code="parent.lastname"/></th>
                    <td>:</td> 
                    <td><div ><form:input type="text" placeholder="" path="lastName" id="lastName" name="lastName" maxlength="30" minlength="2"/></div></td>
                  </tr>
                  <tr>
                   <th><spring:message code="parent.email"/> </th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder=""  path="email" id="email" name="email" maxlength="80"/></div></td>
                  </tr>
                  
                   <tr>
                   <th> </th>
                    <td></td>  
                    <td>
                    <a id="changePassword" onclick="showPassword();"><spring:message code="change.password"/></a>
                   <%--  <a id="cancelPassword" onclick="hidePassword();"><spring:message code="cancel"/></a> --%>
                    </td>
                  </tr>
                  
                        <tr id="cPassword">
                   <th><spring:message code="current.password"></spring:message> </th>
                    <td>:</td>  
                    <td><div ><input type="password" placeholder="" id="password" name="password" maxlength="80"/></div></td>
                  </tr>
                  
                   <tr id="nPassword">
                   <th><spring:message code="new.password"></spring:message> </th>
                    <td>:</td>  
                    <td><div ><form:input type="password" placeholder="" path="newPassword" id="newPassword" name="newPassword" maxlength="80"/></div></td>
                  </tr>
                  
                    <tr id="conPassword">
                   <th><spring:message code="signup.confirmpassword"></spring:message> </th>
                    <td>:</td>  
                    <td><div><input type="password" placeholder=""  id="confirmPassword" name="confirmPassword" maxlength="80"/></div></td>
                  </tr>
                  
               <%--     <tr>
                   <th>Password</th>
                    <td>:</td>  
                    <td><div class="popUp-table-rt"><form:input type="password" placeholder="" path="password" id="password" name="password" maxlength="30"/></div></td>
                  </tr> --%>
                   
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitEditForm();">
                     <input id="cancelPassword" type="button" class="greenBtn-normal" value='<spring:message code="cancel"></spring:message>' onclick="hidePassword();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form:form>
        	
        </div>
   </div>

</div>



</body>

<script type="text/javascript">

function openEditPopUp()
{
	$("#editpopup").show();
	
}

function closeEditPopUp(){
	$("label.error").hide();
	$("#editpopup").hide();
}


function submitEditForm(){
	$("#parentEditForm").attr("action", "<%=request.getContextPath()%>/parent/updateParentInfo");
	$("#parentEditForm").submit();
}


function submitChanagePlanForm(studentId){
	
	$("#studentId").val(studentId);
	$("#changePlanForm").submit();
}

</script>

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
  
  
  $("#cancelPassword").hide();
  $("#cPassword").hide();
  $("#nPassword").hide();
  $("#conPassword").hide();
 
});


function showPassword()
{
	$("#changePassword").hide();
	$("#cancelPassword").show();
	$("#cPassword").show();
	$("#nPassword").show();
	$("#conPassword").show();

}


function hidePassword()
{
	$("#changePassword").show();
	$("#cancelPassword").hide();
	
	$("#password").val("");
	$("#newPassword").val("");
	$("#confirmPassword").val("");
	$('label.error').css('display', 'none');
	
	$("#cPassword").hide();
	$("#nPassword").hide();
	$("#conPassword").hide();
}


</script> 



</html>
