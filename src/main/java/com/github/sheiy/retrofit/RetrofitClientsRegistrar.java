package com.github.sheiy.retrofit;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

/**
 * @author sofior
 * @date 2018/8/18 23:24
 */
public class RetrofitClientsRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    public RetrofitClientsRegistrar() {
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        registerRetrofitClients(metadata, registry);
    }

    public void registerRetrofitClients(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        ClassPathRetrofitScanner scanner = new ClassPathRetrofitScanner(registry);
        scanner.setResourceLoader(this.resourceLoader);
        scanner.setAnnotationClass(RetrofitClient.class);
        scanner.registerFilters();
        scanner.doScan(ClassUtils.getPackageName(metadata.getClassName()));
    }
}
