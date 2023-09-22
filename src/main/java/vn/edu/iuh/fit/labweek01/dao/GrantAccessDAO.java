package vn.edu.iuh.fit.labweek01.dao;

import vn.edu.iuh.fit.labweek01.modules.*;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrantAccessDAO {
    public List<GrantAccess> getAll() throws SQLException, ClassNotFoundException {
        List<GrantAccess> listGrantAccess = new ArrayList<>();
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from grant_access " +
                    "join account on grant_access.account_id = account.account_id " +
                    "join role on grant_access.role_id = role.role_id)";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getString("account_id"),
                        rs.getString("full_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone"), 
                        Status.transferStatus(rs.getInt("status")));
                Role role = new Role(rs.getString("role_id"), 
                        rs.getString("role_name"),
                        rs.getString("description"),
                        Status.transferStatus(rs.getInt("status")));
                listGrantAccess.add(new GrantAccess(role,
                        account,
                        Grant.transferGrant(rs.getInt("is_grant")),
                        rs.getString("note")));
            }

            return listGrantAccess;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //get by id
    public GrantAccess getById(String accountId, String roleId) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from grant_access " +
                    "join account on grant_access.account_id = account.account_id " +
                    "join role on grant_access.role_id = role.role_id " +
                    "where grant_access.account_id = ? and grant_access.role_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountId);
            statement.setString(2, roleId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"),
                        rs.getString("email"), rs.getString("phone"), Status.transferStatus(rs.getInt("status")));
                Role role = new Role(rs.getString("role_id"), rs.getString("role_name"), rs.getString("description"),
                        Status.transferStatus(rs.getInt("status")));
                return new GrantAccess(role, account, Grant.transferGrant(rs.getInt("is_grant")), rs.getString("note"));
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //create
    public boolean create(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
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
    //update
    public boolean update(GrantAccess grantAccess) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE grant_access SET is_grant=?, note=? WHERE account_id=? and role_id=?";
            statement = connection.prepareStatement(sql);
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
    //delete
    public boolean delete(String accountId, String roleId) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM grant_access WHERE account_id=? and role_id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountId);
            statement.setString(2, roleId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean grantPermission(String accountId, String roleIds, String grantType, String note) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        //get list roles frome roleIds string ","
        String[] arrRoleIds = roleIds.split(",");
        try {
            String sql = "INSERT INTO grant_access(account_id, role_id, is_grant, note) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
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
