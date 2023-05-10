package fan.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 *
 * @author Fan
 * @since 2023/5/6 11:09
 */
public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(8888);
    }

    public void start(int port) {
        // 1. 创建 ServerSocket 对象并且绑定一个端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            // 2. 通过 accept() 方法监听客户端请求
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 3. 通过输入流读取客户端发送的请求信息
            Object object = objectInputStream.readObject();
            System.out.println(object);
            // 4. 通过输出流向客户端发送响应信息
            objectOutputStream.writeObject("服务器响应消息");
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
