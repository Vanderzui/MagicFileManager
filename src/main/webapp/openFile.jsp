<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 15.09.2020
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h><title>хихихи</title></h>
</head>
<body>
Вот че тут есть:

<form method="post">

    <%--    ${result}--%>
    <textarea rows="50" cols="100">
        ${result}

  </textarea>
        <input type="text" name="input" >

    <br><button type="submit">Submit</button>
        <br>
</form>
<div>
    <button onclick="location.href='${close}'">Close</button>
</div>
</body>
</html>
