package com.levin.demo.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.ResponseBody;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface JsonBody {
    String callback() default "callback";

    String debugTag() default "debugTag";

    JsonBody.Version version() default JsonBody.Version.v2;

    JsonFeature[] enable() default {};

    JsonFeature[] disable() default {};

    public static enum Version {
        v1,
        v2;

        private Version() {
        }
    }
}