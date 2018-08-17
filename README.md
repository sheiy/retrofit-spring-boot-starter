# Example
```java
@Component
public class TestOrikaMapper implements OrikaMapper {
    @Override
    public void register(MapperFactory mapperFactory) {
        mapperFactory.classMap(X.class, Y.class)
                .field("x", "y")
                .byDefault().register();
    }
}

@RestController
public class TController {
    @Resource
    private MapperFacade facade;
    @GetMapping("/test")
    public Y test() {
        X x = new X();
        x.setX("X");
        x.setCommon("common");
        return facade.map(x, Y.class);
    }
}
```
via '/test' you will see 
```json
{
"common": "common",
"y": "X"
}
```
# custom mapperFactory
```java
@Configuration
public class Config {
    @Bean
    public MapperFactory mapperFactory() {
        //init your own factory here
        return new DefaultMapperFactory.Builder().build();
    }
}
```
more info of orika click [here](http://orika-mapper.github.io/orika-docs)