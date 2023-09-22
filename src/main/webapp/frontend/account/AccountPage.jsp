<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.labweek01.modules.Account" %>
<%@ page import="vn.edu.iuh.fit.labweek01.modules.Role" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Sep-23
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Page</title>
    <link rel="stylesheet" href="styles/AccountPage.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"/>
</head>
<body>
<div class="container">
    <div class="d-flex align-items-center justify-content-between">
        <h1>Account List</h1>
        <button type="button" class="btn btn-success mt-1"
                onclick="window.location.href = 'controlServlet?action=addAccount'">
            <i class="fas fa-plus"></i> Add account
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
                        <th scope="col" style="width: 10%;">ID</th>
                        <th scope="col" style="width: 20%;">Full Name</th>
                        <th scope="col" style="width: 20%;">Email</th>
                        <th scope="col" style="width: 20%;">Phone</th>
                        <th scope="col" style="width: 20%;">Status</th>
                        <th scope="col" style="width: 10%;">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Object accountObj = request.getAttribute("listAccount");

                        if (accountObj != null && accountObj instanceof List) {
                            List<Account> listAccount = (List<Account>) accountObj;

                            for (Account account : listAccount) { %>
                    <tr>
                        <td scope="row">
                            <%= account.getAccount_id()%>
                        </td>
                        <td>
                            <%= account.getFull_name() %>
                        </td>
                        <td>
                            <%= account.getEmail() %>
                        </td>
                        <td>
                            <%= account.getPhone() %>
                        </td>
                        <td>
                            <%
                                String status = String.valueOf(account.getStatus());
                                String color = "success";
                                if (status.equals("Active")) {
                                    color = "success";
                                } else if (status.equals("DEACTIVATE")) {
                                    color = "warning";
                                } else if (status.equals("PENDING")) {
                                    color = "danger";
                                }
                            %>
                            <span class="badge badge-<%= color %>">
                                <%= status %>
                            </span>
                        </td>
                        <td class="d-flex">
                            <button type="button" class="btn btn-warning mr-2"
                                    onclick="window.location.href = 'controlServlet?action=editAccount&id=<%= account.getAccount_id() %>'">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button type="button" class="btn btn-danger mr-2"
                                    onclick="deleteAccount('<%= account.getAccount_id() %>')">
                                <i class="fas fa-trash-alt"></i>
                            </button>
                            <button type="button" class="btn btn-primary" data-toggle="modal"
                                    data-target="#grantPermissionModal<%= account.getAccount_id() %>">
                                Grant permissions
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="grantPermissionModal<%= account.getAccount_id() %>" tabindex="-1"
                                 role="dialog" aria-labelledby="grantPermissionModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="grantPermissionModalLabel">Grant Permissions
                                                to: <%= account.getFull_name() %>
                                            </h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="controlServlet" method="post">
                                                <div>
                                                    <label class="form-label select-label">Select Roles:</label>
                                                    <select class="select" multiple
                                                            id="roleSelect<%= account.getAccount_id() %>"
                                                            style="width: 200px;">
                                                        <%
                                                            Object roleObj = request.getAttribute("listRole");
                                                            if (roleObj != null && roleObj instanceof List) {
                                                                List<Role> listRoles = (List<Role>) roleObj;
                                                                for (Role role : listRoles) {
                                                        %>
                                                        <option value="<%= role.getRole_id() %>"><%= role.getRole_name() %>
                                                        </option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="grantType">Grant Type: </label>
                                                    <select class="form-control" id="grantType" name="grantType">
                                                        <option value="1">Active</option>
                                                        <option value="0">Disable</option>
                                                    </select>
                                                </div >
                                                <div class="form-group">
                                                    <label for="note">Note: </label>
                                                    <textarea class="form-control" id="note" name="note" rows="3"></textarea>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeModel()">Close
                                            </button>
                                            <button type="button" class="btn btn-primary"
                                                    onclick="saveRoleChanges('<%= account.getAccount_id() %>')">Save
                                                changes
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
    function deleteAccount(id) {
        if (confirm("Are you sure?")) {
            window.location.href = "controlServlet?action=deleteAccount&id=" + id;
        }
    }

    $(document).ready(function () {
        // Sử dụng Select2 cho dropdown
        $('[id^=roleSelect]').select2();
    });

    let selectedRoles = [];

    function saveRoleChanges(accountId) {
        // Lấy danh sách các ID của vai trò đã chọn
        const selectedRoleIds = $('#roleSelect' + accountId).val();

        if (selectedRoleIds == null) {
            alert("Please select at least one role!");
            return;
        }  // Lưu danh sách ID của vai trò đã chọn trong đối tượng JavaScript
        selectedRoles[accountId] = selectedRoleIds;

        // Để kiểm tra xem danh sách đã được lưu hay chưa, bạn có thể in ra console:
        console.log(`Selected roles for Account ID ${accountId}:`, selectedRoles[accountId]);

        //convert roleids to string
        const roleIds = selectedRoles[accountId].toString();

        //method post, action controlServlet
        $.post("controlServlet", {
            action: "grantPermission",
            accountId: accountId,
            roleIds: roleIds,
            grantType: $("#grantType").val(),
            note: $("#note").val()
        });

        selectedRoles = [];
    }

    function closeModel() {
        selectedRoles = [];
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</html>
