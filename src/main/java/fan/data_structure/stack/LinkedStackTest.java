package fan.data_structure.stack;

/**
 * 链式栈测试类
 *
 * @author Fan
 * @since 2023/6/1 14:47
 */
public class LinkedStackTest {
    public static void main(String[] args) {
        LinkedStack<Object> linkStack = new LinkedStack<>();
        linkStack.push("张三");
        linkStack.push(1);
        linkStack.push(2);

        System.out.println(linkStack.pop());
        System.out.println("栈顶元素: " + linkStack.peek());
        System.out.println("链表长度: " + linkStack.getLength());

        linkStack.queryAll();
    }
}
