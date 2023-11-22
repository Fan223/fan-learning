package fan.net.rest_template;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 工具类
 *
 * @author Fan
 * @since 2023/1/13 16:16
 */

@Component
public class RestTemplateSample {

    private static RestTemplate restTemplate;

    @Resource
    private void setRestTemplate(RestTemplate restTemplate) {
        staticSetRestTemplate(restTemplate);
    }

    private static synchronized void staticSetRestTemplate(RestTemplate restTemplate) {
        RestTemplateSample.restTemplate = restTemplate;
    }

    /**
     * 以 Get 方式请求第三方接口, getForEntity, 默认返回类型 String
     *
     * @param url 请求地址
     * @return {@link String}
     * @author Fan
     * @since 2023/1/13 16:18
     */
    public static String doGetWithEntity(String url) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        return responseEntity.getBody();
    }

    /**
     * 以 Get 方式请求第三方接口, getForEntity, 指定返回类型
     *
     * @param url   请求地址
     * @param clazz 返回类型
     * @return {@link T}
     * @author Fan
     * @since 2023/1/13 17:08
     */
    public static <T> T doGetWithEntity(String url, Class<T> clazz) {
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);
        return responseEntity.getBody();
    }

    /**
     * 以 Get 方式请求第三方接口, getForObject, 返回值返回的是响应体, 省了再去 getBody(), 默认返回类型 String
     *
     * @param url 请求地址
     * @return {@link String}
     * @author Fan
     * @since 2023/1/13 16:19
     */
    public static String doGetWithObject(String url) {
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 以 Get 方式请求第三方接口, getForObject, 返回值返回的是响应体, 省了再去 getBody(), 指定返回类型
     *
     * @param url   请求地址
     * @param clazz 返回类型
     * @return {@link T}
     * @author Fan
     * @since 2023/1/13 17:16
     */
    public static <T> T doGetWithObject(String url, Class<T> clazz) {
        return restTemplate.getForObject(url, clazz);
    }

    /**
     * 以 Post 方式请求第三方接口, postForEntity, 默认返回类型 String
     *
     * @param url    请求地址
     * @param object 请求参数
     * @return {@link String}
     * @author Fan
     * @since 2023/1/13 16:28
     */
    public static String doPostWithEntity(String url, Object object) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, object, String.class);
        return responseEntity.getBody();
    }

    /**
     * 以 Post 方式请求第三方接口, postForEntity, 指定返回类型
     *
     * @param url    请求地址
     * @param object 请求参数
     * @param clazz  返回类型
     * @return {@link T}
     * @author Fan
     * @since 2023/1/13 17:18
     */
    public static <T> T doPostWithEntity(String url, Object object, Class<T> clazz) {
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, object, clazz);
        return responseEntity.getBody();
    }

    /**
     * 以 Post 方式请求第三方接口, postForObject, 返回值返回的是响应体, 省了再去 getBody(), 默认返回类型 String
     *
     * @param url    请求地址
     * @param object 请求参数
     * @return {@link String}
     * @author Fan
     * @since 2023/1/13 16:29
     */
    public static String doPostWithObject(String url, Object object) {
        return restTemplate.postForObject(url, object, String.class);
    }

    /**
     * 以 Post 方式请求第三方接口, postForObject, 返回值返回的是响应体, 省了再去 getBody(), 指定返回类型
     *
     * @param url    请求地址
     * @param object 请求参数
     * @param clazz  返回类型
     * @return {@link T}
     * @author Fan
     * @since 2023/1/13 17:19
     */
    public static <T> T doPostWithObject(String url, Object object, Class<T> clazz) {
        return restTemplate.postForObject(url, object, clazz);
    }
}
