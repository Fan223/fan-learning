package fan.data_structure.str_match;

import fan.core.util.MapUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

/**
 * RK算法, 暴力匹配升级版
 *
 * @author Fan
 * @since 2023/7/11 15:48
 */
public class RabinKarp {

    private static final Map<Character, Integer> PRIME_MAP = MapUtil.builder('a', 2).put('b', 3).put('c', 5).put('d', 7)
            .put('e', 11).put('f', 13).put('g', 17).put('h', 19).put('i', 23).put('j', 29).put('k', 31).put('l', 37)
            .put('m', 41).put('n', 43).put('o', 47).put('p', 53).put('q', 59).put('r', 61).put('s', 67).put('t', 71)
            .put('u', 73).put('v', 79).put('w', 83).put('x', 89).put('y', 97).put('z', 101).build();

    public static void main(String[] args) {
        String main = "baddef";
        String pattern = "baded";
        System.out.println(rabinKarp(main, pattern));
    }

    /**
     * 暴力匹配优化, 利用哈希算法来提高匹配效率
     *
     * @param main    主串
     * @param pattern 模式串
     * @return {@link int}
     * @author Fan
     * @since 2023/7/12 9:16
     */
    public static int rabinKarp(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }

        Deque<Character> deque = new ArrayDeque<>();
        int mainHash = 0;
        int patternHash = 0;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += PRIME_MAP.get(pattern.charAt(i));
            char ch = main.charAt(i);
            deque.push(ch);
            mainHash += PRIME_MAP.get(ch);
        }

        // 哈希值相等再判断字符串是否匹配, 解决哈希冲突问题
        if (patternHash == mainHash && match(deque, pattern)) {
            return 0;
        }

        for (int i = 1; i <= main.length() - pattern.length(); i++) {
            char current = main.charAt(pattern.length() + i - 1);
            mainHash = mainHash - PRIME_MAP.get(deque.getLast()) + PRIME_MAP.get(current);
            deque.removeLast();
            deque.push(current);

            if (patternHash == mainHash && match(deque, pattern)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean match(Deque<Character> deque, String pattern) {
        Iterator<Character> iterator = deque.iterator();
        int index = pattern.length();

        while (iterator.hasNext()) {
            if (pattern.charAt(--index) != iterator.next()) {
                break;
            }
        }

        return index == 0;
    }
}
