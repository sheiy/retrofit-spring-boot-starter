# Example
```java
@SpringBootApplication
@EnableRetrofitClients
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    
}

@RetrofitClient(url = "${test.url}")
public interface WeatherService {

    @GET("/telematics/v3/weather?location=%E5%98%89%E5%85%B4&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ")
    Call<Map> getBlog();
}

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final UserService userService;

    private final WeatherService weatherService;
    
    @GetMapping("/w")
    public Map w() throws IOException {
        Response<Map> execute = weatherService.getBlog().execute();
        Map body = execute.body();
        return body;
    }
}
```
via '/test/w' you will see 
```json
{
status: 201,
message: "APP被用户自己禁用，请在控制台解禁"
}
```
