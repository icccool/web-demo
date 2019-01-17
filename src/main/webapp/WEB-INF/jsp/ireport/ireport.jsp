<%@ page language="java" contentType="text/html; charset=UTF-8"       
    pageEncoding="UTF-8"%>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>         
<!DOCTYPE html>
<html>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>局部打印案例</title>

    <script type="text/javascript">
        function doPrint() {
            $.ajax({
                type:"GET",
                url:"/report/demoReport2.do",
                dataType:"html",
                success:function(data){
                    //$("#showReport").html("<p2>以下打印的内容</p2><br>");
                    //$("#showReport").html(data);
                    window.document.body.innerHTML=data;
                    window.print();
                },
                error:function(jqXHR){
                    console.log("Error: "+jqXHR.status);
                }
            });
        }
    </script>
</head>

<body>

    <button type="button" onclick="doPrint()">打印</button>

</body>
</html>

