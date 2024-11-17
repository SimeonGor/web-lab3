<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.simeon.lab2.beans.CheckResult" %>

<svg class="graph" height=${param.height} width=${param.width} xmlns="http://www.w3.org/2000/svg">
    <!-- figure -->
    <path class="area" transform="translate(${param.width / 2}, ${param.height/ 2}) scale(2, 2)"
          d="m -${param.width * 0.5 / 6} 0
          l 0 ${param.height / 6}
          l ${param.width * 0.5 / 6} 0
          l 0 -${param.height / 6}
          l ${param.width / 6} 0
          a ${param.width / 6} ${param.height / 6} 0 0 0 -${param.width / 6} -${param.height / 6}
          l -${param.width * 0.5 / 6} ${param.height / 6}
          Z"></path>
    <!-- lines -->
    <line x1="0" y1=${param.height / 2} x2=${param.width} y2=${param.height / 2}></line>
    <line x1=${param.width / 2} y1="0" x2=${param.width / 2} y2=${param.height}></line>
    <polygon points="${param.width / 2},0
                     ${param.width / 2 - 4},10
                     ${param.width/ 2 + 4},10"
             stroke="black"></polygon>
    <polygon points="${param.width},${param.height / 2}
                     ${param.width - 10},${param.height / 2 - 4}
                     ${param.width - 10},${param.height / 2 + 4}"
             stroke="black"></polygon>

    <text x=${param.width - 15} y=${param.height / 2 - 10}>X</text>
    <text x=${param.width / 2 + 10} y="15">Y</text>

    <!-- lines on lines -->
    <c:forEach var="label" items="-R -2,-R/2 -1, ,R/2 1,R 2" varStatus="counter">
        <line x1=${param.width * counter.count / 6} y1=${param.height / 2 - 4} x2=${param.width * counter.count / 6} y2=${param.height / 2 + 4}></line>
        <text class="default" x=${param.width * counter.count / 6 - 5} y=${param.height / 2 - 10}
              visibility="visible">
                ${label.split(" ")[0]}
        </text>
        <text class="scaled"
              x=${param.width * counter.count / 6 - 5} y=${param.height / 2 - 10}
              visibility="hidden">
                ${label.split(" ")[1]}
        </text>
    </c:forEach>
    <c:forEach var="label" items="R 2,R/2 1, ,-R/2 -1,-R -2" varStatus="counter">
        <line x1=${param.width / 2 - 4} y1=${param.height * counter.count / 6} x2=${param.width / 2 + 4} y2=${param.height * counter.count / 6}></line>
        <text class="default" x=${param.width / 2 + 10} y=${param.height * counter.count / 6 + 4}
              visibility="visible">
                ${label.split(" ")[0]}
        </text>
        <text class="scaled" x=${param.width / 2 + 10} y=${param.height * counter.count / 6 + 4}
              visibility="hidden">
                ${label.split(" ")[1]}
        </text>
    </c:forEach>

    <jsp:useBean id="history" scope="session" class="com.simeon.lab2.beans.History"/>
    <c:forEach var="entity" items="${history.resultList}">
        <circle class="point scaled" r="5"
                cx=${(entity.x + 3) * param.width / 6} cy=${(3 - entity.y) * param.height / 6}
                hit="${entity.hit}"
                visibility="hidden"></circle>
    </c:forEach>
</svg>
