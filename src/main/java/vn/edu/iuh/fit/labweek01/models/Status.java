package vn.edu.iuh.fit.labweek01.models;


public enum Status {
    //1-active, 0-deactivate, -1-deleted
    ACTIVE(1), DEACTIVATE(0), DELETED(-1);

    private final int value;
    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static Status transferStatus(int value){
        for(Status status : Status.values()){
            if (status.getValue() == value){
                return status;
            }
        }
        throw new IllegalArgumentException(value + "Not found Status");
    }

}
