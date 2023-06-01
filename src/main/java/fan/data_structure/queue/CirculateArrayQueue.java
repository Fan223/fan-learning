package fan.data_structure.queue;

import java.util.Arrays;

/**
 * 循环数组队列
 *
 * @author Fan
 * @since 2023/2/28 10:08
 */
public class CirculateArrayQueue<T> {

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

    public CirculateArrayQueue() {
        init(DEFAULT_CAPACITY);
    }

    public CirculateArrayQueue(int capacity) {
        init(capacity);
    }

    private void init(int capacity) {
        this.capacity = capacity;
        this.storage = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = 0;
    }

    public void queryQueue() {
        for (int i = head; i != tail; i = (i + 1) % capacity) {
            System.out.print(storage[i] + " ");
        }
        System.out.println();
    }

    /**
     * 判断是否队列已满
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 14:50
     */
    public boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    /**
     * 判断队列是否为空
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 14:53
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
     * @since 2023/2/28 15:21
     */
    public boolean enqueue(T element) {
        // 队满扩容
        if (isFull()) {
            int newCapacity = capacity * GROWTH_FACTOR;
            storage = Arrays.copyOf(storage, newCapacity);
            capacity = newCapacity;
        }

        storage[tail] = element;
        tail = (tail + 1) % capacity;
        return true;
    }

    /**
     * 获取队列中元素个数
     *
     * @return {@link int}
     * @author Fan
     * @since 2023/2/28 14:59
     */
    public int size() {
        return (tail - head + capacity) % capacity;
    }

    /**
     * 出队并返回队首元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 15:01
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        T value = storage[head];
        head = (head + 1) % capacity;
        return value;
    }

    /**
     * 返回队首元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 15:03
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return storage[head];
    }

    public T getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return storage[(tail - 1 + capacity) % capacity];
    }
}
