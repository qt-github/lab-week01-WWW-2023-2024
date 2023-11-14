package vn.edu.iuh.fit.labweek01.dao;


import vn.edu.iuh.fit.labweek01.models.Role;
import vn.edu.iuh.fit.labweek01.models.Status;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    public List<Role> getAll() throws SQLException, ClassNotFoundException {


        List<Role> listRole = new ArrayList<>();

        try {
            Connection connection = ConnectDB.getConnection();
            String sql = "Select * from role order by role_name asc";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                listRole.add(new Role(rs.getString("role_id"),
                        rs.getString("role_name"),
                        rs.getString("description"),
                        Status.transferStatus(rs.getInt("status"))
                        )
                );
            }

            return listRole;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //get by id
    public Role getById(String id) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "Select * from role where role_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Role(rs.getString("role_id"),
                        rs.getString("role_name"),
                        rs.getString("description"),
                        Status.transferStatus(rs.getInt("status")));
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //add
    public boolean add(Role role) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO role(role_id, role_name, description, status) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, role.getRole_id());
            statement.setString(2, role.getRole_name());
            statement.setString(3, role.getDescription());
            statement.setInt(4, role.getStatus().getValue());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //update
    public boolean update(Role role) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE role SET role_name = ?, description = ?, status = ? WHERE role_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, role.getRole_name());
            statement.setString(2, role.getDescription());
            statement.setInt(3, role.getStatus().getValue());
            statement.setString(4, role.getRole_id());
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
            String sql = "DELETE FROM role WHERE role_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Role> getRoleByAccount(String accountId) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectDB.getConnection();
        PreparedStatement statement = null;
        List<Role> listRole = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account JOIN grant_access ON account.account_id = grant_access.account_id JOIN role ON grant_access.role_id = role.role_id WHERE account.account_id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, accountId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                listRole.add(new Role(rs.getString("role_id"),
                        rs.getString("role_name"),
                        rs.getString("description"),
                        Status.transferStatus(rs.getInt("status"))));
            }
            return listRole;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
