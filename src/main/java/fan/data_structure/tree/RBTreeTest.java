package fan.data_structure.tree;

public class RBTreeTest {
    public static void main(String[] args) {
        RBTree<String> tree=new RBTree<>();

        tree.insert("A");
        tree.insert("C");
        tree.insert("E");
        tree.insert("H");
        tree.insert("L");
        tree.insert("M");
        tree.insert("P");
        tree.insert("R");
        tree.insert("S");
        tree.insert("X");

        tree.preOrder();
    }
}
