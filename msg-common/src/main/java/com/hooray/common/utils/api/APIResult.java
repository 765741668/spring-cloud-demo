package com.hooray.common.utils.api;


/**
 * Http请求返回统一格式
 *
 * @author yangfeng
 * @date 2017年10月31日 上午10:36:25
 * Email: yangfeng@hooray.cn
 */
public class APIResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 返回body
     */
    private T data;

    public APIResult() {
    }

    public APIResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public APIResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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
