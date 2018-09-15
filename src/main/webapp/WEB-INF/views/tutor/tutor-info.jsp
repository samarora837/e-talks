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
<title><spring:message code="MiprofeTutorAccount" /></title>
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
<style type="text/css">
.ui-datepicker-calendar {
    display: none;
    }
</style>
<script type="text/javascript">
 $(document).ready(function(){
	
	
 	$.validator.addMethod("emailId", function(value, element) {
 		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
		},"<spring:message code='enter.email' />");
	
	 $.validator.addMethod("emailIdExist", function(value, element) {
		var flag = emailCheck(element);
	  	return this.optional(element) || flag;	   
		},"<spring:message code='email.exist' />"); 
	 
	 $.validator.addMethod('selectcheck', function (value) {
	        return (value != '0');
	    }, "<spring:message code='student.select' />");
	 
	/*  $.validator.addMethod("fnametype", function(value, element) {
		  	return this.optional(element) || /^[A-Za-z ]{2,30}$/i.test(value);
			},"<spring:message code='enteravalidfirstname' />");
	 $.validator.addMethod("lnametype", function(value, element) {
		  	return this.optional(element) || /^[A-Za-z ]{2,30}$/i.test(value);
			},"<spring:message code='enteravalidlastname' />"); */
	 
	 $.validator.addMethod("phonenumbertype", function(value, element) {
		  	return this.optional(element) || /^[+0-9]{2,30}$/i.test(value);
			},"<spring:message code='enteravalidmobilenumber' />");
	 
	 $.validator.addMethod("passwordExist", function(value, element) {
			var flag = passwordCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="current.passwordnotcorrect"></spring:message>'); 
	 
	$('#tutorEditForm').validate({
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
			name:{
				required:true
				//fnametype:true
			},
			lname:{
				required:true
				//lnametype:true
			},
			college: {
				required:true,
				minlength : 2,
				maxlength : 40
            },
            graduationDate: {
            	required:true
            },
            timeZone: {
                selectcheck: true
            },
            mobileNumber: {
            	required:true,
            	phonenumbertype:true,
    			minlength : 10,
    			maxlength : 14
            },
            taxId: {
            	required:true,
            	minlength : 2,
    			maxlength : 10
            },
			country: {
	                selectcheck: true
	            }
			
			},

		
		 messages:{
			 /* email:{
					required:"email is required"
			}, */
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
			 name:{
				required:"<spring:message code='parent.fnameRequired' />"
			},
			lname:{
				required:"<spring:message code='parent.lnameRequired' />"
			},
			college:{
				required:"<spring:message code='collegeisrequired' />",
				minlength:"<spring:message code='atleast2charactersarerequired' />",
				maxlength:"<spring:message code='only40charactersareallowed' />"
				},
			country:{
				required:"<spring:message code='selectatleastvalue' />"
				},
			timezone:{
				required:"<spring:message code='selectatleastvalue' />"
				},
			taxId:{
				required:"<spring:message code='taxIdisrequired' />",
				minlength:"<spring:message code='atleast10digitrequired' />",
				maxlength:"<spring:message code='mobilenumberistoolong' />"
			},
			mobileNumber:{
				required:"<spring:message code='mobilenumberisrequired' />",
				minlength:"<spring:message code='atleast10digitrequired' />",
				maxlength:"<spring:message code='mobilenumberistoolong' />"
			},
			graduationDate:{
				required:"<spring:message code='dateisrequired' />"
			}
			
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});
	 
	

</script>
<script type="text/javascript">

function emailCheck(){
	
	var email=$("#email").val();
	
	var tutorEmail='${dtoTutorDetails.email}';
	
	
	var flag = false;
	if(email==tutorEmail){
		flag=true;
	}
	else{
	var url='<%=request.getContextPath()%>/tutor/tutorEmailCheck';
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
	 var url='<%=request.getContextPath()%>/tutor/passwordCheck';
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
<%@ include file="/WEB-INF/views/tutor/headerTutor.jsp" %>
  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="tutor-info-outr">
          <div class="my-info-tutor">
            <h1 class="text-left"><spring:message code="my.info" /></h1>
            
            
            <div class="my-info-tutor-txt"> <a class="edit-icon" title="Edit"  onclick="openEditPopUp();"><spring:message code="parent.editInfo" /></a>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <th width="130"><spring:message code="parent.name" /></th>
                  <td>: ${dtoTutorDetails.name } ${dtoTutorDetails.lname }</td>
                </tr>
                <tr>
                  <th><spring:message code="parent.email" /></th>
                  <td>: ${dtoTutorDetails.email }</td>
                </tr>
                <tr>
                  <th><spring:message code="college" /></th>
                  <td>: ${dtoTutorDetails.college }</td>
                </tr>
                <tr>
                  <th><spring:message code="graduationDate" /></th>
                  <td>: ${dtoTutorDetails.graduationDate }</td>
                </tr>
               <%--  <tr>
                  <th><spring:message code="parent.time" /></th>
                  <td>: ${dtoTutorDetails.timezoneName }</td>
                </tr> --%>
                <tr>
                  <th><spring:message code="parent.password" /></th>
                  <td>: ${dtoTutorDetails.passwordLength }</td>
                </tr>
                <tr>
                  <th><spring:message code="mobile" /></th>
                  <td>: ${dtoTutorDetails.mobileNumber }</td>
                </tr>
                <tr>
                  <th><spring:message code="taxID" /></th>
                  <td>: ${dtoTutorDetails.taxId }</td>
                </tr>
                
                <tr>
                  <th><spring:message code="streetName" /></th>
                  <td>: ${dtoTutorDetails.street }</td>
                </tr>
                <tr>
                  <th><spring:message code="city" /></th>
                  <td>: ${dtoTutorDetails.city }</td>
                </tr>
                <tr>
                  <th><spring:message code="country" /></th>
                  <td>: ${dtoTutorDetails.countryName }</td>
                </tr>
                <tr>
                  <th><spring:message code="parent.time" /></th>
                  <td>: ${dtoTutorDetails.timezoneName }</td>
                </tr>
                <tr>
                  <th><spring:message code="gpa" /></th>
                  <td>: ${dtoTutorDetails.gpa }</td>
                </tr>
                <tr>
                  <th><spring:message code="gpaScaleName" /></th>
                  <td>: ${dtoTutorDetails.gpaScale }</td>
                </tr>
                
               <%--               
                <tr>
                  <th><spring:message code="depositon" /></th>
                  <td>: 15th of each month in Paypal Account (static)</td>
                </tr> --%>
              </table>
              <div class="img">
              <c:if test="${imageName !=null}">
              	<img alt="" src="<%=request.getContextPath()%>/profilePictures/${userId}/fileupload/${userId}${imageName}"/>
              	</c:if>
              	<c:if test="${imageName ==null && imageName1 ==null}">
              	<img src="<%=request.getContextPath()%>/images/default-img.png" alt="1">
              	</c:if>
              	
              	
              	<c:if test="${imageName1 !=null && imageName ==null}">
              	<img alt="" src="${imageName1}"/>
              	</c:if>
              </div>
              <div class="edit-icon" style="position: inherit;cursor:pointer;" title="Edit Photo" onclick="openPhotoPopup();"></div>
            </div>
          </div>
 
  <div class="subj-i-teach">
            <h1 class="text-left"><spring:message code="subjectsITeach" /></h1>
            <div class="subj-i-teach-txt"> <a class="edit-icon" title="Edit" onclick="openSubjectPopup();"><spring:message code="parent.editInfo" /></a>
              <div class="accordian-subjects">
                <c:forEach items="${activeProgramList}" var="activeProgramList" >
                 <section>
                 <h2 class="actog2"> <span class="num"></span> ${activeProgramList.key} </h2>
                    <div class="accon2">
                 <c:forEach items="${activeProgramList.value}" var="activeProgramListVal" >
                      <ul>
                      <li><input type="checkbox" disabled="disabled"  checked="true" name="abc1"/> ${activeProgramListVal}</li>
                      </ul>
                      <div class="clr"></div>

                    </c:forEach></div>
                </section>
				</c:forEach> 
			         </div></div></div>
          
          <div class="ac-activity ac-activity-2">
            <h1 class="text-left"><spring:message code="myExperience" /></h1>
            <div class="ac-activity-txt tutor-exp-txt"> 
            <a title="Edit" class="edit-icon" onclick="openpopupExperience();"><spring:message code="parent.editInfo" /></a>
            <p> ${dtoTutorDetails.about } </p>
            
            </div>
          </div>
          <div class="ac-activity ac-activity-2">
            <h1 class="text-left"><spring:message code="iLike" /></h1>
            <div class="ac-activity-txt tutor-exp-txt">
            	<a title="Edit" class="edit-icon" onclick="openpopupMoreExperience();"><spring:message code="parent.editInfo" /></a>
           <p> ${dtoTutorDetails.aboutMore } </p>
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

<div id="editpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeEditPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="tutorEditForm" id="tutorEditForm" method="post"  commandName="dtoTutorDetails" >
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="editpopupExperience">
                  <tbody>
                  
                   <tr>
                    <td class="edit-exp-hdr"><spring:message code="my.info" /></td>
                  </tr>
                  
                  <%-- <tr>
                    <th><spring:message code="student.firstname" /></th>
                    <td>:</td> 
                    <td><div ><form:input type="text" placeholder="" path="name" id="firstName" name="name" maxlength="30"/></div></td>
                  </tr>
                  <tr>
                    <th><spring:message code="student.lastname" /></th>
                    <td>:</td> 
                    <td><div ><form:input type="text" placeholder="" path="lname" id="lastName" name="lname" maxlength="30"/></div></td>
                  </tr>
                  <tr>
                  <tr>
                   <th><spring:message code="student.email" /></th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder=""  path="email" id="email" name="email" maxlength="80"/></div></td>
                  </tr>
                    <th><spring:message code="college" /></th>
                    <td>:</td> 
                    <td><div ><form:input type="text" placeholder="" path="college" id="college" name="college" maxlength="40"/></div></td>
                  </tr>
                  <tr>
                    <th><spring:message code="graduationDate" /></th>
                    <td>:</td> 
                    <td>
                   <div > <form:input type="text" class="date-input" placeholder="2/8/2015" maxlength="11" path="graduationDate" readonly="true" id="graduationDate" name="graduationDate" />
                   </div>
                    </td>
                  </tr> --%>
                  <%-- <tr>
                    <th><spring:message code="parent.time" /></th>
                    <td>:</td> 
                    <td>
                    <div >
                    <form:select id="timeZone" name="timeZone" path="timeZone">
                    <option value="0"><spring:message code="selecttimezone" /></option>
                		<c:forEach var="listTimeZoneMaster" items="${listTimeZoneMaster}">
                		
                    	<c:if test="${dtoTutorDetails.timeZone == listTimeZoneMaster.timezne_Id}">
		             <option value='${listTimeZoneMaster.timezne_Id}' selected="selected">${listTimeZoneMaster.timezone_Name}</option>
		             </c:if>
		             <c:if test="${dtoTutorDetails.timeZone ne listTimeZoneMaster.timezne_Id}">
		           <option value='${listTimeZoneMaster.timezne_Id}' selected="selected">${listTimeZoneMaster.timezone_Name}</option>
		             </c:if>
                    	
                    	</c:forEach>
                    </form:select>
                    </div>
                  </tr> --%>
               <%--    <tr>
                   <th><spring:message code="login.password" /></th>
                    <td>:</td>  
                    <td><div ><form:input type="password" placeholder="" path="password" id="password" name="password" maxlength="30"/></div></td>
                  </tr> --%>
                  <tr>
                   <th><spring:message code="mobile" /></th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder="" path="mobileNumber" id="mobile" name="mobile" maxlength="15"/></div></td>
                  </tr>
               <%--    <tr>
                   <th><spring:message code="taxID" /></th>
                    <td>:</td>  
                    <td><div ><form:input type="text" placeholder="" path="taxId" id="taxId" name="taxId" maxlength="10"/></div></td>
                  </tr> --%>
           <%--        <tr>
                   <th><spring:message code="depositon" /></th>
                    <td>:</td>  
                    <td><div ><input type="text" placeholder=""  id="deposit" name="deposit" maxlength="10"/></div></td>
                  </tr> --%>
                  
                           <tr>
                   <th> </th>
                    <td></td>  
                    <td>
                    <a id="changePassword" onclick="showPassword();"><spring:message code="change.password"/></a>
                    <%-- <a id="cancelPassword" onclick="hidePassword();"><spring:message code="cancel"/></a> --%>
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
                  
                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitInfoForm();">
                     <input id="cancelPassword" type="button" class="greenBtn-normal" value='<spring:message code="cancel"></spring:message>' onclick="hidePassword();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form:form>
		        </div>
		   </div>
		</div>
		 
		<spring:message code="tellsomething.aboutyourself" var="tellsomething"></spring:message>
		
		<div id="editpopupExperience" style="display:none;">
		 <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closepopupExperience();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <section>
            	 <form:form name="tutorExperienceForm" id="tutorExperienceForm" method="post"  commandName="dtoTutorDetails" >
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="editpopupExperience">
                  <tbody>
                  <tr>
                    <td class="edit-exp-hdr"><spring:message code="myExperience" /> </td>
                  </tr>
                  <tr>
                    <td>
                    <form:textarea path="about" id="about" name="about" placeholder="${tellsomething}" rows="7" cols="50" class="txtarea-popup" />
                    </td>
                  </tr>
                  <tr>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitExperienceForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form:form>
            </section>
        </div>
        </div>
        </div>
</div>			
		<spring:message code="tellusmore.aboutyourself" var="tellusmore"></spring:message>
		<div id="editpopupMoreExperience" style="display:none;">
		 <div class="form-tutor-popup">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closepopupMoreExperience();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            <section>
            	<form:form name="tutorLikeForm" id="tutorLikeForm" method="post"  commandName="dtoTutorDetails" >
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%" class="editpopupExperience">
                  <tbody>
                  <tr>
                    <td class="edit-exp-hdr"><spring:message code="iLike" /> </td>
                  </tr>
                  <tr>
                    <td>
                    <form:textarea path="aboutMore" id="aboutMore" name="aboutMore" placeholder="${tellusmore}" rows="7" cols="50" class="txtarea-popup"/>
                    </td>
                  </tr>
                  <tr>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitLikeForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form:form>
            </section>
        </div>
        </div>
        </div>
</div>			
		
<div id="editSubjectPopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeSubjectPopup();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form:form name="tutorSubjectForm" id="tutorSubjectForm" method="post"  commandName="dtoTutorDetails" >
           <div class="my-info-popup">
           <div class="form-tutor-con" id="form-tutor-con2">
                <!-- <h1>Which subjects do you want to teach</h1> -->
                
                <div align="left" class="edit-exp-hdr"><spring:message code="whichsubjectsdoyouwanttoteach" /></div>
			
			 <div class="accordian-subjects" style="text-align: left; padding: 8px 2px 15px 2px;">
			
			   <c:forEach items="${allSubjectWithTypes}" var="allSubjectWithTypes" >
                 <section>
                 <h2 class="actog2"> <span class="num"></span> ${allSubjectWithTypes.key} </h2>
                    <div class="accon2">
                 <c:forEach items="${allSubjectWithTypes.value}" var="activeProgramListVal" >
                  <c:if test="${activeProgramListVal.value.selectedValue eq true}">
                    
                      <ul>
                      <li><form:checkbox path="subjectId" checked="true" name="abc1" value="${activeProgramListVal.key}"></form:checkbox> ${activeProgramListVal.value.subjectName}</li>
                      </ul>
                      <div class="clr"></div>
                    
                     </c:if> 
                      <c:if test="${activeProgramListVal.value.selectedValue eq false}">
                    
                      <ul>
                      <li><form:checkbox path="subjectId" name="abc1" value="${activeProgramListVal.key}"></form:checkbox> ${activeProgramListVal.value.subjectName}</li>
                      </ul>
                      <div class="clr"></div>
                    
                     </c:if> 
                    </c:forEach></div>
                </section>
				</c:forEach> 
			
			</div>
			
			 </div>
                      <ul>
                      <li><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitSubjectForm();"></li>
                      </ul>			 
                </div>
                </form:form>
		        </div>
		   </div>
		</div>		
		
		
		
		<!-- Update photo popup start -->
<div id="editPhotoPopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a href="" onclick="closePhotoPopup();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
           <form:form name="tutorEditImageForm" id="tutorEditImageForm" method="post"  commandName="dtoTutorDetails" action="updateImage" enctype="multipart/form-data">
           <div class="upload-img-con">
          <div class="upload-img-outr">
                            	<div class="upload-img">
                            	<c:if test="${imageName !=null}">
                            	<img alt="" id="profilePic" name="profilePic" src="<%=request.getContextPath()%>/profilePictures/${userId}/fileupload/${userId}${dtoTutorDetails.imageName}" />
                            	</c:if> 
                            	<c:if test="${imageName ==null && imageName1 ==null}">
                            	<img alt="" id="profilePic" name="profilePic" src="<%=request.getContextPath()%>/images/default-img.png"/>
                            	</c:if>
                            	<c:if test="${imageName1 !=null && imageName ==null}">
                            	<img alt="" id="profilePic" name="profilePic" src="${imageName1}"/>
                            	</c:if> 
                           	 </div>
                           	
                          </div>
                           <form:input path="uploadFile" type="file" class="inputFile" onchange="readURL(this);"></form:input>
                         
                     </div>
                      <input type="submit" class="greenBtn-normal img-upload" value='<spring:message code="upload"></spring:message>'>
                          
          </form:form>
          
		</div>
  </div>
</div>	
		
		<!-- Update photo popup end -->	

</body>

<script type="text/javascript">


function closeEditPopUp(){
	$("label.error").hide();
	$("#editpopup").hide();
}

function openpopupExperience(){
	$(window).scrollTop(0);
$("#editpopupExperience").show();
}
function closepopupExperience(){
	$("#editpopupExperience").hide();
	}
function openpopupMoreExperience(){
	$(window).scrollTop(0);
	$("#aboutMore").focus();
	$("#editpopupMoreExperience").show();
	}
	function closepopupMoreExperience(){
		$("#editpopupMoreExperience").hide();
		}	
	
function openSubjectPopup(){
	$("#editSubjectPopup").show();
	}
function closeSubjectPopup(){
	$("#editSubjectPopup").hide();
	}		
	
function openPhotoPopup(){
	$("#editPhotoPopup").show();
	}	
function closephotoPopup(){
	$("#editPhotoPopup").hide();
	}


function submitInfoForm(){
	$("#tutorEditForm").attr("action", "<%=request.getContextPath()%>/tutor/updateTutorPersonalInfo");
    $("#tutorEditForm").submit();
}

function submitExperienceForm(){
	$("#tutorExperienceForm").attr("action", "<%=request.getContextPath()%>/tutor/updateTutorExperienceInfo");
    $("#tutorExperienceForm").submit();
}

function submitLikeForm(){
	$("#tutorLikeForm").attr("action", "<%=request.getContextPath()%>/tutor/updateTutorLikeInfo");
    $("#tutorLikeForm").submit();
}

function submitSubjectForm(){
	$("#tutorSubjectForm").attr("action", "<%=request.getContextPath()%>/tutor/updateTutorSubjectInfo");
    $("#tutorSubjectForm").submit();
}



</script>



<script type="text/javascript">
function UpdateTableHeaders() {
    $(".accordian-subjects div section").each(function () {

        var el = $(this),
            offset = el.offset(),
            scrollTop = $(window).scrollTop(),
            floatingHeader = $(".floatingHeader", this)

            if ((scrollTop > offset.top) && (scrollTop < offset.top + el.height())) {
                floatingHeader.css({
                    "visibility": "visible"
                });
            } else {
                floatingHeader.css({
                    "visibility": "hidden"
                });
            };
    });
}

// DOM Ready      
$(function () {

    var clonedHeaderRow;

    $(".accordian-subjects div section").each(function () {
        clonedHeaderRow = $(".actog2", this);
        clonedHeaderRow.before(clonedHeaderRow.clone()).css("width", clonedHeaderRow.width()).addClass("floatingHeader");

    });

    $(window).scroll(UpdateTableHeaders).trigger("scroll");

});

jQuery(document).ready(function () {
    jQuery(".actog2").next(".accon2").hide();
    jQuery(".actog2").click(function () {
        $('.active').not(this).toggleClass('active').siblings('.accon2').slideToggle(500);
        $(this).toggleClass('active').siblings('.accon2').slideToggle(400);
    });
});
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
 
});
</script> 
<%-- <script type="text/javascript" src="js/scrolltop/arrow23.js"></script>
<noscript>
<spring:message code="not.seeing"></spring:message> <a href="http://www.scrolltotop.com/"><spring:message code="scroll.topbutton"></spring:message> </a><spring:message code="goto.faqpage"></spring:message>
</noscript> --%>
<script type="text/javascript">
jQuery(document).ready(function () {
	
	$("#cancelPassword").hide();
	$("#cPassword").hide();
	$("#nPassword").hide();
	$("#conPassword").hide();
	
    jQuery(".actog").next(".accon").hide();
    jQuery(".actog").click(function () {
        $('.active').not(this).toggleClass('active').siblings('.accon').slideToggle(500);
        $(this).toggleClass('active').siblings('.accon').slideToggle(400);
    });
});
</script>
<script type="text/javascript">
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#profilePic')
                .attr('src', e.target.result)
                .width(150)
                .height(150);
        };

        reader.readAsDataURL(input.files[0]);
    }
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


function openEditPopUp()
{
	$("#editpopup").show();
	
	
			$('#graduationDate').datepicker({
			    changeMonth: true,
			    changeYear: true,
			    showButtonPanel: true,
			    dateFormat: 'MM yy',
			    yearRange: '1950:2050',
			    onClose: function(dateText, inst) { 
			        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
			        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
			        $(this).datepicker('setDate', new Date(year, month, 1));
			    }});
	 
	 }


</script>
</html>
