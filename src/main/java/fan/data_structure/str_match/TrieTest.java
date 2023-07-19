package fan.data_structure.str_match;

/**
 * Trie 树测试类
 *
 * @author Fan
 * @since 2023/7/19 9:11
 */
public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("her");
        trie.insert("he");
        trie.insert("hero");
        trie.insert("hear");

        trie.levelOrder();
        System.out.println(trie.find("he"));
        System.out.println(trie.find("hee"));
    }
}
