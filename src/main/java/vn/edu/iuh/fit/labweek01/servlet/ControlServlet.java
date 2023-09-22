package vn.edu.iuh.fit.labweek01.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.labweek01.dao.AccountDAO;
import vn.edu.iuh.fit.labweek01.dao.GrantAccessDAO;
import vn.edu.iuh.fit.labweek01.dao.LogDAO;
import vn.edu.iuh.fit.labweek01.dao.RoleDAO;
import vn.edu.iuh.fit.labweek01.modules.Account;
import vn.edu.iuh.fit.labweek01.modules.Log;
import vn.edu.iuh.fit.labweek01.modules.Role;
import vn.edu.iuh.fit.labweek01.modules.Status;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@WebServlet(name = "controlServlet", urlPatterns = "/controlServlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RoleDAO roleDAO = new RoleDAO();
        AccountDAO accountDAO = new AccountDAO();
        GrantAccessDAO grantAccessDAO = new GrantAccessDAO();

        switch (action) {
            case "listRole":
                try {
                    List<Role> listRole = roleDAO.getAll();
                    req.setAttribute("listRole", listRole);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/role/RolePage.jsp");
                    dispatcher.forward(req, resp);
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "editRole":
                try {
                    Role role = roleDAO.getById(req.getParameter("id"));
                    req.setAttribute("role", role);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/role/UpdateRolePage.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "deleteRole":
                try {
                    boolean res = roleDAO.delete(String.valueOf(req.getParameter("id")));
                    if (res) {
                        resp.sendRedirect("controlServlet?action=listRole");
                        //show toast delete success
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Delete success');");
                        out.println("location='controlServlet?action=listRole';");
                        out.println("</script>");
                    } else {
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Delete failed');");
                        out.println("location='controlServlet?action=listRole';");
                        out.println("</script>");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "addRole": {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/role/AddRolePage.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            case "dashboard":
                //get role of account
                try {
                    //get value cookie by key account_id
                    Cookie[] cookies = req.getCookies();
                    String accountId = "";
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("account_id")) {
                            accountId = cookie.getValue();
                        }
                    }
                    //get role of account
                    List<Role> listRoleByAccount = roleDAO.getRoleByAccount(accountId);
                    req.setAttribute("listRoleByAccount", listRoleByAccount);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/dashboard/DashboardPage.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "logout": {
                // Lấy danh sách các cookie hiện tại
                Cookie[] cookies = req.getCookies();
                //get account_id cookie
                String accountId = "";
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("account_id")) {
                        accountId = cookie.getValue();
                    }
                }
                //update logout_time
                Log log = new Log();
                log.setAccount_id(new Account(accountId));
                log.setLogout_time(new Date(System.currentTimeMillis()));
                log.setNotes("logout");
                try {
                    boolean res = new LogDAO().update(log);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                if (cookies != null) {
                    // Lặp qua từng cookie và đặt thời gian sống của nó thành 0 để xóa cookie
                    for (Cookie cookie : cookies) {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
                }

                RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/login/LoginPage.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            case "listAccount":
                try {
                    List<Account> listAccount = accountDAO.getAllAccount();
                    List<Role> listRole = roleDAO.getAll();
                    req.setAttribute("listAccount", listAccount);
                    req.setAttribute("listRole", listRole);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/account/AccountPage.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "editAccount": {
                Optional<Account> account = accountDAO.getAccountById(req.getParameter("id"));
                req.setAttribute("account", account.get());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/account/UpdateAccountPage.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            case "deleteAccount":
                if (req.getParameter("id").equals(req.getCookies()[0].getValue())) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Can not delete account login');");
                    out.println("location='controlServlet?action=listAccount';");
                    out.println("</script>");
                }
                boolean res = accountDAO.deleteAccount(String.valueOf(req.getParameter("id")));
                if (res) {
                    resp.sendRedirect("controlServlet?action=listAccount");
                    //show toast delete success
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete success');");
                    out.println("location='controlServlet?action=listAccount';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Delete failed');");
                    out.println("location='controlServlet?action=listAccount';");
                    out.println("</script>");
                }
                break;
            case "addAccount": {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/account/AddAccountPage.jsp");
                dispatcher.forward(req, resp);
                break;
            }
            case "listLog":
                try {
                    List<Log> listLog = new LogDAO().getAll();
                    req.setAttribute("listLog", listLog);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/log/LogPage.jsp");
                    dispatcher.forward(req, resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        AccountDAO accountDAO = new AccountDAO();
        RoleDAO roleDAO = new RoleDAO();
        GrantAccessDAO grantAccessDAO = new GrantAccessDAO();
        LogDAO logDAO = new LogDAO();

        if (action.equals("logon")) {
            try {
                Optional<Account> account = accountDAO.logonAccount(req.getParameter("email"),
                        req.getParameter("password"));
                //if account is not exist
                if (account.isEmpty()) {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Account is not exist');");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                } else {
                    System.out.println(account);
                    Log log = new Log();
                    log.setAccount_id(account.get());
                    //yyyy mm dd hh:mm:ss
                    log.setLogin_time(new Date(System.currentTimeMillis()));
                    log.setNotes("login");
                    logDAO.create(log);
                    //save account to cookie
                    Cookie account_id = new Cookie("account_id", account.get().getAccount_id());
                    Cookie full_name = new Cookie("full_name", account.get().getFull_name());
                    Cookie email = new Cookie("email", account.get().getEmail());
                    Cookie phone = new Cookie("phone", account.get().getPhone());
                    Cookie status = new Cookie("status", account.get().getStatus().toString());
                    resp.addCookie(account_id);
                    resp.addCookie(full_name);
                    resp.addCookie(email);
                    resp.addCookie(phone);
                    resp.addCookie(status);
                    //information of account

                    // redirect dashboard and info account
                    req.setAttribute("account", account.get());
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/frontend/dashboard/DashboardPage.jsp");
                    dispatcher.forward(req, resp);
                }
            } catch (SQLException | ClassNotFoundException | ServletException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("register")) {
            Account account = new Account();
            account.setAccount_id(req.getParameter("accountId"));
            account.setPassword(req.getParameter("password"));
            account.setFull_name(req.getParameter("fullName"));
            getAccountInfo(req, account);
            boolean res = false;
            res = accountDAO.createAccount(account);
            if (res) {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register success');");
                out.println("location='index.jsp';");
                out.println("</script>");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Register failed');");
                out.println("location='register.jsp';");
                out.println("</script>");
            }
        } else if (action.equals("editRole")) {
            Role role = new Role();
            role.setRole_id(req.getParameter("role_id"));
            role.setRole_name(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if (req.getParameter("status").equals("1")) {
                role.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                role.setStatus(Status.DEACTIVATE);
            } else {
                role.setStatus(Status.DELETED);
            }
            try {
                boolean res = roleDAO.update(role);
                if (res) {
                    resp.sendRedirect("controlServlet?action=listRole");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Update failed');");
                    out.println("location='controlServlet?action=listRole';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addRole")) {
            Role role = new Role();
            role.setRole_id(req.getParameter("role_id"));
            role.setRole_name(req.getParameter("role_name"));
            role.setDescription(req.getParameter("description"));
            if (req.getParameter("status").equals("1")) {
                role.setStatus(Status.ACTIVE);
            } else if (req.getParameter("status").equals("0")) {
                role.setStatus(Status.DEACTIVATE);
            } else {
                role.setStatus(Status.DELETED);
            }
            try {
                boolean res = roleDAO.add(role);
                if (res) {
                    resp.sendRedirect("controlServlet?action=listRole");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Add failed');");
                    out.println("location='controlServlet?action=listRole';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else if (action.equals("addAccount")) {
            Account account = new Account();
            account.setAccount_id(req.getParameter("account_id"));
            account.setPassword(req.getParameter("password"));
            account.setFull_name(req.getParameter("full_name"));
            getAccountInfo(req, account);
            boolean res = accountDAO.createAccount(account);
            if (res) {
                resp.sendRedirect("controlServlet?action=listAccount");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Add failed');");
                out.println("location='controlServlet?action=listAccount';");
                out.println("</script>");
            }
        } else if (action.equals("editAccount")) {
            Account account = new Account();
            account.setAccount_id(req.getParameter("account_id"));
            account.setPassword(req.getParameter("password"));
            account.setFull_name(req.getParameter("full_name"));
            getAccountInfo(req, account);
            boolean res = accountDAO.updateAccount(account, account.getAccount_id());
            if (res) {
                resp.sendRedirect("controlServlet?action=listAccount");
            } else {
                PrintWriter out = resp.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Update failed');");
                out.println("location='controlServlet?action=listAccount';");
                out.println("</script>");
            }
        } else if (action.equals("grantPermission")) {
            try {
                boolean res = grantAccessDAO.grantPermission(req.getParameter("accountId"),
                        req.getParameter("roleIds"),
                        req.getParameter("grantType"),
                        req.getParameter("note"));
                if (res) {
                    resp.sendRedirect("controlServlet?action=listAccount");
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Grant success');");
                    out.println("location='controlServlet?action=listAccount';");
                    out.println("</script>");
                } else {
                    PrintWriter out = resp.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Grant failed');");
                    out.println("location='controlServlet?action=listAccount';");
                    out.println("</script>");
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getAccountInfo(HttpServletRequest req, Account account) {
        account.setEmail(req.getParameter("email"));
        account.setPhone(req.getParameter("phone"));
        if (req.getParameter("status").equals("1")) {
            account.setStatus(Status.ACTIVE);
        } else if (req.getParameter("status").equals("0")) {
            account.setStatus(Status.DEACTIVATE);
        } else {
            account.setStatus(Status.DELETED);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

}
