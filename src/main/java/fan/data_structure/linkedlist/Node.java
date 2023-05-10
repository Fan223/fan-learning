package fan.data_structure.linkedlist;

/**
 * 单链表节点类
 *
 * @author Fan
 * @since 2023/2/20 10:00
 */
public class Node<T> {

    /**
     * 节点值
     */
    private T value;

    /**
     * 后继节点
     */
    private Node<T> next;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
