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
<title><spring:message code="tutor.signUp" /></title>
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

<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/scrolltop/arrow23.js"></script> --%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>


<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/datepicker/jquery-ui.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/ddsmoothmenu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/datepicker/styleDate.css">

<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

<style type="text/css">
.ui-datepicker-calendar {
    display: none;
    }
</style>

<script type="text/javascript">
$(document).ready(function() {
	
$("#form-tutor-con1").show();
$("#form-tutor-con2").hide();
$("#form-tutor-con3").hide();

$.validator.addMethod("emailId", function(value, element) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
	},"<spring:message code='enter.email'/>");

 $.validator.addMethod("emailIdExist", function(value, element) {
	var flag = emailCheck(value);
  	return this.optional(element) || flag;	   
	},"<spring:message code='email.exist'/>"); 
 
 $.validator.addMethod('selectcheck', function (value) {
        return (value != '0');
    }, "<spring:message code='parent.select'/>");
 
/*  $.validator.addMethod("nametype", function(value, element) {
	  	return this.optional(element) || /^[A-Za-z ]{2,30}$/i.test(value);
		},"<spring:message code='student.validname'/>"); */
 
 $.validator.addMethod("phonenumbertype", function(value, element) {
	  	return this.optional(element) || /^[+0-9]{2,30}$/i.test(value);
		},"<spring:message code='enteravalidmobilenumber'/>");
 
$('#registration').validate({
	rules:{
		email:{
			required:true,
			emailId:true,
			emailIdExist:true
		},
		password:{
			required:true,
			minlength : 8,
			maxlength : 20
		},
		confirmPass:{
			required:true,
			equalTo: "#password"
		},
		mobileNumber:{
			required:true,
			phonenumbertype:true,
			minlength : 10,
			maxlength : 14
		},
		country:{
			selectcheck: true
		},
		taxId:{
			required:true,
			minlength : 2,
			maxlength : 10
		},
		name:{
			required:true
			//nametype:true
		},
		lname:{
			required:true
			//nametype:true
		},
		college:{
			required:true,
			minlength : 2,
			maxlength : 40
		},
		career:{
			required:true,
			minlength : 2,
			maxlength : 40
		},
		graduationDate:{
			required:true
		},
		timeZone:{
			selectcheck: true
		},
		terms:{
			required:true
		},
		street:{
			required:true
		},
		city:{
			required:true
		},
		gpa:{
			required:true
		},
		gpaScale:{
			required:true
		}
	
	},
	 messages:{
		 	email:{
				required:"<spring:message code='email.required'/>"
			},
			password:{
				required:"<spring:message code='password.required'/>",
				minlength:"<spring:message code='parent.atleast8Required'/>",
				maxlength:"<spring:message code='passwordistoolong'/>"
			},
			confirmPass:{
				required:"<spring:message code='confirm.passwordrequired'/>",
				equalTo:"<spring:message code='password.notmatch'/>"
			},
			name:{
				required:"<spring:message code='signup.fNamerequired'/>"
			},
			lname:{
				required:"<spring:message code='signup.lNamerequired'/>"
			},
			taxId:{
				required:"<spring:message code='taxIdisrequired'/>",
				minlength:"<spring:message code='atleast2digitrequired'/>",
				maxlength:"<spring:message code='only10charactersareallowed'/>"
			},
			mobileNumber:{
				required:"<spring:message code='mobilenumberisrequired'/>",
				minlength:"<spring:message code='atleast10digitrequired'/>",
				maxlength:"<spring:message code='mobilenumberistoolong'/>"
			},
			country:{
				required:"<spring:message code='selectatleastvalue'/>"
			},
			timeZone:{
				required:"<spring:message code='selectatleastvalue'/>"
			},
			college:{
				required:"<spring:message code='collegeisrequired'/>",
				minlength:"<spring:message code='atleast2charactersarerequired'/>",
				maxlength:"<spring:message code='only40charactersareallowed'/>"
			},
			career:{
				required:"<spring:message code='careerisrequired'/>",
				minlength:"<spring:message code='atleast2charactersarerequired'/>",
				maxlength:"<spring:message code='only40charactersareallowed'/>"
			},
			graduationDate:{
				required:"<spring:message code='dateisrequired'/>"
			},
			terms:{
				required:"<spring:message code='signup.acceptterms'/>"
			},
			street:{
				required:"<spring:message code='streetrequired'/>"
			},
			city:{
				required:"<spring:message code='cityrequired'/>"
			},
			gpa:{
				required:"<spring:message code='gparequired'/>"
			},
			gpaScale:{
				required:"<spring:message code='gpascalerequired'/>"
			}
	}, 
	submitHandler:function(form){
		$("#tutorSubmit").prop('disabled', true);
		$("#tutorBack").prop('disabled', true);
		
		form.submit();
		
		
	}
});

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

<script type="text/javascript">
function UpdateTableHeaders() {
    $(".main div section").each(function () {

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

    $(".main div section").each(function () {
        clonedHeaderRow = $(".actog", this);
        clonedHeaderRow.before(clonedHeaderRow.clone()).css("width", clonedHeaderRow.width()).addClass("floatingHeader");

    });

    $(window).scroll(UpdateTableHeaders).trigger("scroll");

});

jQuery(document).ready(function () {
    jQuery(".actog").next(".accon").hide();
    jQuery(".actog").click(function () {
        $('.active').not(this).toggleClass('active').siblings('.accon').slideToggle(500);
        $(this).toggleClass('active').siblings('.accon').slideToggle(400);
    });
    
    var userstatus = '${createFirebaseUser}';
    if(userstatus=="Y"){
    	      var myDataRef = new Firebase('https://aloprofe.firebaseio.com');
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

function next() {
	var email = $("#registration").validate().element("#email");
	var password = $("#registration").validate().element("#password");
	var confirmPass = $("#registration").validate().element("#confirmPass");
	var mobileNumber = $("#registration").validate().element("#mobileNumber");
	var country = $("#registration").validate().element("#country");
	var taxId = $("#registration").validate().element("#taxId");

	var name1 = $("#registration").validate().element("#name");
	
	var lname = $("#registration").validate().element("#lname");
	var college = $("#registration").validate().element("#college");
	var career = $("#registration").validate().element("#career");
	var graduationDate = $("#registration").validate().element("#graduationDate");
	var timeZone = $("#registration").validate().element("#timeZone");
	
	var street = $("#registration").validate().element("#street");
	var city = $("#registration").validate().element("#city");
	var gpa = $("#registration").validate().element("#gpa");
	var gpaScale = $("#registration").validate().element("#gpaScale");
	
	if(email && password && confirmPass && mobileNumber && country && name1 && lname && college && career && graduationDate && timeZone && taxId && street && city && gpa && gpaScale){
		$("#form-tutor-con1").hide();
		$("#form-tutor-con2").show();
		$("#form-tutor-con3").hide();
	}
	
}
function next1() {
	$("#form-tutor-con1").hide();
	$("#form-tutor-con2").hide();
	$("#form-tutor-con3").show();
}


function emailCheck(email){
	//var email=$("#email").val();
	var flag = false;
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
	return flag;
}

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
</script>
<script type="text/javascript">
function back() {
	$("#form-tutor-con1").show();
	$("#form-tutor-con2").hide();
	$("#form-tutor-con3").hide();
}
function back1() {
	$("#form-tutor-con1").hide();
	$("#form-tutor-con2").show();
	$("#form-tutor-con3").hide();
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
            <p><spring:message code="wehavesentyourrequesttoAdminforapproval" /></p>
        </div>
        </div>
        </div>
   </c:if> 
   
   <spring:message code="login.email" var="emailP"></spring:message>
   <spring:message code="login.password" var="passwordP"></spring:message>
   <spring:message code="tutor.mbnumber" var="mbnumberP"></spring:message>
   <spring:message code="taxID" var="taxIDP"></spring:message>
    <spring:message code="student.firstname" var="fnameP"></spring:message>
    <spring:message code="student.lastname" var="lnameP"></spring:message>
    <spring:message code="college" var="collegeP"></spring:message>
    <spring:message code="career" var="careerP"></spring:message>
    <spring:message code="graduationDate" var="gdateP"></spring:message>
	<spring:message code="street" var="streetP"></spring:message>
	<spring:message code="city" var="cityP"></spring:message>
	<spring:message code="gpa" var="gpaP"></spring:message>
	<spring:message code="gpaScale" var="gpaScaleP"></spring:message>
    
   <!--Mid Section-->
    <section class="container">
    	<div class="inner-row">
    	<form:form name="registration" id="registration" commandName="dtoTutorRegistration" action="saveTutor" method="post" enctype="multipart/form-data">
    	<!-- First form start -->
    	<div class="form-tutor-con" id="form-tutor-con1">
        	<h1><spring:message code="confirmyourinformation" /></h1>
            <div class="form-tutor-outr">
            	<div class="form-tutor-hdr"><spring:message code="privateInformation" /></div>
                <div class="form-tutor-txt">
                	<p><spring:message code="tutor.desc1"/> </p>
                    <div class="form">
                   		<div class="formRow">
                        	<div class="formtxt">
                        	 <div class="login-form-row">
                            	<form:input type="text" path="email" id="email" name="email" value="${email}" placeholder="${emailP}" maxlength="80" readonly="readonly"/>
                            	</div>
                            </div>
                            
                            <div class="formtxt">
                            <div class="login-form-row">
                            	<form:input path="password" name="password" id="password" type="password" placeholder="${passwordP}" maxlength="80"/>
                            	</div>
                            </div>
                        </div>
                        <div class="formRow">
                         <div class="formtxt">
                         <div class="login-form-row">
                            	<input type="password" name="confirmPass" id="confirmPass" placeholder='<spring:message code="signup.confirmpassword"></spring:message>' maxlength="80">
                            	</div>
                            </div>
                            
                               <div class="formtxt" style="height:40px;">
                            	<span style="color:gray; font-size: 18px;">Address</span>
                            </div>
                            </div>
                             <div class="formRow">
                             <div class="formtxt">
                         <div class="login-form-row">
                            	<form:input type="text" path="street"  id="street" placeholder="${streetP}" maxlength="80"/>
                            	</div>
                            </div>
                             <div class="formtxt">
                         <div class="login-form-row">
                            	<form:input type="text" path="city"  id="city" placeholder="${cityP}" maxlength="80"/>
                            	</div>
                            </div>
                            </div>
                             <div class="formRow">
                              <div class="formtxt">
                         <div class="login-form-row">
                         
                         <form:select id="country" name="country" path="country" onchange="getTimeZone();">
                         <option value="0"><spring:message code="select.country"></spring:message></option>
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
                            </div>
                            
                        	  <div class="formtxt">
                            	
                            	<div class="login-form-row">
                            	<form:select id="timeZone" name="timeZone" path="timeZone">
                            	<option value="0"><spring:message code="selecttimezone"></spring:message></option>
                	<%-- <c:forEach var="listTimeZoneMaster" items="${listTimeZoneMaster}">
                    	<option value='${listTimeZoneMaster.timezne_Id}' >${listTimeZoneMaster.timezone_Name}</option>
                    	</c:forEach> --%>
                    </form:select>
                    </div>
                            </div>
                        </div>
                         <div class="formRow">
                      <div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="mobileNumber" placeholder="${mbnumberP}" maxlength="15"/></div>
                            </div>
                        	<div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="taxId" name="taxId" id="taxId" placeholder="${taxIDP}" />
                            	</div>
                            </div>
                        </div>
                        
                        <div class="clr"></div>
                    </div>
                </div>
            </div>
             <div class="form-tutor-outr">
            	<div class="form-tutor-hdr"><spring:message code="tutor.publicinfo"></spring:message> </div>
                <div class="form-tutor-txt">
                	<p><spring:message code="tutor.desc2"/> </p>
                    <div class="form">
                   		<div class="formRow">
                        	<div class="upload-img-con">
                            
                            
                            <%--fb image start --%>
                            <c:if test="${id != null}">
                             <div class="upload-img-outr">
                             <!-- <input type="file"></input> -->
                            	<div class="upload-img">
                            	<img id="profilePic" alt="" src="http://graph.facebook.com/${id}/picture">
                            	<!-- <img id="profilePic" name="profilePic" alt="" src=""> -->
                            	<%-- <form:input type="hidden" path="imgUrl"/> --%>
                            	 </div>
                            	 <form:input type="hidden" class="inputFile" path="imgUrl" value="http://graph.facebook.com/${id}/picture" />
                            </div>
                            </c:if>
                            <%--fb image end --%>
                            
                            
                            <c:if test="${id == null}">
                            <div class="upload-img-outr">
                            	<div class="upload-img"> 
                            	<img id="profilePic" name="profilePic" alt="" src="">
                            	 </div>
                            </div>
                            <form:input path="uploadFile" type="file" class="inputFile"  onchange="readURL(this);"></form:input>
                            </c:if> 
                            
                            
                            </div>
                        </div>
                        <div class="formRow">
                        	<div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="name" value="${first_name}" id="name" name="name" placeholder="${fnameP}"  maxlength="30" minlength="2"/>
                            	</div>
                            </div>
                            
                            <div class="formtxt">
                            <div class="login-form-row">
                            	<form:input type="text" path="lname" id="lname" value="${last_name}" placeholder="${lnameP}"  maxlength="30" minlength="2"/>
                            	</div>
                            </div>
                        </div>
                        <div class="formRow">
                        	<div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="college" name="college" id="college" placeholder="${collegeP}" /></div>
                            </div>
                            
                            <div class="formtxt">
                            <div class="login-form-row">
                            	<form:input type="text" path="career" id="career" name="career" placeholder="${careerP}"/></div>
                            </div>
                        </div>
                        
                         <div class="formRow">
                        	<div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="graduationDate" placeholder="${gdateP}" id="graduationDate" readonly="true" name="graduationDate" class="calander"/>
                            	</div>
                            </div>
                            
                            	<div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="gpa" placeholder="${gpaP}" id="gpa"  name="gpa"/>
                            	</div>
                            </div>
                            
                             	<div class="formtxt">
                        	<div class="login-form-row">
                            	<form:input type="text" path="gpaScale" placeholder="${gpaScaleP}" id="gpaScale"  name="gpaScale"/>
                            	</div>
                            </div>
                          
                        </div>
                        
                        <div class="clr"></div>
                    </div>
                </div>
            </div>
            
            <div class="bcm-a-tutor form-next">
                    <a id="bcm-a-tutor" onclick="next();"><spring:message code="next" /></a>
                </div>
        </div>
        <!-- First form end -->
        <!-- Second form start -->
        <div class="form-tutor-con" id="form-tutor-con2">
        	<h1><spring:message code="whichsubjectsdoyouwanttoteach" /></h1>
            <div class="accordian-outer">
                <c:set var="count" value="0" scope="page"/>
                <c:forEach items="${subjectList}" var="entry" varStatus="loop">
                <c:set var="count" value="${count+1}" scope="page"/>
                 <section>
                    <h2 class="actog"> <span class="num">${count}</span> ${entry.key} </h2>
                    <div class="accon">
                      <ul>
                      <c:forEach var="list" items="${entry.value}">
                      	<li><form:checkbox path="subjectId" name="abc" value="${list.key}"></form:checkbox> ${list.value}</li>
                      </c:forEach>
                      </ul>
                      
                      <div class="clr"></div>
                    </div>
                </section>
				</c:forEach>
			</div>
           <div class="bcm-a-tutor form-next">
                    <a onclick="back();"><spring:message code="back"></spring:message> </a><a onclick="next1();"><spring:message code="next" /></a>
                </div>
            
        </div>
        
        <spring:message code="tellsomething.aboutyourself" var="tellSomething"></spring:message>
        <spring:message code="tellusmore.aboutyourself" var="tellUsMore"></spring:message>
        
        <!-- Second form end -->
        <!-- Third form start -->
        <div class="form-tutor-con" id="form-tutor-con3">
        	<h1><spring:message code="tellusmoreaboutyourself" /></h1>
            <div class="form-tutor-outr">
            
            <div class="self-intro">
            	<label><spring:message code="doyouhaveteachingexperience" /></label>
                
                <form:textarea rows="5" cols="90" path="about" id="about" name="about" placeholder="${tellSomething}"></form:textarea>
            </div>
            
             <div class="self-intro">
            	<label><spring:message code="whatelsedoyouliketodo" /></label>
                
                <form:textarea rows="5" cols="90" path="aboutMore" placeholder="${tellUsMore}"></form:textarea>
            </div>
             <div class="self-intro">
            <p><spring:message code="tutor.desc"/> </p>
           	
            </div>
            <div class="self-intro">
            	<label class="terms"><input type="checkbox" name="terms" id="terms"> <spring:message code="ihavereadandacceptedthe" /> <a href="<%=request.getContextPath()%>/cms/tutortnc" target="_blank"><spring:message code="footer.termsandcondition" /></a></label>
            </div>
                
            </div>
             <div class="bcm-a-tutor form-apply">
             <input type="button" name="back" id="tutorBack" class="greenBtn-big" onclick="back1();"  value="<spring:message code='back' />" />
            <%--  <a onclick="back1();"><spring:message code="back"></spring:message></a> --%>
             <input name="submit" id="tutorSubmit" class="greenBtn-big"  type="submit" value="<spring:message code='apply' />" />
                   <!--  <a href="javascript:;">Apply</a> -->
                </div>
            
        </div>
        <!-- Third form end -->
        </form:form>
        </div>
    </section>
    <!--//Mid Section-->
   </div>
   <div class="clr"></div>
    <%@ include file="/WEB-INF/views/footerOuter.jsp" %>
<%--     <noscript>
<spring:message code="not.seeing"></spring:message> <a href="http://www.scrolltotop.com/"><spring:message code="scroll.topbutton"></spring:message> </a><spring:message code="goto.faqpage"></spring:message>
</noscript> --%>
</body>
<script type="text/javascript">
$('#graduationDate').datepicker({
    changeMonth: true,
    changeYear: true,
    showButtonPanel: true,
    dateFormat: 'MM yy',
    yearRange: '1950:2050',
    closeText : "Listo",
    currentText : "Hoy",
    onClose: function(dateText, inst) { 
        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
        $(this).datepicker('setDate', new Date(year, month, 1));
        
    }});
    
</script>
</html>
