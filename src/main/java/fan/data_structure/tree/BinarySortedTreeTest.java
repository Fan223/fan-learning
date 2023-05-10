package fan.data_structure.tree;//package fan.tree;
//
//public class BinarySortedTreeTest {
//    public static void main(String[] args) {
//        BinarySortedTree binarySortedTree = new BinarySortedTree();
//
//        // binarySortedTree.insert(binarySortedTree.root, 1);
//        binarySortedTree.insert(5);
//        binarySortedTree.insert(7);
//        binarySortedTree.insert(2);
//        binarySortedTree.insert(1);
//        binarySortedTree.insert(3);
//        binarySortedTree.insert(4);
//        binarySortedTree.insert(6);
//        binarySortedTree.insert(12);
//        binarySortedTree.insert(10);
//        binarySortedTree.insert(9);
//        binarySortedTree.insert(11);
//        binarySortedTree.insert(13);
//
//        System.out.print("先序遍历：");
//        binarySortedTree.preOrder(binarySortedTree.root); // 5 2 4 8
//        System.out.println();
//        System.out.print("中序遍历：");
//        binarySortedTree.inOrder(binarySortedTree.root); // 2 4 5 8
//        System.out.println();
//        System.out.print("后序遍历：");
//        binarySortedTree.postOrder(binarySortedTree.root); // 1 4 2 6 9 8 5
//        System.out.println();
//        System.out.print("层次遍历：");
//        binarySortedTree.levelOrder(binarySortedTree.root); // 5 2 8 1 4 6 9
//
//        System.out.println("查找值【3】对应的节点：" + binarySortedTree.find(3));
//
//        binarySortedTree.delete(7);
//        System.out.print("删除值【7】后的二叉排序树：");
//        binarySortedTree.levelOrder(binarySortedTree.root);
//    }
//}
