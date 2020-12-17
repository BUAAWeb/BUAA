package se.buaa.Entity.Enumeration;

public enum CodeEnum {
    success,sortNotFound,pageNotInteger,pageLessThanOne,pageOutOfRange,documentNotExist,docIdNotExist,noSort,noPage;

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
            case documentNotExist:
                return "305";
            case docIdNotExist:
                return "306";
            case noSort:
                return "307";
            case noPage:
                return "308";
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
            case noPage:
                return "No page parm";
            case noSort:
                return "No sort param";
            case sortNotFound:
                return "Sort way is not exist";
            case pageLessThanOne:
                return "Page is less than 1";
            case pageOutOfRange:
                return "Page is out of range";
            case documentNotExist:
                return "Document is not exist";
            case docIdNotExist:
                return "The Document ID you search is not exist";
            default:
                return "404";
        }
    }

}
