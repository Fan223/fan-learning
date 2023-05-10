package fan.netty.kyro.serializer;

/**
 * 自定义序列化接口
 *
 * @author Fan
 * @since 2023/5/4 17:23
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param obj 要序列化的对象
     * @return {@link byte[]}
     * @author Fan
     * @since 2023/5/4 17:24
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     *
     * @param bytes 序列化后的字节数组
     * @param clazz clazz 类
     * @return {@link T}
     * @author Fan
     * @since 2023/5/4 17:24
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
