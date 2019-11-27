package com.nochi.pet.manage.modular.base.entity;

public class Result<T>{

    private Integer code;
    private String msg;
    private T data;

    public Result success(T data){
        return new Result(200,"success",data);
    }

    public Result fail(String message){
        return new Result(500,message,null);
    }
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
