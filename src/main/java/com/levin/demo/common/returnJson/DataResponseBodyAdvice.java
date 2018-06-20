package com.levin.demo.common.returnJson;

import com.levin.demo.common.JsonBody;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 用于预处理httpMessageConverter
 */
@ControllerAdvice//作用于controller中使用了注解@RequestMapping的方法
public class DataResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(returnType.hasMethodAnnotation(ExceptionHandler.class)) {//处理异常，可以再添加一个异常处理的类，用于处理异常返回格式
            return body;
        } else if(returnType.hasMethodAnnotation(JsonBody.class)){
            return JsonResult.success(body);
        }else{
            return body;
        }
    }
}