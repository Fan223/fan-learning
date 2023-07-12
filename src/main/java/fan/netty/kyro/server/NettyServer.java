package fan.netty.kyro.server;

import fan.netty.kyro.coder.NettyKryoDecoder;
import fan.netty.kyro.coder.NettyKryoEncoder;
import fan.netty.kyro.dto.RpcRequest;
import fan.netty.kyro.dto.RpcResponse;
import fan.netty.kyro.serializer.KryoSerializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

/**
 * Netty 服务端
 *
 * @author Fan
 * @since 2023/5/5 9:05
 */
@Slf4j
public class NettyServer {

    private final int port;

    private NettyServer(int port) {
        this.port = port;
    }

    private void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        KryoSerializer kryoSerializer = new KryoSerializer();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                // TCP 默认开启了 Nagle 算法, 该算法的作用是尽可能的发送大数据快, 减少网络传输. TCP_NODELAY 参数的作用就是控制是否启用 Nagle 算法
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度, 如果连接建立频繁, 服务器处理创建新连接较慢, 可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 128)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(@NonNull SocketChannel ch) {
                        ch.pipeline().addLast(new NettyKryoDecoder(kryoSerializer, RpcRequest.class));
                        ch.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcResponse.class));
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });
        // 绑定端口, 同步等待绑定成功
        try {
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            // 等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyServer(8888).run();
    }
}
