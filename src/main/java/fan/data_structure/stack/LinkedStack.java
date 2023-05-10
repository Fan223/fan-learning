package fan.data_structure.stack;

/**
 * 链式栈
 *
 * @author Fan
 * @since 2023/2/28 9:44
 */
public class LinkedStack<T> {

    /**
     * 头结点, 哨兵节点
     */
    private final StackNode<T> head;

    /**
     * 链表长度
     */
    private int length;

    public LinkedStack() {
        head = new StackNode<>();
        length = 0;
    }

    /**
     * 入栈, 即头插法
     *
     * @param value 节点值
     * @return {@link boolean}
     * @author Fan
     * @since 2023/2/28 10:05
     */
    public boolean push(T value) {
        StackNode<T> addStackNode = new StackNode<>(value);

        addStackNode.setNext(head.getNext());
        head.setNext(addStackNode);

        length++;
        return true;
    }

    /**
     * 出栈并返回栈顶元素
     *
     * @return {@link T}
     * @author Fan
     * @since 2023/2/28 9:53
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        StackNode<T> popStackNode = head.getNext();

        // 将头结点指向后继节点的后继节点
        head.setNext(popStackNode.getNext());
        length--;

        return popStackNode.getValue();
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }

        return head.getNext().getValue();
    }

    public boolean clear() {
        head.setNext(null);
        length = 0;

        return true;
    }

    public boolean isEmpty() {
        return null == head.getNext();
    }

    public int size() {
        return length;
    }
}
