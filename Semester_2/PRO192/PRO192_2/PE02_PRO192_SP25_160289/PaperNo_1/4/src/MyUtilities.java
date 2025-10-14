public class MyUtilities implements IUtilities {

    @Override
    public char getCharacterByIndex(String str, int i) {
        if (str == null || i < 0 || i > str.length()) return '@';
        return str.toUpperCase().charAt(i);
    }

    @Override
    public int getPrime(int n) {
        if (n <= 0) return 0;
        
        int count = 0, result = 2;
        
        while(true) {
            boolean isPrime = true;
            if (result < 2) isPrime = false;
            
            else {
                for (int i = 2; i * i <= result; i++) {
                    if (result % i == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            
            if (isPrime) {
                count++;
                if(count == n) return result;
            }
            result++;
        }
    }
}