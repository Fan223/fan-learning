package fan.juc.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程等待与唤醒
 *
 * @author Fan
 * @since 2023/5/10 11:28
 */
@Slf4j
public class WaitNotifyInterrupt {

    static Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                log.info("---begin---");
                // 阻塞当前线程
                synchronized (resource) {
                    resource.wait();
                }
                log.info("---end---");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();

        Thread.sleep(1000);
        log.info("---begin interrupt thread");
        thread.interrupt();
        log.info("---endk interrupt thread");
    }
}
