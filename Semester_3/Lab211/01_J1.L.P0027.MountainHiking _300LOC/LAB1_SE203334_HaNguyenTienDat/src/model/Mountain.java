package model;

public class Mountain {
    private String code;
    private String name;
    private String location;
    private String description;
    
    public Mountain() {
        this.code = "";
        this.name = "";
        this.location = "";
        this.description = "";
    }
    
    public Mountain(String code, String name, String location, String description) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.description = description;
    }
    
    /*
        matches: so sanh chuoi 1 khop format voi chuoi 2 hay không?
        equals: so sanh chuoi 1 giong y chang voi chuoi 2 hay không?
        compareTo: so sanh 1 chuoi = hay lon hon hay nho hon chuoi 2 hay không?
        contains: ktra chuoi 1 co chua chuoi 2 hay không?
        startsWith: ktra chuoi 1 co bat dau bang chuoi 2 hay không?
    */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.matches(services.iConstants.MountainCodePattern)) {
            this.code = code;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("| %-5s | %-20s | %-12s |",
                code, name, location);
    }
}