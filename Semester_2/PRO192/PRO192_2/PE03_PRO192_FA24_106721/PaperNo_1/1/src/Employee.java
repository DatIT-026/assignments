public class Employee {
    private int id;
    private String name;
    private double salary;
    private int seniority;
    
    public Employee() {}
    
    public Employee(int id, String name, double salary, int seniority) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.seniority = seniority;
    }
    
    public void setSeniority(int seniority) {
        if (seniority <= 0) this.seniority = 1;
        this.seniority = seniority;
    }
    
    @Override
    public String toString() {
        return String.format("%d, %s, %.2f, %d", id, name, salary, seniority);
    }
    
    public void updateSalary() {
        if (seniority >= 3 && seniority <= 5) this.salary = this.salary * 1.3;
        if (seniority >= 6 && seniority <= 10) this.salary = this.salary * 1.5;
        if (seniority >= 10) this.salary = salary;
    }
}
