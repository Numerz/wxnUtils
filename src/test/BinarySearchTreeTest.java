package test;

import wxn.util.tree.BinarySearchTree;

import java.util.Arrays;
import java.util.Collections;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        Integer[] arr = {8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Arrays.asList(arr));

        int i = 1;
        System.out.println(i++ + " " + bst.levelTraversal());
        System.out.println(i++ + " " + bst.preorder());
        System.out.println(i++ + " " + bst.inorder());
        System.out.println(i++ + " " + bst.postorder());
        System.out.println(i++ + " " + bst.toString());
        System.out.println(i++ + " " + bst.max());
        System.out.println(i++ + " " + bst.min());


        System.out.println(i++ + " " + bst.predecessor(7));
        System.out.println(i++ + " " + bst.successor(7));
        // throw NoSuchElement Exception
//        System.out.println(i++ + " " + bst.predecessor(1));
//        System.out.println(i++ + " " + bst.successor(15));
        System.out.println(i++ + " " + bst.contains(1));
        System.out.println(i++ + " " + bst.contains(0));

        bst.delete(15);
        System.out.println(i++ + " " + bst.levelTraversal());
        bst.delete(6);
        System.out.println(i + " " + bst.levelTraversal());
    }
}
