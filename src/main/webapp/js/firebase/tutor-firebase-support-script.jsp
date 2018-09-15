<style type="text/css">
           .popup-box
            {
                display: none;
                position: fixed;
                bottom: 58px;
                right: 220px;
                height: 250px;
                background-color: rgb(237, 239, 244);
                width: 322px;
                border: 1px solid rgba(29, 49, 91, .3);
            }
            
            .popup-box .popup-head
            {
                background-color: #0060a8;
                padding: 5px;
                color: white;
                font-weight: bold;
                font-size: 14px;
                clear: both;
            }
            
            .popup-box .popup-head .popup-head-left
            {
                float: left;
            }
            
            .popup-box .popup-head .popup-head-right
            {
                float: right;
                opacity: 0.5;
                 margin-top: -3px;
            }
            
            .popup-box .popup-head .popup-head-right a
            {
                text-decoration: none;
                color: inherit;
            }
            
            .popup-box .popup-messages
            {
                height: 100%;
            }
            
            
            .chat {
    width: 400px;
}

.bubble{
    background-color: #F2F2F2;
    border-radius: 5px;
    box-shadow: 0 0 6px #B2B2B2;
    display: inline-block;
    padding: 10px 18px;
    position: relative;
    vertical-align: top;
}

.bubble::before {
    background-color: #F2F2F2;
    content: "\00a0";
    display: block;
    height: 16px;
    position: absolute;
    top: 11px;
    transform:             rotate( 29deg ) skew( -35deg );
        -moz-transform:    rotate( 29deg ) skew( -35deg );
        -ms-transform:     rotate( 29deg ) skew( -35deg );
        -o-transform:      rotate( 29deg ) skew( -35deg );
        -webkit-transform: rotate( 29deg ) skew( -35deg );
    width:  20px;
}

.me {
    float: left;   
    margin: 5px 45px 5px 20px;       
   /*background-color: #C4C4C4;  */
   background-color: #dadada; 
  max-width: 228px;
  width:228px;
  overflow: hidden;
  word-break: break-word;
}

.me::before {
    box-shadow: -2px 2px 2px 0 rgba( 178, 178, 178, .4 );
    left: -9px;       
    background-color: #C4C4C4; display: none;    
}

.you {
    float: right;    
    margin: 5px 20px 5px 45px;    
      max-width: 228px;
  overflow: hidden;
  word-break: break-word;     
}

.you::before {
    box-shadow: 2px -2px 2px 0 rgba( 178, 178, 178, .4 );
    right: -9px;    
}

</style>



<script type="text/javascript">
//this function can remove a array element.
Array.remove = function(array, from, to) {
    var rest = array.slice((to || from) + 1 || array.length);
    array.length = from < 0 ? array.length + from : from;
    return array.push.apply(array, rest);
};

//this variable represents the total number of popups can be displayed according to the viewport width
var total_popups = 1;

//arrays of popups ids
var popups = [];

//this is used to close a popup
function close_popup(id,chatSessionId,action)
{
	$("#"+id).css("display", "none");
    endChatSession(chatSessionId,action);
  
}

//displays the popups. Displays based on the maximum number of popups that can be displayed on the current viewport width
var right = 220;
var resetPostion=0;
function display_popups()
{
	 
	/* if(resetPostion==4){
		right = 220;
		resetPostion=0;
    } */
	var right = 220;
	
    var iii = 0;
     for(iii; iii < total_popups; iii++)
    {
        if(popups[iii] != undefined)
        {
        	if(popups[iii]==tutId){
            var element = document.getElementById(popups[iii]);
            element.style.right = right + "px";
            right = right + 320;
            element.style.display = "block";
            resetPostion++;
        	}
        }
    } 
    
    for(var jjj = iii; jjj < popups.length; jjj++)
    {
        var element = document.getElementById(popups[jjj]);
        element.style.display = "none";
    }
}

//creates markup for a new popup. Adds the id to popups array.
var tutId="0";
var studentId='${tutorId}';
var userTutorId='${tutorId}';

