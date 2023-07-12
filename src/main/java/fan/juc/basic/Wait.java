package fan.juc.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程等待
 *
 * @author Fan
 * @since 2023/5/10 11:29
 */
@Slf4j
public class Wait {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取 resourceA 共享资源的监视器锁
                    synchronized (resourceA) {
                        log.info("threadA get resourceA lock");
                        // 获取 resourceB 共享资源的监视器锁
                        synchronized (resourceB) {
                            log.info("threadA get resourceB lock");
                            // 线程 A 阻塞, 并释放获取到的 resourceA 的锁
                            log.info("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    // 获取 resourceA 共享资源的监视器锁
                    synchronized (resourceA) {
                        log.info("threadB get resourceA lock");
                        log.info("threadB try get resourceB lock...");
                        // 获取 resourceB 共享资源的监视器锁
                        synchronized (resourceB) {
                            log.info("threadB get resourceB lock");
                            // 线程 A 阻塞, 并释放获取到的 resourceA 的锁
                            log.info("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
        // 等待两个线程结束
        threadA.join();
        threadB.join();

        log.info("main over");
    }
}
