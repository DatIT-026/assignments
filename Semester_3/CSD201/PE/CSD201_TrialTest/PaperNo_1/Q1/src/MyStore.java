/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

class FruitList {

    Node head, tail;

    FruitList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void loadDataFruit(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

    void addLast(String type, int amount, int price) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------

        Fruit fr = new Fruit(type, amount, price);
        Node newNode = new Node(fr);

        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;

        //---------------------------------------------------------
    }

}

class RequestQueue {

    Node front, rear;

    RequestQueue() {
        front = rear = null;
    }

    boolean isEmpty() {
        return (front == null);
    }

    void clear() {
        front = rear = null;
    }

    void loadDataRequest(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k + 3);
        int[] b = Lib.readLineToIntArray("data.txt", k + 4);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            enQueue(a[i], b[i]);
        }
    }

    void enQueue(String type, int amount) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------

        Fruit fr = new Fruit(type, amount);
        Node newNode = new Node(fr);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

        //---------------------------------------------------------
    }

    Fruit deQueue() {
        Fruit tmp = new Fruit();
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------

        if (isEmpty()) {
            return null;
        }

        tmp = front.info;
        front = front.next;
        if (front == null) {
            rear = null;
        }

        //---------------------------------------------------------
        return tmp;
    }

}

class MyStore {

    FruitList FList = new FruitList();
    RequestQueue RQueue = new RequestQueue();

    MyStore() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = FList.head;
        f.writeBytes("Data Fruit: ");
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
        f.writeBytes("Request   : ");
        p = RQueue.front;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes("(" + p.info.getType() + "," + p.info.getAmount() + ") ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception {
        FList.loadDataFruit(k);
        RQueue.loadDataRequest(k);
    }

    void f1() throws Exception {
        load(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        f.close();
    }

    int purchase(Fruit request) {
        Node curr = FList.head;
        int totalRevenue = 0;

        while (curr != null) {
            if (request.getType().equals(curr.info.getType())
                    && request.getAmount() <= curr.info.getAmount()) {

                totalRevenue += curr.info.getPrice() * request.getAmount();
                curr.info.setAmount(curr.info.getAmount() - request.getAmount());
            }

            curr = curr.next;
        }

        return totalRevenue;
    }

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------

        Fruit currRequest = RQueue.deQueue();
        purchase(currRequest);

        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void f3() throws Exception {
        load(1);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------

        Fruit currRequest = RQueue.deQueue();

        while (currRequest != null) {
            purchase(currRequest);
            currRequest = RQueue.deQueue();
        }

        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void f4() throws Exception {
        load(1);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        int S = 0;
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------

        int totalRevenue = 0;
        Fruit currRequest = RQueue.deQueue();

        while (currRequest != null) {
            totalRevenue += purchase(currRequest);
            currRequest = RQueue.deQueue();
        }

        S = totalRevenue;

        //---------------------------------------------------------
        f.writeBytes("Money     : " + S + " ");
        f.close();
    }

}
