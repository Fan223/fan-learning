package fan.data_structure.linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 单链表测试类
 *
 * @author Fan
 * @since 2023/2/20 10:03
 */
class LinkedNodeTest {
    LinkedNode linkedNode = new LinkedNode<>();

    @Test
    void testAdd() {
        Assertions.assertTrue(linkedNode.addLast(1));
        Assertions.assertTrue(linkedNode.addLast(2));
        Assertions.assertTrue(linkedNode.addLast(3));
        queryAll();

        Assertions.assertTrue(linkedNode.addFirst("头插节点A"));
        Assertions.assertTrue(linkedNode.addFirst("头插节点B"));
        queryAll();

        Assertions.assertTrue(linkedNode.add("添加节点位置0", 0));
        Assertions.assertTrue(linkedNode.add("添加节点位置6", 6));
        queryAll();
    }

    private void queryAll() {
        for (Object object : linkedNode) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    @Test
    void testGet() {
        testAdd();

        Assertions.assertEquals("添加节点位置0", linkedNode.get(0));
        Assertions.assertEquals("添加节点位置6", linkedNode.get(6));
        Assertions.assertEquals(7, linkedNode.getLength());
    }

    @Test
    void testRemove() {
        testGet();

        Assertions.assertEquals("头插节点A", linkedNode.remove(2));
        Assertions.assertEquals("添加节点位置0", linkedNode.remove(0));
        queryAll();
    }

    @Test
    void testIndexOf() {
        testRemove();

        Assertions.assertEquals(4, linkedNode.indexOf("添加节点位置6"));
    }

    @Test
    void testClear() {
        testIndexOf();

        Assertions.assertTrue(linkedNode.clear());
        queryAll();
        Assertions.assertEquals(0, linkedNode.getLength());
    }

    @Test
    void testReverse() {
        testIndexOf();

        Assertions.assertTrue(linkedNode.reverse());
        queryAll();

        Assertions.assertTrue(linkedNode.clear());
        Assertions.assertTrue(linkedNode.reverse());
    }
}
