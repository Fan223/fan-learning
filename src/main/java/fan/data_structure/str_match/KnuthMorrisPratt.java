package fan.data_structure.str_match;

/**
 * KMP 算法
 *
 * @author Fan
 * @since 2023/7/13 9:41
 */
public class KnuthMorrisPratt {

    public static void main(String[] args) {
        String main = "ababaeabac";
        String pattern = "ababacd";
        System.out.println(knuthMorrisPratt(main, pattern));
    }

    private static int knuthMorrisPratt(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }

        char[] mainChars = main.toCharArray();
        char[] patterns = pattern.toCharArray();
        int[] next = getNexts(patterns);

        int j = 0;
        for (int i = 0; i < mainChars.length; i++) {
            // 失配, j 指向匹配的前缀的下标后一位
            while (j > 0 && mainChars[i] != patterns[j]) {
                j = next[j - 1] + 1;
            }
            // 匹配的前缀字符
            if (mainChars[i] == patterns[j]) {
                j++;
            }

            // 找到匹配模式串
            if (j == pattern.length()) {
                return i - pattern.length() + 1;
            }
        }

        return -1;
    }

    /**
     * 获取 Next 数组
     *
     * @param patterns 模式串
     * @return {@link int[]}
     * @author Fan
     * @since 2023/7/17 14:23
     */
    private static int[] getNexts(char[] patterns) {
        int[] next = new int[patterns.length];
        next[0] = -1;
        int k = -1;

        for (int i = 1; i < patterns.length; i++) {
            // 失配, 更改初始位置为公共前缀下标
            while (k != -1 && patterns[k + 1] != patterns[i]) {
                k = next[k];
            }

            // 前缀匹配到的字符
            if (patterns[k + 1] == patterns[i]) {
                k++;
            }
            next[i] = k;
        }

        return next;
    }
}
