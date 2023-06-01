package fan.data_structure.stack;

/**
 * 数组栈测试类
 *
 * @author Fan
 * @since 2023/6/1 14:42
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);
        arrayStack.push(1);
        arrayStack.push("张三");
        arrayStack.push(2);
        arrayStack.push("李四");

        // 李四
        System.out.println(arrayStack.peek());
        // false
        System.out.println(arrayStack.isEmpty());
        // 4
        System.out.println(arrayStack.size());

        for (int i = 0; i < 4; i++) {
            System.out.println(arrayStack.pop());
        }
    }
}
