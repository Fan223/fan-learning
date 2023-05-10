package fan.data_structure.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 单链表操作类
 *
 * @author Fan
 * @since 2023/2/20 10:37
 */
public class LinkedNode<T> implements Iterable<T> {

    /**
     * 头结点, 哨兵节点
     */
    private final Node<T> head;

    /**
     * 链表长度
     */
    private int length;

    public LinkedNode() {
        head = new Node<>();
        length = 0;
    }

    public int getLength() {
        return length;
    }

    /**
     * 头插法
     *
     * @param value 值
     * @author Fan
     * @since 2023/2/22 13:56
     */
    public boolean addFirst(T value) {
        Node<T> addNode = new Node<>(value);

        /*
         * head -> 1 -> 2
         *        /
         *     add
         */
        addNode.setNext(head.getNext());
        /*
         * head    1 -> 2
         *   \    /
         *     add
         */
        head.setNext(addNode);
        length++;

        return true;
    }

    /**
     * 尾插法
     *
     * @param value 值
     * @author Fan
     * @since 2023/2/22 13:57
     */
    public boolean addLast(T value) {
        Node<T> addNode = new Node<>(value);

        // 找到链表的最后一个节点
        Node<T> current = head;
        while (null != current.getNext()) {
            current = current.getNext();
        }

        // head -> 1 -> 2 -> add
        current.setNext(addNode);
        length++;

        return true;
    }

    /**
     * 插入到指定位置
     *
     * @param value 值
     * @param index 位置
     * @author Fan
     * @since 2023/2/22 13:58
     */
    public boolean add(T value, int index) {
        if (0 == index) {
            addFirst(value);
        } else {
            Node<T> addNode = new Node<>(value);

            // 获取添加节点位置的前一个节点
            Node<T> current = getNode(index - 1);
            /*
             * head -> 1(current) -> 2 -> 3
             *                      /
             *                   add
             */
            addNode.setNext(current.getNext());
            /*
             * head -> 1(current)    2 -> 3
             *              \       /
             *                 add
             */
            current.setNext(addNode);
            length++;
        }

        return true;
    }

    /**
     * 获取指定位置的节点
     *
     * @param index 位置
     * @return {@link Node<T>}
     * @author Fan
     * @since 2023/2/20 15:58
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("位置超出链表范围");
        }

        Node<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.getNext();
        }

        return current;
    }

    /**
     * 获取指定位置的节点值
     *
     * @param index 位置
     * @return {@link T}
     * @author Fan
     * @since 2023/2/20 15:14
     */
    public T get(int index) {
        return getNode(index).getValue();
    }

    /**
     * 删除并返回指定位置的节点
     *
     * @param index 位置
     * @author Fan
     * @since 2023/2/20 15:16
     */
    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("位置超出链表范围");
        }

        // 获取删除节点位置的前一个节点
        Node<T> current = 0 == index ? head : getNode(index - 1);

        // 获取删除节点
        Node<T> delNode = current.getNext();
        /*
         * head -> 1(current)    2(del) -> 3
         *               \________________/
         */
        current.setNext(delNode.getNext());
        length--;

        return delNode.getValue();
    }

    /**
     * 查找元素第一次在链表中出现的位置
     *
     * @param value 值
     * @return {@link int}
     * @author Fan
     * @since 2023/2/20 15:30
     */
    public int indexOf(T value) {
        Node<T> current = head;

        for (int i = 0; null != current.getNext(); i++) {
            current = current.getNext();
            if (value.equals(current.getValue())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 清空链表
     *
     * @author Fan
     * @since 2023/2/20 16:19
     */
    public boolean clear() {
        // 头节点的 next 指向 null 就是空链表
        head.setNext(null);
        length = 0;

        return true;
    }

    /**
     * 链表反转
     *
     * @author Fan
     * @since 2023/2/22 13:53
     */
    public boolean reverse() {
        // 存储当前节点的前驱节点
        Node<T> prev = null;
        // 例: head -> 1(current) -> 2 -> 3
        Node<T> current = head.getNext();

        while (null != current) {
            /*
             * 存储当前节点的后继节点
             * (1) head -> 1(current) -> 2(next) -> 3
             * (2) head -> 1(prev) -> null 2(current) -> 3(next)
             * (3) head -> 1 -> null
             *     2(prev) -> 1 -> null 3(current) null(next)
             */
            Node<T> next = current.getNext();

            /*
             * 让当前节点的 next 指向当前节点的前驱节点, 完成反转操作
             * (1) head -> 1(current) -> null(prev) 2(next) -> 3
             * (2) head -> 1(prev) -> null
             *     2(current) -> 1(prev) -> null 3(next)
             * (3) head -> 1 -> null
             *     3(current) -> 2(prev) -> 1 -> null null(next)
             */
            current.setNext(prev);

            /*
             * 将当前节点赋给当前节点的前驱节点
             * (1) head -> 1(prev) -> null 2(next) -> 3
             * (2) head -> 1 -> null
             *     2(prev) -> 1 -> null 3(next)
             * (3) head -> 1 -> null
             *     3(prev) -> 2 -> 1 -> null null(next)
             */
            prev = current;
            /*
             * 将当前节点的后继节点赋给当前节点, 即后移一位
             * (1) head -> 1(prev) -> null 2(current) -> 3
             * (2) head -> 1 -> null
             *     2(prev) -> 1 -> null 3(current)
             * (3) head -> 1 -> null
             *     3(prev) -> 2 -> 1 -> null null(current)
             */
            current = next;
        }

        // 将头结点的 next 指向遍历的最后一个节点 prev, 即反转后的头结点, 链接到反转后的链表, head -> 3(prev) -> 2 -> 1 -> null
        head.setNext(prev);

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return null != current.getNext();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                current = current.getNext();
                return current.getValue();
            }
        };
    }
}
