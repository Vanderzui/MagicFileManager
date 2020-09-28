<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 17.09.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.message" var="message" />
<fmt:message bundle="${loc}" key="local.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.open" var="open" />
<fmt:message bundle="${loc}" key="local.delete" var="deleteD" />
<fmt:message bundle="${loc}" key="local.createDir" var="createDir" />
<fmt:message bundle="${loc}" key="local.close" var="close" />
<fmt:message bundle="${loc}" key="local.createFile" var="createFile" />
<fmt:message bundle="${loc}" key="local.note" var="noteN" />
<fmt:message bundle="${loc}" key="local.back" var="backback" />

<head>
    <title>dirdirdir</title>
</head>
<body>
<h1>
    DIRS
    ${url}

</h1>
<table>
    <c:forEach items="${files}" var="file">
        <tr> <td> <a href="${urlFile}/${file}">${file}</a></td>
            <td> <form method="post">
                <input type="hidden" name="delete" value="${file}">
                <button>${deleteD}</button>
            </form> </td>
            <td> <form method="get">
                <a href="/note${urlFile}/${file}">${noteN}</a>
            </form></td>
        </tr>
    </c:forEach>
</table>


<table>
    <c:forEach items="${directories}" var="dir">
        <tr> <td> <a href="${urlDir}/${dir}">${dir}</a></td>
            <td> <form method="post">
                <input type="hidden" name="delete" value="${dir}">
                <button>${deleteD}</button>
            </form></td>
        </tr>
    </c:forEach>
</table>

<div>
    <button onclick="location.href='${back}'">${backback}</button>
    <br><br>
</div>

<div>
    <form method="post">
        <input name="dirName" type="text"> <br>
        <input type="submit" value="${createDir}"> <br> <br>
        <input name="fileName" type="text"> <br>
        <input type="submit" value="${createFile}"> <br> <br>
    </form>
</div>
<c:out value="${message}" />
<form method="post">
    <input type="hidden" name="local" value="ru" />
    <input type="submit" value="${ru_button}" /><br />
</form>
<form method="post">
    <input type="hidden" name="local" value="en" />
    <input type="submit" value="${en_button}" /><br />
</form>

</body>
</html>
