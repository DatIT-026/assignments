package q1;
import java.io.RandomAccessFile;

public class CompletionQueue {
    private class QueueNode {
        Task task;
        QueueNode next;
        QueueNode(Task t) { this.task = t; this.next = null; }
    }

    private QueueNode front, rear;
    public CompletionQueue() { this.front = this.rear = null; }

    public void ftraverse(RandomAccessFile f) throws Exception {
        f.writeBytes("--- Completion Queue ---\r\n");
        if (isEmpty()) {
            f.writeBytes("Empty\r\n");
        } else {
            QueueNode current = front;
            while (current != null) {
                f.writeBytes(current.task.toString() + "\r\n");
                current = current.next;
            }
        }
    }

    // =======================================================
    // === f1: STUDENT IMPLEMENTS THESE METHODS            ===
    // =======================================================
    public boolean isEmpty() {
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)        
        if (front == null && rear == null) {
            return true;
        }
        return false;
        // -----------------------------------------------------
    }

    public void enqueue(Task task) {
        // ---------- Student's code starts from here ----------
        // Students are welcomed to use any helper function(s)
        
        QueueNode p = new QueueNode(task);
        if (isEmpty()) {
          front = rear = p;
        } else {
          rear.next = p;
          rear = p;
        }
        
        return;
        // -----------------------------------------------------
    }
    // =======================================================
}