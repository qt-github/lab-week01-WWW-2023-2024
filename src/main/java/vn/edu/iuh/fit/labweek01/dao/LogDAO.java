package vn.edu.iuh.fit.labweek01.dao;

import vn.edu.iuh.fit.labweek01.models.Log;
import vn.edu.iuh.fit.labweek01.models.Account;
import vn.edu.iuh.fit.labweek01.models.Status;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    public List<Log> getAll() throws SQLException {
        List<Log> listLog = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM log JOIN account ON log.account_id = account.account_id ORDER BY login_time DESC");
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Account account = new Account(rs.getString("account_id"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"), Status.transferStatus(rs.getInt("status")));
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
            return listLog;
        }
    }

    public Log getById(String id) throws SQLException {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM log WHERE id = ?")) {
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()) {
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
            }
        }
        return null;
    }

    public boolean create(Log log) throws SQLException {
        try (Connection connection = ConnectDB.getConnection()) {
            if (log.getLogin_time() == null) {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO log(account_id, logout_time, notes) VALUES (?, ?, ?)")) {
                    statement.setString(1, log.getAccount_id().getAccount_id());
                    statement.setNull(2, Types.DATE);
                    statement.setString(3, log.getNotes());
                    statement.executeUpdate();
                }
            } else {
                try (PreparedStatement statement = connection.prepareStatement("INSERT INTO log(account_id, login_time, logout_time, notes) VALUES (?, ?, ?, ?)")) {
                    statement.setString(1, log.getAccount_id().getAccount_id());
                    statement.setDate(2, new Date(log.getLogin_time().getTime()));
                    statement.setNull(3, Types.DATE);
                    statement.setString(4, log.getNotes());
                    statement.executeUpdate();
                }
            }
            return true;
        }
    }

    public boolean update(Log log) throws SQLException {
        try (Connection connection = ConnectDB.getConnection()) {
            Log logLast = this.getLastLogByAccountId(log.getAccount_id().getAccount_id());
            try (PreparedStatement statement = connection.prepareStatement("UPDATE log SET account_id = ?, logout_time = ?, notes = ? WHERE id = ?")) {
                statement.setString(1, log.getAccount_id().getAccount_id());
                statement.setDate(2, new Date(log.getLogout_time().getTime()));
                statement.setString(3, log.getNotes());
                statement.setString(4, logLast.getId());
                statement.executeUpdate();
            }
            return true;
        }
    }

    public boolean delete(String id) throws SQLException {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM log WHERE id = ?")) {
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        }
    }

    public Log getLastLogByAccountId(String accountId) throws SQLException {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM log WHERE account_id = ? ORDER BY id DESC LIMIT 1")) {
            statement.setString(1, accountId);
            try (ResultSet rs = statement.executeQuery()) {
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
            }
        }
        return null;
    }
}