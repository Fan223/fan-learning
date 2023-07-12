package fan.classic_sort;

import fan.data_structure.heap.MaxTopHeap;

/**
 * 堆排序
 *
 * @author Fan
 * @since 2023/6/7 14:24
 */
public class HeapSort {
    public static void main(String[] args) {
        // 下面的计算都是以数组从下标 1 的位置开始的, 所以前面存了一个 0. 假如要从下标 0 的位置开始计算, 那么计算子节点的公式再都加上 1 即可
        int[] data = {0, 6, 4, 3, 23, 27, 23, 14, 10};
        heapSort(data, data.length - 1);

        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    /**
     * 建堆, 即从上往下堆化的过程, 因为叶子节点往下堆化只能自己跟自己比较, 所以直接从第一个非叶子节点开始, 依次堆化就行了 <br>
     * 由于堆是一个完全二叉树, 所以 n/2 到 n 的节点都是叶子节点, 即只需要堆化 1 到 n/2 的节点就可以了
     *
     * @param arr 数组
     * @param n   数据个数
     * @author Fan
     * @since 2023/6/7 14:26
     */
    private static void buildHeap(int[] arr, int n) {
        for (int i = n / 2; i >= 1; i--) {
            MaxTopHeap.heapify(arr, n, i);
        }
    }

    /**
     * 排序, 即将元素一个个放到堆顶, 依次堆化, 直到最后一个元素, 则排序完成
     *
     * @param arr 数组
     * @param n   元素个数
     * @author Fan
     * @since 2023/6/7 14:37
     */
    public static void heapSort(int[] arr, int n) {
        buildHeap(arr, n);
        int k = n;
        while (k > 1) {
            MaxTopHeap.swap(arr, 1, k--);
            MaxTopHeap.heapify(arr, k, 1);
        }
    }
}
