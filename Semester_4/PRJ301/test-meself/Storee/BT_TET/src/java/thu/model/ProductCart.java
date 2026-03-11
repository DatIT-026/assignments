/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thu.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author C00kies
 */
public class ProductCart {
    
    public static class Cart implements Serializable {
        
        private Map<Integer, ProductDTO> products;
        
        public Map<Integer, ProductDTO> getProducts() {
            if (this.products == null) {
                this.products = new HashMap<>();
            }
            return products;
        }
        
        public void add(ProductDTO product) {
            if (this.products == null) {
                this.products = new HashMap<>();
            }
            
            int id = product.getProductID();
            
            if (this.products.containsKey(id)) {
                ProductDTO dtoInCart = this.products.get(id);
                dtoInCart.setStockQuantity(dtoInCart.getStockQuantity() + 1);
            } else {
                ProductDTO newCartItem = new ProductDTO(
                        product.getProductID(),
                        product.getProductName(),
                        product.getPrice(),
                        1,
                        product.getCategory(),
                        product.isStatus()
                );
                this.products.put(id, newCartItem);
            }
        }
        
        public void update(int productID, String method) {
            if (this.products == null || !this.products.containsKey(productID)) {
                return;
            }
            
            if (this.products.containsKey(productID)) {
                ProductDTO dto = this.products.get(productID);
                if (method.equals("increase")) {
                    dto.setStockQuantity(dto.getStockQuantity() + 1);
                } else if (method.equals("decrease")) {
                    int newQuantity = dto.getStockQuantity() - 1;
                    if (newQuantity > 0) {
                        dto.setStockQuantity(newQuantity);
                    } else this.products.remove(productID);
                }
            }
        }
    }
}