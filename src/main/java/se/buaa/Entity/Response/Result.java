package se.buaa.Entity.Response;

import se.buaa.Exception.AbstractException;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public Result() {}

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg =msg;
        this.data = data;
    }

    public static Result Success() {
        return new Result("200", "");
    }

    public static Result Success(Object data) {
        return new Result("200", "", data);
    }

    public static Result Success(String msg) {
        return new Result("200", msg);
    }

    public static Result Error() { return new Result(); }

    public static Result Error(String code, String msg) {
        return new Result(code, msg, null);
    }
    public static Result Error(String code, String msg,Object data) {
        return new Result(code, msg, data);
    }

    public static Result Error(AbstractException e) {
        return new Result(e.getErrorCode(), e.getErrorMsg());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
