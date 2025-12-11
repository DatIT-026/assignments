/*
        8
       / \
      2   1
     / \
    4   9

*/

import java.util.LinkedList;
import java.util.Queue;

public class Remove {
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

    // find the deepest rightmost node in the tree
    private static TreeNode getDeeperNode(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode deepest = null;

        while(!queue.isEmpty()) {
            deepest = queue.poll();
            if (deepest.left != null) queue.add(deepest.left);
            if (deepest.right != null) queue.add(deepest.right);
        }
        return deepest;
    }

    // Delete the deepest rightmost node
    private static void deleteDeepestNode(TreeNode root, TreeNode deepestNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if(current.left != null) {
                if (current.left == deepestNode) {
                    current.left = null;
                    return;
                } else queue.add(current.left);
            }

            if(current.right != null) {
                if (current.right == deepestNode) {
                    current.right = null;
                    return;
                } else queue.add(current.right);
            }
        }
    }

    // Remove node by value in binary tree
    public static void removeNode(TreeNode root, char value) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        TreeNode nodeToRemove = null;
        TreeNode current = null;

        // find node to remove
        while (!queue.isEmpty()) {
            current = queue.poll();
            
            if (current.value == value) nodeToRemove = current;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);

            if (nodeToRemove != null) {
                TreeNode deepestNode = getDeeperNode(root);
                if (deepestNode != null) {
                    nodeToRemove.value = deepestNode.value; // replace value
                    deleteDeepestNode(root, deepestNode);
                }
            } else System.out.print("Node with value '" + value + "' not found.");
        }
    }

    // Print tree in level order traversal
    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
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
        TreeNode nodeE = createNewNode('9');

        root.left = nodeA;
        root.right = nodeB;
        nodeA.left = nodeC;
        nodeA.right = nodeD;
        nodeB.left = nodeE;

        System.out.println("Initial tree:");
        printLevelOrder(root);

        
        System.out.println("Removing node '6':");
        removeNode(root, '6');
        printLevelOrder(root);

        System.out.println("root.left.right.value: " + root.left.right.value);
    }   
}