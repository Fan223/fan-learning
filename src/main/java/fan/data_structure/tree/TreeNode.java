package fan.data_structure.tree;

import lombok.Data;

/**
 * 树节点
 *
 * @author Fan
 * @since 2023/5/22 15:02
 */
@Data
public class TreeNode {

    public Integer data;

    public TreeNode leftChild;

    public TreeNode rightChild;

    public TreeNode(Integer data) {
        this.data = data;
    }
}
