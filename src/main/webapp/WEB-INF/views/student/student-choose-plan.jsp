get<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="student.chooseplan" /></title>
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

<script src="<%=request.getContextPath()%>/js/jquery-1.7.2.min.js" type="text/javascript" ></script>

<script src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

<script type="text/javascript">

function changePlan(planId, planMasterId) {
	var planMasterId=planMasterId;
	var planId=planId;
	
	
	if(planMasterId=='1'){
		$("#selectBasic").addClass("disbble");
		$("#selectPopular").removeClass("disbble");
		$("#selectPlus").removeClass('disbble');
		$("#basic").addClass("popular");
		$("#popular").removeClass("popular");
		$("#plus").removeClass('popular');
		$("#imgBasic").attr("src",'<%=request.getContextPath()%>/images/green-tick.png');
		$("#imgPopular").attr("src",'<%=request.getContextPath()%>/images/blue-hand.png');
		$("#imgPlus").attr("src",'<%=request.getContextPath()%>/images/blue-hand.png');
		
		//$("#durationBasic").show();
		//$("#durationPopular").hide();
		//$("#durationPlus").hide();
		
		
		
	}
	else if(planMasterId=='2'){
		$("#selectBasic").removeClass("disbble");
		$("#selectPopular").addClass("disbble");
		$("#selectPlus").removeClass('disbble');
		$("#popular").addClass("popular");
		$("#plus").removeClass('popular');
		$("#basic").removeClass("popular");
		$("#imgBasic").attr("src",'<%=request.getContextPath()%>/images/blue-hand.png');
		$("#imgPopular").attr("src",'<%=request.getContextPath()%>/images/green-tick.png');
		$("#imgPlus").attr("src",'<%=request.getContextPath()%>/images/blue-hand.png');
		
		//$("#durationBasic").hide();
		//$("#durationPopular").show();
		//$("#durationPlus").hide();
		
		
		}
	else if(planMasterId=='3'){
		$("#selectBasic").removeClass("disbble");
		$("#selectPopular").removeClass("disbble");
		$("#selectPlus").addClass('disbble');
		$("#plus").addClass('popular');
		$("#basic").removeClass("popular");
		$("#popular").removeClass("popular");
		$("#imgBasic").attr("src",'<%=request.getContextPath()%>/images/blue-hand.png');
		$("#imgPopular").attr("src",'<%=request.getContextPath()%>/images/blue-hand.png');
		$("#imgPlus").attr("src",'<%=request.getContextPath()%>/images/green-tick.png');
		
		//$("#durationBasic").hide();
		//$("#durationPopular").hide();
		//$("#durationPlus").show();
	}
	
	
	$("#planId").val(planId);
	$("#planMasterId").val(planMasterId);
	
	
	
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

 <c:if test="${paymentFail eq 'Y' }">
        <div class="form-tutor-popup" id="paymentFail" >
   				<div class="popup-back">   </div>
   				<div class="form-tutor-popup my-info-popup-outr">
   			<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closepaymentFail();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
                 <div class="rating-Txt">
            	<spring:message code="paymentfail"></spring:message>
            </div>
     
        </div>
        </div>
        </div>
     </c:if>



  <!--Mid Section-->
  <section class="container">
    <div class="inner-row">
      <div class="form-tutor-con">
        <div class="choosePlan-outr"> 
         <h1><spring:message code="student.chooseplan" /></h1>
          <!--Buy Minutes-->
          <div class="buy-minutes-con cPlan-buy-minutes">
            <div class="buy-min-top">
              <h2><spring:message code="student.buy"></spring:message> <br>
                <spring:message code="student.minutes"></spring:message></h2>
                  <div class="per-month">${dtoPlanRate.buyminPlanHours} <spring:message code="student.1hourpermonth"></spring:message> </br>
                  <span class="currencyType">$</span> <span>${dtoPlanRate.buyPlanRate}</span> 
                </div>
              <p>${dtoPlanRate.topDesc}  </p>
            </div>
                   
							
							<input type="hidden" id="planId" name="planId">
							<input type="hidden" id="planMasterId" name="planMasterId">
							 <input type="hidden" id="subscribeMonth" name="subscribeMonth">

            <div class="buy-min-btm">
              <p>

                <select name="butMin" id="buyMin">
                  <option value="15">15 <spring:message code="mins"/></option>
                  <option value="30">30 <spring:message code="mins"/></option>
                  <option value="45">45 <spring:message code="mins"/></option>
                  <option value="60">60 <spring:message code="mins"/></option>
                </select>
              </p>
              <div class="payBtns"> <a onclick="getBuyMinute('${dtoPlanRate.buyPlanId}');">
              <img src="https://www.paypal.com/es_XC/i/btn/btn_xpressCheckout.gif" align="left" style="margin-right:7px;"> 
              </a>
               </div>
            </div>
           
          </div>
          <!--//Buy Minutes--> 
          
          <!--Pricing-->
          <div class="choose-plan-rt cplan-rt">
            <ul>
              <li id="basic">
                <h2><spring:message code="student.basic"/> <span class="img-active"><img id="imgBasic" src="<%=request.getContextPath()%>/images/blue-hand.png" alt=""></span></h2>
                   <div class="per-month">${dtoPlanRate.basicPlanHour} <spring:message code="student.1hourpermonth"/>
                   <span><span class="currencyType">$</span> ${dtoPlanRate.basicPlanRate}</span> 
                </div>
                <div class="price-desc">${dtoPlanRate.basicDesc} </div>
                <div class="payBtns cPlanBtns"> 
                <div class="clr"><a onclick="changePlan('${dtoPlanRate.basicPlanId}',1);" class="greenBtn-small" id="selectBasic"><spring:message code="selectplan"/></a></div>
                    <div class="set-dura" id="durationBasic" style="display:none;">
                    <label><spring:message code="setduration"/> </label>
                    <select name="subscribeMonthBasic" id="subscribeMonthBasic">
                  <option value="3">3 <spring:message code="months"/></option>
                  <option value="6">6 <spring:message code="months"/></option>
                  <option value="9">9 <spring:message code="months"/></option>
                  <option value="1"><spring:message code="lifeTime"/> </option>
                	</select>
                    </div>
                 </div>
              </li>
              <li id="popular">
                <h2><spring:message code="student.popular"/> <span class="img-inactive"><img id="imgPopular" src="<%=request.getContextPath()%>/images/blue-hand.png" alt=""></span></h2>
                <div class="per-month">${dtoPlanRate.popularPlanHours} <spring:message code="student.2hourpermonth"/>
                	<span><span class="currencyType">$</span> ${dtoPlanRate.popularPlanRate}</span> 
                </div>
                <div class="price-desc">${dtoPlanRate.popularDesc}  </div>
                <div class="payBtns cPlanBtns"> 
                <div class="clr"><a onclick="changePlan('${dtoPlanRate.popularPlanId}',2);" class="greenBtn-small" id="selectPopular"><spring:message code="selectplan"/></a></div>
                    <div class="set-dura" id="durationPopular" style="display:none;">
                    <label><spring:message code="setduration"/></label>
                  <select name="subscribeMonthPopular" id="subscribeMonthPopular" >
                  <option value="3">3 <spring:message code="months"/></option>
                  <option value="6">6 <spring:message code="months"/></option>
                  <option value="9">9 <spring:message code="months"/></option>
                  <option value="1"><spring:message code="lifeTime"/> </option>
                </select>
                    </div>
                </div>
              </li>
              <li id="plus">
                <h2><spring:message code="student.plus"/> <span class="img-inactive"><img id="imgPlus" src="<%=request.getContextPath()%>/images/blue-hand.png" alt=""></span></h2>
                  <div class="per-month">${dtoPlanRate.plusPlanHours} <spring:message code="student.2hourpermonth"/>
                	<span><span class="currencyType">$</span> ${dtoPlanRate.plusPlanRate}</span> 
                </div>
                <div class="price-desc">${dtoPlanRate.plusDesc} </div>
                <div class="payBtns cPlanBtns"> 
                <div class="clr"><a onclick="changePlan('${dtoPlanRate.plusPlanId}',3);" class="greenBtn-small" id="selectPlus"><spring:message code="selectplan"/> </a></div>
                    <div class="set-dura" id="durationPlus" style="display:none;">
                    <label><spring:message code="setduration"/></label>
                    <select name="subscribeMonthPlus" id="subscribeMonthPlus" >
                  <option value="3">3 <spring:message code="months"/></option>
                  <option value="6">6 <spring:message code="months"/></option>
                  <option value="9">9 <spring:message code="months"/></option>
                  <option value="1"><spring:message code="lifeTime"/> </option>
                </select>
                    </div>
                </div>
              </li>
            </ul>
          </div>
          <!--//Pricing--> 
        </div>
        
               <div class="not-ready-buy cPBtns">
          <a onclick="confirmSubscription();" class="greenBtn-normal"><spring:message code="subcribePlan"/></a>
          
           <a onclick="submitForm();" class="greyBtn-normal"><spring:message code="student.readytobuy"></spring:message></a>
           
           
            <!--PayPal Logo -->
           <table border="0" cellpadding="10" cellspacing="0" align="center" class="ppTable">
           <tr><td align="center"></td></tr>
           <tr><td align="center">
           <a  title="HowPayPal Works" >
           <img src="https://www.paypalobjects.com/webstatic/mktg/logo/AM_SbyPP_mc_vs_dc_ae.jpg" border="0" alt="PayPal AcceptanceMark"></a>
           </td></tr>
           </table><!--PayPal Logo -->
        </div>
        
        <%-- 
           <div class="not-ready-buy text-center">
        	<p><spring:message code="student.readytobuy"></spring:message> </p>
            <a href="#" class="greenBtn-normal" onclick="submitForm();"><spring:message code="student.continue"></spring:message> </a> 
            
            <p>	
             <span><spring:message code="student.question"></spring:message> <a href="javascript:;"><spring:message code="student.faq"></spring:message></a></span>
            	<span class="pl-15"><spring:message code="student.chatwithour"></spring:message> <a href="javascript:;"> <spring:message code="student.support"></spring:message> </a></span>
            </p>
        
        </div> --%>
      </div>
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
        
        
        <div class="form-tutor-popup" id="confirmSubscription" style="display:none;">
   <div class="popup-back">   </div>
   <div class="form-tutor-popup my-info-popup-outr">
   		<div class="fTutor-popup-txt">
        	<div class="close-icon"><a onclick="closeConfirmSubscription();"><img src="<%=request.getContextPath()%>/images/close-icon.png" alt=""></a></div>
                 <div class="rating-Txt" id="subcribeMessage">
            	
            </div>
             
     
        </div>
        </div>
        </div>
        
        <form name="chooseFrom" id="chooseFrom">
        
        <input type="hidden" value="${userId}" name="userId" id="userId">
        </form>
  
<script type="text/javascript">
$(document).ready(function() {
	
	
	var currencyType = '${currencyType}';
	if(currencyType=="US"){
		$(".currencyType").text("US $");
	}
	else if(currencyType=="EURO"){
		$(".currencyType").text('\u20AC');
	}
	else if(currencyType=="MXN"){
		$(".currencyType").text("$");
	}
	
	
	
	
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



function submitForm()
{
	
$("#chooseFrom").attr("action", "<%=request.getContextPath()%>/student/registersuccess");
	
	$("#chooseFrom").submit();
}


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

function closePopUp(){
	$("#signupRequired").hide();
}


function sendMsg(){
	 $(window).scrollTop(0);
	 $("#signupRequired").show();
}



function getBuyMinute(planID){
	
	var buyMinute=$("#buyMin").val();
	var userId=$("#userId").val();
	var url="<%=request.getContextPath()%>/student/buyMinuteRegister";
	
	$.ajax({
        url: url,
        method:'GET',
		async :false,
		data:{buyMinute:buyMinute,userId:userId,planID:planID},
        success : function(result) {
        	if(result!=null){
           		/* window.location.href = "https://www.sandbox.paypal.com/webscr?cmd=_ap-payment&paykey="+result.key; */
        		window.location.href = "https://www.paypal.com/webscr?cmd=_ap-payment&paykey="+result;
        	}
        	
        	
        	
          }     

  }); 
}

function closeConfirmSubscription(){
	
	$("#confirmSubscription").hide();
	location.reload();
}


function confirmSubscription(){
	
	var planMasterId=$("#planMasterId").val();
	
	
	var selectDuration="0";;
	
	
	if(planMasterId==''){
		$("#subcribeMessage").html('<span style="padding-top:20px;width: 100%;float: left;"><spring:message code="selectPlan"/></span>');
		$("#subcribeMessage").append('<div class="book-session" style="margin-top:40px;width: 100%;float: left;"><a onclick="closeConfirmSubscription();" class="greenBtn-normal" style="margin-right:5px;line-height:20px;font-weight: normal;"> <spring:message code="ok"></spring:message> </a></div>');
	}
	
	else
		{
		$("#subcribeMessage").html('<spring:message code="selectedMessage1"/> <span id="planName"></span> <spring:message code="selectedMessage2"/> <span id="monthDuration"></span> <spring:message code="selectedMessage3"/>');
		$("#subcribeMessage").append('<div class="book-session" style="margin-top:10px;"><a onclick="subscribePlan();" class="greenBtn-normal" style="margin-right:5px;line-height:20px;font-weight: normal;"> <spring:message code="ok"></spring:message> </a><a onclick="closeConfirmSubscription();" class="greenBtn-normal" style="line-height:20px;font-weight: normal;"> <spring:message code="cancel"></spring:message> </a></div>');
		}
	
	if(planMasterId=='1'){
		$("#planName").html('<spring:message code="basic.plan"/>');
		//selectDuration=$("#subscribeMonthBasic").val();
		selectDuration="0";
	}
	else if(planMasterId=='2'){
		$("#planName").html('<spring:message code="popular.plan"/>');
		//selectDuration=$("#subscribeMonthPopular").val();
		selectDuration="0";
		}
	else if(planMasterId=='3'){
		$("#planName").html('<spring:message code="plus.plan"/>');
		//selectDuration=$("#subscribeMonthPlus").val();
		selectDuration="0";
	}
	
	
	/* if(selectDuration=='1'){
		$("#monthDuration").html('<spring:message code="lifeTime"/>');
	}
	else{
	$("#monthDuration").html(selectDuration+' <spring:message code="months"/>');
	} */
	
	$("#subscribeMonth").val(selectDuration);
	
	$(window).scrollTop(0);
	$("#confirmSubscription").show();
}

function subscribePlan(){
	
	var planId=$("#planId").val();
	//var selectDuration=$("#subscribeMonth").val();
	var selectDuration="0";
	var userId=$("#userId").val();
	
	
	 var url='<%=request.getContextPath()%>/student/planSubscriptionRegister';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{planId:planId,selectDuration:selectDuration,userId:userId},
		success:function(response){
			
			 if(response!='')
				 {
				 window.location.href = response;
				 
				 }else
					 {
					 alert(response);
					 }
			
			 
		}
	}); 
}

function closepaymentFail(){
	$("#paymentFail").hide();
}

</script> 





</body>
</html>