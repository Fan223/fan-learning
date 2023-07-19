package fan.data_structure.str_match;

import lombok.ToString;

/**
 * AC 自动机 Trie 树结点
 *
 * @author Fan
 * @since 2023/7/19 9:38
 */
public class AcTrieNode {

    public char data;

    public AcTrieNode[] children = new AcTrieNode[26];

    /**
     * 字符是否为结尾符
     */
    public boolean isEndingChar = false;

    /**
     * 当 isEndingChar=true 时, 记录模式串长度
     */
    public int length = -1;

    /**
     * 失败指针
     */
    public AcTrieNode fail;

    public AcTrieNode(char data) {
        this.data = data;
    }
}
