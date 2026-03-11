/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author datto
 */
public class Cart implements Serializable {
    private Map<Integer, CartItem> items;

    public Map<Integer, CartItem> getItems() {
        if (items == null) items = new HashMap<>();
        return items;
    }
    
    public void add(ProductDTO product) {
        if (items == null) items = new HashMap<>();
        
        int id = product.getProductID();
        if (items.containsKey(id)) {
            CartItem existingItem = items.get(id);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            items.put(id, new CartItem(product, 1));
        }
    }
    
    public void update(int productID, String method) {
        if (items == null || !items.containsKey(productID)) return;
        
        CartItem item = items.get(productID);
        if ("increase".equals(method)) {
            item.setQuantity(item.getQuantity() + 1);
        } else if ("decrease".equals(method)) {
            int newQty = item.getQuantity() - 1;
            if (newQty > 0) {
                item.setQuantity(newQty);
            } else {
                items.remove(productID);
            }
        }
    }
    
    public void remove(int productID) {
        if (items != null) {
            items.remove(productID);
        }
    }
    
    public double getTotal() {
        double total = 0;
        if (items != null) {
            for (CartItem item : items.values()) {
                total += item.getQuantity() * item.getProduct().getPrice();
            }
        }
        return total;
    }
}