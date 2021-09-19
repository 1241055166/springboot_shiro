package com.ybchen.springboot_shiro.utils;

import java.io.Serializable;

public class JsonData implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码，0表示成功过，1表示处理中，-1表示失败
     */
    private Integer code;
    /**
     * 业务数据
     */
    private Object data;
    /**
     * 信息描述
     */
    private String msg;

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 成功，不用返回数据
     *
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }

    /**
     * 成功，返回数据
     *
     * @param data 返回数据
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }

    /**
     * 成功，返回数据
     *
     * @param code 状态码
     * @param data 返回数据
     * @return
     */
    public static JsonData buildSuccess(int code, Object data) {
        return new JsonData(code, data, null);
    }

    /**
     * 失败，返回信息
     *
     * @param msg 返回信息
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

    /**
     * 失败，返回信息和状态码
     *
     * @param code 状态码
     * @param msg  返回信息
     * @return
     */
    public static JsonData buildError(Integer code, String msg) {
        return new JsonData(code, null, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
