<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>empList</title>

    <!-- CSS goes in the document HEAD or added to your external stylesheet -->
    <style type="text/css">
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }
    </style>

</head>
<body>

    <table class="gridtable">
        <tr>
            <th scope="col">序号</th>
            <th scope="col">emp_no</th>
            <th scope="col">birth_date</th>
            <th scope="col">first_name</th>
            <th scope="col">last_name</th>
            <th scope="col">gender</th>
            <th scope="col">hire_date</th>
        </tr>
        <c:forEach begin="0" step="1" items="${empList}" var="emp" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${emp.empNo}</td>
                <td>${emp.birthDate}</td>
                <td>${emp.firstName}</td>
                <td>${emp.lastName}</td>
                <td>${emp.gender}</td>
                <td>${emp.hireDate}</td>
            </tr>
        </c:forEach>
    </table>

    <p>一共${page.pages}页</p>
    <a href="userList?page=${page.firstPage}">第一页</a>
    <a href="userList?page=${page.nextPage}">下一页</a>
    <a href="userList?page=${page.prePage}">上一页</a>
    <a href="userList?page=${page.lastPage}">最后页</a>

</body>
</html>
