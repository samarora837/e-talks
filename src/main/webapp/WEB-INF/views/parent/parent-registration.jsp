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

<title>MiProfe: <spring:message code="parent.registration"/></title>
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

<script type="text/javascript">
$(document).ready(function(){
	
	
 	$.validator.addMethod("emailId", function(value, element) {
 		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');
	
	 $.validator.addMethod("emailIdExist", function(value, element) {
		var flag = emailCheck(element);
	  	return this.optional(element) || flag;	   
		},'<spring:message code="email.exist"></spring:message>'); 
	 
	 $.validator.addMethod('selectcheck', function (value) {
	        return (value != '0');
	    }, '<spring:message code="student.select"></spring:message>');
	 
	 $.validator.addMethod("nametype", function(value, element) {
		  	return this.optional(element) || /^[]{2,30}$/i.test(value);
			},'<spring:message code="student.validname"></spring:message>');
/* 	 
	 $.validator.addMethod("sameEmailIdExist", function(value, element) {
			var flag = sameEmailCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.exist"></spring:message>'); 
	  */
	 
	 
	$('#registerForm').validate({
		rules:{
			
			student1:{
				required:true,
				emailId:true
				//emailIdExist:true
				
			}, 
			
			name:{
				required:true
				//nametype:true
			},
			lname:{
				required:true
				//nametype:true
			},
			country: {
	                selectcheck: true
	            },
	        timezone: {
	                selectcheck: true
	            },
	            terms:{
	    			required:true
	    		}
			
			},

		
			messages:{
				 student1:{
						required:'<spring:message code="parent.emailRequired" />'
					},
				 name:{
					required:'<spring:message code="parent.fnameRequired" />'
				},
				lname:{
					required:'<spring:message code="parent.lnameRequired" />'
				},
				country:{
					required:'<spring:message code="parent.select" />'
					},
				timezone:{
					required:'<spring:message code="parent.select" />'
					},
					terms:{
						required:'<spring:message code="parent.acceptTerm" />'
					}
				
			},
		submitHandler:function(form){
			if($("#checkChildId").val()=='1'){
    		form.submit();
			}
			else{
				
				var idval = $("#errorFieldId").val();
				$("#childEmailError"+idval).removeAttr( 'style' );
				 $("#childEmailError"+idval).text("<spring:message code='email.alreadytaken'></spring:message>");
				return false;
			}
    	}
	});
	

});
	
function sameEmailCheck(stuId){
	
	var studentId=stuId.id;
    var Idnumber = studentId.substring(7);
	
	var userEmail=$("#userEmail").val();
	var stuEmail=$("#"+studentId).val();
	
	var flag = false;
	if(userEmail==stuEmail){
		 $("#childEmailError"+Idnumber).text("<spring:message code='email.alreadytaken'></spring:message>");
		 $("#checkChildId").val("0");
		 $("#errorFieldId").val(Idnumber);
		flag=false;
	}
	else{
		 $("#childEmailError"+Idnumber).text("");
		 $("#checkChildId").val("1");
		flag=true;
	}
	
}

/* function checkSameUserAsChild(){
	
	var studentId=stuId.id;
    var Idnumber = studentId.substring(7);
	
	var userEmail=$("#userEmail").val();
	var stuEmail=$("#"+studentId).val();
	
	var flag = false;
	if(userEmail==stuEmail){
		 $("#childEmailError"+Idnumber).text("<spring:message code='email.alreadytaken'></spring:message>");
		flag=false;
	}
	else{
		 $("#childEmailError"+Idnumber).text("");
		flag=true;
	}
	
} */

function clrError(stuId)
{
	var studentId=stuId.id;
    var Idnumber = studentId.substring(7);
	$("#childEmailError"+Idnumber).text("");
	
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
			$('#timezone').children().remove();
			 $.each( response.modelMap.zoneList, function( key , value ) {
			$('#timezone').append('<option value="'+key+'">'+value+'</option>');
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
  
  
  <c:if test="${showPopup eq 'true' }">
   <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <div class="success-img"><img src="<%=request.getContextPath()%>/images/success-icon.png" alt=""></div>
            <section>
            	<spring:message code="thankYouforApplyingtoMiProfecom"/>
            </section>
            <%-- <p><spring:message code="wehavesentyourrequesttoAdminforapproval" /></p> --%>
        </div>
        </div>
        </div>
   </c:if> 
  
    <!--Mid Section-->
    <form name="registerForm" id="registerForm" method="post" action="<%=request.getContextPath()%>/parent/saveParentInfo">
    <section class="container">
    	<div class="login-con">
        	<div class="login-hdr"><spring:message code="parent.yourInfo" /></div>
            <div class="login-form">
            	<div class="login-form-row"><input type="text" placeholder='<spring:message code="parent.firstname" />' value="${first_name}" name="name" id="name" maxlength="30" minlength="2"></div>
                <div class="login-form-row"><input type="text" placeholder='<spring:message code="parent.lastname" />' value="${last_name}" name="lname" id="lname" maxlength="30" minlength="2"></div>
                
                <div class="studentRecord">
                 <div class="login-form-row">
                 <input type="text" placeholder='<spring:message code="parent.childEmail" />'  name="student1" id="student1" maxlength="80" onfocus="clrError(this);" onblur="sameEmailCheck(this);">
                 <label id="childEmailError1" class="error"></label>
                 
                 </div>
                </div>
                
              <div class="form-row">
                 <a href="#" class="add-another" onclick="addStudent()"><spring:message code="parent.addStudent" /></a>
                 </div>
                 
                <div class="login-form-row">
               <%--  <select id="country" name="country">
                <option value="0"><spring:message code="parent.country" /></option>
                <c:forEach items="${countryList}" var="countryList">
                <option value="${countryList.country_Id}">${countryList.country_Name}</option>
                </c:forEach>
                </select> --%>
                
                <select id="country" name="country" onchange="getTimeZone();">
                         <option value="0"><spring:message code="select.country"/> </option>
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
                    </select>
                
                
                
                </div>
                
                <div class="login-form-row">
                <select id="timezone" name="timezone">
                <option value="0"> <spring:message code="parent.time" /></option>
               <%--  <c:forEach items="${timezone}" var="timezone">
                <option value="${timezone.timezne_Id}">${timezone.timezone_Name}</option>
                </c:forEach> --%>
                </select>
                </div>
                
                <div class="form-row">
                <a href="<%=request.getContextPath()%>/cms/tnc" target="_blank"><u><spring:message code="parent.tnc" /></u></a>
                </div>
                
                <div class="self1-intro" style="text-align: center;">
            	<label class="terms" ><input type="checkbox" name="terms" id="terms"> <spring:message code="parent.accept" /></label>
            </div>
               
                <div class="form-row">
                	<a href="#" class="greenBtn-normal" onclick="submitForm();"><spring:message code="parent.submit" /></a>
                    <a href="" onclick="resetForm();" class="greyBtn-normal"><spring:message code="parent.reset" /></a>
                </div>
                <div class="clr"></div>
            </div>
                <input type="hidden" readonly="readonly" name="numberofStudents" id="numberofStudents" value="1"/>
                <input type="hidden" readonly="readonly" name="userId" id="userId" value="${userId}">
                 <input type="hidden" readonly="readonly" name="userEmail" id="userEmail" value="${userEmail}">
                 <input type="hidden" readonly="readonly" id="checkChildId" name="checkChildId" value="1"/> 
                 <input type="hidden" readonly="readonly" id="errorFieldId" name="errorFieldId" value="1"/> 
                 
            <div class="clr"></div>
        </div>
    </section>

    </form>
    <!--//Mid Section-->
  </div>
   <div class="clr"></div>
    <%@ include file="/WEB-INF/views/footerOuter.jsp" %>
</body>

<script type="text/javascript">





function submitForm(){
	$("#registerForm").submit();
		
}


var numberofStudents = 1;
function addStudent(){
	
	if($("#checkChildId").val()==1)
	{
	if(($("#student"+numberofStudents).val())==""){
		$("#registerForm").validate().element("#student"+numberofStudents);
		return false;
	}
	numberofStudents++;
	$( ".studentRecord" ).append( '<div class="login-form-row " id="dynamicRecord'+numberofStudents+'"> <input type="text"  placeholder="Your '+ numberofStudents+' Child Email"  id="student'+numberofStudents+'" maxlength="80" class="student'+numberofStudents+'" name="student'+numberofStudents+'" onfocus="clrError(this);" onblur="sameEmailCheck(this);" > <label id="childEmailError'+numberofStudents+'" class="error"></label></div>' ).find('input[name="student'+numberofStudents+'"]').rules('add', {
	      required: true,
	      emailId:true,
			emailIdExist:true,
	      messages: {
	        required: '<spring:message code="parent.emailRequired" />'
	        
	      }
	    }
	); 
	$("#numberofStudents").val(numberofStudents);
	
	if(numberofStudents>"2"){
		var num = numberofStudents - 1;
		$("#dynamicRecord"+num).children("a:first").remove();
		
	}
		$("#dynamicRecord"+numberofStudents).append('<a onclick="removeField(numberofStudents);" class="remove-field"  ></a>');
}
}

function removeField(nmOfMails){
	$("#student"+nmOfMails).parent('div').remove();
	numberofStudents--;
	$("#numberofStudents").val(numberofStudents);
	if(numberofStudents>"1"){
	$("#dynamicRecord"+numberofStudents).append('<a onclick="removeField(numberofStudents);" class="remove-field"  ></a>');
	}
}
	


function emailCheck(val){
	var studentId = val.id;
	var email=$("#"+studentId).val();
//	var parentId = '${userId}';
	var flag = false;
 	 var url='<%=request.getContextPath()%>/parent/verifyStudentEmail';
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
	return flag;
}

function resetForm(){
	$('#timezone').children().remove();
	$('#registerForm')[0].reset();
}

$(document).ready(function(){
var userstatus = '${createFirebaseUser}';
if(userstatus=="Y"){
	
	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
	      //var authData=myDataRef.getAuth();
	      
	      currentUser= myDataRef.createUser({
	      email    : '${createUser}',
	      password : '${createPass}'
	    }, function(error, userData) {
	      if (error) {
	        // console.log("Error creating user:", error);
	      } else {
	        // console.log("Successfully created user account with uid:", userData.uid);
	      }
	    });   
}
});

</script>


</html>
