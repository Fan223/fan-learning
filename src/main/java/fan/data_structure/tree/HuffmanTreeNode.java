package fan.data_structure.tree;

/**
 * 哈夫曼树节点类
 *
 * @author Fan
 * @since 2023/6/7 11:43
 */
public class HuffmanTreeNode<T> {

    public T data;

    public int weight;

    public HuffmanTreeNode<T> leftChild;

    public HuffmanTreeNode<T> rightChild;

    public HuffmanTreeNode(T data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public String toString() {
        return data + " " + weight;
    }
}
