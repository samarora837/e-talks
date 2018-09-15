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
<title><spring:message code="header.managestudent"></spring:message></title>
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
 $(document).ready(function(){
	    $('#myTable').DataTable();
	    $('#myTable1').DataTable();
	});
 </script> 
 <script type="text/javascript"> 

function stopRKey(evt) { 
  var evt = (evt) ? evt : ((event) ? event : null); 
  var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
  if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
} 

document.onkeypress = stopRKey; 

</script>
 
<script type="text/javascript">
$(document).ready(function(){
	
	
	$.validator.addMethod("emailId", function(value, element) {
		return this.optional(element) || /^((([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(value);
		},'<spring:message code="enter.email"></spring:message>');
	
	 
	 $.validator.addMethod("sameEmailIdExist", function(value, element) {
			var flag = sameEmailCheck(value);
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.alreadytaken"></spring:message>'); 
	 
	 
	$('#studentEditEmailForm').validate({
		rules:{
			studentEmail:{
				sameEmailIdExist: true,
				//emailIdExist:true,
				required:true,
				emailId:true
				
			}
			
			},

		
		 messages:{
			 studentEmail:{
					required:'<spring:message code="parent.emailRequired" />'
				}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	
	
	 
	$('#addstudentEmailForm').validate({
		rules:{
			addStudentEmail:{
				sameEmailIdExist: true,
				required:true,
				emailId:true
				
			}
			
			},

		
		 messages:{
			 addStudentEmail:{
					required:'<spring:message code="parent.emailRequired" />'
				}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});


function sameEmailCheck(email){
	var parentEmail=$("#parentEmail").val();
	var stuEmail=email;
	var flag = false;
	if(parentEmail==stuEmail){
		flag=false;
	}
	else{
		flag=true;
	}
	return flag;
}



 function emailCheckStudent(name){
	 var studentEmail;
	 if(name=="add"){	
		 studentEmail=$("#addStudentEmail").val();
	 }
	 else if(name=="edit"){
		 studentEmail=$("#studentEmail").val();
	 }
	var flag = true;
	 var url='<%=request.getContextPath()%>/parent/studentReplationEmailCheck';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{studentEmail:studentEmail},
		success:function(response){
		if(response=="alraedy exist"){
			 $("#studentEmailError").text("<spring:message code='parent.linkedwithyou' /> ");
			 $("#studentEmailEditError").text("<spring:message code='parent.linkedwithyou' /> ");
			flag = false;
			}
		else if(response=="alraedy sent"){
			 $("#studentEmailError").text("<spring:message code='parent.requestsent' /> ");
			 $("#studentEmailEditError").text("<spring:message code='parent.requestsent' /> ");
			flag = false;
			}
		else if(response=="alraedy received"){
			 $("#studentEmailError").text("<spring:message code='parent.requestreceived' /> ");
			 $("#studentEmailEditError").text("<spring:message code='parent.requestreceived' /> ");
			flag = false;
			}
		else{
			flag = true;
		}
		}
		
	});  
	return flag;
}
 


 function clrError()
 {
 	$("#studentEmailError").text("");
 	$("#studentEmailEditError").text("");
 	
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
         <div class="ac-activity ac-activity-2">
           		<h1 class="text-left"> <spring:message code="header.managestudent"></spring:message> <a onclick="openAddStudentPopUp();" class="text-right"> <spring:message code="invitetojoinstudent"></spring:message></a></h1> 
                <div class="grid-tabs">
                <ul>
                <li id="mtab1" class=""><a href="javascript:;"><spring:message code="requestbyparent"></spring:message></a></li>
                <!-- <li id="mtab2"><a href="javascript:showTab(2,2)"><spring:message code="requestbystudent"></spring:message> </a></li> -->
                </ul>
                </div>
                <div id="tab1">
                <div class="ac-activity-txt mesg-tab">
          	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
          	<thead>
                      <tr>
                        <th><spring:message code="full.name"></spring:message> </th>
                        <th><spring:message code="email.student"></spring:message> </th>
                        <th><spring:message code="approval.status"></spring:message></th>
                        <th class="text-center"><spring:message code="action"></spring:message> </th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${listStudentDetailsSize ne 0}">
                      <c:forEach var="listStudentDetails" items="${listStudentDetails}">
                      <tr>
                        <td>${listStudentDetails.fullName}</td>
                        <td>${listStudentDetails.studentEmail}</td>
                        <c:if test="${listStudentDetails.isVerified eq 'Y'}">
                        <td><spring:message code="approved"></spring:message> </td>
                        </c:if>
                         <c:if test="${listStudentDetails.isVerified eq 'N'}">
                        <td><spring:message code="approval.pending"></spring:message> </td>
                        </c:if>
                        <td class="text-center"><a  onclick="openEditPopUp('${listStudentDetails.parentRelationshipId}','${listStudentDetails.studentEmail}');" class="greenBtn-small"><spring:message code="edit"></spring:message></a> 
                        <a onclick="openPopUpDelete('${listStudentDetails.parentRelationshipId}')"; class="greenBtn-small"><spring:message code="delete"></spring:message></a></td>
                      </tr>
                      </c:forEach>
                    </c:if>
                  </tbody>
                    </table>
                </div>
                </div>
                
                
            </div>
        

            
            <input type="hidden" id="parentEmail" value="${parentEmail}"/> 
     
        
        </div>
        
        
        
         <div class="stu-ac-outr">
         <div class="ac-activity ac-activity-2">
           
                <div class="grid-tabs">
                <ul>
                <!--  <li id="mtab1" class="selected"><a href="javascript:showTab(1,2)"><spring:message code="requestbyparent"></spring:message></a></li>-->
                <li id="mtab2"><a href="javascript:;"><spring:message code="requestbystudent"></spring:message> </a></li>
                </ul>
                </div>
               
                
                  <div id="tab2" style="display:block">
                <div class="ac-activity-txt mesg-tab">
             <table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable1">
             <thead>
                      <tr>
                        <th><spring:message code="full.name"></spring:message> </th>
                        <th><spring:message code="email.student"></spring:message> </th>
                        <th><spring:message code="approval.status"></spring:message></th>
                        <th class="text-center"><spring:message code="action"></spring:message> </th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:if test="${listStudentDetailsAddedByStudentSize ne 0}">
                       <c:forEach var="dtoStudentDetail" items="${listStudentDetailsAddedByStudent}">
                      <tr>
                        <td>${dtoStudentDetail.fullName}</td>
                        <td>${dtoStudentDetail.studentEmail}</td>
                        <c:if test="${dtoStudentDetail.isVerified eq 'Y'}">
                        <td><spring:message code="approved"></spring:message></td>
                        </c:if>
                         <c:if test="${dtoStudentDetail.isVerified eq 'N'}">
                        <td><spring:message code="approval.pending"></spring:message></td>
                        </c:if>
                        <td class="text-center">
                         <c:if test="${dtoStudentDetail.isVerified eq 'N'}">
                        <a  onclick="openApprovePopUp('${dtoStudentDetail.parentRelationshipId}');" class="greenBtn-small"><spring:message code="approve"></spring:message></a>
                        </c:if> 
                        <a onclick="openDisApprovePopUp('${dtoStudentDetail.parentRelationshipId}')"; class="greenBtn-small"><spring:message code="delete"></spring:message></a></td>
                      </tr>
                      </c:forEach> 
                    </c:if>
                    
                    </tbody>
                    </table>
                </div>
                </div>
                
            </div>
        

            
            <input type="hidden" id="parentEmail" value="${parentEmail}"/> 
     
        
        </div>
      </div>
    </div>
  </section>
  <!--//Mid Section--> 
</div>


<div class="clr"></div>
<%@ include file="/WEB-INF/views/footerInner.jsp" %>

 <div id="addStudentpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeAddStudentPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form name="addstudentEmailForm" id="addstudentEmailForm" method="post">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  <tr>
                   <th><spring:message code="login.email"></spring:message> </th>
                    <td>:</td>  
                    <td><div ><input type="text"  id="addStudentEmail" name="addStudentEmail" maxlength="80"  onfocus="clrError();"/>
                    <label id="studentEmailError" class="error"></label>
                    </div></td>
                    									
                  </tr>
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="add"></spring:message>' onclick="submitAddStudentForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                </form>
        	
        </div>
   </div>

</div>




<div id="editpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeEditPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form name="studentEditEmailForm" id="studentEditEmailForm" method="post">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  <tr>
                   <th><spring:message code="login.email"></spring:message></th>
                    <td>:</td>  
                    <td><div ><input type="text"  id="studentEmail" name="studentEmail" maxlength="80" onfocus="clrError();"/>
                    <label id="studentEmailEditError" class="error"></label></div></td>
                  </tr>
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="update"></spring:message>' onclick="submitEditEmailForm();"></td>
                  </tr>
                </tbody></table>
                </div>
                <input type="hidden" id="parentStudentRalationId" name="parentStudentRalationId"/>
                </form>
        	
        </div>
   </div>

</div> 


 



 
 <div class="form-tutor-popup" id="deletepopup" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeDeletePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
                 <div class="rating-Txt" >
            	<spring:message code="areyousure.deletestudent"></spring:message>
            </div>
             
           <div class="book-session">
               <a onclick="submitDeleteStudentForm();" class="greenBtn-normal" style="font-weight: normal;"> <spring:message code="delete"></spring:message> </a>
                                
           </div>
           <form name="deleteStudentForm" id="deleteStudentForm" method="post">
            <input type="hidden" id="parentStudentRalationdDeleteId" name="parentStudentRalationdDeleteId"/>
           </form>
           
        </div>
        </div>
        </div>



 <div class="form-tutor-popup" id="approvepopup" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeApprovePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
                 <div class="rating-Txt" >
            	<spring:message code="areyousure.approvestudent"></spring:message>
            </div>
             
           <div class="book-session">
               <a onclick="submitApproveStudentForm();" class="greenBtn-normal" style="font-weight: normal;"> <spring:message code="approve"></spring:message> </a>
                                
           </div>
           <form name="approveStudentForm" id="approveStudentForm" method="post">
            <input type="hidden" id="parentStudentRalationdApproveId" name="parentStudentRalationdApproveId"/>
           </form>
           
        </div>
        </div>
        </div>



 <div class="form-tutor-popup" id="disApprovepopup" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeDisapprovePopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
                 <div class="rating-Txt" >
            	<spring:message code="areyousure.disapprovestudent"></spring:message>
            </div>
             
           <div class="book-session">
               <a onclick="submitDisapproveStudentForm();" class="greenBtn-normal" style="font-weight: normal;"> <spring:message code="delete"></spring:message></a>
                                
           </div>
           <form name="disApproveStudentForm" id="disApproveStudentForm" method="post">
            <input type="hidden" id="parentStudentRalationdDisApproveId" name="parentStudentRalationdDisApproveId"/>
           </form>
           
        </div>
        </div>
        </div>


 




</body>

  <script type="text/javascript">
 
 
 function openAddStudentPopUp()

 {
	 $(window).scrollTop(0);
	 $( "#addStudentEmail" ).focus();
 	$("#addStudentpopup").show();
 	
 }

 function closeAddStudentPopUp(){
	 $("#addStudentEmail").val("");
	 $("#studentEmailError").text("");
	 
	//	$("label.error").hide();
 	$("#addStudentpopup").hide();
 }


 function submitAddStudentForm(){
	 
	 
	  var flag=emailCheckStudent("add");
	 if(flag){	
		 document.getElementById("addstudentEmailForm").action = "<%=request.getContextPath()%>/parent/addStudentEmail";
 	document.getElementById("addstudentEmailForm").submit();
	 }
 }
 
 
 
 
 

function openEditPopUp(id,email)

{
	var parentStudentRalationId=id;
	var studentEmail=email;
	$("#studentEmail").val(studentEmail);
	
	$("#parentStudentRalationId").val(parentStudentRalationId);
	$(window).scrollTop(0);
	$("#editpopup").show();
	
}

function closeEditPopUp(){
	$("#studentEmailEditError").text("");
	$("label.error").hide();
	$("#editpopup").hide();
}


function submitEditEmailForm(){
 var flag=emailCheckStudent("edit");
	 
	 
	 if(flag){
	
	$("#studentEditEmailForm").attr("action", "<%=request.getContextPath()%>/parent/updateStudentEmail");
	$("#studentEditEmailForm").submit();
	 }
}


function openPopUpDelete(id){
	var parentStudentRalationId=id;
	$("#parentStudentRalationdDeleteId").val(parentStudentRalationId);
	$(window).scrollTop(0);
	$("#deletepopup").show();
}

function closeDeletePopUp(){
	$("#deletepopup").hide();
}

function submitDeleteStudentForm(){
	$("#deleteStudentForm").attr("action", "<%=request.getContextPath()%>/parent/deleteStudentEmail");
	$("#deleteStudentForm").submit();
}


 

function openApprovePopUp(id){
	$(window).scrollTop(0);
	var parentStudentRalationId=id;
	$("#parentStudentRalationdApproveId").val(parentStudentRalationId);
	
	$("#approvepopup").show();
}

function closeApprovePopUp(){
	$("#approvepopup").hide();
}

function submitApproveStudentForm(){
	$("#approveStudentForm").attr("action", "<%=request.getContextPath()%>/parent/approveStudentEmail");
	$("#approveStudentForm").submit();
}




function openDisApprovePopUp(id){
	$(window).scrollTop(0);
	var parentStudentRalationId=id;
	$("#parentStudentRalationdDisApproveId").val(parentStudentRalationId);
	
	$("#disApprovepopup").show();
}

function closeDisapprovePopUp(){
	$("#disApprovepopup").hide();
}

function submitDisapproveStudentForm(){
	$("#disApproveStudentForm").attr("action", "<%=request.getContextPath()%>/parent/disApproveStudentEmail");
	$("#disApproveStudentForm").submit();
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
  
});

	

</script> 



</html>
