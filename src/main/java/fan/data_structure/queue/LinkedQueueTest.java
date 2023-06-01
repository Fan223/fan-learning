package fan.data_structure.queue;

/**
 * 链式队列测试类
 *
 * @author Fan
 * @since 2023/6/1 15:24
 */
public class LinkedQueueTest {
    public static void main(String[] args) {
        LinkedQueue<Object> linkQueue = new LinkedQueue<>();
        // 1
        linkQueue.enqueue(1);
        // 张三 1
        linkQueue.enqueue("张三");
        // 2 张三 1
        linkQueue.enqueue(2);
        // 李四 2 张三 1
        linkQueue.enqueue("李四");

        // 1
        System.out.println("出队元素：" + linkQueue.dequeue());
        // 张三
        System.out.println("队头元素：" + linkQueue.peek());
        // 李四
        System.out.println("队尾元素：" + linkQueue.getLast());
        // 张三
        System.out.println("出队元素：" + linkQueue.dequeue());

        linkQueue.queryQueue();

        // 2
        System.out.println("队列长度：" + linkQueue.getLength());
    }
}
