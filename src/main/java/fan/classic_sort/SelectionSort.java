package fan.classic_sort;

/**
 * 选择排序
 *
 * @author Fan
 * @since 2023/3/1 15:21
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        selectionSort(data);

        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    /**
     * 与插入排序类似, 同样将数组分为已排序区间和未排序区间, 不同的是遍历未排序区间, 找到最小的元素, 将其放到已排序区间的末尾,
     * 或者说放到未排序区间的开头, 这里是交换位置, 不是插入, 不需要移动元素
     *
     * @param data 待排序数组
     * @author Fan
     * @since 2023/3/2 14:08
     */
    public static void selectionSort(int[] data) {
        // 遍历次数, 每一遍找到最小的元素, 放到该遍循环的最开头, 前面已遍历的的不参与遍历, 最后一遍时数组已经是排好序的了, 次数为 length - 1
        for (int i = 0; i < data.length - 1; i++) {
            // 定义最小元素的下标, 为当前循环的首位元素
            int minIndex = i;

            // 循环比较, 找到最小元素的下标
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) {
                    minIndex = j;
                }
            }

            // 判断最小元素的下标是否就是首位元素, 有变动再进行调换双方的值, 让首位元素为最小值
            if (minIndex != i) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }
}
