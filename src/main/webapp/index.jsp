<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Checker</title>

    <link href="style/css/header.css" rel="stylesheet" type="text/css" />
    <link href="style/css/main.css" rel="stylesheet" type="text/css" />
    <link href="style/css/graph.css" rel="stylesheet" type="text/css" />
    <link href="style/css/form.css" rel="stylesheet" type="text/css" />
    <link href="style/css/table.css" rel="stylesheet" type="text/css" />


</head>
<body>

<jsp:include page="header.jsp" />

<main>
    <div class="graph-data">
        <div class="graph-container">
            <jsp:include page="graph.jsp">
                <jsp:param name="height" value="300"/>
                <jsp:param name="width" value="300"/>
            </jsp:include>

        </div>

        <form id="coordinates-form" method="GET" action="${pageContext.request.contextPath}/controller">
            <div id="x-field" class="value-input">
                <div>
                    <label>
                        X:
                        <select name="x" required>
                            <option value="" disabled>--select x--</option>
                            <c:forEach var="x" items="-5,-4,-3,-2,-1,0,1,2,3">
                                <option value="${x}">${x}</option>
                            </c:forEach>
                        </select>
                    </label>
                </div>
            </div>
            <div id="y-field" class="value-input">
                <label>
                    Y:
                    <input id="y-input" type="text" name="y" placeholder="(-3; 5)" maxlength="30" autocomplete="off" required>
                </label>
            </div>
            <div id="r-field" class="value-input">
                <div>
                    <label>R:
                        <c:forEach var="r" items="1,1.5,2,2.5,3">
                            <label class="radio-label">
                                <input type="radio" name="r" value="${r}" required>${r}
                            </label>
                        </c:forEach>
                    </label>
                </div>
            </div>
            <button class="submit-button" type="submit">Отправить</button>
        </form>
    </div>

    <jsp:include page="table.jsp" />
</main>

<footer>
</footer>

<script type="text/javascript" src="script/validator.js"></script>
<script type="text/javascript" src="script/area.js"></script>
</body>
</html>