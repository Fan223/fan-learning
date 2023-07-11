package fan.data_structure.str_match;

import fan.core.util.MapUtil;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * 暴力匹配升级版
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
        String pattern = "badge";
        System.out.println(rabinKarp(main, pattern));
    }

    public static int rabinKarp(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int mainHash = 0;
        int patternHash = 0;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += PRIME_MAP.get(pattern.charAt(i));
            Integer hash = PRIME_MAP.get(main.charAt(i));
            deque.push(hash);
            mainHash += hash;
        }

        if (patternHash == mainHash) {
            return 0;
        }

        for (int i = 1; i < main.length() - pattern.length() + 1; i++) {
            int currentHash = PRIME_MAP.get(main.charAt(pattern.length() + i - 1));
            int newHash = mainHash - deque.getLast() + currentHash;

            if (patternHash == newHash) {
                int temp = 0;
                while (temp < pattern.length()) {
                    // 每次主串后移一位与模式串比较
                    if (main.charAt(i + temp) == pattern.charAt(temp)) {
                        temp++;
                    } else {
                        break;
                    }
                }

                if (temp == pattern.length()) {
                    return i;
                }
            } else {
                deque.removeLast();
                deque.push(currentHash);
                mainHash = newHash;
            }
        }

        return -1;
    }
}
