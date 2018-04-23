var menu_order='';
document.domain = 'pazt.com.cn';
$(function(){
	init();
	initAgreeTerm();
})
window.onload=function(){
	windowSize();
}
function init(){
	leftSlide();
	leftBtn();
	//windowSize();
	window.tabScroll=new tabBarScroll();
}

//左侧下拉效果
function leftSlide(){
	$(".menuLev1").click(function(){
		var obj=$(this);
		var nextobj=obj.next();
		var objLi=obj.closest("li");
		var objSiblings=objLi.siblings().find(".menuLev2Wrap");
		
		nextobj.slideToggle("slow");
		if(objSiblings.is(":visible")){
			objSiblings.slideUp("slow");
		}
	})
	
	$(".menuLev2Wrap ul li:last-child").css({"border-bottom":"0"});
}

//左侧伸缩按钮
function leftBtn(){
	var siderBtn=$("#siderBtn");
	
	siderBtn.click(function(){
		var obj=$(this);
		if(!obj.hasClass("siderBtnOn")){
			$("#sider").hide();
			obj.css({"left":"0px"});
			obj.addClass("siderBtnOn");
			$("#mainWrap").css({"margin-left":0});
			$("body").removeClass("image");
		}else{
			$("#sider").show();
			obj.css({"left":"211px"});
			obj.removeClass("siderBtnOn");
			$("#mainWrap").css({"margin-left":"211px"});
			$("body").addClass("image");
		}
	})
	
}

//处理高度和宽度
function layoutSize(){
	var sider=$("#sider");
	
	var size=(function(){
		var winOjb=$(window);
		var topH=109;
		return {w:winOjb.width(),h:winOjb.height()-topH,topH:topH};
	})();
	
	$("#siderBtn").css({"top":(size.h/2 + size.topH)+"px"}).show();
	
	(function(){
		var wrapH=$("#wrap").outerHeight();
		var siderH=sider.outerHeight();
		var h=siderH>wrapH?siderH:wrapH;
		h=h>size.h?h:size.h;
		sider.css({"min-height":h});
	})();
}

//窗口改变
function windowSize(){
	$(window).resize(function(){
		layoutSize();
	}).trigger("resize");
}

//同步iframe高度
function iFrameHeight(ifmID) { 
	var pTar = null;
	if (document.getElementById){
		pTar = document.getElementById(ifmID);
	}
	else{
		eval('pTar = ' + ifmID + ';');
	}
	if (pTar && !window.opera){
		//begin resizing iframe
		pTar.style.display="block"
		if (pTar.contentDocument && pTar.contentDocument.body.offsetHeight){
			//ns6 syntax
			pTar.height = pTar.contentDocument.body.offsetHeight +20;
			//pTar.width = pTar.contentDocument.body.scrollWidth+20;
		}
		else if (pTar.Document && pTar.Document.body.scrollHeight){
			//ie5+ syntax
			pTar.height = pTar.Document.body.scrollHeight;
			//pTar.width = pTar.Document.body.scrollWidth;
		}
	}
}

