<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="table">
    <!-- check table -->
    <table id="check-table" class="table-check">
        <thead>
            <tr>
                <th scope="col">X</th>
                <th scope="col">Y</th>
                <th scope="col">R</th>
                <th scope="col">Результат попадания</th>
                <th scope="col">Время отправки</th>
                <th scope="col">Время исполнения (мс)</th>
            </tr>
        </thead>
        <tbody>
        <jsp:useBean id="history" scope="session" class="com.simeon.lab2.beans.History"/>
        <c:forEach var="row" items="${history.resultList}">
            <tr>
                <td><fmt:formatNumber value="${row.x}" maxFractionDigits="3" minFractionDigits="3" /></td>
                <td><fmt:formatNumber value="${row.y}" maxFractionDigits="3" minFractionDigits="3" /></td>
                <td><fmt:formatNumber value="${row.r}" maxFractionDigits="3" minFractionDigits="3" /></td>
                <td>
                    <c:if test="${row.hit}">Попал</c:if>
                    <c:if test="${!row.hit}">Не попал</c:if>
                </td>
                <td>
                    <fmt:parseDate  value="${row.createdAt}" type="both" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDate" />
                    <fmt:formatDate value="${parsedDate}" type="both" dateStyle="long" timeStyle="short" /></td>
                <td>${row.workingTime}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>