<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<head>
    <link rel="stylesheet" href="indexStyle.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Redressed&display=swap" rel="stylesheet">
</head>
<html>
    <head>
        <title>ADA Dashboard</title>
    </head>
    <body>

    <c:out value="${pageTitle}"/>

    <c:out value="${orhan}"/>

    <c:if test="${sessionScope.is_authorize != null}">
        <p id="HelloMessage">Hello, ${sessionScope.current_username}</p>
        <div style="float: right">
            <a href="/ada_project_war/auth?action=logout">
                <button id="logOut" type="button">
                    Log Out
                </button>
            </a>
        </div>
        <form class="form" action="/ada_project_war/auth" method="POST">
            <div>
                <input id="ffirstname" type="text" name="firstname" placeholder="First Name"/>
            </div>
            <div>
                <input id="flastname" type="text" name="lastname" placeholder="Last Name"/>
            </div>
            <div>
                <input id="fcity" type="text" name="city" placeholder="City"/>
            </div>
            <div>
                <input id="fcountry" type="text" name="country" placeholder="Country"/>
            </div>

            <div style="clear: both"></div>
            <button id="submit-button" type="submit" name="dosubmit" value="Submit"/> Save </button>
        </form>

    <sql:setDataSource
            var="myDS"
            driver="org.postgresql.Driver"
            url="jdbc:postgresql://localhost:5432/postgres"
            user="postgres" password="Maestro76"
    />

    <sql:query var="listCourses"   dataSource="${myDS}">
        SELECT * FROM courses
    </sql:query>

    <div align="center">
        <table id="courseTable" border="1" cellpadding="5">
            <caption><h2>List of Courses</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <c:forEach var="course" items="${listCourses.rows}">
                <tr>
                    <td><c:out value="${course.course_id}" /></td>
                    <td><c:out value="${course.course_name}" /></td>
                    <td> <button id="enroll"> Enroll </button> </td>
                </tr>
            </c:forEach>
        </table>
    </div>


    </c:if>

    <c:if test="${sessionScope.is_authorize != true}">
            <c:import url="auth.jsp"></c:import>
    </c:if>
    </body>
</html>