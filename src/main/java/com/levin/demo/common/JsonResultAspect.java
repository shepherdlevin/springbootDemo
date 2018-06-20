package com.levin.demo.common;


import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author wenlai.wang
 * @since 2017/7/13
 */
@Aspect //@Aspect作用是把当前类标识为一个切面供容器读取
@Component//@component 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
public class JsonResultAspect {
 /*   private static Logger log = LoggerFactory.getLogger(JsonResultAspect.class);
    private static final String logExceptionFormat = "Capture Exception By JsonResultAspect: Code: %s Detail: %s";

    //@Pointcut定义一个切点
    @Pointcut(value = "@annotation(com.levin.demo.common.JsonBody)")
    private void pointcut() {

    }

    *//**
     * 在方法执行前后
     *
     * @param point
     * @param jsonBody
     * @return
     *//*
    @Around(value = "pointcut() && @annotation(jsonBody)")
    public Object around(ProceedingJoinPoint point, JsonBody jsonBody) {

        log.debug("++++执行了around方法++++");
        //拦截的类名
        Class clazz = point.getTarget().getClass();
        //拦截的方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        log.debug("执行了 类:" + clazz + " 方法:" + method );

        try {
            return point.proceed(); //执行程序
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    *//**
     * 方法执行后
     *
     * @param joinPoint
     * @param jsonBody
     * @param result
     * @return
     *//*
    @AfterReturning(value = "pointcut() && @annotation(jsonBody)",returning = "result")
    public Object  afterReturning(JoinPoint joinPoint, JsonBody jsonBody, Object result) {
        return JsonResult.success(result);
    }

   *//* *//**//**
     * 方法执行后 并抛出异常
     * @param ex
     *//**//*
    @AfterThrowing(throwing="ex", pointcut="execution(* com.levin.demo.*.*(..))")
    public String afterThrowing( Throwable  ex) {
        return resultFormat(1, ex);
    }*//*


    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        log.error(String.format(logExceptionFormat, code, ex.getMessage()),ex);
        return JsonResult.failed(code, ex.getMessage());
    }*/
}

