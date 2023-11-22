package fan.letcode.array;

/**
 * <p> 美化数组的最少删除数 </p>
 * 一个下标从 0 开始的整数数组 nums, 如果满足下述条件, 则认为数组 nums 是一个美丽数组
 * <ul>
 *     <li> nums.length 为偶数 </li>
 *     <li> 对所有满足 i % 2 == 0 的下标 i, nums[i] != nums[i + 1] 均成立 </li>
 *     <li> 空数组同样认为是美丽数组 </li>
 * </ul>
 * 可以从 nums 中删除任意数量的元素, 当删除一个元素时, 被删除元素右侧的所有元素将会向左移动一个单位以填补空缺,
 * 而左侧的元素将会保持不变, 返回使 nums 变为美丽数组所需删除的最少元素数目
 * <p> 例: nums = [1,1,2,3,5], 可以删除 nums[0] 或 nums[1], 这样得到的 nums = [1,2,3,5] 是一个美丽数组 </p>
 * <p> 例: nums = [1,1,2,2,3,3], 可以删除 nums[0] 和 nums[5], 这样得到的 nums = [1,2,2,3] 是一个美丽数组 </p>
 *
 * @author Fan
 * @since 2023/11/22 8:53
 */
public class MinDeletion {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 3};
        int i = minDeletion(nums);
        System.out.println(i);
    }

    public static int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        boolean check = true;

        for (int i = 0; i + 1 < n; ++i) {
            /*
             * 判断相邻的两个数是否相等(current 与 next), 如果相等, 则需要删除的数量+1(即删除 current), 同时继续往后比较, 直到不相等,
             * 则此时的 current 与 next 形成了一组 nums[i] != nums[i + 1]. 因此当继续往后走到 next 变成 current, 不需要往后比较
             * 这里用一个布尔值来记录状态, 表示此时 current 是与前面的数一组, 跳过比较. 跳过比较的时候就将状态反转, 下一次则需要比较
             */
            if (check && nums[i] == nums[i + 1]) {
                ++ans;
            } else {
                check = !check;
            }
        }

        // 数组的原始长度减去删除的元素数量, 如果为奇数, 还需要将最后一个元素删除, 即需要删除的数量+1
        if ((n - ans) % 2 != 0) {
            ++ans;
        }
        return ans;

        /*
         * 过程示例:
         * 1. [1(current), 1(next), 2, 2, 3, 3] -> [1(current), 2(next), 2, 3, 3]
         * 2. [1(current), 2(next), 2, 3, 3] -> [1, 2(current), 2(next), 3, 3], 此时的 current 跳过比较
         * [1, 2(current), 2(next), 3, 3] -> [(1, 2), 2(current), 3(next), 3], 重复前面的步骤即可
         * [(1, 2), (2, 3), 3] -> [(1, 2), (2, 3)], 由于删除了一位元素, 数组长度减去删除的元素数量为奇数, 需要将最后一个元素删除
         */
    }
}
