<%--
  Created by IntelliJ IDEA.
  User: hello
  Date: 1/8/2022
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>>Register Page</title>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/auth/registration" modelAttribute="user">
        <div class="form-group">
            <form:label path="username" for="nameInput">UserName</form:label>
            <form:input type="text" path="username" class="form-control" id="nameInput"/>
            <form:errors path="username" style="color:red;"/>
        </div>
        <div class="form-group">
            <form:label path="password" for="ageInput">Password</form:label>
            <form:input type="password" path="password" class="form-control" id="pwdInput"/>
            <form:errors path="password" style="color:red;"/>
        </div>
        <div class="form-group">
            <form:label path="email" for="emailInput">FirstName</form:label>
            <form:input type="text" path="email" class="form-control" id="emailInput"/>
            <form:errors path="email" style="color:red;"/>
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form:form>
</div>
</body>
</html>
