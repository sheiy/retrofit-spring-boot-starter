package com.github.sofior.retrofit;

import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

/**
 * @author sofior
 * @date 2018/8/18 23:23
 */
@Configuration
@ConditionalOnClass(OkHttpClient.class)
public class RetrofitAutoConfiguration {

}