package fan.data_structure.linkedlist;

/**
 * 链表测试类
 *
 * @author Fan
 * @since 2023/6/1 13:46
 */
public class NodeTest {
    public static void main(String[] args) {
        Node head = new Node();

        Node node1 = new Node<>("王五");
        Node node2 = new Node<>(1);

        Node node3 = new Node<>("李四");
        Node node4 = new Node<>(2);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Node addNode = new Node("赵六");
        add(head, addNode);

        Node addIndexNode = new Node("11");
        add(head, addIndexNode, 2);

        delete(head, 4);

        queryAll(head);
    }

    public static void queryAll(Node node) {
        Node currentNode = node;
        while (null != currentNode.next) {
            currentNode = currentNode.next;
            System.out.println(currentNode.value);
        }
    }

    public static void add(Node head, Node addNode) {
        Node currentNode = head;

        // 找到链表的最后一个节点
        while (null != currentNode.next) {
            currentNode = currentNode.next;
        }
        // 让该节点的后一个节点为添加的节点, 即将该节点添加到链表
        currentNode.next = addNode;
    }

    public static void add(Node head, Node addNode, int index) {
        Node currentNode = head;

        // 找到指定位置的上一个节点
        for (int i = 0; i < index - 1; i++) {
            currentNode = null == currentNode.next ? null : currentNode.next;
            if (currentNode == null) {
                System.out.println("位置不存在");
            }
        }
        // 让添加节点的下一个节点为该节点的下一个节点
        addNode.next = currentNode.next;
        // 再让该节点的下一个节点为添加节点, 即在指定位置插入一个节点
        currentNode.next = addNode;
    }

    public static void delete(Node head, int index) {
        Node currentNode = head;

        // 找到指定位置的上一个节点
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next != null ? currentNode.next : null;
            if (currentNode == null) {
                System.out.println("位置不存在");
            }
        }
        // 该节点的下一个节点就是要删除的节点
        Node delNode = currentNode.next;
        // 让该节点的下一个节点为删除节点的下一个节点，指定位置的节点就被删除了
        currentNode.next = delNode.next;
    }
}
