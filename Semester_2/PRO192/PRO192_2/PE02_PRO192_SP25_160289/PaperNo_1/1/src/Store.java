public class Store {
    private String name;
    private String location;
    private int inventoryCount;
    private int capacityLimit;

    public Store(String name, String location, int inventoryCount, int capacityLimit) {
        this.name = name    ;
        this.location = location;
        this.inventoryCount = (inventoryCount < 50) ? 50 : inventoryCount;
        this.capacityLimit = capacityLimit;
    }

    public String getName() {
        if (name.length() <= 5) return "N/A";
        return name.toUpperCase();
    }
        
    public int getAvailableCapacity() {
        if (capacityLimit < inventoryCount) return inventoryCount * 4;
        return capacityLimit - inventoryCount; 
    }

    @Override
    public String toString() {
        return String.format("%s-%s-%d-%d", name, location, inventoryCount, getAvailableCapacity());
    }
}