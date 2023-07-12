package fan.juc.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Caller 任务
 *
 * @author Fan
 * @since 2023/5/10 11:30
 */
@Slf4j
public class CallerTask implements Callable<String> {

    private volatile int tickets = 20;

    @Override
    public String call() throws Exception {
        while (tickets > 0) {
            log.info(Thread.currentThread().getName() + "卖出一张票" + tickets--);
        }

        return "已售完";
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread(futureTask);
        thread1.setName("一号窗口: ");
        thread2.setName("二号窗口: ");
        thread1.start();
        thread2.start();

        try {
            String result = futureTask.get();
            log.info("result: " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
