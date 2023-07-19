package fan.data_structure.str_match;

/**
 * AC 自动机测试类
 *
 * @author Fan
 * @since 2023/7/19 9:51
 */
public class AhoCorasickTest {
    public static void main(String[] args) {
        AhoCorasick ac = new AhoCorasick();
        ac.insert("qnmd");
        ac.insert("ntmd");
        ac.insert("nmd");
        ac.insert("sb");
        ac.insert("sg");

        ac.buildFailPoint();
        ac.match("sbwqnmdgdxsgyg");
    }
}
