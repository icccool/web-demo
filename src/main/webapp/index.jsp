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

</head>

<p2>hello world!!</p2>
<p/>
<p2>报表demo2</p2>   <a href="/report/demoReport2.do">报表demo2</a>
<p/>
<p2>报表demo2[打印表格]</p2>   <a href="/report/reportIndex.do">报表demo2打印</a>
<p/>
<p2>报表demo2[打印列表]</p2>   <a href="/report/report.do">报表demo2列表</a>


</body>
</html>

