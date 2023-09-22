<%@ page import="vn.edu.iuh.fit.labweek01.modules.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Sep-23
  Time: 1:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="DashboardPage.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <%-- Lấy thông tin từ các cookie --%>
    <% Cookie[] cookies = request.getCookies(); %>
    <% String accountId = null, fullName = null, email = null, phone = null, status = null; %>
    <% if (cookies != null) { %>
    <% for (Cookie cookie : cookies) { %>
    <% if ("full_name".equals(cookie.getName())) { %>
    <% fullName = cookie.getValue(); %>
    <% } else if ("email".equals(cookie.getName())) { %>
    <% email = cookie.getValue(); %>
    <% } else if ("phone".equals(cookie.getName())) { %>
    <% phone = cookie.getValue(); %>
    <% } else if ("status".equals(cookie.getName())) { %>
    <% status = cookie.getValue(); %>
    <% } else if ("account_id".equals(cookie.getName())) { %>
    <% accountId = cookie.getValue(); %>
    <% } %>
    <% } %>
    <% } %>
    <div class="d-flex align-items-center justify-content-between">
        <h1>Dashboard</h1>
        <!-- Logout Button -->
        <form action="controlServlet" method="get">
            <button type="submit"
                    class="btn btn-danger"
                    onclick="window.location.href = 'control-servlet?action=logout&id=<%= accountId %>'">
                Logout
            </button>
            <input type="hidden" name="action" value="logout">
        </form>
    </div>
    <div class="row">
        <div class="col-md-3">
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
        <div class="col-md-9">
            <div class="row">
                <div class="col-md-5">
                    <div class="card">
                        <div class="card-header">Information Account</div>
                        <div class="card-body">
                            <%-- Hiển thị thông tin từ các cookie --%>
                            <p><strong>Full Name:</strong>
                                <%= fullName %>
                            </p>
                            <p><strong>Email:</strong>
                                <%= email %>
                            </p>
                            <p><strong>Phone:</strong>
                                <%= phone %>
                            </p>
                            <p><strong>Status:</strong>
                                <span class="badge badge-primary">
                                    <%= status %>
                                </span>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header">Role off account</div>
                        <div class="card-body">
                            <span>Name: </span>
                            <% Object listRoleByAccount = request.getAttribute("listRoleByAccount");
                                if (listRoleByAccount != null) {
                                    List<Role> roles = (List<Role>) listRoleByAccount;
                                    for (Role role : roles) {
                            %>
                            <span>
                                <%= role.getRole_name() %>,
                            </span>
                            <% }
                            } else { %>
                            <span>Not found</span>
                            <% } %>
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
