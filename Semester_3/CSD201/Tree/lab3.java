public class lab3 {
    static class Node {
        int data;
        Node left, right;
        
        Node(int value) {
            data = value;
            left = right = null;
        }
    }
    
    Node root;
    public lab3() {
        root = null;
    }
    
    // insert a new node into the BST
    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    
    // helper method for insertion (recursive)
    private Node insertRecursive(Node current, int value) {
        // if tree is empty, create new node
        if (current == null) return new Node(value);
        
        // traverse the tree to find the correct position
        if (value < current.data) current.left = insertRecursive(current.left, value); // insert in left subtree
            else if (value > current.data) current.right = insertRecursive(current.right, value); // Insert in right subtree
        
        // if value already exists, don't insert duplicate    
        return current;
    }
    
    // search for an item recursively
    public boolean search(int value) {
        return searchRecursive(root, value);
    }
    
    // helper method for search (recursive)
    private boolean searchRecursive(Node current, int value) {
        // if tree is empty or value not found
        if (current == null) return false;
        
        // if value is found at current node
        if (value == current.data) return true;
        
        // search in left subtree if value is smaller
        if (value < current.data) return searchRecursive(current.left, value);
        
        // search in right subtree if value is greater
        return searchRecursive(current.right, value);
    }
    
    // helper method to display the tree (In-order traversal)
    public void inorderTraversal() {
        System.out.print("In-order Traversal: ");
        inorderRecursive(root);
        System.out.println();
    }
    
    private void inorderRecursive(Node node) {
        if (node != null) {
            inorderRecursive(node.left);
            System.out.print(node.data + " ");
            inorderRecursive(node.right);
        }
    }
    
    // main method to test the BST
    public static void main(String[] args) {
        lab3 bst = new lab3();
        
        // test insertion
        System.out.println("Inserting values (50, 30, 70, 20, 40, 60, 80)");
        
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        
        // display the tree
        bst.inorderTraversal();
        
        // test search
        System.out.println("\nSearching for values:");
        System.out.println("Search 40: " + bst.search(40));  // true
        System.out.println("Search 25: " + bst.search(25));  // false
        System.out.println("Search 70: " + bst.search(70));  // true
        System.out.println("Search 100: " + bst.search(100)); // false
    }
}