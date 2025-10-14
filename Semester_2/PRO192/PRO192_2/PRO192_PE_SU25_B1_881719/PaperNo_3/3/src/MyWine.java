import java.util.*;

public class MyWine implements IWine {

    @Override
    public String f1(List<Wine> t) {
        String longest = "";
        for(Wine l : t) {
            if(l.getBrand().length() > longest.length()) if (l.getYear() % 3 == 0) longest = l.getBrand();
        } 
        return (longest.length() == 0) ? "Not found" : longest;
    }
    
    @Override
    public void f2(List<Wine> t) {
        boolean isPrime = true;
        for (Wine l : t) {
            for (int i = 2; i*i < l.getBrand().length(); i++) {
                if(l.getBrand().length() % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            
            if(l.getBrand().length() >= 2 && isPrime == true) {
                String temp = String.valueOf(l.getYear());
                l.setBrand("PRE" + temp.charAt(0));
            }
        }
        
    }

    @Override
    public void f3(List<Wine> t) {
        int max = 0;
        
        for(Wine l : t) if(max < l.getYear() && l.getYear() % 2 == 0) max = l.getYear();
       
        for(int i = 0; i < t.size(); i++) 
            if(Character.isUpperCase(t.get(i).getBrand().charAt(0)) && t.get(i).getYear() == max) 
                t.remove(i--);
    }
    
}