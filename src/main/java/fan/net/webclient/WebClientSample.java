package fan.net.webclient;

import fan.core.collection.ListUtil;
import fan.core.map.MapUtil;
import fan.log.LogUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * WebClient 模板类
 *
 * @author Fan
 * @since 2023/1/31 11:21
 */
public class WebClientSample {

    public static final String URI = "https://v1.hitokoto.cn";

    /**
     * {@code WebClient.create()} 方法, 阻塞返回 Mono 对象
     *
     * @return {@link String}
     * @author Fan
     * @since 2023/1/31 14:01
     */
    public <T> T create(String baseUrl, HttpMethod method, String uri, Class<T> clazz) {
        // 一个 Mono 对象包含 0 个或 1 个元素
        Mono<T> mono = WebClient
                // 创建 WebClient 实例
                .create(baseUrl)
                // 方法调用, 请求类型
                .method(method)
                // 请求 uri
                .uri(uri)
                // 获取响应结果
                .retrieve()
                // 将结果转换为指定类型
                .bodyToMono(clazz);

        // 返回最终调用结果, block 方法是阻塞的
        return mono.block();
    }

    /**
     * {@code WebClient.create()} 方法, 异步响应, 返回 Flux 对象
     *
     * @author Fan
     * @since 2023/3/8 14:36
     */
    public void create() {
        // 一个 Flux 对象包含 1 个或多个元素
        Flux<String> stringFlux = WebClient.create()
                .get()
                .uri(URI)
                .retrieve()
                .bodyToFlux(String.class);

        // 非阻塞式获取响应结果
        stringFlux.subscribe(this::handleResponse);
    }

    /**
     * 异步响应回调
     *
     * @param response 响应
     * @author Fan
     * @since 2023/1/31 14:25
     */
    private void handleResponse(String response) {
        LogUtil.info(response);
    }

    /**
     * {@code WebClient.builder()} 方法, 该对象可以做链式调用, 传递更多的参数 <br/>
     * <ul>
     *     <li> {@code uriBuilderFactory}: 自定义 UriBuilderFactory, 灵活配置使用 Uri </li>
     *     <li> {@code defaultHeader}: 为 HTTP 请求设置 Headers 请求头 </li>
     *     <li> {@code defaultCookie}: 为 HTTP 请求设置 Cookies </li>
     *     <li> {@code defaultRequest}: 自定义 HttpRequest </li>
     *     <li> {@code filter}: 为 HTTP 请求增加客户端过滤器 </li>
     *     <li> {@code exchangeStrategies}: HTTP 读写信息自定义 </li>
     *     <li> {@code clientConnector}: HTTP 客户端连接器设置 </li>
     * </ul>
     *
     * @return {@link String}
     * @author Fan
     * @since 2023/1/31 14:02
     */
    public String build() {
        return WebClient.builder()
                .baseUrl(URI)
                .defaultHeader("build", "build")
                .build()
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * {@code exchange()} 方法, 获取 HTTP 响应完整内容
     *
     * @return {@link String}
     * @author Fan
     * @since 2023/3/8 14:41
     */
    public String exchange() {
        return WebClient.create()
                .get()
                .uri(URI)
                .exchangeToMono(clientResponse -> {
                    // 响应头
                    ClientResponse.Headers headers = clientResponse.headers();
                    // 响应状态码
                    HttpStatusCode httpStatusCode = clientResponse.statusCode();
                    LogUtil.info(headers.toString() + httpStatusCode);

                    // 响应体
                    return clientResponse.bodyToMono(String.class);
                }).block();
    }

    /**
     * 数字占位符传参
     *
     * @return {@link String}
     * @author Fan
     * @since 2023/3/8 14:43
     */
    public String numParam() {
        return WebClient.create()
                .method(HttpMethod.POST)
                .uri("http://localhost:8080/user/{1}/{2}", ListUtil.list(1, 2).toArray())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * 参数名传参
     *
     * @return {@link String}
     * @author Fan
     * @since 2023/3/8 14:43
     */
    public String nameParam() {
        return WebClient.create()
                .method(HttpMethod.POST)
                .uri("http://localhost:8080/user/{id}/{name}", "id", "name")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * Map 传参
     *
     * @return {@link String}
     * @author Fan
     * @since 2023/3/8 14:44
     */
    public String mapParam() {
        return WebClient.create()
                .method(HttpMethod.POST)
                .uri("http://localhost:8080/user/{id}/{name}",
                        MapUtil.ofEntries(false, MapUtil.entry("id", "id"), MapUtil.entry("name", "name")))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
