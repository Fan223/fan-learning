package fan.data_structure.tree;

/**
 * 数组树测试类
 *
 * @author Fan
 * @since 2023/5/22 14:44
 */
public class ArrayTreeTest {
    public static void main(String[] args) {
        ArrayTree arrayTree = new ArrayTree(10);
        arrayTree.addRoot(1);
        arrayTree.addLeft(1, 2);
        arrayTree.addRight(1, 3);

        arrayTree.addLeft(2, 4);
        arrayTree.addLeft(4, 5);
        arrayTree.query();

        System.out.println(arrayTree.getParent(8));
    }
}
