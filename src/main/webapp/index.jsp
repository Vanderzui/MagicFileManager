<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.service.SimpleFileService" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Волшебный проводник</title>
</head>
<body>
<div style="text-align: center;">
    <h1>
        Start to create!
    </h1>
    <table>
        <c:forEach items="${files}" var="file">
                    <%--                <td><a href="openFile.jsp">${file}</a></td>--%>
                <td><a href="/${file}">${file}</a></td>

        </c:forEach>
    </table>
    <table>
        <c:forEach items="${directories}" var="dir">
<%--            <tr>--%>
<%--                <td><a href="http://localhost:8080/${dir}">${dir}</a></td>--%>
<%--            </tr>--%>
            <td><a href="/${dir}">${dir}</a></td>
        </c:forEach>
    </table>

<%--    <p>--%>
<%--    <form method="get" action="название сервлета. где будет создание файла/директории">--%>
<%--        <button onclick="location.href=''">--%>
<%--            <img src="<c:url value="/images/createFile.png"/>" alt="файл" style="vertical-align:middle">--%>
<%--            <br>новую папочку-то создай, ну--%>
<%--        </button>--%>
<%--    </form>--%>

<%--    <form method="post" action="название сервлета. где будет создание директории">--%>
<%--        <button onclick="location.href=''">--%>
<%--            <img src="<c:url value="/images/createFolder.png"/>" alt="папка" style="vertical-align:middle">--%>
<%--            <br>New Directory--%>
<%--        </button>--%>
<%--    </form>--%>

<%--    <form method="put" action="название сервлета. где будет редактирование файла">--%>
<%--        <button onclick="location.href='/magic/*'">--%>
<%--            <img src="<c:url value="/images/open.png"/>" alt="открыть" style="vertical-align:middle">--%>
<%--            <br>Open--%>
<%--        </button>--%>
<%--    </form>--%>

<%--    <form method="delete" action="название сервлета. где будет удаление">--%>
<%--        <button onclick="location.href=''">--%>
<%--            <img src="<c:url value="/images/delete-sign.png"/>" alt="удалить" style="vertical-align:middle">--%>
<%--            <br>Delete--%>
<%--        </button>--%>
<%--    </form>--%>
<%--    </p>--%>
</div>
</body>
</html>