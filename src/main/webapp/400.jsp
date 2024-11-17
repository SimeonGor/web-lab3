<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Error</title>

    <link href='<c:url value="/style/css/header.css" />' rel="stylesheet" type="text/css" />
    <link href='<c:url value="/style/css/main.css" />' rel="stylesheet" type="text/css" />
</head>
<body>
    <jsp:include page="/header.jsp" />

    <main>
        <p>400 - Bad Request</p>
    </main>
</body>
</html>
