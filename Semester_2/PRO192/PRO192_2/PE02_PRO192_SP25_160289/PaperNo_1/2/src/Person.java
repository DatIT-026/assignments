public class Person {
    private String id;
    private String name;
    private String email;

    public Person() {}
    
    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = (email.contains("@") && email.contains(".")) ? email : "N/A";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", id, name.toUpperCase(), email);
    }
}