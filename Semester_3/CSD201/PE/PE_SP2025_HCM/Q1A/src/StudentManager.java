class StudentBST {

    TreeNode root;

    StudentBST() {
        root = null;
    }

    boolean isEmpty() {
        return root == null;
    }

    void clear() {
        root = null;
    }

    TreeNode search(TreeNode p, String xId) {
        if (p == null) {
            return null;
        }
        if (p.info.getId().equals(xId)) {
            return p;
        }
        if (xId.compareTo(p.info.getId()) < 0) {
            return search(p.left, xId);
        } else {
            return search(p.right, xId);
        }
    }

    void insertHelper(TreeNode root, Student students) {
        if (students.getId().charAt(3) < root.info.getId().charAt(3)) {
            if (root.left == null) {
                root.left = new TreeNode(students);
            } else {
                insertHelper(root.left, students);
            }
        } else if (students.getId().charAt(3) > root.info.getId().charAt(3)) {
            if (root.right == null) {
                root.right = new TreeNode(students);
            } else {
                insertHelper(root.right, students);
            }
        }
    }

    void insert(String id, String name, double gpa, String major, double balance) {
        // Implement this function - Insert a new student to the BST
        // If the ID already exists, update the student's information
        // --------------------------------------------------------
        Student x = new Student(id, name, gpa, major, balance);
        if (root == null) {
            root = new TreeNode(x);
        }
        insertHelper(root, x);
        // YOUR CODE HERE
        // --------------------------------------------------------
    }

    Student maxGPA(TreeNode root) {
        if (root == null) {
            return null;
        }

        Student max = root.info;

        Student leftMax = maxGPA(root.left);
        Student rightMax = maxGPA(root.right);

        if (leftMax != null && leftMax.getGpa() > max.getGpa()) {
            max = leftMax;
        }

        if (rightMax != null && rightMax.getGpa() > max.getGpa()) {
            max = rightMax;
        }

        return max;
    }

    // Find student with highest GPA
    Student findHighestGPA() {
        // Implement this function - Find student with highest GPA
        // --------------------------------------------------------

        // YOUR CODE HERE
        // --------------------------------------------------------
        return maxGPA(root); // Change this return statement as needed
    }

    int count(TreeNode root, double threshold) {
        if (root == null) {
            return 0;
        }
        if (root.info.getGpa() >= threshold) {
            return count(root.left, 3.5) + count(root.right, 3.5) + 1;
        } else {
            return count(root.left, 3.5) + count(root.right, 3.5);
        }
    }

    // Count students with GPA greater than or equal to threshold
    int countByGPA(double threshold) {
        // Implement this function - Count students with GPA >= threshold
        // --------------------------------------------------------
        // YOUR CODE HERE
        // --------------------------------------------------------
        return count(root, 3.5); // Change this return statement as needed
    }

    // Count students by major
    int countByMajor(String major) {
        return countByMajor(root, major);
    }

    private int countByMajor(TreeNode p, String major) {
        if (p == null) {
            return 0;
        }
        int count = 0;
        if (p.info.getMajor().equals(major)) {
            count = 1;
        }

        return count + countByMajor(p.left, major) + countByMajor(p.right, major);
    }

    // Update student balance in BST
    boolean updateStudentBalance(String id, double amount) {
        // Implement this function - Update balance of student with given ID
        // --------------------------------------------------------
        if (search(root, id) != null) {
            search(root, id).info.setBalance(search(root, id).info.getBalance() + amount);
            return true;
        }
        // YOUR CODE HERE
        // --------------------------------------------------------
        return false; // Change this return statement as needed
    }

    void loadDataStudents(int k) {
        try {
            String[] ids = Lib.readLineToStrArray("data.txt", k);
            String[] names = Lib.readLineToStrArray("data.txt", k + 1);
            double[] gpas = Lib.readLineToDoubleArray("data.txt", k + 2);
            String[] majors = Lib.readLineToStrArray("data.txt", k + 3);
            double[] balances = Lib.readLineToDoubleArray("data.txt", k + 4);

            int n = Math.min(ids.length, Math.min(names.length, Math.min(gpas.length,
                    Math.min(majors.length, balances.length))));

            for (int i = 0; i < n; i++) {
                insert(ids[i], names[i], gpas[i], majors[i], balances[i]);
            }
        } catch (Exception e) {
            System.out.println("Error loading student data: " + e);
        }
    }
}

// ScholarshipList.java
class ScholarshipList {

    ListNode head, tail;

