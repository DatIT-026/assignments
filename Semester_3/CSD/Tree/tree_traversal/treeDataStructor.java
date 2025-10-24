/*
        8
       / \
      2   1
     / \
    4   6
*/

class Node {
    int data;
    Node leftChild;
    Node rightChild;

    Node (int key) {
        data = key;
        leftChild = rightChild = null;
    }
}

public class treeDataStructor {
    Node root = null;
    void inorder_traversal (Node node) {
        if (node != null) {
            inorder_traversal(node.leftChild);
            System.out.print(node.data + " ");
            inorder_traversal(node.rightChild);
        }
    }

    void preorder_traversal (Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorder_traversal(node.leftChild);
            preorder_traversal(node.rightChild);
        }
    }

    void postorder_traversal (Node node) {
        if (node != null) {
            postorder_traversal(node.leftChild);
            preorder_traversal(node.rightChild);
            System.out.print(node.data + " ");
        }
    }

    public static void main(String[] args) {
        treeDataStructor tree = new treeDataStructor();
        tree.root = new Node(8);
        tree.root.leftChild = new Node(2);
        tree.root.rightChild = new Node(1);
        tree.root.leftChild.leftChild = new Node(4);
        tree.root.leftChild.rightChild = new Node(6);
        

        System.out.print("Inorder traversal: ");
        tree.inorder_traversal(tree.root);
        System.out.print("\nPreorder traversal: ");
        tree.preorder_traversal(tree.root);
        System.out.print("\nPostorder traversal: ");
        tree.postorder_traversal(tree.root);
    }
}