package fan.data_structure.tree;

public class AVLTreeTest {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.root = avlTree.insert(avlTree.root, 30);
        avlTree.root = avlTree.insert(avlTree.root, 20);
        avlTree.root = avlTree.insert(avlTree.root, 40);
        avlTree.root = avlTree.insert(avlTree.root, 15);
        avlTree.root = avlTree.insert(avlTree.root, 25);
        avlTree.preOrder(avlTree.root);
        System.out.println();

        avlTree.root = avlTree.insert(avlTree.root, 20);
        avlTree.preOrder(avlTree.root);
        System.out.println();

        System.out.println("树的高度: " + avlTree.getHeight(avlTree.root));
        System.out.println("树的节点数: " + avlTree.getSize());
    }
}
