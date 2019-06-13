package com.github.sheiy.retrofit;

import java.lang.annotation.*;

/**
 * @author sofior
 * @date 2018/8/18 23:23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RetrofitClient {

    /**
     * An absolute URL or resolvable hostname.
     */
    String url() default "";

}
