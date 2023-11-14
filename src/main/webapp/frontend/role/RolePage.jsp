<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.labweek01.models.Role" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Sep-23
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role Page</title>
    <link rel="stylesheet" href="styles/RolePage.css"/>
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
        <h1>Role List</h1>
        <button type="button" class="btn btn-success mt-1" onclick="window.location.href = 'controlServlet?action=addRole'">
            <i class="fas fa-plus"></i> Add Role
        </button>
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
                        <th scope="col" style="width: 20%;">ID</th>
                        <th scope="col" style="width: 20%;">Name</th>
                        <th scope="col" style="width: 20%;">Description</th>
                        <th scope="col" style="width: 20%;">Status</th>
                        <th scope="col" style="width: 20%;">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object accountObj = request.getAttribute("listRole");

                        if (accountObj instanceof List) {
                            List<Role> listRole = (List<Role>) accountObj;

                            for (Role role : listRole) { %>
                    <tr>
                        <td scope="row">
                            <%= role.getRole_id() %>
                        </td>
                        <td>
                            <%= role.getRole_name() %>
                        </td>
                        <td>
                            <%
                                String description = role.getDescription();
                                if (description.length() > 20) {
                                    description = description.substring(0, 20) + "...";
                                }
                            %>
                            <%= description %>
                        </td>
                        <td>
                            <%
                                String status = String.valueOf(role.getStatus());
                                String color = "success";
                                switch (status) {
                                    case "Active":
                                        color = "success";
                                        break;
                                    case "DEACTIVATE":
                                        color = "warning";
                                        break;
                                    case "PENDING":
                                        color = "danger";
                                        break;
                                }
                            %>
                            <span class="badge badge-<%= color %>"><%= status %></span>
                        </td>
                        <td class="d-flex">
                            <button type="button" class="btn btn-warning mr-2" onclick="window.location.href = 'controlServlet?action=editRole&id=<%= role.getRole_id() %>'">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button type="button" class="btn btn-danger" onclick="deleteRole('<%= role.getRole_id() %>')">
                                <i class="fas fa-trash-alt"></i>
                            </button>
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
<script>
    function deleteRole(id) {
        if (confirm("Are you sure?")) {
            window.location.href = "controlServlet?action=deleteRole&id=" + id;
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
