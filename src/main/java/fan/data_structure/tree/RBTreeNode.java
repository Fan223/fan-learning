package fan.data_structure.tree;

/**
 * 红黑树节点
 *
 * @author Fan
 * @since 2023/5/23 9:42
 */
public class RBTreeNode<T extends Comparable<T>> {

    /**
     * red or black
     */
    boolean color;

    T key;

    RBTreeNode leftChildren;

    RBTreeNode rightChildren;

    RBTreeNode parent;

    public RBTreeNode(T key, boolean color) {
        this.key = key;
        this.color = color;
    }
}
