package fan.data_structure.tree;

/**
 * 二叉查找树测试类
 *
 * @author Fan
 * @since 2023/5/22 15:46
 */
public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        binarySearchTree.insert(5);
        binarySearchTree.insert(7);
        binarySearchTree.insert(2);
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        binarySearchTree.insert(4);
        binarySearchTree.insert(6);
        binarySearchTree.insert(12);
        binarySearchTree.insert(10);
        binarySearchTree.insert(9);
        binarySearchTree.insert(11);
        binarySearchTree.insert(13);

        System.out.print("先序遍历: ");
        // 5 2 1 3 4 7 6 12 10 11 13
        binarySearchTree.preOrder(binarySearchTree.root);
        System.out.println();
        System.out.print("中序遍历: ");
        // 1 2 3 4 5 6 7 9 10 11 12 13
        binarySearchTree.inOrder(binarySearchTree.root);
        System.out.println();
        System.out.print("后序遍历: ");
        // 1 4 3 2 6 9 11 10 13 12 7 5
        binarySearchTree.postOrder(binarySearchTree.root);
        System.out.println();
        System.out.print("层次遍历: ");
        // 5 2 7 1 3 6 12 4 10 13 9 11
        binarySearchTree.levelOrder(binarySearchTree.root);

        System.out.println("查找值【3】对应的节点: " + binarySearchTree.find(3));
        System.out.println("查找值最小的节点: " + binarySearchTree.findMin());

        binarySearchTree.delete(7);
        System.out.print("删除值【7】后的二叉排序树: ");
        // 5 2 9 1 3 6 12 4 10 13 11
        binarySearchTree.levelOrder(binarySearchTree.root);
    }
}
