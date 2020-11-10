package wxn.util.tree;

import java.util.Comparator;

/**
 * @param <T> 注意重写T的equals方法
 */
public class BinarySearchAbstractTree<T> extends AbstractTree<T> {
    TreeNode<T> root;

    private final Comparator<? super T> comparator;

    public BinarySearchAbstractTree(){
        comparator = null;
    }

    public BinarySearchAbstractTree(T data){
        root = new TreeNode<>(data);
        comparator = null;
    }

    public BinarySearchAbstractTree(TreeNode<T> root) {
        this.root = root;
        comparator = null;
    }

    public BinarySearchAbstractTree(TreeNode<T> root, Comparator<? super T> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    /**
     * 插入一个结点
     * 如果存在data域相等的结点，抛异常
     * @param node
     */
    public void insert(TreeNode<T> node){
        T data = node.data;
        insert(data);
    }

    /**
     * 插入一个数据
     * @param data
     */
    public void insert(T data){
        if (comparator != null){
            insertComparator(data);
        }else {
            insertComparable(data);
        }
    }

    private void insertComparable(T data){
        if (root == null){
            root = new TreeNode<>(data);
            return;
        }

        Comparable<? super T> key = (Comparable<? super T>) data;
        TreeNode<T> node = root;
        TreeNode<T> parent;
        int cmp;
        do{
            cmp = key.compareTo(node.data);
            parent = node;

            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0){
                node = node.right;
            }else {
                node.setValue(data);
                return;
            }
        }while (node != null);

        TreeNode<T> treeNode = new TreeNode<>(data, parent);
        if (cmp < 0){
            parent.left = treeNode;
        }else {
            parent.right = treeNode;
        }
    }

    private void insertComparator(T data){
        if (root == null){
            root = new TreeNode<>(data);
            return;
        }

        TreeNode<T> node = root;
        TreeNode<T> parent;
        int cmp;
        do{
            cmp = comparator.compare(data, node.data);
            parent = node;

            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0){
                node = node.right;
            }else {
                node.setValue(data);
                return;
            }
        }while (node != null);

        TreeNode<T> treeNode = new TreeNode<>(data, parent);
        if (cmp < 0){
            parent.left = treeNode;
        }else {
            parent.right = treeNode;
        }
    }


    public boolean contains(T data){
        if (comparator != null){
            return getUsingComparator(data) != null;
        }
        return getComparable(data) != null;
    }

    /**
     *
     * @param data
     * @return 若不存在，返回null；否则返回结点
     */
    public TreeNode<T> get(T data){
        if (comparator != null){
            return getUsingComparator(data);
        }
        return getComparable(data);
    }

    public TreeNode<T> getComparable(T data){
        Comparable<? super T> key = (Comparable<? super T>) data;
        TreeNode<T> node = root;
        while (node != null){
            int cmp = key.compareTo(node.data);

            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0){
                node = node.right;
            }else {
                return node;
            }
        }
        return null;
    }

    public TreeNode<T> getUsingComparator(T data){
        TreeNode<T> node = root;
        while (node != null){
            int cmp = comparator.compare(data, node.data);

            if (cmp < 0){
                node = node.left;
            }else if (cmp > 0){
                node = node.right;
            }else {
                return node;
            }
        }
        return null;
    }

}
