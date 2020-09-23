<%@ page import="java.io.PrintWriter" %><%--
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
    ${url}

</h1>
<table>
    <c:forEach items="${files}" var="file">

 <tr> <td> <a href="${urlFile}/${file}">${file}</a></td>


     <td>   <form method="post">
            <input type="hidden" name="delete" value="${file}">
            <button>Delete</button>
        </form>  </td>
 </tr>
    </c:forEach>
</table>
<table>
    <c:forEach items="${directories}" var="dir">
        <tr>
            <td> <a href="${urlDir}/${dir}">${dir}</a></td>

            <td>  <form method="post">
            <input type="hidden" name="delete" value="${dir}">
            <button>Delete</button>
        </form></td>
        </tr>

    </c:forEach>
</table>
<div>
    <button onclick="location.href='${back}'">Back</button>
    <br>
    <br>
</div>
<div>
    <form method="post">
        <input name="dirName" type="text"> <br>
        <input type="submit" value="Create Folder"> <br> <br>
        <input name="fileName" type="text"> <br>
        <input type="submit" value="Create File"> <br> <br>
    </form>
</div>


</body>
</html>
