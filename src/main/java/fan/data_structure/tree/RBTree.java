package fan.data_structure.tree;

public class RBTree<T extends Comparable<T>> {
    private RBTreeNode<T> mRoot;    // 根结点

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    public RBTree() {
        mRoot=null;
    }

    private boolean isRed(RBTreeNode<T> node) {
        return ((node!=null)&&(node.color==RED)) ? true : false;
    }
    private boolean isBlack(RBTreeNode<T> node) {
        return !isRed(node);
    }

    // 前序遍历"红黑树"
    private void preOrder(RBTreeNode<T> tree) {
        if(tree != null) {
            System.out.print(tree.key+"-" + tree.color + " ");
            preOrder(tree.leftChildren);
            preOrder(tree.rightChildren);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    // 中序遍历"红黑树"
    private void inOrder(RBTreeNode<T> tree) {
        if(tree != null) {
            inOrder(tree.leftChildren);
            System.out.print(tree.key+" ");
            inOrder(tree.rightChildren);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }


    // 后序遍历"红黑树"
    private void postOrder(RBTreeNode<T> tree) {
        if(tree != null)
        {
            postOrder(tree.leftChildren);
            postOrder(tree.rightChildren);
            System.out.print(tree.key+" ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }


    // (递归实现)查找"红黑树x"中键值为key的节点
    private RBTreeNode<T> search(RBTreeNode<T> x, T key) {
        if (x==null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.leftChildren, key);
        else if (cmp > 0)
            return search(x.rightChildren, key);
        else
            return x;
    }

    public RBTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    //  (非递归实现)查找"红黑树x"中键值为key的节点
    private RBTreeNode<T> iterativeSearch(RBTreeNode<T> x, T key) {
        while (x!=null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.leftChildren;
            else if (cmp > 0)
                x = x.rightChildren;
            else
                return x;
        }

        return x;
    }

    public RBTreeNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    // 查找最小结点：返回tree为根结点的红黑树的最小结点.
    private RBTreeNode<T> minimum(RBTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.leftChildren != null)
            tree = tree.leftChildren;
        return tree;
    }

    public T minimum() {
        RBTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    // 查找最大结点：返回tree为根结点的红黑树的最大结点.
    private RBTreeNode<T> maximum(RBTreeNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.rightChildren != null)
            tree = tree.rightChildren;
        return tree;
    }

    public T maximum() {
        RBTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    // 找结点(x)的后继结点. 即, 查找"红黑树中数据值大于该结点"的"最小结点".
    public RBTreeNode<T> successor(RBTreeNode<T> x) {
        // 如果x存在右孩子, 则"x的后继结点"为 "以其右孩子为根的子树的最小结点".
        if (x.rightChildren != null)
            return minimum(x.rightChildren);

        // 如果x没有右孩子. 则x有以下两种可能：
        // (01) x是"一个左孩子", 则"x的后继结点"为 "它的父结点".
        // (02) x是"一个右孩子", 则查找"x的最低的父结点, 并且该父结点要具有左孩子", 找到的这个"最低的父结点"就是"x的后继结点".
        RBTreeNode<T> y = x.parent;
        while ((y!=null) && (x==y.rightChildren)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    // 找结点(x)的前驱结点. 即, 查找"红黑树中数据值小于该结点"的"最大结点".
    public RBTreeNode<T> predecessor(RBTreeNode<T> x) {
        // 如果x存在左孩子, 则"x的前驱结点"为 "以其左孩子为根的子树的最大结点".
        if (x.leftChildren != null)
            return maximum(x.leftChildren);

        // 如果x没有左孩子. 则x有以下两种可能：
        // (01) x是"一个右孩子", 则"x的前驱结点"为 "它的父结点".
        // (01) x是"一个左孩子", 则查找"x的最低的父结点, 并且该父结点要具有右孩子", 找到的这个"最低的父结点"就是"x的前驱结点".
        RBTreeNode<T> y = x.parent;
        while ((y!=null) && (x==y.leftChildren)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.             / \                #
     *  lx   y                          x  ry
     *     /   \                       /  \
     *    ly   ry                     lx  ly
     *
     *
     */
    private void leftRotate(RBTreeNode<T> rbTreeNode) {
        // 将当前节点的右子节点设为新节点
        RBTreeNode<T> newRoot = rbTreeNode.rightChildren;
        // 将新节点的左子节点设为当前节点的右子节点
        rbTreeNode.rightChildren = newRoot.leftChildren;

        // 将 “x的父亲” 设为 “y的父亲”
        newRoot.parent = rbTreeNode.parent;

        if (rbTreeNode.parent == null) {
            this.mRoot = newRoot;            // 如果 “x的父亲” 是空节点, 则将y设为根节点
        } else {
            if (rbTreeNode.parent.leftChildren == rbTreeNode)
                rbTreeNode.parent.leftChildren = newRoot;    // 如果 x是它父节点的左孩子, 则将y设为“x的父节点的左孩子”
            else
                rbTreeNode.parent.rightChildren = newRoot;    // 如果 x是它父节点的左孩子, 则将y设为“x的父节点的左孩子”
        }

        // 将 “x” 设为 “y的左孩子”
        newRoot.leftChildren = rbTreeNode;
    }

    /*
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)-.            /  \                     #
     *        x   ry                           lx   y
     *       / \                                   / \                   #
     *      lx  rx                                rx  ry
     *
     */
    private void rightChildrenRotate(RBTreeNode<T> y) {
        // 设置x是当前节点的左孩子.
        RBTreeNode<T> x = y.leftChildren;

        // 将 “x的右孩子” 设为 “y的左孩子”;
        // 如果"x的右孩子"不为空的话, 将 “y” 设为 “x的右孩子的父亲”
        y.leftChildren = x.rightChildren;
        if (x.rightChildren != null)
            x.rightChildren.parent = y;

        // 将 “y的父亲” 设为 “x的父亲”
        x.parent = y.parent;

        if (y.parent == null) {
            this.mRoot = x;            // 如果 “y的父亲” 是空节点, 则将x设为根节点
        } else {
            if (y == y.parent.rightChildren)
                y.parent.rightChildren = x;    // 如果 y是它父节点的右孩子, 则将x设为“y的父节点的右孩子”
            else
                y.parent.leftChildren = x;    // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
        }

        // 将 “y” 设为 “x的右孩子”
        x.rightChildren = y;

        // 将 “y的父节点” 设为 “x”
        y.parent = x;
    }

    // 插入节点
    public void insert(T key) {
        insert(new RBTreeNode<T>(key, BLACK));
    }

    // 插入节点后, 修正红黑树
    private void insert(RBTreeNode<T> node) {
        int cmp;
        RBTreeNode<T> y = null;
        RBTreeNode<T> x = this.mRoot;

        // 1. 将红黑树当作一颗二叉查找树, 将节点添加到二叉查找树中.
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key); //当前key h和插入的key进行比较
            if (cmp < 0)
                x = x.leftChildren;
            else
                x = x.rightChildren;
        }

        node.parent = y;
        if (y!=null) {
            cmp = node.key.compareTo(y.key); //判断插入到左边还是右边
            if (cmp < 0)
                y.leftChildren = node;
            else
                y.rightChildren = node;
        } else {
            this.mRoot = node;
        }

        // 2. 设置节点的颜色为红色
        node.color = RED;

        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    /*
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡), 再调用该函数;
     * 目的是将它重新塑造成一颗红黑树.
     *1.Z是根节点(即Z插入前是一颗空树)
     * 2.Z的叔节点是红色的
     * 3.Z的叔节点是黑色的, 并且局部呈现三角行(左右三角)
     * 3.Z的叔节点是黑色的, 并且局部呈现直线角行(左右直线)
     *
     * 参数说明：
     *     node 插入的结点
     *
     */
    private void insertFixUp(RBTreeNode<T> addNode) {
        RBTreeNode<T> parent, gparent;

        // 若“父节点存在, 并且父节点的颜色是红色”
        while (((parent = addNode.parent)!=null) && isRed(parent)) {
            gparent = parent.parent;

            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.leftChildren) {
                // Case 1条件：叔叔节点是红色
                RBTreeNode<T> uncle = gparent.rightChildren;
                if ((uncle!=null) && isRed(uncle)) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    addNode = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色, 且当前节点是右孩子
                if (parent.rightChildren == addNode) {
                    RBTreeNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = addNode;
                    addNode = tmp;
                }

                // Case 3条件：叔叔是黑色, 且当前节点是左孩子.
                parent.color = BLACK;
                gparent.color = RED;
                rightChildrenRotate(gparent);
            } else {    //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBTreeNode<T> uncle = gparent.leftChildren;
                if ((uncle!=null) && isRed(uncle)) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    addNode = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色, 且当前节点是左孩子
                if (parent.leftChildren == addNode) {
                    RBTreeNode<T> tmp;
                    rightChildrenRotate(parent);
                    tmp = parent;
                    parent = addNode;
                    addNode = tmp;
                }

                // Case 3条件：叔叔是黑色, 且当前节点是右孩子.
                parent.color = BLACK;
                gparent.color = RED;
                leftRotate(gparent);
            }
        }

        // 将根节点设为黑色
        mRoot.color = BLACK;
    }

    /*
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡), 再调用该函数;
     * 目的是将它重新塑造成一颗红黑树.
     *
     *
     *
     * 参数说明：
     *     node 待修正的节点
     */
    private void removeFixUp(RBTreeNode<T> fixNode, RBTreeNode<T> parent) {
        RBTreeNode<T> other;

        while ((fixNode==null || isBlack(fixNode)) && (fixNode != this.mRoot)) {
            if (parent.leftChildren == fixNode) {//删除的节点是左节点
                other = parent.rightChildren;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    other.color = BLACK;
                    parent.color = RED;
                    leftRotate(parent);
                    other = parent.rightChildren;
                }

                if ((other.leftChildren==null || isBlack(other.leftChildren)) &&
                        (other.rightChildren==null || isBlack(other.rightChildren))) {
                    // Case 2: x的兄弟w是黑色, 且w的俩个孩子也都是黑色的
                    other.color = RED;
                    fixNode = parent;
                    parent = fixNode.parent;
                } else {

                    if (other.rightChildren==null || isBlack(other.rightChildren)) {
                        // Case 3: x的兄弟w是黑色的, 并且w的左孩子是红色, 右孩子为黑色.
                        other.leftChildren.color = BLACK;
                        other.color = RED;
                        rightChildrenRotate(other);
                        other = parent.rightChildren;
                    }
                    // Case 4: x的兄弟w是黑色的; 并且w的右孩子是红色的, 左孩子任意颜色.
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.rightChildren.color = BLACK;
                    leftRotate(parent);
                    fixNode = this.mRoot;
                    break;
                }
            } else { //删除的节点是右节点

                other = parent.leftChildren;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    other.color = BLACK;
                    parent.color = RED;
                    rightChildrenRotate(parent);
                    other = parent.leftChildren;
                }

                if ((other.leftChildren==null || isBlack(other.leftChildren)) &&
                        (other.rightChildren==null || isBlack(other.rightChildren))) {
                    // Case 2: x的兄弟w是黑色, 且w的俩个孩子也都是黑色的
                    other.color = RED;
                    fixNode = parent;
                    parent = fixNode.parent;
                } else {

                    if (other.leftChildren==null || isBlack(other.leftChildren)) {
                        // Case 3: x的兄弟w是黑色的, 并且w的左孩子是红色, 右孩子为黑色.
                        other.rightChildren.color = BLACK;
                        other.color = RED;
                        leftRotate(other);
                        other = parent.leftChildren;
                    }

                    // Case 4: x的兄弟w是黑色的; 并且w的右孩子是红色的, 左孩子任意颜色.
                    other.color = parent.color;


                    parent.color = BLACK;
                    other.leftChildren.color = BLACK;
                    rightChildrenRotate(parent);
                    fixNode = this.mRoot;
                    break;
                }
            }
        }

        if (fixNode!=null)
            fixNode.color = BLACK;
    }

    // 删除结点(delNode), 并返回被删除的结点
    private void remove(RBTreeNode<T> delNode) {
        RBTreeNode<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况.
        if ( (delNode.leftChildren!=null) && (delNode.rightChildren!=null) ) {
            // 被删节点的后继节点. (称为"取代节点")
            // 用它来取代"被删节点"的位置, 然后再将"被删节点"去掉.
            RBTreeNode<T> replace = delNode;

            // 获取后继节点
            replace = replace.rightChildren;
            while (replace.leftChildren != null)
                replace = replace.leftChildren;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (delNode.parent!=null) {
                if (delNode.parent.leftChildren == delNode)
                    delNode.parent.leftChildren = replace;
                else
                    delNode.parent.rightChildren = replace;
            } else {
                // "node节点"是根节点, 更新根节点.
                this.mRoot = replace;
            }

            // child是"取代节点"的右孩子, 也是需要"调整的节点".
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点.
            child = replace.rightChildren;
            parent = replace.parent;
            // 保存"取代节点"的颜色
            color = replace.color;

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == delNode) {
                parent = replace;
            } else {
                // child不为空
                if (child!=null)
                    child.parent = parent;
                parent.leftChildren = child;

                replace.rightChildren = delNode.rightChildren;
                delNode.rightChildren.parent = replace;
            }

            replace.parent = delNode.parent;
            replace.color = delNode.color;
            replace.leftChildren = delNode.leftChildren;
            delNode.leftChildren.parent = replace;

            if (color == BLACK) //红色直接删除 黑色删除后维护
                removeFixUp(child, parent);

            delNode = null;
            return ;
        }

        if (delNode.leftChildren !=null) {
            child = delNode.leftChildren;
        } else {
            child = delNode.rightChildren;
        }

        parent = delNode.parent;
        // 保存"取代节点"的颜色
        color = delNode.color;

        if (child!=null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent!=null) {
            if (parent.leftChildren == delNode)
                parent.leftChildren = child;
            else
                parent.rightChildren = child;
        } else {
            this.mRoot = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        delNode = null;
    }

    /*
     * 删除结点(z), 并返回被删除的结点
     *
     * 参数说明：
     *     tree 红黑树的根结点
     *     z 删除的结点
     */
    public void remove(T key) {
        RBTreeNode<T> node;

        if ((node = search(mRoot, key)) != null)
            remove(node);
    }

    /*
     * 销毁红黑树
     */
    private void destroy(RBTreeNode<T> tree) {
        if (tree==null)
            return ;

        if (tree.leftChildren != null)
            destroy(tree.leftChildren);
        if (tree.rightChildren != null)
            destroy(tree.rightChildren);

        tree=null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"红黑树"
     *
     * key        -- 节点的键值
     * direction  --  0, 表示该节点是根节点;
     *               -1, 表示该节点是它的父结点的左孩子;
     *                1, 表示该节点是它的父结点的右孩子.
     */
    private void print(RBTreeNode<T> tree, T key, int direction) {

        if(tree != null) {

            if(direction==0)    // tree是根节点
                System.out.printf("%2d(B) is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d(%s) is %2d's %6s child\n", tree.key, isRed(tree)?"R":"B", key, direction==1?"rightChildren" : "leftChildren");

            print(tree.leftChildren, tree.key, -1);
            print(tree.rightChildren,tree.key,  1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
