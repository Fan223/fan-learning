package fan.data_structure.str_match;

/**
 * BM 算法
 *
 * @author Fan
 * @since 2023/7/12 9:19
 */
public class BoyerMoore {

    private static final int SIZE = 256;

    public static void main(String[] args) {
        String main = "baddef";
        String pattern = "def";
        System.out.println(boyerMoore(main, pattern));
    }

    private static void generateTable(char[] patterns, int[] table) {
        for (int i = 0; i < SIZE; i++) {
            // 初始化散列表
            table[i] = -1;
        }
        for (int i = 0; i < patterns.length; ++i) {
            // 计算 chars[i] 的 ASCII 值
            int ascii = patterns[i];
            table[ascii] = i;
        }
    }

    private static int boyerMoore(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }

        char[] patterns = pattern.toCharArray();
        char[] mainChars = main.toCharArray();

        // 记录模式串中每个字符最后出现的位置
        int[] chars = new int[SIZE];
        // 构建坏字符哈希表
        generateTable(patterns, chars);
        // 构建好后缀及好后缀的公共前缀数组
        boolean[] prefix = new boolean[pattern.length()];
        int[] suffix = new int[pattern.length()];
        generateSub(patterns, prefix, suffix);

        for (int i = 0; i <= main.length() - pattern.length(); i++) {
            // 坏字符在模式串中的下标, 这里是从后向前匹配
            int j;
            for (j = patterns.length - 1; j >= 0; j--) {
                if (mainChars[i + j] != patterns[j]) {
                    break;
                }
            }

            // 下标小于 0, 则无坏字符, 匹配成功
            if (j < 0) {
                return i;
            }

            // 坏字符往后滑动 j - table[mainChars[i + j]] 位, 即 坏字符下标 - 坏字符在模式串中出现的下标
            int index = j - chars[mainChars[i + j]];
            int sufIndex = 0;
            if (j < pattern.length() - 1) { // 如果有好后缀的话
                sufIndex = moveBySub(j, pattern.length(), suffix, prefix);
            }
            i = i + Math.max(index, sufIndex);
        }

        return -1;
    }

    private static int moveBySub(int j, int length, int[] suffix, boolean[] prefix) {
        int k = length - 1 - j; // 好后缀长度
        if (suffix[k] != -1) {
            return j - suffix[k] + 1;
        }
        for (int r = j + 2; r <= length - 1; ++r) {
            if (prefix[length - r] == true) {
                return r;
            }
        }
        return length;
    }

    /**
     * 生成模式串对应的公共后缀子串的下标的数组及公共后缀子串是否存在公共前缀子串的数组
     *
     * @param patterns 模式串
     * @param prefix   前缀数组
     * @param suffix   后缀数组
     * @author Fan
     * @since 2023/7/12 16:51
     */
    private static void generateSub(char[] patterns, boolean[] prefix, int[] suffix) {
        // 初始化
        for (int i = 0; i < patterns.length; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < patterns.length - 1; i++) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            // 求公共后缀子串, patterns[patterns.length - 1 - k++] 即最后一个后缀字符
            // patterns[j--] 即出现后缀字符的位置, 然后依次往前判断
            while (j >= 0 && patterns[j] == patterns[patterns.length - 1 - k]) {
                // 表示公共后缀子串在模式串中的起始下标, k 需要先加 1
                suffix[++k] = j--;
            }

            // j 为 -1, 则表示公共后缀子串也是模式串的前缀子串
            if (-1 == j) {
                prefix[k] = true;
            }
        }
    }
}
