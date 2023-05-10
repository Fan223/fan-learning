package fan.netty.kyro.server;

import fan.netty.kyro.dto.RpcRequest;
import fan.netty.kyro.dto.RpcResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Netty 服务端处理器
 *
 * @author Fan
 * @since 2023/5/5 9:14
 */
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);


    @Override
    public void channelRead(ChannelHandlerContext ctx, @NonNull Object msg) {
        try {
            RpcRequest rpcRequest = (RpcRequest) msg;
            log.info("服务器接收到消息" + rpcRequest + ATOMIC_INTEGER.getAndIncrement());
            RpcResponse messageFromServer = RpcResponse.builder().message("message from server").build();
            ChannelFuture channelFuture = ctx.writeAndFlush(messageFromServer);
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.info(ctx.channel().remoteAddress() + "服务器发生异常" + cause.getMessage());
        ctx.close();
    }
}
