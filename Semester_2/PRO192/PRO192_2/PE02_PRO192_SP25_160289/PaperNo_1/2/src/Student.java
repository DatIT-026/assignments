public class Student extends Person {
    private int gpa;

    public Student(String id, String name, String email, int gpa) {
        super(id, name, email);
        this.gpa = gpa;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        if (gpa < 0 || gpa > 100) this.gpa = 0;
        this.gpa = gpa;
    }
    
    public double getReward() {
        if (70 <= gpa && gpa < 80) return 7000 * ((double)gpa / 100);
        if (80 <= gpa && gpa < 90) return 8000 * ((double)gpa / 100);
        if (90 <= gpa && gpa <= 100) return 9000 * ((double)gpa / 100);
        
        return 0;
    }

    public String toString() {
        return String.format("%s, %s, %s, %.2f", super.getId(), super.getName().toUpperCase(), super.getEmail(), getReward());
    }
}