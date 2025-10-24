/*
        8
       / \
      2   1
     / \
    4   6
*/

public class BinaryTree {
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

        System.out.println("root.left.right.value: " + root.left.right.value);
    }   
}