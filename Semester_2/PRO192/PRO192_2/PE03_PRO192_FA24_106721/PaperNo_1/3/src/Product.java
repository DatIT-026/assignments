public class Product {
    private int id;
    private String name;
    private double price;
    
    public Product() {}
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name.toUpperCase();
        this.price = price;
    }
    
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name.toUpperCase(); }

    public void setPrice(double price) { this.price = price; }
    
    @Override
    public String toString() {
        return String.format("%d, %s, %.2f", getId(), getName(), getPrice());
    }
}
