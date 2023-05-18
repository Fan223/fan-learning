package fan.classic_sort;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基数排序
 *
 * @author Fan
 * @since 2023/5/18 14:54
 */
@Slf4j
public class RadixSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        int[] radixSort = radixSort(data);

        for (int datum : radixSort) {
            log.info(datum + " ");
        }
    }

    /**
     * <ul>
     *     <li> 先得到数组数据的最大值, 然后计算最大值的位数, 位数即遍历次数, 从低位开始排序 </li>
     *     <li> 每一位的排序方式采用稳定排序, 这里使用桶排序, 不过桶的大小为 1, 每排好一位就重新替换原来的数组顺序为排好当前位后的顺序 </li>
     * </ul>
     *
     * @param arr 待排序数组
     * @return {@link int[]}
     * @author Fan
     * @since 2023/5/18 15:16
     */
    private static int[] radixSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }

        // 获取数组数据的最大值
        int maxValue = arr[0];
        for (int v : arr) {
            if (v > maxValue) {
                maxValue = v;
            }
        }

        // 求最大值的位数
        int n = 1;
        while (maxValue / 10 != 0) {
            maxValue = maxValue / 10;
            n += 1;
        }

        // 位数即遍历次数, 从最低位开始, 对每一位进行排序
        for (int i = 0; i < n; i++) {
            // 其中对每一位的排序需要采用稳定排序, 则使用桶排序, 桶大小为 1
            // 创建桶集合, 由于一位最多只有 0-9 十个数, 即最多十个桶
            List<List<Integer>> radix = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                radix.add(new ArrayList<>());
            }

            // 取数组数据的一位分配到对应的桶里
            for (int v : arr) {
                int index = (v / (int) Math.pow(10, i)) % 10;
                radix.get(index).add(v);
            }

            // 将各个桶的数据依次取出形成新的数组顺序
            int index = 0;
            for (List<Integer> rdx : radix) {
                for (Integer v : rdx) {
                    arr[index++] = v;
                }
            }
        }

        return arr;
    }
}
