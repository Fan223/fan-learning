package fan.data_structure.queue;

import java.util.Arrays;

/**
 * 顺序队列
 *
 * @author Fan
 * @since 2023/2/28 10:08
 */
public class ArrayQueue<T> {

    /**
     * 默认初始容量
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * 扩容倍数
     */
    private static final int GROWTH_FACTOR = 2;

    /**
     * 存放队列元素的数组
     */
    private T[] storage;

    /**
     * 容量
     */
    private int capacity;

    /**
     * 队头下标
     */
    private int head;

    /**
     * 队尾下标
     */
    private int tail;

    public ArrayQueue() {
        init(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        init(capacity);
    }

    private void init(int capacity) {
        this.capacity = capacity;
        this.storage = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 判断队空
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 10:23
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * 入队
     *
     * @param element 元素
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 10:19
     */
    public boolean enqueue(T element) {
        // tail 走到尾
        if (tail == capacity) {
            // 队满扩容
            if (0 == head) {
                int newCapacity = capacity * GROWTH_FACTOR;
                storage = Arrays.copyOf(storage, newCapacity);
                capacity = newCapacity;
            } else {
                // 队未满进行数据搬移
                if (tail - head >= 0) {
                    System.arraycopy(storage, head, storage, 0, tail - head);
                }
                tail -= head;
                head = 0;
            }
        }

        storage[tail++] = element;
        return true;
    }

    /**
     * 出队并返回队首元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 10:34
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return storage[head++];
    }

    /**
     * 返回队首元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 10:37
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return storage[head];
    }

    /**
     * 获取队列中元素个数
     *
     * @return {@link int}
     * @author Fan
     * @since 2023/2/28 14:58
     */
    public int size() {
        return tail - head;
    }
}
