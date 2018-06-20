package com.levin.demo.common;

import com.levin.demo.common.returnJson.JsonResult;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常拦截处理器
 *
 * @author chenmc
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    @JsonBody
    public Object runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(1, ex);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    @JsonBody
    public Object nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(2, ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    @JsonBody
    public Object classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(3, ex);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    @JsonBody
    public Object iOExceptionHandler(IOException ex) {
        return resultFormat(4, ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    @JsonBody
    public Object noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(5, ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @JsonBody
    public Object indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(6, ex);
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @JsonBody
    public Object requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        return resultFormat(7, ex);
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @JsonBody
    public Object requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        return resultFormat(8, ex);
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @JsonBody
    public Object requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        return resultFormat(9, ex);
    }

    //404错误
    @ExceptionHandler({NoHandlerFoundException.class})
    @JsonBody
    public Object requestNOHandlerRequest(NoHandlerFoundException ex,ServerHttpResponse response)
            throws IOException {
        System.out.println("404..MissingServletRequest");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.sendRedirect("/api/user/inCorrectUserId");
        return "";
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @JsonBody
    public Object request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(10, ex);
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @JsonBody
    public Object request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return resultFormat(11, ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @JsonBody
    public Object server500(RuntimeException ex) {
        System.out.println("500...");
        return resultFormat(12, ex);
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    @JsonBody
    public Object requestStackOverflow(StackOverflowError ex) {
        return resultFormat(13, ex);
    }

    //其他错误
    @ExceptionHandler({Exception.class})
    @JsonBody
    public Object exception(Exception ex) {
        return resultFormat(14, ex);
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @JsonBody
    public Object  handleBusinessException(BusinessException e){
        log.error(e.getMessage(), e);
        return resultFormat(100, e);
    }

    private <T extends Throwable> Object resultFormat(Integer code, T ex) {
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JsonResult.failed(code, ex.getMessage());
    }

}
