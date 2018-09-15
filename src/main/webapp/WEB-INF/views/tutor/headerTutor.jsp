<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
(function($){

	  $.extend({
	    playSound: function(){
	      return $("<embed src='"+arguments[0]+".mp3' hidden='true' autostart='true' loop='false' class='playSound'>" + "<audio autoplay='autoplay' style='display:none;' controls='controls'><source src='"+arguments[0]+".mp3' /><source src='"+arguments[0]+".ogg' /></audio>").appendTo('body');
	    }
	  });

	})(jQuery);
var soundurl='<%=request.getContextPath()%>/sound/beep';
var soundAlertCount="0";
var sessionAlertCount="0";
var newChatMessageAlertCount="0";
var newreviewRelationSize="0";
var newassignedReviewRelationSize="0";
</script>


 <header class="hdr-top">
    <div class="hdrTxt">
      <div class="logo"><a href="<%=request.getContextPath()%>/tutor/home"><img src="<%=request.getContextPath()%>/images/Final.png" alt=""></a></div>
      <div class="hdr-top-rt">
        <div class="hello-ac">
       
          <p class="fl top-ellipsis"><strong><spring:message code="student.hello" /></strong>, ${user} </p> 
          <a id="signOut" href="<c:url value="/j_spring_security_logout?name=tutor" />" class="hdr-signout fl"><spring:message code="parent.signout" /></a>
          <span class="img" id="profileImg">
          
          
         <%--  <c:if test="${myImage !=''}">
          	 <img src="<%=request.getContextPath()%>/${userId}/fileupload/${userId}${imageName}" alt="1"> 
          </c:if>
          <c:if test="${dtoTutorDetails.imageName != ''}">
          	<img src="<%=request.getContextPath()%>/${userId}/fileupload/${userId}${imageName}" alt="">
          </c:if>
          <c:if test="${fbImage != null && myImage ==null}">
          	<img alt="2" src="${fbImage}"/>
          </c:if> 
          <c:if test="${myImage ==null && fbImage == null}">
          	 <img src="<%=request.getContextPath()%>/images/default-img.png" alt="1"> 
          </c:if> --%>
          
          
          
          	
                   
          </span>
          
          <div class="h-stars" id="ratingTutor"> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-yellow.png" alt=""> <img src="<%=request.getContextPath()%>/images/star-grey.png" alt=""> </div>
        
        </div>
        
         <%-- <div class="hdr-chatBtn">
         <a href="<%=request.getContextPath()%>/tutor/tutorCustomerChat"  class="blueBtn-normal"><spring:message code="chatWithSupport" /> </a>
         
        
         </div> --%>
    
       <%--  
       Do Not Delete This OLD functionality for Chat support
       <div class="support" id="support">
                    <div class="support-call"><a href="<%=request.getContextPath()%>/tutor/tutorCustomerChat"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>
                    <div class="ques">
                    <a href="<%=request.getContextPath()%>/tutor/tutorCustomerChat">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a>
                    	<div class="msg"></div>
                    </div>
                </div> --%>
                
        <!--  <div class="support" id="support"> --> 
                <!--   <div class="support-call"><a href="http://soporte.aloprofe.com/" target="_blank"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div> -->  
                   <!--  <div class="ques"> -->    
                <!-- <a href="http://soporte.aloprofe.com/" target="_blank">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a> -->    
                    	<!-- <div class="msg"></div> -->
               <!--      </div>  -->
                <!-- </div>   -->    
    
    
      </div>
    </div>
  </header>
  <div class="mins-bal-row">
    <div class="inner-row">
    
        	<div class="mins-bal-lt">
        	 <spring:message code="tutor.balance" /> : <span class="currencyType"></span> <strong id="tutorBalance"> </strong>
            </div>
    
      <nav class="rt-nav rt-nav2">
        <ul>
          <li><a href="<%=request.getContextPath()%>/tutor/home" class="home"><spring:message code="tutor.myhome" /></a><span id="chatFlag" style="display: none;">!</span></li>
          <li><a href="<%=request.getContextPath()%>/tutor/tutorManageProfile" class="my-ac"><spring:message code="my.info" /> </a></li>
          <li><a href="<%=request.getContextPath()%>/tutor/tutorActivities" class="nav-activity"><spring:message code="tutor.myactivity" /></a></li>
          <li><a href="<%=request.getContextPath()%>/tutor/tips" class="nav-tips"><spring:message code="tutor.tips" /></a></li>
        <!--   <li><a href="javascript:;" class="nav-tips"><spring:message code="tutor.myhome" />Tips</a></li> -->
          <li><a href="<%=request.getContextPath()%>/tutor/messages" class="msg"><spring:message code="student.messages" /></a> <span id="messageCount" style="display: none;"></span></li>
          <li><a href="<%=request.getContextPath()%>/tutor/tutorSession" class="mis-sessions"><spring:message code="student.misSessions" /></a></li>
           <li><a href="<%=request.getContextPath()%>/tutor/virtualClassroom" class="virtual-classroom"><spring:message code="virtualClassRoom" /></a></li>
          
        </ul>
      </nav>
    </div>
  </div>
  
  
  <script type="text/javascript">
  setInterval(function(){
	  var soundNotificationFlag ='0';
		var url='<%=request.getContextPath()%>/tutor/getMessageCount';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response){
				if(response != null){
					if(response.modelMap.messageSize=='0'){
						$("#messageCount").text("");
						$("#messageCount").hide();
					}
					else
						{
						$("#messageCount").show();
						$("#messageCount").text(response.modelMap.messageSize);
						if(soundAlertCount<response.modelMap.messageSize){
						//	$.playSound(soundurl);
							soundAlertCount=response.modelMap.messageSize;
							soundNotificationFlag='1';
						}
						}
					
						$("#tutorBalance").text(response.modelMap.tutorBalance);
						
						
						
						if(response.modelMap.newChatMessageSize!=null && response.modelMap.newChatMessageSize>'0'){
							$("#chatFlag").show();
							$("#chatFlag").html(response.modelMap.newChatMessageSize);
							if(newChatMessageAlertCount<response.modelMap.newChatMessageSize){
							//	$.playSound(soundurl);
								newChatMessageAlertCount=response.modelMap.newChatMessageSize;
								soundNotificationFlag='1';
								notifyUser(); //####
							}
						}
						else{
							$("#chatFlag").hide();
							newChatMessageAlertCount=0; //####
						}
						
						//========= for review sesson notification=====
						if(response.modelMap.newChatMessageSize==null || response.modelMap.newChatMessageSize=='0'){
						if(response.modelMap.reviewRelationSize!=null && response.modelMap.reviewRelationSize>'0'){
							$("#chatFlag").show();
							$("#chatFlag").html(response.modelMap.reviewRelationSize);
							if(newreviewRelationSize<response.modelMap.reviewRelationSize){
							//	$.playSound(soundurl);
								newreviewRelationSize=response.modelMap.reviewRelationSize;
								soundNotificationFlag='1';
							}
						}
						else{
							$("#chatFlag").hide();
							newreviewRelationSize=0; //####
						}	
								}
					
						//========= for student accepted/approved review sesson notification=====
						if(response.modelMap.newChatMessageSize==null || response.modelMap.newChatMessageSize=='0'){
						if(response.modelMap.reviewRelationSize==null || response.modelMap.reviewRelationSize=='0'){
							if(response.modelMap.assignedReviewRelationSize!=null && response.modelMap.assignedReviewRelationSize>'0'){
							$("#chatFlag").show();
							$("#chatFlag").html(response.modelMap.assignedReviewRelationSize);
							if(newassignedReviewRelationSize<response.modelMap.assignedReviewRelationSize){
							//	$.playSound(soundurl);
								newassignedReviewRelationSize=response.modelMap.assignedReviewRelationSize;
								soundNotificationFlag='1';
							}
						}
						else{
							$("#chatFlag").hide();
							newassignedReviewRelationSize=0; //####
						}	
								}
				}
						
						
						
						
						if(soundNotificationFlag=='1'){
							$.playSound(soundurl);
						}	
				}
				else{
					location.reload();
				}
			}
		});  
	    
	    
	}, 3000);
  
  $(document).ready(function(){
	  
	  var ratingStar="";
	  
	  var url='<%=request.getContextPath()%>/tutor/getRatings';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response){
					
					if(response==0){
						for(var i=1;i<=5;i++){
						ratingStar+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
						}
					}
					else
						{
						for(var i=1;i<=5;i++){
							
							if(i <= response){
								
								ratingStar+='<img src="<%=request.getContextPath()%>/images/star-yellow.png" alt="">';
							}
							else{
								ratingStar+='<img src="<%=request.getContextPath()%>/images/star-grey.png" alt="">';
							}
							
						}
						}
					$("#ratingTutor").html("");
					$("#ratingTutor").html(ratingStar);
			}
		});  

		
		  var url='<%=request.getContextPath()%>/tutor/getTutorImage';
		  var profileImage = "";
			$.ajax({
				url:url,
				method:'GET',
				async :false,
				success:function(response){
					
					if(response.modelMap.loginStatus=='N'){
						window.location.href = "/j_spring_security_logout?name=tutor";
					}
						if(response.modelMap.myImage!=null){
							profileImage+=   '<img alt="" src="<%=request.getContextPath()%>/profilePictures/'+response.modelMap.tutorImageUserId+'/fileupload/'+response.modelMap.tutorImageUserId+response.modelMap.myImage+'"/>';
								                }	

						if(response.modelMap.myImage==null && response.modelMap.fbImage==null){
							profileImage+=   '<img alt="" src="<%=request.getContextPath()%>/images/default-img.png"/>';
								                }

						if(response.modelMap.myImage==null && response.modelMap.fbImage!=null){
							profileImage+=   '<img alt="" src="'+response.modelMap.fbImage+'"/></div>';				
						}					
					
					$("#profileImg").html(profileImage);
					
					
					
					
					
				}
			  });
  
  });
  
  
  
  $('#signOut').click(function(e) {
	  $("#signOut").attr('disabled','disabled');
	});
  
  
