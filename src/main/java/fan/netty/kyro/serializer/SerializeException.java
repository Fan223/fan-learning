package fan.netty.kyro.serializer;

/**
 * 序列化异常类
 *
 * @author Fan
 * @since 2023/5/5 10:53
 */
public class SerializeException extends RuntimeException {

    public SerializeException(String message) {
        super(message);
    }
}
