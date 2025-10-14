import java.util.ArrayList;
import java.util.List;

public class OrderList extends ArrayList<Order> {
    public void addOrder(Order order) {
        this.add(order);
    }
    
    public double getSaleByOrderId(String orderId) {
        for (Order l : this) if(l.getOrderId().equalsIgnoreCase(orderId)) return l.getSale();
        return 0;
    }
    
    public List<Order> getOrdersBySale(double value) {
        List<Order> l = new ArrayList<>();
        
        for(Order t : this) {
            if (t.getSale() >= value) {
                l.add(t);
            }
        }
        return l.isEmpty() ? null : l;
    }
}