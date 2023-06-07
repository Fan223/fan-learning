package fan.data_structure.heap;

/**
 * 大顶堆
 *
 * @author Fan
 * @since 2023/6/6 11:25
 */
public class MaxTopHeap {

    public static final int DEFAULT_CAPACITY = 16;

    /**
     * 从下标 1 开始存储数据
     */
    private int[] storage;


    private int capacity;

    private int count;

    public MaxTopHeap() {
        storage = new int[DEFAULT_CAPACITY + 1];
        capacity = DEFAULT_CAPACITY;
        count = 0;
    }

    public MaxTopHeap(int capacity) {
        storage = new int[capacity + 1];
        this.capacity = capacity;
        count = 0;
    }

    /**
     * 遍历堆, 即遍历数组
     *
     * @author Fan
     * @since 2023/6/6 14:20
     */
    public void queryHeap() {
        for (int i = 1; i <= count; i++) {
            System.out.print(storage[i] + " ");
        }
        System.out.println();
    }

    public int size() {
        return count;
    }

    /**
     * 插入元素到堆中, 先插入到最后, 然后依次向上比较其与父节点的值, 假如父节点的值小, 则将其进行替换
     *
     * @param data 元素
     * @author Fan
     * @since 2023/6/6 14:21
     */
    public void insert(int data) {
        // 堆满
        if (count >= capacity) {
            return;
        }

        // 将数据插入到最后一个元素的后面
        storage[++count] = data;
        int i = count;

        // 自下往上堆化
        while (i / 2 > 0 && storage[i] > storage[i / 2]) {
            swap(storage, i, i / 2);
            i = i / 2;
        }
    }

    /**
     * 交换下标为 i 和 j 的两个元素
     *
     * @param arr 数据
     * @param i   下标 i
     * @param j   下标 j
     * @author Fan
     * @since 2023/6/6 11:32
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 移除堆的最大值, 即堆顶元素. 先将最后一个元素移动到第一位, 即完成了最大值的删除, 然后从上往下进行堆化
     *
     * @author Fan
     * @since 2023/6/6 14:24
     */
    public void removeMax() {
        if (0 == count) {
            return;
        }

        storage[1] = storage[count--];
        heapify(storage, count, 1);
    }

    /**
     * 从上往下进行堆化, 即判断左右子节点的值是否大于父节点的值, 大于则交换位置
     *
     * @param storage 数组
     * @param count   元素数量
     * @param i       开始下标
     * @author Fan
     * @since 2023/6/6 14:26
     */
    public static void heapify(int[] storage, int count, int i) {
        while (true) {
            int max = i;

            // 左子节点是否大于父节点
            if (2 * i <= count && storage[max] < storage[2 * i]) {
                max = 2 * i;
            }
            // 右子节点是否大于父节点或左子节点
            if (2 * i + 1 <= count && storage[max] < storage[2 * i + 1]) {
                max = 2 * i + 1;
            }

            // 不大于则直接退出
            if (max == i) {
                break;
            }

            // 大于则交换位置
            swap(storage, i, max);
            // 从新的下标继续开始堆化
            i = max;
        }
    }
}
