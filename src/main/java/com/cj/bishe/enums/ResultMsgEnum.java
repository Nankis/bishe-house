package com.cj.bishe.enums;

public enum ResultMsgEnum {

    ILLEGAL_ARGS("101", "参数错误"),
    EMPTY_ARGS("102", "%s不能为空"),
    NOT_PERMISSION("601", "没有权限"),
    NOT_LOGIN("600", "请登录"),
    SYS_ERROR("606", "系统异常"),
    NOTICE_EXIST_USER("111", "用户已存在"),
    NOTICE_NOT_EXIST_USER("112", "用户不存在"),
    NOTICE_NOT_EXIST_HOUSE("112", "房子不存在"),
    ;

    private String code;
    private String value;

    ResultMsgEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
