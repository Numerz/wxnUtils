package wxn.util.tree;

public class MultiwayTreeNode<T> extends TreeNode<T> {
    /**
     * 左孩子右兄弟表示法
     * left child and right sibling
     */

    public MultiwayTreeNode(T data) {
        super(data);
    }

    public MultiwayTreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        super(data, left, right);
    }

    public MultiwayTreeNode(T data, TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right) {
        super(data, parent, left, right);
    }
}
