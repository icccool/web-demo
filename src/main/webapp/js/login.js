$(document).ready(function(){
    $('#loginForm').bind('submit', function(){
    	function successInit(){
    			var i=0;
        		function ajaxFun(){
        			try{
	        			$.ajax({
	    					async: true,//同步
	    			        url:outURL[i] + "/init/session.do",
	    			        timeout: 2000,
	    			        dataType:"jsonp",
	    			        jsonp:"jsonpcallback",
	    			        data: {"sessionId":sessionId},
	    			        success:function(data){
	    			        },
	    			        error:function(data,textstatus){
	    				     },
	    				     complete:function(XMLHttpRequest, textStatus){
	    				        	if(i<outURL.length-1){
	    					        	i++;
	    					        	ajaxFun();
	    				        	}else{
	    				        		window.location.href = getFullURL("/common/main");
	    				        	}
	    				     }
	    				});
        			} catch(e) {
        				if(i<outURL.length-1){
				        	i++;
				        	ajaxFun();
			        	}else{
			        		window.location.href = getFullURL("/common/main");
			        	}
        	    	}
        		}
        		ajaxFun();
    	}
    	var dataPara = getFormJson(this);
    	$.ajax({
    		url: getFullURL("j_spring_security_check"),
    		type: 'POST',
    		data: dataPara,
    		success: function(data) {
    			if (data != "success") {
    				$("#errorMessage").show();
    				$("#errorMessage").html(data);
    				changeVerifyCode();
    			} else {
    				$(".loginBtn").prop("disabled",true);
    				successInit();
    			}
    		},
    		error: function(data) {
    			$("#errorMessage").html("系统异常,请联系管理员");
    		}
    	});
    	return false;
    });
    $("#j_username").bind('focus',function(){
    	$("#errorMessage").html("");
    });
    $("#j_password").bind('focus',function(){
    	$("#errorMessage").html("");
    });
    $("#veryCode").bind('focus',function(){
    	$("#errorMessage").html("");
    });
});

function getFormJson(frm) {
	var o = {};
	var a = $(frm).serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});

	return o;
}

function changeVerifyCode(){
	$("#verifyCodeImgId").attr('src',getFullURL('servlet/VerifyCodeServlet?t=' + (new Date()).getTime())); 
}

function clear(){
	$("#username").val("");
	$("#password").val("");
	$("#veryCode").val("");
}

function login(){
	$("#info").html("");
	var username=$("#j_username").val();
	if(username==""){ 
		showMessage("请输入用户名");  
		return ; 
	}		
	var password=$("#j_password").val();
	if(password==""){ 
		showMessage("请输入密码");  
		return ; 
	}
	var code = $("#veryCode").val();
	if(code==""){ 
		showMessage("请输入验证码");  
		return ; 
	}	
	isRightCode(username,password);
}

function isRightCode(username,password){     
    var code = $("#veryCode").val();           
    $.ajax({     
        type:"POST",     
        url:"resultServlet/validateCode",     
        data:{'c':code},
        dataType:"text",     
        success:function(data){
            if($.trim(data) =='999'){
                showMessage("验证码错误");
                $("#veryCode").val("");                
            }else{
            	formsubmit(username,password);
            }
        }     
    });     
}     

function showMessage(message){     
 //   $("#info").html("<font color='red'>"+data+"</font>");  
	diaAlert("warning",message);    
}  

