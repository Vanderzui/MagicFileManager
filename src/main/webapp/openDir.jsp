<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 17.09.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dirdirdir</title>
</head>
<body>
<h1>
    DIRS
</h1>
<table>
    <c:forEach items="${files}" var="file">
        <tr>
            <td><a href="/${file}">${file}</a></td>
        </tr>

    </c:forEach>
</table>
<table>
    ${url}
    <c:forEach items="${directories}" var="dir">
        <tr>
            <td><a href="${url}/${dir}">${dir}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
