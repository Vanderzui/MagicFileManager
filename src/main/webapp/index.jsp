<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    </h1> </div>
<div style="text-align: center;">
    <form>
        <p><button>
            <img src="<c:url value="/resources/images/createFile.png"/>" alt="файл" style="vertical-align:middle">
            New File
        </button>

        <button>
            <img src="<c:url value="/resources/images/createFolder.png"/>" alt="папка" style="vertical-align:middle">
            New Directory
        </button>

        <button>
            <img src="<c:url value="/resources/images/open.png"/>" alt="открыть" style="vertical-align:middle">
            Open
        </button>

        <button>
            <img src="<c:url value="/resources/images/delete-sign.png"/>" alt="удалить" style="vertical-align:middle">
            Delete
        </button>
        </p>
    </form>
</div>
</body>
</html>