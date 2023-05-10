package fan.data_structure.tree;//package fan.tree;
//
//public class AVLTree {
//    public AVLTreeNode root;
//    private int size;
//
//    public AVLTree() {
//        this.root = null;
//        this.size = 0;
//    }
//
//    public int getSize(){
//        return size;
//    }
//
//    // 获取节点的高度, 让空节点高度为-1
//    public int getHeight(AVLTreeNode avlTreeNode) {
//        return avlTreeNode == null ? -1 : avlTreeNode.height;
//    }
//
//    // 先序遍历
//    public void printTree(AVLTreeNode root) {
//        System.out.print(root.data + " ");
//        if(root.leftChild != null){
//            printTree(root.leftChild);
//        }
//        if(root.rightChild != null){
//            printTree(root.rightChild);
//        }
//    }
//
//    // 插入节点
//    public AVLTreeNode insert(AVLTreeNode avlTreeNode, int val) {
//        if (avlTreeNode == null){
//            avlTreeNode = new AVLTreeNode(val);
//            size ++;
//            return avlTreeNode;
//        }
//
//        if (val < avlTreeNode.data) {
//            // 如果插入的值小于当前节点的值, 则插入到左子树中
//            avlTreeNode.leftChild = insert(avlTreeNode.leftChild, val);
//            // 如果左子树与右子树的高度差大于1, 则需要进行平衡调整, 这里使用左子树的高度减右子树的高度, 节点为空高度为-1
//            if (getHeight(avlTreeNode.leftChild) - getHeight(avlTreeNode.rightChild) > 1) {
//                // 如果插入的值小于当前节点(即失衡节点)的左子节点的值, 即插入的节点在失衡节点的左子节点的左边, 则进行LL型旋转
//                if (val < avlTreeNode.leftChild.data) {
//                    System.out.println("LL型旋转");
//                    avlTreeNode = LLRotate(avlTreeNode);
//                } else { // 如果插入的值不小于当前节点(即失衡节点)的左子节点的值, 即插入的节点在失衡节点的左子节点的右边, 则进行LR型旋转
//                    System.out.println("LR型旋转");
//                    avlTreeNode = LRRotate(avlTreeNode);
//                }
//            }
//        } else { // 如果插入的值不小于当前节点的值, 则插入到右子树中
//            avlTreeNode.rightChild = insert(avlTreeNode.rightChild, val);
//            // 平衡调整
//            if (getHeight(avlTreeNode.rightChild) - getHeight(avlTreeNode.leftChild) > 1){
//                // 如果插入的值小于等于当前节点(即失衡节点)的右子节点的值, 即插入的节点在失衡节点的右子节点的左边, 则进行RL型旋转
//                if (val < avlTreeNode.rightChild.data){
//                    System.out.println("RL型旋转" + avlTreeNode.data);
//                    avlTreeNode = RLRotate(avlTreeNode);
//                } else { // 如果插入的值大于当前节点(即失衡节点)的右子节点的值, 即插入的节点在失衡节点的右子节点的右边, 则进行RR型旋转
//                    System.out.println("RR型旋转");
//                    avlTreeNode = RRRotate(avlTreeNode);
//                }
//            }
//        }
//        // 更新节点的高度, 获取左子树与右子树的最大高度, 叶子节点高度为0, 再加1即为当前节点的高度
//        avlTreeNode.height = Math.max(getHeight(avlTreeNode.leftChild), getHeight(avlTreeNode.rightChild)) + 1;
//        return avlTreeNode;
//    }
//
//    /**
//     * LL旋转,30为失衡点(左右子树高度差大于1), 对失衡点的左子树, 即对结点20进行左旋
//     *           30                        20
//     *          /  \                      /  \
//     *        20  40                   10   30
//     *       /  \      --LL旋转--       /   /  \
//     *     10   25                   5   25   40
//     *    /
//     *   56
//     */
//    private AVLTreeNode LLRotate(AVLTreeNode avlTreeNode) {
//        // 将失衡点(30)的左子节点 20 作为新节点
//        AVLTreeNode newRoot = avlTreeNode.leftChild;
//        // 将新节点(20)的右子节点 25 作为失衡点(30)的左子节点
//        avlTreeNode.leftChild = newRoot.rightChild;
//        // 失衡点(30)作为新节点(20)的右子节点
//        newRoot.rightChild = avlTreeNode;
//        // 更新失衡点和新根节点的高度
//        avlTreeNode.height = Math.max(getHeight(avlTreeNode.leftChild), getHeight(avlTreeNode.rightChild)) + 1;
//        newRoot.height = Math.max(getHeight(newRoot.leftChild), getHeight(newRoot.rightChild)) + 1;
//        // 新节点取代原失衡点
//        return newRoot;
//    }
//
//    /**
//     * RR旋转,20为失衡点(左右子树高度差大于1), 对失衡点的右子树, 即对结点30进行左旋
//     *      20                          30
//     *     /  \                        /  \
//     *    10  30                     20   40
//     *       /  \     --RR旋转--     /  \   \
//     *      25  40                 10  25  50
//     *           \
//     *           50
//     */
//    private AVLTreeNode RRRotate(AVLTreeNode avlTreeNode) {
//        // 将失衡点(20)的右子节点 30 作为新节点
//        AVLTreeNode newRoot = avlTreeNode.rightChild;
//        // 将新节点(30)的左子节点 25 作为失衡点(20)的右子节点
//        avlTreeNode.rightChild = newRoot.leftChild;
//        // 失衡点(20)作为新节点(30)的左子节点
//        newRoot.leftChild = avlTreeNode;
//        // 更新失衡点和新根节点的高度
//        avlTreeNode.height = Math.max(getHeight(avlTreeNode.leftChild), getHeight(avlTreeNode.rightChild)) + 1;
//        newRoot.height = Math.max(getHeight(newRoot.leftChild), getHeight(newRoot.rightChild)) + 1;
//        // 新节点取代原失衡点
//        return newRoot;
//    }
//
//    /**
//     * LR旋转, 先将失衡点(30)的左子树(20)进行 RR 旋转, 再将失衡点(30)进行 LL 旋转
//     *           30                         30                    25
//     *          /  \                       /  \                  /  \
//     *        20  40                     25   40                20  30
//     *       /  \      --先RR旋转--      /  \      --再LL旋转--   /   / \
//     *     10   25                    20   28                 10  28  40
//     *            \                  /
//     *             28               10
//     */
//    private AVLTreeNode LRRotate(AVLTreeNode avlTreeNode) {
//        // 将失衡点(30)的左子节点(20)进行 RR 旋转
//        avlTreeNode.leftChild = RRRotate(avlTreeNode.leftChild);
//        // 将失衡点(30)进行 LL 旋转, 并返回新节点代替原失衡点
//        return LLRotate(avlTreeNode);
//    }
//
//    /**
//     * RL旋转, 先将失衡点(20)的右子树(30)进行 LL 旋转, 再将失衡点(20)进行 RR 旋转
//     *      20                          20                       25
//     *     /  \                        /  \                     /  \
//     *    10  30                     10   25                   20  30
//     *       /  \     --LL旋转--             \    --RR旋转--    /   / \
//     *      25  40                          30               10  28  40
//     *       \                             /  \
//     *       28                           28  40
//     */
//    private AVLTreeNode RLRotate(AVLTreeNode avlTreeNode) {
//        // 将失衡点(20)的右子节点(30)进行 LL 旋转
//        avlTreeNode.rightChild = LLRotate(avlTreeNode.rightChild);
//        // 将失衡点(20)进行 RR 旋转, 并返回新节点代替原失衡点
//        return RRRotate(avlTreeNode);
//    }
//}
