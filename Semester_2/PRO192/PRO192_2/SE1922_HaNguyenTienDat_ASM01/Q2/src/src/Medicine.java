public class Medicine {
    private String name;
    private String indication;
    private int expirationYear;
    
    public Medicine() {}
    
    public Medicine(String name, String indication, int expirationYear) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.indication = indication.substring(0, 1).toUpperCase() + indication.substring(1).toLowerCase();        
        this.expirationYear = expirationYear;
    }
    
    public String getName() { return name; }  
    
    public String getIndication() { return indication; }
    
    public int getExpirationYear() { return expirationYear; }
    
    @Override
    public String toString() {
        return name + ", " + indication + ", " + expirationYear;
    }
}

class PrescriptionMedicine extends Medicine {
    private String doctorName;
    
    public PrescriptionMedicine() {}
    
    public PrescriptionMedicine(String name, String indication, int expirationYear, String doctorName) {
        super(name, indication, expirationYear);
        this.doctorName = doctorName.substring(0, 1).toUpperCase() + doctorName.substring(1).toLowerCase();
    }
    
    public String getDoctorName() { return doctorName; }

    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String isExpired(int currentYear) {
        return getExpirationYear() < currentYear ? "Expired" : "Valid";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", " + doctorName;
    }
}

