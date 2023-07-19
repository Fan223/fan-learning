package fan.data_structure.str_match;

/**
 * Trie 树结点
 *
 * @author Fan
 * @since 2023/7/19 8:59
 */
public class TrieNode {

    public char data;

    public TrieNode[] children = new TrieNode[26];

    public boolean isEndingChar = false;

    public TrieNode(char data) {
        this.data = data;
    }
}
