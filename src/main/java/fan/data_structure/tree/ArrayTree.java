package fan.data_structure.tree;

/**
 * 数组树
 *
 * @author Fan
 * @since 2023/5/22 14:42
 */
public class ArrayTree {

    private final int[] storage;

    public ArrayTree() {
        storage = new int[8];
    }

    public ArrayTree(int length) {
        storage = new int[length];
    }

    /**
     * 添加根节点
     *
     * @param val 节点值
     * @author Fan
     * @since 2023/5/22 14:43
     */
    public void addRoot(int val) {
        storage[1] = val;
    }

    /**
     * 添加左子节点
     *
     * @param index 父节点位置
     * @param val   节点值
     * @author Fan
     * @since 2023/5/22 14:44
     */
    public void addLeft(int index, int val) {
        if (index > storage.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        storage[2 * index] = val;
    }

    /**
     * 添加右子节点
     *
     * @param index 父节点位置
     * @param val   节点值
     * @author Fan
     * @since 2023/5/22 14:59
     */
    public void addRight(int index, int val) {
        storage[2 * index + 1] = val;
    }

    /**
     * 获取一个节点的父节点
     *
     * @param index 节点位置
     * @return {@link int}
     * @author Fan
     * @since 2023/5/22 15:00
     */
    public int getParent(int index) {
        if (index > storage.length || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return storage[index / 2];
    }

    /**
     * 遍历
     *
     * @author Fan
     * @since 2023/5/22 15:01
     */
    public void query() {
        for (int i = 1; i < storage.length; i++) {
            System.out.print(storage[i] + " ");
        }
        System.out.println();
    }
}
