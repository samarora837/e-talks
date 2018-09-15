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
var newChatMessageAlertCount="0"; //####
</script>
  
  
  
  <header class="hdr-top">
    <div class="hdrTxt">
      <div class="logo"><a href="<%=request.getContextPath()%>/parent/home"><img src="<%=request.getContextPath()%>/images/Final.png" alt=""></a></div>
      <div class="hdr-top-rt">
        <div class="hello-ac"> 
        	<p class="fl top-ellipsis"><strong><spring:message code="parent.hello" /></strong>, ${user}</p>
        	<a id="signOut" href="<c:url value="/j_spring_security_logout?name=parent" />" class="hdr-signout"><spring:message code="parent.signout" /></a>
        </div>
        
            <%-- <div class="support" id="support">
                    <div class="support-call"><a href="<%=request.getContextPath()%>/parent/parentCustomerChat"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>
                    <div class="ques">
                    <a href="<%=request.getContextPath()%>/parent/parentCustomerChat">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a>
                    	<div class="msg"></div>
                    </div>
                </div> --%>
                
                    <!--       <div class="support" id="support">
                    <div class="support-call"><a href="http://soporte.aloprofe.com/" target="_blank"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>
                    <div class="ques">  -->
                    <!-- <a href="http://soporte.aloprofe.com/" target="_blank">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a> -->
                  <!--  	<div class="msg"></div> --> 
                <!--     </div> --> 
               <!--  </div>  --> 
        
     
      </div>
    </div>
  </header>
  
  
  <div class="mins-bal-row">
  	<div class="inner-row">
        <nav class="rt-nav">
        	<ul>
            	<li><a href="<%=request.getContextPath()%>/parent/home" class="home"><spring:message code="parent.hometext" /></a><span id="chatFlag" style="display: none;">!</span></li>
                <li><a href="<%=request.getContextPath()%>/parent/myAccount" class="my-ac"> <spring:message code="parent.myAccount" /></a></li> 
                <li><a href="<%=request.getContextPath()%>/parent/messages" class="msg"><spring:message code="parent.messages" /></a><span id="messageCount" style="display:none;"></span></li>
                <li><a href="<%=request.getContextPath()%>/parent/sessionManage" class="mis-sessions"><spring:message code="parent.misSessions" /></a></li>
              <li><a href="<%=request.getContextPath()%>/parent/manageStudent" class="nav-sessions"><spring:message code="header.managestudent"/></a></li>
            </ul>
        </nav>
    </div>
  </div>
  <script type="text/javascript">
  
  
  <%-- $('#support').click(function() {  
	  window.location.href = "<%=request.getContextPath()%>/parent/parentCustomerChat";
	}); --%>
  
  setInterval(function(){
	  var soundNotificationFlag ='0'; //####
		var url='<%=request.getContextPath()%>/parent/getMessageCount';
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
						
						if(soundAlertCount!=response.modelMap.messageSize){
							$.playSound(soundurl);
							soundAlertCount=response;
						}
						}
					
					if(response.modelMap.newChatMessageSize!=null && response.modelMap.newChatMessageSize>'0'){
						$("#chatFlag").show();
						$("#chatFlag").html(response.modelMap.newChatMessageSize);
						if(newChatMessageAlertCount<response.modelMap.newChatMessageSize){
						//	$.playSound(soundurl);
							newChatMessageAlertCount=response.modelMap.newChatMessageSize;
							soundNotificationFlag='1';
							notifyUser();  //####
						}
					}
					else{
						$("#chatFlag").hide();
						newChatMessageAlertCount=0; //####
					}	
					
					if(soundNotificationFlag!='0'){ //####
						$.playSound(soundurl); //####
					}			//####
				
				}
			}
		});  
	    
	    
	}, 2000);
  
  $('#signOut').click(function(e) {
	  $("#signOut").attr('disabled','disabled');
	});
  

  
  
</script>

<script type="text/javascript">

var idleTime = 0;
$(document).ready(function () {
	
	
	var url='<%=request.getContextPath()%>/parent/setMeActive';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		success:function(response){
			
			
		}
	}); 
	
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
   // $("#signOut").click();
   // $("#signOut").trigger("click");
    window.location.href = "/j_spring_security_logout?name=parent";
    }
}





</script> 
  
  