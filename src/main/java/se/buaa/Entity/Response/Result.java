package se.buaa.Entity.Response;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg =msg;
        this.data = data;
    }

    public static Result Success() {
        return new Result(200, "");
    }

    public static Result Success(Object data) {
        return new Result(200, "", data);
    }

    public static Result Error(int code, String msg) {
        return new Result(code, msg, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
