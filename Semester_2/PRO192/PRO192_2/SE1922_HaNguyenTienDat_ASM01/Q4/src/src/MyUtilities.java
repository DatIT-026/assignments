public class MyUtilities implements IUtilities {
    @Override
    public int sumNumber(int number) {
        int sum = 0;
        for(int i = 1; i <= number/2; i++) if(number % i == 0) sum += i;
        
        return sum;
    }

    @Override
    public String replaceString(String sentence, String s1, String s2) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) if(words[i].equalsIgnoreCase(s1)) words[i] = s2;
        return String.join(" ", words);
    }
}

