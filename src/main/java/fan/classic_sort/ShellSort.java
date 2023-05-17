package fan.classic_sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 希尔排序
 *
 * @author Fan
 * @since 2023/5/16 9:15
 */
@Slf4j
public class ShellSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        shellSort(data);

        for (int datum : data) {
            log.info(datum + " ");
        }
    }

    /**
     * 将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序, 待整个序列中的记录 “基本有序” 时, 再对全体记录进行依次直接插入排序
     *
     * @param arr 待排序数组
     * @author Fan
     * @since 2023/5/17 15:14
     */
    public static void shellSort(int[] arr) {
        int length = arr.length;
        int gap = length / 2;

        while (gap > 0) {
            // 子序列进行插入排序
            for (int i = gap; i < length; i++) {
                int current = arr[i];
                int preIndex = i - gap;

                while (preIndex >= 0 && arr[preIndex] > current) {
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = current;
            }

            gap /= 2;
        }
    }
}
