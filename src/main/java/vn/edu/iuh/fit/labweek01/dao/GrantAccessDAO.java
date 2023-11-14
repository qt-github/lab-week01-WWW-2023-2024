package vn.edu.iuh.fit.labweek01.dao;

import vn.edu.iuh.fit.labweek01.models.*;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessDAO {
    private final Connection connection;

    public GrantAccessDAO() throws ClassNotFoundException, SQLException {
        connection = ConnectDB.getConnection();
    }

    public List<GrantAccess> getAll() throws SQLException {
        List<GrantAccess> listGrantAccess = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM grant_access " +
                        "JOIN account ON grant_access.account_id = account.account_id " +
                        "JOIN role ON grant_access.role_id = role.role_id"
        )) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                        rs.getString("account_id"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        Status.transferStatus(rs.getInt("status"))
                );
                Role role = new Role(
                        rs.getString("role_id"),
                        rs.getString("role_name"),
                        rs.getString("description"),
                        Status.transferStatus(rs.getInt("status"))
                );
                listGrantAccess.add(new GrantAccess(
                        role,
                        account,
                        Grant.transferGrant(rs.getInt("is_grant")),
                        rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listGrantAccess;
    }

    public GrantAccess getById(String accountId, String roleId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM grant_access " +
                        "JOIN account ON grant_access.account_id = account.account_id " +
                        "JOIN role ON grant_access.role_id = role.role_id " +
                        "WHERE grant_access.account_id = ? AND grant_access.role_id = ?"
        )) {
            statement.setString(1, accountId);
            statement.setString(2, roleId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                        rs.getString("account_id"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        Status.transferStatus(rs.getInt("status"))
                );
                Role role = new Role(
                        rs.getString("role_id"),
                        rs.getString("role_name"),
                        rs.getString("description"),
                        Status.transferStatus(rs.getInt("status"))
                );
                return new GrantAccess(
                        role,
                        account,
                        Grant.transferGrant(rs.getInt("is_grant")),
                        rs.getString("note")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean create(GrantAccess grantAccess) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)"
        )) {
            statement.setString(1, grantAccess.getAccount_id().getAccount_id());
            statement.setString(2, grantAccess.getRole_id().getRole_id());
            statement.setInt(3, grantAccess.isIs_grant().getValue());
            statement.setString(4, grantAccess.getNote());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(GrantAccess grantAccess) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE grant_access SET is_grant=?, note=? WHERE account_id=? AND role_id=?"
        )) {
            statement.setInt(1, grantAccess.isIs_grant().getValue());
            statement.setString(2, grantAccess.getNote());
            statement.setString(3, grantAccess.getAccount_id().getAccount_id());
            statement.setString(4, grantAccess.getRole_id().getRole_id());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String accountId, String roleId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM grant_access WHERE account_id=? AND role_id=?"
        )) {
            statement.setString(1, accountId);
            statement.setString(2, roleId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean grantPermission(String accountId, String roleIds, String grantType, String note) throws SQLException {
        String[] arrRoleIds = roleIds.split(",");
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)"
        )) {
            for (String roleId : arrRoleIds) {
                statement.setString(1, accountId);
                statement.setString(2, roleId);
                statement.setString(3, grantType);
                statement.setString(4, note);
                statement.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}