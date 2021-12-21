package com.rei.interview.checkout;

import com.rei.interview.product.Product;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {
    private UUID cartId = UUID.randomUUID();
    private Map<Product,Integer> products = new ConcurrentHashMap<>();

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
    
    /*
     * Add a product to the Cart
     */
    public void setProduct(Product product, int quantity) {
        this.products.put(product, quantity);
    }
    
    /*
     * Remove a product from the Cart
     */
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
    
    /*
     * Add a product to the Cart
     */
    public void upodateProduct(Product product, int newQuantity) {
    	Integer quantity = this.products.get(product);
        this.products.put(product, quantity+newQuantity);
    }
        
}
