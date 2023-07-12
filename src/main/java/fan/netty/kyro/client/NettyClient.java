package fan.netty.kyro.client;

import fan.netty.kyro.coder.NettyKryoDecoder;
import fan.netty.kyro.coder.NettyKryoEncoder;
import fan.netty.kyro.dto.RpcRequest;
import fan.netty.kyro.dto.RpcResponse;
import fan.netty.kyro.serializer.KryoSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

/**
 * Netty 客户端
 *
 * @author Fan
 * @since 2023/5/4 16:54
 */
@Slf4j
public class NettyClient {

    private final String host;

    private final int port;

    private static final Bootstrap BOOT_STRAP;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // 初始化相关资源比如 EventLoopGroup, Bootstrap
    static {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        BOOT_STRAP = new Bootstrap();
        KryoSerializer kryoSerializer = new KryoSerializer();
        BOOT_STRAP.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                // 连接的超时时间, 超过这个时间还是建立不上的话则代表连接失败
                // 如果 15 秒之内没有发送数据给服务端的话, 就发送一次心跳请求
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(@NonNull SocketChannel ch) {
                        // 自定义序列化编解码器
                        // RpcResponse -> ByteBuf
                        ch.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcResponse.class));
                        // ByteBuf -> RpcRequest
                        ch.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcRequest.class));
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
    }

    /**
     * 发送消息到服务端
     *
     * @param rpcRequest 消息体
     * @return {@link RpcResponse}
     * @author Fan
     * @since 2023/5/4 17:13
     */
    public RpcResponse sendMessage(RpcRequest rpcRequest) {
        try {
            ChannelFuture channelFuture = BOOT_STRAP.connect(host, port).sync();
            log.info("客户端连接主机: " + host + ",端口: " + port);
            Channel channel = channelFuture.channel();
            log.info("发送消息");

            if (null != channel) {
                channel.writeAndFlush(rpcRequest).addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("消息发送成功," + rpcRequest.toString());
                    } else {
                        log.info("发送消息失败" + future.cause());
                    }
                });
                // 阻塞等待 , 直到Channel关闭
                channel.closeFuture().sync();
                // 将服务端返回的数据也就是 RpcResponse 对象取出
                AttributeKey<RpcResponse> key = AttributeKey.valueOf("rpcResponse");
                return channel.attr(key).get();
            }
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }

        return null;
    }

    private static final int LENGTH = 3;

    public static void main(String[] args) {
        RpcRequest rpcRequest = RpcRequest.builder().interfaceName("interface").methodName("hello").build();
        NettyClient nettyClient = new NettyClient("127.0.0.1", 8888);
        for (int i = 0; i < LENGTH; i++) {
            nettyClient.sendMessage(rpcRequest);
        }
        RpcResponse rpcResponse = nettyClient.sendMessage(rpcRequest);
        log.info(rpcResponse.toString());
    }
}
