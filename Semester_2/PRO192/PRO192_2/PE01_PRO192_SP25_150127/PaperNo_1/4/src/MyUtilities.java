public class MyUtilities implements IUtilities {
    
    @Override
    public int getLengthOfLongestWord(String sentence) {
  
        String[] words = sentence.split(" ");
        
 
        int maxLength = 0;
        
      
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        
        return maxLength;
    }
    
    @Override
    public double calculateAverageValue(String str) {
        int sum = 0;       
        int count = 0;      
        

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
      
            if (Character.isDigit(c)) {
        
                int digit = Character.digit(c, 10);
                
          
                if (digit % 2 == 0) {
                    sum += digit;
                    count++;
                }
            }
        }
        
   
        if (count > 0) {
            return (double) sum / count;
        } else {
            return 0.0; 
        }
    }
}
