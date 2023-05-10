package fan.net;

import fan.log.LogUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 网络模板类
 *
 * @author Fan
 * @since 2023/3/7 9:43
 */
public class NetSample {

    public void getNetInterface() throws SocketException {
        // 得到本机所有的网络接口
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        // 循环遍历所有网络接口
        while (allNetInterfaces.hasMoreElements()) {
            // 顺序拿取某个网络接口
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            // 获得与该网络接口绑定的 IP 地址, 一般只有一个
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress inetAddress = addresses.nextElement();

                // loopback 地址即本机地址, IPv4 的 loopback 范围是 127.0.0.0 ~ 127.255.255.255
                if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()
                        && !inetAddress.getHostAddress().contains(":")) {
                    LogUtil.info(netInterface.getName());
                    LogUtil.info(inetAddress.getHostAddress());
                    LogUtil.info(Arrays.toString(netInterface.getHardwareAddress()));
                }
            }
        }
    }
}
