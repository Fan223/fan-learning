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
        String pattern = "ef";
        System.out.println(boyerMoore(main, pattern));
    }

    /**
     * BM 算法, 先构建坏字符哈希表和好后缀及好后缀的前缀子串哈希表, 然后通过坏字符的下标来计算坏字符及好后缀移动的长度, 取两者最大值
     *
     * @param main    主串
     * @param pattern 模式串
     * @return {@link int}
     * @author Fan
     * @since 2023/7/13 9:42
     */
    private static int boyerMoore(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }

        char[] mainChars = main.toCharArray();
        char[] patterns = pattern.toCharArray();

        // 记录模式串中每个字符最后出现的位置
        int[] badChars = new int[SIZE];
        // 构建坏字符哈希表
        generateBadChar(patterns, badChars);
        // 构建好后缀哈希表以及好后缀的公共前缀哈希表
        boolean[] prefix = new boolean[pattern.length()];
        int[] suffix = new int[pattern.length()];
        generateGoodSuf(patterns, prefix, suffix);

        int i = 0;
        while (i <= main.length() - pattern.length()) {
            // 坏字符在模式串中的下标, 这里是从后向前匹配
            int badIndex;
            for (badIndex = patterns.length - 1; badIndex >= 0; badIndex--) {
                if (mainChars[i + badIndex] != patterns[badIndex]) {
                    break;
                }
            }

            // 下标小于 0, 则无坏字符, 匹配成功
            if (badIndex < 0) {
                return i;
            }

            // 坏字符往后滑动 j - badChars[mainChars[i + j]] 位, 即 坏字符下标 - 坏字符在模式串中出现的下标
            int index = badIndex - badChars[mainChars[i + badIndex]];
            int goodIndex = 0;
            // 坏字符下标不为模式串的最后一位即表示有好后缀
            if (badIndex < pattern.length() - 1) {
                goodIndex = moveByGoodSuf(badIndex, pattern.length(), prefix, suffix);
            }
            // 移动长度取坏字符和好后缀的最大值
            i += Math.max(index, goodIndex);
        }

        return -1;
    }

    private static void generateBadChar(char[] patterns, int[] badChars) {
        for (int i = 0; i < SIZE; i++) {
            // 初始化散列表
            badChars[i] = -1;
        }
        for (int i = 0; i < patterns.length; ++i) {
            // 计算 patterns[i] 的 ASCII 值
            int ascii = patterns[i];
            badChars[ascii] = i;
        }
    }

    /**
     * 生成模式串对应的公共后缀子串的下标的哈希表以及公共后缀子串是否存在公共前缀子串的哈希表
     *
     * @param patterns 模式串
     * @param prefix   前缀数组
     * @param suffix   后缀数组
     * @author Fan
     * @since 2023/7/12 16:51
     */
    private static void generateGoodSuf(char[] patterns, boolean[] prefix, int[] suffix) {
        // 初始化
        for (int i = 0; i < patterns.length; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }

        for (int i = 0; i < patterns.length - 1; i++) {
            int j = i;
            // 公共后缀子串长度
            int k = 0;
            // 求公共后缀子串, patterns[patterns.length - 1 - k++] 即最后一个后缀字符开始往前判断
            // patterns[j--] 即出现后缀字符的位置开始往前判断
            while (j >= 0 && patterns[j] == patterns[patterns.length - 1 - k]) {
                // 表示公共后缀子串在模式串中的起始下标, k 初始为 0, 需要先加 1
                suffix[++k] = j--;
            }

            // j 为 -1, 则表示公共后缀子串也是模式串的前缀子串
            if (-1 == j) {
                prefix[k] = true;
            }
        }
    }

    /**
     * 好后缀移动的长度
     *
     * @param badIndex 坏字符下标
     * @param length   模式串长度
     * @param prefix   前缀数组
     * @param suffix   后缀数组
     * @return {@link int}
     * @author Fan
     * @since 2023/7/13 9:10
     */
    private static int moveByGoodSuf(int badIndex, int length, boolean[] prefix, int[] suffix) {
        // 好后缀长度
        int goodLength = length - 1 - badIndex;
        // 好后缀存在匹配的, 则移动到匹配位置
        if (suffix[goodLength] != -1) {
            return badIndex - suffix[goodLength] + 1;
        }
        // 不存在匹配, 但是存在前缀匹配, 则移动到前缀匹配位置, 坏字符下标为 badIndex,
        // 好后缀起始下标则为 badIndex + 1, 上面好后缀并未存在匹配, 因此好后缀的前缀子串直接从 badIndex + 2 开始
        for (int i = badIndex + 2; i <= length - 1; i++) {
            if (prefix[length - i]) {
                return i;
            }
        }
        // 直接移动到模式串的后面
        return length;
    }
}
