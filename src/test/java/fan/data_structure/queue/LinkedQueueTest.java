package fan.data_structure.queue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 链式队列测试类
 *
 * @author Fan
 * @since 2023/2/28 11:16
 */
class LinkedQueueTest {

    LinkedQueue<Integer> queue = new LinkedQueue<>();

    @Test
    void testEnqueue() {
        Assertions.assertTrue(queue.enqueue(1));
        Assertions.assertTrue(queue.enqueue(2));
        Assertions.assertTrue(queue.enqueue(3));

        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    void testPeek() {
        testEnqueue();

        Assertions.assertEquals(1, queue.peek());
    }

    @Test
    void testDequeue() {
        testPeek();

        Assertions.assertEquals(1, queue.dequeue());
        Assertions.assertEquals(2, queue.dequeue());
        Assertions.assertEquals(3, queue.dequeue());
        Assertions.assertTrue(queue.isEmpty());
    }
}
