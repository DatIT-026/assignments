class Node {
    int data;
    Node left;
    Node right;
    
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree() {
        this.root = null;
    }
    
    public boolean searchRecursive(int key) {
        return searchRecursiveHelper(root, key);
    }
    
    private boolean searchRecursiveHelper(Node node, int key) {
        if (node == null) return false;
        if (node.data == key) return true;
        if (key < node.data) return searchRecursiveHelper(node.left, key);
        return searchRecursiveHelper(node.right, key);
    }
    
    public boolean searchIterative(int key) {
        Node current = root;
        
        while (current != null) {
            if (current.data == key) return true;
            else if (key < current.data) current = current.left;
            else current = current.right;
        }
        
        return false;
    }
    
    public void insert(int data) {
        root = insertHelper(root, data);
    }
    
    private Node insertHelper(Node node, int data) {
        if (node == null) return new Node(data);
        
        if (data < node.data) node.left = insertHelper(node.left, data);
        else if (data > node.data) node.right = insertHelper(node.right, data);
        
        return node;
    }
    
    public Integer findMaximum() {
        if (root == null) return null;
        return findMaximumHelper(root);
    }
    
    private int findMaximumHelper(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }
    
    public Integer findMinimum() {
        if (root == null) return null;
        return findMinimumHelper(root);
    }
    
    private int findMinimumHelper(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }
    
    public void inorderTraversal() {
        System.out.print("In-order Traversal: ");
        inorderHelper(root);
        System.out.println();
    }
    
    private void inorderHelper(Node node) {
        if (node != null) {
            inorderHelper(node.left);
            System.out.print(node.data + " ");
            inorderHelper(node.right);
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
}

public class BSTDemo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        
        System.out.println("=== Binary Search Tree Operations ===\n");
        
        System.out.println("Inserting elements: 50, 30, 70, 20, 40, 60, 80");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        bst.inorderTraversal();
        System.out.println();
        
        System.out.println("--- Search Operations (Recursive) ---");
        System.out.println("Search for 40: " + bst.searchRecursive(40));
        System.out.println("Search for 25: " + bst.searchRecursive(25));
        System.out.println();
        
        System.out.println("--- Search Operations (Iterative) ---");
        System.out.println("Search for 60: " + bst.searchIterative(60));
        System.out.println("Search for 100: " + bst.searchIterative(100));
        System.out.println();
        
        System.out.println("--- Min/Max Operations ---");
        System.out.println("Minimum element: " + bst.findMinimum());
        System.out.println("Maximum element: " + bst.findMaximum());
        System.out.println();
        
        BinarySearchTree emptyBST = new BinarySearchTree();
        System.out.println("--- Empty Tree Test ---");
        System.out.println("Empty tree minimum: " + emptyBST.findMinimum());
        System.out.println("Empty tree maximum: " + emptyBST.findMaximum());
        System.out.println("Search in empty tree: " + emptyBST.searchRecursive(10));
    }
}