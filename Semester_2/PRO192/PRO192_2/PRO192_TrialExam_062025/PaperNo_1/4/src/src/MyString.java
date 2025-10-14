public class MyString implements IString {

    // count the number of string has at least 1 odd digit
    @Override
    public int f1(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                int digit = Character.getNumericValue(c);
                if (digit % 2 != 0) count++;
            }
        }
        return count;
    }

    // count the first palindrom and replace this string to "XX"
    @Override
    public String f2(String str) {
        String[] parts = str.split(" ");
        for (int i = 0; i < parts.length; i++) {
            String word = parts[i];
            String reversed = new StringBuilder(word).reverse().toString();
            if (word.equals(reversed)) {
                parts[i] = "XX";
                break;
            }
        }
        return String.join(" ", parts);
    }

}