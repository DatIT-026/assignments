package q1;
import java.io.File;
import java.io.RandomAccessFile;

public class Assignment {
    TaskBST taskBst;
    CompletionQueue completionQueue;
    String dataFile = "data.txt";

    public Assignment() {
        taskBst = new TaskBST();
        completionQueue = new CompletionQueue();
    }

    public void loadData() {
        int numTasks = Integer.parseInt(Lib.readLineToStr(dataFile, 0));
        String[] ids = Lib.readLineToStrArray(dataFile, 1);
        String[] descs = Lib.readLineToStrArray(dataFile, 2);
        String[] priorities = Lib.readLineToStrArray(dataFile, 3);

        for (int i = 0; i < numTasks; i++) {
            taskBst.insert(new Task(ids[i], descs[i], Integer.parseInt(priorities[i])));
        }
    }

    public void f1() throws Exception {
        String fname = "f1.txt";
        File f = new File(fname);
        if (f.exists()) f.delete();
        RandomAccessFile rf = new RandomAccessFile(fname, "rw");
        
        rf.writeBytes(">>> Testing f1: CompletionQueue isEmpty() and enqueue()\r\n");
        CompletionQueue q = new CompletionQueue();
        rf.writeBytes("Is queue empty initially? " + (q.isEmpty() ? "Yes" : "No") + "\r\n");
        rf.writeBytes("Enqueuing two tasks...\r\n");
        q.enqueue(new Task("C01", "Completed_A", 99));
        q.enqueue(new Task("C02", "Completed_B", 100));
        rf.writeBytes("Is queue empty now? " + (q.isEmpty() ? "Yes" : "No") + "\r\n");
        rf.writeBytes("\r\nFinal Queue State:\r\n");
        q.ftraverse(rf);
        
        rf.close();
    }

    public void f2() throws Exception {
        String fname = "f2.txt";
        File f = new File(fname);
        if (f.exists()) f.delete();
        RandomAccessFile rf = new RandomAccessFile(fname, "rw");
        
        rf.writeBytes(">>> Testing f2: Inserting tasks into BST...\r\n");
        loadData();
        rf.writeBytes("\r\n--- Final State ---\r\n");
        taskBst.ftraverse(rf, 1);
        rf.close();
    }
    
    public void f3() throws Exception {
        String fname = "f3.txt";
        File f = new File(fname);
        if (f.exists()) f.delete();
        RandomAccessFile rf = new RandomAccessFile(fname, "rw");

        loadData();
        rf.writeBytes(">>> Testing f3: Traversing the BST pre-order...\r\n");
        rf.writeBytes("\r\n--- Final State ---\r\n");
        taskBst.ftraverse(rf, 2);
        rf.close();
    }
    
    public void f4() throws Exception {
        String fname = "f4.txt";
        File f = new File(fname);
        if (f.exists()) f.delete();
        RandomAccessFile rf = new RandomAccessFile(fname, "rw");

        loadData();
        rf.writeBytes("--- Initial State ---\r\n");
        taskBst.ftraverse(rf, 1);

        int priorityToRemove = Integer.parseInt(Lib.readLineToStr(dataFile, 4));
        rf.writeBytes("\r\n>>> Testing f4: Removing task with priority " + priorityToRemove + "...\r\n");
        taskBst.remove(priorityToRemove);

        rf.writeBytes("\r\n--- Final State ---\r\n");
        taskBst.ftraverse(rf, 1);
        rf.close();
    }
}