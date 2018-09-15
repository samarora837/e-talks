<!DOCTYPE HTML>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<header class="hdr-top">
    	<div class="hdrTxt">
        	<div class="logo"><a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/Final.png" alt=""></a></div>
            <div class="hdr-top-rt">
            	<div class="top-login-signup">
                	<a href="<%=request.getContextPath()%>/cms/howitworks" class="works"><spring:message code="header.HowWorks" /></a>
                    
                    <a href="<%=request.getContextPath()%>/login" class="login"><spring:message code="header.login"/></a>
                     <a href="<%=request.getContextPath()%>/signup" class="signup"><spring:message code="header.signup"/></a>
                </div>
                
                
       <%--  <div class="support" id="support">
                    <div class="support-call"><a href="javascript:void(0);" onclick="sendMsg();"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>
                    <div class="ques">
                    <a href="javascript:void(0);" onclick="sendMsg();"><input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a>
                    	<div class="msg"></div>
                    </div>
                </div> --%>
                
                
                  <!-- <div class="support" id="support">  --> 
             <!--       <div class="support-call"><a href="http://soporte.aloprofe.com/" target="_blank"><img src="<%=request.getContextPath()%>/images/support-icon.png" alt=""></a></div>  --> 
                  <!--  <div class="ques">  --> 
         <!-- <a href="http://soporte.aloprofe.com/" target="_blank">	<input type="text" readonly="readonly" placeholder='<spring:message code="chatWithSupport" />'></a> -->
                    <!-- <div class="msg"></div>  -->	
              <!--  </div>  -->	
            <!--     </div>  -->     
               
                
                
            </div>
        </div>
    </header>
    
      <script type="text/javascript">
      $(document).ready(function(){
		var url='<%=request.getContextPath()%>/getCustomerSupportList';
		$.ajax({
			url:url,
			method:'GET',
			async :false,
			success:function(response){
			 	if(response != null){
					var customerSupport="";
					
					 $.each( response.modelMap.dtoCustomerSupportDetails, function(index,value) {

							if(value.isOnline=='Y'){
								customerSupport+=	' <option value="'+value.supportUserId+'">'+ value.supportName+'</option>';
							}
					 });
					 $("#supportName").html(customerSupport);
			} 
			}
		});  
      });
	    
      $('#support').click(function() {  
    	//  sendMsg();
    	});
      
</script>