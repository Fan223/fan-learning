package fan.data_structure.stack;

/**
 * 链式栈节点
 *
 * @author Fan
 * @since 2023/2/28 9:44
 */
public class StackNode<T> {
    /**
     * 节点值
     */
    private T value;
    /**
     * 后继节点
     */
    private StackNode<T> next;

    public StackNode() {
    }

    public StackNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public StackNode<T> getNext() {
        return next;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }
}

