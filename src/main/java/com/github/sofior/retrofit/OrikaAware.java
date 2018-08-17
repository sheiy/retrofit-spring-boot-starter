package com.github.sofior.retrofit;


import java.util.Map;

@Configuration
public class OrikaAware implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(OrikaAware.class);

    @Bean
    public MapperFacade mapperFacade() {
        return mapperFactory().getMapperFacade();
    }

    @Bean
    @ConditionalOnMissingBean(MapperFactory.class)
    public MapperFactory mapperFactory() {
        logger.debug("custom mapper factory not found use default");
        return new DefaultMapperFactory.Builder().build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, OrikaMapper> beansOfType = applicationContext.getBeansOfType(OrikaMapper.class);
        final MapperFactory mapperFactory = mapperFactory();
        beansOfType.forEach((s, mapper) -> mapper.register(mapperFactory));
    }
}
