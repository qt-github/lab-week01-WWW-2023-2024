package vn.edu.iuh.fit.labweek01.modules;

public enum Status {
    //1-active, 0-deactive, -1-deleted
    ACTIVE(1), DEACTIVE(0), DELETED(-1);

    private int value;

    private Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
