package com.mengck.api.task.resp;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private int code;

    private String msg;

    private T data;

    public ResponseResult(){
        this.data = null;
        this.code =200;
        this.msg = "success";
    }

    public ResponseResult(T data){
        this.data = data;
        this.code = 200;
        this.msg = "success";
    }

    public ResponseResult(int code, String msg, T data){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }


}
