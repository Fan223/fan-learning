package fan.classic_sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author Fan
 * @since 2023/3/2 17:12
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        int[] mergeSort = mergeSort(data);

        for (int sort : mergeSort) {
            System.out.print(sort + " ");
        }
    }

    /**
     * <ul>
     *     <li> 核心思想就是分治法, 将一个大问题转换为多个小问题来求解. 对一个数组进行排序, 可以转换为对数组的前一半和后一半进行排序 </li>
     *     <li> 前半数组和后半数组排好序后, 再把这两个排好序的数组合并为一个排好序的数组, 这里就需要一个核心的方法,
     *     用来将传入的两个有序数组合并为一个有序的数组, 这样就能返回一个最终的有序数组 </li>
     *     <li> 同时, 前一半数组又能分为前半和后半, 后一半数组也能分为前半和后半, 一直这样递归细分 "递" 下去, 直到前半和后半的长度为 1 或 0,
     *     这时直接返回数组, 因为 1 位的数组可以说就是有序的了, 然后再通过上面说到的核心方法, 将两个有序数组合并为一个, 然后一步步 "归" 回来,
     *     最终得到一个排好序的数组 </li>
     * </ul>
     *
     * @param data 待排序数组
     * @return {@link int[]}
     * @author Fan
     * @since 2023/3/3 10:28
     */
    public static int[] mergeSort(int[] data) {
        if (data.length <= 1) {
            return data;
        }

        // 分治, 一分为二, 划分为两个子问题来求解
        int middle = data.length / 2;
        int[] arr1 = Arrays.copyOfRange(data, 0, middle);
        int[] arr2 = Arrays.copyOfRange(data, middle, data.length);

        return merge(mergeSort(arr1), mergeSort(arr2));
    }

    /**
     * 将两个有序数组合并为一个有序数组
     *
     * @param arr1 有序数组1
     * @param arr2 有序数组2
     * @return {@link int[]}
     * @author Fan
     * @since 2023/3/3 10:40
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        // 定义一个数组来存两个有序数组合并后的有序数组
        int[] sortArr = new int[arr1.length + arr2.length];
        int index = 0;
        int index1 = 0;
        int index2 = 0;

        // 两个有序数组互相比较, 依次找到最小的元素插入到合并后的数组中
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                sortArr[index++] = arr1[index1++];
            } else {
                sortArr[index++] = arr2[index2++];
            }
        }

        // 当两个数组的其中一个比较完后, 直接将另一个数组全部插入合并后的数组里
        if (index1 < arr1.length) {
            while (index1 < arr1.length) {
                sortArr[index++] = arr1[index1++];
            }
        } else {
            while (index2 < arr2.length) {
                sortArr[index++] = arr2[index2++];
            }
        }

        return sortArr;
    }
}
