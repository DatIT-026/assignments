/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;
import java.io.*;


class ComputerStore {
    ActionLogStack actionLogStack = new ActionLogStack(); 
    OrderBST orderTree = new OrderBST();
    Scanner sc = new Scanner(System.in);

    ComputerStore() {
    }

    void load(int k) throws Exception
    {
        orderTree.loadData(k); 
        actionLogStack.loadData(k + 4);
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        f.writeBytes("Action Log Stack: ");
        if (actionLogStack.isEmpty()) {
            f.writeBytes("Empty");
        } else {
            ActionNode currentAction = actionLogStack.top;
            while (currentAction != null) {
                f.writeBytes("(" +  currentAction.info.getActionID() + "," + currentAction.info.getActionType() + ") ");
                currentAction = currentAction.next;
            }
        }
        f.writeBytes("\r\n");
        f.writeBytes("Order BST: ");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentTree = orderTree.root;
        while(currentTree != null || !stack.isEmpty()){
            while(currentTree != null){
                stack.push(currentTree);
                currentTree = currentTree.left;
            }
            currentTree = stack.pop();
            f.writeBytes("(" +  currentTree.info.getID() + "," + currentTree.info.getName() + "," + currentTree.info.getPrice()   + ") ");
            currentTree = currentTree.right;
        }
        f.writeBytes("\r\n");
    }
    
    String loadInput(int k)
    {
        String a = Lib.readLineToStr("data.txt", k);
        return a;
    }

//===========================================================================
//=======YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
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

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        String searchOrderID = loadInput(8); 
        System.out.println("Searching for Order ID in BST: " + searchOrderID);
        Order foundOrder = orderTree.search(searchOrderID);
        
        if (foundOrder != null){
            f.writeBytes("Found Order: ");
            f.writeBytes("(" +  foundOrder.getID() + "," + foundOrder.getName() + "," + foundOrder.getPrice()   + ") ");
            f.writeBytes("\r\n");
        }else{
            f.writeBytes("Order not found in BST");
            f.writeBytes("\r\n");
        }
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
        
        Order minOrder = orderTree.findMin();
        if (minOrder != null){
            f.writeBytes("Lowest Alphabetical Order ID in BST: ");
            f.writeBytes("(" +  minOrder.getID() + "," + minOrder.getName() + "," + minOrder.getPrice()   + ") ");
            f.writeBytes("\r\n");
        }else{
            f.writeBytes("Order BST is empty (no lowest ID found)");
            f.writeBytes("\r\n");
        }
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
        
        StoreAction poppedAction = actionLogStack.pop();
        if (poppedAction != null) {
            System.out.println("Popped from Action Log Stack: " + poppedAction.getActionID() + " (" + poppedAction.getActionType() + ")");
        } else {
            System.out.println("Action Log Stack is empty, nothing to pop.");
        }

        String deleteOrderID = loadInput(10);
        String deleteActionID = loadInput(12);

        System.out.println("Attempting to remove Order ID from BST: " + deleteOrderID);
        System.out.println("Attempting to remove Action ID from Stack: " + deleteActionID);

        orderTree.remove(deleteOrderID);
        actionLogStack.remove(deleteActionID);

        ftraverse(f);
        f.close();
    }
}
