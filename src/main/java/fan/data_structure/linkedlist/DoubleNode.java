package fan.data_structure.linkedlist;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 双向链表节点类
 *
 * @author Fan
 * @since 2023/2/22 17:01
 */
@NoArgsConstructor
@AllArgsConstructor
public class DoubleNode<T> {

    public T value;

    public DoubleNode<T> prev;

    public DoubleNode<T> next;

    public DoubleNode(T value) {
        this.value = value;
    }
}
