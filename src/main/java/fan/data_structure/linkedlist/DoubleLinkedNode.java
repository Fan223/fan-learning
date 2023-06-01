package fan.data_structure.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 双向链表操作类
 *
 * @author Fan
 * @since 2023/2/22 17:11
 */
public class DoubleLinkedNode<T> implements Iterable<T> {

    /**
     * 头结点, 哨兵节点
     */
    private final DoubleNode<T> head;

    private int length;

    public DoubleLinkedNode() {
        head = new DoubleNode<>();
        length = 0;
    }

    public int getLength() {
        return length;
    }

    /**
     * 头插法
     *
     * @param value 节点值
     * @author Fan
     * @since 2023/2/24 14:31
     */
    public boolean addFirst(T value) {
        // 链表长度为 0, 即尾插法
        if (length == 0) {
            addLast(value);
        } else {
            // 例: head <-> 1 <-> 2
            DoubleNode<T> addDoubleNode = new DoubleNode<>(value);

            // head -> 1 <-> 2
            // add <- 1
            head.next.prev = addDoubleNode;
            // head -> 1 <-> 2
            // add <-> 1
            addDoubleNode.next = head.next;
            // head -> add <-> 1 <-> 2
            head.next = addDoubleNode;
            // head <-> add <-> 1 <-> 2
            addDoubleNode.prev = head;

            length++;
        }

        return true;
    }

    /**
     * 尾插法
     *
     * @param value 节点值
     * @author Fan
     * @since 2023/2/24 14:31
     */
    public boolean addLast(T value) {
        // 找到链表的最后一个节点
        DoubleNode<T> current = head;
        while (null != current.next) {
            current = current.next;
        }

        // 例: head <-> 1 <-> 2(current)
        DoubleNode<T> addDoubleNode = new DoubleNode<>(value);

        // head <-> 1 <-> 2(current) -> add
        current.next = addDoubleNode;
        // head <-> 1 <-> 2(current) <-> add
        addDoubleNode.prev = current;

        length++;
        return true;
    }

    /**
     * 插入到指定位置
     *
     * @param value 节点值
     * @param index 指定位置
     * @author Fan
     * @since 2023/2/24 14:31
     */
    public boolean add(T value, int index) {
        if (0 == index) {
            // 如果添加位置等于 0, 即头插法
            addFirst(value);
        } else if (length == index) {
            // 如果添加位置等于链表长度, 即尾插法
            addLast(value);
        } else {
            // 获取添加位置的上一个节点
            DoubleNode<T> current = getDoubleNode(index - 1);

            // 例: head <-> 1 <-> 2 <-> 3, current = 2
            DoubleNode<T> addDoubleNode = new DoubleNode<>(value);

            // head <-> 1 <-> 2 -> 3
            //              add <- 3
            current.next.prev = current;
            // head <-> 1 <-> 2 -> 3
            //              add <-> 3
            addDoubleNode.next = current.next;
            // head <-> 1 <-> 2 -> add <-> 3
            current.next =addDoubleNode;
            // head <-> 1 <-> 2 <-> add <- 3
            addDoubleNode.prev = current;

            length++;
        }

        return true;
    }

    /**
     * 返回指定位置的节点
     *
     * @param index 指定位置
     * @return {@link DoubleNode<T>}
     * @author Fan
     * @since 2023/2/24 14:12
     */
    private DoubleNode<T> getDoubleNode(int index) {
        if (index < 0 || length <= index) {
            throw new IndexOutOfBoundsException("该位置超出链表范围");
        }

        DoubleNode<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }

        return current;
    }

    /**
     * 返回指定位置的节点值
     *
     * @param index 指定位置
     * @return {@link T}
     * @author Fan
     * @since 2023/2/24 14:12
     */
    public T get(int index) {
        return getDoubleNode(index).value;
    }

    /**
     * 移除指定位置的节点并返回删除的节点值
     *
     * @param index 指定位置
     * @return {@link T}
     * @author Fan
     * @since 2023/2/24 14:14
     */
    public T remove(int index) {
        // 获取删除节点, 例: head <-> 1(current) <-> 2
        DoubleNode<T> delDoubleNode = getDoubleNode(index);

        // head <-> 1 -> 2
        // head <- 2
        delDoubleNode.next.prev = delDoubleNode.prev;
        // head <- 1 -> 2
        // head <-> 2
        delDoubleNode.prev.next = delDoubleNode.next;

        length--;
        return delDoubleNode.value;
    }

    /**
     * 返回节点值出现的下标
     *
     * @param value 节点值
     * @return {@link int}
     * @author Fan
     * @since 2023/2/24 14:31
     */
    public int indexOf(T value) {
        DoubleNode<T> current = head;

        for (int i = 0; null != current.next; i++) {
            current = current.next;
            if (value.equals(current.value)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 清空链表
     *
     * @author Fan
     * @since 2023/2/24 14:32
     */
    public boolean clear() {
        head.next = null;
        length = 0;

        return true;
    }

    /**
     * 反转链表
     *
     * @author Fan
     * @since 2023/2/24 14:45
     */
    public boolean reverse() {
        // 存储当前节点的前驱节点
        DoubleNode<T> prev = null;
        DoubleNode<T> current = head.next;

        while (null != current) {
            // 存储当前节点的后继节点
            DoubleNode<T> next = current.next;

            // 将当前节点的 prev 指向当前节点的后继节点, 与下一行代码一起完成反转操作
            current.prev = next;
            // 将当前节点的 next 指向当前节点的前驱节点
            current.next = prev;

            // 将当前节点赋给当前节点的前驱节点
            prev = current;
            // 将当前节点的后继节点赋给当前节点, 即后移一位
            current = next;
        }

        // 将头结点的 next 指向遍历的最后一个节点 prev, 即反转后的头结点, 链接到反转后的链表
        head.next = prev;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            DoubleNode<T> current = head;

            @Override
            public boolean hasNext() {
                return null != current.next;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                current = current.next;
                return current.value;
            }
        };
    }
}
