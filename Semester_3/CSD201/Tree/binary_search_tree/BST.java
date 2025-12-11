/*
    1. START
    2. Tree is empty or not?
    3. If empty => go to 4. Else, do the following step:
        3.1. Search the root of the tree.
        3.2. If the key does not match with the value in the root => search its subtrees.
        3.3. If value of key is less than root value => search left subtrees.
        3.4. If value of key is greater than root value => search right subtrees.\
        3.5. If the key is not found in the tree => return unsuccessful search => go to 4.
    4. END

*/
import java.util.Scanner;

class BSTNode {
    int data;
    BSTNode left, right;
    
    BSTNode (int n) {
        data = n;
        left = right = null;
    }
}

public class BST {
    static BSTNode root;
    
    // constructor
    BST() { root = null; }

    public boolean isEmpty() {
        return root == null;
    }

    private BSTNode insert(BSTNode node, int data) {
        if (node == null) node = new BSTNode(data);
            else {
                if (data <= node.data) node.left = insert(node.left, data);
                else node.right = insert(node.right, data);
            }
        return node;
    }

    public void delete(int k) {
        if (isEmpty()) System.out.print("TREE EMPTY");
        else if (search(k) == false) System.out.print(k + "is not a present");
        else {
            root = delete(root, k);
            System.out.print("\nNOOOOO! " + k + " is gone!");
        }
    }

    public BSTNode delete(BSTNode root, int k) {
        BSTNode p, p2, n;
        if (root.data == k) {
            BSTNode lt, rt;
            lt = root.left;
            rt = root.right;
            if (lt == null && rt == null) return null;
            else if (lt == null) { p = rt; return p; }
            else if (rt == null) { p = lt; return p; }
            else {
                p2 = rt;
                p = rt;
                while (p.left != null) p = p.left;
                p.left = lt;
                return p2;
            }
        }

        if (k < root.data) {
            n = delete(root.left, k);
            root.left = n;
        } else  {
            n = delete(root.right, k);
            root.right = n;
        }
        return root;
    }

    void printTree(BSTNode node, String prefix) {
        if (node == null) return;
        printTree(node.left, prefix);
        System.out.print(prefix + node.data + " ");
        printTree(node.right, prefix);
    }

    public boolean search(int val) {
        return search(root, val);
    }

    public boolean search(BSTNode r, int val) {
        boolean found = false;

        while ((r != null) && !found) {
            int rval = r.data;
            if (val < rval) r = r.left;
            else if (val > rval) r = r.right;
            else {
                found = true;
                break;
            }

            found = search(r, rval);
        }
        return found;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BST bst = new BST();

        root = bst.insert(root, 55);
        root = bst.insert(root, 20);
        root = bst.insert(root, 90);
        root = bst.insert(root, 80);
        root = bst.insert(root, 50);
        root = bst.insert(root, 35);
        root = bst.insert(root, 15);
        root = bst.insert(root, 65);

        System.out.print("\nBST: ");
        bst.printTree(root, "");

        bst.delete(55);

        System.out.print("\nBST: ");
        bst.printTree(root, "");
        
        int e = 80;
        System.out.print("\nElement to be searched: " + e);
        System.out.print("\nElement found: " + bst.search(root, 80));

        System.out.print("\nIs Tree empty? " + bst.isEmpty());
    }
}