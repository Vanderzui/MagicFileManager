<%--
  Created by IntelliJ IDEA.
  User: Алиса
  Date: 23.09.2020
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Note</title>
</head>
<body>
<table>
   <c:forEach items="${note}" var="note">
       <tr><td> ${note} </td></tr>
   </c:forEach>
    </table>
<form method="post">
<input type="text" name="input" > <br>
    <br><button type="submit">Submit</button> </form>
    </body>
    </html>