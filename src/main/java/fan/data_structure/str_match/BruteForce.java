package fan.data_structure.str_match;

/**
 * 暴力匹配
 *
 * @author Fan
 * @since 2023/7/11 15:07
 */
public class BruteForce {

    public static void main(String[] args) {
        String main = "baddef";
        String pattern = "ef";
        System.out.println(bruteForce(main, pattern));
    }

    /**
     * 暴力匹配, 主串每次后移一位与模式串比较
     *
     * @param main    主串
     * @param pattern 模式串
     * @return {@link int}
     * @author Fan
     * @since 2023/7/11 15:40
     */
    public static int bruteForce(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }

        for (int i = 0; i <= main.length() - pattern.length(); i++) {
            int j;
            for (j = 0; j < pattern.length(); j++) {
                // 每次主串后移一位与模式串比较
                if (main.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }

            if (j == pattern.length()) {
                return i;
            }
        }

        return -1;
    }
}
