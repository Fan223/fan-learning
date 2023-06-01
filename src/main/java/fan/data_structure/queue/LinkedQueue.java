package fan.data_structure.queue;

/**
 * 链式队列
 *
 * @author Fan
 * @since 2023/2/28 10:08
 */
public class LinkedQueue<T> {

    /**
     * 队头
     */
    private QueueNode<T> head;

    /**
     * 队尾
     */
    private QueueNode<T> tail;

    private int length;

    public LinkedQueue() {
        head = new QueueNode<>();
        tail = head;
        length = 0;
    }

    public int getLength() {
        return length;
    }

    public void queryQueue() {
        QueueNode<T> current = head;
        while (null != current.next) {
            current = current.next;
            System.out.print(current.value + " ");
        }
        System.out.println();
    }

    /**
     * 判断队列是否为空
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 10:55
     */
    public boolean isEmpty() {
        return 0 == length;
    }

    /**
     * 入队
     *
     * @param element 元素
     * @return {@link boolean}
     * @author zhaojunjie
     * @since 2023/2/28 15:14
     */
    public boolean enqueue(T element) {
        tail.next = new QueueNode<>(element);
        tail = tail.next;
        length++;

        return true;
    }

    /**
     * 出队并返回队首元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 11:08
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        head = head.next;
        length--;

        return head.value;
    }

    /**
     * 返回队首元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 11:15
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.next.value;
    }

    public T getLast() {
        QueueNode<T> current = head;
        while (null != current.next) {
            current = current.next;
        }

        return current.value;
    }
}
