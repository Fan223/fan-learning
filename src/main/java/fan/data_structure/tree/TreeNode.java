package fan.data_structure.tree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 树节点
 *
 * @author Fan
 * @since 2023/5/22 15:02
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TreeNode {

    public Integer data;

    public TreeNode leftChild;

    public TreeNode rightChild;

    public TreeNode(Integer data) {
        this.data = data;
    }
}
