package com.github.sofior.retrofit;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetrofitService {
    String baseUrl();
}
