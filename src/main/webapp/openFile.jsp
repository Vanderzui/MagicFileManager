<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 15.09.2020
  Time: 17:30
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
<fmt:message bundle="${loc}" key="local.close" var="closeC" />
<fmt:message bundle="${loc}" key="local.submit" var="submitS" />

<head>
    <h><title>Файл</title></h>
</head>
<body>
<form method="post">
    <textarea rows="50" cols="100">
        ${result}
    </textarea>
    <input type="text" name="input" >
    <br>
    <button type="submit">${submitS}</button>
    <br>
</form>

<div>
    <button onclick="location.href='${close}'">${closeC}</button>
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
