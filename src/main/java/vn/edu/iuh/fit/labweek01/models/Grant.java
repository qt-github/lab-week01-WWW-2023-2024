package vn.edu.iuh.fit.labweek01.models;

public enum Grant {
    ENABLE(1), DISABLE(0);

    private final int value;
    Grant(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
    public static Grant transferGrant(int value){
        for(Grant grant : Grant.values()){
            if(grant.getValue() == value){
                return grant;
            }
        }
        throw new IllegalArgumentException(value + "Not found Grant");
    }
}
