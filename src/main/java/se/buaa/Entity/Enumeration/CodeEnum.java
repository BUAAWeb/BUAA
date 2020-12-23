package se.buaa.Entity.Enumeration;

public enum CodeEnum {
    success,error,sortNotFound,pageNotInteger,pageLessThanOne,
    pageOutOfRange,documentNotExist,docIdNotExist,noSort,noPage,
    noUser,yearNotInteger,noResult,noExpert;

    public String getCode(){
        switch (this){
            case success:
                return "200";
            case noResult:
                return "100";
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
            case error:
                return "309";
            case noUser:
                return "310";
            case yearNotInteger:
                return "311";
            case noExpert:
                return "312";
            default:
                return "404";
        }
    }

    public String toString(){
        switch (this){
            case success:
                return "success";
            case noResult:
                return "No search Result";
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
            case noUser:
                return "No User ID";
            case yearNotInteger:
                return "Year is not a number";
            case noExpert:
                return "No expert";
            case error:
                return "error";
            default:
                return "404";
        }
    }

}
