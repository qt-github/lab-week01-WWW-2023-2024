<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.labweek01.modules.Log" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Sep-23
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log Page</title>
    <link rel="stylesheet" href="LogPage.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Log List</h1>
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
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th scope="col" style="width: 10%;">ID</th>
                        <th scope="col" style="width: 20%;">Account Name</th>
                        <th scope="col" style="width: 20%;">Email</th>
                        <th scope="col" style="width: 20%;">Phone</th>
                        <th scope="col" style="width: 20%;">Login Time</th>
                        <th scope="col" style="width: 20%;">Logout Time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object logObj = request.getAttribute("listLog");

                        if (logObj instanceof List) {
                            List<Log> listLog = (List<Log>) logObj;

                            for (Log log : listLog) { %>
                    <tr>
                        <td scope="row">
                            <%= log.getId() %>
                        </td>
                        <td>
                            <%= log.getAccount_id().getFull_name() %>
                        </td>
                        <td>
                            <%= log.getAccount_id().getEmail() %>
                        </td>
                        <td>
                            <%= log.getAccount_id().getPhone() %>
                        </td>
                        <td>
                            <%= log.getLogin_time() %>
                        </td>
                        <td>
                            <% if (log.getLogout_time() == null) {
                                out.println("Chưa logout");
                            } else {
                                out.println(log.getLogout_time());
                            }
                            %>
                        </td>
                    </tr>
                    <% }
                    }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
