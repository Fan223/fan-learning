package fan.data_structure.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 顺序栈测试类
 *
 * @author Fan
 * @since 2023/2/28 9:28
 */
class ArrayStackTest {
    ArrayStack<Integer> stack = new ArrayStack<>();

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
        Assertions.assertEquals(2, stack.peek());
    }
}
