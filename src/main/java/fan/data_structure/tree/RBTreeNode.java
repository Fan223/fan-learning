package fan.data_structure.tree;

/**
 * 红黑树节点
 *
 * @author Fan
 * @since 2023/5/23 9:42
 */
public class RBTreeNode<T extends Comparable<T>> {

    /**
     * 红色或黑色
     */
    boolean color;

    T key;

    RBTreeNode<T> left;

    RBTreeNode<T> right;

    RBTreeNode<T> parent;

    public RBTreeNode(T key, boolean color) {
        this.key = key;
        this.color = color;
    }
}
