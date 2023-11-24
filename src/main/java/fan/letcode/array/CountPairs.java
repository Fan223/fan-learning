package fan.letcode.array;

import fan.core.collection.ListUtil;
import fan.data_structure.tree.BinarySearchTree;

import java.util.Collections;
import java.util.List;

/**
 * <p> 统计和小于目标的下标对数目 </p>
 * 一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target,
 * 返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目
 * <p> 例: nums = [-1,1,2,3,1], target = 2; 则下标对有 (0, 1)、(0, 2)、(0, 4) </p>
 *
 * @author Fan
 * @since 2023/11/24 11:34
 */
public class CountPairs {
    public static void main(String[] args) {
        List<Integer> nums = ListUtil.list(-1, 1, 2, 3, 1);
        System.out.println(countPairs1(nums, 2));
        System.out.println(countPairs2(nums, 2));
        System.out.println(countPairs3(nums, 2));
    }

    /**
     * 双指针, 思想与二分查找类似, 但查找不再使用二分, 而是使用双指针来记录
     * <p> 时间复杂度: O(nlogn), 空间复杂度: O(logn) </p>
     *
     * @param nums   数组
     * @param target 目标值
     * @return {@link int}
     * @author Fan
     * @since 2023/11/24 16:33
     */
    public static int countPairs3(List<Integer> nums, int target) {
        Collections.sort(nums);
        int count = 0;

        for (int i = 0, j = nums.size() - 1; i < j; i++) {
            while (i < j && nums.get(i) + nums.get(j) >= target) {
                j--;
            }
            count += j - i;
        }
        return count;
    }

    /**
     * 已知 0 <= i < j < n 且 nums[i] + nums[j] < target,
     * 则可以看成在 nums[0, j-1] 中查找 nums[i] < nums[target - nums[j]] 的个数,
     * 对进行数组排序, 则可以看成查找小于 nums[target - nums[j]] 的最大值的下标, 下标加1即个数, 可以使用二分查找来提高效率.
     * <p> 时间复杂度: O(nlogn), 空间复杂度: O(logn) </p>
     *
     * @param nums   数组
     * @param target 目标值
     * @return {@link int}
     * @author Fan
     * @since 2023/11/24 16:31
     */
    public static int countPairs2(List<Integer> nums, int target) {
        Collections.sort(nums);
        int count = 0;

        for (int i = 1; i < nums.size(); i++) {
            int k = binarySearch(nums, 0, i - 1, target - nums.get(i));
            count += k;
        }
        return count;
    }

    /**
     * 二分查找小于 target 的数量
     *
     * @param nums   数组
     * @param l0     低位
     * @param l1     高位
     * @param target 目标值
     * @return {@link int}
     * @author Fan
     * @since 2023/11/24 16:32
     */
    public static int binarySearch(List<Integer> nums, int l0, int l1, int target) {
        // 设置初始值为高位加1, 当高位小于 target 时, 下面的循环不会进入重设 count 的部分, 此时高位则为最大值的下标, 加1则为数量
        int count = l1 + 1;

        while (l0 <= l1) {
            int mid = l0 + (l1 - l0) / 2;

            // 中间值不小于则高位减1, 小于则低位加1, 找到小于 target 的最大值的下标
            if (nums.get(mid) >= target) {
                // 重设 count, 放在设置下标之前, 下标加1才为数量
                count = mid;
                l1 = mid - 1;
            } else {
                l0 = mid + 1;
            }
        }
        return count;
    }

    /**
     * 循环枚举, 时间复杂度: O(n^2), 空间复杂度: O(1)
     *
     * @param nums   数组
     * @param target 目标值
     * @return {@link int}
     * @author Fan
     * @since 2023/11/24 16:33
     */
    public static int countPairs1(List<Integer> nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    count++;
                }
            }
        }
        return count;
    }
}
