package se.buaa.Entity.Enumeration;

public enum CodeEnum {
    success,sortNotFound,pageNotInteger,pageLessThanOne,pageOutOfRange;

    public String getCode(){
        switch (this){
            case success:
                return "200";
            case pageNotInteger:
                return "301";
            case sortNotFound:
                return "302";
            case pageLessThanOne:
                return "303";
            case pageOutOfRange:
                return "304";
            default:
                return "404";
        }
    }

    public String toString(){
        switch (this){
            case success:
                return "success";
            case pageNotInteger:
                return "Page is not a number";
            case sortNotFound:
                return "Sort way is not exist";
            case pageLessThanOne:
                return "Page is less than 1";
            case pageOutOfRange:
                return "Page is out of range";
            default:
                return "404";
        }
    }

}
