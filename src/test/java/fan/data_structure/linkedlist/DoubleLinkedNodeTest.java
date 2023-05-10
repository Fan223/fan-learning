package fan.data_structure.linkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 双向链表测试类
 *
 * @author Fan
 * @since 2023/2/22 17:22
 */
class DoubleLinkedNodeTest {

    DoubleLinkedNode doubleLinkedNode = new DoubleLinkedNode<>();

    @Test
    void testAdd() {
        Assertions.assertTrue(doubleLinkedNode.addLast(1));
        Assertions.assertTrue(doubleLinkedNode.addLast(2));
        Assertions.assertTrue(doubleLinkedNode.addLast(3));

        Assertions.assertTrue(doubleLinkedNode.addFirst("头插节点A"));
        Assertions.assertTrue(doubleLinkedNode.addFirst("头插节点B"));

        Assertions.assertTrue(doubleLinkedNode.add("添加节点位置0", 0));
        Assertions.assertTrue(doubleLinkedNode.add("添加节点位置6", 6));

        queryAll();
    }

    public void queryAll() {
        for (Object object : doubleLinkedNode) {
            System.out.print(object + " ");
        }
        System.out.println();
    }

    @Test
    void testGet() {
        testAdd();

        Assertions.assertEquals("添加节点位置0", doubleLinkedNode.get(0));
        Assertions.assertEquals("添加节点位置6", doubleLinkedNode.get(6));
    }

    @Test
    void testRemove() {
        testGet();

        Assertions.assertEquals("添加节点位置0", doubleLinkedNode.remove(0));
        queryAll();
    }

    @Test
    void testIndexOf() {
        testRemove();

        Assertions.assertEquals(0, doubleLinkedNode.indexOf("头插节点B"));
        Assertions.assertEquals(5, doubleLinkedNode.indexOf("添加节点位置6"));
    }

    @Test
    void testClear() {
        testIndexOf();

        Assertions.assertTrue(doubleLinkedNode.clear());
        queryAll();
        Assertions.assertEquals(0, doubleLinkedNode.getLength());
    }

    @Test
    void testReverse() {
        testIndexOf();

        Assertions.assertTrue(doubleLinkedNode.reverse());
        queryAll();

        Assertions.assertTrue(doubleLinkedNode.clear());
        Assertions.assertTrue(doubleLinkedNode.reverse());
    }
}
