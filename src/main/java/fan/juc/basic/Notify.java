package fan.juc.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程唤醒
 *
 * @author Fan
 * @since 2023/5/10 11:29
 */
@Slf4j
public class Notify {

    private static volatile Object resource = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取 resource 共享资源的监视器锁
                synchronized (resource) {
                    log.info("threadA get resource lock");
                    try {
                        log.info("threadA begin wait");
                        resource.wait();
                        log.info("threadA end wait");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                // 获取 resource 共享资源的监视器锁
                synchronized (resource) {
                    log.info("threadB get resource lock");
                    try {
                        log.info("threadB begin wait");
                        resource.wait();
                        log.info("threadB end wait");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resource) {
                    log.info("ThreadC begin notify");
                    resource.notifyAll();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();
        log.info("main over");
    }
}
