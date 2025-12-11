/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class OrderBST {

    TreeNode root;

    void loadData(int k) {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        String[] b = Lib.readLineToStrArray("data.txt", k + 1);
        double[] c = Lib.readLineToDoubleArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            Order newOrder = new Order(a[i], b[i], c[i]);
            insert(newOrder);
        }
    }

    public void insert(Order order) {
        this.root = insert(this.root, order);
    }

    private TreeNode insert(TreeNode root, Order order) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (root == null) {
            return new TreeNode(order);
        }
        int cmp = order.orderID.compareTo(root.info.orderID);
        if (cmp < 0) {
            root.left = insert(root.left, order);
        } else if (cmp > 0) {
            root.right = insert(root.right, order);
        } else {
            root = new TreeNode(order);
        }
        //---------------------------------------------------------
        return root;
    }

    public Order search(String id) {
        return search(root, id);
    }

    public Order search(TreeNode root, String id) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (root != null) {
            int cmp = id.compareTo(root.info.orderID);
            if (cmp < 0) {
                return search(root.left, id);
            } else if (cmp > 0) {
                return search(root.right, id);
            } else {
                return root.info;
            }
        }
        //---------------------------------------------------------
        return null;
    }

    public void remove(String id) {
        root = remove(root, id);
    }

    public TreeNode remove(TreeNode root, String id) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (root != null) {
            int cmp = id.compareTo(root.info.orderID);
            if (cmp < 0) {
                root.left = remove(root.left, id);
            } else if (cmp > 0) {
                root.right = remove(root.right, id);
            } else {
                if (root.left == null) {
                    return root.right;
                }
                if (root.right == null) {
                    return root.left;
                }

                Order minOrder = findMin(root.right);
                root.info = minOrder;
                root.right = remove(root.right, minOrder.orderID);
            }
            return root;
        }
        //---------------------------------------------------------
        return null;
    }

    public Order findMin() {
        return findMin(root);
    }

    public Order findMin(TreeNode root) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (root != null) {
            if (root.left == null) {
                return root.info;
            }
            if (root.left != null) {
                return findMin(root.left);
            }
        }
        //---------------------------------------------------------
        return null;
    }
}
