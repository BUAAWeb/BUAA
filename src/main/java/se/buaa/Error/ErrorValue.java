package se.buaa.Error;

public class ErrorValue {
    private String code;
    private String msg;
    private String className;
    private String shortCode;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getClassName() {
        return className;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}
