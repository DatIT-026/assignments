public class Item {

    private String name;
    private int weight;
    private int length;
    private int cost;

    public Item(String name, int weight, int length, int cost) {
        this.name = name;
        this.weight = weight;
        this.length = length;
        this.cost = cost;
    }

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + weight + ", " + length + ", " + cost + ")";
    }
}
