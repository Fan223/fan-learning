package fan.juc.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Thread 测试
 *
 * @author Fan
 * @since 2023/5/10 11:30
 */
@Slf4j
public class ThreadTest extends Thread {
    private volatile int tickets = 20;

    @Override
    public void run() {
        while (tickets > 0) {
            log.info(this.getName() + "卖出一张票" + tickets--);
        }
    }

    public static void main(String[] args) {
        // 实际上一共卖出了60张票，每个线程都有自己的私有的非共享数据。都认为自己有20张票
        ThreadTest test1 = new ThreadTest();
        ThreadTest test2 = new ThreadTest();
        ThreadTest test3 = new ThreadTest();
        test1.setName("一号窗口：");
        test2.setName("二号窗口：");
        test3.setName("三号窗口：");
        test1.start();
        test2.start();
        test3.start();
    }
}

