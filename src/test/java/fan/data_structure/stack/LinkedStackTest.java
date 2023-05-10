package fan.data_structure.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 链式栈测试类
 *
 * @author Fan
 * @since 2023/2/28 9:58
 */
class LinkedStackTest {

    LinkedStack<Integer> stack = new LinkedStack<>();

    @Test
    void testPush() {
        Assertions.assertTrue(stack.push(1));
        Assertions.assertTrue(stack.push(2));
        Assertions.assertTrue(stack.push(3));

        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals(3, stack.size());
    }

    @Test
    void testPeek() {
        testPush();

        Assertions.assertEquals(3, stack.peek());
    }

    @Test
    void testPop() {
        testPeek();

        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertTrue(stack.isEmpty());
    }
}
