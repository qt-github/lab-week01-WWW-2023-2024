package vn.edu.iuh.fit.labweek01.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mariadb.jdbc.Connection;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "controlServlet", urlPatterns = "/controlServlet")
public class ControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
