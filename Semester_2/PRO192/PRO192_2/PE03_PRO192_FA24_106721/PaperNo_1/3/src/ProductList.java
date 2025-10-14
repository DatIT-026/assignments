import java.util.ArrayList;

public class ProductList extends ArrayList<Product> {
    public Product getProductById(int id) {
       for (Product p : this) if (p.getId() == id) return p;
            return null;
    }
    
    
    public Product getProductWithMinPrice() {
        Product min = this.get(0);
        for (Product p : this) if (p.getPrice() < min.getPrice()) min = p;
        return min;
    }
}
