public class BSTree {
    Node root;

    public BSTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void visit(Node p) {
        if (p == null) return;
        System.out.print(p.info + " ");
    }

    public Node search(Node p, int key) {
        if (p == null) return null;
        if (p.info == key) return p;
        else if (p.info > key) return search(p.left, key);
        else return search(p.right, key);
    }

    public void insert(int x) {
        Node p = new Node(x);
        Node f = null, q = root; // f la node cha, q la node duyet tu root
        
        while (q != null) {
            if (q.info == x) {
                System.out.println("Key cannot be duplicated...");
                return;
            }
            if (q.info < x) {
                f = q;  // luu lai node cha
                q = q.right;
            } else {
                f = q;
                q = q.left;
            }
        }

        if (f == null) root = p; // Node cha = null, BST rong -> root la p
        else if (p.info > f.info) f.right = p;
        else f.left = p;
    }

    public Node findMax(Node p) {
        // tree rong? -> tra ve null
        if (p == null) return null;

        Node curr = p;
        while (curr.right != null) {
            curr = curr.right;
        }

        // curr luc nay la nut phai nhat -> nut lon nhat
        return curr;
    }

    public Node findMin(Node p) {
        if (p == null) return null;

        Node curr = p;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    public void preOrder(Node p) {
        if (p == null) return;
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void inOrder(Node p) {
        if (p == null) return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    public void postOrder(Node p) {
        if (p == null) return;
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }
}