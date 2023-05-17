package fan.classic_sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序
 *
 * @author Fan
 * @since 2023/5/17 14:37
 */
@Slf4j
public class QuickSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        quickSort(data, 0, data.length - 1);

        for (int sort : data) {
            log.info(sort + " ");
        }
    }

    /**
     * <ul>
     *     <li> 选择 p 到 r 之间的任意一个数据作为 pivot(分区点), 遍历 p 到 r 之间的数据, 将小于 pivot 的放到左边,
     *     将大于 pivot 的放到右边, 将 pivot 放到中间 </li>
     *     <li> 经过这一步骤之后, 数组 p 到 r 之间的数据就被分成了三个部分, 前面 p 到 q-1 之间都是小于 pivot 的, 中间是 pivot,
     *     后面的 q+1 到 r 之间是大于 pivot 的 </li>
     * </ul>
     *
     * @param arr  待排序数组
     * @param low  起始位置
     * @param high 终止位置
     * @author Fan
     * @since 2023/5/17 15:06
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    /**
     * 分区函数, 返回 pivot 的位置
     *
     * @param arr  要分区的数组
     * @param low  起始位置
     * @param high 终止位置
     * @return {@link int}
     * @author Fan
     * @since 2023/5/17 15:09
     */
    private static int partition(int[] arr, int low, int high) {
        int pointer = low;

        // 分区比较次数
        for (int i = low; i < high; i++) {
            // 这里取最后一位元素作为 pivot
            if (arr[i] <= arr[high]) {
                // 将小于 pivot 的依次交换到前面的位置
                int temp = arr[i];
                arr[i] = arr[pointer];
                arr[pointer] = temp;
                pointer++;
            }
        }

        // 交换 pivot 到两个序列中间位置
        int temp = arr[pointer];
        arr[pointer] = arr[high];
        arr[high] = temp;
        return pointer;
    }
}
