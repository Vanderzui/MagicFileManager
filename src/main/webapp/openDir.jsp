<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<html>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>
<fmt:message bundle="${loc}" key="local.message" var="message"/>
<fmt:message bundle="${loc}" key="local.ru" var="ru_button"/>
<fmt:message bundle="${loc}" key="local.en" var="en_button"/>
<fmt:message bundle="${loc}" key="local.open" var="open"/>
<fmt:message bundle="${loc}" key="local.delete" var="deleteD"/>
<fmt:message bundle="${loc}" key="local.createDir" var="createDir"/>
<fmt:message bundle="${loc}" key="local.close" var="close"/>
<fmt:message bundle="${loc}" key="local.createFile" var="createFile"/>
<fmt:message bundle="${loc}" key="local.note" var="noteN"/>
<fmt:message bundle="${loc}" key="local.back" var="backback"/>
<fmt:message bundle="${loc}" key="local.upload" var="uploadF"/>
<fmt:message bundle="${loc}" key="local.download" var="downloadD"/>


<head>
    <title>Directories</title>
</head>
<body>
<h1>
    DIRS
    ${url}

</h1>
<table>
    <c:forEach items="${files}" var="file">
        <tr>
            <td><a href="${urlFile}/${file}" class="w3-button w3-purple w3-round-xxlarge">${file}</a></td>
            <td>
                <form method="post">
                    <input type="hidden" name="delete" value="${file}">
                    <button class="w3-button w3-white w3-border w3-border-red w3-round-large w3-small">${deleteD}</button>
                </form>
            </td>
            <td>
                <form method="get">
                    <a href="/note${urlFile}/${file}" class="w3-button w3-tiny w3-circle w3-white w3-border w3-blue">${noteN}</a>
                    <a href="/download/${file}">${downloadD}</a>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<table>
    <c:forEach items="${directories}" var="dir">
        <tr>
            <td><a href="${urlDir}/${dir}" class="w3-button w3-pink w3-round-xxlarge">${dir}</a></td>
            <td>
                <form method="post">
                    <input type="hidden" name="delete" value="${dir}">
                    <button class="w3-button w3-white w3-border w3-border-red w3-round-large w3-small">${deleteD}</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<div>
    <button onclick="location.href='${back}'" class="w3-button w3-border w3-hover-blue">${backback}</button>
    <br><br>
</div>

<div>
    <form method="post">
        <input name="dirName" type="text"> <br>
        <input type="submit" value="${createDir}" class="w3-button w3-border w3-hover-blue"> <br> <br>
        <input name="fileName" type="text"> <br>
        <input type="submit" value="${createFile}" class="w3-button w3-border w3-hover-blue"> <br> <br>
    </form>
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type = "file" name = "file" size = "50" />
        <br/>
        <input type = "submit" value = "${uploadF}" />
    </form>
</div>
    <c:out value="${message}"/>
    <form method="post">
        <input type="hidden" name="local" value="ru"/>
        <input type="submit" value="${ru_button}"/>
    </form>
    <form method="post">
        <input type="hidden" name="local" value="en"/>
        <input type="submit" value="${en_button}"/>
    </form>
</body>
</html>
