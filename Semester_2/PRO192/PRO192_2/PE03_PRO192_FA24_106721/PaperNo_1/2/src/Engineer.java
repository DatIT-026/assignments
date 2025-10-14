public class Engineer extends Staff {
    private int benefits;
    
    public Engineer(int id, int salary, int benefits) {
        super(id, salary);
        this.benefits = benefits;
    }
    
    public int getBenefit() { return benefits; }
    public void setBenefit(int benefits) { this.benefits = benefits; }
    
    public int getGrossSalary() {
        return super.getSalary() + benefits;
    }
    
    @Override
    public String toString() {
        return String.format("%d, %d, %d, %d", super.getId(), super.getSalary(), getBenefit(), getGrossSalary());
    }
}
