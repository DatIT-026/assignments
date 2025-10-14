public class asd {
    public static void main(String[] args) {
        String str = "asd hello world asd fuck";
       
        System.out.println(
             str.chars().filter(Character::isDigit).toArray()
        );
    }
}

class Dog {
    static public String bark() {
        return "aaaaaaaaaaaaaaaaaaaaaaa";
    }
}