package vn.edu.iuh.fit.labweek01.modules;
import java.util.Objects;


public class Account {
    //account_id, full_name, password, email, phone, status
    private String account_id;
    private String full_name;
    private String password;
    private String email;
    private String phone;
    private Status status;

    public Account() {
    }

    public Account(String account_id, String full_name, String password, String email, String phone, Status status) {
        this.account_id = account_id;
        this.full_name = full_name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Account(String account_id) {
        this.account_id=account_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String id) {
        this.account_id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id=" + account_id +
                ", full_name='" + full_name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return getAccount_id() == account.getAccount_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccount_id());
    }
}
