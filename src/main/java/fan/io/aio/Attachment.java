package fan.io.aio;

import lombok.Data;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * 附加类
 *
 * @author Fan
 * @since 2023/5/9 15:41
 */
@Data
public class Attachment {

    private AsynchronousServerSocketChannel server;

    private AsynchronousSocketChannel client;

    private boolean isReadMode;

    private ByteBuffer buffer;
}
