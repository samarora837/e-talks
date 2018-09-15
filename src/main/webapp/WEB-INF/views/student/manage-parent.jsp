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
<title><spring:message code="header.manageparents"></spring:message></title>
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
	
/* 	
	 $.validator.addMethod("emailIdExist", function(value, element) {
			var flag = emailCheck();
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.alreadyuse"></spring:message>'); */ 
	 
	 $.validator.addMethod("sameEmailIdExist", function(value, element) {
			var flag = sameEmailCheck(value);
		  	return this.optional(element) || flag;	   
			},'<spring:message code="email.alreadyuse"></spring:message>'); 
	 
	 
	 
	$('#parentEditEmailForm').validate({
		rules:{
			parentEmail:{
				sameEmailIdExist: true,
				//emailIdExist:true,
				required:true,
				emailId:true
				//parentEmailCheck:true
				
			}
			
			},

		
		 messages:{
			 parentEmail:{
					required:'<spring:message code="parent.emailRequired" />'
				}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	
	
	 
	$('#addParentEmailForm').validate({
		rules:{
			addParentEmail:{
				sameEmailIdExist: true,
				//emailIdExist:true,
				required:true,
				emailId:true
				
			}
			
			},

		
		 messages:{
			 addParentEmail:{
					required:'<spring:message code="parent.emailRequired" />'
				}
		}, 
		submitHandler:function(form){
    		form.submit();
    	}
	});
	

});


function sameEmailCheck(email){
	
	
	var parentEmail=email;
	var stuEmail=$("#studentEmail").val();
	var flag = false;
	if(parentEmail==stuEmail){
		flag=false;
	}
	else{
		flag=true;
	}
	return flag;
}


<%-- 
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
} --%>

 function emailCheckParent(name){
	 var parentEmail;
	 if(name=="add"){	
		 parentEmail=$("#addParentEmail").val();
	 }
	 else if(name=="edit"){
		 parentEmail=$("#parentEmail").val();
	 }
	var flag = true;
	 var url='<%=request.getContextPath()%>/student/parentReplationEmailCheck';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{parentEmail:parentEmail},
		success:function(response){
		if(response=="alraedy exist"){
			 $("#parentEmailError").text("<spring:message code='student.linkedwithyou' />");
			 $("#parentEmailEditError").text("<spring:message code='student.linkedwithyou' />");
			flag = false;
			}
		else if(response=="alraedy sent"){
			 $("#parentEmailError").text("<spring:message code='student.requestsent' />");
			 $("#parentEmailEditError").text("<spring:message code='student.requestsent' />");
			flag = false;
			}
		else if(response=="alraedy received"){
			 $("#parentEmailError").text("<spring:message code='student.requestreceived' />");
			 $("#parentEmailEditError").text("<spring:message code='student.requestreceived' />");
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
 	$("#parentEmailError").text("");
 	$("#parentEmailEditError").text("");
 	
 }
 
 
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
         <div class="ac-activity ac-activity-2">
           		<h1 class="text-left"> <spring:message code="header.manageparents"></spring:message> <a onclick="openAddParentPopUp();" class="text-right"> <spring:message code="invitetojoinparent"></spring:message></a></h1> 
                <div class="grid-tabs">
                <ul>
                <li id="mtab1"><a href="javascript:;"><spring:message code="requestbystudent"></spring:message></a></li>
               <%--  <li id="mtab2"><a href="javascript:showTab(2,2)"><spring:message code="requestbyparent"></spring:message></a></li> --%>
                </ul>
                </div>
                <div id="tab1">
                <div class="ac-activity-txt mesg-tab">
          			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable">
          			<thead>
                      <tr>
                         <th><spring:message code="full.name"></spring:message> </th>
                        <th><spring:message code="email.parent"></spring:message> </th>
                        <th><spring:message code="approval.status"></spring:message></th>
                        <th class="text-center"><spring:message code="action"></spring:message> </th>
                      </tr>
                      </thead>
                      <tbody>
                       <c:if test="${listParentDetailsSize ne 0}">
                      <c:forEach var="dtoParentDetail" items="${listParentDetails}">
                      <tr>
                        <td>${dtoParentDetail.fullName}</td>
                        <td>${dtoParentDetail.email}</td>
                        <c:if test="${dtoParentDetail.isVerified eq 'Y'}">
                        <td><spring:message code="approved"></spring:message></td>
                        </c:if>
                         <c:if test="${dtoParentDetail.isVerified eq 'N'}">
                        <td><spring:message code="approval.pending"></spring:message></td>
                        </c:if>
                        <td class="text-center"><a  onclick="openEditPopUp('${dtoParentDetail.parentRelationshipId}','${dtoParentDetail.email}');" class="greenBtn-small"><spring:message code="edit"></spring:message></a> 
                        <a onclick="openPopUpDelete('${dtoParentDetail.parentRelationshipId}')"; class="greenBtn-small"><spring:message code="delete"></spring:message></a></td>
                      </tr>
                      </c:forEach>
                    </c:if>
                   
                    </tbody>
                    </table>
                </div>
                </div>
                
      
                
            </div>
            
             <input type="hidden" id="studentEmail" value="${studentEmail}"/>
     
        
        </div>
        
        
        
         <div class="stu-ac-outr">
         <div class="ac-activity ac-activity-2">
       
                <div class="grid-tabs">
                <ul>
                <%-- <li id="mtab1" class="selected"><a href="javascript:showTab(1,2)"><spring:message code="requestbystudent"></spring:message></a></li> --%>
                <li id="mtab2"><a href="javascript:;"><spring:message code="requestbyparent"></spring:message></a></li>
                </ul>
                </div>
              
                
                  <div id="tab2" style="display:block;">
                <div class="ac-activity-txt mesg-tab">
             <table width="100%" border="0" cellspacing="0" cellpadding="0" id="myTable1">
             <thead>
                      <tr>
                       <th><spring:message code="full.name"></spring:message> </th>
                        <th><spring:message code="email.parent"></spring:message> </th>
                        <th><spring:message code="approval.status"></spring:message></th>
                        <th class="text-center"><spring:message code="action"></spring:message> </th>
                      </tr>
                      </thead>
                      <tbody>
                       <c:if test="${listParentDetailsAddedByParentSize ne 0}">
                      <c:forEach var="dtoParentDetail" items="${listParentDetailsAddedByParent}">
                      <tr>
                        <td>${dtoParentDetail.fullName}</td>
                        <td>${dtoParentDetail.email}</td>
                        <c:if test="${dtoParentDetail.isVerified eq 'Y'}">
                        <td><spring:message code="approved"></spring:message></td>
                        </c:if>
                         <c:if test="${dtoParentDetail.isVerified eq 'N'}">
                       <td><spring:message code="approval.pending"></spring:message></td>
                        </c:if>
                        <td class="text-center">
                         <c:if test="${dtoParentDetail.isVerified eq 'N'}">
                        <a  onclick="openApprovePopUp('${dtoParentDetail.parentRelationshipId}');" class="greenBtn-small"><spring:message code="approve"></spring:message></a>
                        </c:if> 
                        <a onclick="openDisApprovePopUp('${dtoParentDetail.parentRelationshipId}')"; class="greenBtn-small"><spring:message code="delete"></spring:message></a></td>
                      </tr>
                      </c:forEach>
                      </c:if>
                 
                    </tbody>
                    </table>
                </div>
                </div>
                
            </div>
            
             <input type="hidden" id="studentEmail" value="${studentEmail}"/>
     
        
        </div>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      </div>
    </div>
  </section>
  <!--//Mid Section--> 
</div>


<div class="clr"></div>
  <%@ include file="/WEB-INF/views/footerInner.jsp" %>

<div id="addParentpopup" style="display:none;">
<div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a  onclick="closeAddParentPopUp();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
            
          <form name="addParentEmailForm" id="addParentEmailForm" method="post">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  <tr>
                   <th><spring:message code="login.email"></spring:message></th>
                    <td>:</td>  
                    <td><div ><input type="text"  id="addParentEmail" name="addParentEmail" maxlength="80"  onfocus="clrError();"/>
                    <label id="parentEmailError" class="error"></label>
                    </div></td>
                    									
                  </tr>
                                  
                   <tr>
                   <th colspan="2">&nbsp;</th>
                     <td><input type="button" class="greenBtn-normal" value='<spring:message code="add"></spring:message>' onclick="submitAddParentForm();"></td>
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
            
          <form name="parentEditEmailForm" id="parentEditEmailForm" method="post">
           <div class="my-info-popup">
                <table cellspacing="0" cellpadding="0" border="0" width="100%">
                  <tbody>
                  <tr>
                   <th><spring:message code="login.email"></spring:message> </th>
                    <td>:</td>  
                    <td><div ><input type="text"  id="parentEmail" name="parentEmail" maxlength="80" onfocus="clrError();"/>
                    <label id="parentEmailEditError" class="error"></label></div></td>
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
            	<spring:message code="areyousure.deleteparent"></spring:message>
            </div>
             
           <div class="book-session">
               <a onclick="submitDeleteParentForm();" class="greenBtn-normal" style="font-weight: normal;"> <spring:message code="delete"></spring:message></a>
                                
           </div>
           <form name="deleteParentForm" id="deleteParentForm" method="post">
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
            	<spring:message code="areyousure.approveparent"></spring:message>
            </div>
             
           <div class="book-session">
               <a onclick="submitApproveParentForm();" class="greenBtn-normal" style="font-weight: normal;"> <spring:message code="approve"></spring:message> </a>
                                
           </div>
           <form name="approveParentForm" id="approveParentForm" method="post">
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
            	<spring:message code="areyousure.disapproveparent"></spring:message>
            </div>
             
           <div class="book-session">
               <a onclick="submitDisapproveParentForm();" class="greenBtn-normal" style="font-weight: normal;"> <spring:message code="delete"></spring:message></a>
                                
           </div>
           <form name="disApproveParentForm" id="disApproveParentForm" method="post">
            <input type="hidden" id="parentStudentRalationdDisApproveId" name="parentStudentRalationdDisApproveId"/>
           </form>
           
        </div>
        </div>
        </div>






</body>

 <script type="text/javascript">
 
 
 function openAddParentPopUp()
 {
	 $(window).scrollTop(0);
	$( "#addParentEmail" ).focus();
 	$("#addParentpopup").show();
 	
 }

 function closeAddParentPopUp(){
	 $("#addParentEmail").val("");
	// $("label.error").hide();
 	$("#addParentpopup").hide();
 }


 function submitAddParentForm(){
	 
	 var flag=emailCheckParent("add");
	 
	 
	 if(flag){	 
 	$("#addParentEmailForm").attr("action", "<%=request.getContextPath()%>/student/addParentEmail");
 	$("#addParentEmailForm").submit();
	 }
 }
 
 
 
 
 

function openEditPopUp(id,email)

{
	var parentStudentRalationId=id;
	var parentEmail=email;
	$("#parentEmail").val(parentEmail);
	
	$("#parentStudentRalationId").val(parentStudentRalationId);
	$(window).scrollTop(0);
	$("#editpopup").show();
	
}

function closeEditPopUp(){
	$("label.error").hide();
	$("#editpopup").hide();
}


function submitEditEmailForm(){
 var flag=emailCheckParent("edit");
	 
	 
	 if(flag){
	
	$("#parentEditEmailForm").attr("action", "<%=request.getContextPath()%>/student/updateParentEmail");
	$("#parentEditEmailForm").submit();
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

function submitDeleteParentForm(){
	$("#deleteParentForm").attr("action", "<%=request.getContextPath()%>/student/deleteParentEmail");
	$("#deleteParentForm").submit();
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

function submitApproveParentForm(){
	$("#approveParentForm").attr("action", "<%=request.getContextPath()%>/student/approveParentEmail");
	$("#approveParentForm").submit();
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

function submitDisapproveParentForm(){
	$("#disApproveParentForm").attr("action", "<%=request.getContextPath()%>/student/disApproveParentEmail");
	$("#disApproveParentForm").submit();
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
  
 // var status='${approve}';

/*  // if(status=="approve"){
	 // getMyHTMLElement('tab2').style.display = '';
		//getMyHTMLElement('mtab2').className = 'selected';
	//	getMyHTMLElement('tab1').style.display = 'none';
		//getMyHTMLElement('mtab1').className = '';
  }
  else
	  {
	  //getMyHTMLElement('tab1').style.display = '';
		//getMyHTMLElement('mtab1').className = 'selected';
		//getMyHTMLElement('tab2').style.display = 'none';
		//getMyHTMLElement('mtab2').className = '';
	  } */
  


/* function showTab(current,max){
	for (i=1;i<=max;i++){
	//getMyHTMLElement('tab' + i).style.display = 'none';
	//getMyHTMLElement('mtab' + i).className = '';
	//}
	//getMyHTMLElement('tab' + current).style.display = '';
	//getMyHTMLElement('mtab' + current).className = 'selected';
	//}
	
	 */
	

</script> 
<%-- <script type="text/javascript" src="js/scrolltop/arrow23.js"></script>
<noscript>
<spring:message code="not.seeing"></spring:message> <a href="http://www.scrolltotop.com/"><spring:message code="scroll.topbutton"></spring:message> </a><spring:message code="goto.faqpage"></spring:message>
</noscript> --%>


</html>
