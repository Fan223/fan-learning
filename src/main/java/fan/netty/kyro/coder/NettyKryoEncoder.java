package fan.netty.kyro.coder;

import fan.netty.kyro.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * 自定义 Netty 编码器 <br>
 * 网络传输需要通过字节流来实现, ByteBuf 可以看作是 Netty 提供的字节数据的容器, 使用它会让我们更加方便地处理字节数据
 *
 * @author Fan
 * @since 2023/5/4 17:18
 */
@AllArgsConstructor
public class NettyKryoEncoder extends MessageToByteEncoder<Object> {

    private final Serializer serializer;

    private final Class<?> genericClass;

    /**
     * 将对象转换为字节码然后写入到 ByteBuf 对象中
     *
     * @author Fan
     * @since 2023/5/4 17:20
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        if (genericClass.isInstance(msg)) {
            // 1. 将对象转换为byte
            byte[] body = serializer.serialize(msg);
            // 2. 读取消息的长度
            int messageLength = body.length;
            // 3. 写入消息对应的字节数组长度, writerIndex 加 4
            out.writeInt(messageLength);
            // 4. 将字节数组写入 ByteBuf 对象中
            out.writeBytes(body);
        }
    }
}
