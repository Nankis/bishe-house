package com.cj.bishe.exception;

import com.cj.bishe.enums.ResultMsgEnum;

public class ServiceException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public ServiceException(ResultMsgEnum resultMsgEnum) {
        super(resultMsgEnum.getValue());
        this.errorCode = resultMsgEnum.getCode();
        this.errorMsg = resultMsgEnum.getValue();
    }

    public ServiceException(ResultMsgEnum resultMsgEnum, Object... args) {
        super(resultMsgEnum.getValue());
        this.errorCode = resultMsgEnum.getCode();
        this.errorMsg = String.format(resultMsgEnum.getValue(), args);
    }

    public ServiceException(ResultMsgEnum resultMsgEnum, Throwable throwable) {
        super(resultMsgEnum.getValue(), throwable);
        this.errorCode = resultMsgEnum.getCode();
        this.errorMsg = resultMsgEnum.getValue();
    }

    public ServiceException(ResultMsgEnum resultMsgEnum, Throwable throwable, Object... args) {
        super(resultMsgEnum.getValue(), throwable);
        this.errorCode = resultMsgEnum.getCode();
        this.errorMsg = String.format(resultMsgEnum.getValue(), args);
    }

    public ServiceException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}