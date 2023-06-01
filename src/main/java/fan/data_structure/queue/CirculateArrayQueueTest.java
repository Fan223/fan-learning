package fan.data_structure.queue;

/**
 * 循环数组队列测试类
 *
 * @author Fan
 * @since 2023/6/1 16:02
 */
public class CirculateArrayQueueTest {
    public static void main(String[] args) {
        CirculateArrayQueue<Object> circularQueue = new CirculateArrayQueue<>(4);

        // 1
        circularQueue.enqueue(1);
        // 张三 1
        circularQueue.enqueue("张三");
        // 李四 张三 1
        circularQueue.enqueue("李四");

        System.out.println("出队元素：" + circularQueue.dequeue());
        System.out.println("队头元素：" + circularQueue.peek());
        System.out.println("出队元素：" + circularQueue.dequeue());

        circularQueue.queryQueue();

        // 王五 李四
        circularQueue.enqueue("王五");
        // 赵六 王五 李四
        circularQueue.enqueue("赵六");

        circularQueue.queryQueue();

        System.out.println("队尾元素：" + circularQueue.getLast());
    }
}
