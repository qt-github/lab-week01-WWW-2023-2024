<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Sep-23
  Time: 12:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="Register.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <form class="register-page-container" action="controlServlet" method="post">
        <h1 class="mt-5">Registration</h1>
        <div class="form-group">
            <label for="accountId" style="font-weight: bold">Account id:</label>
            <input type="text" class="form-control" id="accountId" name="accountId" required>
        </div>

        <div class="form-group">
            <label for="username" style="font-weight: bold">Full name:</label>
            <input type="text" class="form-control" id="username" name="fullName" required>
        </div>

        <div class="form-group">
            <label for="email" style="font-weight: bold">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password" style="font-weight: bold">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="phone" style="font-weight: bold">Phone:</label>
            <input type="tel" class="form-control" id="phone" name="phone" required>
        </div>

        <div class="form-group">
            <label for="status" style="font-weight: bold">Status:</label>
            <select class="form-control" id="status" name="status" required>
                <option value="1">ACTIVE</option>
                <option value="0">DEACTIVATE</option>
                <option value="-1">DELETED</option>
            </select>
        </div>

        <input type="submit" class="btn btn-secondary" value="Register">
        <input type="hidden" name="action" value="register">
        <button class="btn btn-success" onclick="window.location.href='../login/LoginPage.jsp'">Login</button>
    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>
