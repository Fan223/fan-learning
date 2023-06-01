package fan.data_structure.queue;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 链式队列节点
 *
 * @author Fan
 * @since 2023/2/28 10:08
 */
@NoArgsConstructor
@AllArgsConstructor
public class QueueNode<T> {

    public T value;

    public QueueNode<T> next;

    QueueNode(T value) {
        this.value = value;
    }
}
