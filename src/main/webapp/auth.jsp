<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anario
  Date: 2021-02-14
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<style>
div.error_messages > b {
    color: red;
    font-style: italic;
}
</style>

<div class="center">
    <h1>Auth Page</h1>
    <p>Please login to proceed...</p>




    <form action="/ada_project_war/auth" method="POST">
        <div>
            <label for="fusername">Username:</label>
            <input id="fusername" type="text" name="username" placeholder="Username..."/>
        </div>
        <div>
            <label for="fpassword">Password:</label>
            <input id="fpassword" type="password" name="password" placeholder="Password..."/>
        </div>
        <div style="clear: both"></div>
        <input type="submit" name="dosubmit" value="Submit"/>
    </form>
</div>
