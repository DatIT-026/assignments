
import java.util.ArrayList;
import java.util.Collections;

public class Furniture {
    private int id;
    private String name;
    private int quantity;
    
    Furniture() {};
    
    Furniture(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return id + ", " + name + ", " + quantity;
    }
}

class FurnitureList extends ArrayList<Furniture> {
    public void addFurniture(Furniture furniture) {
        add(furniture);
    }
    
    public String getNameById(int id) {
        for(Furniture f : this) if (f.getId() == id) return f.getName().toUpperCase();
        
        return "N/A";
    }
    
    public FurnitureList getFurnitureList() {
        Collections.sort(this, (f1, f2) -> f2.getQuantity() - f1.getQuantity());
        return this;
    }
    
    public int getTotalQuantity() {
        int total = 0;
        for (Furniture f : this) {
            total += f.getQuantity();
        }
        
        return total;
    }
}