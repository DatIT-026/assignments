public class Furniture {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Furniture() {}

    public Furniture(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getSubTotal() {
        double subTotal = quantity * price;
        if (50 <= subTotal && subTotal < 100) subTotal *= 0.9;
            else if (100 <= subTotal && subTotal <= 500) subTotal *= 0.8;
        
        return subTotal;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + String.format("%.2f", price) + ", " + quantity + ", " + String.format("%.2f", getSubTotal());
    }
}