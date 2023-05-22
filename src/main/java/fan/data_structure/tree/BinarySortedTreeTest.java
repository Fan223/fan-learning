package fan.data_structure.tree;

/**
 * 二叉排序树测试类
 *
 * @author Fan
 * @since 2023/5/22 15:46
 */
public class BinarySortedTreeTest {
    public static void main(String[] args) {
        BinarySortedTree binarySortedTree = new BinarySortedTree();

        binarySortedTree.insert(5);
        binarySortedTree.insert(7);
        binarySortedTree.insert(2);
        binarySortedTree.insert(1);
        binarySortedTree.insert(3);
        binarySortedTree.insert(4);
        binarySortedTree.insert(6);
        binarySortedTree.insert(12);
        binarySortedTree.insert(10);
        binarySortedTree.insert(9);
        binarySortedTree.insert(11);
        binarySortedTree.insert(13);

        System.out.print("先序遍历：");
        // 5 2 1 3 4 7 6 12 10 11 13
        binarySortedTree.preOrder(binarySortedTree.root);
        System.out.println();
        System.out.print("中序遍历：");
        // 1 2 3 4 5 6 7 9 10 11 12 13
        binarySortedTree.inOrder(binarySortedTree.root);
        System.out.println();
        System.out.print("后序遍历：");
        // 1 4 3 2 6 9 11 10 13 12 7 5
        binarySortedTree.postOrder(binarySortedTree.root);
        System.out.println();
        System.out.print("层次遍历：");
        // 5 2 7 1 3 6 12 4 10 13 9 11
        binarySortedTree.levelOrder(binarySortedTree.root);

        System.out.println("查找值【3】对应的节点：" + binarySortedTree.find(3));
        System.out.println("查找值最小的节点：" + binarySortedTree.findMin());

        binarySortedTree.delete(7);
        System.out.print("删除值【7】后的二叉排序树：");
        binarySortedTree.levelOrder(binarySortedTree.root);
    }
}
