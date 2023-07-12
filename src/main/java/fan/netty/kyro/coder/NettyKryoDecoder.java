package fan.netty.kyro.coder;

import fan.netty.kyro.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 自定义 Netty 解码器
 *
 * @author Fan
 * @since 2023/5/4 17:30
 */
@AllArgsConstructor
@Slf4j
public class NettyKryoDecoder extends ByteToMessageDecoder {

    private final Serializer serializer;

    private final Class<?> genericClass;

    /**
     * Netty 传输的消息长度也就是对象序列化后对应的字节数组的大小, 存储在 ByteBuf 头部
     */
    private static final int BODY_LENGTH = 4;

    /**
     * 解码 ByteBuf 对象
     *
     * @param ctx 解码器关联的 ChannelHandlerContext 对象
     * @param in  "入站"数据, 也就是 ByteBuf 对象
     * @param out 解码之后的数据对象需要添加到 out 对象里面
     * @author Fan
     * @since 2023/5/5 8:57
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 1. byteBuf 中写入的消息长度所占的字节数已经是 4 了, 所以 byteBuf 的可读字节必须大于 4
        if (in.readableBytes() >= BODY_LENGTH) {
            // 2. 标记当前 readIndex 的位置, 以便后面重置 readIndex 的时候使用
            in.markReaderIndex();
            // 3. 读取消息的长度, 这里消息长度是 encode 的时候我们自己写入的, 即 NettyKryoEncoder 的 encode 方法
            int messageLength = in.readInt();
            // 4. 遇到不合理的情况直接 return
            if (messageLength < 0 || in.readableBytes() < 0) {
                log.info("data length or byteBuf readableBytes is not valid");
                return;
            }
            // 5. 如果可读字节数小于消息长度的话, 说明是不完整的消息, 重置 readIndex
            if (in.readableBytes() < messageLength) {
                in.resetReaderIndex();
                return;
            }
            // 6. 走到这里说明没什么问题了, 可以序列化了
            byte[] body = new byte[messageLength];
            in.readBytes(body);
            // 将 bytes 数组转换为我们需要的对象
            Object obj = serializer.deserialize(body, genericClass);
            out.add(obj);
            log.info("successful decode ByteBuf to Object");
        }
    }
}
