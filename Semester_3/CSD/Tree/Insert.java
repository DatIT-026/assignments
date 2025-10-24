/*
         8
       /   \
      2     1
     / \   /
    4   6 9

*/

import java.util.LinkedList;
import java.util.Queue;

public class Insert {
    public static class TreeNode {
        char value;
        TreeNode left, right;

        public TreeNode(char value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // create a new binary tree node
    public static TreeNode createNewNode(char value) {
        return new TreeNode(value);
    }

    // insert node into the binary tree at first available position (level order)
    public static void insertNode(TreeNode root, char value) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // delete the first element in queue

            // if left tree is empty, insert here
            if (current.left == null) {
                current.left = createNewNode(value);
                return;
            } else queue.add(current.left); // if not, add it to the queue

            // if right tree is empty, insert here
            if (current.right == null) {
                current.right = createNewNode(value);
                return;
            } else queue.add(current.right);
        }
    }

    // print the tree in level order traversal
    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.value + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode root = createNewNode('8');
        TreeNode nodeA = createNewNode('2');
        TreeNode nodeB = createNewNode('1');
        TreeNode nodeC = createNewNode('4');
        TreeNode nodeD = createNewNode('6');

        root.left = nodeA;
        root.right = nodeB;
        nodeA.left = nodeC;
        nodeA.right = nodeD;

        System.out.println("Initial tree: ");
        printLevelOrder(root);

        insertNode(root, '9');
        System.out.println("After insert '9': ");
        printLevelOrder(root);
        System.out.println("root.right.left.value: " + root.right.left.value);
    }   
}