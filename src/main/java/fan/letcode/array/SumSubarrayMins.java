package fan.letcode.array;

/**
 * 子数组的最小值之和, 存在问题待完善
 *
 * @author Fan
 * @since 2023/11/27 9:01
 */
public class SumSubarrayMins {
    public static void main(String[] args) {
        int[] nums = {11, 81, 94, 43, 3};
        System.out.println(sumSubarrayMins(nums));
    }

    public static long sumSubarrayMins(int[] arr) {
        if (0 == arr.length) {
            return 0;
        } else if (1 == arr.length) {
            return arr[0];
        }

        int sum = 0;
        int mod = 1000000007;
        // 子串长度
        for (int i = 1; i <= arr.length; i++) {
            // 起始下标
            for (int j = 0; j + i <= arr.length; j++) {
                int min = arr[j];
                // 获取子串最小值
                for (int k = j + 1; k < j + i; k++) {
                    if (arr[k] < min) {
                        min = arr[k];
                    }
                }
                sum = (sum + min) % mod;
            }
        }

        return sum;
    }
}
