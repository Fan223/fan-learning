package fan.juc.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * Join
 *
 * @author Fan
 * @since 2023/5/10 11:30
 */
@Slf4j
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Child threadOne over");
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("Child threadTwo over");
        });

        // 启动子线程
        threadOne.start();
        threadTwo.start();
        log.info("Wait All Child Thread Over");

        // 等待子线程执行完毕, 返回
        threadOne.join();
        threadTwo.join();
        log.info("All Child Thread Over");
    }
}
