package com.send.back.domain.response;


public class Result {
    private int status;//返回状态
    private Object info; //返回信息
    private Object returnMessage;//返回数据

    public Result() {}

    public Result(int status, Object info, Object returnMessage) {
        this.status = status;
        this.info = info;
        this.returnMessage = returnMessage;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(Object returnMessage) {
        this.returnMessage = returnMessage;
    }
}