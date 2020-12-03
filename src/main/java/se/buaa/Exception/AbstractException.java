package se.buaa.Exception;

public class AbstractException extends Exception{
    protected String errorCode;
    protected String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public AbstractException() {}

    public AbstractException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
