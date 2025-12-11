/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class ActionLogStack {

    ActionNode top;
    int length;

    public ActionLogStack() {
        this.top = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    public int length() {
        return length;
    }

    public void push(String actionID, String actionType) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        StoreAction stAction = new StoreAction(actionID, actionType);
        ActionNode actNode = new ActionNode(stAction);
        if (top == null) {
            top = actNode;
        } else {
            actNode.next = top;
            top = actNode;
        }
        length++;
        //---------------------------------------------------------
    }

    public StoreAction pop() {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (top != null) {
            ActionNode pop = new ActionNode(top.info);
            top = top.next;
            length--;
            return pop.info;
        }
        //---------------------------------------------------------
        return null;
    }

    public StoreAction peek() {
        if (isEmpty()) {
            return null;
        }
        return this.top.info;
    }

    public boolean remove(String id) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        if (top == null) {
            return false;
        }
        if (top.info.actionID.equals(id)) {
            top = top.next; 
            length--; 
            return true;
        }

        ActionNode prev = top;
        ActionNode cur = top.next;

        while (cur != null) {
            if (cur.info.actionID.equals(id)) {
                prev.next = cur.next;
                length--; 
                return true;
            }
            prev = cur;
            cur = cur.next;
        } 
        //--------------------------------------------------------
        return false;
    }

    void loadData(int k) {
        String[] actionIDs = Lib.readLineToStrArray("data.txt", k);
        String[] actionTypes = Lib.readLineToStrArray("data.txt", k + 1);

        int n = actionIDs.length;
        for (int i = 0; i < n; i++) {
            push(actionIDs[i], actionTypes[i]);
        }
    }

    public void displayActions() {
        if (isEmpty()) {
            System.out.println("Action Log Stack is empty. No actions to display.");
            return;
        }
        ActionNode current = this.top;
        while (current != null) {
            System.out.println(current.info.actionID + " --> ");
            current = current.next;
        }
        System.out.println("END");
    }
}
