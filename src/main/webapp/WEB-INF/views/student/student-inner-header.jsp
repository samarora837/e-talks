<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <meta property="og:site_name" content="http://aloprofe.com">
  <meta property="og:title" content="AloProfe">
  <meta property="og:description" content="Welcome To Aloprofe">
  <meta property="og:type" content="website">
  <meta property="og:image" content="http://www.aloprofe.com/images/Final.png">
    <meta property="og:url" content="-CUSTOMER VALUE-">
  
<div id="fb-root"></div>
<script type="text/javascript">(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.3";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script> 

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
var newreviewProposedSize="0";
var newreviewCompletedSize="0";
var showFlag="0";
</script>



    <header class="hdr-top">
    <div class="hdrTxt">
      <div class="logo"><a href="<%=request.getContextPath()%>/student/home"><img src="<%=request.getContextPath()%>/images/Final.png" alt=""></a></div>
      <div class="hdr-top-rt">
        <div class="hello-ac"> 
        	<p class="fl top-ellipsis"><strong><spring:message code="student.hello" /></strong>, ${user}</p>
        	<a  id="signOut" href="<c:url value="/j_spring_security_logout?name=student" />" class="hdr-signout"><spring:message code="student.signout" /></a>
           <!--  <span class="img"><img src="images/img-small.png" alt=""></span> -->
        </div>
        
       <%--  <div class="support" id="support">
                    <div class="support-call"><a href="<%=request.getContextPath()%>/student/customerSupportList"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>
                    <div class="ques">
                    <a href="<%=request.getContextPath()%>/student/customerSupportList">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a>
                    	<div class="msg"></div>
                    </div>
                </div> --%>
      
       <!--    <div class="support" id="support">
                    <div class="support-call"><a href="http://soporte.aloprofe.com/" target="_blank"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>
                    <div class="ques"> -->
                <!--  <a href="http://soporte.aloprofe.com/" target="_blank">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a>-->
              <!--      <<div class="msg"></div>  --> 
               <!--      </div> --> 
             <!--    </div>  --> 
      </div>
    </div>
  </header>
  
  <div class="mins-bal-row">
  	<div class="inner-row">
    	<div class="mins-bal-lt">
        	<!-- <spring:message code="student.minuteBalance" /> : <strong id="studentBalance">${minBalance} </strong><strong> <spring:message code="mins"/></strong>-->
            <a href="<%=request.getContextPath()%>/student/changePlan" class="chg-planBtn"><spring:message code="student.changePlaneOrBuy" /></a>
        </div>
        <nav class="rt-nav">
        	<ul>
            	<li><a href="<%=request.getContextPath()%>/student/home" id="homelink" class="home"><spring:message code="parent.hometext" /></a><span id="chatFlag" style="display: none;">!</span></li>
                <li><a href="<%=request.getContextPath()%>/student/myAccount" class="my-ac"><spring:message code="parent.myAccount" /></a></li>
                <li><a href="<%=request.getContextPath()%>/student/messages" class="msg"><spring:message code="parent.messages" /></a> <span id="messageCount" style="display: none;"></span></li>
                <li><a href="<%=request.getContextPath()%>/student/sessionManage" class="mis-sessions"><spring:message code="parent.misSessions" /></a><span id="sessionFlag" style="display: none;">!</span></li>
                <li><a href="<%=request.getContextPath()%>/student/manageParent" class="nav-sessions"><spring:message code="header.manageparents" /></a></li>
                <li><a href="<%=request.getContextPath()%>/student/studentFavouriteAndSuggestedtutors" class="fav"><spring:message code="myfavourite.tutor" /></a></li>
                <li><div class="fb-share-button fbBtn" data-href="http://www.aloprofe.com/cms/howitworks" data-layout="button_count"></div></li> 
            </ul>
        </nav>
    </div>
  </div>
  
  <script type="text/javascript">
  $("#messageCount").hide();
  $("#sessionFlag").hide();
  
  
//============call from server side  to replace recursive ajax request STARTS ===============
<%--   var url='<%=request.getContextPath()%>/student/checkNewMessageRequest';
  var eventSource = new EventSource(url); --%>
 /*  eventSource.onmessage = function(event) {
  	if(event.data=="Y"){
  		getNewMessageDetailsStatus();
  	}
  	
  }; */
  
/*   eventSource.addEventListener('Y', function(event) {
	  getNewMessageDetailsStatus();
  }, false);
  
 eventSource.addEventListener('N', function(event) {
	  
  }, false); */
  
  //============ call from server side  to replace recursive ajax request ENDS ===============
  
  
  
    
  setInterval(function(){
	  var soundNotificationFlag ='0';
		var url='<%=request.getContextPath()%>/student/getMessageCount';
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
				
					if(response.modelMap.newSessionFlag!=null){
						$("#sessionFlag").show();
						$("#sessionFlag").html(response.modelMap.newSessionFlag);
						if(sessionAlertCount<response.modelMap.newSessionFlag){
						//	$.playSound(soundurl);
							sessionAlertCount=response.modelMap.newSessionFlag;
							soundNotificationFlag='1';
						}
						showFlag='1';
					}
					else{
					//	$("#sessionFlag").hide();
					}
					
					
					//========= for review sesson notification=====
					if(response.modelMap.newSessionFlag==null){
					if(response.modelMap.reviewRelationSize!=null && response.modelMap.reviewRelationSize>'0'){
						$("#sessionFlag").show();
						$("#sessionFlag").html(response.modelMap.reviewRelationSize);
						if(newreviewRelationSize<response.modelMap.reviewRelationSize){
						//	$.playSound(soundurl);
							newreviewRelationSize=response.modelMap.reviewRelationSize;
							soundNotificationFlag='1';
						}
						showFlag='1';
					}
					else{
					//	$("#sessionFlag").hide();
						newreviewRelationSize=0; //####
					}	
				}
					//========= for review sesson notification=====
					if(response.modelMap.newSessionFlag==null){
					if(response.modelMap.reviewProposedSize!=null && response.modelMap.reviewProposedSize>'0'){
						$("#sessionFlag").show();
						$("#sessionFlag").html(response.modelMap.reviewProposedSize);
						if(newreviewProposedSize<response.modelMap.reviewProposedSize){
						//	$.playSound(soundurl);
							newreviewProposedSize=response.modelMap.reviewProposedSize;
							soundNotificationFlag='1';
						}
						showFlag='1';
					}
					else{
					//	$("#sessionFlag").hide();
						newreviewProposedSize=0; //####
					}	
				}
					
					//========= for completed/delivered review sesson notification=====
					if(response.modelMap.newSessionFlag==null){
					if(response.modelMap.reviewCompletedSize!=null && response.modelMap.reviewCompletedSize>'0'){
						$("#sessionFlag").show();
						$("#sessionFlag").html(response.modelMap.reviewCompletedSize);
						if(newreviewCompletedSize<response.modelMap.reviewCompletedSize){
						//	$.playSound(soundurl);
							newreviewCompletedSize=response.modelMap.reviewCompletedSize;
							soundNotificationFlag='1';
						}
						showFlag='1';
					}
					else{
					//	$("#sessionFlag").hide();
						newreviewCompletedSize=0; //####
					}	
				}
					
					if(showFlag!='1'){
						$("#sessionFlag").hide();
					}
					
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
					
					
					if(soundNotificationFlag!='0'){
						$.playSound(soundurl);
					}
					
					$("#studentBalance").text(response.modelMap.studentBalance);
					
				}
				else{
					location.reload();
				}
			}
		});  

	}, 5000); 

  
  
  $(document).ready(function(){
	  

		
		var url='<%=request.getContextPath()%>/student/setMeActive';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response){
				
				
			}
		}); 
	  
	  
		var url='<%=request.getContextPath()%>/getCustomerSupportList';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response){
			 	if(response != null){
					var customerSupport=" <option value='0'> Select Support</option>";
					
					 $.each( response.modelMap.dtoCustomerSupportDetails, function(index,value) {

							if(value.isOnline=='Y'){
								customerSupport+=	' <option value="'+value.supportUserId+', '+value.supportName+'">'+value.supportName+'</option>';
							}
					 });
					 $("#supportName").html(customerSupport);
			} 
			}
		});  
    });
  
  function chkval(supportDetails){
	  var id= supportDetails.substr(0, supportDetails.indexOf(','));
	  var name= supportDetails.substr(supportDetails.indexOf(',')+1, supportDetails.length);
	  register_popup(id,name,'support');
  }
  
  
  $('#signOut').click(function(e) {
	  $("#signOut").attr('disabled','disabled');
	});
  
  
<%--   $('#support').click(function() {  
	  window.location.href = "<%=request.getContextPath()%>/student/customerSupportList";
	}); --%>
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
    window.location.href = "/j_spring_security_logout?name=student";
    }
} 

</script> 

