package q1;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class TaskBST {

    private class TreeNode {

        Task task;
        TreeNode left, right;

        TreeNode(Task t) {
            this.task = t;
            left = right = null;
        }
    }

    private TreeNode root;

    public TaskBST() {
        root = null;
    }

    public void ftraverse(RandomAccessFile f, int choice) throws Exception {
        f.writeBytes("--- Task BST ---\r\n");
        if (root == null) {
            f.writeBytes("Empty\r\n");
        } else {
            if (choice == 1) { //
                List<Task> tasks = printOut();
                for (Task task : tasks) {
                    f.writeBytes(task.toString() + "\r\n");
                }
            } else if (choice == 2) {
                List<Task> tasks = preOrderTraversal();
                for (Task task : tasks) {
                    f.writeBytes(task.toString() + "\r\n");
                }
            }

        }
    }

    public List<Task> printOut() {
        List<Task> result = new ArrayList<>();
        help_fn(root, result);
        return result;
    }

    private void help_fn(TreeNode node, List<Task> resultList) {
        if (node == null) {
            return;
        }
        help_fn(node.left, resultList);
        resultList.add(node.task);
        help_fn(node.right, resultList);
    }

    // =======================================================
    // === STUDENT IMPLEMENTATION AREA                     ===
    // =======================================================
    // f2: Insert a Task into the BST
    public void insert(Task task) {
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)

        TreeNode q = new TreeNode(task);
        TreeNode f = null, p = root;

        if (root == null) {
            root = q;
            return;
        }

        while (p != null) {
            if (p.task.priority == task.priority) {
                return;
            }
            f = p;
            if (task.priority < p.task.priority) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (task.priority < f.task.priority) {
            f.left = q;
        } else {
            f.right = q;
        }

        return;
        // -----------------------------------------------------
    }

    // f3: Pre-Order Traversal
    public List<Task> preOrderTraversal() {
        List<Task> result = new ArrayList<>();
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)

        if (result == null) {
            return null;
        }
        help_fn2(root, result);

        // -----------------------------------------------------
        return result;
    }

    private void help_fn2(TreeNode node, List<Task> resultList) {
        if (node == null) {
            return;
        }
        resultList.add(node.task);
        help_fn2(node.left, resultList);
        help_fn2(node.right, resultList);
    }

    // f4: Remove a Task from the BST
    public void remove(int priority) {
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)

        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }

        TreeNode f, p;
        p = root;
        f = null;

        while (p != null) {
            if (p.task.priority == priority) {
                break;
            }
            if (priority < p.task.priority) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }

        if (p == null) {
            return;
        }

        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
            } else {
                if (f.left == p) {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
            } else {
                if (f.left == p) {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        if (p.left != null && p.right != null) {
            TreeNode q, fr, rp;
            fr = null;
            q = p.left;
            rp = q;

            while (rp.right != null) {
                fr = rp;
                rp = rp.right;
            }

            p.task = rp.task;

            if (fr == null) {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }

        return;
        // -----------------------------------------------------
    }
    // =======================================================
}
