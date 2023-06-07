package fan.data_structure.heap;

/**
 * 大顶堆测试类
 *
 * @author Fan
 * @since 2023/6/6 11:36
 */
public class HeapTest {
    public static void main(String[] args) {
        MaxTopHeap heap = new MaxTopHeap();
        System.out.println(heap.size());

        heap.insert(33);
        heap.insert(27);
        heap.insert(21);
        heap.insert(16);
        heap.insert(13);
        heap.insert(15);
        heap.insert(19);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);
        heap.insert(1);
        heap.insert(2);
        heap.queryHeap();

        heap.insert(12);
        heap.queryHeap();

        heap.removeMax();
        heap.queryHeap();
    }
}
