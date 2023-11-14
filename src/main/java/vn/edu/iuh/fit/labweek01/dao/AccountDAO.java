package vn.edu.iuh.fit.labweek01.dao;

import vn.edu.iuh.fit.labweek01.models.Account;
import vn.edu.iuh.fit.labweek01.models.Status;
import vn.edu.iuh.fit.labweek01.utils.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO {
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO account (account_id, full_name, password, email, phone, status) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_ACCOUNT_SQL = "UPDATE account SET full_name = ?, email = ? WHERE account_id = ?";
    private static final String DELETE_ACCOUNT_SQL = "DELETE FROM account WHERE account_id = ?";
    private static final String SELECT_ACCOUNT_BY_ID_SQL = "SELECT * FROM account WHERE account_id=?";
    private static final String SELECT_ACCOUNT_BY_EMAIL_PASSWORD_SQL = "SELECT * FROM account WHERE email=? AND password=?";
    private static final String SELECT_ALL_ACCOUNTS_SQL = "SELECT * FROM account";

    // Create account
    public boolean createAccount(Account account) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
            statement.setString(1, account.getAccount_id());
            statement.setString(2, account.getFull_name());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getEmail());
            statement.setString(5, account.getPhone());
            statement.setInt(6, account.getStatus().getValue());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update account
    public boolean updateAccount(Account account, String id) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_SQL)) {
            statement.setString(1, account.getFull_name());
            statement.setString(2, account.getEmail());
            statement.setString(3, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete account
    public boolean deleteAccount(String id) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ACCOUNT_SQL)) {
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get account by id use Optional<> to avoid NullPointerException
    public Optional<Account> getAccountById(String id) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID_SQL)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Account account = new Account();
                if (resultSet.next()) {
                    getAccountInfo(resultSet, account);
                }
                return Optional.of(account);
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    // Get account info
    private void getAccountInfo(ResultSet resultSet, Account account) throws SQLException {
        account.setAccount_id(resultSet.getString("account_id"));
        account.setFull_name(resultSet.getString("full_name"));
        account.setPassword(resultSet.getString("password"));
        account.setEmail(resultSet.getString("email"));
        account.setPhone(resultSet.getString("phone"));
        Status status = Status.transferStatus(resultSet.getInt("status"));
        account.setStatus(status);
    }

    // Logon account by email and password use Optional<> to avoid NullPointerException
    public Optional<Account> logonAccount(String email, String password) {
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_BY_EMAIL_PASSWORD_SQL)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account();
                    getAccountInfo(resultSet, account);
                    return Optional.of(account);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Get all account return List<Account>
    public List<Account> getAllAccount() {
        List<Account> lstAccount = new ArrayList<>();
        try (Connection connection = ConnectDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Account account = new Account();
                getAccountInfo(resultSet, account);
                lstAccount.add(account);
            }
            return lstAccount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
