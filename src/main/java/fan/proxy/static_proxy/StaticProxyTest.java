package fan.proxy.static_proxy;

/**
 * 静态代理测试类
 *
 * @author Fan
 * @since 2023/5/5 11:35
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxyImpl smsProxy = new SmsProxyImpl(smsService);
        smsProxy.send("hello");
    }
}
