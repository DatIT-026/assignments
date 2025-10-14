import java.util.*;

public class MyCala implements ICala {

    // Count objects where 2nd character of owner is a digit
    @Override
    public int f1(List<Cala> t) {
        int count = 0;
        for (Cala i : t) {
            String owner = i.getOwner();
            if (owner.length() >= 2) {
                char secondChar = owner.charAt(1);
                if (Character.isDigit(secondChar)) count++;
            }
        }
        return count;
    }

    // Remove the 2nd Cala with the highest price
    @Override
    public void f2(List<Cala> t) {
        if (t.size() < 2)
            return;

        int maxPrice = -1;
        for (Cala i : t) {
            if (i.getPrice() > maxPrice)
                maxPrice = i.getPrice();
        }

        int matchCount = 0;
        for (int i = 0; i < t.size(); i++) {
            if (t.get(i).getPrice() == maxPrice) {
                matchCount++;
                if (matchCount == 2) {
                    t.remove(i);
                    break;
                }
            }
        }
    }

    // Sort list by 2nd character of owner name
    @Override
    public void f3(List<Cala> t) {
        Collections.sort(t, (a, b) -> {
           char charA = a.getOwner().length() > 1 ? a.getOwner().charAt(1) : ' ';
           char charB = b.getOwner().length() > 1 ? b.getOwner().charAt(1) : ' ';
           return Character.compare(charA, charB);
        });
    }
}
