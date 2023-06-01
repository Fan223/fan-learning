package fan.data_structure.linkedlist;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 单链表节点类
 *
 * @author Fan
 * @since 2023/2/20 10:00
 */
@NoArgsConstructor
@AllArgsConstructor
public class Node<T> {

    public T value;

    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