    ScholarshipList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return head == null;
    }

    void clear() {
        head = tail = null;
    }

    void addLast(String id, String name, double gpa, String major, double scholarshipAmount) {
        // Implement this function - add a new student to the end of the linked list
        // --------------------------------------------------------
        ListNode current = head;
        Student x = new Student(id, name, gpa, major, scholarshipAmount);
        if (head == null) {
            head = new ListNode(x);
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(x);
        }
        // YOUR CODE HERE
        // --------------------------------------------------------
    }

    ListNode search(String xId) {
        ListNode p = head;
        while (p != null) {
            if (p.info.getId().equals(xId)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // Remove a student from scholarship list by ID
    ListNode removeById(String id) {
        // Implement this function - Remove student with given ID from list
        // --------------------------------------------------------
        head = head.next;
        // YOUR CODE HERE
        // --------------------------------------------------------
        return null; // Change this return statement as needed
    }

    int count() {
        int count = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    // Load initial scholarship data from data.txt file (from line k+5)
    void loadInitialScholarship(int k) {
        try {
            String[] ids = Lib.readLineToStrArray("data.txt", k + 5);
            String[] names = Lib.readLineToStrArray("data.txt", k + 6);
            double[] gpas = Lib.readLineToDoubleArray("data.txt", k + 7);
            String[] majors = Lib.readLineToStrArray("data.txt", k + 8);
            double[] amounts = Lib.readLineToDoubleArray("data.txt", k + 9);

            int n = Math.min(ids.length, Math.min(names.length,
                    Math.min(gpas.length, Math.min(majors.length, amounts.length))));

            for (int i = 0; i < n; i++) {
                addLast(ids[i], names[i], gpas[i], majors[i], amounts[i]);
            }
        } catch (Exception e) {
            System.out.println("Error loading initial scholarship data: " + e);
        }
    }
}

class StudentManager {

    StudentBST studentBST = new StudentBST();
    ScholarshipList scholarshipList = new ScholarshipList();

    StudentManager() {
    }

    void fvisit(TreeNode p, java.io.RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void preOrder(TreeNode p, java.io.RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        fvisit(p, f);
        preOrder(p.left, f);
        preOrder(p.right, f);
    }

    void inOrder(TreeNode p, java.io.RandomAccessFile f) throws Exception {
        if (p == null) {
            return;
        }
        inOrder(p.left, f);
        fvisit(p, f);
        inOrder(p.right, f);
    }

    void ftraverse(java.io.RandomAccessFile f) throws Exception {
        f.writeBytes("Student BST (Inorder Traversal):\r\n");
        inOrder(studentBST.root, f);
        f.writeBytes("\r\n");
        f.writeBytes("Scholarship List:\r\n");
        ListNode p = scholarshipList.head;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes(p.info + " ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception {
        studentBST.loadDataStudents(k);
        scholarshipList.loadInitialScholarship(k);
    }

    void f1() throws Exception {
        // Test insert() function in StudentBST and addLast() function in ScholarshipList
        load(0);
        String fname = "f1.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        ftraverse(f);
        f.close();
    }

    void f2() throws Exception {
        // Find and display student with highest GPA
        load(0);
        String fname = "f2.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        ftraverse(f);

        // Find student with highest GPA
        // --------------------------------------------------------
        Student highestGPA = studentBST.findHighestGPA();
        f.writeBytes("\r\nStudent with highest GPA: " + highestGPA + "\r\n");
        // --------------------------------------------------------

        f.close();
    }

    void f3() throws Exception {
        // Count students with GPA >= 3.5
        load(0);
        String fname = "f3.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        // Count students with GPA >= 3.5
        // --------------------------------------------------------
        f.writeBytes(String.valueOf(studentBST.countByGPA(3.5)));
        // YOUR CODE HERE
        // --------------------------------------------------------
        f.close();
    }

    void f4() throws Exception {
        // Apply scholarship funds to student accounts
        load(0);
        String fname = "f4.txt";
        java.io.File g123 = new java.io.File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        java.io.RandomAccessFile f = new java.io.RandomAccessFile(fname, "rw");

        ftraverse(f);

        // Process scholarships - remove from list and add to student balance
        // --------------------------------------------------------
        int count = scholarshipList.count();
        for (int i = 0; i < count; i++) {
            if (studentBST.updateStudentBalance(scholarshipList.head.info.getId(), scholarshipList.search(scholarshipList.head.info.getId()).info.getBalance())) {
                scholarshipList.removeById(scholarshipList.head.info.getId());
            }
        }
        // YOUR CODE HERE
        // --------------------------------------------------------
        ftraverse(f);
        f.close();
    }
}
