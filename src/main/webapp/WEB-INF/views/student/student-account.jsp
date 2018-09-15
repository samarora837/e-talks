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
<title><spring:message code="student.account" /></title>
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

<%-- <script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js" type="text/javascript" ></script> --%>

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>
 <link href="<%=request.getContextPath()%>/css/jquery.dataTables.min.css" rel="stylesheet">

 <script type="text/javascript">
 $(document).ready(function(){
	    $('#myTable').DataTable();
	  
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
			},'<spring:message code="email.alreadyuse"></spring:message>'); 
	 
	/*  $.validator.addMethod("nametype", function(value, element) {
		  	return this.optional(element) || /^[A-Za-z ]{2,30}$/i.test(value);
			},'<spring:message code="student.validname"></spring:message>'); */
	 
	 
	 $.validator.addMethod("passwordExist", function(value, element) {
			var flag = passwordCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="current.passwordnotcorrect"></spring:message>'); 
	 
/* 	 
	 $.validator.addMethod("parentEmailIdExist", function(value, element) {
			var flag = parentEmailCheck();
		  	return this.optional(element) || flag;	   
			},"Email is already registered."); 
	 */
	$('#editInfoForm').validate({
		rules:{
			email:{
				required:true,
				emailId:true,
				emailIdExist:true,
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
		
			firstName:{
				required:true
				//nametype:true
			},
			lastName:{
				required:true
				//nametype:true
				
			},
			birthDate:{
				//ageBelow18:true,
				required:true
			}
			
		
			},

		
		 messages:{
			 email:{
					required:"<spring:message code='email.required'></spring:message>",
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
				required:"<spring:message code='signup.fNamerequired'></spring:message>"
			},
			lastName:{
				required:"<spring:message code='signup.lNamerequired'></spring:message>"
			},
			birthDate:{
				required:"<spring:message code='student.birthdaterequired'></spring:message>"
			}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});


function emailCheck(){
	
	var email=$("#email").val();
	var userEmail='${dtoStudentRegistration.email}';
	var flag = false;
	if(email==userEmail){
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
	 var url='<%=request.getContextPath()%>/student/passwordCheck';
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


<%-- 
function parentEmailCheck(){
	
	var parentEmail=$("#parentEmail").val();
	var flag = false;
	 var url='<%=request.getContextPath()%>/parentEmailCheck';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{parentEmail:parentEmail},
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
} --%>


/* function checkAge()
{
	var birthDate=$("#birthDate").val();
	var flag = true;
	if(birthDate!="")
		{
		var today = new Date();
		
		var parts =birthDate.split('/');
		
		var mydate = new Date(parts[2],parts[0]-1,parts[1]);
		   
		   var age = calculateAge(mydate,today);
		   if(age<18)
			   {
			   $("#parentEmailError").text("parent email required.");
			   
			   flag = false;
			   }
		   else{
			   $("#parentEmailError").text("");
				flag = true;
			}
		   
		}
	return flag;
}

function clrError()
{
	$("#parentEmailError").text("");
	
}

function calculateAge(d1,d2)
{
	
	    
	    var d1 = new Date(d1); //from date yyyy-MM-dd
     var d2 = new Date(d2); //to date yyyy-MM-dd (taken currentdate)
     var Months = d2.getMonth() - d1.getMonth();
     var Years = d2.getFullYear() - d1.getFullYear();
     //var Days = d2.getDate() - d1.getDate();
     Months = (d2.getMonth() + 12 * d2.getFullYear()) - 
     		(d1.getMonth() + 12 * d1.getFullYear());
     var MonthOverflow = 0;
     if (Months - (Years * 12) < 0)
         MonthOverFlow = -1;
     else
         MonthOverFlow = 1;
     if (MonthOverFlow < 0)
         Years = Years - 1; Months = Months - (Years * 12);
     var LastDayOfMonth = new Date(d2.getFullYear(), 
     		d2.getMonth() + 1, 0, 23, 59, 59);
     LastDayOfMonth = LastDayOfMonth.getDate();
      if (MonthOverFlow < 0 && (d1.getDate() > d2.getDate())) {
         Days = LastDayOfMonth + (d2.getDate() - d1.getDate()) - 1;
     }
     else
         Days = d2.getDate() - d1.getDate();
     if (Days < 0)
         Months = Months - 1;
     var l = new Date(d2.getFullYear(), d2.getMonth(), 0);
     var l1 = new Date(d1.getFullYear(), d1.getMonth() + 1, 0);
     if (Days < 0) 
     {
         if (l1 > l)
             Days = l1.getDate() + Days;
         else
             Days = l.getDate() + Days;
     }
     if(Months<0){
    	 
    	 Months=0;
     }
     if(Years<0){
    	 Years=0;
    	 Months=0;
     }
     var calculatedAge=Years;
     return calculatedAge;
    // alert(Years +  "Year(s), " + Months + " Month(s), " + Days + "Day(s)"); 
	
} */



</script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body>
<div id="main">
<%@ include file="/WEB-INF/views/student/student-inner-header.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="stu-ac-outr"> 
       		<div class="my-info">
            	<h1 class="text-left"><spring:message code="my.info"></spring:message> </h1>
                <div class="my-info-txt">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <th width="130"><spring:message code="parent.name"></spring:message></th>
                    <td>:</td>
                    <td> <div class="word-wrap"> ${dtoStudentRegistration.fullName}</div></td>
                  </tr>
                  <tr>
                   <th><spring:message code="parent.email"></spring:message></th>
                    <td>:</td>
                    <td> <div class="word-wrap">${dtoStudentRegistration.email}</div></td>
                  </tr>
                  <tr>
                    <th><spring:message code="student.birthdate1"></spring:message></th>
                    <td>:</td><td> ${dtoStudentRegistration.birthDate}</td>
                  </tr>
                   <tr>
                   <th><spring:message code="education.type"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.eduType}</td>
                  </tr>
                   <tr>
                   <th><spring:message code="career"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.career}</td>
                  </tr>
                  <tr>
                   <th><spring:message code="student.grade"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.grades}</td>
                  </tr>
                    <tr>
                    <th><spring:message code="country"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.countryName}</td>
                  </tr>
                  <tr>
                    <th><spring:message code="parent.time"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.timeZoneName}</td>
                  </tr>
                  <tr>
                   <th><spring:message code="student.parentlabel"></spring:message> </th>
                    <td>:</td>
                    <td><div class="word-wrap"> ${dtoStudentRegistration.parentEmail}</div></td>
                  </tr>
                   <tr>
                    <th><spring:message code="parent.password"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.passwordLength}</td>
                  </tr>
                  <tr>
                   <th><spring:message code="student.plan"></spring:message> </th>
                    <td>:</td>
                    <td> ${dtoStudentRegistration.plan} </td>
                    <%-- <td><a href="<%=request.getContextPath()%>/student/changePlan" class="chg-planBtn"><spring:message code="student.changePlaneOrBuy" /></a></td> --%>
                  </tr>
                   <tr>
                   <th colspan="3"><input type="button" value='<spring:message code="edit.info"></spring:message>' class="edit-infoBtn" onclick="openEditPopUp();" ></th>
                  </tr>
                </table>
                </div>
            
            </div>
            
            <div class="ac-activity">
           		<h1 class="text-left"><spring:message code="student.accountactivity"></spring:message> </h1>
                <div class="ac-activity-txt mesg-tab">
                	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
                	<thead>
                      <tr>
                        <th width="60px"><spring:message code="student.date"></spring:message> </th>
                        <th ><spring:message code="student.activity"></spring:message> </th>
                        
                        <c:if test="${currencyName eq 'US'}">
                        <th>US $ </th>
                        </c:if>
                         <c:if test="${currencyName eq 'MXN'}">
                        <th> $ </th>
                        </c:if>
                         <c:if test="${currencyName eq 'EURO'}">
                        <th> &#8364; </th>
                        </c:if>
                        
                        <th><spring:message code="student.minutes"></spring:message> </th>
                        <th><spring:message code="student.mimbalance"></spring:message>  </th>
                        <%-- <th width="90"><spring:message code="student.view"></spring:message></th> --%>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="studentAccountActivities" items="${studentAccountActivities}">
                      <tr>
                        <td><fmt:formatDate  pattern="dd-MM-yy"  value="${studentAccountActivities.activity_Date}" /></td>
                        <td>${studentAccountActivities.activity_Name}</td>
                        <td>${studentAccountActivities.amount}</td>
                        <td>${studentAccountActivities.activity_Minute} Mins</td>
                        <td>${studentAccountActivities.min_Balance} mins</td>
                       <!--  <td><a >See More</a></td> -->
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
</script> 
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/scrolltop/arrow23.js"></script>
<noscript>
<spring:message code="not.seeing"></spring:message> <a href="http://www.scrolltotop.com/"><spring:message code="scroll.topbutton"></spring:message> </a><spring:message code="goto.faqpage"></spring:message>
</noscript> --%>



