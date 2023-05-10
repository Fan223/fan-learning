package fan.classic_sort;

/**
 * 冒泡排序
 *
 * @author Fan
 * @since 2023/3/1 10:42
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        insertionSort(data);

        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    /**
     * <ul>
     *     <li> 将数组分为已排序和未排序两个区间, 每次都遍历已排序的区间, 取未排序区间的第一位插入到已排序区间里合适有序的位置 </li>
     *     <li> 第一个数不需要比较, 直接插入, 即不需要考虑, 直接从第二个数开始 </li>
     *     <li> 第二个数开始从后面往前面遍历比较, 假如第一个数比第二个数大, 则将第一位数后移一位到第二位, 第二位数赋值给第一位 </li>
     *     <li> 如上, 后面的数从自身开始从后面往前面遍历比较, 插入到合适有序的位置, 最后完成排序 </li>
     * </ul>
     *
     * @param data 待排序数组
     * @author Fan
     * @since 2023/3/1 15:29
     */
    public static void insertionSort(int[] data) {
        // 排序的遍历次数, 第一个数不需要考虑, 直接插入, 次数为 length - 1
        for (int i = 1; i < data.length; i++) {
            // 存储当前元素的值
            int current = data[i];
            // 当前元素的前一个元素的下标
            int preIndex = i - 1;

            // 从当前元素开始从后向前排序, 假如前面的元素大于当前元素, 则往后移一位, 直到当前元素插入到合适的位置
            while (preIndex >= 0 && data[preIndex] > current) {
                data[preIndex + 1] = data[preIndex];
                preIndex -= 1;
            }

            // 插入当前元素, 因为前面减了 1, 因此这里要加上 1 才为当前元素的位置
            data[preIndex + 1] = current;
        }
    }
}
