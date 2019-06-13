package com.github.sheiy.retrofit;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 坑点：注解顺序必须如下，否则加载不了RetrofitClientsRegistrar
 * @author sofior
 * @date 2018/8/18 23:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RetrofitClientsRegistrar.class)
public @interface EnableRetrofitClients {

}
