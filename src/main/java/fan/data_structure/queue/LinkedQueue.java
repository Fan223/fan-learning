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

    public LinkedQueue() {
        head = new QueueNode<>();
        tail = head;
    }

    /**
     * 判断队列是否为空
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 10:55
     */
    public boolean isEmpty() {
        return head == tail;
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
        QueueNode<T> addQueueNode = new QueueNode<>(element);

        tail.setNext(addQueueNode);
        tail = tail.getNext();

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

        head = head.getNext();
        return head.getValue();
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
        return head.getNext().getValue();
    }
}
