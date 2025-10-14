public class SpecWine extends Wine {
    private String taste;
        
    public SpecWine() {}
    public SpecWine(String brand, int year, String taste) {
        super(brand, year);
        this.taste = taste;
    }

    public String getTaste() {
        return taste;
    }

    public String toString() {
        return String.format("%s, %d, %s", super.getBrand(), super.getYear(), getTaste());
    }
    
    public void setData() {
        StringBuilder builder = new StringBuilder(getBrand().toLowerCase());
        builder.reverse();
        
        setBrand(builder.toString());
    }
    
    public String getValue() {
        int m = super.getYear();
        int n = super.getBrand().length();
        
        if (m < n) return getTaste().toUpperCase().substring(0,m) + getTaste().substring(m);
        return getTaste().toLowerCase();
    }
}