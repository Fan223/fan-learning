package fan.data_structure.tree;//package fan.tree;
//
//public class ArrayTree {
//    private int[] storage;
//
//    public ArrayTree(){
//        storage = new int[8];
//    }
//
//    public ArrayTree(int length){
//        storage = new int[length];
//    }
//
//    // 添加根节点
//    public void addRoot(int val){
//        storage[1] = val;
//    }
//
//    // 添加左子节点
//    public void addLeft(int index, int val){
//        if(index > storage.length || index < 0){
//            throw new IndexOutOfBoundsException();
//        }
//
//        storage[2 * index] = val;
//    }
//
//    // 添加右子节点
//    public void addRight(int index, int val){
//        storage[ 2 * index + 1] = val;
//    }
//
//    // 获取一个节点的父节点
//    public int getParent(int index){
//        if(index > storage.length || index < 0){
//            throw new IndexOutOfBoundsException();
//        }
//        return storage[index / 2];
//    }
//
//    // 遍历
//    public void query(){
//        for(int i = 1; i < storage.length; i++){
//            System.out.print(storage[i] + " ");
//        }
//        System.out.println();
//    }
//}
