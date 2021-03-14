<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>ADA Dashboard</title>
    </head>
    <body>

    <c:out value="${pageTitle}"/>

    <c:out value="${orhan}"/>

    <c:if test="${sessionScope.is_authorize != null}">
        <div>
            Hello, ${sessionScope.current_username}
        </div>
        <div style="float: right">
            <a href="/ada_project_war/auth?action=logout">Logout button</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.is_authorize == null}">
            <c:import url="auth.jsp"></c:import>

        </c:if>
    </body>
</html>