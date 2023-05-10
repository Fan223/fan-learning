package fan.data_structure.tree;//package fan.tree;
//
//import java.util.LinkedList;
//
//public class BinarySortedTree {
//
//    public TreeNode root;
//
//    // 非递归构建二叉排序树
//    public void insert(int val) {
//        TreeNode addTreeNode = new TreeNode(val);
//
//        if (root == null) {
//            root = addTreeNode;
//        } else {
//            TreeNode currentTreeNode = root;
//            TreeNode parentTreeNode;
//
//            while (true) {
//                parentTreeNode = currentTreeNode;
//
//                if (addTreeNode.data > currentTreeNode.data) {
//                    currentTreeNode = currentTreeNode.rightChild;
//
//                    if (currentTreeNode == null) {
//                        parentTreeNode.rightChild = addTreeNode;
//                        return;
//                    }
//                } else {
//                    currentTreeNode = currentTreeNode.leftChild;
//
//                    if (currentTreeNode == null) {
//                        parentTreeNode.leftChild = addTreeNode;
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    // 递归构建二叉排序树
//    public TreeNode insert(TreeNode treeNode, int val) {
//        TreeNode addTreeNode = new TreeNode(val);
//
//        if (root == null) {
//            return root = addTreeNode;
//        }
//
//        if (val > treeNode.data) {
//            if (treeNode.rightChild == null) {
//                treeNode.rightChild = addTreeNode;
//                return root;
//            }
//            return insert(treeNode.rightChild, val);
//        } else {
//            if (treeNode.leftChild == null) {
//                treeNode.leftChild = addTreeNode;
//                return root;
//            }
//            return insert(treeNode.leftChild, val);
//        }
//    }
//
//    // 先序遍历二叉排序树
//    public void preOrder(TreeNode treeNode) {
//        if (treeNode == null) {
//            return;
//        }
//
//        System.out.print(treeNode.data + " ");
//        preOrder(treeNode.leftChild);
//        preOrder(treeNode.rightChild);
//    }
//
//    // 中序遍历二叉排序树
//    public void inOrder(TreeNode treeNode) {
//        if (treeNode == null) {
//            return;
//        }
//
//        inOrder(treeNode.leftChild);
//        System.out.print(treeNode.data + " ");
//        inOrder(treeNode.rightChild);
//    }
//
//    // 后序遍历二叉排序树
//    public void postOrder(TreeNode treeNode) {
//        if (treeNode == null) {
//            return;
//        }
//
//        postOrder(treeNode.leftChild);
//        postOrder(treeNode.rightChild);
//        System.out.print(treeNode.data + " ");
//    }
//
//    // 层序遍历二叉排序树
//    public void levelOrder(TreeNode treeNode) {
//        if (treeNode == null) {
//            return;
//        }
//        LinkedList<TreeNode> linkedList = new LinkedList<>();
//        linkedList.offer(treeNode);
//        TreeNode currentTreeNode;
//
//        while (!linkedList.isEmpty()) {
//            currentTreeNode = linkedList.poll();
//            System.out.print(currentTreeNode.data + " ");
//
//            if (currentTreeNode.leftChild != null) {
//                linkedList.offer(currentTreeNode.leftChild);
//            }
//            if (currentTreeNode.rightChild != null) {
//                linkedList.offer(currentTreeNode.rightChild);
//            }
//        }
//        System.out.println();
//    }
//
//    // 查找元素是否在二叉排序树中, 如果在返回该节点, 否则返回null
//    public TreeNode find(int val) {
//        TreeNode currentTreeNode = root;
//
//        while (currentTreeNode != null) {
//            if (currentTreeNode.data == val) {
//                return currentTreeNode;
//            } else if (currentTreeNode.data > val) {
//                currentTreeNode = currentTreeNode.leftChild;
//            } else {
//                currentTreeNode = currentTreeNode.rightChild;
//            }
//        }
//        return null;
//    }
//
//    // 查找二叉排序树中最小值
//    public int findMin() {
//        TreeNode deleteTreeNode = root;
//
//        while (deleteTreeNode.leftChild != null) {
//            deleteTreeNode = deleteTreeNode.leftChild;
//        }
//        return deleteTreeNode.data;
//    }
//
//    // 删除节点
//    public void delete(int val) {
//        TreeNode deleteTreeNode = root;
//        TreeNode parentTreeNode = root;
//
//        // 找到要删除的节点
//        while (deleteTreeNode.data != val) {
//            parentTreeNode = deleteTreeNode;
//
//            if (deleteTreeNode.data > val) {
//                deleteTreeNode = deleteTreeNode.leftChild;
//            } else {
//                deleteTreeNode = deleteTreeNode.rightChild;
//            }
//            if (deleteTreeNode == null) {
//                break;
//            }
//        }
//
//        if (deleteTreeNode != null) {
//            if (deleteTreeNode.leftChild == null && deleteTreeNode.rightChild == null) { // 叶子节点
//                if (deleteTreeNode == root) {
//                    root = null;
//                } else if (parentTreeNode.leftChild == deleteTreeNode) { // 左子节点
//                    parentTreeNode.leftChild = null;
//                } else { // 右子节点
//                    parentTreeNode.rightChild = null;
//                }
//            } else if (deleteTreeNode.leftChild == null) { // 只有一个右子节点
//                if (deleteTreeNode == root) {
//                    root = deleteTreeNode.rightChild;
//                } else if (parentTreeNode.leftChild == deleteTreeNode) { // 左子节点
//                    parentTreeNode.leftChild = deleteTreeNode.rightChild;
//                } else { // 右子节点
//                    parentTreeNode.rightChild = deleteTreeNode.rightChild;
//                }
//            } else if (deleteTreeNode.rightChild == null) { // 只有一个左子节点
//                if (deleteTreeNode == root) {
//                    root = deleteTreeNode.leftChild;
//                } else if (parentTreeNode.leftChild == deleteTreeNode) { // 左子节点
//                    parentTreeNode.leftChild = deleteTreeNode.leftChild;
//                } else { // 右子节点
//                    parentTreeNode.rightChild = deleteTreeNode.leftChild;
//                }
//            } else { // 有两个子节点
//                // 先定义一个 替换节点 保存要删除节点的右子树的最小值
//                TreeNode replaceTreeNode = deleteTreeNode.rightChild;
//                // 定义一个 替换节点的父节点 保存要删除节点的右子树的最小值的父节点
//                TreeNode replaceParentTreeNode = deleteTreeNode;
//
//                // 找到删除节点的右节点的最左子节点, 即右子树的最小值
//                while (replaceTreeNode.leftChild != null) {
//                    // 将其赋值给 替换节点的父节点
//                    replaceParentTreeNode = replaceTreeNode;
//                    // 将其 左节点 赋值给替换节点
//                    replaceTreeNode = replaceTreeNode.leftChild;
//                }
//                // 将替换节点的值赋值给要删除节点
//                deleteTreeNode.data = replaceTreeNode.data;
//                // 如果替换节点的父节点的左节点是替换节点, 则将替换节点的右节点赋值给替换节点的父节点的左节点
//                // 即有最左子节点的情况
//                if (replaceParentTreeNode.leftChild == replaceTreeNode) {
//                    replaceParentTreeNode.leftChild = replaceTreeNode.rightChild;
//                } else {
//                    // 如果替换节点的父节点的右节点是替换节点, 则将替换节点的右节点赋值给替换节点的父节点的右节点
//                    // 即没有左子节点的情况, 删除节点的右子节点就是替换节点, 同时其并没有左子节点, 所以使用替换节点的右节点
//                    replaceParentTreeNode.rightChild = replaceTreeNode.rightChild;
//                }
//            }
//        } else {
//            System.out.println("要删除的节点不存在！");
//        }
//    }
//}
