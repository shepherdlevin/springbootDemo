package com.levin.demo.common.returnJson;


import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;
import java.util.HashMap;

/**
 * @author wemlai.wang
 * @date 2017/10/12 17:18
 */
/**
 * 返回json的模板
 */
public class JsonResult<T> implements Serializable{

    private int code;   //返回码 非0即失败
    private String msg; //消息提示
    private T data; //返回的数据

    public JsonResult(){};

    public JsonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Object success() {
        return success();
    }
    public static Object success(Object data) {
        return JSONObject.toJSON(new JsonResult(0, "成功", data));
    }

    public static Object failed() {
        return failed("失败");
    }
    public static Object failed(String msg) {
        return failed(-1, msg);
    }
    public static Object failed(int code, String msg) {
        return JSONObject.toJSON(new JsonResult(code, msg, new HashMap<>(0)));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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