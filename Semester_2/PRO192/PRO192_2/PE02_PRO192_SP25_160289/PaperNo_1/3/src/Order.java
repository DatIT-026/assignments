public class Order {
    private String orderId;
    private String customerName;
    private double sale;

    public Order() {}
    
    public Order(String orderId, String customerName, double sale) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.sale = sale;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %.2f", orderId.toUpperCase(), customerName.toUpperCase(), sale);
    } 
}