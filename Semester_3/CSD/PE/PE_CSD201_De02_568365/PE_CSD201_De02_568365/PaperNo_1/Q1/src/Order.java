/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Order {
    String orderID;
    String customerName;
    double totalPrice;

    public Order(String orderID, String customerName, double totalPrice) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
    
    public String getID() {
        return this.orderID;
    }

    public void setID(String id) {
        this.orderID = id;
    }

    public String getName() {
        return this.customerName;
    }

    public void setName(String name) {
        this.customerName = name;
    }

    public double getPrice() {
        return this.totalPrice;
    }

    public void setPrice(double price) {
        this.totalPrice = price;
    }
    @Override
    public String toString() {
        return orderID + " | " + customerName + " | " + totalPrice;
    }
}

