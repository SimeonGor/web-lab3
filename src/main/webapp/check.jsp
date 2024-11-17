<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Result</title>

    <link href='<c:url value="/style/css/header.css" />'  rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/css/main.css" />'  rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/css/table.css" />' rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/css/check.css" />' rel="stylesheet" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />

    <main>

        <a href='<c:url value="/index.jsp" />' class="button">Попробовать снова</a>

        <jsp:include page="/table.jsp" />
    </main>
</body>
</html>