function register_popup(id, name,sessionId)
{
	var chatSatus="N";
	if($("#"+id).length!=0){
		$("#"+id).css("display", "block"); 
	} 
	
//	$("#justtest").val(id);
	tutId=id;
     for(var iii = 0; iii <= popups.length; iii++)
    {   
        //already registered. Bring it to front.
         if(id == popups[iii])
        {
        	 
        	 var url='<%=request.getContextPath()%>/tutor/markChatStatusRead';
        	 $.ajax({
        	 	url:url,
        	 	method:'GET',
        	 	async :false,
        	 	data:{tutorId:userTutorId,studentId1:id},
        	 	success:function(response){
        	 	}
        	     });
        	 
            Array.remove(popups, iii);
            popups.unshift(id);
            calculate_popups();
            
            return;
        } 
    }    
    
    	
   // 	$("#chatTutor"+id).removeAttr('href');
    //	$("#chatTutor"+id).css("background-color", "#B2B3B2");
    	
    	  
    	if($("#"+id).length==0){
    var element = '<div class="popup-box chat-popup" id="'+ id +'">';
    element = element + '<div class="popup-head">';
    element = element + '<div class="popup-head-left">'+ name +'</div>';
    element = element + '<div class="popup-head-right"> <a class="minimize_chat" href="javascript:close_popup(\''+ id +'\','+sessionId+',\'minimize\');">_</a> <a href="javascript:close_popup('+ id +','+sessionId+',\'close\');">&#10005;</a></div>';
    
    element = element + '<div style="clear: both"></div></div><div class="popup-messages">';
    
    element = element + ' <div class="db-chats-txt">'+ 
	'<div class="chat-inner" id="chatin'+studentId+'-'+id+'"><div id="position:relative;">'+ 
       ' </div> <div id="position:relative;"></div></div><div class="chat-msg">'+ 
       ' <div class="chat-msg-txt"><input type="text" class="trying" id="chatMsg'+studentId+'-'+id+'"  placeholder="<spring:message code="type.something" /> " >'+ 
       ' <a href="javascript:;" class="chat-msg-submit" onclick="sendMsg('+studentId+'-'+id+');" ><spring:message code="parent.submit" /></a> </div>'+ 
        '<div class="clr"></div> </div></div></div></div>';
    
    chatSatus="Y";
    	}
    	
    	document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element; 
    	popups.unshift(id);
    
    	if($("#"+id).length!=0){
    		calculate_popups();
    	} 
    	
    	openchatwind(id,chatSatus);
}

//calculate the total number of popups suitable and then populate the toatal_popups variable.
function calculate_popups()
{
    var width = window.innerWidth;
  /*   if(width < 540)
    {
        total_popups = 0;
    }
    else
    {
        width = width - 200;
        //320 is width of a single popup box
        total_popups = parseInt(width/320);
    } */
    
    display_popups();
    
}

function endChatSession(chatSessionId,action){
	var url='<%=request.getContextPath()%>/tutor/EndTutorHomeChatSession';
	$.ajax({
		url:url,
		method:'GET',
		async :false,
		data:{chatSessionId:chatSessionId,action:action},
		success:function(){
		}
	});

	}
</script>


<script type="text/javascript" src='https://cdn.firebase.com/js/client/2.2.1/firebase.js'></script>

<script type="text/javascript">

function sendMsg(fixit){ 
$( ".trying" ).keypress();
var text = $('#chatMsg'+fixit).val();
// // console.log("Text:", text);
if(text!=null){
window['newUserRef' + supportId ].push({name: "tutor", text: text});
$('#chatMsg'+fixit).val('');



var name = '${userFname}';
var elementIdVal = fixit;
var tutorUserId = elementIdVal.substring(0, elementIdVal.indexOf('-'));
var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+tutorUserId +':'+supportUserId);
//  var text = $('#'+elementIdVal).val();

var nowDate = new Date();
    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
    var text = messageTime+"]: "+$('#'+elementIdVal).val();

window['newUserRef' + supportId ].push({name: name, text: text});

$('#'+elementIdVal).val('');  

var url='<%=request.getContextPath()%>/tutor/requestSupportChat';
$.ajax({
url:url,
method:'GET',
async :false,
data:{supportId:supportId},
success:function(response){
}
});

}

} 


