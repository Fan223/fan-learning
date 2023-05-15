package fan.io.nio.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * 简单示例
 *
 * @author Fan
 * @since 2023/5/10 11:55
 */
public class SimpleDemo {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(8080);

        tomcat.setConnector(connector);
        tomcat.start();
        tomcat.getServer().await();
    }
}
