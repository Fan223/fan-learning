package fan.classic_sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 计数排序
 *
 * @author Fan
 * @since 2023/5/17 15:28
 */
@Slf4j
public class CountingSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        int[] countingSort = countingSort(data);

        for (int datum : countingSort) {
            log.info(datum + " ");
        }
    }

    /**
     * <ul>
     *     <li> 先得到数组的数据范围, 然后根据数据范围创建计数数组, 即为每一个范围内的值都分配一个 “桶” </li>
     *     <li> 然后计算数组相同值的元素个数, 即计数数组对应的索引的值. 然后对计数数组进行累加,
     *     表示小于等于该值的元素有几个, 也就是说该值是数组中的第几个元素 </li>
     *     <li> 最后参照计数数组, 将数组值一个个放到对应的位置 </li>
     * </ul>
     *
     * @param arr 待排序数组
     * @return {@link int[]}
     * @author Fan
     * @since 2023/5/17 16:33
     */
    private static int[] countingSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        // 获取数组中数据范围
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        // 根据数据范围创建对应的计数数组
        int[] countArr = new int[maxValue - minValue + 1];
        int[] result = new int[arr.length];

        // 计算每个元素的个数
        for (int v : arr) {
            countArr[v - minValue] += 1;
        }
        // 依次累加
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            // 获取数组值在计数数组中的值, 即表示数组的第几位元素, 减 1 即为索引位置
            int index = countArr[arr[i] - minValue] - 1;
            // 对应位置赋值
            result[index] = arr[i];
            // 计数数组相应的元素个数减 1
            countArr[arr[i] - minValue] -= 1;
        }
        return result;
    }

    /**
     * 获取数组中值最小的元素和最大的元素
     *
     * @param arr 数组
     * @return {@link int[]}
     * @author Fan
     * @since 2023/5/17 16:32
     */
    private static int[] getMinAndMax(int[] arr) {
        int maxValue = arr[0];
        int minValue = arr[0];
        for (int v : arr) {
            if (v > maxValue) {
                maxValue = v;
            } else if (v < minValue) {
                minValue = v;
            }
        }
        return new int[]{minValue, maxValue};
    }
}
