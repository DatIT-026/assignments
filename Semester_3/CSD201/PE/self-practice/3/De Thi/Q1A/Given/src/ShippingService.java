
import java.util.*;
import java.io.*;

class ItemList {

    Node head, tail;

    ItemList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void loadItemList(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int[] d = Lib.readLineToIntArray("data.txt", k + 3);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i], d[i]);
        }
    }

    void addLast(String name, int weight, int length, int cost) {
        if (weight > 0 && length > 0 && cost > 0) {
            Node q = new Node(new Item(name, weight, length, cost));
            if (this.isEmpty()) {
                head = tail = q;
            } else {
                tail.next = q;
                tail = q;
            }
        }
    }

}

class Truck {

    Node head;

    Truck() {
        head = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = null;
    }

    void loadTruck(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k + 4);
        int[] b = Lib.readLineToIntArray("data.txt", k + 5);
        int[] c = Lib.readLineToIntArray("data.txt", k + 6);
        int[] d = Lib.readLineToIntArray("data.txt", k + 7);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            push(a[i], b[i], c[i], d[i]);
        }
    }

    void push(String name, int weight, int length, int cost) {
        if (weight > 0 && length > 0 && cost > 0) {
            Node q = new Node(new Item(name, weight, length, cost));
            if (this.isEmpty()) {
                head = q;
            } else {
                q.next = head;
                head = q;
            }
        }
    }

    void push(Node q) {
        if (this.isEmpty()) {
            head = q;
        } else {
            q.next = head;
            head = q;
        }
    }

    Node pop() {
        if (isEmpty()) {
            return null;
        }
        Node tmp = head;
        head = head.next;
        return tmp;
    }
}

class ShippingService {

    ItemList list = new ItemList();
    Truck truck = new Truck();

    ShippingService() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = list.head;
        f.writeBytes("ItemList Inventory: ");
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f);
            p = p.next;
        }
        f.writeBytes("\r\n");

        f.writeBytes("Truck Contents: ");
        p = truck.head;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f);
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception //do not edit this function
    {
        list.loadItemList(k);
        truck.loadTruck(k);
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
        int total = 0;
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
       
        Node currList = list.head;
        Node currTruck = truck.head;
        
        while (currList != null) {
            total += currList.info.getCost();
            currList = currList.next;
        }
        
        while (currTruck != null) {
            total += currTruck.info.getCost();
            currTruck = currTruck.next;
        }
        
        //----------------------------------------------------------------------
        f.writeBytes("Total Revenue: " + total + "\n");
        f.close();
    }

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("Before:\n");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
       
        ItemList item = new ItemList();
        Node currList = list.head;
        
        while (currList != null) {
            if (currList.info.getWeight() < 50) {
                truck.push(
                    currList.info.getName(),
                    currList.info.getWeight(),
                    currList.info.getLength(),
                    currList.info.getCost()
                );
            }
            else {
                item.addLast(
                    currList.info.getName(),
                    currList.info.getWeight(),
                    currList.info.getLength(),
                    currList.info.getCost()
                );
            }
            currList = currList.next;
        }
        
        list = item;
        
        //----------------------------------------------------------------------
        f.writeBytes("After:\n");
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
        f.writeBytes("Before:\n");
        ftraverse(f);
        int total = 0;
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        
        ItemList item = new ItemList();
        Node currList = list.head;
        Node currTruck = truck.head;
        
        while (currList != null) {
            truck.push(
                currList.info.getName(),
                currList.info.getWeight(),
                currList.info.getLength(),
                currList.info.getCost()
            );
            list.clear();
            total += currList.info.getWeight();
            currList = currList.next;
        }
        
        while (currTruck != null) {
            total += currTruck.info.getWeight();
            currTruck = currTruck.next;
        }
        
        //----------------------------------------------------------------------
        f.writeBytes("After:\n");
        ftraverse(f);
        f.writeBytes("Total Weight: " + total + "\n");
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
        f.writeBytes("Before:\n");
        ftraverse(f);
        int total = 0;
        //You should write here appropriate statements to complete this function.
        //----------------------------------------------------------------------
        
        ItemList item = new ItemList();
        Node currList = list.head;
        Node currTruck = truck.head;
        
        while (currList != null) {
            if (currList.info.getLength() < 3) {
                truck.push(
                    currList.info.getName(),
                    currList.info.getWeight(),
                    currList.info.getLength(),
                    currList.info.getCost()
                );
                total += currList.info.getCost();
            }
            currList = currList.next;
        }
        
        while (currTruck != null) {
            total += currTruck.info.getCost();
            currTruck = currTruck.next;
        }
        
        list = item;
        
        //----------------------------------------------------------------------
        f.writeBytes("After:\n");
        ftraverse(f);
        f.writeBytes("Total Revenue: " + total + "\n");
        f.close();
    }

}
