package fan.data_structure.linkedlist;

/**
 * 双向链表测试类
 *
 * @author Fan
 * @since 2023/6/1 14:33
 */
public class DoubleLinkedNodeTest {
    public static void main(String[] args) {
        DoubleLinkedNode<Object> doubleLinkNode = new DoubleLinkedNode<>();
        // 张三
        doubleLinkNode.addFirst("张三");
        // 1 张三
        doubleLinkNode.addFirst(1);

        for (Object o : doubleLinkNode) {
            System.out.print(o + " ");
        }
        System.out.println();

        // 1 张三 李四
        doubleLinkNode.addLast("李四");
        for (Object o : doubleLinkNode) {
            System.out.print(o + " ");
        }
        System.out.println();

        // 1 张三 王五 李四
        doubleLinkNode.add("王五", 2);
        for (Object o : doubleLinkNode) {
            System.out.print(o + " ");
        }
        System.out.println();

        System.out.println("长度: " + doubleLinkNode.getLength());

        doubleLinkNode.reverse();
        for (Object o : doubleLinkNode) {
            System.out.print(o + " ");
        }
    }
}
