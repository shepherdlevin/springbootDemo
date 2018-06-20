package com.levin.demo.common;

import com.levin.demo.common.returnJson.JsonResult;

public class ResultUtil {

    //当正确时返回的值  
    public static JsonResult success(Object data){
        JsonResult result = new JsonResult();
        result.setCode(0);
        result.setMsg("OK");
        result.setData(data);
        return result;
    }

    public static JsonResult success(){
        return success(null);
    }

    //当错误时返回的值
    public static JsonResult error(int code,String msg){
        JsonResult result = new JsonResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}