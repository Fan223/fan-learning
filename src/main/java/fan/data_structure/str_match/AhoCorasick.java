package fan.data_structure.str_match;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC 自动机算法
 *
 * @author Fan
 * @since 2023/7/19 9:37
 */
public class AhoCorasick {

    private final AcTrieNode root = new AcTrieNode('/');

    public void insert(String str) {
        insert(str.toCharArray());
    }

    public void insert(char[] text) {
        AcTrieNode current = root;

        for (char ch : text) {
            int index = ch - 'a';
            if (null == current.children[index]) {
                AcTrieNode newNode = new AcTrieNode(ch);
                current.children[index] = newNode;
            }
            current = current.children[index];
        }
        current.isEndingChar = true;
        current.length = text.length;
    }

    /**
     * 构建失败指针, 类层次遍历的方式
     *
     * @author Fan
     * @since 2023/7/19 9:47
     */
    public void buildFailPoint() {
        Queue<AcTrieNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);

        while (!queue.isEmpty()) {
            AcTrieNode current = queue.remove();

            for (AcTrieNode child : current.children) {
                if (null == child) {
                    continue;
                }

                if (current == root) {
                    child.fail = root;
                } else {
                    AcTrieNode q = current.fail;
                    while (q != null) {
                        AcTrieNode qc = q.children[child.data - 'a'];
                        if (qc != null) {
                            child.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        child.fail = root;
                    }
                }
                queue.add(child);
            }
        }
    }

    public void match(String str) { // text 是主串
        char[] text = str.toCharArray();

        int n = text.length;
        AcTrieNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if (p == null) {
                p = root; // 如果没有匹配的，从 root 开始重新匹配
            }
            AcTrieNode tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar) {
                    int pos = i - tmp.length + 1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.fail;
            }
        }
    }
}