<div id="editpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeEditPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="editInfoForm" id="editInfoForm" method="post" commandName="dtoStudentRegistration">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  
                   <tr>
                   <th><spring:message code="student.firstname"></spring:message></th>
                    <td>:</td>  
                    <td><div ><form:input name="firstName" id="firstName" path="firstName" type="text"  maxlength="30" minlength="2"/></div></td>
                  </tr>
                  
                        <tr>
                   <th><spring:message code="student.lastname"></spring:message></th>
                    <td>:</td>  
                    <td><div ><form:input name="lastName" id="lastName"  path="lastName" type="text"  maxlength="30" minlength="2"/></div></td>
                  </tr>
                  
                  
                  <tr>
                   <th><spring:message code="student.email"></spring:message></th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder="" path="email" id="email" name="email" maxlength="80"/></div></td>
                  </tr>
                
                  <tr>
                   <th><spring:message code="education.type"></spring:message></th>
                    <td align="right">:</td> 
                    <td> 
                    <div >
                    <form:select id="eduType" name="eduType" path="educationType">
                		<c:forEach var="listEduMaster" items="${listEducationTypeMaster}">
                    	<c:if test="${dtoStudentRegistration.educationType == listEduMaster.education_Type_Id}">
		             <option value='${listEduMaster.education_Type_Id}' selected="selected">${listEduMaster.education_Type}</option>
		             </c:if>
		             <c:if test="${dtoStudentRegistration.educationType ne listEduMaster.education_Type_Id }">
		            <option value='${listEduMaster.education_Type_Id}'>${listEduMaster.education_Type}</option>
		             </c:if>
                    	
                    	</c:forEach>
                    </form:select>
                    </div>
                    </td>
                  </tr>
           
              	 <tr>
                   <th><spring:message code="career"></spring:message></th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder="" path="career" id="career" name="career" maxlength="30"/></div></td>
                  </tr>
                     <tr>
                   <th><spring:message code="student.grade"></spring:message></th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder="" path="grades" id="grades" name="grades" maxlength="30"/></div></td>
                  </tr>
                  
                   <tr>
                   <th> </th>
                    <td></td>  
                    <td>
                    <a id="changePassword" onclick="showPassword();"><spring:message code="change.password"/></a>
                  <%--   <a id="cancelPassword" onclick="hidePassword();"><spring:message code="cancel"/></a> --%>
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
                    <td><div ><input type="password" placeholder=""  id="confirmPassword" name="confirmPassword" maxlength="80"/></div></td>
                  </tr>
                
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitEditForm();">
                     <input type="button" id="cancelPassword" class="greenBtn-normal" value='<spring:message code="cancel"></spring:message>' onclick="hidePassword();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form:form>
        	
        </div>
   </div>

</div>


</body>

<script type="text/javascript">

$("#cancelPassword").hide();
$("#cPassword").hide();
$("#nPassword").hide();
$("#conPassword").hide();

function openEditPopUp()
{
	$("#editpopup").show();
	var start = new Date();
	start.setFullYear(start.getFullYear() - 70);
	var end = new Date();
	end.setFullYear(end.getFullYear());
	$('#birthDate').datepicker({changeYear:true, changeMonth:true, showMonthAfterYear:true,  maxDate: end, yearRange: start.getFullYear() + ':' + end.getFullYear()});
}

function closeEditPopUp(){
	$("label.error").hide();
	$("#editpopup").hide();
}


function submitEditForm(){
	
/* 	var flag=true;
	var parentEmail=$("#parentEmail").val();
	if(parentEmail==""){
		flag=checkAge();
	}
if(flag){ */
	
	$("#editInfoForm").attr("action", "<%=request.getContextPath()%>/student/editStudentInfo");
	
	$("#editInfoForm").submit();
//}
}


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