package fan.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端
 *
 * @author Fan
 * @since 2023/5/6 11:10
 */
public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        Object response = client.send("客户端发送消息", "127.0.0.1", 8888);
        System.out.println(response);
    }

    public Object send(String message, String host, int port) {
        // 1. 创建Socket对象并且指定服务器的地址和端口号
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 2. 通过输出流向服务器端发送请求信息
            objectOutputStream.writeObject(message);
            // 3. 通过输入流获取服务器响应的信息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
