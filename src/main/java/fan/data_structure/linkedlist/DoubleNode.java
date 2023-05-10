package fan.data_structure.linkedlist;

/**
 * 双向链表节点类
 *
 * @author Fan
 * @since 2023/2/22 17:01
 */
public class DoubleNode<T> {

    /**
     * 节点值
     */
    private T value;

    /**
     * 前驱节点
     */
    private DoubleNode<T> prev;

    /**
     * 后继节点
     */
    private DoubleNode<T> next;

    public DoubleNode() {
    }

    public DoubleNode(T value) {
        this.value = value;
    }

    public DoubleNode(T value, DoubleNode<T> prev, DoubleNode<T> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public DoubleNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
}
