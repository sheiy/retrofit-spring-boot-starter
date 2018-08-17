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
package com.github.sofior.retrofit;

import org.springframework.beans.factory.FactoryBean;
import retrofit2.Retrofit;

public class RetrofitFactoryBean<T> implements FactoryBean<T> {

    private Class<T> retrofitInterface;

    public RetrofitFactoryBean() {
        //intentionally empty
    }

    public RetrofitFactoryBean(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    @Override
    public T getObject() throws Exception {
        RetrofitService annotation = this.getRetrofitInterface().getAnnotation(RetrofitService.class);
        Retrofit retrofit = new Retrofit.Builder()
                //设置数据解析器
//            .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .baseUrl(annotation.baseUrl())
                .build();
// 创建网络请求接口的实例
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

    public void setRetrofitInterfaceInterface(Class<T> retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public Class<T> getRetrofitInterface() {
        return retrofitInterface;
    }

}
