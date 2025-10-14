public class MyClass implements IProcess {

    @Override
    public int countWords(String str1, String str2) {
        int count = 0;
        String[] word = str1.split(" ");
        
        if (str1.isEmpty() || str2.isEmpty()|| str1 == null || str2 == null) return 0;
        
        for (int i = 0; i < word.length; i++) if (word[i].equalsIgnoreCase(str2)) count++;
        
        return count;
    }

    @Override
    public String getLastWord(String str) {
        String[] word = str.split(" ");
        
        if (str.isEmpty()) return "0";
        
        String up = Character.toUpperCase(word[word.length - 1].charAt(0)) + word[word.length - 1].substring(1);        
        return up;
    }
}
