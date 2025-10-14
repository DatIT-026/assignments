import java.util.ArrayList;

public class WatchList extends ArrayList<Watch> {
    
    public void addWatch(Watch watch) {
        this.add(watch);
    }
    
 
    public WatchList filterByBrand(String value) {
        WatchList result = new WatchList();
        boolean found = false;
        
        for (Watch watch : this) {
            if (watch.getBrand().equalsIgnoreCase(value)) {
                result.add(watch);
                found = true;
            }
        }
        
        if (found) {
            return result;
        } else {
            return null;
        }
    }
    

    public Watch findMostExpensive() {
        if (this.isEmpty()) {
            return null;
        }
        
        Watch mostExpensive = this.get(0);
        
        for (Watch watch : this) {
            if (watch.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = watch;
            }
        }
        
        return mostExpensive;
    }
}