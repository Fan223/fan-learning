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

    public static void shellSort(int[] arr) {
        int length = arr.length;
        int gap = length / 2;

        while (gap > 0) {
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
