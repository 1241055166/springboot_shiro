package com.ybchen.springboot_shiro.exception;

/**
 * @Description：自定义异常
 * @Author：chenyanbin
 * @Date：2021/1/3 7:31 下午
 * @Versiion：1.0
 */
public class CustomException extends RuntimeException{
    private Integer code;
    private String msg;

    public CustomException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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

    @Override
    public String toString() {
        return "CustomException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
