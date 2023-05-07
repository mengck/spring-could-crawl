package com.mengck.common.response;

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


    public static <T> ResponseResult<T> ok(){
        return restResult(200,"success",null);
    }


    public static <T> ResponseResult<T> fail(String failMsg){
        return restResult(500,failMsg,null);
    }

    public static <T> ResponseResult<T> fail(int code,String failMsg){
        return restResult(code,failMsg,null);
    }

    private static <T> ResponseResult<T> restResult(int code, String msg,T data){

        ResponseResult result = new ResponseResult(code,msg,data);
        return result ;
    }


}
