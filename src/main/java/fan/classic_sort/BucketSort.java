package fan.classic_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 桶排序
 *
 * @author Fan
 * @since 2023/5/18 8:46
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        List<Integer> list = bucketSort(Arrays.stream(data).boxed().collect(Collectors.toList()), 3);
        System.out.println(list);
    }

    /**
     * <ul>
     *     <li> 先得到数组的数据范围, 然后除以每个桶的大小计算出桶的数量, 再创建对应数量的桶, 添加进桶的集合里 </li>
     *     <li> 然后将数据分配进对应的桶里, 如果数据不均匀导致某些桶的数据过多, 则对该桶再进行划分 </li>
     *     <li> 然后再将每个桶的数据依次放入结果集里 </li>
     * </ul>
     *
     * @param arr        待排序数组
     * @param bucketSize 桶的大小
     * @return {@link List<Integer>}
     * @author Fan
     * @since 2023/5/18 9:14
     */
    private static List<Integer> bucketSort(List<Integer> arr, int bucketSize) {
        if (arr.size() < 2 || bucketSize == 0) {
            return arr;
        }

        // 数组数据范围
        int[] extremum = getMinAndMax(arr);
        int minValue = extremum[0];
        int maxValue = extremum[1];
        // 桶的数量
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        // 各个桶的集合
        List<List<Integer>> buckets = new ArrayList<>();

        // 有几个桶即创建几个, 添加到桶的集合中
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将数据分配到各个桶中
        for (Integer v : arr) {
            int index = (v - minValue) / bucketSize;
            buckets.get(index).add(v);
        }

        // 单独对每个桶进行排序
        for (int i = 0; i < buckets.size(); i++) {
            // 这里的排序继续使用桶排序递归的方式
            if (buckets.get(i).size() > 1) {
                buckets.set(i, bucketSort(buckets.get(i), bucketSize / 2));
            }
        }

        // 结果集
        List<Integer> result = new ArrayList<>();
        // 将各个桶中的数据合并到结果集中
        for (List<Integer> bucket : buckets) {
            result.addAll(bucket);
        }
        return result;
    }

    private static int[] getMinAndMax(List<Integer> arr) {
        int maxValue = arr.get(0);
        int minValue = arr.get(0);
        for (Integer v : arr) {
            if (v > maxValue) {
                maxValue = v;
            } else if (v < minValue) {
                minValue = v;
            }
        }

        return new int[]{minValue, maxValue};
    }
}