//点击菜单，中间内容切换
function mainShow(title,src,order,height){
	var isAside=true;
	if(src.indexOf("http") == -1) {
		src = basePath + src;
		isAside=false;
	}
	// 如果是custsev 加密
	$.ajax({
        url:basePath + "cust/merSSOCust?adm="+new Date().getTime(),
        type: "get",
        async: false,// 同步
        data:{to_uri:encodeURIComponent(src)},
	    dataType : 'jsonp',//返回值类型 
        success: function(data){
        	if(data.code == '000') {
        		src = data.data.custLoginUrl+"?securityKey="+data.data.securityKey+"&security="+data.data.security+"&source=merchants";
        	}
        }
        
    });
	
	var tabBar=$("#tabBar");
	var tabBarUl=tabBar.find("ul")
	var main=$("#main");
	var tabItem=$("#tabItem-"+order);
	var mainItem=$("#main-"+order);
	
	$("nav li[data-order="+order+"]").addClass("navOn").siblings().removeClass("navOn");
	if(tabItem.length==0){
		//处理tabBar
		(function(tabBarUl,title,order){
			tabBarUl.find(".tabItem").removeClass("tabOn");
			tabBarUl.append(getTabLi(title,order));
		})(tabBarUl,title,order);
		
		//处理main
		(function(main){
			main.find(".webContain").addClass("none");
			main.append(getIframe(src,order,height,isAside));
		})(main);
		tabScroll.addLi();
	}else{
		tabItem.addClass("tabOn").siblings().removeClass("tabOn");
		mainItem.removeClass("none").siblings().addClass("none");
		tabScroll.clickLi(tabItem);
	}
	$("nav").find("li").removeClass("navOn").filter("[data-order="+order+"]").addClass("navOn");
	//tabScroll.judge();
	menu_order=order;
}
function tabItem(order,obj){
	var obj=$(obj);
	var mainThis=$("#main-"+order);
	
	$("nav").find("li").removeClass("navOn").filter("[data-order="+order+"]").addClass("navOn");
	obj.addClass("tabOn").siblings().removeClass("tabOn");
	mainThis.removeClass("none").siblings().addClass("none");
}
function tabItemClose(order,obj){
	var e = arguments.callee.caller.arguments[0] || window.event; 
	if(window.event){
		window.event.cancelBubble = true;
	}else{
		e.stopPropagation();
	}
	
	var obj=$(obj).closest(".tabItem");
	var mainThis=$("#main-"+order);
	//var navObj=$("nav li[data-order="+order+"]");
	
	var objPrev=obj.prev();
	var mainThisPrev=mainThis.prev();
	
	if(obj.hasClass("tabOn")){
		objPrev.addClass("tabOn");
		mainThisPrev.removeClass("none");
		
		$("nav li[data-order="+objPrev.attr("data-order")+"]").addClass("navOn").siblings().removeClass("navOn");
	}
	obj.remove();
	mainThis.remove();
	tabScroll.judge("close");
	return false;
	
}
function getTabLi(title,order){
	return '<li class="tabItem tabOn" id="tabItem-'+order+'" data-order="'+order+'" onclick="tabItem(\''+order+'\',this)"><div class="tabCenter"><div class="tabRight"><div class="tabLeft"><span class="tab-close" onclick="tabItemClose(\''+order+'\',this)">x</span><span class="tab-ico"></span> <span class="tab-text">' + title + '</span></div></div></div></li>';
}
function getIframe(src,order,height,isAside){
	var arr=['intoCussWorkOrderAdd.do'];
	var leng=arr.length;
	var s="";
	for(var i=0;i<leng;i++){
		if(src.indexOf(arr[i])>-1){
			s='onload="iFrameHeight(\'iframe-'+order+'\')"';
			break;
		}
	}
	var iframeObj="";
	if(isAside==true){
		iframeObj='<div class="webContain main-'+order+'" id="main-'+order+'"><iframe id="iframe-'+order+'" '+s+' src="'+src+'" width="100%" height="'+height+'" frameborder="0" scrolling="yes"></iframe></div>';
	} else {
		iframeObj='<div class="webContain main-'+order+'" id="main-'+order+'"><iframe id="iframe-'+order+'" '+s+' src="'+src+'" width="100%" height="'+height+'" frameborder="0" scrolling="no"></iframe></div>';
	}
	return iframeObj;
}
function navShow(title,src,order,obj,height){
	$(obj).addClass("navOn").siblings().removeClass("navOn");
	mainShow(title,src,order,height);
}

function addFavorite() {
    try {
        window.external.addFavorite(window.location.href, document.title);
    } catch (e) {
    	try {
    		window.sidebar.addPanel(document.title, window.location.href, "");
    	} catch (e) {
    		alert("抱歉，由于chrome,safari,opara浏览器还未支持自动收藏,请使用Ctrl+D进行添加!");
    	}
    }
}

//修改密码
function changePws(userName){
	parent.diaWin(basePath+'user/intoModifyPwd',"修改密码",550,300);
}

