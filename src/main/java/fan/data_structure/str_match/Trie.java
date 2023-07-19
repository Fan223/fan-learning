package fan.data_structure.str_match;

import java.util.LinkedList;

/**
 * Trie 树
 *
 * @author Fan
 * @since 2023/7/19 9:03
 */
public class Trie {

    /**
     * 存储无意义字符
     */
    private final TrieNode root = new TrieNode('/');

    /**
     * 层次遍历
     *
     * @author Fan
     * @since 2023/7/19 9:19
     */
    public void levelOrder() {
        LinkedList<TrieNode> linkedList = new LinkedList<>();
        // 添加到队列尾部
        linkedList.offer(root);
        TrieNode current;

        while (!linkedList.isEmpty()) {
            // 获取首位元素, 并从队列删除
            current = linkedList.poll();
            // 输出当前元素
            System.out.print(current.data + " ");

            // 并将当前元素的子节点添加到队列中, 即为下一层遍历的节点
            for (TrieNode child : current.children) {
                if (null != child) {
                    linkedList.offer(child);
                }
            }
        }
        System.out.println();
    }

    public void insert(String str) {
        insert(str.toCharArray());
    }

    /**
     * 往 Trie 树中插入一个字符串
     *
     * @param text 字符串
     * @author Fan
     * @since 2023/7/19 9:05
     */
    public void insert(char[] text) {
        TrieNode current = root;

        for (char ch : text) {
            int index = ch - 'a';
            if (null == current.children[index]) {
                TrieNode newNode = new TrieNode(ch);
                current.children[index] = newNode;
            }
            current = current.children[index];
        }
        current.isEndingChar = true;
    }

    public boolean find(String str) {
        return find(str.toCharArray());
    }

    /**
     * 在 Trie 树中查找一个字符串
     *
     * @param pattern 模式串
     * @return {@link boolean}
     * @author Fan
     * @since 2023/7/19 9:10
     */
    public boolean find(char[] pattern) {
        TrieNode current = root;

        for (char ch : pattern) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                // 不存在 pattern
                return false;
            }
            current = current.children[index];
        }

        // 最后一个字符是否是结尾符
        return current.isEndingChar;
    }
}
