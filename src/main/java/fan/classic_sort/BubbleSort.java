package fan.classic_sort;

/**
 * 冒泡排序
 *
 * @author Fan
 * @since 2023/3/1 9:57
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = {6, 4, 3, 23, 27, 23, 14, 10};
        bubbleSort(data);

        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    /**
     * <ul>
     *     <li> 每次比较相邻的两个数, 将大的数放到后面. 遍历一次后, 最后一位的数肯定是最大的 </li>
     *     <li> 然后第二次遍历, 已经得到的最后一位最大的数不参与遍历, 将第二大的数放到倒数第二位 </li>
     *     <li> 每次遍历都找到一个当前最大的数, 所有都遍历完后, 则排序完成 </li>
     * </ul>
     *
     * @param data 待排序数组
     * @author Fan
     * @since 2023/3/1 15:23
     */
    public static void bubbleSort(int[] data) {
        // 排序的遍历次数, 每一遍让当前最大的一个数 "沉" 到最后, 到最后一个数时已经排好序了, 即最后一遍不需要考虑, 次数为 length - 1
        for (int i = 1; i < data.length; i++) {
            // 定义一个布尔变量来判断当前数组是不是已经排序好的
            boolean flag = true;

            // 排序, 交换相邻的元素, 让大的元素在后面, 排完一遍后最后的数是最大的, 下次排序就不用遍历这个数, length - 遍历次数
            for (int j = 0; j < data.length - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;

                    // 到这里, 说明该数组不是排序好的, 置为 false
                    flag = false;
                }
            }

            // 假如数组是排序好的, 该遍循环直接跳出, 从而减少后续不必要的循环
            if (flag) {
                break;
            }
        }
    }
}
