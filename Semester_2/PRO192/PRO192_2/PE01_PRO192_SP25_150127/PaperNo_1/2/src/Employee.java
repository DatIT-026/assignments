public class Employee {

    private int id;
    private String name;
    private double standardSalary;

    public Employee() {
        this.id = 0;
        this.name = "";
        this.standardSalary = 0.0;
    }
    

    public Employee(int id, String name, double standardSalary) {
        this.id = id;
        this.name = name;
        this.standardSalary = standardSalary;
    }
    

    public int getId() {
        return id;
    }
    

    public String getName() {
        return name.toUpperCase();
    }
    

    public double getStandardSalary() {
        return standardSalary;
    }
}