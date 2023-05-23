package fan.data_structure.tree;

import java.util.LinkedList;

/**
 * 二叉 查找/排序 树
 *
 * @author Fan
 * @since 2023/5/22 15:06
 */
public class BinarySortedTree {

    public TreeNode root;

    /**
     * 非递归构建二叉排序树
     *
     * @param val 节点值
     * @author Fan
     * @since 2023/5/22 15:38
     */
    public void insert(int val) {
        // 树为空, 即根节点为空, 则新节点应该作为根节点
        if (null == root) {
            root = new TreeNode(val);
            return;
        }

        // 当前节点, 用于循环
        TreeNode current = root;
        while (true) {
            // 如果要加入的值大于当前节点值
            if (val > current.data) {
                // 表示要加入的值在当前节点的右侧, 假如右子节点为空, 直接添加, 否则继续找下一位右子节点
                if (null == current.rightChild) {
                    current.rightChild = new TreeNode(val);
                    return;
                }
                current = current.rightChild;
            } else {
                // 如果要加入的值小于当前节点值, 则表示要加入的值在当前节点的左侧, 假如左子节点为空, 直接添加, 否则继续找下一位左子节点
                if (null == current.leftChild) {
                    current.leftChild = new TreeNode(val);
                    return;
                }
                current = current.leftChild;
            }
        }
    }

    /**
     * 递归构建二叉排序树
     *
     * @param treeNode 当前节点
     * @param val      节点值
     * @return {@link TreeNode}
     * @author Fan
     * @since 2023/5/22 15:54
     */
    public TreeNode insert(TreeNode treeNode, int val) {
        // 树为空, 即根节点为空, 则新节点应该作为根节点
        if (null == root) {
            return new TreeNode(val);
        }

        // 如果要加入的值大于当前节点值
        if (val > treeNode.data) {
            // 表示要加入的值在当前节点的右侧, 假如右子节点为空, 直接添加, 否则继续找下一位右子节点
            if (null == treeNode.rightChild) {
                treeNode.rightChild = new TreeNode(val);
                return root;
            }
            return insert(treeNode.rightChild, val);
        } else {
            // 如果要加入的值小于当前节点值, 则表示要加入的值在当前节点的左侧, 假如左子节点为空, 直接添加, 否则继续找下一位左子节点
            if (null == treeNode.leftChild) {
                treeNode.leftChild = new TreeNode(val);
                return root;
            }
            return insert(treeNode.leftChild, val);
        }
    }

