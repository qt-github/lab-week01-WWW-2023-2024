package vn.edu.iuh.fit.labweek01.dao;

import vn.edu.iuh.fit.labweek01.modules.Log;
import vn.edu.iuh.fit.labweek01.modules.Account;
import vn.edu.iuh.fit.labweek01.modules.Status;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    public List<Log> getAll() throws SQLException, ClassNotFoundException {
        List<Log> listLog = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from log join account on log.account_id = account.account_id order by login_time desc";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("account_id"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"), Status.transferStatus(rs.getInt("status")));
                //if logout_time is null
                if (rs.getDate("logout_time") == null) {
                    listLog.add(new Log(rs.getString("id"),
                            account,
                            new Date(rs.getDate("login_time").getTime()),
                            rs.getString("notes")));
                } else {
                    listLog.add(new Log(rs.getString("id"),
                            account,
                            new Date(rs.getDate("login_time").getTime()),
                            new Date(rs.getDate("logout_time").getTime()),
                            rs.getString("notes")));
                }
            }

            System.out.println(listLog);
            return listLog;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //get by id
    public Log getById(String id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from log where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("account_id"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        Status.transferStatus(rs.getInt("status")));
                return new Log(rs.getString("id"),
                        account,
                        new Date(rs.getDate("login_time").getTime()),
                        new Date(rs.getDate("logout_time").getTime()),
                        rs.getString("notes"));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //create
    public boolean create(Log log) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            if (log.getLogin_time() == null) {
                String sql = "insert into log(account_id, logout_time, notes) values(?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, log.getAccount_id().getAccount_id());
                statement.setDate(2, new Date(log.getLogout_time().getTime()));
                statement.setString(3, log.getNotes());
                statement.executeUpdate();
            } else {
                String sql = "insert into log(account_id, login_time, logout_time, notes) values(?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, log.getAccount_id().getAccount_id());
                statement.setDate(2, new Date(log.getLogin_time().getTime()));
                //set value null
                statement.setDate(3, null);
                statement.setString(4, log.getNotes());
                statement.executeUpdate();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //update
    public boolean update(Log log) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            //get log last
            Log logLast = this.getLastLogByAccountId(log.getAccount_id().getAccount_id());
            String sql = "update log set account_id = ?, logout_time = ?, notes = ? where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, log.getAccount_id().getAccount_id());
            statement.setDate(2, new Date(log.getLogout_time().getTime()));
            statement.setString(3, log.getNotes());
            statement.setString(4, logLast.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //delete
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "delete from log where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Log getLastLogByAccountId(String accountId) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        System.out.println(accountId);
        try {
            String sql = "select * from log where account_id = ? order by id desc limit 1";
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("account_id"));
                if (rs.getDate("logout_time") == null) {
                    return new Log(rs.getString("id"),
                            account,
                            new Date(rs.getDate("login_time").getTime()),
                            rs.getString("notes"));
                } else {
                    return new Log(rs.getString("id"),
                            account,
                            new Date(rs.getDate("login_time").getTime()),
                            new Date(rs.getDate("logout_time").getTime()),
                            rs.getString("notes"));
                }
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
