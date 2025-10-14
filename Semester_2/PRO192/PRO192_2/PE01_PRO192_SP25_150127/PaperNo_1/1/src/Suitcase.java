public class Suitcase {

    private int id;
    private String name;
    private double capacity;
    private int maxWeight;

    public Suitcase() {
        this.id = 0;
        this.name = "";
        this.capacity = 0.0;
        this.maxWeight = 0;
    }
    

    public Suitcase(int id, String name, double capacity, int maxWeight) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.maxWeight = maxWeight;
    }
    

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getCapacity() {
        return capacity;
    }
    
    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
    
    public int getMaxWeight() {
        return maxWeight;
    }
    
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
    

    public double expandSuitcase() {
        double newCapacity = capacity;
        
        if (maxWeight >= 15) {
            newCapacity = capacity + 5;
        } else if (maxWeight > 7 && maxWeight < 15) {
            newCapacity = capacity + 2;
        } else if (maxWeight <= 7) {
            newCapacity = capacity + 0;
        }
        
  
        this.capacity = newCapacity;
        return newCapacity;
    }
    
  
    @Override
    public String toString() {
        return id + 
               "," + name.toUpperCase() + 
               "," + String.format("%.2f", capacity) + 
               "," + maxWeight;
    }
}
