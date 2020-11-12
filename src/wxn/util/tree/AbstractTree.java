package wxn.util.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public abstract class AbstractTree<T> {
    TreeNode<T> root;

    public String levelTraversal(){
        StringBuilder sb = new StringBuilder("{");
        LinkedList<TreeNode<T>> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()){
            TreeNode<T> node = list.removeFirst();
            sb.append(node.toString()).append(", ");
            if (node.left != null){
                list.addLast(node.left);
            }
            if (node.right != null){
                list.addLast(node.right);
            }
        }
        // string operation
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    public String preorder(){
        StringBuilder sb = new StringBuilder("{");
        Deque<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> p = root;
        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                // read data
                sb.append(p.toString()).append(", ");
                p = p.left;
            }
            TreeNode<T> node = stack.pop();
            p = node.right;
        }
        // string operation
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    public String inorder(){
        StringBuilder sb = new StringBuilder("{");
        Deque<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> p = root;
        while (p != null || !stack.isEmpty()){
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            TreeNode<T> node = stack.pop();
            // read data
            sb.append(node.toString()).append(", ");
            p = node.right;
        }
        // string operation
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    public String postorder(){
        StringBuilder sb = new StringBuilder("{");
        Deque<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> p = root;
        TreeNode<T> prev = null;   // 记录前一个出栈元素，判断是否在回溯过程中
        while (p != null || !stack.isEmpty()){
            // 此循环结束时的结点必没有左孩子
            while (p != null){
                stack.push(p);
                p = p.left;
            }
            TreeNode<T> node = stack.pop();
            if (node.right == null || node.right == prev){
                // 出栈情况
                // 结点没有左右孩子或结点处于出栈链（它的右孩子刚出栈）
                // read data
                sb.append(node.toString()).append(", ");
                prev = node;
            }else {
                // 不出栈情况
                stack.push(node);
                p = node.right;
            }

        }
        // string operation
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return inorder();
    }
}
