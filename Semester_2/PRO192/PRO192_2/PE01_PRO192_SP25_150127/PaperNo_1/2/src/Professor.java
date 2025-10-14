public class Professor extends Employee {

    private String department;
    
    public Professor() {
        super();
        this.department = "";
    }
    

    public Professor(int id, String name, double standardSalary, String department) {
        super(id, name, standardSalary);
        this.department = department;
    }
    

    public String getDepartment() {
        return department.toUpperCase();
    }
    

    public double calculateActualSalary() {
        double k = 1.0;
        
        if (department.equalsIgnoreCase("GD")) {
            k = 1.5;
        } else if (department.equalsIgnoreCase("IT")) {
            k = 2.0;
        }
        
        return getStandardSalary() * k;
    }
    

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getDepartment() + "," + 
               String.format("%.2f\n", calculateActualSalary());
    }
}