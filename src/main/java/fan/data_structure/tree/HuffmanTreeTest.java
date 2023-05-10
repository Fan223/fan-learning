package fan.data_structure.tree;//package fan.tree;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HuffmanTreeTest {
//    public static void main(String[] args) {
//        HuffmanTree huffmanTree = new HuffmanTree();
//
//        List<HuffmanTreeNode> huffmanTreeNodes = new ArrayList<HuffmanTreeNode>();
//        //把节点加入至list中
//        huffmanTreeNodes.add(new HuffmanTreeNode("张三", 10));
//        huffmanTreeNodes.add(new HuffmanTreeNode(1, 15));
//        huffmanTreeNodes.add(new HuffmanTreeNode("李四", 12));
//        huffmanTreeNodes.add(new HuffmanTreeNode(2, 3));
//        huffmanTreeNodes.add(new HuffmanTreeNode("王五", 4));
//        huffmanTreeNodes.add(new HuffmanTreeNode(3, 13));
//        huffmanTreeNodes.add(new HuffmanTreeNode("赵六", 1));
//
//        // 进行哈夫曼树的构造
//        HuffmanTreeNode root = huffmanTree.buildHuffmanTree(huffmanTreeNodes);
//        // 打印哈夫曼树
//        huffmanTree.printTree(root);
//    }
//}
