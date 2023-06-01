package fan.data_structure.stack;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 链式栈节点
 *
 * @author Fan
 * @since 2023/2/28 9:44
 */
@NoArgsConstructor
@AllArgsConstructor
public class StackNode<T> {

    public T value;

    public StackNode<T> next;

    public StackNode(T value) {
        this.value = value;
    }
}

