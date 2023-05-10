package fan.data_structure.tree;//package fan.tree;
//
//import java.util.List;
//
//public class HuffmanTree {
//    // 构建Huffman树
//    public <T> HuffmanTreeNode<T> buildHuffmanTree(List<HuffmanTreeNode> huffmanTreeNodes) {
//        while (huffmanTreeNodes.size() > 1){
//            sortTreeNode(huffmanTreeNodes);
//
//            // 左边比右边小
//            HuffmanTreeNode left = huffmanTreeNodes.get(0);
//            HuffmanTreeNode right = huffmanTreeNodes.get(1);
//
//            // 生成一个新的节点,父结点权重为两个子结点之和
//            HuffmanTreeNode parent = new HuffmanTreeNode(null, left.weight + right.weight);
//
//            // 让子结点与父结点连接
//            parent.leftChild = left;
//            parent.rightChild = right;
//
//            // 删除最小的
//            huffmanTreeNodes.remove(0);
//            // 删除第二小的
//            huffmanTreeNodes.remove(0);
//            // 把新的父结点加入到 list 中
//            huffmanTreeNodes.add(parent);
//        }
//        // 返回哈夫曼树的根节点
//        return huffmanTreeNodes.get(0);
//    }
//
//    // 对节点进行排序, 从小到大
//    public void sortTreeNode(List<HuffmanTreeNode> huffmanTreeNodes) {
//        for (int i = 0; i < huffmanTreeNodes.size(); i++){
//            for (int j = 0; j < huffmanTreeNodes.size() - 1 - i; j++){
//                if (huffmanTreeNodes.get(j).weight > huffmanTreeNodes.get(j + 1).weight) {
//                    HuffmanTreeNode temp = huffmanTreeNodes.get(j + 1);
//                    huffmanTreeNodes.set(j+1,huffmanTreeNodes.get(j));
//                    huffmanTreeNodes.set(j,temp);
//                }
//            }
//        }
//    }
//
//    // 打印哈夫曼树
//    public void printTree(HuffmanTreeNode root) {
//        System.out.println("Node-" + root.toString());
//        if(root.leftChild != null){
//            System.out.print("left:");
//            printTree(root.leftChild);
//        }
//        if(root.rightChild !=null){
//            System.out.print("right:");
//            printTree(root.rightChild);
//        }
//    }
//}
