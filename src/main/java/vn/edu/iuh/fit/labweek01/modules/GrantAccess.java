package vn.edu.iuh.fit.labweek01.modules;

public class GrantAccess {
    //account_id, role_id, is_grant, note
    private Account account_id;
    private Role role_id;
    private boolean is_grant;
    private String note;

    public GrantAccess() {
    }

    public GrantAccess(Account account_id, Role role_id, boolean is_grant, String note) {
        this.account_id = account_id;
        this.role_id = role_id;
        this.is_grant = is_grant;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isIs_grant() {
        return is_grant;
    }

    public void setIs_grant(boolean is_grant) {
        this.is_grant = is_grant;
    }

    public Account getAccount_id() {
        return account_id;
    }

    public Role getRole_id() {
        return role_id;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account_id=" + account_id +
                ", role_id=" + role_id +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}