var tutorId1="0";
var supportId = "0";
var newUserRef ="";
function openchatwind(supportId,chatSatus){
tutorId1='${tutorId}';
$("#justtest").val(supportId);


<%-- var url='<%=request.getContextPath()%>/tutor/requestSupportChat';
$.ajax({
url:url,
method:'GET',
async :false,
data:{supportId:supportId},
success:function(response){

}
}); --%>
var url='<%=request.getContextPath()%>/tutor/markChatStatusRead';
$.ajax({
	url:url,
	method:'GET',
	async :false,
	data:{tutorId:tutorId1,studentId1:supportId},
	success:function(response){
	}
    });


// ajax ends
//complete:function(){

var posfix =tutorId1+"-"+supportId;



if(chatSatus=='Y'){
window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+ tutorId1 +':'+ supportId);

window['newUserRef' + supportId ].on('child_added', function(DataSnapshot) {
  // console.log("Name of snapshot is",DataSnapshot.key().toString());
var message = DataSnapshot.val();
displayChatMessage(message.name, message.text);

});

var myDataRef = new Firebase('https://aloprofe.firebaseio.com');

var stuId = '${tutorId}';
myDataRef.authWithPassword({
	 email    : '${firebaseUser}',
	 password : '${firebasePass}'
	}, function(error, authData) {
	  if (error) {
	    // console.log("Login Failed!", error);
	  } else {
	    // console.log("Authenticated successfully with payload:", authData);
	    // console.log("Token", authData.token);
	    // console.log("UserId is :",authData.uid);
	    authData3=authData;
	 
	  }},
	  {
		  remember:"sessionOnly"
	  
	  
	}); 

var postFix = posfix;

$('.trying').keypress(function (e) {
  var textBoxId = $(e.target).attr("id");
  var msgVal = $("#"+textBoxId).val();
    if ((e.keyCode == 13 || typeof e.which === "undefined") && msgVal.trim()!="") {
      var name = '${userFname}';
      var elementIdVal = $(e.target).attr("id");
      var tutorUserId = elementIdVal.substring(7, elementIdVal.indexOf('-'));
      var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
      
	  window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+tutorUserId +':'+supportUserId);
    //  var text = $('#'+elementIdVal).val();
     
    var nowDate = new Date();
    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
    var text = messageTime+"]: "+$('#'+elementIdVal).val();
    
    window['newUserRef' + supportId ].push({name: name, text: text});
   
      $('#'+elementIdVal).val('');  
      
      var url='<%=request.getContextPath()%>/tutor/requestSupportChat';
  	$.ajax({
  		url:url,
  		method:'GET',
  		async :false,
  		data:{supportId:supportId},
  		success:function(response){
  		}
  	    });
    
    }
  });

function displayChatMessage(name, text) {

if(name=='${userFname}'){
    $('<div class="bubble me" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
    $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;
	}
	else{
		$('<div class="bubble you" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;	
	}


};


}

if(chatSatus=='N'){
window['newUserRef' + supportId ].on('child_added', function(DataSnapshot) {
  // console.log("Name of snapshot is",DataSnapshot.key().toString());
var message = DataSnapshot.val();
displayChatMessage(message.name, message.text);

});


var myDataRef = new Firebase('https://aloprofe.firebaseio.com');

var stuId = '${tutorId}';
myDataRef.authWithPassword({
	 email    : '${firebaseUser}',
	 password : '${firebasePass}'
	}, function(error, authData) {
	  if (error) {
	    // console.log("Login Failed!", error);
	  } else {
	    // console.log("Authenticated successfully with payload:", authData);
	    // console.log("Token", authData.token);
	    // console.log("UserId is :",authData.uid);
	    authData3=authData;
	 
	  }},
	  {
		  remember:"sessionOnly"
	  
	  
	}); 



var postFix = posfix;


$('.trying').keypress(function (e) {
  var textBoxId = $(e.target).attr("id");
  var msgVal = $("#"+textBoxId).val();
    if ((e.keyCode == 13 || typeof e.which === "undefined") && msgVal.trim()!="") {
      var name = '${userFname}';
      var elementIdVal = $(e.target).attr("id");
      var tutorUserId = elementIdVal.substring(7, elementIdVal.indexOf('-'));
      var supportUserId = elementIdVal.substring(elementIdVal.indexOf('-')+1,elementIdVal.length);
	  window['newUserRef' + supportId ]=new Firebase('https://aloprofe.firebaseio.com/users/'+tutorUserId +':'+supportUserId);
   //   var text = $('#'+elementIdVal).val();
     
   var nowDate = new Date();
    var messageTime = nowDate.getDate() + '/' + (nowDate.getMonth()+1) + '/' + nowDate.getFullYear() + ' ' + nowDate.getHours() + ':' + nowDate.getMinutes() + ':' + nowDate.getSeconds();
    var text = messageTime+"]: "+$('#'+elementIdVal).val();
   
   window['newUserRef' + supportId ].push({name: name, text: text});
   
      $('#'+elementIdVal).val('');  
      
      var url='<%=request.getContextPath()%>/tutor/requestSupportChat';
  	$.ajax({
  		url:url,
  		method:'GET',
  		async :false,
  		data:{supportId:supportId},
  		success:function(response){
  		}
  	    });
      
      
    
    }
  });


function displayChatMessage(name, text) {
if(name=='${userFname}'){
    $('<div class="bubble me" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
    $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;
	}
	else{
		$('<div class="bubble you" />').text(text).prepend($('<em/>').text(name+' [')).appendTo($('#chatin'+postFix));
        $('#chatin'+postFix)[0].scrollTop = $('#chatin'+postFix)[0].scrollHeight;	
	}
};


}	   


//}  ////complete ends



//}); // ajax ends
}
          
 </script>