    /**
     * 先序遍历二叉排序树
     *
     * @param treeNode 起始节点
     * @author Fan
     * @since 2023/5/22 15:49
     */
    public void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        // 根左右
        System.out.print(treeNode.data + " ");
        preOrder(treeNode.leftChild);
        preOrder(treeNode.rightChild);
    }

    /**
     * 中序遍历二叉排序树
     *
     * @param treeNode 起始节点
     * @author Fan
     * @since 2023/5/22 15:49
     */
    public void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        // 左根右
        inOrder(treeNode.leftChild);
        System.out.print(treeNode.data + " ");
        inOrder(treeNode.rightChild);
    }

    /**
     * 后序遍历二叉排序树
     *
     * @param treeNode 起始节点
     * @author Fan
     * @since 2023/5/22 15:50
     */
    public void postOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        // 左右根
        postOrder(treeNode.leftChild);
        postOrder(treeNode.rightChild);
        System.out.print(treeNode.data + " ");
    }

    /**
     * 层序遍历二叉排序树
     *
     * @param treeNode 起始节点
     * @author Fan
     * @since 2023/5/22 16:05
     */
    public void levelOrder(TreeNode treeNode) {
        // 为空, 直接返回
        if (null == treeNode) {
            return;
        }

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        // 添加到队列尾部
        linkedList.offer(treeNode);
        TreeNode current;

        while (!linkedList.isEmpty()) {
            // 获取首位元素, 并从队列删除
            current = linkedList.poll();
            // 输出当前元素
            System.out.print(current.data + " ");

            // 并将当前元素的左右子节点添加到队列中, 即为下一层遍历的节点
            if (current.leftChild != null) {
                linkedList.offer(current.leftChild);
            }
            if (current.rightChild != null) {
                linkedList.offer(current.rightChild);
            }
        }
        System.out.println();
    }

    /**
     * 查找元素是否在二叉排序树中, 如果在返回该节点, 否则返回 null
     *
     * @param val 要查找的元素
     * @return {@link TreeNode}
     * @author Fan
     * @since 2023/5/22 16:10
     */
    public TreeNode find(int val) {
        TreeNode current = root;

        while (null != current && current.data != val) {
            // 值小于, 则继续向左边找
            if (current.data > val) {
                current = current.leftChild;
            } else {
                // 否则向右边找
                current = current.rightChild;
            }
        }

        // 返回当前节点, 未找到则为 null
        return current;
    }

    /**
     * 查找二叉排序树中最小值, 即寻找最左子节点的值
     *
     * @return {@link int}
     * @author Fan
     * @since 2023/5/22 16:13
     */
    public int findMin() {
        TreeNode current = root;

        while (null != current.leftChild) {
            current = current.leftChild;
        }
        return current.data;
    }

    /**
     * 删除节点
     *
     * @param val 节点值
     * @author Fan
     * @since 2023/5/22 16:44
     */
    public void delete(int val) {
        // 记录要删除的节点, 初始为根节点
        TreeNode delete = root;
        // 记录要删除节点的父节点
        TreeNode deleteParent = null;

        while (null != delete && delete.data != val) {
            deleteParent = delete;
            if (delete.data > val) {
                delete = delete.leftChild;
            } else {
                delete = delete.rightChild;
            }
        }

        // 未找到, 直接返回
        if (null == delete) {
            System.out.println("要删除的节点不存在!");
            return;
        }

        // 要删除节点有两个子节点, 需要找到删除节点右子树的最小值, 然后替换删除节点
        if (null != delete.leftChild && null != delete.rightChild) {
            // 记录要删除节点的右子树的最小节点, 初始值即为删除节点的右子节点
            TreeNode min = delete.rightChild;
            // 记录删除节点的右子树的最小节点的父节点, 初始值即为删除节点(删除节点的右子节点的父节点)
            TreeNode minParent = delete;

            // 找到删除节点的右节点的最左子节点, 即右子树的最小值
            while (null != min.leftChild) {
                // 将原值赋值给最小节点的父节点
                minParent = min;
                // 最小节点的左节点赋值给最小节点
                min = min.leftChild;
            }

            // 将最小节点的值赋值给要删除节点
            delete.data = min.data;
            // 然后就是删除最小节点, 让最小节点成为删除节点, 对应最小节点的父节点即为删除节点的父节点, 这样就完成了替换, 通过后面的步骤即可删除
            delete = min;
            deleteParent = minParent;
        }

        // 删除节点是叶子节点或仅有一个子节点, 找到删除节点的子节点, 然后让删除节点的父节点的子节点指向删除节点的子节点, 从而删除待删除节点
        TreeNode child;
        if (null != delete.leftChild) {
            child = delete.leftChild;
        } else if (null != delete.rightChild) {
            child = delete.rightChild;
        } else {
            child = null;
        }

        // 删除节点是根节点, 即删除节点的父节点为 null
        if (null == deleteParent) {
            // 则让根节点为删除节点的子节点
            root = child;
        } else if (delete == deleteParent.leftChild) {
            // 让删除节点的父节点的左子节点为删除节点的子节点, 即将删除节点删除
            deleteParent.leftChild = child;
        } else {
            // 让删除节点的父节点的右子节点为删除节点的子节点, 即将删除节点删除
            deleteParent.rightChild = child;
        }
    }
}
