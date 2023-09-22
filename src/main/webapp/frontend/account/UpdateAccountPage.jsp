<%@ page import="vn.edu.iuh.fit.labweek01.modules.Account" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Sep-23
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Account Page</title>
    <link rel="stylesheet" href="styles/UpdateAccountPage.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Role Edit</h1>
        <button type="button" class="btn btn-primary mt-2" onclick="window.location.href = 'controlServlet?action=listAccount'">
            <i class="fas fa-arrow-left"></i> Back
        </button>
    </div>
    <div class="row">
        <div class="col-md-2">
            <!-- Menu bên trái -->
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="controlServlet?action=dashboard">Dashboard</a>
                </li>
                <li class="list-group-item">
                    <a href="controlServlet?action=listAccount">Account</a>
                </li>
                <li class="list-group-item">
                    <a href="controlServlet?action=listRole">Role</a>
                </li>
                <li class="list-group-item">
                    <a href="controlServlet?action=listLog">Log</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-header">Edit account</div>
                        <div class="card-body">
                            <%
                                Account account = (Account) request.getAttribute("account");
                            %>
                            <form action="controlServlet" method="post">
                                <input type="hidden" name="account_id" value="<%= account.getAccount_id() %>">
                                <div class="form-group">
                                    <label for="full_name">Account Name</label>
                                    <input type="text" class="form-control" id="full_name" name="full_name" value="<%= account.getFull_name() %>">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" value="<%= account.getPassword() %>">
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" value="<%= account.getEmail() %>">
                                </div>
                                <div class="form-group">
                                    <label for="phone">Phone</label>
                                    <input type="number" class="form-control" id="phone" name="phone" value="<%= account.getPhone() %>">
                                </div>
                                <div class="form-group">
                                    <label for="status">Status</label>
                                    <select class="form-control" id="status" name="status">
                                        <option value="1" <%= account.getStatus().getValue() == 1 ? "selected" : "" %>>Active</option>
                                        <option value="0" <%= account.getStatus().getValue() == 0 ? "selected" : "" %>>DEACTIVATE</option>
                                        <option value="-1" <%= account.getStatus().getValue() == -1 ? "selected" : "" %>>Delete</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <input type="hidden" name="action" value="editAccount">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
