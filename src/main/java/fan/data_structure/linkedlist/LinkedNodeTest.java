package fan.data_structure.linkedlist;

/**
 * 单链表测试类
 *
 * @author Fan
 * @since 2023/6/1 14:21
 */
public class LinkedNodeTest {
    public static void main(String[] args) {
        LinkedNode linkNode = new LinkedNode<>();
//        LinkNode<String> linkNode = new LinkNode<>(); // 限制泛型

        linkNode.addLast("张三11");
        linkNode.addLast(22);
        linkNode.addLast("王五33");

        for (Object o : linkNode) {
            System.out.print(o + " ");
        }
        System.out.println();

        linkNode.add("赵六", 1);
        for (Object o : linkNode) {
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.println("删除的元素: " + linkNode.remove(2));
        for (Object o : linkNode) {
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.println(linkNode.indexOf("赵六"));

        linkNode.reverse();
        for (Object o : linkNode) {
            System.out.print(o + " ");
        }
    }
}
