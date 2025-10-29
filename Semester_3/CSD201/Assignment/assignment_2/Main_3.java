// Main.java
public class Main_3 {
  public static void main(String[] args) {
    BSTree tree = new BSTree();

    // insertion of a new node
    tree.insert(50);
    tree.insert(30);
    tree.insert(70);
    tree.insert(20);
    tree.insert(40);
    tree.insert(60);
    tree.insert(80);

    // in-order
    // expect: 20 30 40 50 60 70 80
    System.out.print("BST after insert (in-order): ");
    tree.inOrder(tree.root);
    System.out.println("\n");

    // search a given item
    int item1 = 40;
    Node result1 = tree.search(tree.root, item1);
    if (result1 != null) System.out.println(result1.info + " is found!"); // expect: tim thay 40
    else System.out.println(item1 + " is not found!");

    int item2 = 99;
    Node result2 = tree.search(tree.root, item2);
    if (result2 != null) System.out.println(result2.info + " is found!");
    else System.out.println(item2 + " is not found!"); // expect: khong tim thay 99
    System.out.println();

    // maximum element of BST
    Node maxNode = tree.findMax(tree.root);
    if (maxNode != null) System.out.println("Maximum element: " + maxNode.info); // expect: 80
    else System.out.println("BST is empty!");

    // minimum element of BST
    Node minNode = tree.findMin(tree.root);
    if (minNode != null) System.out.println("Minimum element: " + minNode.info); // expect: 20
    else System.out.println("BST is empty!");
  }
}