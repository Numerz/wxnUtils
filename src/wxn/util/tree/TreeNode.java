package wxn.util.tree;

/**
 * binary tree node
 * @param <T>
 */
public class TreeNode<T> {
    T data;

    TreeNode<T> parent;

    /**
     * left child and right child
     */
    TreeNode<T> left, right;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode(T data, TreeNode<T> parent, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public void setValue(T data){
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
