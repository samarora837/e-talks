<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title><spring:message code="student.register" /></title>
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

<script type="text/javascript">


$(document).ready(function(){
	
	
	$.validator.addMethod("emailId", function(value, element) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');
	
	 $.validator.addMethod("emailIdExist", function(value, element) {
			var flag = emailCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.alreadyuse"></spring:message>'); 
	 
	 $.validator.addMethod('selectcheck', function (value) {
	        return (value != '0');
	    }, '<spring:message code="student.select"></spring:message>');
	 
	/*  $.validator.addMethod("nametype", function(value, element) {
		  	return this.optional(element) || /^[A-Za-záéíñóúüÁÉÍÑÓÚÜ ]{2,30}$/i.test(value);
			},'<spring:message code="student.validname"></spring:message>'); */
	 
	 $.validator.addMethod("sameEmailIdExist", function(value, element) {
			var flag = sameEmailCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.alreadyuse"></spring:message>'); 
	/*  $.validator.addMethod("ageBelow18", function(value, element) {
			var flag = checkAge();
		  	return this.optional(element) || flag;	   
			},"parent email required."); */
	
	$('#studentRegistrationForm').validate({
		
		focusCleanup: true,
		
		rules:{
			parentEmail:{
				//ageBelow18:true,
				
				emailId:true,
				emailIdExist:true,
				sameEmailIdExist: true
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
			},
			country: {
                selectcheck: true,
                required:true
            },
            timeZone: {
                selectcheck: true,
                required:true
            },
			terms:{
				required:true
			}
		
			},

		
		 messages:{
			
			 firstName:{
				required:"<spring:message code='signup.fNamerequired'></spring:message>"
			},
			lastName:{
				required:"<spring:message code='signup.lNamerequired'></spring:message>"
			},
			country:{
				required:"<spring:message code='student.select'></spring:message>"
				},
				timeZone:{
				required:"<spring:message code='student.select'></spring:message>"
				},
			terms:{
				required:"<spring:message code='signup.acceptterms'></spring:message>"
			}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});


function submitFrom(){
	
	var flag=true;
	var parentEmail=$("#parentEmail").val();
	if(parentEmail==""){
		flag=checkAge();
	}
if(flag){

$("#studentRegistrationForm").attr("action", "<%=request.getContextPath()%>/student/saveStudent");
	
	$("#studentRegistrationForm").submit();
}
}

function resetForm(){
	$('#timeZone').children().remove();
	$('#studentRegistrationForm')[0].reset();	
	
}



function sameEmailCheck(){
	
	var parentEmail=$("#parentEmail").val();
	var stuEmail=$("#email").val();
	var flag = false;
	if(parentEmail==stuEmail){
		flag=false;
	}
	else{
		flag=true;
	}
	return flag;
}



function emailCheck(){
	
	var parentEmail=$("#parentEmail").val();
	var flag = false;
	 var url='<%=request.getContextPath()%>/student/parentEmailCheck';
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
}

function checkAge()
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
			   $("#parentEmailError").text("<spring:message code='parent.emailrequired'></spring:message>");
			   
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
	
}


</script>
<script type="text/javascript">
function getTimeZone() {
	
	var country = $("#country").val();
	var url='<%=request.getContextPath()%>/getTimeZone';
	$.ajax({
		dataType: "json",
		contentType: "application/json",
		url:url,
		method:'GET',
		async :false,
		data:{country:country},
		success:function(response){
			
			$('#timeZone').children().remove();
			 $.each( response.modelMap.zoneList, function( key , value ) {
			$('#timeZone').append('<option value="'+key+'">'+value+'</option>');
			 });
			
		}
		
	}); 
	
	
	
}
</script>
<%@ include file="/WEB-INF/views/commonHeaderScript.jsp" %>
</head>
<body>
<div id="main">
<%@ include file="/WEB-INF/views/headerOuter.jsp" %>
    <!--Mid Section-->
    <section class="container">
    	<div class="login-con">
        	<div class="login-hdr"><spring:message code="personal.info"></spring:message></div>
        	
        	<spring:message code="student.firstname" var="fnameP"></spring:message>
        	<spring:message code="student.lastname" var="lnameP"></spring:message>
        	<spring:message code="student.parentemail" var="stuParentEmailP"></spring:message>
        	<spring:message code="student.career" var="careerP"></spring:message>
        	<spring:message code="student.grade" var="gradeP"></spring:message>
        	
        	
        	<form:form name="studentRegistrationForm" id="studentRegistrationForm" commandName="dtoStudentRegistration"  method="post">
        	
            <div class="login-form">
            	<div class="login-form-row"><form:input name="firstName" id="firstName" value="${first_name}" path="firstName" type="text" placeholder="${fnameP}"  maxlength="30" minlength="2"/></div>
                <div class="login-form-row"><form:input name="lastName" id="lastName" value="${last_name}" path="lastName" type="text" placeholder="${lnameP}" maxlength="30" minlength="2"/></div>
                <div class="login-form-row">
                
                
                	<form:select id="eduType" name="eduType" path="educationType">
                	 <c:forEach var="listEducationTypeMaster" items="${listEducationTypeMaster}">
                    	<option value='${listEducationTypeMaster.education_Type_Id}' >${listEducationTypeMaster.education_Type}</option>
                    	</c:forEach>
                    </form:select>
                </div>
                
                <div class="login-form-row"><form:input name="careerType" id="careerType"  path="career" type="text" placeholder="${careerP}"  maxlength="30"/></div>
                <div class="login-form-row"><form:input name="levelType" id="levelType"  path="grades" type="text" placeholder="${gradeP}" maxlength="30"/></div>	
                
                 <%-- <div class="login-form-row">
                	<form:select id="careerType" name="careerType" path="careerType">
                	<c:forEach var="listCareerTypeMaster" items="${listCareerTypeMaster}">
                    	<option value='${listCareerTypeMaster.career_Type_Id}' >${listCareerTypeMaster.career_Type}</option>
                    	</c:forEach>
                    </form:select>
                </div>
                 <div class="login-form-row">
                		<form:select id="levelType" name="levelType" path="level">
                		<c:forEach var="listLevelMaster" items="${listLevelMaster}">
                    	<option value='${listLevelMaster.level_Id}' >${listLevelMaster.level}</option>
                    	</c:forEach>
                    </form:select>
                </div> --%>
                 <div class="login-form-row">
                 	<label><spring:message code="student.birthdate"></spring:message>:</label>
                    <form:input type="text" path="birthDate" class="calander" placeholder="dd-mm-yy" id="birthDate" readonly="readonly" name="birthDate" onchange="checkAge();"/>
                </div>
                <div class="login-form-row"><form:input type="text" name="parentEmail" id="parentEmail" path="parentEmail" placeholder="${stuParentEmailP}" onfocus="clrError();" maxlength="80"/>
                							<label id="parentEmailError" class="error"></label>
                </div>
                 <div class="login-form-row">
                	<%-- <form:select id="country" name="country" path="country">
                	<option value="0">Country of Residence</option>
                	<c:forEach var="listCountryMaster" items="${listCountryMaster}">
                    	<option value='${listCountryMaster.country_Id}' >${listCountryMaster.country_Name}</option>
                    	</c:forEach>
                    </form:select> --%>
                    
              
                    
                     <form:select id="country" name="country" path="country" onchange="getTimeZone();">
                         <option value="0"><spring:message code="select.country"/></option>
                         <optgroup label='<spring:message code="spanishspeakingcountries"/>'>
                	<c:forEach var="listSpanishCountry" items="${listSpanishCountry}">
                    	<option value='${listSpanishCountry.country_Id}'>${listSpanishCountry.country_Name}</option>
                    	</c:forEach>
                    	</optgroup>
                    	<optgroup label='<spring:message code="otherCountries"/>'>
                	<c:forEach var="listOtherCountry" items="${listOtherCountry}">
                    	<option value='${listOtherCountry.country_Id}' >${listOtherCountry.country_Name}</option>
                    	</c:forEach>
                    	</optgroup>
                    </form:select>
                    
                    
                </div>
                 <div class="login-form-row">
                	<form:select id="timeZone" name="timeZone" path="timeZone">
                	 <option value="0"><spring:message code="parent.time"/></option>
                	<%-- <c:forEach var="listTimeZoneMaster" items="${listTimeZoneMaster}">
                    	<option value='${listTimeZoneMaster.timezne_Id}' >${listTimeZoneMaster.timezone_Name}</option>
                    	</c:forEach> --%>
                    </form:select>
                </div>
                <div class="form-row">
                <a href="<%=request.getContextPath()%>/cms/tnc" target="_blank"><u><spring:message code="parent.tnc"></spring:message> </u></a>
                </div>
                 <div class="form-row">
                 <div class="login-form-row">
               		<input type="checkbox" name="terms" id="terms"/>  <spring:message code="parent.accept"></spring:message>
               		
               		</div>
                </div>
               
                <div class="form-row">
                	<a onclick="submitFrom();" class="greenBtn-normal"><spring:message code="parent.submit"></spring:message></a>
                    <a onclick="resetForm();" class="greyBtn-normal"><spring:message code="parent.reset"></spring:message> </a>
                </div>
                <div class="clr"></div>
            </div>
            <form:input type="hidden" path="userId"/>
            <form:input type="hidden" path="email" id="email" name="email"/>
                       
            </form:form>
            <div class="clr"></div>
        </div>
    </section>
    <!--//Mid Section-->
  </div>
   <div class="clr"></div>
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

var start = new Date();
start.setFullYear(start.getFullYear() - 70);
var end = new Date();
end.setFullYear(end.getFullYear());
$('#birthDate').datepicker({changeYear:true, changeMonth:true, showMonthAfterYear:true, dateFormat: 'dd-mm-y',  maxDate: end, yearRange: start.getFullYear() + ':' + end.getFullYear()});


function closePopUp(){
	$("#signupRequired").hide();
}


function sendMsg(){
	 $(window).scrollTop(0);
	 $("#signupRequired").show();
}


</script>

</html>