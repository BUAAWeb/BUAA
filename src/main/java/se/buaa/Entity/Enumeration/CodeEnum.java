package se.buaa.Entity.Enumeration;

public enum CodeEnum {
    success;

    public int getCode(){
        switch (this){
            case success:
                return 200;
            default:
                return 404;
        }
    }

}