<%--   $('#support').click(function() {  
	  window.location.href = "<%=request.getContextPath()%>/tutor/tutorCustomerChat";
	}); --%>
  
  
  
//========== get tutor currency type by its country=======
  var url='<%=request.getContextPath()%>/tutor/getCurrencyType';
$.ajax({
url:url,
method:'GET',
async :false,
success:function(response){
var currencyType = response.modelMap.currencyType;
		if(currencyType=="US"){
			$(".currencyType").text("US $");
		}
		else if(currencyType=="EURO"){
			$(".currencyType").text('\u20AC');
		}
		else if(currencyType=="MXN"){
			$(".currencyType").text("$");
		}
	
}
}); 


//-----------------------------------------------------------
</script>

<script type="text/javascript">

var idleTime = 0;
$(document).ready(function () {
    //Increment the idle time counter every minute.
    var idleInterval = setInterval(timerIncrement, 120000); // 6 seconds

    //Zero the idle timer on mouse movement.
   $(this).mousemove(function (e) {
	
        idleTime = 0;
   });
    $(this).keypress(function (e) {
	
        idleTime = 0;
    });
});

function timerIncrement() {
    idleTime = idleTime + 1;
    if (idleTime >= 35) { 
       //alert("idle");
   // $("#signOut").click();
   // $("#signOut").trigger("click");
    window.location.href = "/j_spring_security_logout?name=tutor";
    }
}


</script> 