function tabBarScroll(){
	if($("#tabBar").size()==0){
		return false;
	}
	this.init();
}
tabBarScroll.prototype={
	liWidth:145,
	init:function(){
		this.getElem();
		this.attachEvent();
		
	},
	getElem:function(){
		this.tabBar=$("#tabBar");
		this.tabBarUl=this.tabBar.find("ul");
		this.leftBtn=$("#tabBar-leftBtn");
		this.rightBtn=$("#tabBar-rightBtn");
	},
	attachEvent:function(){
		this.leftClick();
		this.rightClick();
		this.resizeWindow();
	},
	leftClick:function(){
		var that=this;
		this.leftBtn.click(function(){
			that.tabBarUl.animate({left:"+="+that.liWidth+"px"},100,function(){
				that.judge();
			});
		})
	},
	rightClick:function(){
		var that=this;
		this.rightBtn.click(function(){
			that.tabBarUl.animate({left:"-="+that.liWidth+"px"},100,function(){
				that.judge();
			});
		})
	},
	resizeWindow:function(){
		var that=this;
		$(window).resize(function(){
			that.judge();
		})
	},
	judge:function(type){
		var barWidth=this.getAllWidth();
		var ulWidth=this.getUlWidth();
		
		var left=this.getUlLeft();
		
		var barNum=Math.floor(barWidth/this.liWidth);
		var ulRightNum=(ulWidth+this.getUlLeft())/this.liWidth;
		
		if(left<0){
			this.leftBtn.show();
		}else{
			this.leftBtn.hide();
		}
		
		if(ulRightNum>barNum){
			this.rightBtn.show();
		}else{
			this.rightBtn.hide();
		}
		
		if(type=="close"&&left<0&&(ulRightNum<barNum)){
			this.leftBtn.trigger("click");
		}
	},
	getAllWidth:function(){
		return this.tabBar.width();
	},
	getNum:function(){
		var num=this.tabBar.find("li").size();
		return num;
	},
	getUlWidth:function(){
		return this.liWidth*this.getNum();
	},
	getUlLeft:function(){
		return this.left(this.tabBarUl.css("left"));
	},
	left:function(str){
		return parseInt(str.substr(0,str.length-2));
	},
	addLi:function(){
		if(this.getAllWidth()<this.getUlWidth()){
			var that=this;
			that.tabBarUl.animate({left:"-="+that.liWidth+"px"},0,function(){
				that.judge();
			});
			//this.judge();
		}
	},
	clickLi:function(obj){
		var barWidth=this.getAllWidth(); //bar宽度
		var barNum=Math.floor(barWidth/this.liWidth); //bar内显示li完整的个数
		var barLiWidth=barNum*this.liWidth; //bar内显示li完整的宽度
		
		var ulWidth=this.getUlWidth(); //ul内li的总和
		var left=this.getUlLeft(); //ul的left值
		var ulRightNum=(ulWidth+this.getUlLeft())/this.liWidth; //ul去掉左边后，右边的数量
		
		var index=this.tabBarUl.find("li").index(obj); //当前对象index
		var objLeftW=(index+1)*this.liWidth; //当前对象和左边li的宽度和
		
		if(objLeftW<=Math.abs(left)){
			var that=this;
			that.tabBarUl.animate({left:"-"+(that.liWidth*index)+"px"},0,function(){
				that.judge();
			});
		}
		
		if(objLeftW>Math.abs(left)+barLiWidth){
			var that=this;
			that.tabBarUl.animate({left:"-="+(objLeftW-Math.abs(left)-barLiWidth)+"px"},0,function(){
				that.judge();
			});
		}
		
	}
}

function initAgreeTerm() {
	if(agreeTerm != 'Y') {
		diaWin(basePath + "common/agreeTerm","入驻须知", 900, 700);
		$(".aui_close").hide();
	}
}

function submitAgreeTerm() {
	var url = basePath + "merchant/agreeterm";
	$.ajax({
        url:url + "?adm="+new Date().getTime(),
        type: "GET",
	    dataType : 'json',//返回值类型 
        success: function(data){
        	if(data.code == '000') {
        		art.dialog.list['diaWinId'].close();
        	}
        }
        
    });
}

function otherSysLogout(){
	var i=0;
	function ajaxFun(){
		$.ajax({
			async: true,
	        url:outURL[i] + "/init/destorySession.do",
	        timeout: 1000,
	        dataType:"jsonp",
	        jsonp:"jsonpcallback",
	        data: {},
	        success:function(data){
	        },
	        error:function(data,textstatus){
	        	if("timeout" == textstatus){
	        		i = 5000;
	        	}
		    },
		    complete:function(XMLHttpRequest, textStatus){
	        	if(i<outURL.length-1){
		        	i++;
		        	ajaxFun();
	        	}else{
	        		if(i == 5000){
	        			diaAlert2("error","抱歉，关联系统退出异常，请以关闭游览器的方式退出！");
	        			$(".aui_close").hide();
	        		} else {
	        			var logoutUrl = basePath + '/common/loginout.do';	
		        		window.location.href = logoutUrl;
	        		}
	        	}
		    }
		});
	}
	ajaxFun();
}

function logout() {
	otherSysLogout();
}


























