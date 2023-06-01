package fan.data_structure.queue;

/**
 * 数组单队列测试类
 *
 * @author Fan
 * @since 2023/6/1 14:54
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue<Object> arrayQueue = new ArrayQueue<>();
        // 1
        arrayQueue.enqueue(1);
        // 张三 1
        arrayQueue.enqueue("张三");
        // 2 张三 1
        arrayQueue.enqueue(2);
        // 李四 2 张三 1
        arrayQueue.enqueue("李四");

        System.out.println("出队元素：" + arrayQueue.dequeue());
        System.out.println("出队元素：" + arrayQueue.dequeue());
        System.out.println("获取队头元素不出队：" + arrayQueue.peek());

        arrayQueue.queryQueue();

        System.out.println("出队元素：" + arrayQueue.dequeue());
        System.out.println("出队元素：" + arrayQueue.dequeue());
        System.out.println("队列是否为空：" + arrayQueue.isEmpty());
        System.out.println("队列是否已满：" + arrayQueue.isFull());
    }
}
