<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 21-Sep-23
  Time: 10:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="LoginPage.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">

    <form class="login-page-container" action="controlServlet" method="post" onsubmit="return validateForm()">
        <h1>Login</h1>
        <div class="form-group">
            <label for="email" style="font-weight: bold">Email:</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="email@gmail.com">
        </div>
        <div class="form-group">
            <label for="password" style="font-weight: bold">Password:</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-success">Login</button>
        <a href="../register/Register.jsp" class="btn btn-secondary">Register</a>
        <input type="hidden" name="action" value="logon">
    </form>
</div>
<script>
    function validateForm() {
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        if (email === "" || password === "") {
            alert("Vui lòng điền đầy đủ thông tin");
            return false;
        }
        return true;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
