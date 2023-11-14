<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.labweek01.models.Log" %><%--
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
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Include jQuery and Select2 CSS & JS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
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
