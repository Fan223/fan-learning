package fan.netty.kyro.client;

import fan.netty.kyro.dto.RpcResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

/**
 * Netty 客户端处理器
 *
 * @author Fan
 * @since 2023/5/4 17:13
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, @NonNull Object msg) {
        try {
            RpcResponse rpcResponse = (RpcResponse) msg;
            log.info("客户端接收到的响应消息" + rpcResponse);
            // 声明一个 AttributeKey 对象
            AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
            // 将服务端的返回结果保存到 AttributeMap 上, AttributeMap 可以看作是一个 Channel 的共享数据源
            // AttributeMap 的 key 是 AttributeKey, value是 Attribute
            ctx.channel().attr(key).set(rpcResponse);
            ctx.channel().close();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info("客户端异常" + cause);
        ctx.close();
    }
}
