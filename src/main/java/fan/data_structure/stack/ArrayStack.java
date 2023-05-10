package fan.data_structure.stack;

import java.util.Arrays;

/**
 * 顺序栈
 *
 * @author Fan
 * @since 2023/2/21 16:53
 */
public class ArrayStack<T> {

    /**
     * 默认初始容量
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * 扩容倍数
     */
    private static final int GROWTH_FACTOR = 2;

    /**
     * 存放栈中元素的数组
     */
    private T[] storage;

    /**
     * 栈的容量
     */
    private int capacity;

    /**
     * 栈中元素数量
     */
    private int count;

    public ArrayStack() {
        init(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity too small.");
        }
        init(capacity);
    }

    private void init(int capacity) {
        this.capacity = capacity;
        this.storage = (T[]) new Object[capacity];
        this.count = 0;
    }

    /**
     * 判断栈满
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 10:26
     */
    public boolean isFull() {
        return count == capacity;
    }

    /**
     * 判断栈是否为空
     *
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 9:32
     */
    public boolean isEmpty() {
        return 0 == count;
    }

    /**
     * 入栈
     *
     * @param element 元素
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 9:22
     */
    public boolean push(T element) {
        // 如果栈满, 则扩容
        if (isFull()) {
            expand();
        }
        storage[count++] = element;
        return true;
    }

    /**
     * 扩容
     *
     * @author Fan
     * @since 2023/2/28 10:28
     */
    private void expand() {
        int newCapacity = capacity * GROWTH_FACTOR;
        storage = Arrays.copyOf(storage, newCapacity);
        capacity = newCapacity;
    }

    /**
     * 出栈并返回栈顶元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 9:23
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty.");
        }

        return storage[--count];
    }

    /**
     * 返回栈顶元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 9:28
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Stack is empty.");
        }

        return storage[count - 1];
    }

    /**
     * 返回栈的大小
     *
     * @return {@link int}
     * @author Fan
     * @since 2023/2/28 9:32
     */
    public int size() {
        return count;
    }
}
