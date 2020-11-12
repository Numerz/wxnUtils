package wxn.util.tree;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 所有数据互异
 * @param <T>
 */
public class BinarySearchTree<T> extends AbstractTree<T> {

    private final Comparator<? super T> comparator;

    public BinarySearchTree(){
        comparator = null;
    }

    public BinarySearchTree(T data){
        root = new TreeNode<>(data);
        comparator = null;
    }

    public BinarySearchTree(Collection<T> collection){
        for (T t : collection) {
            insert(t);
        }
        comparator = null;
    }

    public BinarySearchTree(TreeNode<T> root) {
        this.root = root;
        comparator = null;
    }

    public BinarySearchTree(TreeNode<T> root, Comparator<? super T> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    /**
     * 插入一个结点
     * 如果存在data域相等的结点，抛异常
     * @param node
     */
    private void insert(TreeNode<T> node){
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

    private TreeNode<T> getComparable(T data){
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

    private TreeNode<T> getUsingComparator(T data){
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

    public T successor(T data){
        TreeNode<T> res = successorNode(data);
        if (res == null){
            throw new NoSuchElementException();
        }
        return res.data;
    }

    private TreeNode<T> successorNode(T data){
        TreeNode<T> node = get(data);
        return successorNode(node);
    }

    private TreeNode<T> successorNode(TreeNode<T> node){
        if (node.right != null){
            return minNode(node.right);
        }else {
            while (node.parent != null && node == node.parent.right){
                node = node.parent;
            }
            return node.parent;
        }
    }

    public T min(){
        TreeNode<T> res = minNode(root);
        if (res == null){
            throw new NoSuchElementException();
        }
        return res.data;
    }

    private TreeNode<T> minNode(TreeNode<T> node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    public T predecessor(T data){
        TreeNode<T> res = predecessorNode(data);
        if (res == null){
            throw new NoSuchElementException();
        }
        return res.data;
    }

    private TreeNode<T> predecessorNode(T data){
        TreeNode<T> node = get(data);
        return predecessorNode(node);
    }

    private TreeNode<T> predecessorNode(TreeNode<T> node){
        if (node.left != null){
            return maxNode(node.left);
        }else {
            while (node.parent != null && node == node.parent.left){
                node = node.parent;
            }
            return node.parent;
        }
    }

    public T max(){
        TreeNode<T> res = maxNode(root);
        if (res == null){
            throw new NoSuchElementException();
        }
        return res.data;
    }

    private TreeNode<T> maxNode(TreeNode<T> node){
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    public void delete(T data){
        TreeNode<T> node = get(data);
        deleteNode(node);
    }

    private void deleteNode(TreeNode<T> node){
        if (node == null){
            return;
        }

        if (node.left == null){
            transplant(node, node.right);
        }else if (node.right == null){
            transplant(node, node.left);
        }else {
            // 直接写minNode函数而非successor可少一次判断
            TreeNode<T> succ = minNode(node.right);
            // 后继必无左孩子,不必考虑
            // 若是后继是node右孩子，直接带着右子树上位即可
            if (succ.parent != node){
                transplant(succ, succ.right);
                succ.right = node.right;
                node.right.parent = succ;
            }
            transplant(node, succ);
            succ.left = node.left;
            node.left.parent = succ;
        }
    }

    /**
     * 仅将source.parent的某个孩子指针指向target
     * 并将target.parent指向source.parent
     * @param source
     * @param target 可为null
     */
    private void transplant(TreeNode<T> source, TreeNode<T> target){
        if (source.parent == null){
            root = target;
        }
        else if (source == source.parent.left){
            source.parent.left = target;
        }else {
            source.parent.right = target;
        }

        if (target != null){
            target.parent = source.parent;
        }
    }

}
