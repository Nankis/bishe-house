package com.cj.bishe.common;

import com.cj.bishe.enums.ResultMsgEnum;
import com.cj.bishe.exception.ServiceException;

import java.io.Serializable;

public class HttpResult<T> implements Serializable {

    private boolean success;
    private T data;
    private String code;
    private String msg;

    public HttpResult() {
    }

    private HttpResult(boolean success, String code, String msg) {
        this.success = success;
        this.code = code;
        this.msg = msg;
    }

    public static <T> HttpResult<T> success() {
        HttpResult<T> httpResult = new HttpResult<T>();
        httpResult.setSuccess(true);
        httpResult.setCode("200");
        httpResult.setMsg("成功");
        return httpResult;
    }

    public static <T> HttpResult<T> success(T data) {
        HttpResult<T> httpResult = success();
        httpResult.setData(data);
        return httpResult;
    }

    public static <T> HttpResult<T> fail(String code, String message) {
        return new HttpResult<T>(false, code, message);
    }

    public static <T> HttpResult<T> fail(ResultMsgEnum resultMsgEnum) {
        return new HttpResult<T>(false, resultMsgEnum.getCode(), resultMsgEnum.getValue());
    }

    public static <T> HttpResult<T> fail(ResultMsgEnum resultMsgEnum, Object... args) {
        return new HttpResult<T>(false, resultMsgEnum.getCode(), String.format(resultMsgEnum.getValue(), args));
    }

    public static <T> HttpResult<T> fail(ServiceException e) {
        return new HttpResult<T>(false, e.getErrorCode(), e.getErrorMsg());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}