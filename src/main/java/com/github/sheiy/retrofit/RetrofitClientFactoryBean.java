/**
 * Copyright 2010-2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.sheiy.retrofit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.time.Duration;

@Slf4j
public class RetrofitClientFactoryBean<T> implements FactoryBean<T>, EnvironmentAware {

    private Class<T> retrofitInterface;

    private Environment environment;

    private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            log.info(message);
        }
    });

    private OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .retryOnConnectionFailure(false)
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    public RetrofitClientFactoryBean() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public RetrofitClientFactoryBean(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    @Override
    public T getObject() throws Exception {
        RetrofitClient annotation = this.getRetrofitInterface().getAnnotation(RetrofitClient.class);
        String url = environment.resolvePlaceholders(annotation.url());
        log.debug("{} base url is {}", retrofitInterface.getName(), url);
        Retrofit retrofit = new Retrofit.Builder()
                //首先判断是否需要转换成字符串，简单类型
                .addConverterFactory(ScalarsConverterFactory.create())
                //再将转换成bean
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .client(httpClient)
                .build();
        return retrofit.create(retrofitInterface);
    }

    @Override
    public Class<T> getObjectType() {
        return this.retrofitInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setRetrofitInterface(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public Class<T> getRetrofitInterface() {
        return retrofitInterface;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
