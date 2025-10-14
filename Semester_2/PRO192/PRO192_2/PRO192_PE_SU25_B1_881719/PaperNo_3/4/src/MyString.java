import java.util.*;

public class MyString implements IString{
    @Override
    public int f1(String str) {
        int[] letters = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(Character.isLetter(c) && Character.isUpperCase(c)) letters[c - 'A']++;
        }
        int ascii = 0, index = -1;
        for (int i = 0; i < 26; i++) {
            if(letters[i] > ascii) {
                ascii = letters[i];
                index = i;
            }
        }
        return (ascii == 0) ? -1 : index + (int) 'A';
    }

    @Override
    public String f2(String t) {
        
        return null;
        
    }
}
