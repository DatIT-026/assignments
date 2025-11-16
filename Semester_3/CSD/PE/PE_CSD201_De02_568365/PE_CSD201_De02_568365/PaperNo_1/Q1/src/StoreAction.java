/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class StoreAction {
    String actionID;       
    String actionType;              

    public StoreAction(String actionID, String actionType) {
        this.actionID = actionID;
        this.actionType = actionType;
    }
    
    public String getActionID() {
        return actionID;
    }

    public String getActionType() {
        return actionType;
    }

    @Override
    public String toString() {
        return actionID + " | " + actionType + " | ";
    }
}
