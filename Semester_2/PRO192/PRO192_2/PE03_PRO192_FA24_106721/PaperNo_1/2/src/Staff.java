public class Staff {
    private int id;
    private int salary;
    
    public Staff() {}
    
    public Staff(int id, int salary) {
        this.id = id;
        this.salary = salary;
    }
    
    public int getId() { return id; }
    public int getSalary() { return salary; }
    
    public void setSalary(int salary) { 
        if (1000 > salary) this.salary = 1000;
        this.salary = salary; 
    }
    
    @Override
    public String toString() {
        return String.format("%d, %d", id, salary);
    }
}
