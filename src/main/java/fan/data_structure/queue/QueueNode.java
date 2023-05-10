package fan.data_structure.queue;

/**
 * 链式队列节点
 *
 * @author Fan
 * @since 2023/2/28 10:08
 */
public class QueueNode<T> {

    /**
     * 节点值
     */
    private T value;

    /**
     * 后继节点
     */
    private QueueNode<T> next;

    public QueueNode() {
    }

    QueueNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public QueueNode<T> getNext() {
        return next;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }
}
